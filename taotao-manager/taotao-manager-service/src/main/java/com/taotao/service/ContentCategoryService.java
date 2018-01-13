package com.taotao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;

/**
 * 内容分类服务
 * 
 * @author zhangchao
 *
 */
public interface ContentCategoryService {

	List<EUTreeNode> getCategoryList(long parentId);

	TaotaoResult insertContentCategpry(long parentId, String name);
}
