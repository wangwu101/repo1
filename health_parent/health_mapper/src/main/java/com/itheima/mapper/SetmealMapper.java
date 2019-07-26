package com.itheima.mapper;

import com.itheima.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SetmealMapper {
    //新增套餐
    void add(Setmeal setmeal);
    //设置中间表关系
    void setSetmealAndCheckGroup(Map<String,Integer> map);

    List<Setmeal> findByCondition(@Param("queryString")String queryString);

    //查询套餐列表
    List<Setmeal> findSetmealAll();

    //通过id查询套餐和检查组以及检查项数据
    Setmeal findById(@Param("id")Integer id);
}
