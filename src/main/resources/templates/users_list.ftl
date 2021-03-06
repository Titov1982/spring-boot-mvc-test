<#import "common/main.ftl" as m>


<#--Страница вывода списка всех зарегистрированных пользователей-->

<@m.main>
    <div class="container">

        <h3>Список зарегистрированных пользователей</h3>

        <#--Выстовляем кастомный шрифт для таблицы-->
        <minimal-font>
            <#--Собираем заголовок таблицы-->
            <table class="table table-sm table-hover">
                <thead class="thead-light">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Имя пользователя</th>
                    <th scope="col">Имя</th>
                    <th scope="col">Фамилия</th>
                    <th scope="col">Email</th>
                    <th scope="col">Роли пользователя</th>
                    <th scope="col">Управление</th>
                </tr>
                </thead>
                <tbody>
                <#--Выводим в цыкле всех зарегистрированных пользователей-->
                <#list users as u>
                    <tr>
                        <td>${u.id}</td>
                        <td>${u.login}</td>
                        <td>${u.firstName}</td>
                        <td>${u.lastName}</td>
                        <td>${u.email}</td>
                        <td>
                            <#--Воводим список ролей пользователей-->
                            <#list u.roles as role>
                                ${role.role}<#sep>,
                            </#list>
                        </td>
                        <td>
                            <#--Форма обработки удаления пользователя.
                                Форма отсылает в контроллер ID выбранного для удаления пользователя.-->
                            <form action="/delete_user" method="post" class="form-check form-check-inline mr-1">
                                <input name="id" type="hidden" class="form-control" id="id" value="${u.id}">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <button type="submit" class="btn btn-outline-secondary btn-sm bg-danger">Del</button>
                            </form>

                            <#--<button id="deleteUserButton" data-delete="${u.id}" type="button" class="btn btn-outline-secondary btn-sm" data-toggle="modal" data-target="#deleteModalCenterWindow">Удалить</button>-->

                            <#--Форма обработки редактирования пользователя.
                                Форма отсылает в контроллер ID выбранного для редактирования пользователя.-->
                            <form action="/userEdit" method="get" class="form-check form-check-inline mr-1">
                                <input name="id" type="hidden" class="form-control" id="idUserEdit" value="${u.id}">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <button type="submit" class="btn btn-outline-secondary btn-sm">Edit</button>
                            </form>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </minimal-font>
    </div>


    <!-- Modal -->
    <div class="modal fade" id="deleteModalCenterWindow" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header"><h5 class="modal-title" id="exampleModalLongTitle">Подтверждение удаления</h5></div>
                <div class="modal-body">Вы уверены, что хотите удалить пользователя?</div>

                <div class="modal-footer">
                    <form>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <input name="id" type="hidden" class="form-control" id="idDeleteUser">
                        <button type="submit" class="btn btn-outline-secondary btn-sm bg-danger" >Удалить</button>
                        <button type="button" class="btn btn-outline-secondary btn-sm" data-dismiss="modal">Закрыть</button>
                    </form>
                </div>

            </div>
        </div>
    </div>

</@m.main>