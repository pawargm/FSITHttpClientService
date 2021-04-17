package mandinet.HttpClient.services.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import mandinet.HttpClient.Model.Customer;




@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

	public Customer findBycustomerName(String customerName);
	public Customer findBymail(String mail);
	public void deleteBymail(String mail);
}
