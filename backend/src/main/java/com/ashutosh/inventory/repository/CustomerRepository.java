package com.ashutosh.inventory.repository;

import com.ashutosh.inventory.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPhone(String phone);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByGstNumber(String gstNumber);

    boolean existsByPhone(String phone);

    boolean existsByEmail(String email);

    boolean existsByGstNumber(String gstNumber);
}
