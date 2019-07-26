package com.itheima.jobs;

import com.itheima.constant.RedisConstant;
import com.itheima.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import java.util.Iterator;
import java.util.Set;

@Component
public class ClearImgJob {

    @Autowired
    private JedisPool jedisPool;

    public void clearImg(){
        //计算两个set集合中的差值，获取差值集合
        Set<String> sdiff = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        Iterator<String> iterator = sdiff.iterator();
        while (iterator.hasNext()){
            //获取集合中的图片名称
            String pic = iterator.next();
            //删除七牛云中多余的图片
            QiniuUtils.deleteFileFromQiniu(pic);
            //删除redis的set集合中自动上传的多余的图片
            jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,pic);
        }

    }

}
