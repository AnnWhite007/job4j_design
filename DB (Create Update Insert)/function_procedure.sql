create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

---Хранимая функция (обязана возвращать значение, можно использовать в любом SQL-запросе)
--- функция удаляет записи если количество товара равно 0

create or replace function f_delete_data(f_count integer, f_id integer)
returns varchar
language 'plpgsql'
as
$$
    declare
        result varchar;
    begin
        if f_count = 0 THEN
            select into result name from products where id = f_id;
            delete from products where id = f_id;
        end if;
        return result;
    end;
$$;

select f_delete_data(0, 2);

---Хранимая процедура (имеет особый синтаксис и ряд ограничений)
--- процедура удаляет записи если количество товара равно 0
create or replace procedure p_delete_data(p_count integer, p_id integer)
language 'plpgsql'
as $$
    BEGIN
    if p_count = 0 THEN
            delete from products where id = p_id;
        end if;
    END
$$;

call delete_data(0, 2);