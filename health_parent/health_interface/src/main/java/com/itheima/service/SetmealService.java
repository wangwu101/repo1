package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.Setmeal;

import java.util.List;

public interface SetmealService {
    void add(Integer[] checkgroupIds, Setmeal setmeal);

    //分页查询
    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    //查询套餐列表
    List<Setmeal> findSetmealAll();

    //通过id查询套餐、检查组和检查项
    Setmeal findById(Integer id);
}
