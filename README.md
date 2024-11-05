# Home Assignment: Scratch Game #

## Requirements ##

I have used JDK 17 to develop the app and would recommend to use run it on JDK 17 or up.
To run the app , place the shared jar and any test json files relative to each other so that when running the app , the location of the json would be accessible from the location of the app

You can also generate the app jar bu unzipping source code and ideally import it you your preferred IDE and run a mvn clean install , which would generate the same named jar on the target folder, in a case that there may be a need to use a different JDK version , one can also edit the target and sorurce of the JDK on the POM and regenerate the appfor a specific jdk.

```bash
java -jar cyberspeed-assignment-1.0-RELEASE-jar-with-dependencies.jar --config config.json --betting-amount 100
 ```
where config.son is the location of the file relative to the path of the produced *cyberspeed-assignment-1.0-SNAPSHOT-jar-with-dependencies.jar* and 100 is the betting amount in this case , one way to run the application is to have the jar and the config on the same directory.

Th idea is that every time you want to run the app , you can run the same command and you would be getting a different matrix , note the matrix will be shown on the logs with the print out of the matrix shown on the console , so that the user can try and visualise the scratch board and would know immidiately if they won or lost and if the won , they would get to see the reward amount immidiately and on the log they would see the winning permutations that got them to the amount.