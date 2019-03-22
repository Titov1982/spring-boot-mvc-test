
-- Выбрать всех пользователей с их ролями
select login, role from usr
                          inner join user_role ur
                                     on usr.id = ur.user_id
                          inner join role r
                                     on ur.role_id = r.id;


-- Выбрать указанного пользователя с его ролями
select login, role from usr u
                          inner join user_role ur
                                     on u.id = ur.user_id
                          inner join role r
                                     on ur.role_id = r.id
where u.login = 'admin';