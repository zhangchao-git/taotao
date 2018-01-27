package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemService;

/**
 * 商品信息管理
 * 
 * @author zhangchao
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;

	@Value("${REDIS_ITEM_EXPIRE}")
	private int REDIS_ITEM_EXPIRE;

	@Override
	public TaotaoResult getItemBaseInfo(long itemId) {
		// 添加缓存逻辑
		// 从商品中获取商品信息
		try {
			String itemStr = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":BASE");
			if (StringUtils.isNotBlank(itemStr)) {
				TbItem item = JsonUtils.jsonToPojo(itemStr, TbItem.class);
				return TaotaoResult.ok(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbItem itemList = itemMapper.selectByPrimaryKey(itemId);
		// 把商品信息写入缓存,需要设置Keys有效期
		try {
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":BASE", JsonUtils.objectToJson(itemList));
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":BASE", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok(itemList);
	}

	@Override
	public TaotaoResult getItemDescInfo(long itemId) {
		// 添加缓存逻辑
		// 从商品中获取商品信息
		try {
			String itemStr = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":DESC");
			if (StringUtils.isNotBlank(itemStr)) {
				TbItemDesc itemDesc = JsonUtils.jsonToPojo(itemStr, TbItemDesc.class);
				return TaotaoResult.ok(itemDesc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		// 把商品信息写入缓存,需要设置Keys有效期
		try {
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":DESC", JsonUtils.objectToJson(itemDesc));
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":DESC", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok(itemDesc);
	}

	@Override
	public TaotaoResult getItemParamInfo(long itemId) {
		// 添加缓存
		try {
			// 添加缓存逻辑
			// 从缓存中取商品信息，商品id对应的信息
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":PARAM");
			// 判断是否有值
			if (!StringUtils.isBlank(json)) {
				// 把json转换成java对象
				TbItemParamItem paramItem = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
				return TaotaoResult.ok(paramItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 根据商品id查询规格参数
		// 设置查询条件
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		// 执行查询
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0) {
			TbItemParamItem paramItem = list.get(0);
			try {
				// 把商品信息写入缓存
				jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":PARAM", JsonUtils.objectToJson(paramItem));
				// 设置key的有效期
				jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":PARAM", REDIS_ITEM_EXPIRE);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return TaotaoResult.ok(paramItem);
		}
		return TaotaoResult.build(400, "无此商品规格");

	}

}
