package mandinet.HttpClient.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import mandinet.HttpClient.Model.Customer;
import mandinet.HttpClient.Model.Order;

public class OrderService {

	@Autowired
	RestTemplate restTemplate;
	
	String url = "http://Order-Service/order/";
	
	
	@RequestMapping(value="/order/create", method=RequestMethod.POST)
	public HttpStatus addProduct(@RequestBody Order order) {
		
		HttpEntity<Order> request = new HttpEntity(order);
		ResponseEntity<Order> response = restTemplate.postForEntity(url+"create", request, Order.class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			System.out.println("Response "+response.getStatusCode());
			System.out.println("Customer succeefully created");
		} else {
			System.out.println("Error to create product");
			return HttpStatus.BAD_REQUEST;
		}
		return HttpStatus.OK;
	}
	
	@RequestMapping(value="/order/removeOrder", method=RequestMethod.DELETE)
	public HttpStatus removeOrder(@RequestParam String customerId, @RequestParam String productId) {
		
		try {
			restTemplate.delete(url+"delete?customerId="+customerId+"&productId=?"+productId);
		} catch(Exception e) {
			System.out.println("Exception while deleting product "+e.toString());
			return HttpStatus.BAD_REQUEST;
		}
		return HttpStatus.OK;
	}
	
	@RequestMapping(value="/order/getByOrderId",  method=RequestMethod.GET)
	public Order getByOrderId(@RequestParam String orderid) {
		
		Order custobj = 
				restTemplate.getForObject(url+"getOrderById?"+"orderid="+orderid, 
				Order.class);
		return custobj;
	}
	
	@RequestMapping(value="/order/getByProduct",  method=RequestMethod.GET)
	public List<Order> getByProductId(@RequestParam String productid) {
		
		List<Order> custobj = 
				restTemplate.getForObject(url+"getOrderByProductId?"+"productid="+productid, 
				List.class);
		return custobj;
	}
	
	@RequestMapping(value="/order/getByCustomerid",  method=RequestMethod.GET)
	public List<Order> getByCustomerid(@RequestParam String customerid) {
		
		List<Order> custobj = 
				restTemplate.getForObject(url+"getOrderByCustomerId?"+"customerid="+customerid, 
				List.class);
		return custobj;
	}
	
}
