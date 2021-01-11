-- Автор Азарков Максим Николаевич
-- Версия 2021-01-11 v.0.1

-- Подключимся к БД
\c attaches

--- MAIN TABLE ---


-- Комментарии Заявок.
CREATE TABLE comments (
    id_comments serial PRIMARY KEY,
    name VARCHAR(20)
);

-- Приложенные Файлы.
CREATE TABLE attachs (
    id_attachs serial PRIMARY KEY,
    name VARCHAR(20)
);

-- Заявки.
CREATE TABLE item (
    id_item serial PRIMARY KEY,
    name VARCHAR(20),
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
    name VARCHAR(20),
	user_id int references t_user(id_user)    -- user - role = many-to-one
);

-- Права ролей.
CREATE TABLE rules (
    id_rules serial PRIMARY KEY,
    name VARCHAR(20)
);

CREATE TABLE role_rules (
    id_rr serial PRIMARY KEY,
	-- role - rules = many-to-many
    role_id int references role(id_role),
    rules_id int references rules(id_rules)
);

-- Категории заявки.
CREATE TABLE category (
    id_category serial PRIMARY KEY,
    name VARCHAR(20),
	item_id int references item(id_item)	-- item - category = many-to-one
);

-- Состояние заявки.
CREATE TABLE state (
 	id_state serial PRIMARY KEY,
	item_id int references item(id_item)	-- item - state = many-to-one
);