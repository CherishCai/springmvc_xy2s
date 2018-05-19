

package com.controller;

import com.dao.*;
import com.entity.*;
import com.util.VeDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/index")
public class IndexController extends BaseController {

	@Autowired
	private BbsDAO bbsDAO;
	@Autowired
	private UsersDAO usersDAO;
	@Autowired
	private CateDAO cateDAO;
	@Autowired
	private CartDAO cartDAO;
	@Autowired
	private FavDAO favDAO;
	@Autowired
	private OrdersDAO ordersDAO;
	@Autowired
	private RebbsDAO rebbsDAO;
	@Autowired
	private ArticleDAO articleDAO;
	@Autowired
	private GoodsDAO goodsDAO;
	@Autowired
	private TopicDAO topicDAO;

	// 公共方法 提供公共查询数据
	private void front(Map<String, Object> map) {
		getRequest().setAttribute("title", "网上二手市场");
		List<Cate> cateList = this.cateDAO.getAllCate();
		map.put("cateList", cateList);
		List<Goods> hotList = this.goodsDAO.getGoodsByHot();
		map.put("hotList", hotList);
	}

	// 首页显示的控制器
	@RequestMapping("index.shtml")
	public String index(Map<String, Object> map, HttpServletRequest request) {
		this.front(map);
		List<Cate> cateList = this.cateDAO.getAllCate();
		List<Cate> frontList = new ArrayList<Cate>();
		for (Cate cate : cateList) {
			List<Goods> goodsList = this.goodsDAO.getGoodsByCate(cate.getCateid());
			cate.setGoodsList(goodsList);
			frontList.add(cate);
		}
		map.put("frontList", frontList);
		List<Goods> newsList = this.goodsDAO.getGoodsByNews();
		map.put("newsList", newsList);
		List<Article> articleList = this.articleDAO.getFrontArticle();
		map.put("articleList", articleList);
		return "users/index";
	}

	// 按分类查询
	@RequestMapping("cate.shtml")
	public String cate(Map<String, Object> map, String id, String number) {
		this.front(map);
		Goods goods = new Goods();
		goods.setCateid(id);
		goods.setStatus("上架");
		List<Goods> goodsList = new ArrayList<Goods>();
		List<Goods> tempList = new ArrayList<Goods>();
		tempList = this.goodsDAO.getGoodsByCond(goods);
		int pageNumber = tempList.size();
		int maxPage = pageNumber;
		if (maxPage % 12 == 0) {
			maxPage = maxPage / 12;
		} else {
			maxPage = maxPage / 12 + 1;
		}
		if (number == null) {
			number = "0";
		}
		try {
			if (Integer.parseInt(number) < 0) {
				number = "0";
			}
		} catch (Exception ex) {
			number = "0";
		}
		if (Integer.parseInt(number) > maxPage) {
			number = "0";
		}
		int start = Integer.parseInt(number) * 12;
		int over = (Integer.parseInt(number) + 1) * 12;
		int count = pageNumber - over;
		System.out.println(start);
		System.out.println(over);
		System.out.println(count);
		if (count <= 0) {
			over = pageNumber;
		}
		System.out.println(over);
		for (int i = start; i < over; i++) {
			Goods x = tempList.get(i);
			goodsList.add(x);
		}
		String html = "";
		StringBuffer buffer = new StringBuffer();
		buffer.append("&nbsp;&nbsp;共为");
		buffer.append(maxPage);
		buffer.append("页&nbsp; 共有");
		buffer.append(pageNumber);
		buffer.append("条&nbsp; 当前为第");
		buffer.append((Integer.parseInt(number) + 1));
		buffer.append("页 &nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("首页");
		} else {
			buffer.append("<a href=\"index/cate.shtml?number=0&id=" + id + "\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"index/cate.shtml?number=" + (Integer.parseInt(number) - 1) + "&id=" + id
					+ "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"index/cate.shtml?number=" + (Integer.parseInt(number) + 1) + "&id=" + id
					+ "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"index/cate.shtml?number=" + (maxPage - 1) + "&id=" + id + "\">尾页</a>");
		}
		html = buffer.toString();
		map.put("html", html);
		map.put("goodsList", goodsList);
		return "users/list";
	}

