<html>
<head></head>

<body>
<h1>Login</h1>

<form name='f' action="/login" method='POST'>
    <#if message??>
        <h3>Message:</h3>
        <p><i>${message}</i></p>
    </#if>
    <h1>Custom login</h1>
    <p>REGISTERED_USER login: vlad password: vlad</p>
    <p>BOOKING_MANAGER Use login: admin password: admin</p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='username' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password' /></td>
        </tr>
        <tr>
            <td>Remember Me:</td>
            <td><input type="checkbox" name="remember-me-plz" /></td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="submit" /></td>
        </tr>
    </table>
</form>

</body>
</html>