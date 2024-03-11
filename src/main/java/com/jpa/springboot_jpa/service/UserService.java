package com.jpa.springboot_jpa.service;

import com.jpa.springboot_jpa.pojo.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     * 查询所有客户
     * @return
     */
    List<User> findAll();


    /**
     * 查询客户
     * @param id
     * @return
     */
    Optional<User> findById(Integer id);


    /**
     * 删除客户
     * @param id
     */

    void deleteById(Integer id);


    /**
     * 更新客户
     * @param customer
     */
    void updateById(User user);


    /**
     * 保存客户
     * @param customer
     */
    void save(User user);


    /**
     * 有条件的分页查询
     * @param customer
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Page<User> pageUser(User user,int pageNumber,int pageSize);

}
