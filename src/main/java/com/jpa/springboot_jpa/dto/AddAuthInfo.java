package com.jpa.springboot_jpa.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddAuthInfo
{
    /*
    {
  "name": "杭州大华",
  "province": "浙江",
  "app_type": "园区应用",
  "contacts": "陈松",
  "telephone": "15850773489",
  "sdk_interface_manager": "申晓亚",
  "containers_num": 3,
  "video_fusion_num": 1,
  "remarks": "备注啦 ~~~",
  "expire_timestamp": 323243,
  "cycle": 23432
}
     */


    @NotEmpty(message = "客户名称")
    @Size(min = 1, max = 1024, message = "客户名称")
    private String name;

    @NotEmpty(message = "省份")
    @Size(min = 1, max = 255, message = "省份")
    private String province;


    @NotEmpty(message = "应用类型")
    @Size(min = 1, max = 255, message = "应用类型")
    private String app_type;
    @NotEmpty(message = "联系人")
    @Size(min = 1, max = 255, message = "联系人")
    private String contacts;



    @NotEmpty(message = "电话")
    @Size(min = 1, max = 255, message = "电话")
    private String telephone;

    @NotEmpty(message = "接口负责人")
    @Size(min = 1, max = 255, message = "接口负责人")
    private String sdk_interface_manager;

    @NotEmpty(message = "容器的数量")
    @Size(min = 1, max = 255, message = "容器的数量")
    private Integer containers_num;


    @NotEmpty(message = "视频融合数量")
    @Size(min = 1, max = 255, message = "视频融合数量")
    private Integer video_fusion_num;

    @NotEmpty(message = "备注")
    @Size(min = 1, max = 255, message = "备注")
    private String remarks;


    @NotEmpty(message = "有效时间")
    @Size(min = 1, max = 255, message = "有效时间")
    private long         expire_timestamp ;


    @NotEmpty(message = "认证周期")
    @Size(min = 1, max = 255, message = "认证周期")
    private long         cycle ;
}
