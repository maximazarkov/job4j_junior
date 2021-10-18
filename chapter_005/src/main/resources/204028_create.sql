-- Автор Азарков Максим Николаевич
-- Версия 2021-01-19 v.2.0

-- Подключимся к БД
\c attaches

-- Роли.
CREATE TABLE role (
    id_role serial PRIMARY KEY,
    name VARCHAR(20)
);

-- Пользователи.
CREATE TABLE t_user (
    id_user serial PRIMARY KEY,
    name VARCHAR(20),
    role_id INT references role(id_role)	            -- user - role = many-to-one
);

-- Права ролей.
CREATE TABLE rules (
    id_rules serial PRIMARY KEY,
    name VARCHAR(40)
);

CREATE TABLE role_rules (
    id_rr serial PRIMARY KEY,
	-- role - rules = many-to-many
    role_id INT references role(id_role),
    rules_id INT references rules(id_rules)
);

-- Категории заявки.
-- Например, заявка на материалы, заявка на консультацию и т.п. В общем заранее определенная категория заявки
CREATE TABLE category (
    id_category serial PRIMARY KEY,
    name VARCHAR(100)
);

-- Состояние заявки.
-- Например, на рассмотрении, отклоненена, утверждена к выполнению, выполнена
CREATE TABLE state (
 	id_state serial PRIMARY KEY,
	stateItem VARCHAR(100)
);

-- Заявки.
CREATE TABLE item (
    id_item serial PRIMARY KEY,
    name VARCHAR(200),
    user_id INT references t_user(id_user),              -- item - user = many-to-one
    category_id INT references category (id_category),   -- item - category = many-to-one
    state_id INT references state(id_state)              -- item - state = many-to-one
);

-- Приложенные Файлы.
CREATE TABLE attachs (
    id_attachs serial PRIMARY KEY,
    nameFile VARCHAR(20),
    item_id INT references item(id_item)                 -- item - attachs = one-to-many
);

-- Комментарии Заявок.
CREATE TABLE comments (
    id_comments serial PRIMARY KEY,
    commentText VARCHAR(200),
    item_id INT references item(id_item)                 -- item - comments = one-to-many
);