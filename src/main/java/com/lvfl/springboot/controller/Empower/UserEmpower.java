package com.lvfl.springboot.controller.Empower;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class UserEmpower {
    @TableId(type = IdType.AUTO,value = "uid")
    private Integer uid;
    @Alias("用户名")
    private String uname;
    @Alias("密码")
    private String upwd;
    @Alias("昵称")
    private String unickname;
    @Alias("身份")
    private Integer status;
    @Alias("电话")
    private int uphone;
    @Alias("邮箱")
    private String uemail;
    @Alias("头像路径")
    private String avatarUrl;
    private String token;

}
