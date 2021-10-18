-- В системе заданы таблицы 

-- DROP DATABASE cheese_db;
CREATE DATABASE cheese_db;
\c cheese_db

-- product(id, name, type_id, expired_date, price)
CREATE TABLE product (
	id serial PRIMARY KEY,
	name VARCHAR(30),
	type_id INTEGER, 
	expired_date DATE, 
	price DEC(4.2)
);

-- type(id, name)
CREATE TABLE type (
	id serial PRIMARY KEY,
	name VARCHAR(30)
);

-- +------------+
-- |product     |
-- +------------+       +----+
-- |id          |       |type|
-- |name        |       +----+
-- |type_id     |------>|id  |
-- |expired_date|       |name|
-- |price       |       +----+
-- +------------+

INSERT INTO type (name) VALUES ('СЫР'), ('МОЛОКО'), ('МОРОЖЕННОЕ');

INSERT INTO product 
	(name, type_id, expired_date,price) 
VALUES 
	('Российский',1,'2021-02-01',600.00),
	('Простоквашино',2,'2021-01-20',70.00),
	('Русский стандарт',3,'2021-02-01',80.00);
--Задание.

-- 1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT * FROM product WHERE ...

-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT * FROM product WHERE name LIKE '%мороженное%';

-- 3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT * FROM product WHERE EXTRACT(month FROM expired_date)=(EXTRACT(month FROM now()) + 1);

-- 4. Написать запрос, который выводит самый дорогой продукт.
SELECT * FROM product WHERE MAX(price);

-- 5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT * FROM product WHERE ...

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT * FROM product WHERE ...

-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.  
SELECT * FROM product WHERE ...

-- 8. Вывести все продукты и их тип.
SELECT * FROM product WHERE ...