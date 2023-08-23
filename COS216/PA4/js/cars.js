//James Cooks u21654680
//I use asyncronous calls so that my javascript can continue to run while the data is being loaded


//if api_key cookie is not set set it to the default value
if (getCookie("api_key") == null) {
    setCookie("api_key", "a9198b68355f78830054c31a39916b7f", 1);
}
else {
    api_key = getCookie("api_key");
}


//xhttp request to get the data from the server
var studentnum = "u21654680";
var html = "";
var limit = 4;
var make = ["BMW","Mercedes-Benz","Audi","Jaguar","Lamborghini"];

var theme = "dark";
var transmission = "none";
var fuel = "none";

//Call getPrefrences function after everything is loaded
if(api_key != "a9198b68355f78830054c31a39916b7f"){
    getPrefrences();
}

if($(".cars").length == 0 || api_key == "a9198b68355f78830054c31a39916b7f") {
    //Get the data from the server
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
                for (var i = 0; i <= limit; i++) {
                    var car = response.data[i];
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
                    $(".cars").append(html);
                }
                
            }
        }
    }
}
function loadIMg(brand, model, i) {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        if (this.readyState == 4 && this.status == 200) {
            var result = this.responseText;
            var img = document.getElementById("carIMG" + i);
            img.setAttribute("src", result);
        }

    }
    xhttp.open("GET", "https://wheatley.cs.up.ac.za/api/getimage?brand=" + brand + "&model=" + model,true);
    xhttp.send();
}
////////////////////////////////////////////////////////
//SEARCH FUNCTIONALITY
////////////////////////////////////////////////////////
document.getElementById("search").addEventListener("click", function(){
    var makeSearch = document.getElementById("searchMake").value;
    var modelSearch = document.getElementById("searchModel").value;
    if (makeSearch == "") {
        alert("Please enter a brand");
        return;
    }
    $(".cars").empty();
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "../api.php", true);
    if (modelSearch == "") {
        xhttp.send(JSON.stringify({
            "studentnum": studentnum,
            "apikey": api_key,
            "type":"GetAllCars",
            "search":{
                "make": makeSearch
            },
            "sort" : "max_speed_km_per_h",
            "fuzzy": true,
            "return" : "*"
        }));
    } else {
        xhttp.send(JSON.stringify({
            "studentnum": studentnum,
            "apikey": api_key,
            "type":"GetAllCars",
            "search":{
                "make": makeSearch,
                "model": modelSearch
            },
            "sort" : "max_speed_km_per_h",
            "fuzzy": true,
            "return" : "*"
    }));
    }

    xhttp.onload = function() {
        if (this.readyState == 4 && this.status == 200) {
            var response = JSON.parse(this.responseText);
            //loop through the data and add it to the html
            if (response.data.length == 0) {
                $(".cars").append("<h2>No cars found</h2>");
                return;
            }
            for (var i = 0; i < response.data.length; i++) {
                var car = response.data[i];
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
                $(".cars").append(html);
            }
            
        }
    }

});

////////////////////////////////////////////////////////
//FILTER FUNCTIONALITY
////////////////////////////////////////////////////////
function filter(transmission, fuel){
    $(".cars").empty();
    var make = ["BMW", "Honda", "Mazda", "Mercedes-Benz", "Nissan", "Toyota"];


    for(i = 0; i < make.length; i++){
        xhr2 = new XMLHttpRequest();
    xhr2.open("POST", "../api.php", true);
    xhr2.send(JSON.stringify({
        "type" : "filter",
        "apikey" : api_key,
        "search" : {"transmission" : transmission, "engine_type" : fuel, "make" : make[i]},
        "limit" : 5,
        "return" : "*"
    }));
    xhr2.onload = function() {
        if (this.readyState == 4 && this.status == 200) {
            var response = JSON.parse(this.responseText);
            //loop through the data and add it to the html
            if (response.data.length == 0) {
                return;
            }
            for (var i = 0; i < response.data.length; i++) {
                var car = response.data[i];
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
                $(".cars").append(html);
            }
            
        }
    }
}
}

//Apply filter button
    
$("#ApplyFilters").click(function(){
    var transmission = document.querySelector('input[name="Transmission"]:checked');
    //If neither of the radio buttons are checked, set transmission to none
    if(transmission == undefined){
        transmission = "none";
    }
    else{
        transmission = transmission.value;
    }

    var fuel = document.querySelector('input[name="Fuel"]:checked');
    //If neither of the radio buttons are checked, set fuel to none
    if(fuel == undefined){
        fuel = "none";
    }
    else{
        fuel = fuel.value;
    }
    filter(transmission, fuel);
});

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

//////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////Prefrences///////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////

//Get all prefrences from cookies and apply the filters
function getPrefrences(){
    var theme = getCookie("prefrence");
    var transmission = getCookie("transmission");
    var fuel = getCookie("fuel");

    //Change theme
    if (theme == "light") {
    $("body").css("background-color", "#eaeaea");
    }
    else {
        $("body").css("background-color", "rgb(26, 26, 26)");
    }


    //Apply filters
    filter(transmission, fuel);

}



    







