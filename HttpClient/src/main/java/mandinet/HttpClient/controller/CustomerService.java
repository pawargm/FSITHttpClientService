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
import mandinet.HttpClient.Model.Product;


@RestController
public class CustomerService {

	@Autowired
	RestTemplate restTemplate;
	
	String url = "http://Customer-Service/customer/";
	
	@RequestMapping(value="/customer/getByMailId",  method=RequestMethod.GET)
	public Customer getByMailId(@RequestParam String mail) {
		
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("mail", mail);
		System.out.println("Calling Customer");
		
		Customer custobj = 
				restTemplate.getForObject(url+"getByMail?"+"mail="+mail, 
				Customer.class);
		return custobj;
	}
		
	@RequestMapping(value="/customer/createCustomer", method=RequestMethod.POST)
	public HttpStatus createCustomer(@RequestBody Customer custobj) {
		
		HttpEntity<Customer> request = new HttpEntity(custobj);
	
		Customer cust = null;//restTemplate.postForObject("http://Customer-Service/customer/create", request, Customer.class);
		
		ResponseEntity<Customer> response = restTemplate.postForEntity(url+"create", request, Customer.class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			System.out.println("Response "+response.getStatusCode());
			System.out.println("Customer succeefully created");
		} else {
			System.out.println("Error to create customer");
			return HttpStatus.BAD_REQUEST;
		}
		return HttpStatus.OK;
	}
	
	@RequestMapping(value="/customer/deleteByMailid", method=RequestMethod.DELETE)
	public HttpStatus removeCustomer(@RequestParam String mail) {
		
		try {
			restTemplate.delete(url+"delete?mail="+mail);
		} catch(Exception e) {
			System.out.println("Exception while deleting customer "+e.toString());
			return HttpStatus.BAD_REQUEST;
		}
		return HttpStatus.OK;
	}
	
	@RequestMapping(value="/customer/getOrderList", method=RequestMethod.GET)
	public List<String> getOrderList(@RequestParam String mail) {
		
		List<String> lst;
		try {
			lst = restTemplate.getForObject(url+"getOrderList?mail="+mail, List.class);
		} catch (Exception e) {
			System.out.println("Exception to get order list "+e.toString());
			return null;
		}
		return lst;
	}
	
	@RequestMapping(value="/customer/getProductList", method=RequestMethod.GET)
	public List<String> getProductList(@RequestParam String mail) {
		
		List<String> lst;
		try {
			lst = restTemplate.getForObject(url+"getProductsInCart?mail="+mail, List.class);
		} catch (Exception e) {
			System.out.println("Exception to get order list "+e.toString());
			return null;
		}
		return lst;
	}
	
	@RequestMapping(value="/customer/login", method=RequestMethod.POST)
	public HttpStatus customerLogin(@RequestBody Customer cust) {
		HttpEntity<Customer> request = new HttpEntity(cust);
		try {
			ResponseEntity<Customer> response = restTemplate.postForEntity(url+"login", request, Customer.class);
		
			if(response.getStatusCode() == HttpStatus.OK) {
				System.out.println("Response "+response.getStatusCode());
				System.out.println("Customer succeefully created");
			} else {
				System.out.println("Error to create customer");
				return HttpStatus.BAD_REQUEST;
			}
			
		} catch (Exception e) {
			System.out.println("Exception to get order list "+e.toString());
			return HttpStatus.BAD_REQUEST;
		}
		
		return HttpStatus.OK;
	}
	
	@RequestMapping(value="/customer/addtocart", method=RequestMethod.GET)
	public HttpStatus addToCart(@RequestParam String productid, @RequestParam String mail) {
		
		System.out.println("In add to category");
		try {
			restTemplate.getForObject(url+"addtocart?productId="+productid+"&mail="+mail, String.class);
		} catch (Exception e) {
			System.out.println("Exception to get order list "+e.toString());
			return HttpStatus.BAD_REQUEST;
		}
		return HttpStatus.OK;
	}
	
	@RequestMapping(value="/customer/addtoBuyLst", method=RequestMethod.GET)
	public HttpStatus addToBuyLst(@RequestParam String productid, @RequestParam String mail) {
		
		try {
			restTemplate.getForObject(url+"addtoBuyLst?productId="+productid+"&mail="+mail, String.class);
		} catch (Exception e) {
			System.out.println("Exception to get order list "+e.toString());
			return HttpStatus.BAD_REQUEST;
		}
		return HttpStatus.OK;
	}
	
	@RequestMapping(value="/customer/removefromcart", method=RequestMethod.GET)
	public List<String> removeFromCart(@RequestParam String productid, @RequestParam String mail) {
		
		try {
			restTemplate.getForObject(url+"removefromcart?productId="+productid+"&mail="+mail, String.class);
		} catch (Exception e) {
			System.out.println("Exception to get order list "+e.toString());
			return null;
		}
		
		List<String> lst = getProductList(mail);
		
		return lst;
	}
	
	@RequestMapping(value="/customer/removefromBuyLst", method=RequestMethod.GET)
	public HttpStatus removeFromBuyLst(@RequestParam String productid, @RequestParam String mail) {
		
		try {
			restTemplate.getForObject(url+"removefromBuyLst?productId="+productid+"&mail="+mail, String.class);
		} catch (Exception e) {
			System.out.println("Exception to get order list "+e.toString());
			return HttpStatus.BAD_REQUEST;
		}
		return HttpStatus.OK;
	}
}
