
<#macro user_login >
    <#if login??>
        <p>${login}</p>
    <#else>
        <p>Гость</p>
    </#if>
</#macro>