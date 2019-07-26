package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.entity.PageResult;
import com.itheima.pojo.OrderSetting;
import com.itheima.mapper.OrderSettingMapper;
import com.itheima.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service(interfaceClass = OrderSettingService.class)
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingMapper orderSettingMapper;
    /**
     * 新增预约数据
     * @param orderSettings
     */
    @Override
    public void add(List<OrderSetting> orderSettings) {

        for (OrderSetting orderSetting : orderSettings) {

            //检查当前预约日期是否在数据库中设置过
            int count = orderSettingMapper.findCountByOrderDate(orderSetting.getOrderDate());
            if (count>0){
                //已存在就更新预约数量
                orderSettingMapper.updateNumberByOrderDate(orderSetting);
            }else{
                //不存在就新增
                orderSettingMapper.add(orderSetting);
            }
        }
    }

    /**
     * 查询当前月的预约数据
     *
     * 数据格式：{ date: 1, number: 120, reservations: 1 }
     * @param date
     * @return
     */
    @Override
    public List<Map> findOrderSettingByMonth(String date) {

        String monthBegin = date+"-1";
        String monthEnd = date+"-31";
        //查询当月的预约数据
        List<OrderSetting> orderSettings = orderSettingMapper.findOrderSettingByMonth(monthBegin,monthEnd);

        List<Map> orderSettingMap = new ArrayList<>();
        for (OrderSetting orderSetting : orderSettings) {
            Map<String, Object> map = new HashMap<>();
            map.put("date",orderSetting.getOrderDate().getDate());
            map.put("number",orderSetting.getNumber());
            map.put("reservations",orderSetting.getReservations());
            orderSettingMap.add(map);
        }
        return orderSettingMap;
    }

    /**
     * 更新预约数量
     * @param orderSetting
     */
    @Override
    public void updateNumberByOrderDate(OrderSetting orderSetting) {
        //根据日期统计之前是否设置过预约数据
        int count = orderSettingMapper.findCountByOrderDate(orderSetting.getOrderDate());
        if (count>0){
            //如果设置过预约数据，执行更新
            orderSettingMapper.updateNumberByOrderDate(orderSetting);
        }else{
            //如果之前没有设置过预约数据，执行新增
            orderSettingMapper.add(orderSetting);
        }

    }

    /**
     * 预约数据分页查询
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {

        PageHelper.startPage(currentPage,pageSize);

        List<OrderSetting> orderSettings = orderSettingMapper.findPage(queryString);

        PageInfo<OrderSetting> pageInfo = new PageInfo<>(orderSettings);

        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }
}
