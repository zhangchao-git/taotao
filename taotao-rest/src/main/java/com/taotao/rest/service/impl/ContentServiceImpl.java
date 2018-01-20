package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ContentService;

/**
 * 内容管理
 * 
 * @author zhangchao
 *
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;

	@Override
	public List<TbContent> getContentList(long contentCid) {
		try {
			String resultJson = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentCid + "");
			if (!StringUtils.isBlank(resultJson)) {
				List<TbContent> resultList = JsonUtils.jsonToList(resultJson, TbContent.class);
				return resultList;
			}
		} catch (Exception e) {
		}
		// 根据内容分类Id查询内容列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCid);
		List<TbContent> result = contentMapper.selectByExample(example);
		// 向缓存添加内容
		try {
			// 需要先把list转换成字符串
			String cacheString = JsonUtils.objectToJson(result);
			jedisClient.hset(INDEX_CONTENT_REDIS_KEY, contentCid + "", cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
