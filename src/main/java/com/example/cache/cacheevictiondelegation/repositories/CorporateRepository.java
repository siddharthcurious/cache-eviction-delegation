package com.example.cache.cacheevictiondelegation.repositories;

import com.example.cache.cacheevictiondelegation.models.Corporate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporateRepository extends JpaRepository<Corporate, String> {
}
