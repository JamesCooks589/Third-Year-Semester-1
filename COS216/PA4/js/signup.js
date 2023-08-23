const lengthCheck = /.{8,}/;
const upperCaseCheck = /[A-Z]/;
const lowerCaseCheck = /[a-z]/;
const numberCheck = /[0-9]/;
const specialCharCheck = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/;
const emailCheck = /(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/;



document.getElementById("submitButton").onclick = function() {{
   var Username = document.getElementById("name").value;
   var surname = document.getElementById("surname").value;
   var email = document.getElementById("email").value;
   var pass = document.getElementById("password").value;
   var error = document.getElementById("error");
   var flag = true;  
   if (Username == "") {
        //clear error message
        error.innerHTML = "";
        //add error message
        error.innerHTML = "Please enter a name";
        document.getElementById("name").focus();
        flag = false;
        return flag;
    }
    if (surname == "") {
        error.innerHTML = "";
        error.innerHTML = "Please enter a surname";
        document.getElementById("surname").focus();
        flag = false;
        return flag;
    }
    if (emailCheck.test(email) == false) {
        error.innerHTML = "";
        error.innerHTML = "Please enter a valid email";
        document.getElementById("email").focus();
        flag = false;
        return flag;
    }
    if (pass == "") {
        error.innerHTML = "";
        error.innerHTML = "Please enter a password";
        document.getElementById("password").focus();
        flag = false;
        return flag;
    }
    if (lengthCheck.test(pass) == false) {
        error.innerHTML = "";
        error.innerHTML = "Password must be at least 8 characters long";
        document.getElementById("password").focus();
        flag = false;
        return flag;
    }
    if (upperCaseCheck.test(pass) == false) {
        error.innerHTML = "";
        error.innerHTML = "Password must contain at least one uppercase letter";
        document.getElementById("password").focus();
        flag = false;
        return flag;
    }
    if (lowerCaseCheck.test(pass) == false) {
        error.innerHTML = "";
        error.innerHTML = "Password must contain at least one lowercase letter";
        document.getElementById("password").focus();
        flag = false;
        return flag;
    }
    if (numberCheck.test(pass) == false) {
        error.innerHTML = "";
        error.innerHTML = "Password must contain at least one number";
        document.getElementById("password").focus();
        flag = false;
        return flag;
    }
    if (specialCharCheck.test(pass) == false) {
        error.innerHTML = "";
        error.innerHTML = "Password must contain at least one special character";
        document.getElementById("password").focus();
        flag = false;
        return flag;
    }


    if (flag == true) {
        //ajax call to validate-signup.php
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "validate-signup.php", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send("name=" + Username + "&surname=" + surname + "&email=" + email + "&password=" + pass);
        xhr.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                if (this.responseText == "success") {
                    alert("You have successfully signed up!");
                } else {
                    error.innerHTML = this.responseText;
                }
            }
        };
    }
}};



