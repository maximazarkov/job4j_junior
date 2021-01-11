-- ����� ������� ������ ����������
-- ������ 2021-01-12 v.1.0

-- ����������� � ��
\c attaches

INSERT INTO role (name)
VALUES 
	('Admin'),('User');

INSERT INTO rules (name)
VALUES 
	('read'),('write');


INSERT INTO role_rules (role_id, rules_id)
VALUES (1,1), (1,2), (2,1);

INSERT INTO t_user (name, role_id)
VALUES ('Vasya', 1), ('Vova', 2);

INSERT INTO category (category_name)
VALUES ('������ �� ��������'),('������ �� ������������');

INSERT INTO state (state_name)
VALUES
	('�� ������������'),
	('�����������'),
	('���������� � ����������'),
	('���������');

INSERT INTO item (item_txt, category_id, state_id, user_id)
VALUES('������ 1',1,1,1), ('������ 2',2,4,1);

INSERT INTO comments (msg, item_id)
VALUES ('����������', 2);

-- SELECT * FROM role;
-- SELECT * FROM rules;
-- SELECT * FROM role_rules;
-- SELECT * FROM t_user;
SELECT * FROM category;
SELECT * FROM state;
SELECT * FROM item;
SELECT * FROM comments;
SELECT * FROM attachs;