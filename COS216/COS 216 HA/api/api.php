<?php
    //James Cooks u21654680
    
    require_once("config.php");

    $uri = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);
    $uri = explode( '/', $uri );

    if ($uri[4] !== "GetRandomBrands"){
        header("HTTP/1.1 404 Not Found");
        exit();
    }

    $method = $_SERVER['REQUEST_METHOD'];

    $db = DBConnection::instance(); 



    if ($method == 'GET') {
        $sql = "SELECT * FROM brands ORDER BY RAND() LIMIT 5";
        $result = $db->query($sql);

        if($result->num_rows > 0){
            $brands = array();
            while($row = $result->fetch_assoc()){
                $brands[] = $row;
            }
            header('Content-Type: application/json');
            echo json_encode($brands);
        }else{
            header("HTTP/1.1 404 Not Found");
            exit();
        }
    }
    else {
        header("HTTP/1.1 404 Not Found");
        exit();
    }   

?>