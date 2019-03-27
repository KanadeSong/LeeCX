package com.ljj.web.controller;

import com.ljj.common.utils.LeeJSONResult;
import com.ljj.web.shiro.ShiroDBRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author leechenxiang
 * @version V1.0
 * @Title: CenterController.java
 * @Package com.itzixi.web.controller
 * @Description: shiro测试的controller
 * Copyright: Copyright (c) 2016
 * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
 * @date 2017年7月26日 下午2:08:52
 */
@Controller
@RequestMapping("shiroTest")
public class ShiroTestController extends BaseController {

    @Autowired
    private ShiroDBRealm realm;

    @RequestMapping("/shiroPage")
   //@RequiresPermissions(value = {"company:mng" , "appuser:check" , "company:check"}, logical = Logical.OR)
    public String index() {
		
	/*	ActiveUser user = (ActiveUser)SecurityUtils.getSubject().getPrincipal();
		user.setUsername(new Date() + "");
//		user.isPermitted("company:mng");
		
		System.out.println("当前主体用户有权限进入本controller~");
		
    	ModelAndView modelAndView = new ModelAndView("shiro/shiroPage");
		
		return modelAndView;*/
        String h = "hello";
        return "shiro/shiroPage";
    }

    /**
     * @Title: ShiroTestController.java
     * @Package com.itzixi.web.controller
     * @Description: 强制清除缓存
     * Copyright: Copyright (c) 2017
     * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
     * @author leechenxiang
     * @date 2017年10月27日 下午8:37:20
     * @version V1.0
     */
    @RequestMapping("/clearCache")
    @ResponseBody
    public LeeJSONResult clearCache() {
//		realm.clearCache();
        return LeeJSONResult.ok();
    }
}
