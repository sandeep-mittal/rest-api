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

  
### book-repo (backend rest api) ###

1. cd <repo-path>/rest-api/books-repo-api
2. mvn clean install
3. cd target
4. java -jar books-repo-api.jar

### library-api (front end rest api) ###

1. cd <repo-path>/rest-api/library-api
2. mvn clean install
3. cd target
4. java -jar library-api-docker.jar

## Docker ##
