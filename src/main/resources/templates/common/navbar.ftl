<#--Файл конфигурации navbar(меню).
Встраивается в основной шаблон страниц-->

<#--Вставляем макрос для вывода логина залогиненного пользователя пользователя справа в navbar-->
<#import "macros.ftl" as m>

<#--fixed-top-->

<#--<nav class="navbar navbar-dark bg-dark justify-content-end text-monospace">-->

    <#--<a class="navbar-brand mb-0 h1" href="/">@tai</a>-->

    <#--<div class="collapse navbar-collapse" id="navbarSupportedContent">-->
        <#--<ul class="navbar-nav mr-auto">-->
            <#--<li>-->
                <#--<a class="nav-link text-secondary active" href="/registration">Регистрация</a>-->
            <#--</li>-->
            <#--<li>-->
                <#--<a class="nav-link text-secondary" href="/users">Пользователи</a>-->
            <#--</li>-->
            <#--<li>-->
                <#--<a class="nav-link text-secondary" href="/messages">Чат</a>-->
            <#--</li>-->

            <#--<a class="btn btn-primary btn-sm my-2 my-sm-0" href="/userEdit" role="button"><@m.user_login/></a>-->
        <#--</ul>-->
    <#--</div>-->

<#--</nav>-->

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand mb-0 h1" href="/">@tai</a>


    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link text-secondary" href="/registration">Регистрация</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-secondary" href="/users">Пользователи</a>
            </li>

            <li class="nav-item">
                <a class="nav-link text-secondary" href="/messages">Чат</a>
            </li>
        </ul>
        <a class="btn btn-primary btn-sm my-2 my-sm-0" href="/userEdit" role="button"><@m.user_login/></a>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />

            <button type="submit" class="btn btn-secondary btn-sm">Выход</button>
        </form>
    </div>
</nav>