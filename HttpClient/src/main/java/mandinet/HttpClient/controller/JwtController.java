package mandinet.HttpClient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mandinet.HttpClient.Model.JwtRequest;
import mandinet.HttpClient.Model.JwtResponse;
import mandinet.HttpClient.services.CustomerUserDetailService;
import mandinet.HttpClient.controller.JwtUtil;

@RestController
@CrossOrigin(origins = "*")
public class JwtController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomerUserDetailService customeUSerDetailService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@RequestMapping(value="/token", method=RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) {
		
		System.out.println("USername and password");
		System.out.println(jwtRequest.getPassword());
		System.out.println(jwtRequest.getMail());
		
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getMail(), jwtRequest.getPassword()));
			
		} catch (UsernameNotFoundException e) {
			System.out.println(e);
		}
		//Fine area
		
		UserDetails userDetail = this.customeUSerDetailService.loadUserByUsername(jwtRequest.getMail());
		
		//JwtUtil jwtObj = new JwtUtil();
		
		String token = jwtUtil.generateToken(userDetail);//jwtObj.generateToken(userDetail);
		System.out.println("JWT: "+token);
		
		
		return ResponseEntity.ok(new JwtResponse(token));
	}	
}
