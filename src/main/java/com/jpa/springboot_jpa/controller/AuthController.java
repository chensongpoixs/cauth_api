package com.jpa.springboot_jpa.controller;


import com.jpa.springboot_jpa.dto.*;
import com.jpa.springboot_jpa.pojo.Auth;
import com.jpa.springboot_jpa.pojo.Customer;
import com.jpa.springboot_jpa.service.impl.AuthServiceImpl;
import com.jpa.springboot_jpa.service.impl.CustomerServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
            auth.setSystemCode(system_code);
            auth.setRegisterTimestamp(System.currentTimeMillis()/1000);
            auth.setCycle(addAuthInfo.getCycle());
            auth.setName(addAuthInfo.getName());
            auth.setProvince(addAuthInfo.getProvince());
            auth.setAppType(addAuthInfo.getApp_type());
            auth.setContacts(addAuthInfo.getContacts());
            auth.setTelephone(addAuthInfo.getTelephone());
            auth.setSdkInterfaceManager(addAuthInfo.getSdk_interface_manager());
            auth.setContainersNum(addAuthInfo.getContainers_num());
            auth.setVideo_fusionNum(addAuthInfo.getVideo_fusion_num());
            auth.setRemarks(addAuthInfo.getRemarks());
            auth.setExpireTimestamp(addAuthInfo.getExpire_timestamp());
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
//            authdb.get().setId();
            authdb.get().setCycle(updateAuthInfo.getCycle());
            authdb.get().setName(updateAuthInfo.getName());
            authdb.get().setProvince(updateAuthInfo.getProvince());
            authdb.get().setAppType(updateAuthInfo.getApp_type());
            authdb.get().setContacts(updateAuthInfo.getContacts());
            authdb.get().setTelephone(updateAuthInfo.getTelephone());
            authdb.get().setSdkInterfaceManager(updateAuthInfo.getSdk_interface_manager());
            authdb.get().setContainersNum(updateAuthInfo.getContainers_num());
            authdb.get().setVideo_fusionNum(updateAuthInfo.getVideo_fusion_num());
            authdb.get().setRemarks(updateAuthInfo.getRemarks());
            authdb.get().setExpireTimestamp(updateAuthInfo.getExpire_timestamp());
            authdb.get().setCycle(updateAuthInfo.getCycle());
//            Customer customer1 =  customerService.save(customerdb.get());
            result.setStatus(200);
            result.setMsg("保存成功");
            result.setData(authServiceimpl.save(authdb.get()));
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
//            Pageable pageable = PageRequest.of(searchCustomerInfo.getPage(), searchCustomerInfo.getPage_size(), Sort.unsorted());

//            if (!searchAuthInfo.getName ().isEmpty())
//            {
//                List<Customer> customerPage =   customerService.SearchAll(  searchCustomerInfo.getSort());
//
//                log.info(customerPage.toString());
//                CustomerController.Customerlist customerlist = new CustomerController.Customerlist(customerPage );
//                result.setData(customerlist);
//            }
//            else if (! searchAuthInfo.getSystem_code().isEmpty())
//            {
//
//            }
//            else
//            {
//                List<Customer>  customerPage =   customerService.searchByname(searchCustomerInfo.getCompany_name(),0, 1000000, searchCustomerInfo.getSort());// searchCustomerInfo.getPage(), searchCustomerInfo.getPage_size(), searchCustomerInfo.getSort());
//                CustomerController.Customerlist customerlist = new CustomerController.Customerlist(customerPage/*, customerPage.size(),  searchCustomerInfo.getPage_size(), customerPage.size(), customerPage.size()*/);
//                log.info(CustomerController.Customerlist.builder().toString());
//                result.setData(customerlist);
//            }

            List<Auth> auths=  authServiceimpl.findAll(searchAuthInfo.getName(), searchAuthInfo.getSystem_code(), searchAuthInfo.getSort());

            result.setData(auths);
            result.setStatus(200);
            result.setMsg("保存成功");
//            result.setData(customerdb);
        }catch (Exception e){
            result.setStatus(0);
            result.setMsg("保存失败");
        }
        return result;
    }


}
