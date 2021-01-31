SELECT *
FROM devices d
LEFT JOIN owners o
ON d.owner_id = o.id;

SELECT *
FROM devices d
LEFT JOIN owners o
ON d.owner_id = o.id
WHERE o.id is null;

SELECT *
FROM owners o
LEFT JOIN devices d
ON o.id = d.owner_id;

\! echo --------

select * from devices d left join owners o on d.owner_id = o.id;
select * from owners o right join devices d on d.owner_id = o.id;

select * from owners o left join devices d on o.id = d.owner_id;
select * from devices d right join owners o on d.owner_id = o.id;

\! echo --------

select * from devices d full join owners o on d.owner_id = o.id;

\! echo --------

select * from devices d left join owners o on d.owner_id = o.id
union
select * from devices d right join owners o on d.owner_id = o.id;

select * from devices d cross join owners o;

select n1.num as a, n2.num as b, (n1.num * n2.num) as "a*b=" from numbers n1 cross join numbers n2;