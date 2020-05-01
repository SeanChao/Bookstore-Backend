# Bookstore Backend

## Backend API (**WIP**)

### Book Data

path: /api/book

|Path|Method|Description|
|----|----|----|
|/info?:id|GET|return data of the book with matching `id` |
|/author/:name|GET|return books whose author is `name`|
|/all|GET|return an array of data of all books |

### User Authentication

|Path|Method|Description|
|---|---|---|
|/login|POST|User login. The body should include `username` and `password`.|
|/logout|GET|User logout.|

## Database Design (**WIP**)

The MySQL database includes a schema named `bookstore` with following tables: 

- book
- user (**WIP**)
 