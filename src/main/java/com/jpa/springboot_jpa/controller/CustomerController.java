package com.jpa.springboot_jpa.controller;

//import com.jpa.springboot_jpa.pojo.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jpa.springboot_jpa.dto.AddCustomerInfo;
import com.jpa.springboot_jpa.dto.SearchCustomerInfo;
import com.jpa.springboot_jpa.dto.UpdateCustomerInfo;
import com.jpa.springboot_jpa.pojo.Customer;
import com.jpa.springboot_jpa.pojo.User;
import com.jpa.springboot_jpa.service.CustomerService;
import com.jpa.springboot_jpa.dto.ResultData;
import com.jpa.springboot_jpa.service.impl.CustomerServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/v1", produces =  MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RestController
//@RequestMapping(API_V1)
@RequiredArgsConstructor
@Validated
//@RequestMapping(value = "/admin", produces = "application/json;charset=utf-8")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;



    @ApiOperation(value = "增加客户信息")
    @RequestMapping(value = "/add_customer" , method = RequestMethod.POST)
    // @RequestMapping(value =  "/v1/login/username={username}/password={password}" , method = RequestMethod.GET)
    public ResultData addCustomer( @ApiParam(value = "X-token", required = true)
                                       @RequestHeader(name = "X-token")
                                       final String token ,
                                   @ApiParam(value = " 增加客户信息", required = true)
                                       @Valid
                                   @RequestBody
                                  final AddCustomerInfo addCustomerInfo
    ){
                            ResultData result = new ResultData();
        try{
            Customer customer = new Customer();
            customer.setRegion(addCustomerInfo.getRegion());
            customer.setCompany_name(addCustomerInfo.getCompany_name());
            customer.setAttributable_sales(addCustomerInfo.getAttributable_sales());
            customer.setInternal_code(addCustomerInfo.getInternal_code());
            customer.setCreate_timestamp(System.currentTimeMillis()/1000);
            customer.setUsed_system_code(0);
             Customer customer1 =  customerService.save(customer);
            result.setStatus(200);
            result.setMsg("保存成功");
            result.setData(customer1);
        }catch (Exception e){
            result.setStatus(0);
            result.setMsg("保存失败");
        }
        return result;
    }



    //UpdateCustomerInfo
    @ApiOperation(value = "修改客户信息")
    @RequestMapping(value = "/update_customer" , method = RequestMethod.POST)
    // @RequestMapping(value =  "/v1/login/username={username}/password={password}" , method = RequestMethod.GET)
    public ResultData UpdateCustomer( @ApiParam(value = "X-token", required = true)
                                   @RequestHeader(name = "X-token")
                                   final String token ,
                                   @ApiParam(value = "修改客户信息", required = true)
                                   @Valid
                                   @RequestBody
                                   final UpdateCustomerInfo updateCustomerInfo
    ){
        ResultData result = new ResultData();
        try{
            Optional<Customer> customerdb = customerService.findById(updateCustomerInfo.getId());
            if(customerdb.isPresent() == false)
            {
                result.setStatus(610);
                result.setMsg("not find customer id ");
                return result;
            }
            if (!updateCustomerInfo.getRegion().isEmpty())
            {
                customerdb.get().setRegion(updateCustomerInfo.getRegion());
            }
            if (!updateCustomerInfo.getCompany_name().isEmpty())
            {
                customerdb.get().setCompany_name(updateCustomerInfo.getCompany_name());
            }
            if (!updateCustomerInfo.getAttributable_sales().isEmpty())
            {
                customerdb.get().setAttributable_sales(updateCustomerInfo.getAttributable_sales());
            }
            if (!updateCustomerInfo.getInternal_code().isEmpty())
            {
                customerdb.get().setInternal_code(updateCustomerInfo.getInternal_code());
            }
            Customer customer1 =  customerService.save(customerdb.get());
            result.setStatus(200);
            result.setMsg("保存成功");
            result.setData(customer1);
        }catch (Exception e){
            result.setStatus(0);
            result.setMsg("保存失败");
        }
        return result;
    }
