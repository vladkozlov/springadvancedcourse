<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Rest Api Docs</title>
</head>
<body>
<b style="color: red;">Info</b>
<p>
    Available REST API:<br>
    <p><b>NOTE:</b> you can control Accept and Content-type. Available are application/pdf and application/json. <br> pdf by default</p><br>

    <b>GET</b> <code><a href="/rest/users">/rest/users/</a></code> (Get all users PDF)<br>
    <b>GET</b> <code><a href="/rest/users/1">/rest/users/{userId}</a></code> (get particular user PDF)<br>

    <b>POST</b> <code><a href="javascript:void(0);" onclick="postUser()">/rest/users/</a></code> (add user)<br>
    <p>Note: clicking on the POST link will create new user test using rest api</p>

    <b>PUT</b> <code><a href="javascript:void(0);" onclick="putUser()">/rest/users/</a></code> (edit user)<br>
    <p>Note: clicking on the PUT link will change name of the first user</p>

    <b>DELETE</b> <code><a href="javascript:void(0);" onclick="deleteUser()">/rest/users/</a></code> (delete user)<br>
    <p>Note: clicking on the DELETE link delete user with id 1 (user vlad would be unavailable)</p>


    <b>GET</b><code><a href="/rest/images/cat">/rest/images/cat</a></code> (for REST call from remote resource)
</p>
</body>
<script>
function postUser() {
    var data = {
        "username":"testUser",
        "fullName":"Test User",
        "password": "test",
        "phoneNumbers":[
            {
                "company":"Test User Company",
                "number":"+666666"
            }
        ],
        "roles":"REGISTERED_USER"
    };

    fetch("/rest/users", {
        method: 'POST',
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json',
            'Accept' : 'application/json'
        },
        redirect: 'follow',
        referrer: 'no-referrer',
        body: JSON.stringify(data)
    })
    .then(function (response) { alert("successfully created user " + JSON.stringify(data)) });
}
function putUser() {
    var data = {
        "fullName": "Vladislav K."
    };

    fetch("/rest/users/1", {
        method: 'PUT',
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json',
            'Accept' : 'application/json'
        },
        redirect: 'follow',
        referrer: 'no-referrer',
        body: JSON.stringify(data)
    })
        .then(function (response) { alert("successfully changed the name of the user 1 " + JSON.stringify(data)) });
}
function deleteUser() {

    fetch("/rest/users/1", {
        method: 'DELETE',
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json',
            'Accept' : 'application/json'
        },
        redirect: 'follow',
        referrer: 'no-referrer'
    })
    .then(function (response) { alert("successfully deleted the the user 1 (vlad)" ) });
}

</script>
</html>