	// 全部商品
	@RequestMapping("all.shtml")
	public String all(Map<String, Object> map, String number) {
		this.front(map);
		Goods goods = new Goods();
		goods.setStatus("上架");
		List<Goods> goodsList = new ArrayList<Goods>();
		List<Goods> tempList = new ArrayList<Goods>();
		tempList = this.goodsDAO.getAllGoods();
		int pageNumber = tempList.size();
		int maxPage = pageNumber;
		if (maxPage % 12 == 0) {
			maxPage = maxPage / 12;
		} else {
			maxPage = maxPage / 12 + 1;
		}
		if (number == null) {
			number = "0";
		}
		try {
			if (Integer.parseInt(number) < 0) {
				number = "0";
			}
		} catch (Exception ex) {
			number = "0";
		}
		if (Integer.parseInt(number) > maxPage) {
			number = "0";
		}
		int start = Integer.parseInt(number) * 12;
		int over = (Integer.parseInt(number) + 1) * 12;
		int count = pageNumber - over;
		System.out.println(start);
		System.out.println(over);
		System.out.println(count);
		if (count <= 0) {
			over = pageNumber;
		}
		System.out.println(over);
		for (int i = start; i < over; i++) {
			Goods x = tempList.get(i);
			goodsList.add(x);
		}
		String html = "";
		StringBuffer buffer = new StringBuffer();
		buffer.append("&nbsp;&nbsp;共为");
		buffer.append(maxPage);
		buffer.append("页&nbsp; 共有");
		buffer.append(pageNumber);
		buffer.append("条&nbsp; 当前为第");
		buffer.append((Integer.parseInt(number) + 1));
		buffer.append("页 &nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("首页");
		} else {
			buffer.append("<a href=\"index/all.shtml?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"index/all.shtml?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"index/all.shtml?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"index/all.shtml?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		map.put("html", html);
		map.put("goodsList", goodsList);
		return "users/list";
	}

	// 新品上架
	@RequestMapping("news.shtml")
	public String news(Map<String, Object> map, String number) {
		this.front(map);
		List<Goods> goodsList = this.goodsDAO.getGoodsByNews();
		map.put("goodsList", goodsList);
		return "users/list";
	}

	// 查询商品
	@RequestMapping("query.shtml")
	public String query(Map<String, Object> map, String number, String name) {
		this.front(map);
		Goods goods = new Goods();
		goods.setGoodsname(name);
		goods.setStatus("上架");
		List<Goods> goodsList = new ArrayList<Goods>();
		List<Goods> tempList = new ArrayList<Goods>();
		tempList = this.goodsDAO.getGoodsByLike(goods);
		int pageNumber = tempList.size();
		int maxPage = pageNumber;
		if (maxPage % 12 == 0) {
			maxPage = maxPage / 12;
		} else {
			maxPage = maxPage / 12 + 1;
		}
		if (number == null) {
			number = "0";
		}
		try {
			if (Integer.parseInt(number) < 0) {
				number = "0";
			}
		} catch (Exception ex) {
			number = "0";
		}
		if (Integer.parseInt(number) > maxPage) {
			number = "0";
		}
		int start = Integer.parseInt(number) * 12;
		int over = (Integer.parseInt(number) + 1) * 12;
		int count = pageNumber - over;
		System.out.println(start);
		System.out.println(over);
		System.out.println(count);
		if (count <= 0) {
			over = pageNumber;
		}
		System.out.println(over);
		for (int i = start; i < over; i++) {
			Goods x = tempList.get(i);
			goodsList.add(x);
		}
		String html = "";
		StringBuffer buffer = new StringBuffer();
		buffer.append("&nbsp;&nbsp;共为");
		buffer.append(maxPage);
		buffer.append("页&nbsp; 共有");
		buffer.append(pageNumber);
		buffer.append("条&nbsp; 当前为第");
		buffer.append((Integer.parseInt(number) + 1));
		buffer.append("页 &nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("首页");
		} else {
			buffer.append("<a href=\"index/query.shtml?number=0&name=" + name + "\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"index/query.shtml?number=" + (Integer.parseInt(number) - 1) + "&name=" + name
					+ "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"index/query.shtml?number=" + (Integer.parseInt(number) + 1) + "&name=" + name
					+ "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"index/cate.shtml?number=" + (maxPage - 1) + "&name=" + name + "\">尾页</a>");
		}
		html = buffer.toString();
		map.put("html", html);
		map.put("goodsList", goodsList);
		return "users/list";
	}

	// 商品详情
	@RequestMapping("detail.shtml")
	public String detail(Map<String, Object> map, String id) {
		this.front(map);
		Goods goods = this.goodsDAO.getGoodsById(id);
		goods.setHits("" + (Integer.parseInt(goods.getHits()) + 1));
		this.goodsDAO.updateGoods(goods);
		map.put("goods", goods);
		Topic topic = new Topic();
		topic.setGoodsid(id);
		List<Topic> topicList = this.topicDAO.getTopicByCond(topic);
		map.put("topicList", topicList);
		map.put("tnum", topicList.size());
		return "users/detail";
	}

	@RequestMapping("addTopic.shtml")
	public String addTopic(Map<String, Object> map) {
		this.front(map);
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		Topic topic = new Topic();
		topic.setTopicid(VeDate.getStringDatex());
		topic.setAddtime(VeDate.getStringDateShort());
		topic.setContents(this.getRequest().getParameter("contents"));
		topic.setGoodsid(this.getRequest().getParameter("goodsid"));
		topic.setNum(this.getRequest().getParameter("num"));
		topic.setUsersid(userid);
		this.topicDAO.insertTopic(topic);
		return "redirect:/index/detail.shtml?id=" + topic.getGoodsid();
	}

	// 商城公告
	@RequestMapping("article.shtml")
	public String article(Map<String, Object> map) {
		this.front(map);
		List<Article> articleList = this.articleDAO.getAllArticle();
		map.put("articleList", articleList);
		return "users/article";
	}

	@RequestMapping("read.shtml")
	public String read(Map<String, Object> map, String id) {
		this.front(map);
		Article article = this.articleDAO.getArticleById(id);
		article.setHits("" + (Integer.parseInt(article.getHits()) + 1));
		this.articleDAO.updateArticle(article);
		map.put("article", article);
		return "users/read";
	}

	// 添加收藏
	@RequestMapping("addfav.shtml")
	public String addfav(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		Fav fav = new Fav();
		fav.setFavid(VeDate.getStringDatex());
		fav.setAddtime(VeDate.getStringDateShort());
		fav.setGoodsid(getRequest().getParameter("id"));
		fav.setUsersid(userid);
		this.favDAO.insertFav(fav);
		return "redirect:/index/myfav.shtml";
	}

	// 我的收藏
	@RequestMapping("myfav.shtml")
	public String myfav(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		Fav fav = new Fav();
		fav.setUsersid(userid);
		List<Fav> favList = this.favDAO.getFavByCond(fav);
		map.put("favList", favList);
		return "users/myfav";
	}

	// 删除收藏
	@RequestMapping("deletefav.shtml")
	public String deletefav(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		this.favDAO.deleteFav(getRequest().getParameter("id"));
		return "redirect:/index/myfav.shtml";
	}

	// 准备发布商品
	@RequestMapping("preGoods.shtml")
	public String preGoods(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		return "users/addgoods";
	}

	// 发布商品
	@RequestMapping("addgoods.shtml")
	public String addgoods(Map<String, Object> map, Goods goods) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		goods.setAddtime(VeDate.getStringDateShort());
		goods.setHits("0");
		goods.setUsersid(userid);
		goods.setGoodsid(VeDate.getStringDatex());
		goods.setStatus("上架");
		this.goodsDAO.insertGoods(goods);
		return "redirect:/index/preGoods.shtml";
	}

	// 我的商品
	@RequestMapping("mygoods.shtml")
	public String mygoods(Map<String, Object> map, String number) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		Goods goods = new Goods();
		goods.setUsersid(userid);
		List<Goods> goodsList = new ArrayList<Goods>();
		List<Goods> tempList = new ArrayList<Goods>();
		tempList = this.goodsDAO.getGoodsByCond(goods);
		int pageNumber = tempList.size();
		int maxPage = pageNumber;
		if (maxPage % 10 == 0) {
			maxPage = maxPage / 10;
		} else {
			maxPage = maxPage / 10 + 1;
		}
		if (number == null) {
			number = "0";
		}
		int start = Integer.parseInt(number) * 10;
		int over = (Integer.parseInt(number) + 1) * 10;
		int count = pageNumber - over;
		if (count <= 0) {
			over = pageNumber;
		}
		for (int i = start; i < over; i++) {
			Goods goods1 = tempList.get(i);
			goodsList.add(goods1);
		}
		String html = "";
		StringBuffer buffer = new StringBuffer();
		buffer.append("&nbsp;&nbsp;共为");
		buffer.append(maxPage);
		buffer.append("页&nbsp; 共有");
		buffer.append(pageNumber);
		buffer.append("条&nbsp; 当前为第");
		buffer.append((Integer.parseInt(number) + 1));
		buffer.append("页 &nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("首页");
		} else {
			buffer.append("<a href=\"index/mygoods.shtml?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"index/mygoods.shtml?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"index/mygoods.shtml?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"index/mygoods.shtml?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		map.put("html", html);
		map.put("goodsList", goodsList);
		return "users/mygoods";
	}

	// 准备编辑商品
	@RequestMapping("getGoodsById.shtml")
	public String getGoodsById(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		map.put("goods", this.goodsDAO.getGoodsById(getRequest().getParameter("id")));
		return "users/editgoods";
	}

	// 编辑商品 updateGoods
	@RequestMapping("updateGoods.shtml")
	public String updateGoods(Map<String, Object> map, Goods goods) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		this.goodsDAO.updateGoods(goods);
		return "redirect:/index/mygoods.shtml";
	}

	// 删除商品
	@RequestMapping("deletegoods.shtml")
	public String deletegoods(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		this.goodsDAO.deleteGoods(getRequest().getParameter("id"));
		return "redirect:/index/mygoods.shtml";
	}

	// 上架下架
	@RequestMapping("status.shtml")
	public String status(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		Goods goods = this.goodsDAO.getGoodsById(getRequest().getParameter("id"));
		String status = "上架";
		if (goods.getStatus().equals(status)) {
			status = "下架";
		}
		goods.setStatus(status);
		this.goodsDAO.updateGoods(goods);
		return "redirect:/index/mygoods.shtml";
	}

	// 添加商品到购物车
	@RequestMapping("addcart.shtml")
	public String addcart(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		Cart cart = new Cart();
		cart.setCartid(VeDate.getStringDatex());
		cart.setAddtime(VeDate.getStringDateShort());
		cart.setGoodsid(getRequest().getParameter("goodsid"));
		cart.setPrice(getRequest().getParameter("price"));
		cart.setUsersid(userid);
		cart.setNum(getRequest().getParameter("num"));
		this.cartDAO.insertCart(cart);
		return "redirect:/index/cart.shtml";
	}

	// 查看购物车
	@RequestMapping("cart.shtml")
	public String cart(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		Cart cart = new Cart();
		cart.setUsersid(userid);
		List<Cart> cartList = this.cartDAO.getCartByCond(cart);
		map.put("cartList", cartList);
		return "users/cart";
	}

	// 删除购物车中的商品
	@RequestMapping("deletecart.shtml")
	public String deletecart(Map<String, Object> map, String id) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		this.cartDAO.deleteCart(id);
		return "redirect:/index/cart.shtml";
	}

