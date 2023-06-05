package com.jpa.springboot_jpa.service.impl;

import com.jpa.springboot_jpa.mapper.CustomerRepository;
import com.jpa.springboot_jpa.pojo.Customer;
import com.jpa.springboot_jpa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository jpa;

    @Override
    public List<Customer> findAll() {
        return jpa.findAll();
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return jpa.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        jpa.deleteById(id);
    }

    @Override
    public void updateById(Customer customer) {
        jpa.saveAndFlush(customer);
    }

    @Override
    public void save(Customer customer) {
        jpa.save(customer);
    }

    @Override
    public Page<Customer> pageCustomer(Customer customer, int pageNumber, int pageSize) {


        Integer id = customer.getId();
        String name = customer.getName();
        String remark = customer.getRemark();
        String telephone = customer.getTelephone();
        String address = customer.getAddress();
        //构造器 参数
        //创建匹配器，即如何使用查询条件，没有定义的作为=的方式查询，为空不作为条件
        ExampleMatcher matcher=ExampleMatcher.matching() //构建对象
                .withMatcher("name",ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("remark",ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("telephone",ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("address",ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("id"); //忽略属性：是否关注 因为是基本类型，需要忽略掉 创建时间和修改时间
        //初始化实例
        Example<Customer> example=Example.of(customer,matcher);
        if (pageNumber<0)pageNumber = 0;
        if (pageSize<0) pageSize = 2;
        Page page = jpa.findAll(example, Pageable.ofSize(pageSize));
        return page;
    }
}