//    @ResponseBody
//    @GetMapping("/byIffd")
//    public ResultData byIsdfdsfdsd(User user){
//        ResultData result = new ResultData();
//        try{
////            Optional<Customer> customer= customerService.findById(id);
//            result.setStatus(200);
//            result.setMsg("请求成功");
////            result.setData(customer);
//        }catch (Exception e){
//            result.setStatus(0);
//            result.setMsg("请求失败");
//        }
//        return result;
//    }


//    @GetMapping("/page")
////    @Operation(summary = "获得报警设置分页")
////    @PreAuthorize("@ss.hasPermission('syz:alarm-setting:query')")
//    public User getAlarmSettingPage(@Valid User pageVO) {
////        User pageResult = alarmSettingService.getAlarmSettingPage(pageVO);
//        return pageVO;
//    }
    @ApiOperation(value = "删除客户信息")
    @RequestMapping(value = "/delete_customer/id={id}" , method = RequestMethod.GET)
    // @RequestMapping(value =  "/v1/login/username={username}/password={password}" , method = RequestMethod.GET)
    public ResultData DeleteCustomer( @ApiParam(value = "X-token", required = true)
                                      @RequestHeader(name = "X-token")
                                      final String token ,
                                      @ApiParam(value = "删除客户信息", required = true)
                                      @PathVariable final int id
    ){
        ResultData result = new ResultData();
        try{
            Optional<Customer> customerdb = customerService.findById(id);
            if(customerdb.isPresent() == false)
            {
                result.setStatus(610);
                result.setMsg("not find customer id ");
                return result;
            }
            customerService.deleteById(id);

            result.setStatus(200);
            result.setMsg("保存成功");
            result.setData(customerdb);
        }catch (Exception e){
            result.setStatus(0);
            result.setMsg("保存失败");
        }
        return result;
    }


    @ApiOperation(value = "查询客户信息")
    @RequestMapping(value = "/search_customer" , method = RequestMethod.POST)
    // @RequestMapping(value =  "/v1/login/username={username}/password={password}" , method = RequestMethod.GET)
    public ResultData SearchCustomer( @ApiParam(value = "X-token", required = true)
                                      @RequestHeader(name = "X-token")
                                      final String token ,
                                      @ApiParam(value = "修改客户信息", required = true)
                                      @Valid
                                      @RequestBody
                                      final SearchCustomerInfo searchCustomerInfo
    ){
        ResultData result = new ResultData();
        try{
//            Pageable pageable = PageRequest.of(searchCustomerInfo.getPage(), searchCustomerInfo.getPage_size(), Sort.unsorted());

            if (searchCustomerInfo.getCompany_name().isEmpty())
            {
                List<Customer>  customerPage =   customerService.SearchAll(  searchCustomerInfo.getSort());

                log.info(customerPage.toString());
                Customerlist customerlist = new Customerlist(customerPage );
                result.setData(customerlist);
            }
            else
            {
              List<Customer>  customerPage =   customerService.searchByname(searchCustomerInfo.getCompany_name(),0, 1000000, searchCustomerInfo.getSort());// searchCustomerInfo.getPage(), searchCustomerInfo.getPage_size(), searchCustomerInfo.getSort());
                Customerlist customerlist = new Customerlist(customerPage/*, customerPage.size(),  searchCustomerInfo.getPage_size(), customerPage.size(), customerPage.size()*/);
                log.info(Customerlist.builder().toString());
                result.setData(customerlist);
            }

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
    private static final class Customerlist {

        @JsonProperty("customer")
        private final List<Customer> customers;



        /*
        "page_size": 10,
  "page_number": 0,
  "total_pages": 94,
  "total_elements": 0,
         */
//        @JsonProperty("page_size")
//        private  final int page_size ;
//        @JsonProperty("page_number")
//        private final int page_number;
//        @JsonProperty("total_pages")
//        private final int total_pages;
//        @JsonProperty("total_elements")
//        private final long total_elements;


//        @JsonProperty("customer")
//        private List<Customer> getCustomers () {return customers;}

    }

}
