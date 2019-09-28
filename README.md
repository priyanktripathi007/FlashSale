# FlashSale
Sales SpringBoot API

Please use the below links

1. To retrieve the list of customer in the application

http://localhost:8191/flashsale/getAllCustomer

2.To retrieve the list of products in the inventory

http://localhost:8191/flashsale/getAllProduct

3.To retrieve the purchase order details list

http://localhost:8191/flashsale/getAllPurchaseOrder

4.To create customer

http://localhost:8191/flashsale/createUser

SAMPLE POST DATA
{
    "name": "priyank",
    "email":"priyank123@gmail.com" ,
    "shippingAddress": "Hyderabad",
    "phoneNumber": "9188823323",
    "paymentInformation": "Master Card-16xxxx"
}

5. To add new products to inventory

http://localhost:8191/flashsale/addProduct

SAMPLE POST DATA

{
    "name": "Rolex Wrist Watch",
    "itemCategory": "Fashion",
    "description": "Gold studded watch",
    "price": 20000,
    "discount":5,
    "quantity":2
}

6. To register customer for sales

http://localhost:8191/flashsale/registerUserForSale

SAMPLE POST DATA

{
    "email":"priyank123@gmail.com"  
}


7.To buy product

http://localhost:8191/flashsale/buyProduct

SAMPLE POST DATA

{
    "userId": "priyank@gmail.com",
    "productId":"f9722cce-2aee-499d-a541-dca75c99ba7a"
 }
 
 



