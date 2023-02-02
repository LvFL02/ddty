package com.lvfl.springboot.controller;

import com.lvfl.springboot.entity.NovelChapter;
import com.lvfl.springboot.mapper.NovelChapterMapper;
import com.lvfl.springboot.service.Impl.NovelChapterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/novelchapter")
public class NovelChapterController {

    @Autowired
    private NovelChapterMapper novelChapterMapper;

    @Autowired
    private NovelChapterServiceImpl novelChapterService;

    //    @GetMapping("/") //查询所有
    public List<NovelChapter> findAll(Integer nid) {
        nid = 1;
        return novelChapterMapper.chapterList(nid);
    }

    @GetMapping("/{nid}") //显示小说详细
    public Map<String, Object> chapterList(@PathVariable Integer nid) {
        return novelChapterService.chapterList(nid);
    }


    @GetMapping("/content/{cid}") //分页查询
    //    这里可以输入@RequestPara（required=”false”）表示不传该参数也是可以的
    public Map<String, Object> content(@PathVariable Integer cid) {
        return novelChapterService.read(cid);
    }

}
