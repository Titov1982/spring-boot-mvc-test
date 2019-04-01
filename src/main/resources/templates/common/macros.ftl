
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
