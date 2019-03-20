
<#--Главная страница приложения-->

<#include "common/header.ftl">

    <H2>Hello from index.ftl</H2>
    <div>
        <p>Hello ${name}</p>
        <p><a href="/users">Посмотреть список пользователей</a></p>
        <p><a href="/registration">Регистрация</a></p>
    </div>

<#include "common/footer.ftl">