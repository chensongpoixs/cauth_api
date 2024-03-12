package com.jpa.springboot_jpa.repository;

import com.jpa.springboot_jpa.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>
{
    //我们按照springboot jpa文档的范例和平常的习惯经常会这么写
    @Query(value ="select u.id, u.username, u.password, u.token from t_admin u where u.username=:username ",nativeQuery = true)
    public Optional<User> findByUserName(@Param("username")String username);
    @Query(value ="select u.id, u.username, u.password, u.token from t_admin u where u.token=:token ",nativeQuery = true)
    public Optional<User> findByToken(@Param("token")String token);

    @Modifying
    @Query(value="update t_admin o set o.token=:token where o.username=:username",nativeQuery = true)
    public int updateUsernameToken(@Param("username") String username, @Param("token") String token);

    @Modifying
    @Query(value="update t_admin o set o.password=:password where o.username=:username",nativeQuery = true)
    public int updateUsernamePassword(@Param("username") String username, @Param("password") String password);




}