package guru.springfamework.controllers.v1;


import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static guru.springfamework.controllers.v1.AbstractRestController.asJsonString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerRepository customerRepository;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

    }

    @Test
    public void testListCategories() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(3l);
        customerDTO.setFirstName("test");
        customerDTO.setLastName("test");

        List<CustomerDTO> customerDTO2 = Arrays.asList(customerDTO);

        when(customerService.getAllCustomers()).thenReturn(customerDTO2);

        mockMvc.perform(get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(1)));

    }

    @Test
    public void getCustomersById() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("test");
        customerDTO.setLastName("test");
        customerDTO.setCustomer_url("/api/v1/customers/1");

        //List<CustomerDTO> customerDTO2 = Arrays.asList(customerDTO);

        //when(customerService.getCustomerById(3l)).thenReturn(customerDTO);

        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO);

        mockMvc.perform(get("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("test")));
    }

    @Test
    public void SaveCustomer() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("test");
        customerDTO.setLastName("test");
        customerDTO.setCustomer_url("/api/v1/customers/1");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName("test");
        returnDTO.setLastName("test");
        returnDTO.setCustomer_url("/api/v1/customers/1");

//      /*******Service*****/
/*
        Customer SavedCustomer = new Customer();
        SavedCustomer.setFirstName(customerDTO.getFirstName());
        SavedCustomer.setLastName(customerDTO.getLastName());
        SavedCustomer.setId(1l);
*/
//      /**************/

        when(customerService.createNewCustomer(customerDTO)).thenReturn(returnDTO);

        mockMvc.perform(post("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("test")))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
    }
}
