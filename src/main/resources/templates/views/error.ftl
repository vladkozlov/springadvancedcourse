<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error occurred!</title>
</head>
<body>

    <h2>Unfortunately error occurred!</h2>
    <h3>This is a custom message</h3>
    <#if msg??>
    <p>Additional information is below:</p>
    <p style="color: red;">${msg}</p>
    </#if>
</body>
</html>