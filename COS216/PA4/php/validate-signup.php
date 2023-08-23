<?php
//James Cooks u21654680

$req = $_REQUEST;
$name = $req['name'];
$surname = $req['surname'];
$email = $req['email'];
$password = $req['password'];
$theme = "dark";
$transmission = "none";
$fuel = "none";


require_once '../../config.php';
$db = DBConnection::instance();

$conn = $db->conn;

//////////////////////////////////////////
//Password encryption//
/////////////////////////////////////////
    //Generate Salt 
    //take last letter of name and last letter of surname and prepend to password
    //take first letter of name and first letter of surname and append to password
    //append reverse of last 6 characters of email to password

    $salt = substr($name, -1) . substr($surname, -1) . substr($name, 0, 1) . substr($surname, 0, 1) . strrev(substr($email, -6));

    $saltedPassword = $salt . $password;

    //Hash password
    $password = hash('sha256', $saltedPassword);
/////////////////////////////////////////

//Sanitize input to prevent SQL injection
    $name = mysqli_real_escape_string($conn, $name);
    $surname = mysqli_real_escape_string($conn, $surname);
    $email = mysqli_real_escape_string($conn, $email);
    $password = mysqli_real_escape_string($conn, $password);


//GENERATE API KEY

    //Generate API key of length 10 the first 3 characters is the first letter of name and surname and email
    //the last 7 are genrated with bin2hex and random_bytes
    $APIkey = substr($name, 0, 1) . substr($surname, 0, 1) . substr($email, 0, 1) . bin2hex(random_bytes(7));





//Check if email already exists
$sql = "SELECT * FROM users WHERE Email = '$email'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    echo "Email already exists";
} else {
    //Insert new user into database
    $sql = "INSERT INTO users (name, Surname, email, password, APIkey, salt, prefrence, transmission, fuel) VALUES ('$name', '$surname', '$email', '$password', '$APIkey', '$salt', '$theme', '$transmission', '$fuel')";
    if (mysqli_query($conn, $sql)) {
        //jquery remove evrything in body
        echo "<script>$('*').empty();</script>";
        //display success message
        echo "<h1>Success!</h1>";
        echo "<h2>You have been signed up succesfully</h2>";
        echo "<h2>You API key is : " . $APIkey . "</h2>";
        echo "<h2><a href='../index.php'>Back to Home page</a></h2>";
        echo "<h2><a href='login.php'>Login</a></h2>";
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($conn);
    }
}

?>