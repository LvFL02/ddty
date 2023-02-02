package com.lvfl.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lvfl.springboot.common.Constants;
import com.lvfl.springboot.common.Result;
import com.lvfl.springboot.controller.Empower.UserEmpower;
import com.lvfl.springboot.entity.Key;
import com.lvfl.springboot.entity.User;

import com.lvfl.springboot.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    private Result login(@RequestBody UserEmpower userEmpower) {
        String uname = userEmpower.getUname();
        String upwd = userEmpower.getUpwd();
        if (StrUtil.isBlank(uname) || StrUtil.isBlank(upwd)) {
            //hutool 验证是否为空
            return Result.error(Constants.CODE_500, "错误");
        }
        return Result.success(userService.login(userEmpower));
//        return userService.login(userEmpower);
    }


    @PostMapping("/register")
    public Result register(@RequestBody User user,@RequestBody Key key) {
        return Result.success(userService.register(user));
    }

    @PostMapping //修改与插入
    public Result save(@RequestBody User user) {
        if (user.getUname() != null || user.getUpwd() != null || user.getUnickname() != null || user.getUemail() != null) {
            return Result.success(userService.saveOrUpdate(user));
        }
        return  Result.error(Constants.CODE_500, "不能为空");
    }
    @GetMapping("/") //查询所有
    public List<User> findAll(){
        return userService.list();
    }

    @GetMapping("/information/{uid}") //查询登录用户信息
    public Result userSelect(@PathVariable Integer uid){
        return Result.success(userService.userSelect(uid));
    }

    @DeleteMapping("/{uid}") //删除
    public boolean delete(@PathVariable Integer uid){
        return userService.removeById(uid);
    }

    @PostMapping("/batchDel") //批量删除
    public boolean batchDel(@RequestBody List<Integer> uids){
        return userService.removeByIds(uids);
    }

    @GetMapping("/page") //分页查询
//    @RequestPara（required=”false”）表示不传该参数也是可以的
    public IPage<User> userPage(@RequestParam Integer pageNum,
                            @RequestParam Integer pageSize,
                            @RequestParam(defaultValue = "") String uname,
                            @RequestParam(defaultValue = "") String unickname){
      return userService.pageSelect(pageNum,pageSize,uname,unickname);
    }
    @GetMapping("/authorPage") //分页查询
    public IPage<User> authorPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String uname,
                                @RequestParam(defaultValue = "") String unickname){
        return userService.authorPage(pageNum,pageSize,uname,unickname);
    }
    @GetMapping("/rootPage") //分页查询
    public IPage<User> rootPage(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam(defaultValue = "") String uname,
                                  @RequestParam(defaultValue = "") String unickname){
        return userService.rootPage(pageNum,pageSize,uname,unickname);
    }
    @GetMapping("/readerPage") //分页查询
    public IPage<User> readerPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String uname,
                                @RequestParam(defaultValue = "") String unickname){
        return userService.readerPage(pageNum,pageSize,uname,unickname);
    }

    @GetMapping("/export") //上传
    public void export(HttpServletResponse response) throws Exception {
        userService.export(response);
    }
    @PostMapping("/import") //下载
    public void upload(MultipartFile file) throws Exception {
        userService.upload(file);
    }
}

