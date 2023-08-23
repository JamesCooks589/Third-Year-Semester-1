<?php
    //James Cooks u21654680
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