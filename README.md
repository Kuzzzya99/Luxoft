# README #
### Requirements ###
For building and running the application you need:

Java 17

Maven 3

PostgreSQL Server 15

### Application details ###
Application built to consume data-snapshots from one client, validate and persist data in storage, distribute persisted data to other clients via REST interface.

##### To upload .csv file: #####

```sh
POST localhost:8083/upload
Body: form-data, select file from your computer
```
You can use this file for test: https://drive.google.com/file/d/1Ogfllr4EkGdlY4dibQZ9Ut1dSflWowNH/view?usp=share_link

##### To get record: #####
```sh
GET localhost:8083/{primary_key}
```

##### To delete record: #####
```sh
DELETE localhost:8083/owner/{primary_key}
```
