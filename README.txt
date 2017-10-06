To whom it may concern.
This is the final hand in By Ednecia Sewagodimo Matlapeng(MTLEDN001)
For the 2017 UCT Honors project CHEFREG

For this project I built a mobile application and a webserver.
The mobile application was developed in android studio
It java's mailing service to send Emails to the school
It also uses android SMS to send emergency texts to the school.
It then connects to the django API to update user information

The application connects to a django API that I have designed to allow users to register, login and change their personal and professional details.
The API is in a zipped folders called chefproject(I zipped it save space)
You can check my git logs to how I progressed while developing the application
The git files for the android project are in the "main" folder, the git files in the project level folder are not detailed enough
For the server, the git files are in the project.

*************************************************
THE MOBILE APPLICATION
*************************************************
The mobile application  connects to the server using Volley
It sends GET and POST requests to the server
The application is made up of two activities
The LoginActivity and the MainAcitvity
Each activity has it own fragments
		LoginActivity
The login activity has the following main methods:
onCreate(Bundle savedInstanceState) 
	- this method is called then the activity loads
	- it loads the activity_login fragment (home view)
		-The home fragment has the login_home,login_register and login_forgotpassword your password options
		- The register option goes to the register fragment: 
		- The login_register has the : sendEmail() method that sends an email to the school informing them of the new users
								:onCreate() to load the register fragment and extract values from the main activity
								: ComposeEma() generates the string text that will be sent to the user
								: the oncreate() also access the registerUser in the LoginActivity
		- the login_forgotpassword: sends an sms with the password to he user when they have forgotten their password
		- Login Acitivty: oncreate() to load the acitvity_login
				 	   : registerUser() sends a POST reguest to the server with the title 'register', the server interprets it
					   : onforgotPassword() Sends an SMS or an email to the user with their passwords
					   : showProgress() shows the progress for the application
		- Main Activity: 
						- updateprofile() updates the users profile, starting with the api server (POST), the sqlite db and then the user
						- updateAddress() updates the users address
						-changework() changes the user statues, makes is empty if they have lost their job(then updates server, sqlite and db)
						- logout()- logs the user out of the applicaion: ai they send them an SMS with their password
		- Profile: This is the class the stores the user, OOP :)
				 : the constructor creates a new user based on the information in the sqlite
				 : the updateAddress() updates the user's address and the address in sqlite
				 : the updateprofile() updates the users's profile details and the profile info in sqlite
				 : 


MainAcitivity 


Thank you
MTLEDN001@myuct.ac.za
