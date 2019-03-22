

<#--Страница регистрации новых пользователей-->

<#include "common/header.ftl">

    <#--Форма для внесения параметров нового пользователя.
        Свойства name тегов <input> должно соответствовать
        названию параметра (@RequestParam) в соответствующем методе контроллера, отвечающего
        за обработку данных формы-->

<div class="container">

    <h3>Регистрация нового пользователя</h3>

    <form action="/registration" method="post">
        <div class="form-group">
            <label for="inputLogin">Имя пользователя</label>
            <input name="login" type="text" class="form-control" id="inputLogin" aria-describedby="loginHelp" placeholder="Введите имя пользователя">
            <small id="loginHelp" class="form-text text-muted">Не сообщайте свое имя пользователя посторонним.</small>
        </div>
        <div class="form-group">
            <label for="inputPassword">Пароль</label>
            <input name="password" type="password" class="form-control" id="inputPassword" placeholder="Введите пароль">
            <small id="passwordHelp" class="form-text text-muted">Не сообщайте свой пароль посторонним.</small>
        </div>
        <div class="form-group">
            <label for="inputFirstName">Имя</label>
            <input name="firstName" type="text" class="form-control" id="inputFirstName" aria-describedby="firstNameHelp" placeholder="Введите имя">
        </div>
        <div class="form-group">
            <label for="inputLastName">Фамилия</label>
            <input name="lastName" type="text" class="form-control" id="inputLastName" aria-describedby="lastNameHelp" placeholder="Введите фамилию">
        </div>
        <div class="form-group">
            <label for="inputEmail">Email</label>
            <input name="email" type="email" class="form-control" id="inputEmail" aria-describedby="emailHelp" placeholder="Введите email">
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-secondary btn-sm">Подтвердить</button>
        <button type="reset" class="btn btn-secondary btn-sm">Сбросить</button>
    </form>

</div>

<#include "common/footer.ftl">