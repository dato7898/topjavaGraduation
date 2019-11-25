DELETE FROM user_roles;
DELETE FROM lunch;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);

INSERT INTO lunch (price, date, description)
VALUES (2000, '2015-05-30', 'Средне'),
       (1500, '2015-05-30', 'Бедно'),
       (3000, '2015-05-30', 'По богатому'),
       (3000, '2015-05-31', 'По богатому'),
       (2500, '2015-05-31', 'Средне'),
       (2000, '2015-05-31', 'Бедно');