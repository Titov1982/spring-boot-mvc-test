<#--Файл конфигурации navbar(меню).
Встраивается в основной шаблон страниц-->

<#--Вставляем макрос для вывода логина залогиненного пользователя пользователя справа в navbar-->
<#import "macros.ftl" as m>

<#--fixed-top-->

<nav class="navbar navbar-dark bg-dark justify-content-end text-monospace">
    <a class="navbar-brand" href="/">@tai</a>
    <a class="nav-link text-secondary active" href="/registration">Регистрация</a>
    <a class="nav-link text-secondary" href="/users">Пользователи</a>
    <a class="nav-link text-secondary" href="/messages">Чат</a>
    <#--<a class="nav-link text-secondary" href="#" ><@m.user_login/></a>-->
    <a class="btn btn-primary btn-sm my-2 my-sm-0" href="#" role="button"><@m.user_login/></a>
    <#--<a class="nav-link text-secondary disabled" href="#">Disabled</a>-->
</nav>