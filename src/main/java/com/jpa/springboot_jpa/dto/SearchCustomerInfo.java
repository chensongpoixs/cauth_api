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
public class SearchCustomerInfo
{

    @NotEmpty(message = "公司名称")
    @Size(min = 1, max = 1024, message = "公司名称")
    private String company_name;

    @NotEmpty(message = "照创建时间正序或倒序排列")
    @Size(min = 0, max = 1, message = "照创建时间正序或倒序排列")
    private Integer sort;
//    @NotEmpty(message = "page")
//    @Size(min = 1, max = 1023333333, message = "page")
//    private Integer page;
//    @NotEmpty(message = "page_size")
//    @Size(min = 1, max = 1023333333, message = "page_size")
//    private Integer page_size;
}
