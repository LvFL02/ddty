package com.lvfl.springboot.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "user")
public class User {
    @TableId(type = IdType.AUTO,value = "uid")
    private Integer uid;
    @Alias("用户名")
    private String uname;
    @Alias("密码")
    private String upwd;
    @Alias("昵称")
    private String unickname;
    @Alias("电话")
    private int uphone;
    @Alias("邮箱")
    private String uemail;
    @Alias("身份")
    private Integer status;
    @Alias("头像路径")
    private String avatarUrl;

    @Alias("创建时间")
    private Date createTime;

//    @TableField(value = "avatar_url")  // 指定数据库的字段名称
}
