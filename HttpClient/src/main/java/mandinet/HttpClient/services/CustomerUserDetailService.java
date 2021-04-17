package mandinet.HttpClient.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mandinet.HttpClient.Model.Customer;
import mandinet.HttpClient.services.repository.CustomerRepository;

@Service
public class CustomerUserDetailService implements UserDetailsService{

	@Autowired(required = true)
	CustomerRepository customer;
	
	
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		
		
		//Use database here 
		Customer cust = customer.findBymail(mail);
		
		
		if(cust != null) {
			
			return new User(mail,cust.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found !!");
		}
		
		
	}

	
	
	
}
