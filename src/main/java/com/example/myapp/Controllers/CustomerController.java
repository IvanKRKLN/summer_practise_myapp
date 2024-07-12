package com.example.myapp.Controllers;

import com.example.myapp.DB.model.Customer;
import com.example.myapp.DB.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(customer -> ResponseEntity.ok().body(customer))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setCustomerCode(customerDetails.getCustomerCode());
                    customer.setCustomerName(customerDetails.getCustomerName());
                    customer.setCustomerInn(customerDetails.getCustomerInn());
                    customer.setCustomerKpp(customerDetails.getCustomerKpp());
                    customer.setCustomerLegalAddress(customerDetails.getCustomerLegalAddress());
                    customer.setCustomerPostalAddress(customerDetails.getCustomerPostalAddress());
                    customer.setCustomerEmail(customerDetails.getCustomerEmail());
                    customer.setCustomerCodeMain(customerDetails.getCustomerCodeMain());
                    customer.setOrganization(customerDetails.getOrganization());
                    customer.setPerson(customerDetails.getPerson());
                    Customer updatedCustomer = customerRepository.save(customer);
                    return ResponseEntity.ok().body(updatedCustomer);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customerRepository.delete(customer);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
