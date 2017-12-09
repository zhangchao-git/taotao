package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;

/**
 * 商品规格参数管理
 * 
 * @author zhangchao
 *
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;

	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public TaotaoResult getItemParamByCid(@PathVariable Long itemCatId) {
		return itemParamService.getItemParamByCid(itemCatId);
	}

	@RequestMapping("/save/{itemCatId}")
	@ResponseBody
	public TaotaoResult insertItemParam(@PathVariable Long itemCatId, String paramData) {
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(itemCatId);
		itemParam.setParamData(paramData);
		return itemParamService.insertItemParam(itemParam);
	}

}
