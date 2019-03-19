<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Регистрация пользователя</title>
</head>
<body>
<h3>Регистрация нового пользователя</h3>
    <#--Форма для внесения параметров нового пользователя.
        Свойства name тегов <input> должно соответствовать
        названию параметра (@RequestParam) в соответствующем методе контроллера, отвечающего
        за обработку данных формы-->
    <form action="/registration" method="post">
        <p><input type="text" name="login"></p>
        <p><input type="password" name="password"></p>
        <p><input type="text" name="firstName"></p>
        <p><input type="text" name="lastName"></p>
        <p><input type="email" name="email"></p>
        <p><input type="submit" value="Submit" /> <input type="reset" value="Reset" /></p>
    </form>

    <p><a href="/">На главную страницу</a></p>

</body>
</html>