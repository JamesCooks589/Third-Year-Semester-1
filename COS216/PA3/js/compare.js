//James Cooks u21654680

var car1Make = document.getElementById("car1Make");
var car1Model = document.getElementById("car1Model");
var html = "";
var studentnum = "u21654680";
var apiKey = "a9198b68355f78830054c31a39916b7f";
var maxOptions = 2;

//empty car1Model when you click on car1Make
car1Make.addEventListener("click", function() {
    $("#car1Model").empty();
});

var xhttp = new XMLHttpRequest();
xhttp.open("POST", "../api.php", true);
xhttp.send(JSON.stringify(
    {
        "studentnum": studentnum,
        "type":"GetAllMakes",
        "apikey": apiKey,
        return : ["make", "model"]
    }
));

xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        var result = JSON.parse(this.responseText);
        for (var i = 0; i < result.data.length; i++) {
            html += "<option value='" + result.data[i].make + "'>" + result.data[i].make + "</option>";
        }
        $("#car1Make").append(html);
    }
}


//On change of car1Make
car1Make.addEventListener("change", function() {
    var xhttp = new XMLHttpRequest();
    html = "";
    xhttp.open("POST", "../api.php", true);
    xhttp.send(JSON.stringify(
        {
            "studentnum": studentnum,
            "type": "GetAllCars",
            "limit": 50,
            "apikey": apiKey,
            "search": {
                "make": this.value
            },
            "return": ["model", "make"]
        }
    ));

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {

            var result = JSON.parse(this.responseText);
            console.log(result);
            html += "<option value=''>Select Model</option>";
            for (var i = 0; i < result.data.length; i++) {
                html += "<option value='" + result.data[i].model + "'>" + result.data[i].model + "</option>";
            }
            $("#car1Model").append(html);
        }
    }

});

//On change of car1Model
car1Model.addEventListener("change", function() {
    $("#car1IMGDiv").empty();
    $("#car1IMGDiv").append("<img id='car1Img' alt='car image' class='carIMG'/>");
    loadImg(car1Make.value, this.value,1);
    $("#car1Title").text(car1Make.value + " " + this.value);
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "../api.php", true);
    xhttp.send(JSON.stringify(
        {
            "studentnum": studentnum,
            "type": "GetAllCars",
            "apikey": apiKey,
            "search": {
                "make": car1Make.value,
                "model": this.value
                },
            "return": "*"
        }
    ));

    xhttp.onreadystatechange = function() {
       if (this.readyState == 4 && this.status == 200) {
        var result = JSON.parse(this.responseText);
        $("#year1Compare").html(result.data[0].year_from);
        $("#cylinder1Compare").html(result.data[0].number_of_cylinders);
        $("#speed1Compare").html(result.data[0].max_speed_km_per_h);
        $("#driveTrain1Compare").html(result.data[0].drive_wheels);
       } 
    }
    
});

function loadImg(brand, model, i) {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        if (this.readyState == 4 && this.status == 200) {
            var result = this.responseText;
            var img = document.getElementById("car" + i +"Img");
            img.setAttribute("src", result);
        }

    }
    xhttp.open("GET", "https://wheatley.cs.up.ac.za/api/getimage?brand=" + brand + "&model=" + model,true);
    xhttp.send();
}

//car 2
var car2Make = document.getElementById("car2Make");
var car2Model = document.getElementById("car2Model");
var html = "";
var studentnum = "u21654680";

//empty car2Model when you click on car2Make
car2Make.addEventListener("click", function() {
    $("#car2Model").empty();
}
);

var xhttp = new XMLHttpRequest();
xhttp.open("POST", "../api.php", true);
xhttp.send(JSON.stringify(
    {
        "studentnum": studentnum,
        "type":"GetAllMakes",
        "apikey": apiKey,
        return : ["make", "model"]
    }
));

xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        var result = JSON.parse(this.responseText);
        for (var i = 0; i < result.data.length; i++) {
            html += "<option value='" + result.data[i].make + "'>" + result.data[i].make + "</option>";
        }
        $("#car2Make").append(html);
    }
}


//On change of car2Make
car2Make.addEventListener("change", function() {
    var xhttp = new XMLHttpRequest();
    html = "";
    xhttp.open("POST", "../api.php", true);
    xhttp.send(JSON.stringify(
        {
            "studentnum": studentnum,
            "type": "GetAllCars",
            "limit": 50,
            "apikey": apiKey,
            "search": {
                "make": this.value
            },
            "return": ["model", "make"]
        }
    ));

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var result = JSON.parse(this.responseText);
            html += "<option value=''>Select Model</option>";
            for (var i = 0; i < result.data.length; i++) {
                html += "<option value='" + result.data[i].model + "'>" + result.data[i].model + "</option>";
            }
            $("#car2Model").append(html);
        }
    }

});

