<!--James Cooks u21654680-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PA3</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/compare.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
    <script src="js/loader.js"></script>
</head>
<body>
    <?php include 'php/header.php'; ?>
    <h1>Compare 2 cars of your choice</h1>
    <div class="cars">
        <div class="car">
                <div class="selecter">
                    <h1>Select Car 1</h1>
                    <h3>Select car brand</h3>
                    <select name="car1Make" id="car1Make">
                        <option value="" selected disabled hidden>Choose here</option>
                    </select>
                    <h3>Select car model</h3>
                    <select name="car1Model" id="car1Model">
                        <option value="" selected disabled hidden>Choose here</option>
                    </select>
                </div>
                <h1 id="car1Title" class="carTitle"></h1>
            <div id="car1IMGDiv">
            </div>
            <table>
                <tr>
                    <th>Year</th>
                    <th>Cylinders</th>
                    <th>Top Speed</th>
                    <th>Drive train</th>
                </tr>
                <tr>
                    <td id="year1Compare"></td>
                    <td id="cylinder1Compare"></td>
                    <td id="speed1Compare"></td>
                    <td id="driveTrain1Compare"></td>
                </tr>
            </table> 
        </div>
        <div class="car">
            <div class="selecter">
                <h1>Select Car 2</h1>
                <h3>Select car brand</h3>
                <select name="car2Make" id="car2Make">
                    <option value="" selected disabled hidden>Choose here</option>
                </select>
                <h3>Select car model</h3>
                <select name="car2Model" id="car2Model">
                    <option value="" selected disabled hidden>Choose here</option>
                </select>
            </div>
            <h1 id="car2Title" class="carTitle"></h1>
        <div id="car2IMGDiv">
        </div>
        <table>
            <tr>
                <th>Year</th>
                <th>Cylinders</th>
                <th>Top Speed</th>
                <th>Drive train</th>
            </tr>
            <tr>
                <td id="year2Compare"></td>
                <td id="cylinder2Compare"></td>
                <td id="speed2Compare"></td>
                <td id="driveTrain2Compare"></td>
            </tr>
        </table> 
    </div>
        <div class="car" id="car3">
            <h2>Add a third option</h2>
        </div>
    </div>
    <?php include 'php/footer.php'; ?>
    <script src="js/compare.js"></script>
</body>
</html>