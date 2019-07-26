package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("setmeal")
public class SetmealMobileControler {

    @Reference
    private SetmealService setmealService;

    /**
     * 查询所有套餐
     * @return
     */
    @RequestMapping("findSetmealAll")
    public Result findSetmealAll(){
        try {
            List<Setmeal> setmealList = setmealService.findSetmealAll();
            return new Result(true,MessageConstant.QUERY_SETMEALLIST_SUCCESS,setmealList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_SETMEALLIST_FAIL);
        }

    }

    /**
     * 通过id查询套餐和检查组以及检查项数据
     * @return
     */
    @RequestMapping("findById")
    public Result findById(@RequestParam("id")Integer id){

        try {
            Setmeal setmeal = setmealService.findById(id);
            return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);
        }

    }
}
