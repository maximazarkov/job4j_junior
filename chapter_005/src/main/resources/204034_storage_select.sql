\c mstorage

-- 1. ¬ывести список всех машин и все прив€занные к ним детали.
SELECT c.name car, b.name body, e.name engine, t.name transmissions FROM cars c
INNER JOIN bodycars b ON c.bc_id = b.id
INNER JOIN engines e ON c.en_id = e.id
INNER JOIN transmissions t ON c.tr_id = t.id;

-- 2. ¬ывести отдельно детали, которые не используютс€ в машине, кузова, двигатели, коробки передач.
SELECT b.name body FROM bodycars b
LEFT JOIN cars c ON c.bc_id = b.id
WHERE c.bc_id is null;

SELECT e.name engine FROM engines e
LEFT JOIN cars c ON c.en_id = e.id
WHERE c.en_id is null;

SELECT t.name transmissions FROM transmissions t
LEFT JOIN cars c ON c.tr_id = t.id
WHERE c.tr_id is null;