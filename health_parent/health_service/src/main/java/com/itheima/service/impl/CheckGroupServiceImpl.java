package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.mapper.CheckGroupMapper;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupMapper checkGroupMapper;

    /**
     * 新增检查组，同时将检查组和检查项的关系添加到中间表
     * @param checkitemIds
     * @param checkGroup
     */
    @Override
    public void add(Integer[] checkitemIds, CheckGroup checkGroup) {

        //新增检查组
        checkGroupMapper.add(checkGroup);
        //设置检查组和检查项的关系
        setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);

    }

    /**
     * 设置检查组和检查项中间表的关系
     */
    private void setCheckGroupAndCheckItem(Integer id, Integer[] checkitemIds) {

        //定义map集合，用来添加检查项和检查组的id
        Map<String, Integer> map = new HashMap<>();

        if (checkitemIds!=null){
            for (Integer checkitemId : checkitemIds) {
                //添加中间表关系
                map.put("checkitem_id",checkitemId);
                map.put("checkgroup_id",id);
                //添加中间表关系
                checkGroupMapper.setCheckGroupAndCheckItem(map);
            }
        }
    }

    /**
     * 查询检查组分页数据
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {

        //使用分页插件定义当前页和页面大小
        PageHelper.startPage(currentPage,pageSize);
        //条件查询检查组数据
        List<CheckGroup> checkGroupList = checkGroupMapper.findByCondition(queryString);
        //将数据进行包装
        PageInfo<CheckGroup> pageInfo = new PageInfo<>(checkGroupList);

        //将分页数据封装到PageResult
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());

    }

    /**
     * 通过id查询检查组数据
     * @param id
     * @return
     */
    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupMapper.findById(id);
    }

    /**
     * 编辑更新数据
     * @param ids
     * @param checkGroup
     */
    @Override
    public void edit(Integer [] ids, CheckGroup checkGroup) {

        //通过检查组id删除中间表关系
        checkGroupMapper.deleteAssociation(checkGroup.getId());

        //更新检查组信息
        checkGroupMapper.edit(checkGroup);

        //通过检查组id设置中间表关系
        setCheckGroupAndCheckItem(checkGroup.getId(),ids);
    }

    /**
     * 查询所有检查组
     * @return
     */
    @Override
    public List<CheckGroup> findAll() {
        return checkGroupMapper.findAll();
    }
}
