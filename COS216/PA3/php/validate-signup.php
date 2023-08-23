<?php
//James Cooks u21654680

$req = $_REQUEST;
$name = $req['name'];
$surname = $req['surname'];
$email = $req['email'];
$password = $req['password'];


//Connect to database
$servername = "wheatley.cs.up.ac.za";
$UserName = "u21654680";
$DB_Pass = "6WD6YG4XNUTBIEEN44EL2GGHT6H4BITE";
$DB_Name = "u21654680";

$conn = new mysqli($servername, $UserName, $DB_Pass, $DB_Name);

//Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

//////////////////////////////////////////
//Password encryption//
/////////////////////////////////////////
    //Generate Salt 
    //take last letter of name and last letter of surname and prepend to password
    //take first letter of name and first letter of surname and append to password
    //append reverse of last 6 characters of email to password

    $salt = substr($name, -1) . substr($surname, -1) . $password . substr($name, 0, 1) . substr($surname, 0, 1) . strrev(substr($email, -6));

    //Hash password
    $password = hash('sha256', $salt);
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
    $sql = "INSERT INTO users (name, Surname, email, password, APIkey) VALUES ('$name', '$surname', '$email', '$password', '$APIkey')";
    if (mysqli_query($conn, $sql)) {
        //jquery remove evrything in body
        echo "<script>$('*').empty();</script>";
        //display success message
        echo "<h1>Success!</h1>";
        echo "<h2>You have been signed up succesfully</h2>";
        echo "<h2>You API key is : " . $APIkey . "</h2>";
        echo "<h2>Please keep this key safe as it is the only way to access your account</h2>";
        echo "<h2><a href='../index.php'>Back to Home page</a></h2>";
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($conn);
    }
}

//close connection
$conn->close();







?>