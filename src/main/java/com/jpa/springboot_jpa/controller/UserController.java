package com.jpa.springboot_jpa.controller;

import com.jpa.springboot_jpa.dto.UserDto;
//import com.jpa.springboot_jpa.mapper.UserMapper;
import com.jpa.springboot_jpa.pojo.User;
import com.jpa.springboot_jpa.service.UserService;
import com.jpa.springboot_jpa.dto.ResultData;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping(value = "/admin", produces = "application/json;charset=utf-8")
@Slf4j
@RestController
//@RequestMapping(API_V1)
@RequiredArgsConstructor
@Validated
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private UserMapper userMapper;
    /**
     * 获取所有客户列表
     * @return
     */
//    @ResponseBody
//    @GetMapping("/users")
//    public ResultData findAllusers(){
//        ResultData result = new ResultData();
//        try {
//            List<User> users =  userService.findAll();
//            result.setData(users);
//            result.setMsg("请求成功");
//            result.setStatus(200);
//        }catch (Exception ex){
//            result.setMsg("失败"+ex.getMessage());
//            result.setStatus(0);
//        }
//        return result;
//    }


//    @ResponseBody
//    @PostMapping("/delete")
//    public ResultData deleteById(Integer id){
//        ResultData result = new ResultData();
//        try{
//            userService.deleteById(id);
//            result.setStatus(200);
//            result.setMsg("删除成功");
//        }catch (Exception e){
//            result.setStatus(0);
//            result.setMsg("删除失败"+e.getMessage());
//        }
//        return result;
//    }



//    @ResponseBody
//    @PostMapping("/save")
//    public ResultData save(User user){
//        ResultData result = new ResultData();
//        try{
//            userService.save(user);
//            result.setStatus(200);
//            result.setMsg("保存成功");
//        }catch (Exception e){
//            result.setStatus(0);
//            result.setMsg("保存失败");
//        }
//        return result;
//    }



//    @ResponseBody
//    @PostMapping("/update")
//    public ResultData update(User user){
//        ResultData result = new ResultData();
//        try{
//            userService.updateById(user);
//            result.setStatus(200);
//            result.setMsg("更新成功");
//        }catch (Exception e){
//            result.setStatus(0);
//            result.setMsg("更新失败");
//        }
//        return result;
//    }



//    @ApiOperation("获取用户信息")
////    @ResponseBody
////    @PostMapping(value = "/findUserId")
//    @RequestMapping(value =  "/v1/findUserId/id={id}" , method = RequestMethod.GET)
//    public ResultData findUserId(@ApiParam("参数id") @PathVariable Integer id )
//    {
//        log.info("id = " + id);
//        ResultData result = new ResultData();
//        try{
//            Optional<User> user= userService.findById(id);
//            result.setStatus(200);
//            result.setMsg("请求成功");
//            result.setData(user);
//        }catch (Exception e){
//            result.setStatus(0);
//            result.setMsg("请求失败");
//        }
//        return result;
//    }

    @ApiOperation("用户登录")
    @RequestMapping(value =  "/v1/login/username={username}/password={password}" , method = RequestMethod.GET)
    public ResultData findUserId(@ApiParam("用户名") @PathVariable String username,
                                 @ApiParam("密码") @PathVariable String password)
    {
        log.info("[username = " + username + "][ password = " + password+ "]");
        ResultData result = new ResultData();
        try{
            Optional<User> user= userService.findByUsername(username);

//            if (user.map())
            if (user.isPresent() == false)
            {
                result.setStatus(600);
                result.setMsg("not find username !!!");
                return result;
            }
            if (!user.get().getPassword().equals(password))
            {
                result.setStatus(601);
                result.setMsg(" password error  !!!");
                return result;
            }
            String token = UUID.randomUUID().toString();
            log.info("token =" + token);
            int update = userService.updateByUsernameToken(username, token);
            if (update<= 0)
            {
                result.setStatus(602);
                result.setMsg(" update token failed  !!!");
                return result;
            }
            user.get().setToken(token);
//            UserDto userDto = userMapper.toResponseDto(user.get());
//            if (update > 0)
//            {}
            log.info("udpate ===> " + update);
            // update token
            result.setStatus(200);
            result.setMsg("请求成功");
            result.setData(user);
        }catch (Exception e){
            result.setStatus(500);
            result.setMsg("请求失败");
        }
        return result;
    }


    @ApiOperation("修改授权后台管理系统的用户密码")
    @RequestMapping(value =  "/v1/resetpassword/username={username}/old_password={old_password}/new_password={new_password}" , method = RequestMethod.GET)
    public ResultData findUserId(@ApiParam("用户名") @PathVariable String username,
            @ApiParam("老密码") @PathVariable String old_password,
                                 @ApiParam("新密码") @PathVariable String new_password)
    {
        log.info("[username = " + username + "][ old_password = " + old_password+ "][new_password =" + new_password + "");
        ResultData result = new ResultData();
        try{
            Optional<User> user= userService.findByUsername(username);
            if (user.isPresent() == false)
            {
                result.setStatus(600);
                result.setMsg("not find username !!!");
                return result;
            }
            if (!user.get().getPassword().equals(old_password))
            {
                result.setStatus(601);
                result.setMsg(" password error  !!!");
                return result;
            }

            int update = userService.updateByUsernamePassword(username, new_password);
            if (update<= 0)
            {
                result.setStatus(603);
                result.setMsg(" update password failed  !!!");
                return result;
            }
            user.get().setPassword(new_password);
//            UserDto userDto = userMapper.toResponseDto(user.get());
//            if (update > 0)
//            {}
            log.info("udpate ===> " + update);
            // update token
            result.setStatus(200);
            result.setMsg("请求成功");
            result.setData(user);
        }catch (Exception e){
            result.setStatus(500);
            result.setMsg("请求失败");
        }
        return result;
    }




    @ApiOperation("用户退出")
    @RequestMapping(value =  "/v1/username_exit/username={username}/password={password}" , method = RequestMethod.GET)
    public ResultData UserExit(@ApiParam("用户名") @PathVariable String username,
                               @ApiParam("密码") @PathVariable String password)
    {
        log.info("[username = " + username + "][ password = " + password+ "]");
        ResultData result = new ResultData();
        try{
            Optional<User> user= userService.findByUsername(username);

//            if (user.map())
            if (user.isPresent() == false)
            {
                result.setStatus(600);
                result.setMsg("not find username !!!");
                return result;
            }
            if (!user.get().getPassword().equals(password))
            {
                result.setStatus(601);
                result.setMsg(" password error  !!!");
                return result;
            }

            int update = userService.updateByUsernameToken(username, "");
            if (update<= 0)
            {
                result.setStatus(602);
                result.setMsg(" update token failed  !!!");
                return result;
            }
            user.get().setToken("");
//            UserDto userDto = userMapper.toResponseDto(user.get());
//            if (update > 0)
//            {}
            log.info("udpate ===> " + update);
            // update token
            result.setStatus(200);
            result.setMsg("");
            result.setData(user);
        }catch (Exception e){
            result.setStatus(500);
            result.setMsg(" ");
        }
        return result;
    }
}
