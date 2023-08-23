<!--James Cooks u21654680-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PA3</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/cars.css">
    <link rel="stylesheet" href="css/FindCar.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
    <script src="js/loader.js"></script>
</head>
<body>
    <?php include 'php/header.php'; ?>
    <h1>Find a Car for you</h1>
    <div id="main">
        <form>
            <h2>Answer some quick questions and help us find cars fit for you</h2>
            <h4>&#10088;* means its a required field&#10089;</h4>
            <div class="questions">
                <div class="question">
                    <h3>What brand do you like most?</h3>
                    <input type="text" id="brand" placeholder="Enter brand name" required="required" value="">
                </div>
                <div class="question">
                    <h3>What type of car are you looking for?*</h3>
                    <select required="required" id="body_type">
                        <option value="" disabled selected>Select your option</option>
                        <option value="Sedan">Sedan</option>
                        <option value="Hatchback">Hatchback</option>
                        <option value="SUV">SUV</option>
                        <option value="Coupe">Coupe</option>
                        <option value="Convertible">Convertible</option>
                        <option value="Wagon">Wagon</option>
                        <option value="Van">Van</option>
                        <option value="Crossover">Crossover</option>
                    </select>
                </div>
                <div class="question">
                    <h3>What type of Transmission do you like?*</h3>
                    <select required="required" id="transmission">
                        <option value="" disabled selected>Select your option</option>
                        <option value="Manual">Manual</option>
                        <option value="Automatic">Automatic</option>
                    </select>
                </div>
                <div class="question">
                    <h3>What drive type do you prefer?*</h3>
                    <select required="required" id="drive_type">
                        <option value="" disabled selected>Select your option</option>
                        <option value="Front wheel drive">Front Wheel Drive</option>
                        <option value="Rear wheel drive">Rear Wheel Drive</option>
                        <option value="All wheel drive (AWD)">All Wheel Drive</option>
                    </select>
                </div>
                <div class="question">
                    <h3>Speed</h3>
                    <input type="range" id="speed" name="speed" min="1" max="3" value="1" step="1">
                </div>
                <div class="question">
                    <h3>Which type of fuel do you prefer to use?</h3>
                    <select required="required" id="fuel_type">
                        <option value="" disabled selected>Select your option</option>
                        <option value="Gasoline">Gasoline</option>
                        <option value="Diesel">Diesel</option>
                        <option value="Electric">Electric</option>
                        <option value="Hybrid">Hybrid</option>
                    </select>
                </div>
                <input type="button" value="Find a car for me" id="submit"/>
            </div>           
            

            

        </form>
    </div>

    <h1>Results</h1>
    <div class="cars">
        
    </div>
    <?php include 'php/footer.php'; ?>
    <script src="js/FindCar.js"></script>
    <script src="js/theme.js"></script>
</body>
</html>