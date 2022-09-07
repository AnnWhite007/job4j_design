create table products (
    id serial primary key,
    name varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, count, price) VALUES ('product_1', 1, 5);
insert into products (name, count, price) VALUES ('product_2', 2, 10);
insert into products (name, count, price) VALUES ('product_3', 3, 15);
insert into products (name, count, price) VALUES ('product_4', 4, 20);
insert into products (name, count, price) VALUES ('product_5', 5, 25);
insert into products (name, count, price) VALUES ('product_6', 6, 30);
insert into products (name, count, price) VALUES ('product_7', 7, 35);
insert into products (name, count, price) VALUES ('product_8', 8, 40);
insert into products (name, count, price) VALUES ('product_9', 9, 45);
insert into products (name, count, price) VALUES ('product_10', 10, 50);
insert into products (name, count, price) VALUES ('product_11', 11, 55);
insert into products (name, count, price) VALUES ('product_12', 12, 60);
insert into products (name, count, price) VALUES ('product_13', 13, 65);
insert into products (name, count, price) VALUES ('product_14', 14, 70);
insert into products (name, count, price) VALUES ('product_15', 15, 75);
insert into products (name, count, price) VALUES ('product_16', 16, 80);
insert into products (name, count, price) VALUES ('product_17', 17, 85);
insert into products (name, count, price) VALUES ('product_18', 18, 90);
insert into products (name, count, price) VALUES ('product_19', 19, 95);
insert into products (name, count, price) VALUES ('product_20', 20, 100);


---Курсор – это объект, который позволяет отдельно обрабатывать строки из результирующего набора данных, который возвращается оператором SELECT. Строки при этом можно перебирать последовательно.
---1. Объявить курсор – используется команда DECLARE имя_курсора ([[NO] SCROLL] – указание того, что курсор можно прокручивать или нет назад. FORWARD и BACKWARD направления)
---2. Открыть курсор – используется команда OPEN имя_курсора;
---3. Чтение следующей строки из курсора – используется команда FETCH имя_курсора;
---4. Закрыть курсор – используется команда CLOSE имя_курсора;
---5. Удалить курсор из памяти – используется команда DEALLOCATE имя_курсора.
begin transaction;
DECLARE
    cursor_products SCROLL cursor for select * from products;
MOVE LAST FROM cursor_products;
MOVE NEXT FROM cursor_products;
FETCH BACKWARD ALL FROM cursor_products;
CLOSE cursor_products;
COMMIT;


