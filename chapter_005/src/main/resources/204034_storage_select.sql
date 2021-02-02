\c mstorage

-- 1. Вывести список всех машин и все привязанные к ним детали.
SELECT * FROM cars c
LEFT JOIN bodycars b ON c.bc_id = b.id
LEFT JOIN engines e ON c.en_id = e.id
LEFT JOIN transmissions t ON c.tr_id = t.id;

-- тот же запрос, но немного компактнее вывод
SELECT c.name car, b.name body, e.name engine, t.name transmissions FROM cars c
LEFT JOIN bodycars b ON c.bc_id = b.id
LEFT JOIN engines e ON c.en_id = e.id
LEFT JOIN transmissions t ON c.tr_id = t.id;

-- 2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
-- ВНИМАНИЕ! Возможно этот запрос работает не верно из-за неверного понимания ТЗ.
-- TODO необходимо уточнить у ментора, исправить
SELECT c.name car, b.name body, e.name engine, t.name transmissions FROM cars c
FULL JOIN bodycars b ON c.bc_id = b.id
FULL JOIN engines e ON c.en_id = e.id
FULL JOIN transmissions t ON c.tr_id = t.id
WHERE c.bc_id is null OR c.en_id is null OR c.tr_id is null
GROUP BY c.name, b.name, e.name, t.name;