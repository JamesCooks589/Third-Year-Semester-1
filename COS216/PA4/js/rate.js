//The api will return a list of cars but store them in an array to only show one car at a time
var cars = [];

//Get api_key in cookie
function getCookie(name) {
    var cookieArr = document.cookie.split(";");

    for (var i = 0; i < cookieArr.length; i++) {
        var cookiePair = cookieArr[i].split("=");

        if (name == cookiePair[0].trim()) {
            return decodeURIComponent(cookiePair[1]);
        }
    }
    return null;
}

var make = ["BMW", "Honda", "Mercedes-Benz", "Nissan"];
var limit = 3;
var api_key = getCookie("api_key");

//Get all cars from api and store them in cars array
function getCars() {
    for (var j = 0; j < make.length; j++){
        //Request the data from the server
       var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "../api.php", true);
            xhttp.send(JSON.stringify({
                "type":"GetAllCars",
                "limit": limit,
                "apikey": api_key,
                "search":{
                    "make": make[j]
                },
                "order" : "DESC",
                "sort" : "max_speed_km_per_h",
                "fuzzy": true,
                "return" : '*'
            }));
    
            //Process response
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    var response =  JSON.parse(this.responseText);
                    //loop through the data and add it to the html
                    for (var i = 0; i < limit; i++) {
                        var car = response.data[i];
                        cars.push(car);
                    }
                    
                }
            }
        }
}

getCars();

setTimeout(function() {
    if (cars.length > 0) {
        displayCar(0);
    }
    else
    {
        //wait 1 second and try again
        setTimeout(function() {
            if (cars.length > 0) {
                displayCar(0);
            }
        }
        , 1000);
    }
}, 1000);


//Display cars in html
function displayCar(i) {
    var html = "";
    var car = cars[i];
    html = "<div class='container'>";
    html += "<div class='car'>";
    html += "<div class='carVisual'>";
    html += "<h3>" + car.make + " " + car.model + "</h3>";
    html += "<img id=carIMG" + i + " alt='Image of car' class='carImage' src=" + car.image + " />";
    html += "</div>";
    html += "<div class='carInfo'>";
    html += "<h3>Year - " + car.year_from + "</h3>";
    html += "<h3>Brand - " + car.make + "</h3>";
    html += "<h3>Transmission - " + car.transmission + "</h3>";
    html += "<h3>Fuel - " + car.engine_type + "</h3>";
    html += "<h3>Body type - " + car.body_type + "</h3>";
    html += "<h3>Drive type - " + car.drive_wheels + "</h3>";
    html += "<h3>Top speed - " + car.max_speed_km_per_h + "km/h</h3>";
    html += "</div>";
    html += "</div>";
    html += "</div>";
    $(".cars").html(html);

    //Display rating
    displayRating();
}


//On click of like button send data to api and select next car
function likeCar(){
    console.log("like fired");
    var car = cars[0];
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "../api.php", true);
    xhttp.send(JSON.stringify({
        "type":"RateCar",
        "apikey": api_key,
        "id_trim" : car.id_trim,
        "car_model ": car.model,
        "search":{"make": car.make},
        "rating": "like",
        "return" : '*'
    }));

    //Process response
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var response =  JSON.parse(this.responseText);
            cars.shift();
            displayCar(0);

        }
    }
    

}

//On click of dislike button send data to api and select next car
function dislikeCar(){
    var car = cars[0];
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "../api.php", true);
    xhttp.send(JSON.stringify({
        "type":"RateCar",
        "apikey": api_key,
        "id_trim" : car.id_trim,
        "car_model ": car.model,
        "search":{"make": car.make},
        "rating": "dislike",
        "return" : '*'
    }));

    //Process response
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var response =  JSON.parse(this.responseText);
            cars.shift();
            displayCar(0);
        }
    }
    

}

//Display average rating of car
function displayRating(){
    var car = cars[0];
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "../api.php", true);
    xhttp.send(JSON.stringify({
        "type":"GetCarRating",
        "apikey": api_key,
        "id_trim" : car.id_trim,
        "car_model ": car.model,
        "search":{"make": car.make},
        "return" : '*'
    }));

    //Process response
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var response =  JSON.parse(this.responseText);
            //Take likes and subtract dislikes
            var rating = response.data[0].likes - response.data[0].dislikes;
            $("#AvgRatingValue").html(rating);
        }
    }
}


$("#Like").click(function(){
    console.log("like");
    likeCar();
});

$("#Dislike").click(function(){
    console.log("dislike");
    dislikeCar();
});



    



