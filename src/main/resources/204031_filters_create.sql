-- Автор Азарков Максим Николаевич
-- Версия 2021-01-24 v.0.1

\c products;

CREATE TABLE type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30)
);

CREATE TABLE product(
    id SERIAL PRIMARY KEY,
    name VARCHAR(30),
    type_id INT REFERENCES type (id),
    expired_date DATE,
    price DEC(6,2)
);