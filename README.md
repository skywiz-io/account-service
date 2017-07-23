## Accounts microservice
This service provides accounts and transactions REST API.
This service is one part of a larger micro services example app.

### How to run
There are some env veriables needed for the app to run:
1. DB_URL - url to a MySql DB. Example: jdbc:mysql://1.2.3.4:3306/accounts?useUnicode=true&characterEncoding=UTF-8
1. DB_USER - username for the db connection
1. DB_PASSWORD - password for the db connection

### Live api test via swagger ui
The app is deployed on heroku and can be found here: https://ocp-accounts-service.herokuapp.com/swagger-ui.html