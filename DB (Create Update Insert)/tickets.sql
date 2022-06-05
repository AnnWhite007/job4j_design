create table ticket(
    id serial primary key,
    seat int,
    date date
);

create table people(
    id serial primary key,
    name varchar(255),
    ticket_id int references ticket(id) unique
);

insert into ticket(seat, date) values (123, '2022-05-06');
insert into ticket(seat, date) values (456, '2022-06-05');
insert into ticket(seat, date) values (589, '2022-09-05');

insert into people(name, ticket_id) values ('Ivan', 1);
insert into people(name, ticket_id) values ('Anna', 2);
insert into people(name, ticket_id) values ('Kate', 3);
insert into people(name) values ('Kate');

---Написать 3 запроса с inner join с использованием альясов
select * from people
join ticket t
on people.ticket_id = t.id;

select pp.name, t.seat, t.date
from people as pp
join ticket as t
on pp.ticket_id = t.id;

select pp.name as Имя, t.seat as Место,
t.date as Дата from people as pp
join ticket as t
on pp.ticket_id = t.id;