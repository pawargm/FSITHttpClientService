package mandinet.HttpClient;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
@EnableEurekaClient
public class App 
{
    public static void main( String[] args )
    {
       SpringApplication.run(App.class, args);
    }
    
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate()
	{
		RestTemplate restTemplate= new RestTemplate();
		return restTemplate;
	}
	
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedHeaders("*").allowedOrigins("*").allowedMethods("*").allowCredentials(true);
			}
		};
	}
	
  @Bean
 	public FilterRegistrationBean corsFilterRegistration() {
 		FilterRegistrationBean reg = new FilterRegistrationBean(new CORSFilter());
 		reg.setName("CORS Filter");
 		reg.addUrlPatterns("/*");
 		reg.setOrder(1);
 		return reg;	
 	}
 
	
	  @Bean
	    public LocaleResolver localeResolver()
	    {
	    	SessionLocaleResolver localeResolver=new SessionLocaleResolver();
	    	localeResolver.setDefaultLocale(Locale.US);
	    	return localeResolver;
	    	
	    }

	    @Bean
	    public ResourceBundleMessageSource messageSource()
	    {
	    	ResourceBundleMessageSource bundleSource= new ResourceBundleMessageSource();
	    	bundleSource.setBasename("message");
	    	return bundleSource;
	    }
}

