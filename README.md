# Library API and Book Repo API (Backend Service)
Library API is front end rest api. It exposes two rest apis:-
1. library-api/addBook- This rest api does following:-
   *validates incoming JSON request
   *transforms front end JSON request to backend rest api JSON request ( library-api-to-book-repo)
   * 
   *hits backend service-book-repo(another Spring boot service with Spring JPA repository)
   * 
2. library-api/removeBook
