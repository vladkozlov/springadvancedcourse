<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h2>My profile</h2>
<h4>My name: ${user.fullName}</h4>
<h4>My cell operator: <#if account.getCellOperator()?has_content>${account.getCellOperator()}</#if></h4>
<h4>My phone number: <#if account.getPhoneNumber()?has_content>${account.getPhoneNumber()}</#if></h4>
<h4>My balance: <#if account.getBalance()?has_content>${account.getBalance()}</#if></h4>


<hr>
<h1>Change cell operator</h1>
<form name='f' action="/me/changeMobileOperator" method='POST'>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <table>
        <tr>
            <td>Operator name:</td>
            <td><input type='text' name='operator' value='' placeholder="Input operator name"></td>
        </tr>
        <tr>
            <td>Phone number:</td>
            <td><input type='text' name='number' value='<#if account.getPhoneNumber()?has_content>${account.getPhoneNumber()}</#if>' placeholder="Input operator name"></td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="submit" /></td>
        </tr>
    </table>
</form>
</body>
</html>