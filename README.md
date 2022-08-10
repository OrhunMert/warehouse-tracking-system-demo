# Warehouse Tracking System

## Summary

![image](https://user-images.githubusercontent.com/70032540/183904821-a1230ab5-ef8b-4ebc-bf51-9af984e31462.png)

User have a warehouse. If user want to buy a new warehouse, user can it. Our goal is send to email or sms to user according to warehouse state. When User want to take the notificaiton for warehouse state, we send email and sms to user. While user is buying product or selling product from warehouse, if warehouse is empty or full we will send to information about warehouse state. 

![image](https://user-images.githubusercontent.com/70032540/183908391-fd2a4ff5-7fc7-4d45-8aa8-d366651cb77a.png)

I used to Microservice Artitechture for this project. I developt User, Warehouse and Notification Services in Intellij Idea.

## Requirements
- Java 11
- JUnit 4
- MySQL
- Spring Boot
- GSM Phone SMS - Android App(to send sms)
- GSM Helper Tool - Android App(to send sms)

## Microservices

![image](https://user-images.githubusercontent.com/70032540/183912028-3d89a4df-9b6f-4088-95e9-f95bdb809e88.png)

## 1. User

In the user service, we create the user.

```sh
{
    "username":String,
    "password":String,
    "mail":String,
    "phone_number":String
}
```

We check the mail and phone_number to valid. If mail or phone number is not valid, we send to error as response to user.

```sh
Email or phone number is not valid to create User!!!"
```




