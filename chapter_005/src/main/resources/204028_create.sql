-- Автор Азарков Максим Николаевич
-- Версия 2021-01-12 v.1.2

-- Подключимся к БД
\c attaches

--- MAIN TABLE ---
-- Роли.
CREATE TABLE role (
    id_role serial PRIMARY KEY,
    name VARCHAR(20)
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
    category_name VARCHAR(20)
);

-- Состояние заявки.
-- Например, на рассмотрении, отклоненена, утверждена к выполнению, выполнена
CREATE TABLE state (
 	id_state serial PRIMARY KEY,
	state_name VARCHAR(20)
);

-- Заявки.
-- Для того, чтобы оформить заявку, сначал нужно указать (выгрузить) тип и состояние заявки. берется
CREATE TABLE item (
    id_item serial PRIMARY KEY,
    item_txt VARCHAR(20),
	-- item - category = many-to-one - заявок может быть много, а категория только одна для каждой заявки
	category_id int references category(id_category),
	-- item - state = many-to-one - состояние так же выбираем и заранее подготовленного списка
	state_id int references state(id_state),
	-- item - user = many-to-one - мого заявок на одного пользователя
	user_id int references t_user(id_user)
);

-- Комментарии Заявок.
CREATE TABLE comments (
    id_comments serial PRIMARY KEY,
    msg VARCHAR(20),
	-- item - comments = one-to-many - у заявки может быть много комментариев
	item_id int references item(id_item)
);

-- Приложенные Файлы.
CREATE TABLE attachs (
    id_attachs serial PRIMARY KEY,
    attachs_name VARCHAR(20),
	-- item - attachs = one-to-many. У коментария могут быть привязаны несколько файлов
	item_id int references item(id_item)
);

