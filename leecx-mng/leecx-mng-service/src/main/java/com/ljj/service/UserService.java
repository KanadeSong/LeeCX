package com.ljj.service;

import com.ljj.common.pojo.JqGridResult;
import com.ljj.pojo.SysPermission;
import com.ljj.pojo.SysUser;

import java.util.List;

/**
 *
 * @Description: 处理用户的service
 * @Param
 * @return
 * @author LeeJack
 * @Date 15:09 2019/3/14/014
 */
public interface UserService {

    /**
     *
     * @Description: 保存用户
     * @Param [user]
     * @return boolean
     * @author LeeJack
     * @Date 15:09 2019/3/14/014
     */
    public boolean saveUser(SysUser user);

    /**
     *
     * @Description: 查询用户列表
     * @Param [user, page, pageSize]
     * @return com.ljj.common.pojo.JqGridResult
     * @author LeeJack
     * @Date 15:09 2019/3/14/014
     */
    public JqGridResult queryUserList(SysUser user,Integer page,Integer pageSize);

    /**
     *
     * @Description: 查询用户详细信息
     * @Param [userId]
     * @return com.ljj.pojo.SysUser
     * @author LeeJack
     * @Date 15:11 2019/3/14/014
     */
    public SysUser queryUserInfoById(String userId);

    /**
     *
     * @Description: 删除用户
     * @Param [userId]
     * @return void
     * @author LeeJack
     * @Date 15:12 2019/3/14/014
     */
    public void deleteUserById(String userId);

    /**
     *
     * @Description: 修改用户
     * @Param [user]
     * @return void
     * @author LeeJack
     * @Date 15:13 2019/3/14/014
     */
    public void updateUserById(SysUser user);

    /**
     *
     * @Description: 检查昵称是否存在
     * @Param [nickname, userId]
     * @return boolean
     * @author LeeJack
     * @Date 15:14 2019/3/14/014
     */
    public boolean queryNicknameIsExist(String nickname,String userId);

    /**
     *
     * @Description: 检查姓名是否存在
     * @Param [nickname, userId]
     * @return boolean
     * @author LeeJack
     * @Date 15:14 2019/3/14/014
     */
    public boolean queryUsernameIsExist(String username,String userId);

    /**
     *
     * @Description: 根据用户查询用户信息，如果查不到则返回空，用于登录查询
     * @Param [username]
     * @return com.ljj.pojo.SysUser
     * @author LeeJack
     * @Date 15:16 2019/3/14/014
     */
    public SysUser queryUserByUsername(String username);

    /**
     *
     * @Description: 根据用户id查询用户的权限资源字符串percode
     * @Param [userId]
     * @return java.util.List<com.ljj.pojo.SysPermission>
     * @author LeeJack
     * @Date 16:54 2019/3/15/015
     */
    public List<SysPermission> findPermissionListByUserId(String userId) throws Exception;

}
