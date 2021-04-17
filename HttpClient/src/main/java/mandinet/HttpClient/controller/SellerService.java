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
import mandinet.HttpClient.Model.Seller;

public class SellerService {

	
	@Autowired
	RestTemplate restTemplate;
	
	String url = "http://Seller-Service/seller/";
	
	@RequestMapping(value="/seller/create", method=RequestMethod.POST)
	public HttpStatus addProduct(@RequestBody Seller seller) {
		
		HttpEntity<Seller> request = new HttpEntity(seller);
		ResponseEntity<Seller> response = restTemplate.postForEntity(url+"create", request, Seller.class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			System.out.println("Response "+response.getStatusCode());
			System.out.println("SellerObj succeefully created");
		} else {
			System.out.println("Error to create product");
			return HttpStatus.BAD_REQUEST;
		}
		return HttpStatus.OK;
	}
	
	@RequestMapping(value="/seller/removeSeller", method=RequestMethod.DELETE)
	public HttpStatus removeSeller(@RequestParam String mail) {
		
		try {
			restTemplate.delete(url+"delete?mail="+mail);
		} catch(Exception e) {
			System.out.println("Exception while deleting SellerObj "+e.toString());
			return HttpStatus.BAD_REQUEST;
		}
		return HttpStatus.OK;
	}
	
	@RequestMapping(value="/seller/getBymail",  method=RequestMethod.GET)
	public Seller getByMailId(@RequestParam String mail) {
		
		Seller custobj = 
				restTemplate.getForObject(url+"getByMail?"+"mail="+mail, 
				Seller.class);
		return custobj;
	}
	
	@RequestMapping(value="/seller/addToProdLst",  method=RequestMethod.GET)
	public Seller addToProdLstMailProd(@RequestParam String mail, @RequestParam String prodid) {
		
		Seller custobj = 
				restTemplate.getForObject(url+"addToProdLst?"+"mail="+mail+"&prodid="+prodid, 
				Seller.class);
		return custobj;
	}
	
	@RequestMapping(value="/seller/removeFromProdLst",  method=RequestMethod.GET)
	public Seller removefromProdLstMailProd(@RequestParam String mail, @RequestParam String prodid) {
		
		Seller custobj = 
				restTemplate.getForObject(url+"removeFromProdLstmail?"+"mail="+mail+"&prodid="+prodid, 
				Seller.class);
		return custobj;
	}
	
	@RequestMapping(value="/seller/getProdLst",  method=RequestMethod.GET)
	public List<String> getProdLst(@RequestParam String mail) {
		
		List<String> custobj = 
				restTemplate.getForObject(url+"getProdLst?"+"mail="+mail, 
				List.class);
		return custobj;
	}
}
