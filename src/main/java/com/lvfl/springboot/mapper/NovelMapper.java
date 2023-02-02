package com.lvfl.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lvfl.springboot.entity.Novel;
import com.lvfl.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface NovelMapper extends BaseMapper<Novel> {

    boolean insertNovel(Novel novel);

    boolean updateNovel(Novel novel);
    //    @Select("select nid,nname,unickname,ntname from novel n join user u join noveltype nt on n.uid = u.uid and n.ntid = nt.ntid limit #{pageNum},#{pageSize}")
//    @Select("select nid,nname,unickname,ntname from novel")
    List<Novel> pageSelect(Integer pageNum, Integer pageSize,String nname);

    Integer totalSelect(String nname);

    List<Novel> findAll();

}
