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
public class UpdateCustomerInfo
{
    @NotEmpty(message = "序号")
    @Size(min = 1, max = 102400000, message = "序号")
    private Integer id;
    @NotEmpty(message = "公司名称")
    @Size(min = 1, max = 1024, message = "公司名称")
    private String company_name;

    @NotEmpty(message = "所在区域")
    @Size(min = 1, max = 255, message = "所在区域")
    private String region;


    @NotEmpty(message = "内部代号")
    @Size(min = 1, max = 255, message = "内部代号")
    private String internal_code;
    @NotEmpty(message = "归属销售")
    @Size(min = 1, max = 255, message = "归属销售")
    private String attributable_sales;
}
