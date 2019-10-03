package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 9/24/17.
 */
@Component
public class Bootstrap implements CommandLineRunner{

    private CategoryRepository categoryRespository;

    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRespository, CustomerRepository customerRepository) {
        this.categoryRespository = categoryRespository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCategories();

        loadCustomer();


        System.out.println("Data Loaded = " + categoryRespository.count() + "Customers" + categoryRespository.count() );

    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRespository.save(fruits);
        categoryRespository.save(dried);
        categoryRespository.save(fresh);
        categoryRespository.save(exotic);
        categoryRespository.save(nuts);
    }

    private void loadCustomer() {
        Customer c1 = new Customer();
        c1.setId(1l);
        c1.setFirstName("Juan");
        c1.setLastName("Perez");

        Customer c2 = new Customer();
        c2.setId(2l);
        c2.setFirstName("Michael");
        c2.setLastName("Jordan");

        customerRepository.save(c1);
        customerRepository.save(c2);
    }
}
