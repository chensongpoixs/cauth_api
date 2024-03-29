package com.jpa.springboot_jpa.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import java.util.List;

@Entity(name = "t_customer")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @Basic
    @Column(name = "company_name", nullable = true, length = 1024)
    // 公司名称      如果某公司有正在使用的授权记录，则禁止删除
    private String company_name;
    @Basic
    @Column(name = "region", nullable = true, length = 1024)
    // 所在区域
    private String region;
    @Basic
    @Column(name = "internal_code", nullable = true, length = 1024)
    // 内部代号
    private String internal_code;
    @Basic
    @Column(name = "attributable_sales", nullable = true, length = 1024)
    // 归属销售
    private String attributable_sales;

    @Basic
    @Column(name = "create_timestamp", nullable = true )
    // 创建时间戳
    private long create_timestamp;


    @Basic
    @Column(name = "used_system_code", nullable = true )
    // 是否有使用的
    private long used_system_code;



    @Basic
    @Column(name = "list_regions", nullable = true )
//    // 是否有使用的
//    private List<String> list_regions;
//    @Column(name="arguments")
    @ElementCollection(targetClass=Integer.class)
    private List<Integer> list_regions;
}
