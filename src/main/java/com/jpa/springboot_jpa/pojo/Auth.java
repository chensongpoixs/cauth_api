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
    @Column(name = "name", nullable = false, length = 1024)
    // 公司名称      如果某公司有正在使用的授权记录，则禁止删除
    private String name;
    @Basic
    @Column(name = "province", nullable = false, length = 1024)
    // 所在区域
    private String province;
    @Basic
    @Column(name = "app_type", nullable = false, length = 1024)
    // 内部代号
    private String appType;
    @Basic
    @Column(name = "contacts", nullable = false, length = 1024)
    // 归属销售
    private String contacts;

    @Basic
    @Column(name = "telephone", nullable = false , length = 1024)
    // 创建时间戳
    private String telephone;


    @Basic
    @Column(name = "sdk_interface_manager", nullable = false, length = 1024 )
    private String sdkInterfaceManager;
    @Basic
    @Column(name = "containers_num", nullable = false )
    private Integer containersNum;
    @Basic
    @Column(name = "video_fusion_num", nullable = false )
    private Integer video_fusionNum;
    @Basic
    @Column(name = "system_code", nullable = false, length = 1024 )
    private String systemCode;

    @Basic
    @Column(name = "register_timestamp", nullable = false )
    private long registerTimestamp;
    @Basic
    @Column(name = "expire_timestamp", nullable = false )
    private long expireTimestamp;
    @Basic
    @Column(name = "cycle", nullable = false )
    private long cycle;
    @Basic
    @Column(name = "auth_timestamp", nullable = false  )
    private long authTimestamp;
    @Basic
    @Column(name = "remarks", nullable = false , length = 1024 )
    private String remarks;
}
