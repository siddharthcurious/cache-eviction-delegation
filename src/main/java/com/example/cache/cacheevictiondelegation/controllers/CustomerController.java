package com.example.cache.cacheevictiondelegation.controllers;

import com.example.cache.cacheevictiondelegation.models.Customer;
import com.example.cache.cacheevictiondelegation.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Cacheable(value = "customers", key = "#id")
    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable("id") String id){
        return customerRepository.findById(id).orElse(null);
    }

    @CachePut(value = "customers", key = "#customer.id")
    @PostMapping("/customer")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerRepository.saveAndFlush(customer);
    }

    @GetMapping("/customers/{corporateId}")
    public List<Customer> getCorporateCustomer(@PathVariable("corporateId") String corporateId){
        return customerRepository.findByCorporateIdentifier(corporateId);
    }

    @CacheEvict(value = "customer", key = "#id")
    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable("id") String id){
        customerRepository.deleteById(id);
    }
}
