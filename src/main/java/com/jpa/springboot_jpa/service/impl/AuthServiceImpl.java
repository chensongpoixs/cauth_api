package com.jpa.springboot_jpa.service.impl;

import com.jpa.springboot_jpa.pojo.Auth;
import com.jpa.springboot_jpa.pojo.Customer;
import com.jpa.springboot_jpa.repository.AuthRepository;
import com.jpa.springboot_jpa.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl
{
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private CustomerRepository customerRepository;


    public void deleteById(Integer id) {
        authRepository.deleteById(id);
    }
    public Optional<Auth> findById(Integer id) {
        return authRepository.findById(id);
    }
    public Auth save(Auth auth) {
        return authRepository.save(auth);
    }



    public List<Auth> findAll(String name, String system_code, int sort)
    {

        if (!name.isEmpty())
        {
           if  (sort>0)
           {
               return authRepository.findByNameAsc(name,0, 100000);
           }
            return authRepository.findByNameDesc(name,0, 100000);
        }
        else if (!system_code.isEmpty())
        {
            if  (sort>0)
            {
                return authRepository.findSystemCodeByAsc(system_code,0, 100000);
            }
            return authRepository.findSystemCodeByDesc(system_code,0, 100000);
        }
        else if (sort > 0)
        {
            return authRepository.findByAsc(0, 100000);
        }

        return authRepository.findByDesc(0, 100000);
    }


//    public int  update(Auth auth)
//    {
//        return  authRepository.
//    }





}
