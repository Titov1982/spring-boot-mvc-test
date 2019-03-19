
INSERT INTO usr (email, first_name, last_name, login, password) values ('titov@mail.ru', 'Andrey', 'Titov', 'titov', '123qwe');
INSERT INTO usr (email, first_name, last_name, login, password) values ('asd@mail.ru', 'Asd', 'Dsa', 'asd', '456qwe');


INSERT INTO role (role) values ('USER_R');
INSERT INTO role (role) values ('ADMIN_R');

INSERT INTO user_role (user_id, role_id) values (1, 2);
INSERT INTO user_role (user_id, role_id) values (2, 1);
INSERT INTO user_role (user_id, role_id) values (1, 1);


