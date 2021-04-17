package mandinet.HttpClient.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import mandinet.HttpClient.Model.Customer;
import mandinet.HttpClient.Model.Product;

@RestController

public class ProductService {

	@Autowired
	RestTemplate restTemplate;
	
	String url = "http://Product-Service/product/";
	
	@RequestMapping(value="/product/getByCategory", method=RequestMethod.GET)
	public List<Product> getByCategory(@RequestParam String category) {
		
		Map<String, String> vars= new HashMap<String,String>();
		System.out.println("Getting Category is "+category);
		vars.put("category", category);
		
		List<Product> lst= 
				restTemplate.getForObject("http://Product-Service/product/filterByCategory?category="+category,
						List.class);
		return lst;	
	}
	
	@RequestMapping(value="/product/addProduct", method=RequestMethod.POST)
	public HttpStatus addProduct(@RequestBody Product prod) {
		
		HttpEntity<Product> request = new HttpEntity(prod);
		ResponseEntity<Product> response = restTemplate.postForEntity(url+"addProduct", request, Product.class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			System.out.println("Response "+response.getStatusCode());
			System.out.println("Customer succeefully created");
		} else {
			System.out.println("Error to create product");
			return HttpStatus.BAD_REQUEST;
		}
		return HttpStatus.OK;
	}
	
	@RequestMapping(value="/product/removeProduct", method=RequestMethod.DELETE)
	public HttpStatus removeProduct(@RequestParam String ownerid, @RequestParam String productname) {
		
		try {
			restTemplate.delete(url+"removeProduct?ownerid="+ownerid+"&productname=?"+productname);
		} catch(Exception e) {
			System.out.println("Exception while deleting product "+e.toString());
			return HttpStatus.BAD_REQUEST;
		}
		return HttpStatus.OK;
	}
	
	@RequestMapping(value="/product/getProdImg", method=RequestMethod.GET)
	public String getProdImg(@RequestParam String ownerid, @RequestParam String productname) {
		String lst= 
				restTemplate.getForObject("http://Product-Service/product/getProdImg?ownerid="+ownerid+"&productname="+productname,
						String.class);
		return lst;	
	}
	
	@RequestMapping(value="/product/getProdById", method=RequestMethod.GET)
	public Product getProdImg(@RequestParam String prodid) {

		Product lst= 
				restTemplate.getForObject("http://Product-Service/product/getProdById?prodid="+prodid,
						Product.class);
		return lst;	
	}
	
	@RequestMapping(value="/product/filterByName", method=RequestMethod.GET)
	public List<Product> getByName(@RequestParam String name) {
		
		List<Product> lst= 
				restTemplate.getForObject("http://Product-Service/product/filterByName?name="+name,
						List.class);
		return lst;	
	}
	
	@RequestMapping(value="/product/filterByNameCategory", method=RequestMethod.GET)
	public List<Product> getByNameCategory(@RequestParam String category, @RequestParam String name) {
		List<Product> lst= 
				restTemplate.getForObject("http://Product-Service/product/filterByNameCategory?category="+category+"&name="+name,
						List.class);
		return lst;	
	}

	@CrossOrigin(origins = "http://localhost:4200/")
	@RequestMapping(value="/product/getAllProd", method=RequestMethod.GET)
	public List<Product> getAllProd() {
		
		System.out.println("Reached");
		List<Product> lst = 
				restTemplate.getForObject("http://Product-Service/product/getAllProd",List.class);
		return lst;
	}
	
}
