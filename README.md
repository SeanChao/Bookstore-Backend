# Bookstore Backend

## Backend API

### Book Data

path: /api/book

|Path|Method|Description|
|----|----|----|
|/author/:name|GET|return books whose author is `name`|
|/all|GET|return an array of data of all books |
|/info?:id|GET|return data of the book with matching `id` |
|/new|POST|Inserts a new book.|
|/del|POST|Mark a book as deleted.|

### User Authentication

|Path|Method|Description|
|---|---|---|
|/login|POST|User login. The body should include `username` and `password`.|
|/logout|GET|User logout.|
|/checkSession|GET|Check user authentication.|

### Order

path: `/api/orders`

|Path|Method|Description|
|---|---|---|
|/all?id=:id|GET|Get all of the orders of user whose ID is `id`.|
|/new|POST|Post JSON data of a new order. JSON data should be an array of `OrderItem` with `bookId`, `amount`.|

## Database Design

The MySQL database includes a schema named `bookstore` with following tables: 

- book
- user
- orders
- order_item
 
 ## Frontend-Backend Messages
 
 In general, a response from backend is a `JSON` object, with a `status` attribute indicating the result of requests. In
  most cases, `0` indicates a successful operation while a positive number indicates something wrong happened.
  
 |Key|Description|
 |---|---|
 |status|operation status code|
 |msg|A short description about an error|
 
|Status Code| Description|
|---|---|
|1|Permission denied.| 
|2|User Blocked.|
|3|Duplicate username.|
|4|Invalid email address in user registration.|
|100| Generic error: invalid data|

## Drawbacks

- Some ORM is not well implemented
- Authentication is fake
