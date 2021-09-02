# CPlayers - A Case Study

## Problem Statement

Build a system to search for a cricket player, get player statistics and add players to favourite list.

## Requirements

- The application needs to fetch cricket players from the following API.
https://www.cricapi.com/

- Refer the following URLs to explore more on the cricket player APIs.
  - https://www.cricapi.com/how-to-use.aspx
  - https://www.cricapi.com/how-to-use/player-stats-api.aspx
  - https://www.cricapi.com/how-to-use/player-finder.aspx

- A frontend where the user can register/login to the application, find cricket player, get player statistics and add player to favourite list.
  - On launching the application the user should get the login page. The login page should have a link for registration using which the user should be able to register. On successful registration the user should be taken to the login page. Upon login, the user should be taken to the home page.
  - Proper navigation links for all the pages should be available within pages.
  - Error handling should be implemented across pages. Appropriate messages should be    displayed for the same. Success messages should be displayed for the User Registration.
  - Logout feature should be implemented.

- User can add a player to favourite list and should be able view the favourite list.

## Modules

### UI (User interface) -  should be able to
  - Register user, login and logout
  - Search a player by name
  - View player statistics
  - Add a player to favourite list
  - should be able to see favourite players
  - UI should be responsive which can run smoothly on various devices 
  - UI should be appealing and user friendly.

### UserService
  - should be able to manage user accounts.
### FavouriteService
  - should be able to store all the favourite players for a user

## Tech Stack
    - Spring Boot
    - MySQL
    - REACT
    - CI (Gitlab Runner)
    - Docker, Docker Compose

## Flow of Modules

### Building frontend
    - Building responsive views: 
        * Register/Login
        * Search for a player
        * Player list - populating from external API
        * Build a view to show favourite players
    - Using Services to populate these data in views
    - Stitching these views using Routes and Guards
    - Making the UI Responsive
    - E2E scripts using protractor should be created for the functional flows
    - 

### Note: FrontEnd and all the backend services should be dockerized and run through docker-compose


### Building the UserService
  1. Creating a server in Spring Boot to facilitate user registration and login with MySQL as backend. Upon login, JWT token has to be generated. It has to be used in the Filters set in other services.
  2. Writing swagger documentation
  3. Unit Testing of the entire code which involves the positive and negative scenarios
  4. Implement continuous Integration CI ( use .gitlab-ci.yml)
  5. Dockerize the UserService 

### Building the Favourite Service
  1. Building a server in Spring Boot to facilitate CRUD operation over favourite players stored in Mongo.JWT Filter should be applied for all the API calls of the favourite service. JWT token should be used to authorize the user access to all the resources.
  2. Writing Swagger Documentation
  3. Write Unit Test Cases and get it executed.
  4. Implement CI - continuous Integration ( use .gitlab-ci.yml)
  5. Dockerize the Favourite Service

### Demonstrate the entire application
    1. Make sure all the functionalities are implemented
    2. Make sure both the UI (Component and Services) and server side (For all layers) codes are unit tested. 
    3. All the Services are up and running using docker (Dockercompose should be used for running them)
    4. E2E tests should be executed and shown
    5. Application is completely responsive in nature
    6. Use Eureka Service Registry

