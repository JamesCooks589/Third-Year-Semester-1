//James Cooks u21654680
//I use asyncronous calls so that my javascript can continue to run while the data is being loaded

//xhttp request to get the data from the server
var studentnum = "u21654680";
var html = "";
var limit = 4;
var make = ["BMW","Mercedes-Benz","Audi","Jaguar","Lamborghini"];

//Get the data from the server
for (var j = 0; j < make.length; j++){
    //Request the data from the server
   var xhttp = new XMLHttpRequest();
xhttp.open("POST", "../api.php", true);
        xhttp.send(JSON.stringify({
            "type":"GetAllCars",
            "limit": limit,
            "apikey":"a9198b68355f78830054c31a39916b7f",
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
                console.log(this.responseText);
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
            "apikey":"a9198b68355f78830054c31a39916b7f",
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
            "apikey":"a9198b68355f78830054c31a39916b7f",
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

setTimeout(getAllCars, 1000);
//get all cars in dom
function getAllCars(){
    var cars = document.querySelectorAll(".container");
    var carsInDom = [];
    for (var i = 0; i < cars.length; i++) {
        carsInDom.push(cars[i]);
    }
    return carsInDom;
}

//filter cars by transmission
function filterCarsTrans(){
    //if both are unchecked return 0
    var manual = document.getElementById("Manual");
    var automatic = document.getElementById("Automatic").checked;
    if (!manual && !automatic) {
        return 0;
    }
    var transmissionType = document.querySelectorAll('input[name="Transmission"]:checked')[0].value;
    var carsInDom = getAllCars();
    //Get value after '-' in transmission
    var result = [];
    for (var i = 0; i < carsInDom.length; i++) {
        var carTransmission = carsInDom[i].children[0].children[1].children[2].innerHTML.split("- ")[1];
        if (carTransmission == transmissionType) {
            result.push(carsInDom[i]);
        }
    }
    
    console.log(result);
    return result;
}

//filter cars by fuel
function filterCarsFuel(){
    var carsTrans = filterCarsTrans();
    var petrol = document.getElementById("Petrol").checked;
    var diesel = document.getElementById("Diesel").checked;
    if (!petrol && !diesel) {
        return 0;
    }
    var fuelType = document.querySelectorAll('input[name="Fuel"]:checked')[0].value;
    var carsInDom = getAllCars();
    //Get value after '-' in transmission
    var result = [];
    for (var i = 0; i < carsTrans.length; i++) {
        var carFuel = carsTrans[i].children[0].children[1].children[3].innerHTML.split("- ")[1];
        if (carFuel == fuelType) {
            result.push(carsTrans[i]);
        }
    }
    console.log(result);
    return result;
}


document.getElementById("ApplyFilters").addEventListener("click", function(){
    $("body").append("<div class='loader'><img src='https://cdn.dribbble.com/users/492711/screenshots/2528440/media/e831c923cb7e821b137c60055378ca4d.gif'/></div>");
    setTimeout(function() {
        const loader = document.querySelector(".loader");
        loader.classList.add("loader-hidden");
    }, 750);
    $(".loader").on("transitionend", function() {
        $(".loader").remove();
    });
    //if both filter types arent check alert
    var manual = document.getElementById("Manual").checked;
    var automatic = document.getElementById("Automatic").checked;
    var petrol = document.getElementById("Petrol").checked;
    var diesel = document.getElementById("Diesel").checked;
    if ((!manual && !automatic) || (!petrol && !diesel)) {
        alert("Please select both filters");
        return;
    }
    var carsInDom = filterCarsFuel();
    $(".cars").empty();
    $(".cars").prev("h1").empty();
    $(".cars").prev("h1").append("<h3>Filtered Cars</h3>");
    for (var i = 0; i < carsInDom.length; i++) {
        $(".cars").append(carsInDom[i]);
    }
});

//sort all cars in dom
function sortCars(){
    var cars = getAllCars();
    //Sort by name of make and model based on the select value
    var sortType = document.getElementById("Sort").value;
    if (sortType == "NameAZ") {
        cars.sort(function(a, b){
            var nameA = a.children[0].children[0].children[0].innerHTML.toUpperCase();
            var nameB = b.children[0].children[0].children[0].innerHTML.toUpperCase();
            if (nameA < nameB) {
                return -1;
            }
            if (nameA > nameB) {
                return 1;
            }
            return 0;
        });
    } else if (sortType == "NameZA") {
        cars.sort(function(a, b){
            var nameA = a.children[0].children[0].children[0].innerHTML.toUpperCase();
            var nameB = b.children[0].children[0].children[0].innerHTML.toUpperCase();
            if (nameA < nameB) {
                return 1;
            }
            if (nameA > nameB) {
                return -1;
            }
            return 0;
        });
    }
    //Sort by year based on the select value
    else if (sortType == "YearNew") {
        cars.sort(function(a, b){
            var yearA = a.children[0].children[1].children[0].innerHTML.split("- ")[1];
            var yearB = b.children[0].children[1].children[0].innerHTML.split("- ")[1];
            return yearB - yearA;
        });
    } else if (sortType == "YearOld") {
        cars.sort(function(a, b){
            var yearA = a.children[0].children[1].children[0].innerHTML.split("- ")[1];
            var yearB = b.children[0].children[1].children[0].innerHTML.split("- ")[1];
            return yearA - yearB;
        });
    }
    return cars;
}

document.getElementById("SortBtn").addEventListener("click", function(){
    $("body").append("<div class='loader'><img src='https://cdn.dribbble.com/users/492711/screenshots/2528440/media/e831c923cb7e821b137c60055378ca4d.gif'/></div>");
    setTimeout(function() {
        const loader = document.querySelector(".loader");
        loader.classList.add("loader-hidden");
    }, 750);
    $(".loader").on("transitionend", function() {
        $(".loader").remove();
    });
    var result = sortCars();
    $(".cars").empty();
    $(".cars").prev("h1").empty();
    $(".cars").prev("h1").append("<h3>Sorted Cars</h3>");
    for (var i = 0; i < result.length; i++) {
        $(".cars").append(result[i]);
    }
});



