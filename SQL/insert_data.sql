
INSERT INTO usr (active, email, first_name, last_name, login, password) values (true, 'admin@admin.net', 'Admin', 'Admin', 'admin', 'admin');
INSERT INTO usr (active, email, first_name, last_name, login, password) values (true, 'user@user.net', 'User', 'User', 'user', 'user');


INSERT INTO role (role) values ('ADMIN_R');
INSERT INTO role (role) values ('USER_R');


INSERT INTO user_role (user_id, role_id) values (1, 1);
INSERT INTO user_role (user_id, role_id) values (1, 2);
INSERT INTO user_role (user_id, role_id) values (2, 2);


