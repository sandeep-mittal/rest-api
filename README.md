# Library API and Books Repo API (Backend Service)
Library API is front end rest api. It exposes two rest apis over 8080 port:-
1. /library-api/addBook
2. /library-api/removeBook

At high level, rest api's does following:-
   * validates incoming JSON request against JSON Schema (library-api-schema.json)
   * transforms front end JSON request to backend rest api JSON request ( library-api-to-book-repo)
   * validates backend JSON request against JSON Schema (book-repo-service-schema.json)
   * hits backend service-book-repo(another Spring boot service with Spring JPA repository)
   * validates backend JSON response against JSON Schema (book-repo-service-schema.json)
   * transforms backend-book-repo api response to front end-library-api response

Books-Repo-api is backend rest api. It exposes rest apis for CRUD over 8085 port:-
1. /books-repo-api/getBooks
2. /books-repo-api/addBook
3. /books-repo-api/removeBook
4. /books-repo-api/getbook/{bookId}

At high level, rest apis are exposing CRUD operations with in-memory H2 database.

## Technology ##
1. Spring Boot: v2.2.5
2. Apache Camel: v3.0.0
3. Java: v1.8
4. springfox-swagger2: v2.8.0
5. springfox-swagger-ui: v2.8.0
6. Json
7. Docker
8. Git

## How to Build and Run ##
Both spring boot applications need to be built and run. Starting with backend service-"book-repo" first.
Clone the repository
git clone https://github.com/sandeep-mittal/rest-api.git

### library-api (front end rest api) ###

1. cd <repo-path>/rest-api/library-api
2. mvn clean install
3. cd target
4. java -jar library-api-docker.jar ( final name in pom.xml)

___API URL:- /library-api/addBook___http://http://localhost:8080/library-api/addBook

* HTTP POST
* JSON REQUEST-Happy Scenario
{
        "bookId": 4,
        "bookName": "Book04",
        "author": "test_04",
        "type": "Friction_04"
    }
* JSON RESPONSE
{
    "status": "SUCCESS",
    "message": "Added Book04 successfully."
}

* JSON REQUEST-Error Scenario
{
        "bookId": 4,
        "author": "test_04",
        "type": "Friction_04"
    }
* JSON RESPONSE
{
    "timestamp": "2020-03-26T08:31:53.517+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "bookName: null found, string expected",
    "path": "/library-api/addBook"
}
___API URL:- /library-api/removeBook/{bookId}___http://http://localhost:8080/library-api/removeBook/1

* HTTP GET

* JSON RESPONSE- Happy Scenario
{
    "status": "SUCCESS",
    "message": "Book is removed successfully"
}

### book-repo (backend rest api) ###

1. cd <repo-path>/rest-api/books-repo-api
2. mvn clean install
3. cd target
4. java -jar books-repo-api-docker.jar ( final name in pom.xml)
  
___API URL:- /book-repo/addBook___http://localhost:8085/book-repo/addBook

* HTTP POST
* JSON REQUEST-Happy Scenario

{
    "id": 1,
    "name": "Book01",
    "author": "test_01",
     "type" : "Friction"
}


* JSON RESPONSE Added Book01 successfully.

___API URL:- /book-repo/getBooks___http://localhost:8085/book-repo/getBooks

* HTTP GET

* JSON RESPONSE
[
    {
        "id": 1,
        "name": "Book01",
        "author": "test_01",
        "type": "Friction"
    },
    {
        "id": 2,
        "name": "Book02",
        "author": "test_01",
        "type": "Friction"
    },
    {
        "id": 4,
        "name": "Book04",
        "author": "test_04",
        "type": "Friction_04"
    }
]

___API URL:- /book-repo/getBook/{id}___http://localhost:8085/book-repo/getbook/1

* HTTP GET
* JSON RESPONSE 
{
    "id": 1,
    "name": "Book01",
    "author": "test_01",
    "type": "Friction"
}

___API URL:- /book-repo/removeBook?{id}___http://localhost:8085/book-repo/removeBook?bookId=1

* HTTP GET
* JSON RESPONSE Book is removed successfully

## Docker ##
1. docker -t books-repo-api-docker.jar .
2. docken run -p 9090:8085 books-repo-api-docker.jar
3. docker -t library-api-docker.jar .
4. docken run -p 9090:8080 library-api-docker.jar

## Docker Hub ##
1. docker login
2. docker tag books-repo-api-docker.jar release/books-repo-api-docker.jar
3. docker push release/books-repo-api-docker.jar
4. docker pull release/books-repo-api-docker.jar
5. docker push release/library-api-docker.jar
4. docker pull release/library-api-docker.jar



