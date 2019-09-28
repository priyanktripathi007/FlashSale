package com.pramati.metaconfigapp.service;

import com.pramati.metaconfigapp.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SalesService {

	
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	

	public void registerCustomer(CustomerDetails customerDetails) {
		mongoTemplate.save(customerDetails);
		
	}

	public List<CustomerDetails> getCustomer() {
		return mongoTemplate.findAll(CustomerDetails.class);

	}

	public void registerCustomerForSale(String emailId) {
		//Query query = new Query();
		//query.addCriteria(Criteria.where("email").is(emailId));

		CustomerDetails customerDetails= mongoTemplate.findOne(Query.query(Criteria.where("email").is(emailId)),CustomerDetails.class);
		if(customerDetails!=null) {
			customerDetails.setRegisteredForSale(true);
			mongoTemplate.save(customerDetails);
		}


	}
	public List<Inventory> getProduct() {
		return mongoTemplate.findAll(Inventory.class);

	}

	public List<PurchaseDetail> getPurchaseDetails() {
		return mongoTemplate.findAll(PurchaseDetail.class);

	}

	public void addProduct(Inventory product) {
		
		mongoTemplate.save(product);
		
	}


	public void addPurchaseDetails(PurchaseDetail details) {

		mongoTemplate.save(details);

	}

	public ResponseModel buyProduct(PurchaseRequest request) {
		String userId=request.getUserId();
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(userId));
		CustomerDetails customer=mongoTemplate.findOne(query, CustomerDetails.class);
		if(!customer.isRegisteredForSale())
		{
			return new ResponseModel("Please register for sale");
		}
		
		Query updateProductQuery = new Query();
		updateProductQuery.addCriteria(Criteria.where("itemId").is(request.getProductId()));
		Inventory product=mongoTemplate.findOne(updateProductQuery, Inventory.class);
		int quantity=product.getQuantity();
		
		if(quantity<=0)
		{
			return new ResponseModel("OUT OF STOCK");

		}
		
		synchronized (product) {
			product.setQuantity(quantity-1); 
			 mongoTemplate.save(product);
			 PurchaseDetail detail=new PurchaseDetail();
			 detail.setAmount(product.getPrice()*(1.0-(product.getDiscount()/100.0)));
			 detail.setDescription("Item purchased in big billion sale");
			 detail.setPaymentDetails(customer.getPaymentInformation());
			 detail.setItemId(product.getItemId());
			 detail.setItemCategory(product.getItemCategory());
			 detail.setPurchaseDate(new Date());
			 detail.setName(customer.getName());
			 detail.setQuantity(1);
			 detail.setShippingAddress(customer.getShippingAddress());
			 this.addPurchaseDetails(detail);
			return new PurchaseResponse("Order placed successfully",detail);

		}

		//return new ResponseModel("Order places sucessfully");

	}


	
	
	
	

}
