package com.ebanma.cloud.post.config;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ebanma.cloud.post.utils.CurrentUserUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author banma-
 */
@Component
public class DateConfig implements MetaObjectHandler {

    @Autowired
    private CurrentUserUtils currentUserUtils;


    /**
     * 使用mp做添加操作时候，这个方法执行
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {

        //设置属性值
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
        this.setFieldValByName("createUser",this.getUserName(),metaObject);
        this.setFieldValByName("updateUser",this.getUserName(),metaObject);
    }

    /**
     * 使用mp做修改操作时候，这个方法执行
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
        this.setFieldValByName("updateUser",this.getUserName(),metaObject);
    }

    public String getUserName() {
        String  currentUserDetail = currentUserUtils.getCurrentUserName();
        //使用fastjson解析json格式的字符串为json对象
        JSONObject jsonObject = JSONObject.parseObject(currentUserDetail);
        //获取通信标识code
        String username = jsonObject.getString("userPhone");
        return username;
    }
}

