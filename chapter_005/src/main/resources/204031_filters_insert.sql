-- ����� ������� ������ ����������
-- ������ 2021-01-24 v.0.1

\c products;

INSERT INTO type (name)
VALUES
    ('����������'),
    ('���'),
    ('������');

INSERT INTO product (name, type_id, expired_date, price)
VALUES
    ('���������� ������', 1, '2020-02-01', 99.99),
    ('���������� ������', 1, '2020-02-02', 99.99),
    ('���������� ������', 1, '2020-02-05', 99.99),
    ('���������� ������', 1, '2020-02-01', 99.99),
    ('���������� ������', 1, '2020-02-01', 99.99),
    ('���������� ������', 1, '2020-02-01', 99.99),
    ('���������� ������', 1, '2020-02-01', 99.99),
    ('���������� ������', 1, '2020-02-01', 99.99),
    ('���������� ������', 1, '2020-02-01', 99.99),
    ('���������� ������', 1, '2020-02-01', 99.99),
    ('���������� ������', 1, '2020-02-01', 99.99),
    ('���������� ������', 1, '2020-02-01', 99.99),
    ('��� ����������', 2, '2020-05-01', 456.50),
    ('��� �������', 2, '2020-03-01', 345.00),
    ('������ ������������, 2,5%', 3, '2020-01-28', 456.50);