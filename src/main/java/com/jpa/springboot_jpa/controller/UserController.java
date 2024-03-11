package com.jpa.springboot_jpa.controller;

import com.jpa.springboot_jpa.pojo.User;
import com.jpa.springboot_jpa.service.UserService;
import com.jpa.springboot_jpa.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有客户列表
     * @return
     */
    @ResponseBody
    @GetMapping("/users")
    public ResultData findAllusers(){
        ResultData result = new ResultData();
        try {
            List<User> users =  userService.findAll();
            result.setData(users);
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
            userService.deleteById(id);
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
    public ResultData save(User user){
        ResultData result = new ResultData();
        try{
            userService.save(user);
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
    public ResultData update(User user){
        ResultData result = new ResultData();
        try{
            userService.updateById(user);
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
            Optional<User> user= userService.findById(id);
            result.setStatus(200);
            result.setMsg("请求成功");
            result.setData(user);
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
    @PostMapping("/pageUsers")
    public ResultData pageUsers(User user,Integer pageNum,Integer pageSize){
        ResultData result = new ResultData();
        try{
            Page page= userService.pageUser(user,pageNum,pageSize);
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
