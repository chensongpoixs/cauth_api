package com.jpa.springboot_jpa.repository;

import com.jpa.springboot_jpa.pojo.Auth;
import com.jpa.springboot_jpa.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthRepository extends JpaRepository<Auth,Integer>
{



    @Query(value ="select u.id, u.app_type, u.auth_timestamp, u.contacts, u.containers_num, u.cycle, u.expire_timestamp, u.name, u.province, u.register_timestamp, u.remarks, u.sdk_interface_manager, u.system_code, u.telephone,  u.video_fusion_num, u.client_device from t_auth_info u   where u.name  LIKE %?1%   order by u.register_timestamp asc limit ?2,?3 ",nativeQuery = true)
    public List<Auth> findByNameAsc(String name, Integer pageNumber, Integer pageSize);
    @Query(value ="select u.id, u.app_type, u.auth_timestamp, u.contacts, u.containers_num, u.cycle, u.expire_timestamp, u.name, u.province, u.register_timestamp, u.remarks, u.sdk_interface_manager, u.system_code, u.telephone,  u.video_fusion_num, u.client_device from t_auth_info u    where u.name  LIKE %?1%  order by u.register_timestamp desc limit ?2,?3 ",nativeQuery = true)
    public List<Auth> findByNameDesc(String name, Integer pageNumber,Integer pageSize);


    @Query(value ="select u.id, u.app_type, u.auth_timestamp, u.contacts, u.containers_num, u.cycle, u.expire_timestamp, u.name, u.province, u.register_timestamp, u.remarks, u.sdk_interface_manager, u.system_code, u.telephone,  u.video_fusion_num, u.client_device from t_auth_info u   where u.system_code  LIKE %?1%     order by u.register_timestamp asc limit ?2,?3 ",nativeQuery = true)
    public List<Auth> findSystemCodeByAsc(String System_code, Integer pageNumber, Integer pageSize);
    @Query(value ="select u.id, u.app_type, u.auth_timestamp, u.contacts, u.containers_num, u.cycle, u.expire_timestamp, u.name, u.province, u.register_timestamp, u.remarks, u.sdk_interface_manager, u.system_code, u.telephone,  u.video_fusion_num, u.client_device from t_auth_info u   where u.system_code  LIKE %?1%    order by u.register_timestamp desc limit ?2,?3 ",nativeQuery = true)
    public List<Auth> findSystemCodeByDesc(String System_code, Integer pageNumber,Integer pageSize);


    @Query(value ="select u.id, u.app_type, u.auth_timestamp, u.contacts, u.containers_num, u.cycle, u.expire_timestamp, u.name, u.province, u.register_timestamp, u.remarks, u.sdk_interface_manager, u.system_code, u.telephone,  u.video_fusion_num, u.client_device from t_auth_info u   where u.name  LIKE %?1%   or u.system_code LIKE %?2% order by u.register_timestamp asc limit ?3,?4 ",nativeQuery = true)
    public List<Auth> findByNameAndSystemAsc(String name, String system_code, Integer pageNumber, Integer pageSize);
    @Query(value ="select u.id, u.app_type, u.auth_timestamp, u.contacts, u.containers_num, u.cycle, u.expire_timestamp, u.name, u.province, u.register_timestamp, u.remarks, u.sdk_interface_manager, u.system_code, u.telephone,  u.video_fusion_num, u.client_device from t_auth_info u    where u.name  LIKE %?1%  or u.system_code LIKE %?2%  order by u.register_timestamp desc limit ?3,?4 ",nativeQuery = true)
    public List<Auth> findByNameAndSystemDesc(String name,String system_code, Integer pageNumber,Integer pageSize);


    @Query(value ="select u.id, u.app_type, u.auth_timestamp, u.contacts, u.containers_num, u.cycle, u.expire_timestamp, u.name, u.province, u.register_timestamp, u.remarks, u.sdk_interface_manager, u.system_code, u.telephone,  u.video_fusion_num, u.client_device from t_auth_info u      order by u.register_timestamp asc limit ?1,?2 ",nativeQuery = true)
    public List<Auth> findByAsc(Integer pageNumber, Integer pageSize);
    @Query(value ="select u.id, u.app_type, u.auth_timestamp, u.contacts, u.containers_num, u.cycle, u.expire_timestamp, u.name, u.province, u.register_timestamp, u.remarks, u.sdk_interface_manager, u.system_code, u.telephone,  u.video_fusion_num, u.client_device from t_auth_info u      order by u.register_timestamp desc limit ?1,?2 ",nativeQuery = true)
    public List<Auth> findByDesc( Integer pageNumber,Integer pageSize);


    @Query(value ="select u.id, u.app_type, u.auth_timestamp, u.contacts, u.containers_num, u.cycle, u.expire_timestamp, u.name, u.province, u.register_timestamp, u.remarks, u.sdk_interface_manager, u.system_code, u.telephone,  u.video_fusion_num, u.client_device from t_auth_info u    where u.name =?1 ",nativeQuery = true)
    public List<Auth> findByName( String name);
    @Query(value ="select u.id, u.app_type, u.auth_timestamp, u.contacts, u.containers_num, u.cycle, u.expire_timestamp, u.name, u.province, u.register_timestamp, u.remarks, u.sdk_interface_manager, u.system_code, u.telephone,  u.video_fusion_num, u.client_device from t_auth_info u    where u.system_code =?1 ",nativeQuery = true)
    public List<Auth> findBySystemCode( String SystemCode);
}
