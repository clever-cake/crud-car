# crud-car

# Getting started

This application needs a connection to a postgres database. The database can easily setup by using docker-compose. Simply run 

```
docker-compose up -d
```

or if you use docker compose v2 

```
docker compose up -d
```

The data is stored in a docker volumes. You can inspect the data stored in the db by using pgadmin4. It is available under localhost:5050.

Use the credentials

```
User: admin@admin.com
Password: admin
```


And create a connection for a server on the host postgres:5432 with user postgres and the password postgres


# Perform http requests

If you use intellij you may use the predefined http requests in the file _requests.http_ to explore the api.