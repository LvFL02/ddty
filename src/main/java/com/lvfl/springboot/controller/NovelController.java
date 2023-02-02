package com.lvfl.springboot.controller;

import com.lvfl.springboot.entity.Novel;
import com.lvfl.springboot.entity.User;
import com.lvfl.springboot.mapper.NovelMapper;
import com.lvfl.springboot.service.Impl.NovelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/novel")
public class NovelController {

    @Autowired
    private NovelServiceImpl novelService;
    @Autowired
    private NovelMapper novelMapper;

    @GetMapping("/") //查询所有
    public List<Novel> findAll(){
        return novelService.findAll();
    }

    @PostMapping //修改与插入
    public boolean save(@RequestBody Novel novel){
        return novelService.novelSave(novel);
    }

    @DeleteMapping("/{nid}") //删除
    public boolean delete(@PathVariable Integer nid){
        return novelService.removeById(nid);
    }

    @PostMapping("/batchDel")
    public boolean batchDel(@RequestBody List<Integer> nids){
        return novelService.removeByIds(nids);
    }

    @GetMapping("/page") //分页查询
//    @RequestPara（required=”false”）表示不传该参数也是可以的
    public Map<String, Object> novelPage(@RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize,
                                         @RequestParam(defaultValue = "") String nname
                                 ){
        return novelService.novelPage(pageNum,pageSize,nname);
    }
}
