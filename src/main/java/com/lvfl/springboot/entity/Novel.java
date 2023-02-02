package com.lvfl.springboot.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "novel")
public class Novel {
    @TableId(type = IdType.AUTO,value = "nid")
    private Integer nid;
    private Integer uid;
    private Integer ntid;
    private String nname;
    @Alias("昵称")
    private String unickname;
    private String ntname;
    private String blurb;
    private String chapterUrl;

}
