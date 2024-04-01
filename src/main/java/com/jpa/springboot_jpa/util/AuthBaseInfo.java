package com.jpa.springboot_jpa.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AuthBaseInfo
{

    private String systemCode;

    private String clientDevice;
}
