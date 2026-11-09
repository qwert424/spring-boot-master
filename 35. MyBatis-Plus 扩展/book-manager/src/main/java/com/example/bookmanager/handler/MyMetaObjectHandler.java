package com.example.bookmanager.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 重写插入填充方法，在插入数据时自动填充指定字段
     * @param metaObject 元数据对象，包含实体信息
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 填充创建时间字段，使用当前时间
        this.strictInsertFill(metaObject, "createdAt", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updatedAt", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "createdBy", String.class, getCurrentUser());
        this.strictInsertFill(metaObject, "updatedBy", String.class, getCurrentUser());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updatedAt", LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, "updatedBy", String.class, getCurrentUser());
    }

    // 获取当前用户，一般从SecurityContext 或 ThreadLocal获取
    private String getCurrentUser() {
        // 模拟从Spring Security里面拿
        // SecurityContext
        return "system";
    }
}
