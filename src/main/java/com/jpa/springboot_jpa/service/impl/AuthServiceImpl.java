package com.jpa.springboot_jpa.service.impl;

import com.jpa.springboot_jpa.pojo.Auth;
import com.jpa.springboot_jpa.pojo.Customer;
import com.jpa.springboot_jpa.repository.AuthRepository;
import com.jpa.springboot_jpa.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
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


    public List<Auth> findByName(String name)
    {
        return authRepository.findByName(name);
    }


    public List<Auth> findAll(String name, int expire_auth, String system_code, int sort)
    {

        List<Auth> auths = null;


        if(!name.isEmpty() && !system_code.isEmpty())
        {
            log.info("name === " + name + ", system_code = " + system_code);
            if  (sort>0)
            {

                auths = authRepository.findByNameAndSystemAsc(name, system_code, 0, 100000);
            }

            else
            {
                auths = authRepository.findByNameAndSystemDesc(name, system_code, 0, 100000);
            }
        }
        else if (!name.isEmpty())
        {
            log.info("name === " + name + ", system_code = " + system_code);
           if  (sort>0)
           {

               auths = authRepository.findByNameAsc(name,0, 100000);
           }

            else
           {
               auths = authRepository.findByNameDesc(name,0, 100000);
           }
        }
        else if (!system_code.isEmpty())
        {
            if  (sort>0)
            {
                auths = authRepository.findSystemCodeByAsc(system_code,0, 100000);
            }
            else
            {
                auths = authRepository.findSystemCodeByDesc(system_code,0, 100000);
            }
        }
        else if (sort > 0)
        {
            auths = authRepository.findByAsc(0, 100000);
        }

        else
        {
            auths = authRepository.findByDesc(0, 100000);
        }
        log.info("--auth_. db >>");
        if(auths.isEmpty())
        {
            return auths;
        }
        List<Auth> authList = new ArrayList<>();
        if (expire_auth == 2)
        {
            authList = auths;
        }
        else if (expire_auth == 1)
        {
            long timestamp = System.currentTimeMillis() / 1000;
            for (Auth auth : auths)
            {
                if (auth.getExpire_timestamp() < timestamp)
                {
                    authList.add(auth);
                }
            }
        }
        else
        {
            long timestamp = System.currentTimeMillis() / 1000;
            for (Auth auth : auths)
            {
                if (auth.getExpire_timestamp() > timestamp)
                {
                    authList.add(auth);
                }
            }
        }
        return authList;
    }


    public List<Auth> findSystemCode(String SystemCode)
    {
        return authRepository.findBySystemCode(SystemCode);
    }

    @Transactional
    public int UpdateCompayName(String commpay_name1, String commpay_name2)
    {
        return authRepository.UpdateCompayName(commpay_name1, commpay_name2);
    }
//    public int  update(Auth auth)
//    {
//        return  authRepository.
//    }





}
