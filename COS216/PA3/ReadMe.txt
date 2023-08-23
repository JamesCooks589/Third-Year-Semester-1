Welcome to StreamLine

How to use StreamLine

1. Open the homepage of StreamLine at https://wheatley.cs.up.ac.za/u21654680/COS216/index.html
2. Click on the "Sign Up" button to create an account
3. Enter your details and click on the "Sign Up" button
4. After that feel free to explore all the features of StreamLine

Default API key for prac 3 : a9198b68355f78830054c31a39916b7f

Functionality missing:

-return can't include the word "image" although image is returned when return type is "*" I tried ways of fixing this but couldn't find a solution yet

Explanation of password requirements:

-Password must be at least 8 characters long as longer passwords are more secure against brute force attacks
-Passwords must have at least one uppercase letter, one lowercase letter, one number and one special character as this makes it harder for attackers to guess the password
-Salt was generated using some of the users details

Choice of hashing algorithm:

I chose sha256 as it is very commonly used in industry because it a very good all rounded hashing algorithm. Not the fastest or most secure but it is a good balance between the two.
There also is no known way to reverse sha256 hashes.
And sha256 is better than md5 seeing as the output is longer thus reducing the chance of a collision.
