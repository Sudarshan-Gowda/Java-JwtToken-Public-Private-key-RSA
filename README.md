# Java JwtToken Public Private key RSA

<img src="https://github.com/Sudarshan-Gowda/Java-JwtToken-Public-Private-key-RSA/blob/master/docs/Project-Flow.gif"/>


## Topics Covered
* Generate public/private key pair using RSA and store it into file.
* Generate JWT with the genearted private key
* Claim the respose with the public key and jwt id


## In case you find a bug/suggested improvement for Spring Restfull Webservices
Our issue tracker is available here: https://github.com/Sudarshan-Gowda/Java-JwtToken-Public-Private-key-RSA/issues


## Working with Rest in Eclipse

### prerequisites
The following items should be installed in your system:
* Tool - STS(Spring Toot Suite) or Eclipse

### Steps:

1) Download this Project and do maven import.
```
git clone https://github.com/Sudarshan-Gowda/Java-JwtToken-Public-Private-key-RSA.git
```
2) To Import the Praject Using STS or Eclipse.
```
File -> Import -> Maven -> Existing Maven project
```


## Looking for something in particular?

|Spring Boot Configuration | Class or Java property files  |
|--------------------------|---|
|The Main Class | [JwtTokenwithRsa](https://github.com/Sudarshan-Gowda/Java-JwtToken-Public-Private-key-RSA/blob/master/src/main/java/com/star/sud/JwtTokenwithRsa.java) |


## Steps to test the application:

1) Once the application is installed properly, Run as Java Application.
2) Once the application successfully started means it will do the following operation: 
   <ul>
	 <li>generate public/private key using RSA</li>
	 <li>encode and store it to file with the specified location in the top of the file, this stored public key file can be shared to your clients.</li>
	 <li>generate jwt token using private key with expiry time and share the generated jwt to the clients.</li>
	 <li>you can also claim the token with the public key and verify it.</li>	
   </ul>
3) You can verify the result which will be printed in the console.
   <img src="https://github.com/Sudarshan-Gowda/Java-JwtToken-Public-Private-key-RSA/blob/master/docs/Pic1.PNG"/>
   

# Contributing

The [issue tracker](https://github.com/Sudarshan-Gowda/Java-JwtToken-Public-Private-key-RSA/issues) is the preferred channel for bug reports, features requests and submitting pull requests.

