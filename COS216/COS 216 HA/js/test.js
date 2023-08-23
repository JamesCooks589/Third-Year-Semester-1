var xhttp = new XMLHttpRequest();

//get request
xhttp.open("GET", "api.php/GetRandomBrands", true);
xhttp.send();

//Handle response
xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        var response = JSON.parse(this.responseText);
        console.log(response);
    }
}
