package com.taotao.portal.service;

import com.taotao.pojo.TbItemDesc;
import com.taotao.portal.pojo.ItemInfo;

public interface ItemService {

	ItemInfo getItemById(Long tiemId);

	String getItemDescById(long itemId);

	String getItemParam(long itemId);
}
