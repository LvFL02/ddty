package com.lvfl.springboot.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvfl.springboot.common.Constants;
import com.lvfl.springboot.common.Result;
import com.lvfl.springboot.controller.Empower.UserEmpower;
import com.lvfl.springboot.entity.Key;
import com.lvfl.springboot.entity.User;
import com.lvfl.springboot.exception.ServiceException;
import com.lvfl.springboot.mapper.UserMapper;
import com.lvfl.springboot.service.UserService;
import com.lvfl.springboot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;


    public boolean saveUser(User user){
//        if (user.getUid()==null){  user的id为空则新增
//            return save(user);
//        }else {
//            return updateById(user);
//        }
        return saveOrUpdate(user);
    }

    @Override
    public List<User> list() {
        return userMapper.findAll();
    }




//    @Override
//    public Map<String, Object> pageSelect(Integer pageNum, Integer pageSize ,String uname) {
//        pageNum = (pageNum - 1) * pageSize;
//        List<User> data = userMapper.pageSelect(pageNum, pageSize, uname);
//        Integer total = userMapper.pageTotal(uname);
//        Map<String,Object> map = new HashMap<>();
//        map.put("data",data);
//        map.put("total",total);
//        return map;
//    }




    public IPage<User> pageSelect(Integer pageNum, Integer pageSize , String uname, String unickname) {
        IPage<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!"".equals(uname)) {
            queryWrapper.like("uname", uname);
        }
        if (!"".equals(unickname)) {
            queryWrapper.like("unickname", unickname);
        }
           queryWrapper.orderByDesc("uid");
        return page(page, queryWrapper);
    }

    @Override
    public IPage<User> readerPage(Integer pageNum, Integer pageSize, String uname, String unickname) {
        IPage<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Integer status = 1;
        if (!"".equals(uname)) {
            queryWrapper.like("uname", uname);
        }
        if (!"".equals(unickname)) {
            queryWrapper.like("unickname", unickname);
        }
        queryWrapper.eq("status",status);
        queryWrapper.orderByDesc("uid");
        return page(page, queryWrapper);
    }

    @Override
    public IPage<User> authorPage(Integer pageNum, Integer pageSize, String uname, String unickname) {
        IPage<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Integer status = 2;
        if (!"".equals(uname)) {
            queryWrapper.like("uname", uname);
        }
        if (!"".equals(unickname)) {
            queryWrapper.like("unickname", unickname);
        }
        queryWrapper.eq("status",status);
        queryWrapper.orderByDesc("uid");
        return page(page, queryWrapper);
    }

    @Override
    public IPage<User> rootPage(Integer pageNum, Integer pageSize, String uname, String unickname) {
        IPage<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Integer status = 3;
        if (!"".equals(uname)) {
            queryWrapper.like("uname", uname);
        }
        if (!"".equals(unickname)) {
            queryWrapper.like("unickname", unickname);
        }
        queryWrapper.eq("status",status);
        queryWrapper.orderByDesc("uid");
        return page(page, queryWrapper);
    }


    @Override
    public UserEmpower login(UserEmpower userEmpower) {
        User one;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uname", userEmpower.getUname());
        queryWrapper.eq("upwd", userEmpower.getUpwd());
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        if (one != null) {
            if(one.getStatus() != 1 ) {

                BeanUtil.copyProperties(one, userEmpower, true);
                // token
                String token = TokenUtils.genToken(one.getUid().toString(), one.getUpwd());
                userEmpower.setToken(token);
                return userEmpower;
            }else {
                throw new ServiceException(Constants.CODE_400, "权限不足");
            }
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }
    @Override
    public boolean register(User user) {
        return save(user);
    }

    @Override
    public User userSelect(Integer uid) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        return getOne(queryWrapper);
    }

    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<User> list = userMapper.findAll();
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
//        writer.addHeaderAlias("uname", "用户名");
//        writer.addHeaderAlias("upwd", "密码");
//        writer.addHeaderAlias("unickname", "昵称");
//        writer.addHeaderAlias("uemail", "邮箱");
//        writer.addHeaderAlias("uphone", "电话");
//        writer.addHeaderAlias("createTime", "创建时间");
//        writer.addHeaderAlias("avatarUrl", "头像");

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
    }


    public Boolean upload(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
//        reader.addHeaderAlias("用户名","uname");
//        reader.addHeaderAlias("密码","upwd");
//        reader.addHeaderAlias("昵称","unickname");
//        reader.addHeaderAlias("邮箱","uemail");
//        reader.addHeaderAlias("电话","uphone");
        List<User> list = reader.readAll(User.class);
//        reader.addHeaderAlias(中文,英文)。用这个就可以把中文转回来跟输出一样
        saveBatch(list);
        System.out.println(list);
        return true;
    }



}
