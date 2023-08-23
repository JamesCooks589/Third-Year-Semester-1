<?php
    //James Cooks u21654680
    if(!isset($_SESSION)){
        session_start();
    }
    $req = $_REQUEST;
    $email = $req['email'];
    $password = $req['password'];

    require_once '../../config.php';
    $db = DBConnection::instance();

    //Check if email exists
    $conn = $db->conn;
    $sql = "SELECT * FROM users WHERE Email = '$email'";
    $result = $conn->query($sql);

    if ($result->num_rows == 0) {
        echo "Email does not exist";
        exit();
    }

    //Convert result to string
    $row = mysqli_fetch_assoc($result);

    $api_key = $row['APIkey'];
    $transmission = $row['transmission'];
    $fuel = $row['fuel'];
    $prefrence = $row['prefrence'];

    $saltedPassword = $row['salt'] . $password;

    $password = hash('sha256', $saltedPassword);

    //Check if password is correct
    if ($password != $row['password']) {
        echo "Incorrect password";
        exit();
    }

    //Log user in
    $_SESSION["logged_in"] = true;
    $_SESSION["name"] = $row['name'];
    //Set cookies
    setcookie('api_key', $api_key, time() + (86400 * 30), "/");
    setcookie('transmission', $transmission, time() + (86400 * 30), "/");
    setcookie('fuel', $fuel, time() + (86400 * 30), "/");
    setcookie('prefrence', $prefrence, time() + (86400 * 30), "/");

    
    
    echo "success";


?>