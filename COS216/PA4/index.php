<!--James Cooks u21654680-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PA4</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/cars.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
    <script src="js/loader.js"></script>
</head>
<body>    

    <div class="SearchBar">
        <input type="text" id="searchMake" placeholder="Brand (Required)" value=""/>
        <input type="text" id="searchModel" placeholder="Model (optional)" value=""/>
        <div id="search">Search</div>
    </div>
    <?php include 'php/header.php'; ?>
    <h1>Quick Filters & Sort</h1>
    <div class="QuickFilters">
        <div class="TransmissionType">
            <h2>Transmission Type</h2>
            <input type="radio" id="Automatic" name="Transmission" value="Automatic" class="Transmission">
            <label for="Automatic">Automatic</label>
            <input type="radio" id="Manual" name="Transmission" value="Manual" class="Transmission">
            <label for="Manual">Manual</label><br>
        </div>
        <div class="FuelType">
            <h2>Fuel Type</h2>
            <input type="radio" id="Petrol" name="Fuel" value="Gasoline" class="Fuel">
            <label for="Petrol">Gasoline</label>
            <input type="radio" id="Diesel" name="Fuel" value="Diesel" class="Fuel">
            <label for="Diesel">Diesel</label>
        </div>
        <div class="Sort">
            <h2>Sort</h2>
            <select name="Sort" id="Sort">
                <option value="" selected disabled hidden>Sort By</option>
                <option value="NameAZ">Name &#10088;A - Z&#10089;</option>
                <option value="NameZA">Name &#10088;Z - A&#10089;</option>
                <option value="YearNew">Year &#10088;New to Old&#10089;</option>
                <option value="YearOld">Year &#10088;Old to New&#10089;</option>
            </select>       
        </div>    
    </div>
    <div id="FnS">
        <div id="ApplyFilters">
            <h2>Apply Filters</h2>
        </div>
        <div id="SortBtn">
            <h2>Sort</h2>
        </div>
    </div>
    <h1>Our Cars</h1>
    <div class="cars">
        
    </div>

<?php include 'php/footer.php'; ?>
<script src="js/cars.js"></script>
</body>
</html>