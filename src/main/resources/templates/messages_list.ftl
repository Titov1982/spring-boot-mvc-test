<#import "common/main.ftl" as m>

<#--Страница вывода сообщений зарегистрированных пользователей-->

<@m.main>

<#--Форма ввода нового сообщения пользователя-->
    <div class="container">
        <h3>Сообщения пользователей</h3>

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

    </div>

</@m.main>
