<!--James Cooks u21654680-->

<?php include '../config.php'; ?>

<!--Loader-->
<?php echo '<div class="loader"><img src="https://cdn.dribbble.com/users/492711/screenshots/2528440/media/e831c923cb7e821b137c60055378ca4d.gif" alt="loading gif"/></div>'; ?>
<!--Header-->
<?php echo '<a href="../index.html"><img src="img/Logo.png" id="Logo" alt="Logo"/></a>'; ?>
<nav class="NavBar">
        <?php
        echo '<a href="php/login.php">Login</a>';
        echo '<a href="php/signup.php">Sign Up</a>';
        if($_SERVER['SCRIPT_NAME']=="https://wheatley.cs.up.ac.za/u21654680/COS216/PA3/index.php") 
        { echo '<a href="index.php" class = "active" >Cars</a>'; }
        else 
        echo '<a href="index.php">Cars</a>';
        
         if($_SERVER['SCRIPT_NAME']=="https://wheatley.cs.up.ac.za/u21654680/COS216/PA3/Brands.php") 
        { echo '<a href="Brands.php" class = "active" >Brands</a>'; }
        else
        echo '<a href="Brands.php">Brands</a>';
        
         if($_SERVER['SCRIPT_NAME']=="https://wheatley.cs.up.ac.za/u21654680/COS216/PA3/FindCar.php") 
        { echo '<a href="FindCar.php" class = "active" >Find a Car</a>'; }
        else
        echo '<a href="FindCar.php">Find a Car</a>';
        if($_SERVER['SCRIPT_NAME']=="https://wheatley.cs.up.ac.za/u21654680/COS216/PA3/Compare.php") 
        { echo '<a href="Compare.php" class = "active" >Compare</a>'; }
        else
        echo '<a href="Compare.php">Compare</a>';
        ?>
</nav>
