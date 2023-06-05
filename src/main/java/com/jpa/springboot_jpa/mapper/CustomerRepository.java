package com.jpa.springboot_jpa.mapper;

import com.jpa.springboot_jpa.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}
