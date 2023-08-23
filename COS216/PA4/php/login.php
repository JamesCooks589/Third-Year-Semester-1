<!--James Cooks u21654680-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="login">
        <h1>Login</h1>
        <div id="error"></div>
        <form action="" method="post" id="SignIn">
            <input type="text" name="email" placeholder="Email" required id="email">
            <input type="password" name="password" placeholder="Password" required id="pass">
            <input type="button" value="Login" id="BtnSubmit">
        </form>

        <div id="cancel">
            <a href="../index.php">Cancel</a>
        </div>
        <script src="../js/login.js"></script>

    
</body>
</html>