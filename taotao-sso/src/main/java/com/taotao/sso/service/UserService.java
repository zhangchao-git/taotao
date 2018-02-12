package com.taotao.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

public interface UserService {

	TaotaoResult checkData(String content, int type);

	TaotaoResult createUser(TbUser user);

	TaotaoResult userLogin(String name, String password, HttpServletRequest request, HttpServletResponse response);

	TaotaoResult getUserByToken(String token);
}
