package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {
    //新增检查项
    void add(CheckItem checkItem);
    //分页查询检查项
    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);
    //通过id删除检查项
    void deleteById(Integer id);

    //通过id查询检查项
    CheckItem findById(Integer id);

    //编辑更新检查项
    void edit(CheckItem checkItem);

    //查询所有检查项
    List<CheckItem> findAll();

    //查询检查组对应的检查项id
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
}
