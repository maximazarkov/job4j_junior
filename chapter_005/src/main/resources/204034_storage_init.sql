\c postgres

DROP DATABASE mstorage;
CREATE DATABASE mstorage;

\c mstorage

CREATE TABLE bodycars(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE engines(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE transmissions(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE cars(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    bc_id INT references bodycars(id),
    en_id INT references engines(id),
    tr_id INT references transmissions(id)
);

INSERT INTO bodycars(name) VALUES ('����� 1');
INSERT INTO bodycars(name) VALUES ('����� 2');
INSERT INTO bodycars(name) VALUES ('����� 3');  -- ���� ����� �� ������� �� �����������

INSERT INTO engines(name) VALUES ('��������� 1600 16V');

INSERT INTO transmissions(name) VALUES ('���� 5'); -- ��� ������� �� ������� �� ������������
INSERT INTO transmissions(name) VALUES ('���� 4');

INSERT INTO cars(name, bc_id, en_id, tr_id) VALUES ('������ 1', null, null, null);
INSERT INTO cars(name, bc_id, en_id, tr_id) VALUES ('������ 2', 1, null, null);
INSERT INTO cars(name, bc_id, en_id, tr_id) VALUES ('������ 3', 2, 1, null);
INSERT INTO cars(name, bc_id, en_id, tr_id) VALUES ('������ 4', 2, 1, 2);