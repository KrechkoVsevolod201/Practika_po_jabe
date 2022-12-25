<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="charset" content="windows-1251">
<title>Create Student</title>
</head>
<body>
<div  style="font-size: 20px; text-align: center; padding: 30px; margin: 50px; border: 5px solid pink; border-radius: 5px; display: flex; flex-direction: column; justify-content: center;">
<h3> Enter the data to create a title:</h3>
    <form method="post" action="createtitle">
    <label>
        <span>Book_id: </span>
        <input type="number" name="book_id"/><br><br>
    </label>
    <label>
        <span>Book_title: </span>
        <input type="text" name="book_title"/><br><br>
    </label>
    <input type="submit" value="Create!" />
</div>
</body>
</html>