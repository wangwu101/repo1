package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import com.itheima.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("setmeal")
public class SetmealController {


    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 上传图片
     * @param file
     * @return
     */
    @RequestMapping("upload")
    public Result upload(@RequestParam("imgFile")MultipartFile file){


        //获取上传文件的名称
        String originalFilename  = file.getOriginalFilename();
        //获取后缀.的索引号
        int index = originalFilename .lastIndexOf(".");
        //截取后缀名称
        String suffix = originalFilename .substring(index);
        //生成一个随机不重复的字符串+后缀名称组合成一个不会重复的文件名称
        String fileName = UUID.randomUUID().toString()+suffix;

        //调用七牛云上传工具
        try {
            QiniuUtils.upload2Qiniu(file.getBytes(),fileName);
            //将自动上传的图片保存到redis的set集合中
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);

            //图片上传成功响应数据
            return new Result(true,MessageConstant.PIC_UPLOAD_SUCCESS,fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
        }

    }

    /**
     *  新增套餐
     * @param checkgroupIds
     * @param setmeal
     * @return
     */
    @RequestMapping("add")
    public Result add(@RequestParam("checkgroupIds")Integer []checkgroupIds,
                      @RequestBody Setmeal setmeal){

        try {
            setmealService.add(checkgroupIds,setmeal);
            return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }

    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @RequestMapping("findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){

        try {
            PageResult pageResult = setmealService.findPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize(),queryPageBean.getQueryString());
            return new Result(true,MessageConstant.QUERY_SETMEALLIST_SUCCESS,pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_SETMEALLIST_FAIL);
        }
    }

}
