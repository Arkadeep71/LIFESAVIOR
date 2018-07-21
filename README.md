# LIFESAVIOR
a Android Based Blood Banking System

#Introduction

This project is based on Android App Development.  Android is a mobile operating system (OS) developed by Google. Android currently holds 84.82% of the global market share and is a very promising and vast expanding area.
“Life Savior” is a simple App which has been made by implementing some of the basic concepts in the area of mobile app development.
#OBJECTIVE

Objective of “Live Savior” is basically an app to reduce the time lag between searching for and contacting different blood donors across the country in just few clicks. Donor will be prompted to enter an individual's details, like name, phone number, and blood type. after that your contact details will be saved in the database. at the urgent time of a blood requirement, one does not need to register but he can quickly check for contacts matching a particular or related blood group and reach out to them via phone call through the Life Savior app.

#FUNCTIONALITIES 

o	Splash Screen Page: This is the first page which will be displayed on clicking the app icon.
o	Log in Page: This page is linked with webhost MySQL database. This page is for existing users. Here the user needs to enter his user-name which is the unique key and then has to enter his password and confirm it. After successful login the user is directed to Homepage.
                If the user is new user then there is a button named “Register yourself” to Register himself/herself in the application database.
o	Registration Page: This page is also linked with webhost MySQL database. This page content 9 data-field to collect new user’s information. After clicking submit button the user will be added into database and redirected to the log in page.
o	Homepage: This page contains a navigation Drawer with hamper button and four buttons. The navigation drawer content seven items i.e. Home, Profile, Log out, About us, About dev, Send, share. The four buttons of homepage are donor Search, Request, Motivational Videos, Blood donation info. on click on these items or buttons it direct to their respective pages.
o	Profile Page: This page is also linked with webhost MySQL database.  It shows the details information about logged in user .In this page there is a log out button by clicking which the user can be logged out from the app .
o	Donor Search Page: This page contains a clickable spinner whose drop down menu shows the different blood groups. By clicking a particular blood group, the user can search the donor list with contact information of that particular blood group. This page is also linked with webhost MySQL database
o	Request Page: In this page there is a list of blood recipient requests with contact details. Here is a Add button by clicking which it direct to add Request page. This page is also linked with webhost MySQL database
o	Add Request Page: Here The user can add a blood request with patient information.
o	Motivational video page: In this page there is a list of motivational videos. The user can click on the video button and play the video.
o	Blood Donation Info: This page contains details information about blood donation.
o	About us page: This page contains details information about “Life Savior” application
o	About dev page:     This page contains details information about the app developer and a rating bar.

#	TOOLS USED
	Client Side:
This app has been made on Android Studio which is Google’s official Android Development IDE. The basic widgets such as Text View, Image View, Button, Password, Spinner, Rating Bar, WebView, Alert Dialog Box have been used. Appropriate “Toast” messages have been used at every step to make the app more user-friendly. 

	Server Side:
For the server-side code and database we used Webhost Server which provide us a online domain. User data is stored in webhost MySQL database
