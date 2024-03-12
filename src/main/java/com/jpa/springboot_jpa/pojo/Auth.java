package com.jpa.springboot_jpa.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "t_auth_info")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Auth
{

    /*

    "id": 1,
    "name": "杭州大华",
    "province": "浙江",
    "app_type": "园区应用",
    "contacts": "陈松",
    "telephone": "15850773489",
    "sdk_interface_manager": "申晓亚",
    "containers_num": 3,
    "video_fusion_num": 1,
    "system_code": "2323-23432-23323",
    "register_timestamp": 232323323,
    "expire_timestamp": 232323323,
    "cycle": 23432,
    "auth_timestamp": 23432,
    "remarks": "备注啦 ~~~"

     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @Basic
    @Column(name = "name", nullable = true, length = 1024)
    // 公司名称      如果某公司有正在使用的授权记录，则禁止删除
    private String name;
    @Basic
    @Column(name = "province", nullable = true, length = 1024)
    // 所在区域
    private String province;
    @Basic
    @Column(name = "app_type", nullable = true, length = 1024)
    // 内部代号
    private String app_type;
    @Basic
    @Column(name = "contacts", nullable = true, length = 1024)
    // 归属销售
    private String contacts;

    @Basic
    @Column(name = "telephone", nullable = true , length = 1024)
    // 创建时间戳
    private String telephone;


    @Basic
    @Column(name = "sdk_interface_manager", nullable = true, length = 1024 )
    private String sdk_interface_manager;
    @Basic
    @Column(name = "containers_num", nullable = true )
    private Integer containers_num;
    @Basic
    @Column(name = "video_fusion_num", nullable = true )
    private Integer video_fusion_num;
    @Basic
    @Column(name = "system_code", nullable = true, length = 1024 )
    private String system_code;

    @Basic
    @Column(name = "register_timestamp", nullable = true )
    private long register_timestamp;
    @Basic
    @Column(name = "expire_timestamp", nullable = true )
    private long expire_timestamp;
    @Basic
    @Column(name = "cycle", nullable = true )
    private long cycle;
    @Basic
    @Column(name = "auth_timestamp", nullable = true  )
    private long auth_timestamp;
    @Basic
    @Column(name = "remarks", nullable = true , length = 1024 )
    private String remarks;
    @Basic
    @Column(name = "client_device", nullable = true , length = 1024 )
    private String client_device;
}
