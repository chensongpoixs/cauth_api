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
public class SearchAuthInfo
{
    @NotEmpty(message = "客户名称")
    @Size(min = 1, max = 1024, message = "客户名称")
    private String name;

    @NotEmpty(message = "系统识别码")
    @Size(min = 1, max = 1024, message = "系统识别码")
    private String system_code;

    @NotEmpty(message = "照创建时间正序或倒序排列")
    @Size(min = 0, max = 1, message = "照创建时间正序或倒序排列")
    private Integer sort;
}
