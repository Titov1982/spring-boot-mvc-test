

<#--Шаблон заголовка для всех страниц-->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<#--Включаем моноширинный стиль текста на всех страницах.
    Закрывающий </div> находится в шаблоне footer.ftl-->
<div class="text-monospace">

<#--Добавляем меню из шаблона navbar.ftl для всех страниц-->
<#include "navbar.ftl">