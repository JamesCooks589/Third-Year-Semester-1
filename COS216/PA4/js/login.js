//James Cooks u21654680

document.getElementById("BtnSubmit").addEventListener("click", function() {
    var email = document.getElementById("email").value;
    var pass = document.getElementById("pass").value;
    var error = document.getElementById("error");
    var flag = true;
    if (email == "") {
        error.innerHTML = "";
        error.innerHTML = "Please enter an email";
        document.getElementById("email").focus();
        return false;
    }

    if (pass == "") {
        error.innerHTML = "";
        error.innerHTML = "Please enter a password";
        document.getElementById("pass").focus();
        return false;
    }

    if (flag == true) {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "validate-login.php", true);
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.send("email=" + email + "&password=" + pass);
        xhr.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                console.log(this.responseText);
                if (this.responseText == "success") {
                    window.location.href = "../index.php";
                } else {
                    error.innerHTML = this.responseText;
                }
            }
        }
    }

    
});