create table cars(
id serial primary key,
	brand varchar(255),
	model varchar(255),
	age int,
	price money
);

insert into cars(brand, model, age, price) values('Honda', 'Civic', 6, 1000000);
insert into cars(brand, model, age, price) values('Honda', 'Accord', 4, 1500000);

---Уровни изолированности транзакций:

---Чтение неподтвержденных данных, грязное чтение (read uncommitted, dirty read) нет в PostgreSQL
---– данный уровень изолированности позволяет читать незафиксированные изменения (до подтверждения или отмены транзакции) выполненных любой транзакцией (как той, что читает данные, так работающая параллельно с ней).
set session transaction isolation level read uncommitted;

---Чтение подтвержденных данных (read committed) –СУБД PostgreSQL выбирает уровень изоляции транзакции Read Committed по умолчанию
---данный уровень изолированности транзакций допускает чтение всех изменений, которые выполнены транзакцией, и только зафиксированных изменений, выполненных другими параллельными транзакциями.
begin transaction;
BEGIN
insert into cars(brand, model, age, price) values('BMW', 'X5', 5, 3000000);
delete from cars where age = 4;
update cars set price = 1200000 where model = 'Civic';
commit;

---Повторяющееся чтение (repeatable read)
---этот уровень изолированности допускает только чтение изменений, которые выполнены самой транзакцией, а прочитанные ею данные будут недоступны для изменения параллельными транзакциями.
begin transaction isolation level repeatable read;

---Снимок (snapshot)
--- поддерживается не всеми СУБД. Он допускает только чтений изменений, которые выполнены самой транзакцией, а прочитанные транзакцией данные остаются доступны для изменения параллельными транзакциями (в этом и есть основное отличие от уровня повторяющегося чтения).

---Сериализация (serializable)
---этот уровень допускает только выполнение изменений данных, как будто все модифицирующие данные транзакции выполняются не параллельно, а последовательно.
begin transaction isolation level serializable;
BEGIN
select sum(price) from cars;
update cars set price = 1500000 where brand = 'Honda';
commit;