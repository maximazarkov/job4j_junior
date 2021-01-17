-- Автор Азарков Максим Николаевич
-- Версия 2021-01-18 v.1.3

-- Подключимся к БД
\c attaches

--- MAIN TABLE ---
-- Комментарии Заявок.
CREATE TABLE comments (
    id_comments serial PRIMARY KEY,
    commentText VARCHAR(200)
);

-- Приложенные Файлы.
CREATE TABLE attachs (
    id_attachs serial PRIMARY KEY,
    nameFile VARCHAR(20)
);

-- Заявки.
CREATE TABLE item (
    id_item serial PRIMARY KEY,
    name VARCHAR(50),
	comments_id int references comments(id_comments),	-- item - comments = one-to-many
	attaches_id int references attachs(id_attachs)		-- item - attachs = one-to-many
);

-- Пользователи.
CREATE TABLE t_user (
    id_user serial PRIMARY KEY,
    name VARCHAR(20),
	item_id int references item(id_item) 				-- item - user = many-to-one
);

-- Роли.
CREATE TABLE role (
    id_role serial PRIMARY KEY,
    name VARCHAR(20)
);

-- Права ролей.
CREATE TABLE rules (
    id_rules serial PRIMARY KEY,
    name VARCHAR(40)
);

CREATE TABLE role_rules (
    id_rr serial PRIMARY KEY,
	-- role - rules = many-to-many
    role_id int references role(id_role),
    rules_id int references rules(id_rules)
);

-- Пользователи.
CREATE TABLE t_user (
    id_user serial PRIMARY KEY,
    name VARCHAR(20),
	-- user - role = many-to-one - прикручиваем какую-то одну роль
	role_id int references role(id_role)
);

-- Категории заявки.
-- Например, заявка на материалы, заявка на консультацию и т.п. В общем заранее определенная категория заявки
CREATE TABLE category (
    id_category serial PRIMARY KEY,
    category_name VARCHAR(100)
);

-- Состояние заявки.
-- Например, на рассмотрении, отклоненена, утверждена к выполнению, выполнена
CREATE TABLE state (
 	id_state serial PRIMARY KEY,
	stateItem VARCHAR(30),
	item_id int references item(id_item)	-- item - state = many-to-one
);
