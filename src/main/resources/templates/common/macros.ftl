
<#--Получаем из модели login пользователя или замену-->
<#macro user_login>
    <#if user??>
        ${user.login}
    <#else>
        Гость
    </#if>
</#macro>

<#--Получаем из модели ID пользователя или замену-->
<#macro user_id>
    <#if user??>
        ${user.id}
    <#else>
        GUEST
    </#if>
</#macro>

<#--Формы выхода из приложения-->
<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-secondary btn-sm">Выход</button>
    </form>
</#macro>