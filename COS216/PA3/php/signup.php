<!--James Cooks u21654680-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <link rel="stylesheet" href="../css/style.css">

</head>
<body>
    <h1>Sign Up</h1>
    <div id="error"></div>
    <form id="signupForm" action="">
        <input type="text" name="name" placeholder="Name" required id="name">
        <input type="text" name="surname" placeholder="Surname" required id="surname">
        <input type="text" name="email" placeholder="Email" required id="email">
        <input type="password" name="password" placeholder="Password" required id="password">
        <input type="button" value="Sign Up" id="submitButton">
    </form>
    <div id="cancel">
        <a href="../index.php">Cancel</a>
    </div>
    <script src="../js/signup.js"></script>
</body>
</html>