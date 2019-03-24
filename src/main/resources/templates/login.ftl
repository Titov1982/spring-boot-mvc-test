<#import "common/main.ftl" as m>

<#--Страница входа зарегистрированного пользователей-->

<@m.main>
    <div class="container">

        <div class="container">
        </div>

        <div class="container col-4">
            <h3>Вход в приложение</h3>
            <form action="/login" method="post">
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

                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit" class="btn btn-secondary btn-sm">Войти</button>
            </form>

            <div class="mt-2">
                <a href="/registration">Зарегистрировать нового пользователя</a>
            </div>
        </div>

        <div class="container">
        </div>

    </div>
</@m.main>

