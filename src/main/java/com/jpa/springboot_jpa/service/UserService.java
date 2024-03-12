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


    Optional<User> findById(Integer id);

    Optional<User> findByUsername(String username);
    Optional<User> findByToken(String token);

    int updateByUsernameToken(String username, String token);
    int updateByUsernamePassword(String username, String password);
    void deleteById(Integer id);


    /**
     * 更新客户
     * @param user
     *
     */
    void updateById(User user);


    void save(User user);


    /**
     * 有条件的分页查询
     * @param user
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Page<User> pageUser(User user,int pageNumber,int pageSize);

}
