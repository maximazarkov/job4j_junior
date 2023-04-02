-- � ������� ������ ������� 

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

INSERT INTO type (name) VALUES ('���'), ('������'), ('����������');

INSERT INTO product 
	(name, type_id, expired_date,price) 
VALUES 
	('����������',1,'2021-02-01',600.00),
	('�������������',2,'2021-01-20',70.00),
	('������� ��������',3,'2021-02-01',80.00);
--�������.

-- 1. �������� ������ ��������� ���� ��������� � ����� "���"
SELECT * FROM product WHERE ...

-- 2. �������� ������ ��������� ���� ���������, � ���� � ����� ���� ����� "����������"
SELECT * FROM product WHERE name LIKE '%����������%';

-- 3. �������� ������, ������� ������� ��� ��������, ���� �������� ������� ������������� � ��������� ������.
SELECT * FROM product WHERE EXTRACT(month FROM expired_date)=(EXTRACT(month FROM now()) + 1);

-- 4. �������� ������, ������� ������� ����� ������� �������.
SELECT * FROM product WHERE MAX(price);

-- 5. �������� ������, ������� ������� ���������� ���� ��������� ������������� ����.
SELECT * FROM product WHERE ...

-- 6. �������� ������ ��������� ���� ��������� � ����� "���" � "������"
SELECT * FROM product WHERE ...

-- 7. �������� ������, ������� ������� ��� ���������, ������� �������� ������ 10 ����.  
SELECT * FROM product WHERE ...

-- 8. ������� ��� �������� � �� ���.
SELECT * FROM product WHERE ...