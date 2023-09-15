# Football-Project
with external api given spring security using jwt

if ROLE_ADMIN 
can access details or api's
else ROLE_USER
cant access

----------
http://localhost:8080/api/v1/auth/signup
register

{
    "name": "John",
    "userName": "john",
    "email": "john@example.com",
    "password": "password",
    "userRole": "ROLE_ADMIN"
}
------------
http://localhost:8080/api/v1/auth/signin
sign in
-----------
http://localhost:8080/api/v1/token-details
token details validation
-----------
http://localhost:8080/api/v1/draw-matches?year=2011
getting the required details
-------------
