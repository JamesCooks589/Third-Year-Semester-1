<?php 
//James Cooks u21654680
require_once 'config.php';
//Get request JSON object sent by client via xhttp.send
$req = json_decode(file_get_contents('php://input'), true);

//Response object which returns a JSON object

function response($code, $message){
    header("Content-Type: application/json");
    if ($code == 200){
        $response = array(
            "status" => "success",
            "timestamp" => time(),
            "data" => $message
        );
    }
    else
    {
        $response = array(
            "status" => $code,
            "timestamp" => time(),
            "Data" => "ERROR. " . $message
        );
    }
    
    echo json_encode($response);
    return;
}

//Check if request is empty
if(empty($req)){
    response(400, 'No request provided');
    return;
}

//API KEY

    if(!isset($req['apikey'])){
        response(400, 'No API key provided');
        return;
    }

    //Check if api key is valid
    if($req['apikey'] != "a9198b68355f78830054c31a39916b7f" && $_SESSION['logged_in'] == false){
        response(401, 'Invalid API key');
        return;
    }
    else if($_SESSION['logged_in'] == true && $req['apikey'] != $_COOKIE['api_key']){
        response(401, 'Invalid API key');
        return;
    }
    

//TYPE

    //Check if request contains type
    if(!isset($req['type'])){
        response(400, 'No type provided');
        return;
    }
    else
    {
        //assign type to variable
        $type = $req['type'];
    }

//RETURN

    //Check if request contains return type
    if(!isset($req['return'])){
        response(400, 'No return type provided');
        return;
    }
    else
    {
        //assign return type to variable
        $return = $req['return'];
        //if return type is an array turn it into a comma seperated string
        if(is_array($return)){
            $return = implode(',', $return);
        }    
    }

    //if image is requested check remove it from return type and flag that it was removed
    if(strpos($return, 'image') !== false){
        $return = str_replace('image', '', $return);
        $image = true;
    }
    else
    {
        $image = false;
    }


//LIMIT
      
    if(isset($req['limit'])){
        //assign limit to variable
        if($req['limit'] > 0 && $req['limit'] <= 500){
            $limit = $req['limit'];
        }
        else
        {
            response(400, 'Invalid limit');
            return;
        }
    }
    else
    {
        $limit = 50;
    }

//SORT

    if(isset($req['sort'])){
        //assign sort to variable
        $sort = $req['sort'];
    }
    else
    {
        $sort = 'id_trim';
    }

//ORDER
    
        if(isset($req['order'])){
            //Can only be ASC or DESC
            if($req['order'] == 'ASC' || $req['order'] == 'DESC'){
                //assign order to variable
                $order = $req['order'];
            }
            else
            {
                response(400, 'Invalid order type');
                return;
            }
        }
        else
        {
            $order = 'ASC';
        }

//FUZZY

    if(isset($req['fuzzy'])){
        //assign fuzzy to variable
        $fuzzy = $req['fuzzy'];
    }
    else
    {
        $fuzzy = true;
    }

//Disitinct
    if($type == 'GetAllMakes'){
        $query = "SELECT DISTINCT make FROM cars";

        //Run query and return response
        $db = DBConnection::instance();

        $results = $db->query($query);

        $json = array();

        while($row = $results->fetch_assoc()){
            $json[] = $row;
        }

        response(200, $json);
        return;
    }

//Update
    if($type == 'Update'){
        if(!isset($_SESSION)){
            session_start();
        }
        
        //Check if user is logged in
        if($_SESSION['logged_in'] == false){
            response(401, 'You must be logged in to update prefrences');
            return;
        }

        //Update user prefrences, transmission and fuel type
        $query = "UPDATE users SET prefrence = '" . $req['theme'] . "', transmission = '" . $req['transmission'] . "', fuel = '" . $req['fuel'] . "' WHERE APIkey = '" . $_COOKIE['api_key'] . "'";

        //Run query and return response
        $db = DBConnection::instance();

        $results = $db->query($query);

        if($results){
            response(200, 'Prefrences updated');
            return;
        }
        else
        {
            response(400, 'Error updating prefrences');
            return;
        }

        

    }

