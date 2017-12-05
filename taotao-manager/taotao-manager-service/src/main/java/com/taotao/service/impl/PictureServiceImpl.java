package com.taotao.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictuerService;

/**
 * 上传图片处理服务实现类
 * 
 * @author 入云龙
 * @date 2015年8月15日下午2:59:38
 * @version 1.0
 */
@Service
public class PictureServiceImpl implements PictuerService {

	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;

	@Value("${FTP_PORT}")
	private Integer FTP_PORT;

	@Value("${FTP_USER_NAME}")
	private String FTP_USER_NAME;

	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;

	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;

	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;

	@Override
	public Map uplpadPicture(MultipartFile uploadFile) {
		Map map = new HashMap<>();
		// 判断上传图片是否为空
		if (null == uploadFile || uploadFile.isEmpty()) {
			map.put("error", "1");
			map.put("message", "上传失败");
			return map;
		}
		// 取文件扩展名
		String originalFilename = uploadFile.getOriginalFilename();
		String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
		// 生成新文件名
		String imageName = IDUtils.genImageName();
		// 文件在服务器的存放路径，应该使用日期分隔的目录结构
		DateTime dateTime = new DateTime();
		String filePath = dateTime.toString("/yyyy/MM/dd");
		try {
			FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USER_NAME, FTP_PASSWORD, FTP_BASE_PATH, filePath,
					imageName + ext, uploadFile.getInputStream());
		} catch (Exception e) {
			map.put("error", "1");
			map.put("message", "上传失败,发生异常");
			return map;
		}
		// 返回结果，生成一个可以访问到图片的url返回
		map.put("error", 0);
		map.put("url", IMAGE_BASE_URL + filePath + "/" + imageName + ext);
		return map;
	}

}
