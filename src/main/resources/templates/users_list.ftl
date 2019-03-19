<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Users</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>


    <div class="container bg-light text-dark" >
        <div class="row bg-secondary text-white">
            <div class="col-sm border border-success">ID</div>
            <div class="col-sm border border-success">Имя пользователя</div>
            <div class="col-sm border border-success">Имя</div>
            <div class="col-sm border border-success">Фамилия</div>
            <div class="col-sm border border-success">Email</div>
        </div>
        <#list users as user>
            <div class="row">
                <div class="col-sm border border-success">${user.id}</div>
                <div class="col-sm border border-success">${user.login}</div>
                <div class="col-sm border border-success">${user.firstName}</div>
                <div class="col-sm border border-success">${user.lastName}</div>
                <div class="col-sm border border-success">${user.email}</div>
            </div>
        </#list>
    </div>

    <p><a href="/">На главную страницу</a></p>



    <#--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>-->
    <#--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>-->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>