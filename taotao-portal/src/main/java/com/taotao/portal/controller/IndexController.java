package com.taotao.portal.controller;

import java.awt.PageAttributes.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.portal.service.ContentService;

@Controller
public class IndexController {

	@Autowired
	private ContentService ContentService;

	@RequestMapping("/index")
	public String showIndex(Model model) {
		String adJson = ContentService.getContentList();
		model.addAttribute("ad1", adJson);
		return "index";
	}

	@RequestMapping(value = "/httpclient/post", method = RequestMethod.POST, produces=org.springframework.http.MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String testPost(String name, String password) {
		System.out.println("name:" + name + "\tpassword:" + password);
		return "name:" + name + "\tpassword:" + password;
		// return TaotaoResult.ok();
	}
}
