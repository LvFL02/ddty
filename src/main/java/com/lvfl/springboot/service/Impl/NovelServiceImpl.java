package com.lvfl.springboot.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvfl.springboot.entity.Novel;
import com.lvfl.springboot.mapper.NovelMapper;
import com.lvfl.springboot.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NovelServiceImpl extends ServiceImpl<NovelMapper,Novel> implements NovelService {

    @Autowired
    private NovelMapper novelMapper;

//    public boolean novelExport(HttpServletResponse response, Integer nid) throws Exception {
//        //通过Mybatis-plus自带的方法根据id查询文件详情
//        Novel file = getById(nid);
//        //获取文件路径
//        String filePath = file.getChapterUrl();
//        //获取文件名
//        String fileName = file.getNname();
//        // 在内存操作，写出到浏览器
//        ExcelWriter writer = ExcelUtil.getWriter(true);
//        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
////        writer.write(list, true);
//        // 设置浏览器响应的格式
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
//        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".txt");
//
//        FileInputStream fileInputStream = new FileInputStream(filePath);
//        ServletOutputStream out = response.getOutputStream();
//        writer.flush(out, true);
//        out.close();
//        writer.close();
//        return true;
//    }


    @Override
    public List<Novel> findAll() {
        return novelMapper.findAll();
    }

    @Override
    public Map<String, Object> novelPage(Integer pageNum, Integer pageSize, String nname) {
        pageNum = (pageNum - 1) * pageSize;
        List<Novel> data = novelMapper.pageSelect(pageNum,pageSize,nname);
        Integer total = novelMapper.totalSelect(nname);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);

        return res;
    }

    @Override
    public boolean novelSave(Novel novel){
        if (novel.getNid()==null){  //novel的id为空则新增
            return novelMapper.insertNovel(novel);
        }else {
            return novelMapper.updateNovel(novel);
        }
    }


}
