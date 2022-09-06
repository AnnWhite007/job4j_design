create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

---1. Триггер должен срабатывать после вставки данных, для любого товара и просто насчитывать налог на товар (нужно прибавить налог к цене товара).
---Действовать он должен не на каждый ряд, а на запрос (statement уровень)
create trigger nalog_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure nalog();

create or replace function nalog()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.18
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

---2. Триггер должен срабатывать до вставки данных и насчитывать налог на товар (нужно прибавить налог к цене товара). Здесь используем row уровень.
create trigger nalog_before_trigger
    before insert
    on products
    for each row
    execute procedure nalog_before();

create or replace function nalog_before()
    returns trigger as
$$
    BEGIN
        new.price = new.price + new.price * 0.18;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

---3.  Нужно написать триггер на row уровне, который при вставке продукта в таблицу products, будет заносить имя, цену и текущую дату в таблицу history_of_price.
create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create trigger price_history
    after insert
    on products
    for each row
    execute procedure history();

create or replace function history()
    returns trigger as
$$
    BEGIN
        insert into history_of_price(name, price, date) values (new.name, new.price, now());
    END;
$$
LANGUAGE 'plpgsql';

