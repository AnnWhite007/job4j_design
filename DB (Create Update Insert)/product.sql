create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
	name varchar(255),
    type_id int references type(id),
	expired_date date,
	price money
);

insert into type(name) values ('Молоко'), ('Мороженое'), ('Сыр');
insert into product(name, type_id, expired_date, price) values ('Топленое молоко', 1, '2022-05-01', 65), ('Шоколадное молоко', 1, '2022-06-10', 100), ('Отборное молоко', 1, '2022-06-05', 70);
insert into product(name, type_id, expired_date, price) values ('Мороженое пломбир', 2, '2022-03-01', 450), ('Мороженое крембрюле', 2, '2022-08-01', 350), ('Мороженое рожок', 2, '2022-5-25', 400);
insert into product(name, type_id, expired_date, price) values ('Голландия', 3, '2022-02-26', 250), ('Моцарелла', 3, '2022-09-01', 150);

---Написать запрос получение всех продуктов с типом "СЫР"
select p.name
from product as p
join type t 
on p.type_id = t.id
where t.name = 'Сыр';

---Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
select p.name
from product as p 
join type t 
on p.type_id = t.id
where p.name like '%Мороженое%';

---Написать запрос, который выводит все продукты, срок годности которых уже истек
select p.name
from product as p 
join type t 
on p.type_id = t.id
where p.expired_date < current_date;

---Написать запрос, который выводит самый дорогой продукт.
select name
from product
where price = (select max(price) from product);

---Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих.
select t.name, count(p.type_id)
from product as p
join type t
on p.type_id = t.id
group by t.name;

---Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select p.name
from product as p 
join type t 
on p.type_id = t.id
where t.name = 'Сыр' or t.name = 'Молоко';

---Написать запрос, который выводит тип продуктов, которых количество продуктов определенного типа осталось меньше 2 штук.
select t.name, count(p.type_id)
from product as p
join type t
on p.type_id = t.id
group by t.name
having count(p.type_id) < 3;

---Вывести все продукты и их тип.
select * from product 
join type t 
on product.type_id = t.id;