//On change of car2Model
car2Model.addEventListener("change", function() {
    $("#car2IMGDiv").empty();
    $("#car2IMGDiv").append("<img id='car2Img' alt='car image' class='carIMG'/>");
    loadImg(car2Make.value, this.value,2);
    $("#car2Title").text(car1Make.value + " " + this.value);
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "../api.php", true);
    xhttp.send(JSON.stringify(
        {
            "studentnum": studentnum,
            "type": "GetAllCars",
            "apikey": apiKey,
            "search": {
                "make": car2Make.value,
                "model": this.value
                },
            "return": "*"
        }
    ));

    xhttp.onreadystatechange = function() {
       if (this.readyState == 4 && this.status == 200) {
        var result = JSON.parse(this.responseText);
        $("#year2Compare").html(result.data[0].year_from);
        $("#cylinder2Compare").html(result.data[0].number_of_cylinders);
        $("#speed2Compare").html(result.data[0].max_speed_km_per_h);
        $("#driveTrain2Compare").html(result.data[0].drive_wheels);
       } 
    }
    
});


//Car 3

document.getElementById("car3").addEventListener("click", function() {
    html = "";
    $("#car3").empty();
    html += "<div class='selecter'>";
    html += "<h1>Select Car 3</h1>";
    html += "<h3>Select car brand</h3>";
    html += "<select name='car3Make' id='car3Make'>";
    html += "<option value='' selected disabled hidden>Choose here</option>";
    html += "</select>";
    html += "<h3>Select car model</h3>";
    html += "<select name='car3Model' id='car3Model'>";
    html += "<option value='' selected disabled hidden>Choose here</option>";
    html += "</select>";
    html += "</div>";
    html += "<h1 id='car3Title' class='carTitle'></h1>";
    html += "<div id='car3IMGDiv'>";
    html += "</div>";
    html += "<table>";
    html += "<tr>";
    html += "<th>Year</th>";
    html += "<th>Cylinders</th>";
    html += "<th>Top Speed</th>";
    html += "<th>Drive train</th>";
    html += "</tr>";
    html += "<tr>";
    html += "<td id='year3Compare'></td>";
    html += "<td id='cylinder3Compare'></td>";
    html += "<td id='speed3Compare'></td>";
    html += "<td id='driveTrain3Compare'></td>";
    html += "</tr>";
    html += "</table>";
    $("#car3").append(html);

    var car3Make = document.getElementById("car3Make");
    var car3Model = document.getElementById("car3Model");
    var html = "";
    var studentnum = "u21654680";

    //empty car3Model when you click on car3Make
    car3Make.addEventListener("click", function() {
        $("#car3Model").empty();
    }
    );

    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "../api.php", true);
    xhttp.send(JSON.stringify(
        {
            "studentnum": studentnum,
            "type":"GetAllMakes",
            "apikey": apiKey,
            return : ["make", "model"]
        }
    ));

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var result = JSON.parse(this.responseText);
            for (var i = 0; i < result.data.length; i++) {
                html += "<option value='" + result.data[i].make + "'>" + result.data[i].make + "</option>";
            }
            $("#car3Make").append(html);
        }
    }


    //On change of car3Make
    car3Make.addEventListener("change", function() {
        var xhttp = new XMLHttpRequest();
        html = "";
        xhttp.open("POST", "../api.php", true);
        xhttp.send(JSON.stringify(
            {
                "studentnum": studentnum,
                "type": "GetAllCars",
                "limit": 50,
                "apikey": apiKey,
                "search": {
                    "make": this.value
                    },
                "return": ["model", "make"]
            }
        ));

        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                var result = JSON.parse(this.responseText);
                html += "<option value=''>Select Model</option>";
                for (var i = 0; i < result.data.length; i++) {
                    html += "<option value='" + result.data[i].model + "'>" + result.data[i].model + "</option>";
                }
                $("#car3Model").append(html);

            }
        }
    });

    //On change of car3Model

    car3Model.addEventListener("change", function() {
        $("#car3IMGDiv").empty();
        $("#car3IMGDiv").append("<img id='car3Img' alt='car image' class='carIMG'/>");
        loadImg(car3Make.value, this.value,3);
        $("#car3Title").text(car3Make.value + " " + this.value);
        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", "../api.php", true);
        xhttp.send(JSON.stringify(
            {
                "studentnum": studentnum,
                "type": "GetAllCars",
                "apikey": apiKey,
                "search": {
                    "make": car3Make.value,
                    "model": this.value
                    },
                "return": "*"
            }
        ));

        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                var result = JSON.parse(this.responseText);
                $("#year3Compare").html(result.data[0].year_from);
                $("#cylinder3Compare").html(result.data[0].number_of_cylinders);
                $("#speed3Compare").html(result.data[0].max_speed_km_per_h);
                $("#driveTrain3Compare").html(result.data[0].drive_wheels);
            }
        }
    });



    document.getElementById("car3").removeEventListener("click", arguments.callee);
});






