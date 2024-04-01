package com.jpa.springboot_jpa.controller;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.jpa.springboot_jpa.dto.*;
import com.jpa.springboot_jpa.pojo.Auth;
import com.jpa.springboot_jpa.pojo.Customer;
import com.jpa.springboot_jpa.service.impl.AuthServiceImpl;
import com.jpa.springboot_jpa.service.impl.CustomerServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping(value = "/api/v1", produces =  MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RestController
//@RequestMapping(API_V1)
@RequiredArgsConstructor
@Validated
public class AuthController
{

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private   AuthServiceImpl authServiceimpl;






    @ApiOperation(value = "增加授权用户信息")
    @RequestMapping(value = "/add_auth_user" , method = RequestMethod.POST)
    // @RequestMapping(value =  "/v1/login/username={username}/password={password}" , method = RequestMethod.GET)
    public ResultData addAuthUser(@ApiParam(value = "X-token", required = true)
                                  @RequestHeader(name = "X-token")
                                  final String token ,
                                  @ApiParam(value = " 增加授权用户信息", required = true)
                                  @Valid
                                  @RequestBody
                                  final AddAuthInfo addAuthInfo
    ){
        ResultData result = new ResultData();
        try{
            String system_code  =  UUID.randomUUID().toString();
            Auth auth = new Auth();
            auth.setSystem_code(system_code);
            auth.setRegister_timestamp(System.currentTimeMillis()/1000);
            auth.setCycle(addAuthInfo.getCycle());
            auth.setName(addAuthInfo.getName());
            auth.setProvince(addAuthInfo.getProvince());
            auth.setApp_type(addAuthInfo.getApp_type());
            auth.setContacts(addAuthInfo.getContacts());
            auth.setTelephone(addAuthInfo.getTelephone());
            auth.setSdk_interface_manager(addAuthInfo.getSdk_interface_manager());
            auth.setContainers_num(addAuthInfo.getContainers_num());
            auth.setVideo_fusion_num(addAuthInfo.getVideo_fusion_num());
            auth.setRemarks(addAuthInfo.getRemarks());
            auth.setExpire_timestamp(addAuthInfo.getExpire_timestamp());
            auth.setCycle(addAuthInfo.getCycle());

            ;
//            Customer customer = new Customer();
//            customer.setRegion(addCustomerInfo.getRegion());
//            customer.setCompany_name(addCustomerInfo.getCompany_name());
//            customer.setAttributable_sales(addCustomerInfo.getAttributable_sales());
//            customer.setInternal_code(addCustomerInfo.getInternal_code());
//            customer.setCreate_timestamp(System.currentTimeMillis()/1000);
//            customer.setUsed_system_code(0);
//            Customer customer1 =  customerService.save(customer);
            result.setStatus(200);
            result.setMsg("保存成功");

            result.setData(authServiceimpl.save(auth));
            List<Auth> auths = authServiceimpl.findByName(addAuthInfo.getName());
            long timestamps = 0;
            for (Auth auth_: auths)
            {
                if (timestamps < auth_.getExpire_timestamp())
                {
                    timestamps = auth_.getExpire_timestamp();
                }
            }
            customerService.updateAuthTimestamp(addAuthInfo.getName(), timestamps);
        }catch (Exception e){
            result.setStatus(0);
            result.setMsg("保存失败");
        }
        return result;
    }


    @ApiOperation(value = "修改授权信息")
    @RequestMapping(value = "/update_auth_info" , method = RequestMethod.POST)
    // @RequestMapping(value =  "/v1/login/username={username}/password={password}" , method = RequestMethod.GET)
    public ResultData UpdateAuthInfo( @ApiParam(value = "X-token", required = true)
                                      @RequestHeader(name = "X-token")
                                      final String token ,
                                      @ApiParam(value = "修改授权信息", required = true)
                                      @Valid
                                      @RequestBody
                                      final UpdateAuthInfo updateAuthInfo
    ){
        ResultData result = new ResultData();
        try{
            Optional<Auth> authdb = authServiceimpl.findById(updateAuthInfo.getId());
            if(authdb.isPresent() == false)
            {
                result.setStatus(610);
                result.setMsg("not find auth id ");
                return result;
            }
             Customer  customerList =  customerService.findByName(authdb.get().getName() );
            if (customerList== null)
            {
                result.setStatus(619);
                result.setMsg("not find customer info failed !!! ");
                return result;
            }
            customerService.updateAuthTimestamp(authdb.get().getName(), 0);
//            authdb.get().setId();
            authdb.get().setCycle(updateAuthInfo.getCycle());
            authdb.get().setName(updateAuthInfo.getName());

            authdb.get().setProvince(updateAuthInfo.getProvince());
            authdb.get().setApp_type(updateAuthInfo.getApp_type());
            authdb.get().setContacts(updateAuthInfo.getContacts());
            authdb.get().setTelephone(updateAuthInfo.getTelephone());
            authdb.get().setSdk_interface_manager(updateAuthInfo.getSdk_interface_manager());
            authdb.get().setContainers_num(updateAuthInfo.getContainers_num());
            authdb.get().setVideo_fusion_num(updateAuthInfo.getVideo_fusion_num());
            authdb.get().setRemarks(updateAuthInfo.getRemarks());
            authdb.get().setExpire_timestamp(updateAuthInfo.getExpire_timestamp());
//            customerService.updateAuthTimestamp(authdb.get().getName(), authdb.get().getExpire_timestamp());
            authdb.get().setCycle(updateAuthInfo.getCycle());
//            Customer customer1 =  customerService.save(customerdb.get());
            result.setStatus(200);
            result.setMsg("保存成功");
            result.setData(authServiceimpl.save(authdb.get()));
            List<Auth> auths = authServiceimpl.findByName(authdb.get().getName());
            long timestamps = 0;
            for (Auth auth: auths)
            {
                if (timestamps < auth.getExpire_timestamp())
                {
                    timestamps = auth.getExpire_timestamp();
                }
            }
            customerService.updateAuthTimestamp(authdb.get().getName(), timestamps);
        }catch (Exception e){
            result.setStatus(0);
            result.setMsg("保存失败");
        }
        return result;
    }


    @ApiOperation(value = "删除授权信息")
    @RequestMapping(value = "/delete_auth/id={id}" , method = RequestMethod.GET)
    // @RequestMapping(value =  "/v1/login/username={username}/password={password}" , method = RequestMethod.GET)
    public ResultData DeleteAuthInfo( @ApiParam(value = "X-token", required = true)
                                      @RequestHeader(name = "X-token")
                                      final String token ,
                                      @ApiParam(value = "删除授权信息", required = true)
                                      @PathVariable final int id
    ){
        ResultData result = new ResultData();
        try{
            Optional<Auth> customerdb = authServiceimpl.findById(id);
            if(customerdb.isPresent() == false)
            {
                result.setStatus(610);
                result.setMsg("not find Auth id ");
                return result;
            }
            authServiceimpl.deleteById(id);

            result.setStatus(200);
            result.setMsg("保存成功");
            result.setData(customerdb);
        }catch (Exception e){
            result.setStatus(0);
            result.setMsg("保存失败");
        }
        return result;
    }

    @ApiOperation(value = "查询授权信息")
    @RequestMapping(value = "/search_authinfo" , method = RequestMethod.POST)
    // @RequestMapping(value =  "/v1/login/username={username}/password={password}" , method = RequestMethod.GET)
    public ResultData SearchAuthInfo( @ApiParam(value = "X-token", required = true)
                                      @RequestHeader(name = "X-token")
                                      final String token ,
                                      @ApiParam(value = "查询授权信息", required = true)
                                      @Valid
                                      @RequestBody
                                      final SearchAuthInfo searchAuthInfo
    ){
        ResultData result = new ResultData();
        try{

            List<Auth> auths=  authServiceimpl.findAll(searchAuthInfo.getName(), searchAuthInfo.getAuth_expire(), searchAuthInfo.getSystem_code(), searchAuthInfo.getSort());
            int  total_pages = (auths.size() ) / searchAuthInfo.getPage_size() + ((auths.size() % searchAuthInfo.getPage_size() == 0?0: 1));
            int  total_elements = auths.size();
            int start_index = (searchAuthInfo.getPage() ) * searchAuthInfo.getPage_size();
            List<Auth> new_auths = new ArrayList<>();
            int count = 0;
            for (Auth auth :auths)
            {
                ++count;
                if (count > start_index)
                {
                    new_auths.add(auth);
                }
                if (new_auths.size()>= searchAuthInfo.getPage_size())
                {
                    break;
                }
            }
            log.info(new_auths.toString());
            Authlist authlist = new Authlist( new_auths, searchAuthInfo.getPage(), searchAuthInfo.getPage_size(), total_pages, total_elements);
            result.setData(authlist);
            result.setStatus(200);
            result.setMsg("保存成功");
//            result.setData(customerdb);
        }catch (Exception e){
            result.setStatus(0);
            result.setMsg("保存失败");
        }
        return result;
    }
    @Data
    @ToString
    @RequiredArgsConstructor
    @Builder
    private static final class Authlist {

        @JsonProperty("auths")
        private final List<Auth> auths;



        /*
        "page_size": 10,
  "page_number": 0,
  "total_pages": 94,
  "total_elements": 0,
         */
        @JsonProperty("page_size")
        private  final int page_size ;
        @JsonProperty("page_number")
        private final int page_number;
        @JsonProperty("total_pages")
        private final int total_pages;
        @JsonProperty("total_elements")
        private final long total_elements;


//        @JsonProperty("customer")
//        private List<Customer> getCustomers () {return customers;}

    }


}
