-- ����� ������� ������ ����������
-- ������ 2021-01-24 v.0.1

\c products;

----1. �������� ������ ��������� ���� ��������� � ����� "���"

---- ������� �������� ���������� ������
-- SELECT t.id AS type_id FROM type AS t WHERE t.name='���'; -- �� ������ ������� ���������
---- ������ ������� ������� ������
-- SELECT p.name AS product_name FROM product AS p;
---- ������ �� ���������  JOIN �� ����� id ��� ���� �������� (ON)
---- ���������� ����� �� �����, �� �������� �� ��� ����������
SELECT p.name AS product_name, t.name AS type_name
FROM product AS p
JOIN type AS t
ON p.type_id = t.id
--WHERE p.type_id = (SELECT t.id AS type_id FROM type AS t WHERE t.name LIKE '���'); -- ����� ���� ����� ������� �� LIKE
WHERE p.type_id = (SELECT t.id AS type_id FROM type AS t WHERE t.name ~ '^(�|�)��'); -- � ����� � ����� ���� �������������� ���������� ����������


----2. �������� ������ ��������� ���� ���������, � ���� � ����� ���� ����� "����������"
---- ������������� ���������� ����������
SELECT name FROM product WHERE name ~ '(�|�)���������';

--3. �������� ������, ������� ������� ��� ��������, ���� �������� ������� ������������� � ��������� ������.
--SELECT Extract(MONTH FROM expired_date) FROM product;
--SELECT Extract(MONTH FROM current_date);
SELECT name, expired_date
FROM product
WHERE Extract(MONTH FROM expired_date) = (Extract(MONTH FROM current_date  + INTERVAL '1 month'));

----4. �������� ������, ������� ������� ����� ������� �������
---- product(id, name, type_id, expired_date, price)
SELECT name AS product_name, price AS max_price
FROM product
WHERE price =
    (SELECT MAX(p.price) AS max_price
    FROM product AS p);

----5.  �������� ������, ������� ������� ���������� ���� ��������� ������������� ����.
SELECT COUNT(p.name) AS count_product, t.name AS type_name
FROM product AS p
JOIN type AS t
ON p.type_id = t.id
GROUP BY t.name;

----6.  �������� ������ ��������� ���� ��������� � ����� "���" � "������"
SELECT p.name AS product_name, t.name AS type_name
FROM product AS p
JOIN type AS t
ON p.type_id = t.id
WHERE p.type_id
    IN (SELECT t.id AS type_id FROM type AS t WHERE t.name ~ '^((�|�)��)' OR t.name ~ '^((�|�)�����)');

---- 7. �������� ������, ������� ������� ��� ���������, ������� �������� ������ 10 ����.
SELECT t.name AS type_product
FROM type AS t
JOIN product AS p
ON t.id = p.type_id
GROUP BY t.name
HAVING COUNT(p.name) < 10;

---- 8. ������� ��� �������� � �� ���.
SELECT p.name, t.name
FROM product AS p
JOIN type AS t
ON p.type_id = t.id;



