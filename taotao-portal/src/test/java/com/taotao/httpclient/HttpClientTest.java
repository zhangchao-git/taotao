package com.taotao.httpclient;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.StringEncoder;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {

	public void doGet() throws Exception {
		// HttpClient创建对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建get对象
		HttpGet get = new HttpGet("http://www.baidu.com");
		// 执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		// 获取响应结果
		System.out.println(response.getStatusLine().getStatusCode());
		HttpEntity entity = response.getEntity();
		System.out.println(EntityUtils.toString(entity, "utf-8"));
		// 关闭httpclient
		response.close();
		httpClient.close();
	}

	public void doGetWithParam() throws Exception {
		// HttpClient创建对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建URI对象
		URIBuilder uriBuilder = new URIBuilder("http://www.baidu.com/s");
		uriBuilder.addParameter("wd", "花千骨");
		// 创建get对象
		HttpGet get = new HttpGet(uriBuilder.build());
		// 执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		// 获取响应结果
		System.out.println(response.getStatusLine().getStatusCode());
		HttpEntity entity = response.getEntity();
		System.out.println(EntityUtils.toString(entity, "utf-8"));
		// 关闭httpclient
		response.close();
		httpClient.close();
	}

	public void doPost() throws Exception {
		// HttpClient创建对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建post对象
		HttpPost post = new HttpPost("http://localhost:8082/httpclient/post.html");
		// 执行post请求
		CloseableHttpResponse response = httpClient.execute(post);
		// 获取响应结果
		System.out.println(response.getStatusLine().getStatusCode());
		HttpEntity entity = response.getEntity();
		System.out.println(EntityUtils.toString(entity, "utf-8"));
		// 关闭httpclient
		response.close();
		httpClient.close();
	}

	@Test
	public void doPostWithParam() throws Exception {
		// HttpClient创建对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建post对象
		HttpPost post = new HttpPost("http://localhost:8082/httpclient/post.html");
		// 创建一个Entity.模拟一个表单
		List<NameValuePair> kvList = new ArrayList<>();
		kvList.add(new BasicNameValuePair("name", "张三"));
		kvList.add(new BasicNameValuePair("password", "123"));
		// 包装成entity对象
		StringEntity entity = new UrlEncodedFormEntity(kvList);
		// 设置请求内容
		post.setEntity(entity);
		// 执行post请求
		CloseableHttpResponse response = httpClient.execute(post);
		// 获取响应结果
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(EntityUtils.toString(entity, "utf-8"));
		// 关闭httpclient
		response.close();
		httpClient.close();
	}
}
