
<#--Главная страница приложения-->

<#--<#import "common/macros.ftl" as m>-->

<#include "common/header.ftl">

<div>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="submit" value="Sign Out"/>
    </form>
</div>

<div сlass="conteiner">
    <H3>Hello from index.ftl</H3>

    <@m.user_login/>

    <#--<#if login??>-->
        <#--<p>Hello ${login}</p>-->
    <#--<#else>-->
        <#--<p>Hello гость</p>-->
    <#--</#if>-->
</div>

<#include "common/footer.ftl">