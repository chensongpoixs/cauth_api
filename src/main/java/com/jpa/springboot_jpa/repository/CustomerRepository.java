package com.jpa.springboot_jpa.repository;

import com.jpa.springboot_jpa.pojo.Customer;
import com.jpa.springboot_jpa.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {


//    @Modifying
//    @Query(value="update UserModel o set o.name=:newName where o.name like %:nn")
//    public int findByUuidOrAge(@Param("nn") String name,@Param("newName") String newName);
    @Query(value ="select id, attributable_sales, company_name, create_timestamp, internal_code, region, used_system_code from t_customer  where company_name   LIKE CONCAT('%',?1,'%')  order by create_timestamp asc  limit ?2,?3",nativeQuery = true)
    public List<Customer> findByCompanyNameAsc(String name, Integer pageNumber, Integer pageSize);
    @Query(value ="select id, attributable_sales, company_name, create_timestamp, internal_code, region, used_system_code from t_customer where company_name LIKE CONCAT('%',?1,'%')  order by create_timestamp desc  limit ?2,?3",nativeQuery = true)
    public List<Customer> findByCompanyNameDesc(String name, Integer pageNumber,Integer pageSize);


    @Query(value ="select u.id, u.attributable_sales, u.company_name, u.create_timestamp, u.internal_code, u.region, u.used_system_code from t_customer u     order by u.create_timestamp asc limit ?1,?2 ",nativeQuery = true)
    public List<Customer> findByAsc(Integer pageNumber,Integer pageSize);
    @Query(value ="select u.id, u.attributable_sales, u.company_name, u.create_timestamp, u.internal_code, u.region, u.used_system_code from t_customer u     order by u.create_timestamp desc limit ?1,?2 ",nativeQuery = true)
    public List<Customer> findByDesc( Integer pageNumber,Integer pageSize);
}
