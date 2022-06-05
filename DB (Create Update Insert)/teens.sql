create table teens(
	name varchar(255),
	gender varchar(255)
);

insert into teens(name, gender) values ('A', 'M');
insert into teens(name, gender) values ('B', 'W');
insert into teens(name, gender) values ('C', 'M');
insert into teens(name, gender) values ('D', 'W');
insert into teens(name, gender) values ('E', 'M');

---Используя cross join составить все возможные разнополые пары
select t1.name as a, t2.name as b
from teens
t1 cross join teens t2
where t1.gender != t2.gender;