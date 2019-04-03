



<#--Форма ввода нового сообщения пользователя
    класс summernote является редактором-->

<form class="summernote" action="/add_message" method="post">
    <div class="form-group">
        <label for="my-summernote">Введите новое сообщение:</label>
        <textarea id="my-summernote" name="message" class="form-control mb-1"></textarea>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-secondary btn-sm mt-1">Отправить</button>
        <button type="reset" class="btn btn-secondary btn-sm mt-1">Стереть</button>
    </div>
</form>