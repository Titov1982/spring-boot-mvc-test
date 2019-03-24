<#import "common/main.ftl" as m>

<#--Страница регистрации новых пользователей-->

<@m.main>
    <div class="container">

        <h3>Редактирование информации о пользователе</h3>

        <#--Форма для внесения параметров нового пользователя.
        Свойства name тегов <input> должно соответствовать
        названию параметра (@RequestParam) в соответствующем методе контроллера, отвечающего
        за обработку данных формы-->
        <form action="/userEdit" method="post">
            <div class="form-group">
                <label for="inputLogin">Имя пользователя</label>
                <input name="login" type="text" class="form-control" id="inputLogin" aria-describedby="loginHelp" placeholder="Введите имя пользователя" value="${user.login}">
                <small id="loginHelp" class="form-text text-muted">Не сообщайте свое имя пользователя посторонним.</small>
            </div>
            <div class="form-group">
                <label for="inputPassword">Пароль</label>
                <input name="password" type="password" class="form-control" id="inputPassword" placeholder="Введите пароль" value="${user.password}>
            <small id="passwordHelp" class="form-text text-muted">Не сообщайте свой пароль посторонним.</small>
            </div>
            <div class="form-group">
                <label for="inputFirstName">Имя</label>
                <input name="firstName" type="text" class="form-control" id="inputFirstName" aria-describedby="firstNameHelp" placeholder="Введите имя" value="${user.firstName}">
            </div>
            <div class="form-group">
                <label for="inputLastName">Фамилия</label>
                <input name="lastName" type="text" class="form-control" id="inputLastName" aria-describedby="lastNameHelp" placeholder="Введите фамилию" value="${user.lastName}">
            </div>
            <div class="form-group">
                <label for="inputEmail">Email</label>
                <input name="email" type="email" class="form-control" id="inputEmail" aria-describedby="emailHelp" placeholder="Введите email"  value="${user.email}">
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-secondary btn-sm">Подтвердить</button>
            <button type="reset" class="btn btn-secondary btn-sm">Сбросить</button>
        </form>

    </div>

</@m.main>
