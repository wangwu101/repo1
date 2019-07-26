package com.itheima.mapper;

import com.itheima.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CheckGroupMapper {
    //新增检查组
    void add(CheckGroup checkGroup);

    //添加检查项和检查组中间表关系
    void setCheckGroupAndCheckItem(Map<String,Integer> map);

    List<CheckGroup> findByCondition(@Param("queryString")String queryString);

    //通过id查询检查项数据
    CheckGroup findById(@Param("id")Integer id);

    //删除中间表关系
    void deleteAssociation(@Param("id")Integer id);

    //更新检查组信息
    void edit(CheckGroup checkGroup);
    //查询所有检查组
    List<CheckGroup> findAll();
}
