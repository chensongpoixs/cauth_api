package com.jpa.springboot_jpa.service;

import com.jpa.springboot_jpa.pojo.Customer;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    /**
     * 查询所有客户
     * @return
     */
    List<Customer> findAll();


    /**
     * 查询客户
     * @param id
     * @return
     */
    Optional<Customer> findById(Integer id);


    /**
     * 删除客户
     * @param id
     */

    void deleteById(Integer id);


    /**
     * 更新客户
     * @param customer
     */
    void updateById(Customer customer);


    /**
     * 保存客户
     * @param customer
     */
    void save(Customer customer);


    /**
     * 有条件的分页查询
     * @param customer
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Page<Customer> pageCustomer(Customer customer,int pageNumber,int pageSize);

}
