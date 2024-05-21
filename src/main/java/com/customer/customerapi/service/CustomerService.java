package com.customer.customerapi.service;


import com.customer.customerapi.model.Customer;
import com.customer.customerapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public ResponseEntity<Customer> getCustomerById(Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        return ResponseEntity.ok(customer.get());
    }

    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public ResponseEntity<Customer> updateCustomer(Long id, Customer customerDetails){
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()){
            Customer updateCustomer = customer.get();
            updateCustomer.setName(customerDetails.getName());
            updateCustomer.setEmail(customerDetails.getEmail());
            updateCustomer.setAddress(customerDetails.getAddress());
            return ResponseEntity.ok(customerRepository.save(updateCustomer));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteCustomer(Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()){
            customerRepository.delete(customer.get());
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
