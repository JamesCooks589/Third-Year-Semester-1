const port = ('; '+document.cookie).split(`; port_number=`).pop().split(';')[0];
const uuid = ('; '+document.cookie).split(`; uuid=`).pop().split(';')[0];
const socket = io('http://localhost:'+port);

var Gname = "";

//If the user is on the home page add the event listener to the button
if(window.location.pathname == "/"){

document.getElementById("checkUsername").addEventListener("click", function(event) {
    var name = document.getElementById("username").value;
    $.ajax({
        url: "http://localhost:"+port+"/checkUsername",
        type: "POST",
        contentType: 'application/json',
        data: JSON.stringify({
            username: name
        }),
        success: function(data){
            if(!data){
                setCookie("username", name);
                socket.emit('newUser', uuid, name);
                window.location.href = "/gameSetUp.html";
            }
            else{
                document.getElementById("username").value = "";
                document.getElementById("username").focus();
                document.getElementById("usernameErr").innerHTML = "Username already exists";
            }
        }
    });
});
}

//If the user is on the game page add the event listeners to the buttons
if(window.location.pathname == "/gameSetUp.html"){
    //Send the gameID to the client
    document.getElementById("generateGameCode").addEventListener("click", function(event) {
        console.log("Generating game code");
        //Create a game
        username = getCookie("username");
        console.log(username);
        socket.emit('newGame', uuid, username);
        //Remove the button
        document.getElementById("generateGameCode").remove();
        document.getElementById("join").remove();
    });

    document.getElementById("joinGame").addEventListener("click", function(event) {
        console.log("Joining game");
        var gameCode = document.getElementById("gameCode").value;
        $.ajax({
            url: "http://localhost:"+port+"/checkGameID",
            type: "POST",
            contentType: 'application/json',
            data: JSON.stringify({
                gameID: gameCode
            }),
            success: function(data){
                if(data){
                    socket.emit('joinGame', gameCode, getCookie("username"));
                }
                else{
                    console.log("Game does not exist");
                }
            }
        });
    });

    socket.on("gameID", function(gameID){
        document.getElementById("gameID").innerHTML = "Your game code is: " +  gameID;
    });
    
}



socket.on('notifyUser', function(message) {
    console.log(message);
});

function setCookie(cname, cvalue) {
    document.cookie = cname + "=" + cvalue + ";path=/";
}

function getCookie(cname){
    //Return the cookie with the passed in name
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var cookieArray = decodedCookie.split(';');
    for(var i = 0; i < cookieArray.length; i++) {
        var cookie = cookieArray[i];
        while (cookie.charAt(0) == ' ') {
        cookie = cookie.substring(1);
        }
        if (cookie.indexOf(name) == 0) {
        return cookie.substring(name.length, cookie.length);
        }
    }
    return "";
}