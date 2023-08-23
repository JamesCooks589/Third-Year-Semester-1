<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rating</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/cars.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
    <script src="js/loader.js"></script>
</head>
<body>
    <?php include 'php/header.php'; ?>
    <h1>Rate Cars</h1>

    <div class="cars">

    </div>
    <div id="AvgRating">
        <h2>Average Rating for this car</h2>
        <h3 id="AvgRatingValue"></h3>
    </div>
    <div id="LikeDislike">    
        <div id="Like">
            <h3>Like</h3>
        </div>
        <div id="Dislike">
            <h3>Dislike</h3>
        </div>
    </div>

    <script src="js/rate.js"></script>
    
</body>
</html>