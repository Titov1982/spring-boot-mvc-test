<#--Файл конфигурации navbar(меню).
Встраивается в основной шаблон страниц-->


<#-- Вставляем шаблон проверки входа пользователя в систему для изменения интерфейса-->
<#include "security.ftl">

<#--Вставляем макрос для вывода логина залогиненного пользователя пользователя справа в navbar-->
<#import "macros.ftl" as macros>


<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
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

        <#--Форма обработки редактирования пользователя.
            Форма отсылает в контроллер ID выбранного для редактирования пользователя.-->
        <form action="/userEdit" method="get" class="form-check form-check-inline mr-1">
            <input name="id" type="hidden" class="form-control" id="id" value=${currentUserId}>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-outline-secondary btn-sm">${login}</button>
        </form>

        <#--Если пользователь вошел-->
        <#if user??>
        <#--Формы выхода из приложения-->
            <form action="/logout" method="post" >
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit" class="btn btn-secondary btn-sm">Выход</button>
            </form>
        <#else>
        <#--Формы входа в приложения-->
            <form action="/login" method="post" >
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit" class="btn btn-secondary btn-sm">Вход</button>
            </form>
        </#if>

    </div>
</nav>