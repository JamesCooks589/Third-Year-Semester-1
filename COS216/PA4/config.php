<?php
//James Cooks u21654680
//Create singleton class to connect to database
if(!isset($_SESSION)){
    session_start();
}

if(!isset($_COOKIE["api_key"])){
    $_COOKIE["api_key"] = "a9198b68355f78830054c31a39916b7f";
}
if(!isset($_SESSION["logged_in"])){
    $_SESSION["logged_in"] = false;
}

class DBConnection {
    public static function instance(){
        static $instance = null;
        if($instance === null){
            $instance = new DBConnection();
        }
        return $instance;
    }
    public $conn;
    private function __construct(){
        //connect to database
        $servername = "wheatley.cs.up.ac.za";
        $UserName = "u21654680";
        $DBPassword = "6WD6YG4XNUTBIEEN44EL2GGHT6H4BITE";
        $DBName = "u21654680";
        $this->conn = new mysqli($servername, $UserName, $DBPassword, $DBName);
        if ($this->conn->connect_error) {
            die("Connection failed: " . $this->conn->connect_error);
        }
    }

    public function __destruct()
    {
        $this->conn->close();
    }

    public function query($sql){
        $result = $this->conn->query($sql);
        if($result){
            return $result;
        }else{
            return $this->conn->error;
        }
    }

    public function close(){
        $this->conn->close();
    }


}



?>
