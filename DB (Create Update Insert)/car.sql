create table body(
	id serial primary key,
	name varchar(255)
);
create table engine(
	id serial primary key,
	name varchar(255)
);
create table transmission(
	id serial primary key,
	name varchar(255)
);
create table car(
	id serial primary key,
	name varchar(255),
	body_id int references body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into body(name) values ('sedan'), ('hatchback'), ('pickup');
insert into engine(name) values ('gasoline'), ('diesel'), ('gas');
insert into transmission(name) values ('automatic'), ('manual');

insert into car(name, body_id, engine_id, transmission_id) values ('BMW', 1, null, 2), ('Volvo', 2, 2, null), ('Honda', 2, 1, 1);

---Вывести список всех машин и все привязанные к ним детали.
select c.name as Марка, b.name as Кузов, e.name as Двигатель, t.name as Коробка
from car c
left join body b on (c.body_id = b.id)
left join engine e on (c.engine_id = e.id)
left join transmission t on (c.transmission_id = t.id);

---Вывести отдельно детали (1 деталь - 1 запрос), которые не используются НИ в одной машине, кузова, двигатели, коробки передач.
select b.name
from body b
left join car c on (c.body_id = b.id)
where c.body_id is null;

select e.name
from engine e
left join car c on (c.engine_id = e.id)
where c.engine_id is null;

select t.name
from transmission t
left join car c on (c.transmission_id = t.id)
where c.transmission_id is null;