create table departments(
	id serial primary key,
	name varchar(255)
);

create table employees(
	id serial primary key,
	name varchar(255),
	department_id int references departments(id)
);

insert into departments(name) values ('ИТ'), ('Финансовый'), ('ИБ'), ('HR');
insert into employees(name, department_id) values ('Anna', 1), ('Ivan', 1);
insert into employees(name, department_id) values ('Kate', 2), ('Maria', 2);
insert into employees(name, department_id) values ('Dima', 3);
insert into employees(name, department_id) values ('Sasha', null), ('Dasha', null);

---Выполнить запросы с left, rigth, full, cross соединениями
select * from employees e 
left join departments d
on e.department_id = d.id;

select * from employees e 
right join departments d
on e.department_id = d.id;

select * from employees e 
full join departments d
on e.department_id = d.id;

select * from employees e
cross join departments d;

---Используя left join найти департаменты, у которых нет работников
select * from departments d
left join employees e 
on e.department_id = d.id
where e.id is null;

---Используя left и right join написать запросы, которые давали бы одинаковый результат (порядок вывода колонок в эти запросах также должен быть идентичный).
select * from employees e 
left join departments d
on e.department_id = d.id
where d.id is not null;

select * from employees e
right join departments d
on e.department_id = d.id
where e.id is not null;
