

<#--Страница вывода списка всех зарегистрированных пользователей-->

<#include "common/header.ftl">

<div class="container">

    <h3>Список зарегистрированных пользователей</h3>

    <div class="container bg-light text-dark" >
        <#--Собираем заголовок таблицы-->
        <div class="row bg-secondary text-white">
            <div class="col-sm border border-success">ID</div>
            <div class="col-sm border border-success">Имя пользователя</div>
            <div class="col-sm border border-success">Имя</div>
            <div class="col-sm border border-success">Фамилия</div>
            <div class="col-sm border border-success">Email</div>
        </div>
        <#--Выводим в цыкле всех зарегистрированных пользователей-->
        <#list users as user>
            <div class="row">
                <div class="col-sm border border-success">${user.id}</div>
                <div class="col-sm border border-success">${user.login}</div>
                <div class="col-sm border border-success">${user.firstName}</div>
                <div class="col-sm border border-success">${user.lastName}</div>
                <div class="col-sm border border-success">${user.email}</div>
            </div>
        </#list>
    </div>

</div>

<#include "common/footer.ftl">