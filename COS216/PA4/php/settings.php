<?php 
    //James Cooks u21654680
    require_once '../../config.php';
    if(!isset($_SESSION)){
        session_start();
    }

    


?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Settings</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <h1>Settings</h1>
    <div id="error"></div>
    <form action="" method="post" id="Settings">
        <h2>Change settings and prefrences</h2>
        <!--Dark or light mode-->
        <h3>Light or Dark mode</h3>
        <select name="theme" id="theme">
            <option value="dark">Dark</option>
            <option value="light">Light</option>
        </select>
        <br>

        <!--Preffered filters-->
        <!--Transmission type-->
        
        <h3>Transmission type</h3>
        <select name="transmission" id="transmission">
            <option value="none">No prefrence</option>
            <option value="Manual">Manual</option>
            <option value="Automatic">Automatic</option>
        </select>
        <br>

        <!--Fuel type-->
        <h3>Fuel type</h3>
        <select name="fuel" id="fuel">
            <option value="none">No prefrence</option>
            <option value="Gasoline">Gasoline</option>
            <option value="Diesel">Diesel</option>
        </select>
        <br>
        <br>
        <input type="button" value="Save" id="save">
        <div id="cancel">
            <a href="../index.php">Cancel</a>
        </div>

    </form>

        <script src="../js/settings.js"></script>

</body>
</html>

