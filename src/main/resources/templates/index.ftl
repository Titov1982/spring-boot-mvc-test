
<#--Главная страница приложения-->

<#include "common/header.ftl">

<div>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="submit" value="Sign Out"/>
    </form>
</div>

<div сlass="conteiner">
    <H3>Hello from index.ftl</H3>
    <p>Hello ${name}</p>
</div>

<#include "common/footer.ftl">