\c mstorage

-- 1. ������� ������ ���� ����� � ��� ����������� � ��� ������.
SELECT * FROM cars c
LEFT JOIN bodycars b ON c.bc_id = b.id
LEFT JOIN engines e ON c.en_id = e.id
LEFT JOIN transmissions t ON c.tr_id = t.id;

-- ��� �� ������, �� ������� ���������� �����
SELECT c.name car, b.name body, e.name engine, t.name transmissions FROM cars c
LEFT JOIN bodycars b ON c.bc_id = b.id
LEFT JOIN engines e ON c.en_id = e.id
LEFT JOIN transmissions t ON c.tr_id = t.id;

-- 2. ������� �������� ������, ������� �� ������������ � ������, ������, ���������, ������� �������.
-- ��������! �������� ���� ������ �������� �� ����� ��-�� ��������� ��������� ��.
-- TODO ���������� �������� � �������, ���������
SELECT c.name car, b.name body, e.name engine, t.name transmissions FROM cars c
FULL JOIN bodycars b ON c.bc_id = b.id
FULL JOIN engines e ON c.en_id = e.id
FULL JOIN transmissions t ON c.tr_id = t.id
WHERE c.bc_id is null OR c.en_id is null OR c.tr_id is null
GROUP BY c.name, b.name, e.name, t.name;