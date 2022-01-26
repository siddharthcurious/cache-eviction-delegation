package com.example.cache.cacheevictiondelegation.controllers;

import com.example.cache.cacheevictiondelegation.cache.CustomerCacheService;
import com.example.cache.cacheevictiondelegation.models.Corporate;
import com.example.cache.cacheevictiondelegation.models.Customer;
import com.example.cache.cacheevictiondelegation.repositories.CorporateRepository;
import com.example.cache.cacheevictiondelegation.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@RestController
public class CorporateController {

    @Autowired
    CorporateRepository corporateRepository;
    @Autowired
    CustomerCacheService customerCacheService;

    @Cacheable(value = "corporates", key = "#id")
    @GetMapping("/corporate/{id}")
    public Corporate getCorporate(@PathVariable("id") String id) throws InterruptedException {
        return corporateRepository.findById(id).orElse(null);
    }

    @Cacheable("corporates")
    @GetMapping("/corporates")
    public List<Corporate> getCorporatesList(){
        return corporateRepository.findAll();
    }

    @CachePut(value = "corporates", key = "#corporate.id")
    @PostMapping("/corporate")
    public Corporate createCorporate(@RequestBody Corporate corporate){
        return corporateRepository.saveAndFlush(corporate);
    }

    @CacheEvict(value = "corporates", key = "#id")
    @DeleteMapping("/corporate/{id}")
    public void deleteCorporate(@PathVariable("id") String id){

        Corporate corporate = corporateRepository.findById(id).orElse(null);

        if(corporate != null){
            corporateRepository.deleteById(id);
            String corporateId = corporate.getCorporateIdentifier();
            customerCacheService.evictCustomersOnCorporateDelete(corporateId);
        }
    }
}