	// 准备结算
	@RequestMapping("preCheckout.shtml")
	public String preCheckout(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		Cart cart = new Cart();
		cart.setUsersid(userid);
		List<Cart> cartList = this.cartDAO.getCartByCond(cart);
		if (cartList.size() == 0) {
			getRequest().getSession().setAttribute("message", "请选购商品");
			return "redirect:/index/cart.shtml";
		}
		return "users/checkout";
	}

	// 结算
	@RequestMapping("checkout.shtml")
	public String checkout(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		Cart cart1 = new Cart();
		cart1.setUsersid(userid);
		List<Cart> cartList = this.cartDAO.getCartByCond(cart1);
		if (cartList.size() == 0) {
			getRequest().getSession().setAttribute("message", "请选购商品");
			return "redirect:/index/cart.shtml";
		} else {
			// 获取一个1000-9999的随机数 防止同时提交
			Random rand = new Random();
			String ordercode = "PD" + VeDate.getStringDatex() + (rand.nextInt(900) + 100);
			for (Cart cart : cartList) {
				Goods goods = this.goodsDAO.getGoodsById(cart.getGoodsid());
				Orders orders = new Orders();
				orders.setAddress(getRequest().getParameter("address"));
				orders.setAddtime(VeDate.getStringDateShort());
				orders.setContact(getRequest().getParameter("contact"));
				orders.setOrdercode(ordercode);
				orders.setOrdersid(UUID.randomUUID().toString().replace("-", ""));
				orders.setReceiver(getRequest().getParameter("receiver"));
				orders.setStatus("未付款");
				orders.setUsersid(userid);
				orders.setSellerid(goods.getUsersid());
				orders.setNum(cart.getNum());
				orders.setGoodsid(goods.getGoodsid());
				orders.setPrice(goods.getPrice());
				this.ordersDAO.insertOrders(orders);
				this.cartDAO.deleteCart(cart.getCartid());
			}
		}
		return "redirect:/index/showOrders.shtml";
	}

