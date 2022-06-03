create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('Apple', 45000), ('Samsung', 5000), ('LG', 3000);
insert into people(name) values ('Аня'), ('Ваня'), ('Боря');
insert into devices_people(device_id, people_id) values (1, 1), (1, 2);
insert into devices_people(device_id, people_id) values (2, 1), (2, 2), (2, 3);
insert into devices_people(device_id, people_id) values (3, 2), (3, 3);

select avg(price) from devices;

select p.name, avg(d.price)
from devices as d, people as p, devices_people as dp
where dp.device_id = d.id
and dp.people_id = p.id
group by p.name;

select p.name, avg(d.price)
from devices as d, people as p, devices_people as dp
where dp.device_id = d.id
and dp.people_id = p.id
group by p.name
having avg(d.price) > 5000;
