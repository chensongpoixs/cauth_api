package com.jpa.springboot_jpa.mapper;

import com.jpa.springboot_jpa.pojo.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTypeRepository extends JpaRepository<Type,Integer> {
}
