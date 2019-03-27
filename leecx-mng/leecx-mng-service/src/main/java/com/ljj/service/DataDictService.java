package com.ljj.service;

import com.ljj.common.pojo.JqGridResult;
import com.ljj.pojo.DataDict;

import java.util.List;

/**
 * @CLassName DataDictService
 * @Description 处理数据字典的service
 * @Author LeeJack
 * @Date 2019/3/14/014 15:21
 * @Version 1.0
 */
public interface DataDictService {

    /**
     *
     * @Description: 保存数据字典
     * @Param [dataDict]
     * @return void
     * @author LeeJack
     * @Date 15:23 2019/3/14/014
     */
    public void saveDataDict(DataDict dataDict);

    /**
     *
     * @Description: 查询数据字典列表
     * @Param [typeName, typeCode, page, pageSize]
     * @return com.ljj.common.pojo.JqGridResult
     * @author LeeJack
     * @Date 15:25 2019/3/14/014
     */
    public JqGridResult queryDataDictList(String typeName,String typeCode,Integer page,Integer pageSize);

    /**
     *
     * @Description: 删除数据字典
     * @Param [ddId]
     * @return void
     * @author LeeJack
     * @Date 15:26 2019/3/14/014
     */
    public void deleteDataDictById(Integer ddId);

    /**
     *
     * @Description: 查询数据字典
     * @Param [ddId]
     * @return com.ljj.pojo.DataDict
     * @author LeeJack
     * @Date 15:26 2019/3/14/014
     */
    public DataDict queryDataDictById(Integer ddId);

    /**
     *
     * @Description: 修改数据字典
     * @Param [dataDict]
     * @return void
     * @author LeeJack
     * @Date 15:27 2019/3/14/014
     */
    public void updateDataDictById(DataDict dataDict);

    /**
     *
     * @Description: 根据类型码和ddkey查询ddvalue
     * @Param [typeCode, ddKey]
     * @return java.lang.String
     * @author LeeJack
     * @Date 15:28 2019/3/14/014
     */
    public String queryDataDictValueByCodeKey(String typeCode,String ddKey);

    /**
     *
     * @Description: 根据字典码获取数据字典的列表
     * @Param [typeCode]
     * @return java.util.List<com.ljj.pojo.DataDict>
     * @author LeeJack
     * @Date 15:30 2019/3/14/014
     */
    public List<DataDict> queryDataDictListByTypeCode(String typeCode);

}
