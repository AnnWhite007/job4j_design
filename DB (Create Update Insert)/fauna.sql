create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);
insert into fauna(name, avg_age, discovery_date)
values ('Cat', 54705, '1456-04-07');
insert into fauna(name, avg_age, discovery_date)
values ('Red fish', 21055, null);
insert into fauna(name, avg_age, discovery_date)
values ('Dog', 43800, '1369-09-02');
insert into fauna(name, avg_age, discovery_date)
values ('Horse', 17300, '1965-12-14');
insert into fauna(name, avg_age, discovery_date)
values ('Salmon fish', 13285, null);

select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';
