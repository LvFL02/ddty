package com.lvfl.springboot.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class NovelType {
    @TableId(type = IdType.AUTO,value = "ntid")
    private Integer ntid;
    private String ntname;
}
