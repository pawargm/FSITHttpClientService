package mandinet.HttpClient.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import mandinet.HttpClient.services.CustomerUserDetailService;
import mandinet.HttpClient.controller.JwtUtil;

@Component

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	
	@Autowired
	private CustomerUserDetailService customeUserDetailsService;
	
	@Autowired
	private JwtUtil jwtutil;
	
	@CrossOrigin(origins = "*")
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//get jwt token
		//Bearer 
		//Validate
		System.out.println("In do FilterInternal");
		System.out.println(request);
	
		String reqTokenHeader = request.getHeader("authorization");
		String username = null;
		String jwtToken = null;
		
		System.out.println("token " + reqTokenHeader);
		
		if(reqTokenHeader != null && reqTokenHeader.startsWith("Bearer ")) {
			
			jwtToken = reqTokenHeader.substring(7);
			
			try {
				username = this.jwtutil.getUsernameFromToken(jwtToken);
				
				
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("In exception"+e.toString());
				
			}
			
			UserDetails userDetails = this.customeUserDetailsService.loadUserByUsername(username);
			if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				
				UsernamePasswordAuthenticationToken usernamepasswordtoken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamepasswordtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamepasswordtoken);
				
			} else {
				System.out.println("Token Invalid");
			}
			
			
		}	 else {
			System.out.println("In else Token is null");
			//throw new ArithmeticException("/ by zero");
		} 
		filterChain.doFilter(request, response);
	}

	
	
}
