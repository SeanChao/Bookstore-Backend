# Bookstore Backend

## Backend API (**WIP**)

### Book Data

path: /api/book

|Path|Method|Description|
|----|----|----|
|/author/:name|GET|return books whose author is `name`|
|/all|GET|return an array of data of all books |
|/info?:id|GET|return data of the book with matching `id` |
|/new|POST|Inserts a new book.|

### User Authentication

|Path|Method|Description|
|---|---|---|
|/login|POST|User login. The body should include `username` and `password`.|
|/logout|GET|User logout.|

### Order

path: `/api/orders`

|Path|Method|Description|
|---|---|---|
|/all?id=:id|GET|Get all of the orders of user whose ID is `id`.|
|/new|POST|Post JSON data of a new order. JSON data should be an array of `OrderItem` with `bookId`, `amount`.|

## Database Design (**WIP**)

The MySQL database includes a schema named `bookstore` with following tables: 

- book
- user
- orders
- order_item
 