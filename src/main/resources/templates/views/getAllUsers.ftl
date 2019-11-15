<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hey!</title>
</head>
<body>
<h2>List of users!</h2>
    <table class="datatable">
        <tr>
            <th>Id</th>
            <th>Full name</th>
            <th>Phone numbers</th>
        </tr>
        <#list users as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.fullName}</td>
                <td><#list user.phoneNumbers as num>${num.number} (${num.company}) <br> </#list></td>
            </tr>
        </#list>
    </table>
</body>
</html>