package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

/**
 * 商品分类管理
 * 
 * @author zhangchao
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;

	/**
	 * 查询类目
	 */
	@Override
	public List<EUTreeNode> getItemCatList(long parentId) {
		// 创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 根据条件查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<>();
		for (TbItemCat itemCat : list) {
			EUTreeNode euTreeNode = new EUTreeNode();
			euTreeNode.setId(itemCat.getId());
			euTreeNode.setText(itemCat.getName());
			euTreeNode.setState(itemCat.getIsParent() ? "closed" : "open");
			resultList.add(euTreeNode);
		}
		return resultList;
	}

}
