package com.jpa.springboot_jpa.service.impl;

import com.jpa.springboot_jpa.mapper.CustomerTypeRepository;
import com.jpa.springboot_jpa.pojo.Customer;
import com.jpa.springboot_jpa.pojo.Type;
import com.jpa.springboot_jpa.service.CustomerService;
import com.jpa.springboot_jpa.service.CustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerTypeServiceImpl implements CustomerTypeService {

    @Autowired
    private CustomerTypeRepository customerTypeJPA;

    @Override
    public List<Type> findAll() {
        return customerTypeJPA.findAll();
    }

    @Override
    public Optional<Type> findById(Integer id) {
        return customerTypeJPA.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        customerTypeJPA.deleteById(id);
    }

    @Override
    public void updateById(Type customerType) {
       customerTypeJPA.save(customerType);
    }

    @Override
    public void save(Type customerType) {
        customerTypeJPA.save(customerType);
    }

    @Override
    public Page<Customer> pageCustomer(Type customerType, int pageNumber, int pageSize) {
        Integer id = customerType.getId();
        String name = customerType.getType_name();
        //构造器 参数
        //创建匹配器，即如何使用查询条件，没有定义的作为=的方式查询，为空不作为条件
        ExampleMatcher matcher=ExampleMatcher.matching() //构建对象
                .withMatcher("type_name",ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("id"); //忽略属性：是否关注 因为是基本类型，需要忽略掉 创建时间和修改时间
        //初始化实例
        Example<Type> example=Example.of(customerType,matcher);
        if (pageNumber<0)pageNumber = 0;
        if (pageSize<0) pageSize = 2;
        Page page = customerTypeJPA.findAll(example, Pageable.ofSize(pageSize));
        return page;
    }
}
