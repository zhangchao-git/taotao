package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

/**
 * 商品分类服务
 * 
 * @author zhangchao
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public CatResult getItemCatList() {
		CatResult catResult = new CatResult();
		// 使用递归
		catResult.setData(getCatList(0));
		return catResult;
	}

	/**
	 * 查询分类列表方法(递归)
	 * 
	 * @param parentId
	 * @return
	 */
	private List<?> getCatList(long parentId) {
		// 创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		// 创建返回值
		List resultList = new ArrayList<>();
		int count = 0;
		for (TbItemCat itemCat : list) {
			// 判断是否叶子节点
			if (itemCat.getIsParent()) {
				CatNode catNode = new CatNode();
				if (parentId == 0) {
					count++;
					if (count >= 15) {
						break;
					}
					catNode.setName("<a href='/products/" + itemCat.getId() + ".html'>" + itemCat.getName() + "</a>");
				} else {
					catNode.setName(itemCat.getName());
				}
				catNode.setUrl("/products/" + itemCat.getId() + ".html");
				catNode.setItem(getCatList(itemCat.getId()));
				resultList.add(catNode);
			} else {
				resultList.add("/products/" + itemCat.getId() + ".html|" + itemCat.getName());
			}

		}
		return resultList;
	}
}
