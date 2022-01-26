package com.example.cache.cacheevictiondelegation.cache;

import com.example.cache.cacheevictiondelegation.models.Customer;
import com.example.cache.cacheevictiondelegation.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerCacheService {

    @Autowired
    CacheManager cacheManager;
    @Autowired
    CustomerRepository customerRepository;

    private final String cacheName = "customers";

    @Async
    public void evictCustomer(String id){
        cacheManager.getCache(cacheName).evictIfPresent(id);
    }

    @Async
    public void evictCustomers(List<Customer> customerList){
        for(Customer customer: customerList){
            cacheManager.getCache(cacheName).evictIfPresent(customer.getId());
        }
    }

    @Async
    public void evictCustomersOnCorporateDelete(String corporateId){
        for(Customer customer: customerRepository.findByCorporateIdentifier(corporateId)){
            cacheManager.getCache(cacheName).evictIfPresent(customer.getId());
        }
    }
}
