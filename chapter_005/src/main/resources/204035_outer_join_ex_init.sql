\c postgres

DROP DATABASE outer_join_example;
CREATE DATABASE outer_join_example;

\c outer_join_example

CREATE TABLE owners(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE devices(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    owner_id INT references owners(id)
);

INSERT INTO owners(name) VALUES ('Owner 1');
INSERT INTO owners(name) VALUES ('Owner 2');
INSERT INTO owners(name) VALUES ('Owner 3');

INSERT INTO devices(name, owner_id) VALUES ('Device 1', 1);
INSERT INTO devices(name, owner_id) VALUES ('Device 2', 2);
INSERT INTO devices(name, owner_id) VALUES ('Device 3', 3);
INSERT INTO devices(name, owner_id) VALUES ('Device 4', null);
INSERT INTO devices(name, owner_id) VALUES ('Device 5', null);
INSERT INTO devices(name, owner_id) VALUES ('Device 6', 1);

-- таблица умножения
create table numbers(
    num int unique
);

insert into numbers(num) values (1);
insert into numbers(num) values (2);
insert into numbers(num) values (3);
insert into numbers(num) values (4);
insert into numbers(num) values (5);
insert into numbers(num) values (6);
insert into numbers(num) values (7);
insert into numbers(num) values (8);
insert into numbers(num) values (9);