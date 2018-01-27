package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

public interface ItemService {

	TaotaoResult getItemBaseInfo(long itemId);
	
	TaotaoResult getItemDescInfo(long itemId);
	
	TaotaoResult getItemParamInfo(long itemId);
}
