<html>
<head>
<meta charset="UTF-8">
<title>Create book</title>
</head>
<body>
<div style="font-size: 20px; text-align: center; padding: 30px; margin: 50px; border: 5px solid pink; border-radius: 5px; display: flex; flex-direction: column; justify-content: center;">
<h3> Enter the data to create a book: </h3>
    <form method="post" action="createbook">
        <label>
            <span>ID: </span>
            <input type="number" name="id"/><br><br>
        </label>
    <label>
        <span>book_id: </span>
        <input type="number" name="book_id"/><br><br>
    </label>
    <label>
        <span>price</span>
        <input type="number" name="price"/><br><br>
    </label>
    <label>
        <span>author_name: </span>
        <input type="text" name="author_name"/><br><br>
    </label>
    <input style="font-size: 25px;" type="submit" value="Create!" />
</div>
</body>
</html>