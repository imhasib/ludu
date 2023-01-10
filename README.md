## Technology Stack:

* Open JDK 17
* Open JRE 17
* Springboot 2.7.7
* Gradle 7.6

## How to run it?

* Build the project:
        
        gradlew build
* Generated .jar file should be found in in the directory: "/build/libs"
* Run the project:

        gradlew bootrun
* Port 8080 should be occupied by this project by default.
* Clean the project:

        gradlew clean

### API documentation:

* Swagger UI:

        http://localhost:8080/swagger-ui/index.html
* API docs:

        http://localhost:8080/v3/api-docs
* Create a new player:

      POST http://localhost:8080/api/players

* Start game:

      POST http://localhost:8080/api/start

* Retrieve current scores:

      GET http://localhost:8080/api/score


### How to run the application with Docker
* Dockerfile is located in the root folder.
* To create an image from Dockerfile, we have to run ‘docker build' like before:

      docker build --tag=ludu:latest .

* To run the container from the image:

      docker run -p 8080:8080 ludu


### Rules of the game:

* There is a maximum of 4 players.
* Each player has a name and age.
* The first player to get a total sum of 25 is the winner. A player does not have to
get 25 exactly (>=25 is OK). The number 25 should be configurable.
* To get started the player will need to get 6. If the player gets 1-5 they will then
have to wait for their turn before having another go.
* When finally hitting the number 6 the player will have to throw again to determine
the starting point. Getting a 6 on the first try will give you 0.
* Each time a player hits number 4, he will get -4 from the total score.
* If a player hits a 4 after hitting the first 6, they do not get a negative score but will
have to roll another 6 before they start accumulating points.
* Each time a player hits the number 6 he will then get one extra throw.
* Output could be shown through the console/terminal or frontend UI. Both options are fine.
* Output should be Logged into console-like - 'Player name:””, Total
  Score:””, Current Value of Dice:”” '
* URL to roll the dice:

        http://developer-test.hishab.io/api/v1/roll-dice
