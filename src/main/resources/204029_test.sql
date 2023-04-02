-- ВНИМАНИЕ. Прямой запуск данного скрипта запрещен !!!!!
-- Данный файл предназначен для копипаста в среде управления БД.
-- Тестова версия файла первой БД
-- Автор Азарков Максим Николаевич
-- Версия 2020-12-27 v.0.1

-- Дополнительная литература:
-- 1. Head First SQL, Линн Бейли
-- 2. Официальная документация Postgresql
-- 3. Кратко по всем командам: https://www.w3schools.com/sql/default.asp


-- Создаем БД
CREATE DATABASE ascpscontrol;

-- Создаем таблицу в созданной базе данных
CREATE TABLE employee(
 id_empl BIGSERIAL NOT NULL PRIMARY KEY,
 name_empl VARCHAR(50) NOT NULL,
 lvl_security SMALLINT NOT NULL,
 birthday DATE NOT NULL
);

-- Вставляем данные в таблицу
-- можно попробовать заполнять данными таблицы с помощью Mockaroo
-- Ссылка на Mockaroo: https://mockaroo.com/
-- Но пока поэксперементируем руками
INSERT INTO employee(
 name_empl, lvl_security, birthday
) VALUES (
  ('IVAN', 0, '2000-01-01'),
  ('STEPAN', 2, '1999-11-25')
);

-- проверяем правильность записи
SELECT * FROM employee;
--  id_empl | name_empl | lvl_security |  birthday
--  --------+-----------+--------------+------------
--        1 | IVAN      |            0 | 2000-01-01
--        2 | STEPAN    |            2 | 1999-11-25

-- Повысим уровень секретности у Ивана
UPDATE employee SET lvl_security=1 WHERE name_empl='IVAN';

-- Уволим Степана
DELETE * FROM employee WHERE name_empl='STEPAN';

-- проверяем правильность записи
SELECT * FROM employee;
--  id_empl | name_empl | lvl_security |  birthday
--  --------+-----------+--------------+------------
--        1 | IVAN      |            1 | 2000-01-01