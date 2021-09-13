# cleevio-watch-test
Test project for Cleevio

Project represents spring boot web application providing tomcat http server running on default localhost:8080, serving endpoint /watch/upload for uploading Watch object
and saving it to H2 database.
Application provide also backend validation for all request object fields and also exception handling with custom ApiError object returning in error response.
Application can now handle json and xml requests and responses.
There are several Watch rest controller test for proper response http status and validation/exception handling
Given base64 fountain image from request is converted to byte[] for size reduction and saved to db as BLOB.
