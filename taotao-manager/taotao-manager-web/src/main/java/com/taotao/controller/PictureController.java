package com.taotao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PictuerService;

/**
 * 上传图片处理
 * 
 * @author zhangchao
 *
 */
@Controller
public class PictureController {

	@Autowired
	private PictuerService pictuerService;

	@RequestMapping("/pic/upload")
	@ResponseBody
	public Map upload(MultipartFile uploadFile) {
		Map result = pictuerService.uplpadPicture(uploadFile);
		String json = JsonUtils.objectToJson(result);
		return result;
	}

}
