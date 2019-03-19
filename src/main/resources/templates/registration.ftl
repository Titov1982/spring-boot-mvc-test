<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Регистрация пользователя</title>
</head>
<body>
<h3>Регистрация нового пользователя</h3>
    <form action="/registration" method="post">
        <p><input type="text" value="${user.login}"></p>
        <p><input type="text" value="${user.password}"></p>
        <p><input type="text" value="${user.firstName}"></p>
        <p><input type="text" value="${user.lastName}"></p>
        <p><input type="text" value="${user.email}"></p>
        <#--<p><input type="text" value="${login}"></p>-->
        <#--<p><input type="text" value="${password}"></p>-->
        <#--<p><input type="text" value="${firstName}"></p>-->
        <#--<p><input type="text" value="${lastName}"></p>-->
        <#--<p><input type="text" value="${email}"></p>-->
        <p><input type="submit" value="Submit" /> <input type="reset" value="Reset" /></p>
    </form>

</body>
</html>