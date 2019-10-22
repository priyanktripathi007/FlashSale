package com.pramati.metaconfigapp.controller;

import com.pramati.metaconfigapp.model.*;
import com.pramati.metaconfigapp.service.SalesService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FlashControllerUnitTest  {
    @Autowired
    private SalesService salesService;

    @Autowired
    private TestRestTemplate template;


    @Test
    public void createUser1() throws Exception{
        CustomerDetails customerDetails=new CustomerDetails();
        customerDetails.setEmail("mohan@gmail.com");
        customerDetails.setName("Mohan");
        customerDetails.setPhoneNumber("1234566");
        customerDetails.setShippingAddress("Hyderabad");
        ResponseEntity<ResponseModel> response=template.postForEntity("/flashsale/user",customerDetails,ResponseModel.class);
        Assert.assertEquals("User Created Successfully",response.getBody().getMessage());

        response=template.postForEntity("/flashsale/register",customerDetails,ResponseModel.class);
        Assert.assertEquals("User Registered for Sales Successfully",response.getBody().getMessage());

    }


    @Test
    public void createUser2() throws Exception{
        CustomerDetails customerDetails=new CustomerDetails();
        customerDetails.setEmail("raju@gmail.com");
        customerDetails.setName("Raju");
        customerDetails.setPhoneNumber("9887654");
        customerDetails.setShippingAddress("Delhi");
        ResponseEntity<ResponseModel> response=template.postForEntity("/flashsale/user",customerDetails,ResponseModel.class);
        Assert.assertEquals("User Created Successfully",response.getBody().getMessage());
        ResponseEntity<ResponseModel> saleresponse=template.getForEntity("/flashsale/endsale", ResponseModel.class);
        response=template.postForEntity("/flashsale/register",customerDetails,ResponseModel.class);
        Assert.assertEquals("User Registered for Sales Successfully",response.getBody().getMessage());

    }

    @Test
    public void createProduct() throws Exception{
        Inventory product=new Inventory();
        product.setDiscount(5);
        product.setQuantity(2);
        product.setName("RolexWatch15");
        product.setItemCategory("Watch");
        product.setDescription("Diamond Studded watch for sale");
        product.setPrice(1000);

        HttpEntity<String> request = new HttpEntity<String>(product.toString());
        ResponseEntity<ResponseModel> response=template.postForEntity("/flashsale/product",product,ResponseModel.class);
        Assert.assertEquals("Product Added to Inventory Successfully",response.getBody().getMessage());

    }

    @Test
    public void purchaseProduct() throws Exception{
        Inventory product=new Inventory();
        product.setDiscount(5);
        product.setQuantity(2);
        product.setName("NewRolexWatchTest");
        product.setItemCategory("Watch");
        product.setDescription("Diamond Studded watch for sale");
        product.setPrice(1000);

        HttpEntity<String> request = new HttpEntity<String>(product.toString());
        template.postForEntity("/flashsale/addProduct",product,ResponseModel.class);

        ResponseEntity<Inventory[]> response=template.getForEntity("/flashsale/product", Inventory[].class);
        String itemId=response.getBody()[0].getItemId();
        PurchaseRequest request1=new PurchaseRequest();
        request1.setProductId(itemId);
        request1.setUserId("raju@gmail.com");
        PurchaseRequest request2=new PurchaseRequest();
        request2.setProductId(itemId);
        request2.setUserId("mohan@gmail.com");
        int i=2;
//        while(i-- >= 0) {
//            new Thread(() -> {
//
//            }).start();
//            new Thread(() -> {
//                ResponseEntity<PurchaseResponse> responses = template.postForEntity("/flashsale/buyProduct", request2, PurchaseResponse.class);
//            }).start();
//
//        }
        ResponseEntity<ResponseModel> saleresponse=template.getForEntity("/flashsale/startsale", ResponseModel.class);
        ResponseEntity<PurchaseResponse> purchase1 = template.postForEntity("/flashsale/purchase", request1, PurchaseResponse.class);
        ResponseEntity<PurchaseResponse> purchase2 = template.postForEntity("/flashsale/purchase", request2, PurchaseResponse.class);
        ResponseEntity<Inventory[]> response1 = template.getForEntity("/flashsale/product", Inventory[].class);
        int quantity = response1.getBody()[0].getQuantity();
        Assert.assertEquals(true, quantity >= 0);
         saleresponse=template.getForEntity("/flashsale/endsale", ResponseModel.class);
    }

}
