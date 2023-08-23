//James Cooks u21654680

document.getElementById("save").addEventListener("click", function() {
    var theme = document.getElementById("theme").value;
    var transmission = document.getElementById("transmission").value;
    var fuel = document.getElementById("fuel").value;
    var display = document.getElementById("error");

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

    //Send data to api.php as json
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "../../api.php", true);
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(JSON.stringify({
        "theme": theme,
        "transmission": transmission,
        "fuel": fuel,
        "type" : "Update",
        "apikey" : getCookie("api_key"),
        "return" : "*"
    }));
    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            JSON.parse(this.responseText);
            display.innerHTML = "Settings Saved";
        }
    }

    //Update cookies
    function setCookie(name, value, days) {
        var expires = "";
        if (days) {
            var date = new Date();
            date.setTime(date.getTime() + (days * 24 * 60 * 60 *1000));
            expires = "; expires=" + date.toUTCString();
        }
        document.cookie = name + "=" + (value || "") + expires + "; path=/";
    }

    setCookie("prefrence", theme, 1);
    setCookie("transmission", transmission, 1);
    setCookie("fuel", fuel, 1);

    
});