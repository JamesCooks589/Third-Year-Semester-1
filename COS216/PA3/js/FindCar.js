//James Cooks u21654680
var studentnum = "u21654680";
var html = "";
document.getElementById("submit").addEventListener("click", function() {
    $("body").append("<div class='loader'><img src='https://cdn.dribbble.com/users/492711/screenshots/2528440/media/e831c923cb7e821b137c60055378ca4d.gif'/></div>");
    setTimeout(function() {
        const loader = document.querySelector(".loader");
        loader.classList.add("loader-hidden");
    }, 750);
    $(".loader").on("transitionend", function() {
        $(".loader").remove();
    });
    $(".cars").empty();
    var brand = document.getElementById("brand").value;
    if (brand == "") {
        alert("Please select a brand");
        document.getElementById("brand").focus();
        return;
    }
    var body_type = document.getElementById("body_type").value;
    if (body_type == "") {
        alert("Please select a body type");
        document.getElementById("body_type").focus();
        return;
    }
    var transmission = document.getElementById("transmission").value;
    if (transmission == "") {
        alert("Please select a transmission");
        document.getElementById("transmission").focus();
        return;
    }
    var drive_type = document.getElementById("drive_type").value;
    if (drive_type == "") {
        alert("Please select a drive type");
        document.getElementById("drive_type").focus();
        return;
    }
    var speed = document.getElementById("speed").value;
    var fuel_type = document.getElementById("fuel_type").value;
    var sort = "ASC";
    if (speed > 1) {
        sort = "DESC";
    }

    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "../api.php", true);
    if (fuel_type == ""){
        xhttp.send(JSON.stringify(
            {
                "studentnum": studentnum,
                "type": "GetAllCars",
                "apikey" : "a9198b68355f78830054c31a39916b7f",
                "search": {
                    "make" : brand,
                    "body_type" : body_type,
                    "transmission" : transmission,
                    "drive_wheels" : drive_type
                },
                "sort" : "max_speed_km_per_h",
                "order" : sort,
                "return" : "*"
            }
        ));
    }
    else {
        xhttp.send(JSON.stringify(
            {
                "studentnum": studentnum,
                "type": "GetAllCars",
                "apikey" : "a9198b68355f78830054c31a39916b7f",
                "search": {
                    "make" : brand,
                    "body_type" : body_type,
                    "transmission" : transmission,
                    "drive_wheels" : drive_type,
                    "engine_type" : fuel_type
                },
                "sort" : "max_speed_km_per_h",
                "order" : sort,
                "return" : "*"
            }
        ));
    }
    
    xhttp.onload = function() {
        if (this.readyState == 4 && this.status == 200) {
            var response = JSON.parse(this.responseText);
            console.log(response);
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
                html += "<img id=carIMG" + i + " alt='Image of car' class='carImage' src='"+ car.image+ "'/>"
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