//Filter
    if($type == 'filter'){
        if(!isset($_SESSION)){
            session_start();
        }

        //Process request
        $search = $req['search'];
        $transmission = $search['transmission'];
        $fuel = $search['engine_type'];
        $make = $search['make'];

        $limit = $req['limit'];


        $makeQuery = "`make` = '" . $make . "'";

        if($transmission == "none"){
            $transmission = '%';
            $transQuery = "`transmission` LIKE '" . $transmission . "'";
        }
        else{
            $transQuery = "`transmission` = '" . $transmission . "'";
        }

        if($fuel == "none"){
            $fuel = '%';
            $fuelQuery = "`engine_type` LIKE '" . $fuel . "'";
        }
        else{
            $fuelQuery = "`engine_type` = '" . $fuel . "'";
        }

        //Query
        $query = "SELECT * FROM cars WHERE " . $makeQuery . " AND " . $transQuery . " AND " . $fuelQuery . " ORDER BY id_trim ASC LIMIT " . $limit;


        //Run query and return response
        $db = DBConnection::instance();

        $results = $db->query($query);

    }

//Rating

    if($type == 'RateCar'){
        if($req['rating'] == "like"){
            $query = "UPDATE ratings SET likes = likes + 1 WHERE id_trim = '" . $req['id_trim'] . "'";
        }
        else if($req['rating'] == "dislike"){
            $query = "UPDATE ratings SET dislikes = dislikes + 1 WHERE id_trim = '" . $req['id_trim'] . "'";
        }
        
        //Run query and return response
        $db = DBConnection::instance();


        $results = $db->query($query);
    }

//GetCarRating
    if($type == "GetCarRating"){
        $query = "SELECT likes, dislikes FROM ratings WHERE id_trim = '" . $req['id_trim'] . "'";

        //Run query and return response
        $db = DBConnection::instance();

        $results = $db->query($query);

        $json = array();

        while($row = $results->fetch_assoc()){
            $json[] = $row;
        }

        response(200, $json);
        return;
    }

//Search
//Search will be a JSON object where the key is the column name and the value is the value to search for

    if(isset($req['search'])){
        //assign search to variable
        $search = $req['search'];
        //Set variables to search values for query
        if (isset($search['make'])){
            $make = $search['make'];
            //sanitize make
            $make = str_replace(' ', '', $make);
        }
        else
        {
            $make = '%';
        }
        if (isset($search['model'])){
            $model = $search['model'];

            //sanitize model
            $model = str_replace(' ', '', $model);
        }
        else
        {
            $model = '%';
        }
        if (isset($search['body_type'])){
            $body_type = $search['body_type'];
        }
        else
        {
            $body_type = '%';
        }
        if (isset($search['engine_type']) && $search['engine_type'] != 'none'){
            $engine_type = $search['engine_type'];
        }
        else
        {
            $engine_type = '%';
        }
        if (isset($search['transmission']) && $search['transmission'] != 'none'){
            $transmission = $search['transmission'];
        }
        else
        {
            $transmission = '%';
        }

        if($fuzzy == true){
            $make = '%' . $make . '%';
            $model = '%' . $model . '%';
            $body_type = '%' . $body_type . '%';
            $engine_type = '%' . $engine_type . '%';
            $transmission = '%' . $transmission . '%';
        }
        //Set where clause for query
        $where = "WHERE make LIKE '$make' AND model LIKE '$model' AND body_type LIKE '$body_type' AND engine_type LIKE '$engine_type' AND transmission LIKE '$transmission'";

        //Set query to search query
        $query = "SELECT $return FROM cars $where order by $sort $order LIMIT $limit";
    }
    else
    {
        //Set query to get all query
        $query = "SELECT $return FROM cars order by $sort $order LIMIT $limit";
    }

//Connect to database
require_once 'config.php';
$db = DBConnection::instance();

//Execute query
$results = $db->query($query);

//Fetch the assoiciated image with CURL GET to https://wheatley.cs.up.ac.za/api/getimage with headers 'make' and 'model' set to the make and model of the car
$url = 'https://wheatley.cs.up.ac.za/api/getimage?';
function fetchImage($url){
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, $url);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    $output = curl_exec($ch);
    //If output incleds a space replace it with %20 
    if(strpos($output, ' ') !== false){
        $output = str_replace(' ', '%20', $output);
    }
    //Sometimes the image returns an error 
    curl_close($ch);
    return $output;
}

$json = array();

//Fetch results
while($row = $results->fetch_assoc()){
    //if image is requested or * is used in return type fetch the image
    if($image == true || $return == '*'){
        //Store make and model in variables
        $make = $row['make'];
        $model = $row['model'];
        //URL encode make and model
        $make = urlencode($make);
        $model = urlencode($model);

        //Set URL for CURL request
        $url = $url . 'brand=' . $make . '&model=' . $model;
        //Fetch image
        $image = fetchImage($url);
        //Add image to row
        $row['image'] = $image;
    }
    //Add row to json array
    $json[] = $row;
       
}

//Return results
response(200, $json);

return;



?>