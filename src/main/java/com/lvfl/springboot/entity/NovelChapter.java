package com.lvfl.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "chapter")
public class NovelChapter {
    @TableId(type = IdType.AUTO,value = "cid")
    private Integer cid;
    private String nname;
    private String cname;

    private Integer nid;
    private String blurb;
    private String path;
}
