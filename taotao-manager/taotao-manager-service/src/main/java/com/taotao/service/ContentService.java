package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {

	EUDataGridResult getContentList(long catId, int page, int rows);

	TaotaoResult insertContent(TbContent content);
}
