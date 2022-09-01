---Представления
--- это объекты базы данных, которые всегда создаются на основе одной или более базовых таблиц (или других представлений),
--- используя информацию метаданных. Эта информация (включая имя представления и способ получения строк из базовых таблиц) – все, что сохраняется физически для представления.
--- это SQL-запрос, который можно выполнять, обращаясь к нему по заранее указанному имени. (результат не сохраняется)
--- Материализованное представление – это объект базы данных, значением которого является сохраненный результат заранее выполненного запроса,
--- заданного при создании материализованного представления. Повторное вычисление и сохранение полученного результата происходит согласно правилам, которые определяются при создании материализованного представления.
--- это SQL-запрос, который можно выполнять, обращаясь к нему по заранее указанному имени, и результат выполнения этого запроса сохраняется для дальнейшего использования.


create table authors (
    id serial primary key,
	name varchar(50)
);

insert into authors (name) values ('Александр Пушкин');
insert into authors (name) values ('Николай Гоголь');
insert into authors (name) values ('Михаил Булгаков');
insert into authors (name) values ('Лев Толстой');

create table books (
    id serial primary key,
	name varchar(200),
	author_id int references authors(id)
);

insert into books (name, author_id) values ('Евгений Онегин', 1);
insert into books (name, author_id) values ('Капитанская дочка', 1);
insert into books (name, author_id) values ('Дубровский', 1);
insert into books (name, author_id) values ('Мертвые души', 2);
insert into books (name, author_id) values ('Вий', 2);
insert into books (name, author_id) values ('Мастер и Маргарита', 3);
insert into books (name, author_id) values ('Война и мир', 4);

create table students (
    id serial primary key,
	name varchar(50)
);

insert into students (name) values ('Иван Иванов');
insert into students (name) values ('Петр Петров');
insert into students (name) values ('Василий Васильев');

create table orders (
    id serial primary key,
    active boolean default true,
	book_id int references books(id),
	student_id int references students(id)
);

insert into orders (book_id, student_id) values (1, 1);
insert into orders (book_id, student_id) values (3, 1);
insert into orders (book_id, student_id) values (5, 2);
insert into orders (book_id, student_id) values (4, 1);
insert into orders (book_id, student_id) values (2, 2);
insert into orders (book_id, student_id) values (6, 2);
insert into orders (book_id, student_id) values (7, 2);

---Количество книг у студентов
create view show_count_book_at_students
    as select s.name, count(b.name) from students as s
        join orders o on s.id = o.student_id
        join books b on o.book_id = b.id
        join authors a on b.author_id = a.id
        group by s.name;


