package com.jekirdek.crm_system.repository;

import com.jekirdek.crm_system.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
