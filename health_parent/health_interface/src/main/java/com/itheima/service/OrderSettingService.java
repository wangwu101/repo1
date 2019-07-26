package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrderSettingService {
    //新增预约数据
    void add(List<OrderSetting> orderSettings);

    //查询当前月的预约数据
    List<Map> findOrderSettingByMonth(String date);

    //更新预约数量
    void updateNumberByOrderDate(OrderSetting orderSetting);

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);
}
