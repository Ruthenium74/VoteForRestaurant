DELETE FROM USER_ROLES;
DELETE FROM VOTES;
DELETE FROM DISHES;
DELETE FROM RESTAURANTS;
DELETE FROM USERS;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO USERS (name, email, password) VALUES
('User', 'user@gmail.com', 'userpassword'),
('Admin', 'admin@gmail.com', 'admin');

INSERT INTO USER_ROLES (USER_ID, ROLE) VALUES
(100000, 'USER'),
(100001, 'ADMIN'),
(100001, 'USER');

INSERT INTO RESTAURANTS (name) VALUES
('Сова'),
('Слон'),
('Уральские пельмени'),
('Рёбрышковая'),
('CHESTER'),
('У Гоги');

INSERT INTO DISHES (NAME, PRICE, RESTAURANT_ID) VALUES
('Салат цезарь', 35000, 100002),
('Борщ', 40000, 100002),
('Пюре с котлетой', 50000, 100002),
('Сбитень', 32000, 100002),
('Салат королевский', 36000, 100003),
('Паста карбонара', 65000, 100003),
('Капучино', 120000, 100003),
('Сельдь под шубой', 35000, 100004),
('Пельмени', 50000, 100004),
('Морс', 15000, 100004),
('Салат греческий', 38000, 100005),
('Ребрышки фирменные', 120000, 100005),
('Имбирный чай', 120000, 100005),
('Oatmeal', 25000, 100006),
('Beef sandwich', 35000, 100006),
('Pale ale', 35000, 100006),
('Хачапури по аджарски', 37000, 100007),
('Чахохбили', 42000, 100007),
('Саперави', 35000, 100007);

INSERT INTO DISHES (NAME, PRICE, RESTAURANT_ID, DATE) VALUES
('Old dish', 12500, 100002, '2020-06-22'),
('Old dish1', 32500, 100002, '2020-08-23');
