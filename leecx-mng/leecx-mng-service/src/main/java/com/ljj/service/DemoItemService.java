package com.ljj.service;

import com.ljj.common.pojo.JqGridResult;
import com.ljj.pojo.DemoItem;

/**
 * @CLassName DemoItemService
 * @Description DEMO-商品service
 * @Author LeeJack
 * @Date 2019/3/14/014 15:21
 * @Version 1.0
 **/
public interface DemoItemService {

    /**
     *
     * @Description: 保存商品
     * @Param [item]
     * @return void
     * @author LeeJack
     * @Date 15:33 2019/3/14/014
     */
    public void saveItem(DemoItem item);

    /**
     *
     * @Description: 分页查询商品列表
     * @Param [page, pageSize]
     * @return com.ljj.common.pojo.JqGridResult
     * @author LeeJack
     * @Date 15:34 2019/3/14/014
     */
    public JqGridResult queryItemList(Integer page,Integer pageSize);

    /**
     *
     * @Description: 根据ID查询商品信息
     * @Param [itemId]
     * @return com.ljj.pojo.DemoItem
     * @author LeeJack
     * @Date 15:35 2019/3/14/014
     */
    public DemoItem queryItemById(String itemId);

    /**
     *
     * @Description: 修改商品信息
     * @Param [item]
     * @return void
     * @author LeeJack
     * @Date 15:36 2019/3/14/014
     */
    public void updateItem(DemoItem item);

    /**
     *
     * @Description: 删除商品
     * @Param [itemId]
     * @return void
     * @author LeeJack
     * @Date 15:37 2019/3/14/014
     */
    public void deleteItem(String itemId);
}
