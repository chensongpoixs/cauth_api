package com.jpa.springboot_jpa.service;

import com.jpa.springboot_jpa.pojo.Customer;
import com.jpa.springboot_jpa.pojo.Type;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CustomerTypeService {

    /**
     * 查询所有客户
     * @return
     */
    List<Type> findAll();


    /**
     * 查询客户
     * @param id
     * @return
     */
    Optional<Type> findById(Integer id);


    /**
     * 删除客户
     * @param id
     */

    void deleteById(Integer id);


    /**
     * 更新客户
     * @param Type
     */
    void updateById(Type customerType);


    /**
     * 保存客户
     * @param Type
     */
    void save(Type customerType);


    /**
     * 有条件的分页查询
     * @param Type
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Page<Customer> pageCustomer(Type customerType, int pageNumber, int pageSize);

}
