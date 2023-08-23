//James Cooks u21654680

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

theme = getCookie("prefrence");

if (theme == "light") {
    $("body").css("background-color", "#eaeaea");
}
else {
    $("body").css("background-color", "rgb(26, 26, 26)");
}