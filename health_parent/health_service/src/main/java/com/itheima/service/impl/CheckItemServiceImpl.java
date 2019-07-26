package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.mapper.CheckItemMapper;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemMapper checkItemMapper;

    /**
     * 新增检查项
     * @param checkItem
     */
    @Override
    public void add(CheckItem checkItem) {
        checkItemMapper.add(checkItem);
    }

    /**
     * 分页查询检查项
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {

        //使用mybatis的分页插件
        PageHelper.startPage(currentPage,pageSize);
        //条件查询分页数据
        List<CheckItem> checkItemList = checkItemMapper.findByCondition(queryString);
        //将查询出来的数据进行包装
        PageInfo<CheckItem> pageInfo = new PageInfo<>(checkItemList);

        PageResult pageResult = new PageResult(pageInfo.getTotal(), pageInfo.getList());

        return pageResult;
    }

    /**
     * 通过id删除检查项
     * @param id
     */
    @Override
    public void deleteById(Integer id) {

        //通过检查项的id查询是否在中间表中被引用
        int count = checkItemMapper.findCountByCheckItemId(id);
        if (count>0){
            throw  new RuntimeException(MessageConstant.CHECKITEM_IS_QUOTED);
        }
        //通过id删除检查项
        checkItemMapper.deleteById(id);
    }

    /**
     * 通过id查询检查项
     * @param id
     * @return
     */
    @Override
    public CheckItem findById(Integer id) {

        CheckItem checkItem = checkItemMapper.findById(id);

        return checkItem;
    }

    /**
     * 编辑更新检查项
     * @param checkItem
     */
    @Override
    public void edit(CheckItem checkItem) {

        checkItemMapper.edit(checkItem);

    }

    /**
     * 查询所有检查项
     * @return
     */
    @Override
    public List<CheckItem> findAll() {

        return  checkItemMapper.findAll();

    }

    /**
     * 查询检查组对应的检查项id
     * @param id
     * @return
     */
    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {

        return checkItemMapper.findCheckItemIdsByCheckGroupId(id);
    }
}
