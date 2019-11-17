<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hey!</title>
</head>
<body>
<form id="logout" action="/logout" method="post" >
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="submit" value="Logout me please" name="submit">
</form>

<h2>My profile</h2>
<a href="/me/account"><h3><b style="color: blue">My account. You can Change operator HERE</b></h3></a>
<h4>My name: ${user.fullName}</h4>
<h4>My roles are: ${user.roles}</h4>

<h4>My phone dictionary:</h4>
<table class="datatable">
    <tr>
        <th>Company</th>
        <th>Phone numbers</th>
    </tr>
    <#list user.phoneNumbers as num>
    <tr>
        <td>${num.company}</td>
        <td>${num.number}</td>
    </tr>
    </#list>
</table>
<h1>Upload your JSON Phone dictionary here:</h1>
<p><b>Note!</b> Example JSON file is located in /resources/static/phoneDict.json</p>
<form action="/upload" method="post" target="_blank" enctype="multipart/form-data">
    Select JSON file to upload:
    <input type="file" name="file" id="file">
    <input type="submit" value="Upload json" name="submit">
</form>

<#if isBookingManager??>
    <hr>
    <h5>This is only visible for Booking manager: </h5>
    <b>Download PDF</b>
    <a href="/users/pdf">Download PDF</a>
    <b>Get users as a HTML</b>
    <a href="/users" target="_blank">Get users in new tab</a>
    <hr>
</#if>
</body>
</html>