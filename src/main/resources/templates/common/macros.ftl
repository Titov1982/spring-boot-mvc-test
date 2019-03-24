
<#macro user_login>
    <#if user??>
        ${user.login}
    <#else>
        Гость
    </#if>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-secondary btn-sm">Выход</button>
    </form>
</#macro>