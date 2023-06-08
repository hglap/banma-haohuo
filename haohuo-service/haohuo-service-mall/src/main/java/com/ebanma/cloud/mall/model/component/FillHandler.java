package com.ebanma.cloud.mall.model.component;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FillHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTime dateTime =new DateTime();
//        String curUser = AppContext.getUserId();
        String curUser = "userId";
//        String tenantID = AppContext.getTenantId();
        this.fillStrategy(metaObject, "createTime", dateTime);
        this.fillStrategy(metaObject, "lastModified", dateTime);
        this.fillStrategy(metaObject, "createUser", curUser);
        this.fillStrategy(metaObject, "lastModifyUser", curUser);
        this.fillStrategy(metaObject, "version", 1);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTime dateTime =new DateTime();
//        String curUser = AppContext.getUserId();
        String curUser = "userId";
        this.fillStrategy(metaObject, "lastModified", dateTime);
        this.fillStrategy(metaObject, "lastModifyUser", curUser);
    }

}
