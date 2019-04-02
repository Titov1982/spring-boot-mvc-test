



<#--Определяем наличие залогиненного пользователя-->
<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>

<#--Если пользователь залогинен, то полученм его объект и т.д.-->
<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    login = user.getLogin()
    <#--isAdmin = user.isAdmin()-->
    currentUserId = user.getId()
    >
<#--Иначе, говорим, что это гость-->
<#else>
    <#assign
    login = "Гость"
    <#--isAdmin = false-->
    currentUserId = -1
    >
</#if>
