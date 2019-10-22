package com.pramati.metaconfigapp.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.pramati.metaconfigapp.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import com.pramati.metaconfigapp.service.SalesService;

@RestController
@RequestMapping(value = "/")
@org.springframework.web.bind.annotation.CrossOrigin(origins = "*", allowedHeaders = "*")
public class FlashSaleController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	SalesService salesService;


	
	@RequestMapping(value = "/purchase", method = RequestMethod.POST)
	public ResponseModel buy(@RequestBody PurchaseRequest request)
	{
			return salesService.buyProduct(request);

	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseModel createUser(@RequestBody CustomerDetails customerDetails)
	{	customerDetails.setRegisteredForSale(false);
		salesService.registerCustomer(customerDetails);
		return new ResponseModel("User Created Successfully");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseModel registerUser(@RequestBody CustomerDetails 	 customerDetails )
	{

		salesService.registerCustomerForSale(customerDetails.getEmail());
		return new ResponseModel("User Registered for Sales Successfully");
	}

	@RequestMapping(value = "/unregister", method = RequestMethod.POST)
	public ResponseModel unregisterUser(@RequestBody CustomerDetails 	 customerDetails )
	{

		salesService.unregisterCustomerForSale(customerDetails.getEmail());
		return new ResponseModel("User Registered for Sales Successfully");
	}

	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public ResponseModel addProduct(@RequestBody Inventory product)
	{
		UUID uuid = UUID.randomUUID();
		product.setItemId(uuid.toString());
		product.setSaleStartDate(new Date());
		product.setSaleEndDate(new Date());
		salesService.addProduct(product);
		return new ResponseModel("Product Added to Inventory Successfully");
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<CustomerDetails> getAllCustomer()
	{

		return salesService.getCustomer();
	}

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public List<Inventory> getAllProduct()
	{

		return salesService.getProduct();
	}

	@RequestMapping(value = "/purchase", method = RequestMethod.GET)
	public List<PurchaseDetail> getPurchaseOrder()
	{

		return salesService.getPurchaseDetails();
	}
	
	

}