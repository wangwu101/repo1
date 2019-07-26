package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    //新增检查组
    void add(Integer[] checkitemIds, CheckGroup checkGroup);

    //查询分页数据
    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    //通过id查询检查组数据
    CheckGroup findById(Integer id);

    //编辑更新数据
    void edit(Integer [] ids, CheckGroup checkGroup);

    //查询所有检查组
    List<CheckGroup> findAll();
}