	// 查看订购
	@RequestMapping("showOrders.shtml")
	public String showOrders(String number, Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		Orders orders = new Orders();
		orders.setUsersid(userid);
		List<Orders> ordersList = new ArrayList<Orders>();
		List<Orders> tempList = this.ordersDAO.getOrdersByCond(orders);
		int pageNumber = tempList.size();
		int maxPage = pageNumber;
		if (maxPage % 10 == 0) {
			maxPage = maxPage / 10;
		} else {
			maxPage = maxPage / 10 + 1;
		}
		if (number == null) {
			number = "0";
		}
		int start = Integer.parseInt(number) * 10;
		int over = (Integer.parseInt(number) + 1) * 10;
		int count = pageNumber - over;
		if (count <= 0) {
			over = pageNumber;
		}
		for (int i = start; i < over; i++) {
			Orders o = tempList.get(i);
			ordersList.add(o);
		}
		String html = "";
		StringBuffer buffer = new StringBuffer();
		buffer.append("&nbsp;&nbsp;共为");
		buffer.append(maxPage);
		buffer.append("页&nbsp; 共有");
		buffer.append(pageNumber);
		buffer.append("条&nbsp; 当前为第");
		buffer.append((Integer.parseInt(number) + 1));
		buffer.append("页 &nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("首页");
		} else {
			buffer.append("<a href=\"index/showOrders.shtml?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"index/showOrders.shtml?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"index/showOrders.shtml?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"index/showOrders.shtml?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		map.put("html", html);
		map.put("ordersList", ordersList);
		return "users/orderlist";
	}

	// 查看订购
	@RequestMapping("mysell.shtml")
	public String mysell(String number, Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		Orders orders = new Orders();
		orders.setSellerid(userid);
		List<Orders> ordersList = new ArrayList<Orders>();
		List<Orders> tempList = this.ordersDAO.getOrdersByCond(orders);
		int pageNumber = tempList.size();
		int maxPage = pageNumber;
		if (maxPage % 10 == 0) {
			maxPage = maxPage / 10;
		} else {
			maxPage = maxPage / 10 + 1;
		}
		if (number == null) {
			number = "0";
		}
		int start = Integer.parseInt(number) * 10;
		int over = (Integer.parseInt(number) + 1) * 10;
		int count = pageNumber - over;
		if (count <= 0) {
			over = pageNumber;
		}
		for (int i = start; i < over; i++) {
			Orders o = tempList.get(i);
			ordersList.add(o);
		}
		String html = "";
		StringBuffer buffer = new StringBuffer();
		buffer.append("&nbsp;&nbsp;共为");
		buffer.append(maxPage);
		buffer.append("页&nbsp; 共有");
		buffer.append(pageNumber);
		buffer.append("条&nbsp; 当前为第");
		buffer.append((Integer.parseInt(number) + 1));
		buffer.append("页 &nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("首页");
		} else {
			buffer.append("<a href=\"index/showOrders.shtml?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"index/showOrders.shtml?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"index/showOrders.shtml?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"index/showOrders.shtml?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		map.put("html", html);
		map.put("ordersList", ordersList);
		return "users/mysell";
	}

	// 准备付款
	@RequestMapping("prePay.shtml")
	public String prePay(Map<String, Object> map, String id) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		map.put("id", id);
		return "users/pay";
	}

	// 付款
	@RequestMapping("pay.shtml")
	public String pay(Map<String, Object> map, String id) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		Orders orders = this.ordersDAO.getOrdersById(getRequest().getParameter("id"));
		orders.setStatus("已付款");
		this.ordersDAO.updateOrders(orders);
		return "redirect:/index/showOrders.shtml";
	}

	// 确认收货
	@RequestMapping("over.shtml")
	public String over(Map<String, Object> map, String id) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		Orders orders = this.ordersDAO.getOrdersById(getRequest().getParameter("id"));
		orders.setStatus("已收货");
		this.ordersDAO.updateOrders(orders);
		return "redirect:/index/showOrders.shtml";
	}

	// 确认收货
	@RequestMapping("send.shtml")
	public String send(Map<String, Object> map, String id) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		Orders orders = this.ordersDAO.getOrdersById(getRequest().getParameter("id"));
		orders.setStatus("已发货");
		this.ordersDAO.updateOrders(orders);
		Goods goods = this.goodsDAO.getGoodsById(orders.getGoodsid());
		goods.setStorage("" + (Integer.parseInt(goods.getStorage()) - Integer.parseInt(orders.getNum())));
		this.goodsDAO.updateGoods(goods);
		return "redirect:/index/mysell.shtml";
	}

