package com.lvfl.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "key")
public class Key {
    @TableId(type = IdType.AUTO,value = "kid")
    private Integer kid;
    private String kname;
}
