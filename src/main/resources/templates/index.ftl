
<#--Главная страница приложения-->

<#include "common/header.ftl">

<div>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="submit" value="Sign Out"/>
    </form>
</div>

<div сlass="conteiner">

    <@m.user_login/>

</div>

<#include "common/footer.ftl">