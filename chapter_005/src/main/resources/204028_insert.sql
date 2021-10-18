-- ����� ������� ������ ����������
-- ������ 2021-01-19 v.2.0

-- ����������� � ��
\c attaches

-- ������, ���������� ����
INSERT INTO
    role (name)
VALUES
    ('�����'),
    ('������������');

-- ������ �������� ������������� � �������� � ��� �� ����
INSERT INTO
    t_user (name, role_id)
VALUES
    ('���� ������', 1),
    ('���� ������', 2),
    ('������ ��������', 2);

-- �������� �������
INSERT INTO
    rules (name)
VALUES
    ('������'),
    ('������');

--  ������ ����������� ������� � ����
INSERT INTO
    role_rules (role_id, rules_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 1);

-- ��������� �������� ���������,...
INSERT INTO
    category (name)
VALUES
    ('�����'),
    ('������� ��������'),
    ('������ ��������');

-- ...���������.
INSERT INTO
    state (stateItem)
VALUES
    ('������ �������. � �������� �� ��������'),
    ('������� �� ��������'),
    ('������ ���������, �����������'),
    ('������ ���������, �� �����������');

-- ������ ������� ������
INSERT INTO
    item (name, user_id, category_id, state_id)
VALUES
    ('����� ���� � ����!', 1, 1, 1),
    ('����� ������ ��� ��������', 2, 2, 3);

-- �� � ��������, ��� ����� ��������� �������� � �����
INSERT INTO
    attachs (nameFile, item_id)
VALUES
    ('beer.file', 1);

-- � ��������������� ���� �������!
INSERT INTO
    comments (commentText, item_id)
VALUES
    ('� �������� ������!!! )))', 1);