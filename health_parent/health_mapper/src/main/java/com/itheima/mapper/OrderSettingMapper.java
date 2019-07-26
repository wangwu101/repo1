package com.itheima.mapper;

import com.itheima.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderSettingMapper {
    //统计预约日期是否已被设置过
    int findCountByOrderDate(@Param("orderDate") Date orderDate);

    //更新预约数量
    void updateNumberByOrderDate(OrderSetting orderSetting);

    //新增预约数据
    void add(OrderSetting orderSetting);

    //查询当月预约数据
    List<OrderSetting> findOrderSettingByMonth(@Param("monthBegin") String monthBegin,@Param("monthEnd")  String monthEnd);

    //查询可预约分页数据
    List<OrderSetting> findPage(@Param("queryString")String queryString);
}
