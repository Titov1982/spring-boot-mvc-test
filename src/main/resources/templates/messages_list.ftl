

<#--Страница вывода сообщений зарегистрированных пользователей-->

<#include "common/header.ftl">

<div class="container">
    <h3>Сообщения пользователей</h3>

    <#list messages as message>
        <div class="card mb-1">
            <h6 class="card-header">Дата и время:${message.datetime}</h6>
            <div class="card-body">
                <h6 class="card-title">Пользователь:${message.user.login}</h6>
                <p class="card-text"><h6>${message.message}</h6></p>
            </div>
        </div>
    </#list>


</div>


<#--Форма ввода нового сообщения пользователя-->
<div class="container mt-5">

    <#--action="/registration" method="post"-->
    <form action="/add_message" method="post">
    <div class="form-group">
        <label for="inputNewMassage">Введите новое сообщение:</label>
        <textarea name="message" class="form-control mb-1" id="inputNewMassage" rows="3"></textarea>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-secondary btn-sm">Отправить</button>
        <button type="reset" class="btn btn-secondary btn-sm">Стереть</button>
    </div>
    </form>



</div>

<#include "common/footer.ftl">