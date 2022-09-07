create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

---Хранимая функция (обязана возвращать значение, можно использовать в любом SQL-запросе)
--- функция удаляет записи если количество товара равно 0

create or replace function f_delete_data(f_id integer)
returns varchar
language 'plpgsql'
as
$$
    declare
        result varchar;
    begin
            select into result name from products where id = f_id and count = 0;
            delete from products where id = f_id and count = 0;
        return result;
    end;
$$;

select f_delete_data(2);

---Хранимая процедура (имеет особый синтаксис и ряд ограничений)
--- процедура удаляет записи если количество товара равно 0
create or replace procedure p_delete_data(p_id integer)
language 'plpgsql'
as $$
    BEGIN
            delete from products where id = p_id and count = 0;
    END
$$;

call delete_data(2);