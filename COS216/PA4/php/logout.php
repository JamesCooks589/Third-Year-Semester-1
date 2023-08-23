<?php
    //James Cooks u21654680
    session_start();
    //Clear cookies
    setcookie('api_key', "", time() - 3600, "/");
    setcookie('transmission', "", time() - 3600, "/");
    setcookie('fuel', "", time() - 3600, "/");
    setcookie('prefrence', "", time() - 3600, "/");
    //Clear session
    session_destroy();

    //Set cookies back to default
    setcookie('api_key', "a9198b68355f78830054c31a39916b7f", time() + (86400 * 30), "/");
    setcookie('transmission', "none", time() + (86400 * 30), "/");
    setcookie('fuel', "none", time() + (86400 * 30), "/");
    setcookie('prefrence', "dark", time() + (86400 * 30), "/");
    //redirect to index.php

    header("Location: ../index.php");
?>