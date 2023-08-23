//James Cooks u21654680

var xhttp = new XMLHttpRequest();
var studentnum = "u21654680";
var apiKey = "a9198b68355f78830054c31a39916b7f";
var html = "";
var limit = 0;

xhttp.open("POST", "../api.php", true);
xhttp.send(JSON.stringify(
    {
        "studentnum":"u12345678",
        "type":"GetAllMakes",
        "apikey": apiKey,
        "return": ["make", "model"]
    }
));

xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        var result = JSON.parse(this.responseText);
        limit = result.data.length;
        for (var i = 0; i < limit; i++) {
            html += "<div class='brand'>";
            html += "<h2>" + result.data[i].make + "</h2>";
            html += "<img id=brandIMG" + i + " alt='brand image' class='brandIMG'/>"
            html += "</div>";
            loadIMg(result.data[i].make, i);
        }
        $("#brands").append(html);
    }
}


function loadIMg(brand, i) {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        if (this.readyState == 4 && this.status == 200) {
            var result = this.responseText;
            var img = document.getElementById("brandIMG" + i);
            img.setAttribute("src", result);
        }

    }
    xhttp.open("GET", "https://wheatley.cs.up.ac.za/api/getimage?brand=" + brand,true);
    xhttp.send();
}
