<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta name="format-detection" content="telephone=no" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="format-detection" content="telephone=no" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link rel="stylesheet" type="text/css"
	href="/css/purchase.base.2012.css" />
<link rel="stylesheet" type="text/css" href="/css/purchase.sop.css" />
<title>订单成功页面 - 淘淘商城</title>
<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
<script type="text/javascript" src="/js/base-2011.js" charset="utf-8"></script>
<script type="text/javascript" src="/js/jquery.cookie.js"
	charset="utf-8"></script>
<script type="text/javascript" src="/js/taotao.js" charset="utf-8"></script>
</head>
<body id="mainframe">
	<!--shortcut start-->
	<jsp:include page="commons/shortcut.jsp" />
	<!--shortcut end-->
	<div class="main">
		<div class="success-wrap">
			<div class="w" id="result">
				<div class="m succeed-box">
					<div class="mc success-cont">
						<div class="success-lcol">
							<div class="success-top">
								<b class="succ-icon"></b>
								<h3 class="ftx-02">商品已成功加入购物车！</h3>
							</div>
							<div class="p-item">
								<div class="p-img">
									<a href="https://item.jd.com/4213326.html" target="_blank"><img
										src="./商品已成功加入购物车_files/5a741246N8d605a19.jpg"
										clstag="pageclick|keycount|201601152|11"></a>
								</div>
								<div class="clr"></div>
							</div>
						</div>
						<div class="success-btns success-btns-new">
							<div class="success-ad">
								<a
									href="https://cart.jd.com/addToCart.html?rcd=1&amp;pid=4213326&amp;pc=1&amp;eb=1&amp;rid=1518335599727&amp;em=#none"></a>
							</div>
							<div class="clr"></div>
							<div>
								<a class="btn-tobback" href="https://item.jd.com/4213326.html"
									target="_blank" clstag="pageclick|keycount|201601152|3">查看商品详情</a><a
									class="btn-addtocart"
									href="/cart/cart.html"
									id="GotoShoppingCart" clstag="pageclick|keycount|201601152|4"><b></b>去购物车结算</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="w">
			<!-- links start -->
			<jsp:include page="commons/footer-links.jsp"></jsp:include>
			<!-- links end -->
		</div>
		<!-- footer end -->
</body>
</html>