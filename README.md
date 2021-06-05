# Lucky Draw
Lucky Draw API app 

Technologies used:
Maven, SprinBoot, Postgres, Hibernate/JPA for DB Queries/ORM 

## API's supported :

General nomenclature for param's in api's:
uid : User ID
cid : Contest ID

### GET API's

getContest : http://localhost:8080/api/getContest?cid=1

computeWinner : http://localhost:8080/api/computeWinner?cid=1

pastWeekWinners : http://localhost:8080/api/pastWeekWinners

nextContest : http://localhost:8080/api/nextContest


getUser : http://localhost:8080/user/get?id=1&name=rahul

API accepts these combinations of params:

1. only id

2. only name 

3. both id and name

### POST API's

createGift : http://localhost:8080/api/createGift?gift=Mobile



createContest :  http://localhost:8080/api/createContest?date=07-06-2021 02:00&gifts=1,2

For createContest Api,

Contest is created on the date mentioned in param 'date'
and this contest would be able to provide gifts given as an input of list of int with param 'gifts'.

createTicket : http://localhost:8080/api/createTicket?uid=2&cid=1

createUser : http://localhost:8080/user/createUser?name=rahul&add=Delhi

## Steps to run

1. Install Postgres and open pgAdmin app.
2. Create a db named luckydraw.
3. Update these properties in file application.properties:
   spring.datasource.url , spring.datasource.username , spring.datasource.password
4. Run the DB server.
5. Build and Run the project in IntelliJ / Eclipse .
