# Angular-spring-boot-starter

Environment Essentials :-<br>
Java 1.8 SDK<br>
NodeJS(NPM) 16.20.0<br>
Spring-boot 2
Angular 9
(supported npm 9.6.5 with node 16)

Spring-boot code is inside url directory you can start the app using

	-> go inside url directory and run (mvn clean install -DskipTests)
	-> after that, go inside target folder and run (java -jar url-0.0.1-SNAPSHOT.jar)
	-> your service will run on port 8081, you should also map 10.113.169.136 in your system in etc/hosts file.

Angular code is inside client directory you can start the app using

	-> go inside client folder and run (npm install)
	-> then run (npm start)
	-> in ui navigate to (localhost:4205/home)

Test cases covered

-> User will input url in the provided text box(proper link validation has been done in UI itself).
-> After click on generate button, A random uel will be generated.
	-> If Provided url is new, then a new random url will be generated.
	-> If provided url is existing only(i.e, earlier you have provided same url) then
		-> If you have provided within 5 min, then same generated url will be shown in the screen with the given url timestamp updated to current time.
		-> If you have provided after 5 min, then a new random url would be generated.
-> Here we are using scheduler, which will regularly check the time whether for a provided url is expired or not.
-> when click on the random generated url(If all above check are true), you will be redirected to a new page from random generated url.
-> If you will access url after 5 min, then invalid url will pop on screen.




