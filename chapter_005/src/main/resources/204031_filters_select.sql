-- Автор Азарков Максим Николаевич
-- Версия 2021-01-24 v.0.1

\c products;

--1. Написать запрос получение всех продуктов с типом "СЫР"

---- Сначала подберем внутренний запрос
-- SELECT t.id AS type_id FROM type AS t WHERE t.name='Сыр'; -- не лучший вариант сравнения
---- Теперь соберем внешний запрос
-- SELECT p.name AS product_name FROM product AS p;
---- Теперь их объединим  JOIN по ключу id для типа продукта (ON)
---- псевдонимы особо не нужны, но применим их для тренировки
--SELECT p.name AS product_name, t.name AS type_name
--FROM product AS p
--JOIN type AS t
--ON p.type_id = t.id
----WHERE p.type_id = (SELECT t.id AS type_id FROM type AS t WHERE t.name LIKE 'Сыр'); -- здесь знак равно заменил на LIKE
--WHERE p.type_id = (SELECT t.id AS type_id FROM type AS t WHERE t.name ~ '^(С|с)ыр'); -- а здесь в замен лайк воспользовался регулярным выражением


--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
---- Воспользуемся релудярным выражением
--SELECT * FROM product WHERE name ~ '(М|м)ороженное';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
-- === НЕ РЕШЕНО === --
--SELECT name, expired_date
--FROM product; -- все продукты

--SELECT Extract(MONTH FROM expired_date) FROM product;
--SELECT Extract(MONTH FROM current_date);

--SELECT name, expired_date
--FROM product
--WHERE age(timestamp expired_date) = INTERVAL '1 month';

--4. Написать запрос, который выводит самый дорогой продукт
---- product(id, name, type_id, expired_date, price)
--SELECT name AS product_name, price AS max_price
--FROM product WHERE price = (SELECT MAX(p.price) AS max_price FROM product AS p);

--5.  Написать запрос, который выводит количество всех продуктов определенного типа.
--SELECT COUNT(p.name) AS count_product, t.name AS type_name
--FROM product AS p
--JOIN type AS t
--ON p.type_id = t.id
--GROUP BY t.name;

--6.  Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT p.name AS product_name, t.name AS type_name
FROM product AS p
JOIN type AS t
ON p.type_id = t.id
WHERE
    p.type_id = (SELECT t.id AS type_id FROM type AS t WHERE t.name ~ '^((С|с)ыр)')
    OR p.type_id = (SELECT t.id AS type_id FROM type AS t WHERE t.name ~ '^((М|м)олоко)');


--  Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.

-- Вывести все продукты и их тип.
-- === НЕ РЕШЕНО === --
-- SELECT * FROM

