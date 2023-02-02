package com.lvfl.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lvfl.springboot.controller.Empower.UserEmpower;
import com.lvfl.springboot.entity.Key;
import com.lvfl.springboot.entity.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserMapper  extends BaseMapper<User> {

    List<User> findAll();

    List<Key> Key(Key key);

//    int insert(User user);
//
//    int update(User user);
//
//    Integer deleteById(@Param("uid") Integer id);

//    @Select("select * from user where uname like concat('%', #{uname}, '%') limit #{pageNum},#{pageSize}")
    List<User> pageSelect(Integer pageNum, Integer pageSize, String uname);

//    @Select("select count(uid) from user where uname like concat('%', #{uname}, '%')")
//    Integer pageTotal(String uname);


    User selectUser(Integer uid);

    IPage<User> authorPage(Integer pageNum, Integer pageSize, String uname, String unickname);


//    Page<User> findPage(Page<User> page, @Param("uname") String uname, @Param("unickname") String unickname);
}