	// 准备登录
	@RequestMapping("preLogin.shtml")
	public String preLogin(Map<String, Object> map) {
		this.front(map);
		return "users/login";
	}

	// 用户登录
	@RequestMapping("login.shtml")
	public String login(Map<String, Object> map) {
		this.front(map);
		String username = getRequest().getParameter("username");
		String password = getRequest().getParameter("password");
		Users u = new Users();
		u.setUsername(username);
		List<Users> usersList = this.usersDAO.getUsersByCond(u);
		if (usersList.size() == 0) {
			getRequest().setAttribute("message", "用户名不存在");
			return "redirect:/index/preLogin.shtml";
		} else {
			Users users = usersList.get(0);
			if (users.getStatus().equals("锁定")) {
				getRequest().getSession().setAttribute("message", "账户被锁定");
				return "redirect:/index/preLogin.shtml";
			}
			if (password.equals(users.getPassword())) {
				getRequest().getSession().setAttribute("userid", users.getUsersid());
				getRequest().getSession().setAttribute("username", users.getUsername());
				getRequest().getSession().setAttribute("users", users);
			} else {
				getRequest().setAttribute("message", "密码错误");
				return "redirect:/index/preLogin.shtml";
			}
		}
		return "redirect:/index/usercenter.shtml";
	}

