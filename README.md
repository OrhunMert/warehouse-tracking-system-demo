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

### 1. User Service

#### http://localhost:8080/users

****Mapping Type: POST

In the user service, we create the user.

```sh
{
    "username":String,
    "password":Integer,
    "mail":String,
    "phone_number":String
}
```

Also we check the validation for email, phone number and password. We have conditions of Turkey for phone number's validation. Passoword length should be upper than 3
If The conditions is not met, we will throw a exception about it. 

```sh
"Email or phone number is not valid to create User!!!"
```

#### http://localhost:8080/users/{id}

**** Mapping Type: GET

You can get information by id about users. We send to response without user id. 

```sh
{
  "username": String,
  "password": Integer,
  "mail": String,
  "phone_number": String
}
```

When user is not found, we throw error message as response to server.

```sh
"User is not found by id!!!"
```

#### http://localhost:8080/users/{id}

****Mapping Type: PUT

User can update to your information. But Update operation is not valid for all information of user. 

```sh
{
  "mail": String,
  "password": Integer,
  "phone_number":String,
  "username": String
}
```

when update is completed succesfully, we send the response to user.

```sh
{
  "mail": String,
  "password": Integer,
  "phone_number":String,
  "username": String
}
```

#### http://localhost:8080/users/{id}

****Mapping Type: DELETE

User can delete yourself. if user is not found while user is deleting yourself, we send error message to server as response. 

```sh
"User is not found by id! to delete!!!"
```

#### http://localhost:8080/users/notification/{id}

****Mapping Type: GET

We use the this end-point to send operations in the warehouse. We take the phone number and email information of users with this end-point. When user is found, we send the phone number and email information of user as response to server.

```sh
{
  "mail": String,
  "phoneNumber": String
}
```

### 2. Notification Service

We send the sms or email to user about warehouse state in notification service. There are three state for the warehouse to send. First we send the snms and email to to user when user want to get notification about warehouse. Second after the buying operation if warehouse is being full. Third after the selling operation if warehouse is being empty. We check the this conditions in warehouse service. Warehouse service request api of notification service for the send sms and email to user. Notification service has two controller. First controller is for the email operations, other controller is for the sms operations. 

#### 2.1 Email Controller

Email controller has there end-point for send operation. We send the email with SMTP.

##### What is the SMTP?

![image](https://user-images.githubusercontent.com/70032540/184081605-39bca173-e65d-4977-a09a-42e18f9f655d.png)

The Simple Mail Transfer Protocol or SMTP is the Internet standard for sending and receiving emails. Email clients use SMTP to send messages to a mail server for delivery while email servers use it to forward messages to their recipients.

Outgoing emails are usually sent using port 587 or 465 while port 25 is used for relaying the message between mail servers.

##### Initiliaze to Spring Boot for the SMTP

First step, you should create gmail account for the sender address. Because you connection with smtp server in spring. after you created the your smtp account for gmail, you add the this code part to application.properties file in the spring boot.

![image](https://user-images.githubusercontent.com/70032540/184083411-9477329b-ac85-46d6-afca-207612bb94cf.png)

You can follow the link how to create account and implementation in your project: https://www.geeksforgeeks.org/spring-boot-sending-email-via-smtp/

##### Restful Apis

- http://localhost:8082/emails --> Mapping type is POST. we send the a simple message to user. We don't use to send email in warehouse service. 
- http://localhost:8082/emails/attachment --> Mapping type is POST. we send the email with attachment to user. We don't use to send email in warehouse service.
- http://localhost:8082/emails/sendemail/info --> Mapping type is GET. we send the email a simple message to user for warehouse state. We use to send email in warehouse service.

#### 2.2 SMS Controller

In the projects usually use the RabbitMQ, Kafka, Free Rest Api for SMS Operations. I prefer a Android App on the mobile device to send sms. There are two android app from Play Store that should download your mobile device. 

https://play.google.com/store/apps/details?id=com.gsmmodem
https://github.com/sadiqodho/GSM-Helper-Tool/blob/master/download-app/latest-helper-tool-app.apk

After the you downloaded the android apps your mobile device, you need to define user, port and address for spring boot and your mobile device. 

![Screenshot](https://user-images.githubusercontent.com/70032540/184116537-a7b1f1a5-c1d7-4d3c-a56b-5c3013e25d24.PNG)

We send the Sms like email operations to user about warehouse. So when user wants to get information about warehouse state, when warehouse was empty, when warehouse was full. 

SMS Controller has two Restful Apis to send SMS. 

- http://localhost:8082/sms/sendsms --> Mapping Type is GET. we send the a simple sms to user. We use to send sms in warehouse service. 
- http://localhost:8082/sms/sendAllSMS --> Mapping Type is GET. we send the a simple sms to user. We don't use to send sms in warehouse service.



