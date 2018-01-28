package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

public interface UserService {

	TaotaoResult checkData(String content, int type);

	TaotaoResult createUser(TbUser user);

	TaotaoResult userLogin(String name, String password);

	TaotaoResult getUserByToken(String token);
}
