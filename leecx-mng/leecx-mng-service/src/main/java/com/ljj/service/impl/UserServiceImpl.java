package com.ljj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljj.common.enums.YesOrNo;
import com.ljj.common.pojo.JqGridResult;
import com.ljj.mapper.SysPermissionMapperCustom;
import com.ljj.mapper.SysUserMapper;
import com.ljj.pojo.SysPermission;
import com.ljj.pojo.SysUser;
import com.ljj.pojo.SysUserExample;
import com.ljj.pojo.SysUserExample.Criteria;
import com.ljj.pojo.vo.UserVO;
import com.ljj.service.DataDictService;
import com.ljj.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @CLassName UserServiceImpl
 * @Description 实现类
 * @Author LeeJack
 * @Date 2019/3/14/014 15:20
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private Sid sid;

    @Autowired
    private DataDictService dataDictService;

    @Autowired
    private SysPermissionMapperCustom sysPermissionMapperCustom;

    @Override
    public boolean saveUser(SysUser user) {

        String userId = sid.nextShort();
        user.setId(userId);
        user.setRegistTime(new Date());
        user.setIsDelete(YesOrNo.NO.value);
        int result = userMapper.insert(user);
        return result == 1 ? true : false;
    }


    @Override
    public JqGridResult queryUserList(SysUser user , Integer page , Integer pageSize) {

        PageHelper.startPage(page , pageSize);

        SysUserExample ue = new SysUserExample();
        Criteria uc = ue.createCriteria();

        if (StringUtils.isNotEmpty(user.getUsername())) {
            uc.andUsernameLike("%" + user.getUsername() + "%");
        }

        if (StringUtils.isNotEmpty(user.getNickname())) {
            uc.andNicknameLike("%" + user.getNickname() + "%");
        }

        if (user.getSex() != null) {
            uc.andSexEqualTo(user.getSex());
        }

        uc.andIsDeleteEqualTo(YesOrNo.NO.value);

        List<SysUser> userList = userMapper.selectByExample(ue);

        //转换userlist，使用数据字典
        List<UserVO> newUserList = new ArrayList<UserVO>();

        for (SysUser u : userList) {
            UserVO newUser = new UserVO();
            BeanUtils.copyProperties(u , newUser);

            String sexValue = dataDictService.queryDataDictValueByCodeKey("sex" , String.valueOf(newUser.getSex()));
            newUser.setSexValue(sexValue);

            String jobValue = dataDictService.queryDataDictValueByCodeKey("job" , String.valueOf(newUser.getJob()));
            newUser.setJobValue(jobValue);

            newUserList.add(newUser);
        }

        PageInfo<UserVO> pageList = new PageInfo<UserVO>(newUserList);
        //		PageInfo<SysUser> pageList = new PageInfo<SysUser>(userList);

        JqGridResult grid = new JqGridResult();
        grid.setTotal(pageList.getPages());
        grid.setRows(newUserList);
        grid.setPage(pageList.getPageNum());
        grid.setRecords(pageList.getTotal());

        return grid;
    }

    @Override
    public SysUser queryUserInfoById(String userId) {
        SysUser user = userMapper.selectByPrimaryKey(userId);
        return user;
    }

    @Override
    public void deleteUserById(String userId) {
        //		userMapper.deleteByPrimaryKey(userId);
        SysUser user = new SysUser();
        user.setId(userId);
        user.setIsDelete(YesOrNo.YES.value);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void updateUserById(SysUser user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public boolean queryNicknameIsExist(String nickname , String userId) {

        SysUserExample userExample = new SysUserExample();
        Criteria userCriteria = userExample.createCriteria();
        userCriteria.andNicknameEqualTo(nickname);

        if (StringUtils.isNotEmpty(userId)) {
            userCriteria.andIdNotEqualTo(userId);
        }

        int counts = userMapper.countByExample(userExample);
        return counts > 0;
    }

    @Override
    public boolean queryUsernameIsExist(String username , String userId) {

        SysUserExample userExample = new SysUserExample();
        Criteria userCriteria = userExample.createCriteria();
        userCriteria.andUsernameEqualTo(username);

        if (StringUtils.isNotEmpty(userId)) {
            userCriteria.andIdNotEqualTo(userId);
        }

        int counts = userMapper.countByExample(userExample);
        return counts > 0;
    }


    @Override
    public SysUser queryUserByUsername(String username) {

        SysUserExample userExample = new SysUserExample();
        Criteria userCriteria = userExample.createCriteria();
        userCriteria.andUsernameEqualTo(username);
        List<SysUser> userList = userMapper.selectByExample(userExample);

        if (userList != null && !userList.isEmpty()) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public List<SysPermission> findPermissionListByUserId(String userId) throws Exception {
        return sysPermissionMapperCustom.findPermissionListByUserId(userId);
    }
}
