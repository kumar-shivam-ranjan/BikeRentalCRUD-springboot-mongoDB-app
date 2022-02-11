# BikeRentalCRUD-springboot-mongoDB-app

## Problem Statement
This project aims to solve the problem of Renting Bikes remotely and hence provides the solution that facilitates the process of renting bikes online on daily and weekly basis.

## Overview
- Full stack web application that allows users to rent bikes (extendible to any type of vehicle)
- Facilitates the process of renting a vehicle
- Rent bikes/cars on daily or weekly basis.

## Introduction
A company has many hubs located across the city. Each hub has some bikes parked in that location which are available for booking. Customers can check for availability in respective hubs and book bikes on daily/weekly basis. Prices vary based on time duration of booking. It will be the customer 's responsibility to return the bikes on time. Bikes will be available for renting again once customers return it. If 2 customers request for booking at the same time, only one will be booked and the other will be denied.

1. Customers can book bikes from any hub.
2. Customer can register themselves into system
3. System should to extensible to add new hubs
4. System should support the addition of new vehicles - cars etc.

## Technology
- Spring boot
- MongoDB
- Thymeleaf

## Steps to run this project:
- **Step 1**:  Clone this github repository through `git clone https://github.com/ksrrock/BikeRentalCRUD-springboot-mongoDB-app.git`
- **Step 2**:  Open the Root folder `BikeRentalCRUD-springboot-mongoDB-app` on your favourite IDE (IntelliJ or SpringToolSuite)
- **Step 3**:  Run the imported project using the run button on your IDE
- **Step 4**:  Access the User Dashboard at http://localhost:8080/user/index
- **Step 5**:  Access the admin Dashboard at http://localhost:8080/adminlogin
- **Step 6**:  Credentials for Admin Login is:
  - Username: admin@gmail.com
  - Password: admin@123


# Schema
| Users  | Vehicles  | Station |
| :------------ |:---------------:| -----:|
| User ID      | Vehicle ID | Station ID |
| First Name      | vehicle type       |   Station Head |
| Last Name | Engine        |    Head contact |
| Email | Fuel type      |    Street |
| Password |  Mileage        |    city |
| Role |  Description    |    State |
| Contact |  Daily Price        |    Pincode |
| Driving License |  Weeky Price     |     |


| Inventory  | Booking Requests  | 
| :------------ |:---------------:| 
| Vehicle ID      | User ID | 
| Station ID      | Vehicle ID       |   
| Quantity | Station ID        |    
| Availability status | CheckIn time      |    
|  | checkOut Time        |    
|  | Total Price      |    

## Rest Endpoints
#### Swagger UI has been used for REST API documentation.
#### It can be accessed via the URL: http://localhost:8080/swagger-ui/index.html

<img width="1424" alt="Screenshot 2022-01-28 at 9 41 16 PM" src="https://user-images.githubusercontent.com/42781233/151581850-726ac4e1-8ddc-44d8-bb83-1b7df59ed962.png">
<img width="1440" alt="Screenshot 2022-01-28 at 9 39 24 PM" src="https://user-images.githubusercontent.com/42781233/151581537-534ec74b-2665-4c53-a3cb-d36125bba2ac.png">
<img width="1440" alt="Screenshot 2022-01-28 at 9 55 17 PM" src="https://user-images.githubusercontent.com/42781233/151584121-295e3466-f8e1-476a-b5cd-d0589c4ff2e1.png">

## Screenshots

<img width="1440" alt="Screenshot 2022-01-29 at 4 44 09 PM" src="https://user-images.githubusercontent.com/42781233/151660811-594c35b3-f98d-4d2b-8bf0-054ce1d19264.png">
<img width="1440" alt="Screenshot 2022-01-29 at 4 44 20 PM" src="https://user-images.githubusercontent.com/42781233/151660822-0e8c1973-1728-4bcc-a632-2df0314da07e.png">
<img width="1440" alt="Screenshot 2022-01-29 at 4 44 30 PM" src="https://user-images.githubusercontent.com/42781233/151660872-79da5449-bb2f-45e6-b63c-b9547ae35cd6.png">
<img width="1440" alt="Screenshot 2022-01-29 at 4 44 42 PM" src="https://user-images.githubusercontent.com/42781233/151660875-7f9a8a28-1baf-4a35-988d-8d042f839bf1.png">
<img width="1440" alt="Screenshot 2022-01-29 at 4 44 55 PM" src="https://user-images.githubusercontent.com/42781233/151660971-5cc3f7b8-c9a8-464b-a3bb-92d9fef76934.png">
<img width="1440" alt="Screenshot 2022-01-29 at 4 45 03 PM" src="https://user-images.githubusercontent.com/42781233/151660990-d5dd440f-c7f7-4a20-8ec8-34dc623f211e.png">

#### To know more about me , visit : https://ksrrock.github.io/