	// 准备注册
	@RequestMapping("preReg.shtml")
	public String preReg(Map<String, Object> map) {
		this.front(map);
		return "users/register";
	}

	// 用户注册
	@RequestMapping("register.shtml")
	public String register(Users users, Map<String, Object> map) {
		this.front(map);
		Users u = new Users();
		u.setUsername(users.getUsername());
		List<Users> usersList = this.usersDAO.getUsersByCond(users);
		if (usersList.size() == 0) {
			users.setUsersid(VeDate.getStringDatex());
			users.setRegdate(VeDate.getStringDateShort());
			users.setStatus("锁定");
			this.usersDAO.insertUsers(users);
		} else {
			getRequest().setAttribute("message", "用户名已存在");
			return "redirect:/index/preReg.shtml";
		}
		return "redirect:/index/preLogin.shtml";
	}

	// 退出登录
	@RequestMapping("exit.shtml")
	public String exit(Map<String, Object> map) {
		this.front(map);
		getRequest().getSession().removeAttribute("userid");
		getRequest().getSession().removeAttribute("username");
		getRequest().getSession().removeAttribute("users");
		return "index";
	}

	// 用户中心
	@RequestMapping("usercenter.shtml")
	public String usercenter(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		return "users/usercenter";
	}

	// 用户中心
	@RequestMapping("userinfo.shtml")
	public String userinfo(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		return "users/userinfo";
	}

