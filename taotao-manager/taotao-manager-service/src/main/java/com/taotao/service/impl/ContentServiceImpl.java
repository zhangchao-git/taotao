package com.taotao.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;

/**
 * 内容管理
 * 
 * @author zhangchao
 *
 */
@Service
public class ContentServiceImpl implements com.taotao.service.ContentService {

	@Autowired
	TbContentMapper contentMapper;

	@Value("${REST_BASE_URL}")
	String REST_BASE_URL;

	@Value("${REST_CONTENT_SYNC_URL}")
	String REST_CONTENT_SYNC_URL;

	@Override
	public EUDataGridResult getContentList(long catId, int page, int rows) {
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(catId);
		PageHelper.startPage(page, rows);
		List<TbContent> contentList = contentMapper.selectByExample(example);
		// 记录总条数
		PageInfo<TbContent> pageInfo = new PageInfo<>(contentList);
		long total = pageInfo.getTotal();
		// 创建一个返回对象
		EUDataGridResult result = new EUDataGridResult();
		result.setTotal(total);
		result.setRows(contentList);
		return result;
	}

	@Override
	public TaotaoResult insertContent(TbContent content) {
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		// 添加缓存同步逻辑
		try {
			HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok();
	}

}
