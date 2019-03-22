

<#--Страница вывода списка всех зарегистрированных пользователей-->

<#include "common/header.ftl">

<div class="container">

    <h3>Список зарегистрированных пользователей</h3>

    <div class="container bg-light text-dark" >
        <#--Собираем заголовок таблицы-->
        <div class="row bg-secondary text-white">
            <div class="col-sm border border-white text-center">ID</div>
            <div class="col-sm border border-white text-center">Имя пользователя</div>
            <div class="col-sm border border-white text-center">Имя</div>
            <div class="col-sm border border-white text-center">Фамилия</div>
            <div class="col-sm border border-white text-center">Email</div>
            <div class="col-sm border border-white text-center">Управление</div>
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
                    <form action="/delete_user" method="post" class="form-check form-check-inline">
                        <input name="id" type="hidden" class="form-control" id="id" value="${user.id}">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-outline-secondary btn-sm">Удалить</button>
                    </form>
                    <#--<button type="button" class="btn btn-outline-secondary btn-sm" data-toggle="modal" data-target="#deleteModalCenterWindow" value="${user.id}">Удалить</button>-->
                </div>
            </div>
        </#list>
    </div>

</div>


<!-- Modal -->
<#--<div class="modal fade" id="deleteModalCenterWindow" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">-->
    <#--<div class="modal-dialog modal-dialog-centered" role="document">-->
        <#--<div class="modal-content">-->
            <#--<div class="modal-header">-->
                <#--<h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>-->
                <#--<button type="button" class="close" data-dismiss="modal" aria-label="Close">-->
                    <#--<span aria-hidden="true">&times;</span>-->
                <#--</button>-->
            <#--</div>-->
            <#--<div class="modal-body">-->
                <#--...-->
            <#--</div>-->
            <#--<div class="modal-footer">-->
                <#--<button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>-->
                <#--<form action="/delete_user" method="post">-->
                    <#--<input name="id" type="text" class="form-control" id="inputId" value="">-->
                    <#--<button type="submit" class="btn btn-outline-secondary btn-sm">Удалить</button>-->
                <#--</form>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->

<#include "common/footer.ftl">