

package com.controller;

import com.dao.CateDAO;
import com.entity.Cate;
import com.util.VeDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/cate")
public class CateController extends BaseController {
	// 注入AdminDAO 并getter setter
	@Autowired
	private CateDAO cateDAO;

	// 准备添加数据
	@RequestMapping("createCate.shtml")
	public String createCate(Map<String, Object> map) {
		return "admin/addcate";
	}

	// 添加数据
	@RequestMapping("addCate.shtml")
	public String addCate(Cate cate) {
		cate.setCateid(VeDate.getStringDatex());
		this.cateDAO.insertCate(cate);
		return "redirect:/cate/createCate.shtml";
	}

	// 通过主键删除数据
	@RequestMapping("deleteCate.shtml")
	public String deleteCate(String id) {
		this.cateDAO.deleteCate(id);
		return "redirect:/cate/getAllCate.shtml";
	}

	// 更新数据
	@RequestMapping("updateCate.shtml")
	public String updateCate(Cate cate) {
		this.cateDAO.updateCate(cate);
		return "redirect:/cate/getAllCate.shtml";
	}

	// 显示全部数据
	@RequestMapping("getAllCate.shtml")
	public String getAllCate(String number, Map<String, Object> map) {
		List<Cate> cateList = new ArrayList<Cate>();
		List<Cate> tempList = new ArrayList<Cate>();
		tempList = this.cateDAO.getAllCate();
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
			Cate cate = tempList.get(i);
			cateList.add(cate);
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
			buffer.append("<a href=\"cate/getAllCate.shtml?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"cate/getAllCate.shtml?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"cate/getAllCate.shtml?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"cate/getAllCate.shtml?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		map.put("html", html);
		map.put("cateList", cateList);
		return "admin/listcate";
	}

	// 按条件查询数据 (模糊查询)
	@RequestMapping("queryCateByCond.shtml")
	public String queryCateByCond(String cond, String name, Map<String, Object> map) {
		List<Cate> cateList = new ArrayList<Cate>();
		Cate cate = new Cate();
		if (cond != null) {
			if ("catename".equals(cond)) {
				cate.setCatename(name);
				cateList = this.cateDAO.getCateByLike(cate);
			}
		}
		map.put("cateList", cateList);
		return "admin/querycate";
	}

	// 按主键查询数据
	@RequestMapping("getCateById.shtml")
	public String getCateById(String id, Map<String, Object> map) {
		Cate cate = this.cateDAO.getCateById(id);
		map.put("cate", cate);
		return "admin/editcate";
	}

	public CateDAO getCateDAO() {
		return cateDAO;
	}

	public void setCateDAO(CateDAO cateDAO) {
		this.cateDAO = cateDAO;
	}

}
