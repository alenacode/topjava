DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;
ALTER SEQUENCE global_seq1 RESTART WITH 1;

INSERT INTO users (name, email, password) VALUES
    ('User', 'user@yandex.ru', 'password'),
    ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
    ('ROLE_USER', 100000),
    ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, datetime, description, calories) VALUES
    (100000, TIMESTAMP '2020-01-31 20:00:00', 'description_user', 300),
    (100000, TIMESTAMP '2020-01-31 20:01:00', 'description_admin', 500);

