<#import "common/main.ftl" as m>

<#--Страница вывода сообщений зарегистрированных пользователей-->

<@m.main>

    <minimal-font>
        <#--Форма ввода нового сообщения пользователя-->
        <div class="container">
            <h3>Сообщения пользователей</h3>

            <#--Добавляем форму ввода сообщений из шаблона rich_edit_form.ftl-->
            <#include "common/rich_edit_form.ftl">
        </div>
    </minimal-font>

    <minimal-font>
        <div class="container mt-5">
            <#list messages as message>
                <div class="card mb-1">
                    <p class="card-header">Дата и время: ${message.datetime}</p>
                    <div class="card-body">
                        <p class="card-title">Пользователь: <strong>${message.user.login}</strong></p>

                        <p class="card-text">${message.message}</p>

                        <form action="/delete_message" method="post">
                            <input name="id" type="hidden" class="form-control" id="id" value="${message.id}">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-outline-secondary btn-sm">Удалить сообщение</button>
                        </form>
                    </div>
                </div>
            </#list>
    </minimal-font>
    </div>

</@m.main>
