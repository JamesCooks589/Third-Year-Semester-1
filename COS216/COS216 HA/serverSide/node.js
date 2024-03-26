const express = require('express');
const bodyParser = require('body-parser');
const cookieParser = require('cookie-parser');
const { v1: uuidv1, v4: uuidv4 } = require('uuid');
const ajax = require('ajax');
const e = require('express');

const app = express();
const http = require('http').Server(app);
const https = require('https');
const io = require('socket.io')(http);

app.use(cookieParser());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());


//let user input a port number
let port = process.argv.slice(2);


//check if the port number is valid
if (port < 1024 || port > 49151) {
  console.log("Please enter a valid port number between 1024 and 49151: ");
  process.exit(1);
}


//Set up the server
http.listen(port.toLocaleString(), function() {
    console.log("Server is listening on port " + port);
});

app.get("/", (req, res) => {
  res.cookie(`uuid`,uuidv4(), {httpOnly: false, sameSite: 'none', secure: true});
  res.cookie(`port_number`, port.toLocaleString(), {httpOnly: false, sameSite: 'none', secure: true});
  res.sendFile(directory, "clientSide/index.html");
});

app.get("/gameSetUp.html", (req, res) => {
  //Redirect the user to the gameSetUp.html page
  res.sendFile(directory, "clientSide/gameSetUp.html");

});

//set directiory root relative to server.js file
let directory = __dirname;
console.log(directory);
directory = directory.replace("serverSide", "");

//make static library for loading od client assets and files
app.use(express.static(directory + 'clientSide'));

let users = [];
let userIDs = [];
let games = [];
let msg = "Opponent has disconnected";
 

//checkGameID
app.post("/checkGameID", (req, res) => {
  let gameID = req.body.gameID;
  let gameExists = false;
  for(let i = 0; i < games.length; i++){
    //If the gameID exists and there are less than 2 players in the game
    if(games[i].id == gameID && games[i].players.length < 2){
      gameExists = true;
      break;
    }
  }
  res.send(gameExists);
});


app.post("/checkUsername", (req, res) => {
  let username = req.body.username;
  let usernameExists = false;
  for(let i = 0; i < users.length; i++){
    if(users[i] == username){
      usernameExists = true;
      break;
    }
  }
  res.send(usernameExists);
});

io.sockets.on('connection', function(socket) {
  socket.on('newUser', function(id, name) {
    socket.user = id;
    users.push(name);
    userIDs.push(socket.id);
  });

  //if a user disconnects, remove them from the list of users
  socket.on('disconnect', function () {
    //search for user in active games and remove them, max 2 players per game, notify other player
    for(let i = 0; i < games.length; i++){
      for(let j = 0; j < games[i].players.length; j++){
        if(games[i].players[j] == socket.user){
          if(games[i].players.length == 2){
            if(j == 0){
              io.to(userIDs[i+1]).emit('notifyUser', msg);
            }
            else{
              io.to(userIDs[i]).emit('notifyUser', msg);
            }
          }
          //Remove the game from the list of games
          games.splice(i, 1);
          break;
        }
      }
    }

    
    //remove user from list of users
    for(var i = 0; i < users.length; i++) {
        if(users[i] == socket.user) {
                
            users.splice(i, 1);
            userIDs.splice(i, 1);
            break;
        }
    }
  });

  socket.on('newGame', function(gameID, player) {
    let game = {
      id: gameID,
      players: [player]
    };
    games.push(game);

    //Send the gameID to the client
    socket.emit('gameID', gameID);
  
  }
  );

  socket.on('joinGame', function(gameID, player) {
    console.log("Joining game " + gameID);
    for(let i = 0; i < games.length; i++){
      if(games[i].id == gameID){
        games[i].players.push(player);
        break;
      }
    }
  });

  
 
});


//Set up commands

process.stdin.resume();
process.stdin.setEncoding('utf8');

process.stdin.on('data', function (text) {
  let command = text.trim();

    //if LIST command, display a list of connected users by displaying their ID
    if (command === 'LIST') {
        console.log(users.length+" users currently connected");
        for(let i = 0; i < users.length; i++){
            console.log(users[i] + " : " + userIDs[i]);
        }
    }

        //Kill command - remove a user from the list of users and disconnect them from the server, command will be entered as KILL <user ID>
        if(command.startsWith('KILL'))
        {
            let userID = command.substring(5);
            let userExists = false;
            for(let i = 0; i < userIDs.length; i++){
                if(userIDs[i] == userID){
                    userExists = true;
                    break;
                }
            }
            if(userExists){
                console.log("User " + userID + " has been disconnected");
                io.to(userID).emit('notifyUser', msg);
                io.sockets.sockets.forEach((socket) => {
                    if(socket.id == userID){
                        socket.disconnect(true);
                    }
                });
                for(let i = 0; i < users.length; i++){
                    if(userIDs[i] == userID){
                        users.splice(i, 1);
                        userIDs.splice(i, 1);
                        break;
                    }
                }
            }
            else{
                console.log("User " + userID + " does not exist");
            }
        }
        

      //quit command

      if(command === 'QUIT'){
        console.log("Server has been terminated");
        
        io.sockets.emit('quit');

        io.sockets.sockets.forEach((socket) => {
          socket.disconnect(true);
        });

        users = [];
        userIDs = [];
        games = [];
        process.exit(1);
      }

      //GAMES command, list all games and their players
      if(command === 'GAMES'){
        if(games.length > 0){
        console.log("Current games: ");
        for(let i = 0; i < games.length; i++){
          console.log("Game " + i + ": With ID - " + games[i].id + " : " + games[i].players[0] + " vs " + games[i].players[1]);
        }
      }
      else{
        console.log("No games currently active");
      }
      }

  
});

// //Wheatlet request
// var host = 'https://wheatley.cs.up.ac.za';
// var path = '/u21654680/Homework_Assignment/api.php/GetRandomBrands';
// const username = 'username';
// const password = 'password';
// const auth = 'Basic ' + Buffer.from(username + ':' + password).toString('base64');

// const options = {
//   hostname: host,
//   path: path,
//   method : 'GET',
//   headers : {
//     'Content-Type': 'application/json',
//     'Authorization' : auth
//   }
// };

// const req = https.request(options, res => {
//   console.log(`statusCode: ${res.statusCode}`);

//   let cars = [];

//   res.on('data', d => {
//     process.stdout.write(d);
//     cars.push(d);
//   }
//   );

//   res.on('end', () => {
//     console.log(cars);
//   }
//   );
// });




