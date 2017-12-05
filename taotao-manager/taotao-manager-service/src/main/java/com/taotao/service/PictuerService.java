package com.taotao.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface PictuerService {

	Map uplpadPicture(MultipartFile uploadFile);
}
