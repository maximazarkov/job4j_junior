\c company

-- 2. Выполнить запросы с left, rigth, full, cross соединениями
SELECT *
FROM departments d
LEFT JOIN employees e
ON e.depart_id = d.id;

SELECT *
FROM departments d
RIGHT JOIN employees e
ON e.depart_id = d.id;

SELECT *
FROM departments d
FULL JOIN employees e
ON e.depart_id = d.id;

SELECT * FROM departments CROSS JOIN employees;

-- 3. Используя left join найти работников, которые не относятся ни к одну из департаментов
SELECT *
FROM employees e
LEFT JOIN departments d
ON d.id = e.depart_id
WHERE e.depart_id is null;

-- 4. Используя left и right join написать запросы, которые давали бы одинаковый результат.
SELECT *
FROM departments d
LEFT JOIN employees e
ON e.depart_id = d.id;

SELECT *
FROM employees e
RIGHT JOIN departments d
ON d.id = e.depart_id;

SELECT *
FROM departments d
RIGHT JOIN employees e
ON e.depart_id = d.id;

SELECT *
FROM employees e
LEFT JOIN departments d
ON d.id = e.depart_id;

-- 5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join
-- составить все возможные разнополые пары
SELECT t1.name, t2.name
FROM teens AS t1
CROSS JOIN teens AS t2
WHERE t1.gender <> t2.gender;