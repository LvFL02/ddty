package com.lvfl.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lvfl.springboot.controller.Empower.UserEmpower;
import com.lvfl.springboot.entity.Key;
import com.lvfl.springboot.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

//    int save(User user);

//    List<User> list();


//    Integer deleteById(@Param("uid") Integer id);

//      List<UserEmpower> selectUser(String uname,String upwd);

      IPage<User> pageSelect(Integer pageNum, Integer pageSize, String uname, String unickname);

      UserEmpower login(UserEmpower userEmpower);

      boolean register(User user) ;

      User userSelect(Integer uid);

    IPage<User> authorPage(Integer pageNum, Integer pageSize, String uname, String unickname);

    IPage<User> rootPage(Integer pageNum, Integer pageSize, String uname, String unickname);

    IPage<User> readerPage(Integer pageNum, Integer pageSize, String uname, String unickname);
}
