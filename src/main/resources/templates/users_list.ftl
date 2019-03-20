

<#--Страница вывода списка всех зарегистрированных пользователей-->

<#include "common/header.ftl">

<div class="container">

    <h3>Список зарегистрированных пользователей</h3>

    <div class="container bg-light text-dark" >
        <#--Собираем заголовок таблицы-->
        <div class="row bg-secondary text-white">
            <div class="col-sm border border-white">ID</div>
            <div class="col-sm border border-white">Имя пользователя</div>
            <div class="col-sm border border-white">Имя</div>
            <div class="col-sm border border-white">Фамилия</div>
            <div class="col-sm border border-white">Email</div>
        </div>
        <#--Выводим в цыкле всех зарегистрированных пользователей-->
        <#list users as user>
            <div class="row">
                <div class="col-sm border border-white">${user.id}</div>
                <div class="col-sm border border-white">${user.login}</div>
                <div class="col-sm border border-white">${user.firstName}</div>
                <div class="col-sm border border-white">${user.lastName}</div>
                <div class="col-sm border border-white">${user.email}</div>
                <div class="col-sm border border-white">
                    <form action="/delete_user" method="post">
                        <input name="id" type="hidden" class="form-control" id="inputId" value="${user.id}">
                        <button type="submit" class="btn btn-secondary">Удалить</button>
                    </form>
                </div>
            </div>
        </#list>
    </div>

</div>

<#include "common/footer.ftl">