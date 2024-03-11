package com.jpa.springboot_jpa.service.impl;

import com.jpa.springboot_jpa.mapper.UserRepository;
import com.jpa.springboot_jpa.pojo.User;
import com.jpa.springboot_jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository jpa;

    @Override
    public List<User> findAll() {
        return jpa.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return jpa.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        jpa.deleteById(id);
    }

    @Override
    public void updateById(User user) {
        jpa.saveAndFlush(user);
    }

    @Override
    public void save(User user) {
        jpa.save(user);
    }

    @Override
    public Page<User> pageUser(User user, int pageNumber, int pageSize) {


       // Integer id = user.getId();
       // String name = user.getUsername();
       // String remark = customer.getRemark();
       // String telephone = customer.getTelephone();
       // String address = customer.getAddress();
        //构造器 参数
        //创建匹配器，即如何使用查询条件，没有定义的作为=的方式查询，为空不作为条件
        ExampleMatcher matcher=ExampleMatcher.matching() //构建对象
                .withMatcher("username",ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("password",ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("token",ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("id"); //忽略属性：是否关注 因为是基本类型，需要忽略掉 创建时间和修改时间
        //初始化实例
        Example<User> example=Example.of(user,matcher);
        if (pageNumber<0)
        {
            pageNumber = 0;
        }
        if (pageSize<0)
        {
             pageSize = 2;
        }
        Page page = jpa.findAll(example, Pageable.ofSize(pageSize));
        return page;
    }
}
