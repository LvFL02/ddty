package com.lvfl.springboot.service;

import com.lvfl.springboot.entity.NovelChapter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface NovelChapterService {

    Map<String, Object> chapterPage(Integer pageNum, Integer pageSize, String nid);

    Map<String, Object> chapterList(Integer nid);

    Map<String, Object> read(Integer cid);
}
