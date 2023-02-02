package com.lvfl.springboot.service;

import com.lvfl.springboot.entity.Novel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface NovelService {

    List<Novel> findAll();

    Map<String, Object> novelPage(Integer pageNum, Integer pageSize,String nname);

    boolean novelSave(Novel novel);
}
