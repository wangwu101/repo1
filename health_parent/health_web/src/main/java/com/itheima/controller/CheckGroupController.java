package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("checkgroup")
public class CheckGroupController {

    @Reference
    private CheckGroupService checkGroupService;

    /**
     * 新增检查组
     * @param checkitemIds
     * @param checkGroup
     * @return
     */
    @RequestMapping("add")
    public Result add(@RequestParam("checkitemIds")Integer [] checkitemIds, @RequestBody CheckGroup checkGroup){

        try {
            checkGroupService.add(checkitemIds,checkGroup);
            return new Result(true,MessageConstant.ADD_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_CHECKGROUP_FAIL);
        }
    }

    /**
     * 查询检查组分页数据
     * @param queryPageBean
     * @return
     */
    @RequestMapping("findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){

        try {
            PageResult pageResult = checkGroupService.findPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize(),queryPageBean.getQueryString());
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }

    }

    /**
     * 通过id查询检查组数据
     * @param id
     * @return
     */
    @RequestMapping("findById")
    public Result findById(@RequestParam("id")Integer id){

        try {
            CheckGroup checkGroup = checkGroupService.findById(id);
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }

    }

    /**
     * 编辑更新数据
     * @param ids
     * @param checkGroup
     * @return
     */
    @RequestMapping("edit")
    public Result edit(@RequestParam("ids")Integer [] ids,@RequestBody CheckGroup checkGroup){

        try {
            checkGroupService.edit(ids,checkGroup);
            return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);

        }
    }

    /**
     * 查询所有检查组
     * @return
     */
    @RequestMapping("findAll")
    public Result findAll(){

        try {
            List<CheckGroup> checkGroupList = checkGroupService.findAll();
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroupList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);

        }
    }
}
