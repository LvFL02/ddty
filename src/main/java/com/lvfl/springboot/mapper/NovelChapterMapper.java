package com.lvfl.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lvfl.springboot.entity.Novel;
import com.lvfl.springboot.entity.NovelChapter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NovelChapterMapper extends BaseMapper<NovelChapter> {

    List<NovelChapter> chapterList(Integer nid);

    boolean insertChapter(NovelChapter novelChapter);

    boolean updateChapter(NovelChapter novelChapter);

    List<NovelChapter> findAll();

    List<NovelChapter> read(Integer cid);
}
