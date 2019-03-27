package com.ljj.mapper;

import com.ljj.pojo.SysPermission;

import java.util.List;

/**
 * @CLassName SysPermissionMapperCustom
 * @Description 资源权限自定义mapper
 * @Author LeeJack
 * @Date 2019/3/15/015 16:33
 * @Version 1.0
 */
public interface SysPermissionMapperCustom {

    /**
     *
     * @Description: 根据userid动态查询菜单资源url
     * @Param [userId]
     * @return java.util.List<com.ljj.pojo.SysPermission>
     * @author LeeJack
     * @Date 16:34 2019/3/15/015
     */
    public List<SysPermission> findMenuListUserId(String userId)throws Exception;

    /**
     *
     * @Description: 根据userid查询权限资源字符串
     * @Param [userId]
     * @return java.util.List<com.ljj.pojo.SysPermission>
     * @author LeeJack
     * @Date 16:35 2019/3/15/015
     */
    public List<SysPermission> findPermissionListByUserId(String userId)throws Exception;

}