	// 修改个人信息
	@RequestMapping("personal.shtml")
	public String personal(Map<String, Object> map, Users users) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		this.usersDAO.updateUsers(users);
		getRequest().getSession().setAttribute("users", users);
		return "redirect:/index/userinfo.shtml";
	}

	// 准备修改密码
	@RequestMapping("prePwd.shtml")
	public String prePwd(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		return "users/editpwd";
	}

	// 修改密码
	@RequestMapping("editpwd.shtml")
	public String editpwd(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		String password = getRequest().getParameter("password");
		String repassword = getRequest().getParameter("repassword");
		Users users = this.usersDAO.getUsersById(userid);
		if (password.equals(users.getPassword())) {
			users.setPassword(repassword);
			this.usersDAO.updateUsers(users);
		} else {
			getRequest().setAttribute("message", "旧密码错误");
			return "redirect:/index/prePwd.shtml";
		}
		return "redirect:/index/prePwd.shtml";
	}

	// 留言板
	@RequestMapping("bbs.shtml")
	public String bbs(Map<String, Object> map) {
		this.front(map);
		List<Bbs> bbsList = this.bbsDAO.getAllBbs();
		map.put("bbsList", bbsList);
		return "users/bbs";
	}

	// 发布留言
	@RequestMapping("addbbs.shtml")
	public String addbbs(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		Bbs bbs = new Bbs();
		bbs.setBbsid(VeDate.getStringDatex());
		bbs.setAddtime(VeDate.getStringDate());
		bbs.setContents(getRequest().getParameter("contents"));
		bbs.setHits("0");
		bbs.setRepnum("0");
		bbs.setTitle(getRequest().getParameter("title"));
		bbs.setUsersid(userid);
		this.bbsDAO.insertBbs(bbs);
		return "redirect:/index/bbs.shtml";
	}

	// 查看留言
	@RequestMapping("readbbs.shtml")
	public String readbbs(Map<String, Object> map) {
		this.front(map);
		Bbs bbs = this.bbsDAO.getBbsById(getRequest().getParameter("id"));
		bbs.setHits("" + (Integer.parseInt(bbs.getHits()) + 1));
		this.bbsDAO.updateBbs(bbs);
		map.put("bbs", bbs);
		Rebbs rebbs = new Rebbs();
		rebbs.setBbsid(bbs.getBbsid());
		List<Rebbs> rebbsList = this.rebbsDAO.getRebbsByCond(rebbs);
		map.put("rebbsList", rebbsList);
		return "users/readbbs";
	}

	// 回复留言
	@RequestMapping("rebbs.shtml")
	public String rebbs(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.shtml";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		Rebbs rebbs = new Rebbs();
		rebbs.setRebbsid(VeDate.getStringDatex());
		rebbs.setAddtime(VeDate.getStringDate());
		rebbs.setContents(getRequest().getParameter("contents"));
		rebbs.setBbsid(getRequest().getParameter("bbsid"));
		rebbs.setUsersid(userid);
		this.rebbsDAO.insertRebbs(rebbs);
		Bbs bbs = this.bbsDAO.getBbsById(rebbs.getBbsid());
		bbs.setRepnum("" + (Integer.parseInt(bbs.getRepnum()) + 1));
		this.bbsDAO.updateBbs(bbs);
		String path = "redirect:/index/readbbs.shtml?id=" + bbs.getBbsid();
		return path;
	}

	public BbsDAO getBbsDAO() {
		return bbsDAO;
	}

	public void setBbsDAO(BbsDAO bbsDAO) {
		this.bbsDAO = bbsDAO;
	}

	public UsersDAO getUsersDAO() {
		return usersDAO;
	}

	public void setUsersDAO(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}

	public CateDAO getCateDAO() {
		return cateDAO;
	}

	public void setCateDAO(CateDAO cateDAO) {
		this.cateDAO = cateDAO;
	}

	public CartDAO getCartDAO() {
		return cartDAO;
	}

	public void setCartDAO(CartDAO cartDAO) {
		this.cartDAO = cartDAO;
	}

	public FavDAO getFavDAO() {
		return favDAO;
	}

	public void setFavDAO(FavDAO favDAO) {
		this.favDAO = favDAO;
	}

	public OrdersDAO getOrdersDAO() {
		return ordersDAO;
	}

	public void setOrdersDAO(OrdersDAO ordersDAO) {
		this.ordersDAO = ordersDAO;
	}

	public RebbsDAO getRebbsDAO() {
		return rebbsDAO;
	}

	public void setRebbsDAO(RebbsDAO rebbsDAO) {
		this.rebbsDAO = rebbsDAO;
	}

	public ArticleDAO getArticleDAO() {
		return articleDAO;
	}

	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	public GoodsDAO getGoodsDAO() {
		return goodsDAO;
	}

	public void setGoodsDAO(GoodsDAO goodsDAO) {
		this.goodsDAO = goodsDAO;
	}

	public TopicDAO getTopicDAO() {
		return topicDAO;
	}

	public void setTopicDAO(TopicDAO topicDAO) {
		this.topicDAO = topicDAO;
	}

}
