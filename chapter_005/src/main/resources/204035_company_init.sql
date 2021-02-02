\c postgres

DROP DATABASE company;
CREATE DATABASE company;

\c company

CREATE TABLE departments(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE employees(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    depart_id INT references departments(id)
);

INSERT INTO departments(name) VALUES ('Department 1');
INSERT INTO departments(name) VALUES ('Department 2');
INSERT INTO departments(name) VALUES ('Department 3');

INSERT INTO employees(name, depart_id) VALUES ('employe 1', 1);
INSERT INTO employees(name, depart_id) VALUES ('employe 2', 2);
INSERT INTO employees(name, depart_id) VALUES ('employe 3', 2);
INSERT INTO employees(name, depart_id) VALUES ('employe 4', 3);
INSERT INTO employees(name, depart_id) VALUES ('employe 5', 3);
INSERT INTO employees(name, depart_id) VALUES ('employe 6', 3);
INSERT INTO employees(name, depart_id) VALUES ('employe 7', 3);
INSERT INTO employees(name, depart_id) VALUES ('employe 8', null);
INSERT INTO employees(name, depart_id) VALUES ('employe 9', null);

-- в этой же БД создадим таблицу для пар
CREATE TABLE teens (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    gender CHAR(1)
);

INSERT INTO teens (name, gender)
VALUES
('male 1', 'm'),
('male 2', 'm'),
('male 3', 'm'),
('female 1', 'f'),
('female 2', 'f'),
('female 3', 'f');