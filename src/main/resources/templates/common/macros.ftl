
<#macro user_login>
    <#if user??>
        ${user.login}
    <#else>
        Гость
    </#if>
</#macro>