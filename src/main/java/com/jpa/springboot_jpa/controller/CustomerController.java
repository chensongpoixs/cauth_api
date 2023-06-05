package com.jpa.springboot_jpa.controller;

import com.jpa.springboot_jpa.pojo.Customer;
import com.jpa.springboot_jpa.service.CustomerService;
import com.jpa.springboot_jpa.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 获取所有客户列表
     * @return
     */
    @ResponseBody
    @GetMapping("/customers")
    public ResultData findAllCustomers(){
        ResultData result = new ResultData();
        try {
            List<Customer> customers =  customerService.findAll();
            result.setData(customers);
            result.setMsg("请求成功");
            result.setStatus(200);
        }catch (Exception ex){
            result.setMsg("失败"+ex.getMessage());
            result.setStatus(0);
        }
        return result;
    }

    /**
     * 删除客户
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/delete")
    public ResultData deleteById(Integer id){
        ResultData result = new ResultData();
        try{
            customerService.deleteById(id);
            result.setStatus(200);
            result.setMsg("删除成功");
        }catch (Exception e){
            result.setStatus(0);
            result.setMsg("删除失败"+e.getMessage());
        }
        return result;
    }


    /**
     * 保存客户
     * @param customer
     * @return
     */
    @ResponseBody
    @PostMapping("/save")
    public ResultData save(Customer customer){
        ResultData result = new ResultData();
        try{
            customerService.save(customer);
            result.setStatus(200);
            result.setMsg("保存成功");
        }catch (Exception e){
            result.setStatus(0);
            result.setMsg("保存失败");
        }
        return result;
    }


    /**
     * 更新客户
     * @param customer
     * @return
     */
    @ResponseBody
    @PostMapping("/update")
    public ResultData update(Customer customer){
        ResultData result = new ResultData();
        try{
            customerService.updateById(customer);
            result.setStatus(200);
            result.setMsg("更新成功");
        }catch (Exception e){
            result.setStatus(0);
            result.setMsg("更新失败");
        }
        return result;
    }


    /**
     * 查询指定客户
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/byId")
    public ResultData byId(Integer id){
        ResultData result = new ResultData();
        try{
            Optional<Customer> customer= customerService.findById(id);
            result.setStatus(200);
            result.setMsg("请求成功");
            result.setData(customer);
        }catch (Exception e){
            result.setStatus(0);
            result.setMsg("请求失败");
        }
        return result;
    }

    /**
     * 根据特定条件分页检索
     * @param cus
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @PostMapping("/pageCus")
    public ResultData pageCustomers(Customer cus,Integer pageNum,Integer pageSize){
        ResultData result = new ResultData();
        try{
            Page page= customerService.pageCustomer(cus,pageNum,pageSize);
            result.setStatus(200);
            result.setMsg("请求成功");
            result.setData(page);
        }catch (Exception e){
            result.setStatus(0);
            result.setMsg("请求失败");
        }
        return result;
    }




}
