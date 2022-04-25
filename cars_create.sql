create table cars(
id serial primary key,
	brand varchar(255),
	model varchar(255),
	age int,
	price money
);
insert into cars(brand, model, age, price) values('Honda', 'Civic', 6, 1000000);
insert into cars(brand, model, age, price) values('Honda', 'Accord', 4, 1500000);
update cars set brand = 'HONDA';
delete from cars;
select * from cars;
