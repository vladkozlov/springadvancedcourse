<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>File upload download</title>
</head>
<body>
    <h1>Upload your JSON Phone dictionary here</h1>
    <p><b>Note!</b> Example JSON file is located in /resources/static/phoneDict.json</p>
    <form action="/upload" method="post" target="_blank" enctype="multipart/form-data">
        Select JSON file to upload:
        <input type="file" name="file" id="file">
        <input type="submit" value="Upload json" name="submit">
    </form>
</body>
</html>