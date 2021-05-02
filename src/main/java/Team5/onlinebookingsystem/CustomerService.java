package Team5.onlinebookingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {
    @Autowired
    protected CustomerRepository repo;


    public List<Customer> listAll() {
        return repo.findAll();
    }

    public void save(Customer customer) {
        repo.save(customer);
    }

    public Customer get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }

}
