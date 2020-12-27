-- Автор Азарков Максим Николаевич
-- Версия 2020-12-28 v.0.1

--- MAIN TABLE ---
-- Пользователи.
CREATE TABLE t_user (
    id_user serial PRIMARY KEY,
    name VARCHAR(20) references item(id_item) -- item - user = many-to-one
);

-- Роли.
CREATE TABLE t_role (
    id_role serial PRIMARY KEY,
    name VARCHAR(20) references user(id_user)    -- user - role = many-to-one
);

-- Права ролей.
CREATE TABLE t_rules (
    id_rules serial PRIMARY KEY,
    name VARCHAR(20)
);

-- Заявки.
CREATE TABLE t_item (
    id_item serial PRIMARY KEY,
    name VARCHAR(20)
);

-- Комментарии Заявок.
CREATE TABLE t_comments (
    id_comments serial PRIMARY KEY,
    name VARCHAR(20)
);

-- Приложенные Файлы.
CREATE TABLE t_attachs (
    id_attachs serial PRIMARY KEY,
    name VARCHAR(20)
);

-- Категории заявки.
CREATE TABLE t_category (
    id_category serial PRIMARY KEY,
    name VARCHAR(20)
-- item - category = many-to-one

);

-- Состояние заявки.
CREATE TABLE t_state (
-- item - state = many-to-one
);

--- TABLE FOR RULES ---
CREATE TABLE t_role_t_rules (
    id_rr serial PRIMARY KEY,
    -- role - rules = many-to-many
    role_id int references t_role(id_role),
    rules_id int references t_rules(id_rules)
);

-- item - comments = one-to-many
CREATE TABLE t_item_t_comments (
    id_ic serial PRIMARY KEY,
    item_id int references t_comments(id_comments)  -- item - comments = one-to-many
    item_ - attachs = one-to-many -- item - attachs = one-to-many
);
