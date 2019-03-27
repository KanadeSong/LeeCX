package com.ljj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljj.common.enums.YesOrNo;
import com.ljj.common.pojo.JqGridResult;
import com.ljj.mapper.DataDictMapper;
import com.ljj.pojo.DataDict;
import com.ljj.pojo.DataDictExample;
import com.ljj.pojo.DataDictExample.Criteria;
import com.ljj.service.DataDictService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataDictServiceImpl implements DataDictService {
	
	@Autowired
	private DataDictMapper dataDictMapper;
	
//	@Autowired
//	private JedisClient jedis;
	
	//@Autowired
	//private RedisOperator jedis;
	
	@Override
	public void saveDataDict(DataDict dataDict) {
		
		dataDictMapper.insert(dataDict);
	}

	@Override
	public JqGridResult queryDataDictList(String typeName, String typeCode, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		
		DataDictExample dde = new DataDictExample();
		dde.setOrderByClause(" type_code,ddkey asc ");
		Criteria dc = dde.createCriteria();
		
		if (StringUtils.isNotEmpty(typeName)) {
			dc.andTypeNameLike("%" + typeName + "%");
		}
		if (StringUtils.isNotEmpty(typeCode)) {
			dc.andTypeCodeLike("%" + typeCode + "%");
		}
		
		List<DataDict> userList = dataDictMapper.selectByExample(dde);
		
		PageInfo<DataDict> pageList = new PageInfo<DataDict>(userList);
		
		JqGridResult grid = new JqGridResult();
		grid.setTotal(pageList.getPages());
		grid.setRows(userList);
		grid.setPage(pageList.getPageNum());
		grid.setRecords(pageList.getTotal());
		
		return grid;
	}

	@Override
	public void deleteDataDictById(Integer ddId) {
		dataDictMapper.deleteByPrimaryKey(ddId);
	}

	@Override
	public void updateDataDictById(DataDict dataDict) {
		dataDictMapper.updateByPrimaryKey(dataDict);
	}

	@Override
	public DataDict queryDataDictById(Integer ddId) {
		return dataDictMapper.selectByPrimaryKey(ddId);
	}
	
	@Override
	public String queryDataDictValueByCodeKey(String typeCode, String ddKey) {
		//String redisKey = "redis_datadict:" + typeCode + ":" + ddKey;
		// 从缓存中获取数据字典的值，如果没有该值，则从数据库中获取，最后再存入redis中
		/*String redisDdvalue = jedis.get(redisKey);
		if (StringUtils.isNotEmpty(redisDdvalue)) {
			return redisDdvalue;
		}*/
		
		DataDictExample dataDictExample = new DataDictExample();
		Criteria dataDictCriteria = dataDictExample.createCriteria();
		dataDictCriteria.andTypeCodeEqualTo(typeCode);
		dataDictCriteria.andDdkeyEqualTo(ddKey);
		dataDictCriteria.andIsShowEqualTo(YesOrNo.YES.value);
		List<DataDict> list = dataDictMapper.selectByExample(dataDictExample);
		if (list != null && list.size() > 0) {
			DataDict dd = (DataDict)list.get(0);
			
			String ddvalue = dd.getDdvalue();
			// 在Redis中设置数据字典的值
			//jedis.set(redisKey, ddvalue);
			
			return ddvalue;
		}
		
		return "";
	}
	
	@Override
	public List<DataDict> queryDataDictListByTypeCode(String typeCode) {
		String redisKey = "redis_datadict_list:" + typeCode;
		// 从缓存中获取数据字典的值，如果没有该值，则从数据库中获取，最后再存入redis中
		/*String redisDdListJson = jedis.get(redisKey);
		if (StringUtils.isNotEmpty(redisDdListJson)) {
			List<DataDict> list = JsonUtils.jsonToList(redisDdListJson, DataDict.class);
			return list;
		}*/
		
		DataDictExample dataDictExample = new DataDictExample();
		Criteria dataDictCriteria = dataDictExample.createCriteria();
		dataDictCriteria.andTypeCodeEqualTo(typeCode);
		dataDictCriteria.andIsShowEqualTo(YesOrNo.YES.value);
		List<DataDict> list = dataDictMapper.selectByExample(dataDictExample);
		
		// 在Redis中设置数据字典的值
		//jedis.set(redisKey, JsonUtils.objectToJson(list));
		
		return list;
	}
}
