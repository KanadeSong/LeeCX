package com.ljj.web.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @CLassName SpringUtils
 * @Description Spring上下文操作类
 * @Author LeeJack
 * @Date 2019/3/15/015 8:02
 * @Version 1.0
 */
@Component
public class SpringUtils implements ApplicationContextAware {

    /**上下文对象*/
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = context;
    }

    /**
     *
     * 获取Spring上下文对象
     *
     * @return
     */
    public static ApplicationContext getContext() {
        return context;
    }

}
