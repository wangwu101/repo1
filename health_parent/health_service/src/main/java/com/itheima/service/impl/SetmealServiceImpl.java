package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.constant.RedisConstant;
import com.itheima.entity.PageResult;
import com.itheima.mapper.SetmealMapper;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = SetmealService.class )
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private JedisPool jedisPool;
    /**
     * 新增套餐
     * @param checkgroupIds
     * @param setmeal
     */
    @Override
    public void add(Integer[] checkgroupIds, Setmeal setmeal) {

        //执行新增
        setmealMapper.add(setmeal);
        //设置中间表关系
        if (checkgroupIds!=null){
            setSetmealAndCheckGroup(checkgroupIds,setmeal.getId());
        }

        //将保存到数据中的图片存入redis中
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());

    }
    //设置中间表关系
    private void setSetmealAndCheckGroup(Integer[] checkgroupIds, Integer id) {
        Map<String, Integer> map = new HashMap<>();
        for (Integer checkgroupId : checkgroupIds) {
            map.put("setmeal_id",id);
            map.put("checkgroup_id",checkgroupId);
            setmealMapper.setSetmealAndCheckGroup(map);
        }
    }

    /**
     * 查询套餐分页数据
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        List<Setmeal> setmealList = setmealMapper.findByCondition(queryString);
        PageInfo<Setmeal> pageInfo = new PageInfo<>(setmealList);

        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 查询所有套餐
     * @return
     */
    @Override
    public List<Setmeal> findSetmealAll() {
        return setmealMapper.findSetmealAll();
    }

    /**
     * 通过id查询套餐以及检查组和检查项数据
     */
    @Override
    public Setmeal findById(Integer id) {
        return setmealMapper.findById(id);
    }
}
