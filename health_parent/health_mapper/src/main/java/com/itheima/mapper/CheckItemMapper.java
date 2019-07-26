package com.itheima.mapper;

import com.itheima.pojo.CheckItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckItemMapper {
    void add(CheckItem checkItem);

    List<CheckItem> findByCondition(@Param("queryString") String queryString);

    //通过id删除检查项
    void deleteById(@Param("id")Integer id);

    //通过检查项id查询是否在中间表被引用
    int findCountByCheckItemId(@Param("id")Integer id);

    //通过id查询检查项
    CheckItem findById(@Param("id")Integer id);

    //编辑更新检查项
    void edit(CheckItem checkItem);

    //查询作用检查项
    List<CheckItem> findAll();

    //查询检查组对应的检查项id
    List<Integer> findCheckItemIdsByCheckGroupId(@Param("id")Integer id);
}
