package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;

/**
 * 内容分类管理
 * 
 * @author zhangchao
 *
 */
@RequestMapping("/content/category")
@Controller
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;

	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getContenCategoryList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		return contentCategoryService.getCategoryList(parentId);
	}

	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult insertContentCatrgory(long parentId, String name) {
		return contentCategoryService.insertContentCategpry(parentId, name);
	}
}
