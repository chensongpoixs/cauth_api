package com.jpa.springboot_jpa.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jpa.springboot_jpa.dto.ResultData;
import com.jpa.springboot_jpa.pojo.Auth;
import com.jpa.springboot_jpa.service.impl.AuthServiceImpl;
import com.jpa.springboot_jpa.service.impl.CustomerServiceImpl;
import com.jpa.springboot_jpa.util.AuthBaseInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping(value = "/auth/v1", produces =  MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RestController
//@RequestMapping(API_V1)
@RequiredArgsConstructor
@Validated
public class RenderServerController
{

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private AuthServiceImpl authServiceimpl;

    @ApiOperation(value = "查询授权用户信息")
    @RequestMapping(value = "/net_auth/data={data}" , method = RequestMethod.GET)
    // @RequestMapping(value =  "/v1/login/username={username}/password={password}" , method = RequestMethod.GET)
    public ResultData NetAuthInfo(@ApiParam(value = "删除授权信息", required = true)
                                       @PathVariable final String data
    ){
        ResultData result = new ResultData();
        try{

            log.info("String = > " + data);
            byte[] json_base64 = Base64.getDecoder().decode(data);


            log.info("json_data64 = "+ json_base64);

//            JSONObject jsonpObject = new JSONObject();
//            JSON.parseObject(jsonpObject.get("data").toString(), DeviceInfo.class);

            AuthBaseInfo authBaseInfo=  JSON.parseObject(json_base64, AuthBaseInfo.class);
            log.info("not find system code = " + authBaseInfo.getSystemCode() + ", client_device = " + authBaseInfo.getClientDevice());

            if (null != authBaseInfo)
            {
                List<Auth> auths= authServiceimpl.findSystemCode(authBaseInfo.getSystemCode());
                if (auths.isEmpty())
                {
                    log.warn("not find system code = " + authBaseInfo.getSystemCode() + ", client_device = " + authBaseInfo.getClientDevice());
                    result.setStatus(203);
                    result.setMsg("not find system code ");
                    return result;
                }
                if (auths.get(0 ).getClient_device().isEmpty())
                {
                    auths.get(0).setClient_device(authBaseInfo.getClientDevice());
                }
                if (!auths.get(0 ).getClient_device().equals(authBaseInfo.getClientDevice()))
                {
                    log.warn("  system code = " + authBaseInfo.getSystemCode() + ", client_device = " + authBaseInfo.getClientDevice() + ", old device = " + auths.get(0 ).getClient_device());
                    result.setStatus(203);
                    result.setMsg("not find  device failed ！！！ ");
                    return result;
                }
                auths.get(0).setAuth_timestamp(System.currentTimeMillis()/1000);
                authServiceimpl.save(auths.get(0));
                result.setStatus(200);
                result.setMsg("searche ok ");

                result.setData(Base64.getEncoder().encode(JSON.toJSON(auths.get(0)).toString().getBytes()));
                return result;
            }
            result.setStatus(203);
            result.setMsg("authBaseInfo == null failed !!!");
            ;
//            Customer customer = new Customer();
//            customer.setRegion(addCustomerInfo.getRegion());
//            customer.setCompany_name(addCustomerInfo.getCompany_name());
//            customer.setAttributable_sales(addCustomerInfo.getAttributable_sales());
//            customer.setInternal_code(addCustomerInfo.getInternal_code());
//            customer.setCreate_timestamp(System.currentTimeMillis()/1000);
//            customer.setUsed_system_code(0);
//            Customer customer1 =  customerService.save(customer);


//            result.setData(authServiceimpl.save(auth));
//            List<Auth> auths = authServiceimpl.findByName(addAuthInfo.getName());
//            long timestamps = 0;
//            for (Auth auth_: auths)
//            {
//                if (timestamps < auth_.getExpire_timestamp())
//                {
//                    timestamps = auth_.getExpire_timestamp();
//                }
//            }
//            customerService.updateAuthTimestamp(addAuthInfo.getName(), timestamps);
        }catch (Exception e){
            result.setStatus(0);
            result.setMsg("保存失败");
        }
        return result;
    }
}
