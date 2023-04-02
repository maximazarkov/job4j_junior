-- ВНИМАНИЕ. Прямой запуск данного скрипта запрещен !!!!!
-- Данный файл предназначен для копипаста в среде управления БД.
-- Тестова версия файла создания схемы прав пользователей и ролей
-- Автор Азарков Максим Николаевич
-- Версия 2020-12-28 v.0.1

-- Дополнительная литература:
-- 1. Head First SQL, Линн Бейли
-- 2. Официальная документация Postgresql
-- 3. Кратко по всем командам: https://www.w3schools.com/sql/default.asp

-- создадим таблицы и добавим связи между ними.
-- Допустим у нас есть музыкальные композиции и авторы. Эти таблицы описываются связью many-to-one.
-- Автор может иметь много записей.
-- Сначала создадим таблицу автора.
create table author (
    id serial primary key,
    name varchar(2000)
);

-- Теперь создадим таблицу композиций.
create table compose (
    id serial primary key,
    name varchar(2000),
-- Строчка  author_id int references author(id) - создает связь таблицами author и compose.
-- В данном случае связь указана many-to-one.
    author_id int references author(id)
);

-- Если мы захотим вставить данные в таблицу compose нам нужно сначала взять данные из таблицы author.
-- references - указывает на ограничение, что данное поле должно быть в таблице auther.
-- В этом примере связь реализуется сразу в таблице.

-- Давайте рассмотрим пример many-to-many.
-- Допустим у нас появляется таблица меломанов.
create table music_lover (
    id serial primary key,
    name varchar(2000)
);

-- Теперь надо добавить ограничения - связать таблицы music_lover и compose.
-- Для этого нам нужно добавить вспомогательную таблицу - music_lovel_compose
create music_lovel_compose (
    id serial primary key,
    music_lovel_id int references music_lovel(id),
    compose_id int references compose(id)
);

