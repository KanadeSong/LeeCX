package com.ljj.web.controller;

import com.ljj.components.utils.RedisOperator;
import com.ljj.pojo.ActiveUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @CLassName BaseController
 * @Description basic controller, controller中的大部分通用方法写在此
 * @Author LeeJack
 * @Date 2019/3/14/014 17:09
 * @Version 1.0
 */
public class BaseController {

    //	@Autowired
    //	public JedisClient jedis;

    @Autowired
    public RedisOperator jedis;

    //	@Value("${REDIS_SESSION_KEY}")
    //	public String REDIS_SESSION_KEY;
    //	
    //	@Value("${SESSION_EXPIRE}")
    //	public Integer SESSION_EXPIRE;
    //	
    //	@Value("${ITZIXI_TOKEN}")
    //	public String ITZIXI_TOKEN;

    /**
     * @Description: 默认分页行数
     * @Param
     * @return
     * @author LeeJack
     * @Date 17:14 2019/3/14/014
     */
    public static final Integer pageSize = 10;

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.String>
     * @Description: 验证并且获得bean上的错误
     * @Param [result]
     * @author LeeJack
     * @Date 17:17 2019/3/14/014
     */
    protected Map<String, String> getErrors(BindingResult result) {
        Map<String, String> map = new HashMap<String, String>();
        List<FieldError> list = result.getFieldErrors();
        for (FieldError error : list) {
            map.put(error.getField() , error.getDefaultMessage());
        }
        return map;
    }

    /**
     * @return java.lang.String
     * @Description: 获得域名地址路径
     * @Param [request]
     * @author LeeJack
     * @Date 17:19 2019/3/14/014
     */
    protected String getWebUrlAddress(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length() , url.length()).append("/").toString();
        return tempContextUrl;
    }

    /**
     *
     * @Description: 获得当前登录用户
     * @Param []
     * @return com.ljj.pojo.ActiveUser
     * @author LeeJack
     * @Date 17:22 2019/3/14/014
     */
    protected ActiveUser getLoginUser() {
        return (ActiveUser) SecurityUtils.getSubject().getPrincipal();
    }


}
