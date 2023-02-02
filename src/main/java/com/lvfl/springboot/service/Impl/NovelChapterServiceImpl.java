package com.lvfl.springboot.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvfl.springboot.entity.NovelChapter;
import com.lvfl.springboot.mapper.NovelChapterMapper;
import com.lvfl.springboot.service.NovelChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NovelChapterServiceImpl extends ServiceImpl<NovelChapterMapper, NovelChapter> implements NovelChapterService {

    @Autowired
    private NovelChapterMapper novelChapterMapper;

    @Override
    public Map<String, Object> chapterPage(Integer pageNum, Integer pageSize, String nid) {
        return null;
    }

    @Override
    public Map<String, Object> chapterList(Integer nid) {
        List<NovelChapter> data = novelChapterMapper.chapterList(nid);
        Map<String, Object> res = new HashMap<>();
        res.put("data",data);
        return res;
    }

    @Override
    public Map<String, Object> read(Integer cid) {
        List<NovelChapter> data = novelChapterMapper.read(cid);
        Map<String, Object> res = new HashMap<>();
        res.put("data",data);
        return res;
    }


}
