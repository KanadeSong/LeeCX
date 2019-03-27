package com.ljj.web.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @CLassName ShiroPasswordUtil
 * @Description 统一密码加密工具类
 * @Author LeeJack
 * @Date 2019/3/15/015 16:13
 * @Version 1.0
 */
public class ShiroPasswordUtil {

    /**
     * @return java.lang.String
     * @Description: 获取加盐并且循环多次散列值的密码
     * @Param [password , salt, hashCount]
     * @author LeeJack
     * @Date 16:14 2019/3/15/015
     */
    public static String getShiroPassword(String password , String salt , int hashCount) {
        return new Md5Hash(password , salt , hashCount).toString();
    }
}
