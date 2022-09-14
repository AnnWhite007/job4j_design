CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);


insert into company(id, name) values (1, 'A');
insert into company(id, name) values (2, 'B');
insert into company(id, name) values (3, 'C');
insert into company(id, name) values (5, 'D');

insert into person(id, name, company_id) values (1, 'FF', 1);
insert into person(id, name, company_id) values (2, 'GG', 2);
insert into person(id, name, company_id) values (3, 'HH', 3);
insert into person(id, name, company_id) values (4, 'II', 2);
insert into person(id, name, company_id) values (5, 'JJ', 2);
insert into person(id, name, company_id) values (6, 'KK', 3);
insert into person(id, name, company_id) values (7, 'LL', 3);
insert into person(id, name, company_id) values (8, 'MM', 5);


--- 1. В одном запросе получить

--- имена всех person, которые не состоят в компании с id = 5;
SELECT name FROM person
WHERE company_id != 5;
--- название компании для каждого человека.
SELECT p.name, c.name FROM person as p
JOIN company c
ON p.company_id = c.id;

---2. Необходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании
---нужно учесть, что таких компаний может быть несколько, и вывести надо информацию о каждой компании.
SELECT c.name, count(p.name)
FROM company as c
join person p
ON c.id = p.company_id
GROUP BY c.name
HAVING count(p.name) = (SELECT MAX (num)
    FROM (SELECT count(p.name) num
        FROM person as p
        join company c
        ON c.id = p.company_id
        GROUP BY c.name) as n);