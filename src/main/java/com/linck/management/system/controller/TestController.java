package com.linck.management.system.controller;

import com.linck.management.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @program: Easy-Management
 * @description
 * @author: Linck
 * @create: 2020-08-23 13:16
 **/
@Api(tags = "测试控制器")
@RestController
@RequestMapping("/test")
public class TestController {

    List<User> userList = new ArrayList<>();

    @CrossOrigin
    @ApiOperation("获取当前系统时间")
    @GetMapping("/getTime")
    public Date getTime(){
        return new Date();
    }

    @CrossOrigin
    @ApiOperation("添加用户")
    @PostMapping("/addUser")
    public CommonResult addUser(@RequestBody User user){
        if(userList.isEmpty()){
            user.setId(1);
        }else{
            user.setId(userList.get(userList.size()-1).getId() + 1);
        }
        userList.add(user);
        return CommonResult.success(null);
    }

    @CrossOrigin
    @ApiOperation("删除用户")
    @PostMapping("/deleteUser")
    public CommonResult deleteUser(@RequestBody Integer id){
        userList.removeIf(user -> user.getId().equals(id));
        return CommonResult.success("删除成功");
    }

    @CrossOrigin
    @ApiOperation("查询用户")
    @PostMapping("/getUser")
    public CommonResult<User> getUser(@RequestBody Integer id){
        for(User user : userList){
            if(user.getId().equals(id)){
                return CommonResult.success(user);
            }
        }
        return CommonResult.failed("查询失败");
    }

    @CrossOrigin
    @ApiOperation("查询所有用户")
    @GetMapping("/listUser")
    public CommonResult listUser(){
        return CommonResult.success(userList,"");
    }

    @CrossOrigin
    @ApiOperation("查询天气")
    @GetMapping("/findWeather")
    public CommonResult<Map<String, String>> findWeather(String cityName){
       Map<String, String> map = new HashMap<>();
       map.put("weather", "今天天气很不错,"+cityName+"空气质量指数："+new Random().nextInt(10));
       return CommonResult.success(map,"查询成功");
    }

}

class User{
    private Integer id;
    private String username;
    private Double salary;
    private Integer age;
    private String phone;

    public User(Integer id, String username, Double salary, Integer age, String phone) {
        this.id = id;
        this.username = username;
        this.salary = salary;
        this.age = age;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
