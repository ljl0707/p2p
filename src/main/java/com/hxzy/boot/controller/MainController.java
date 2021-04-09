package com.hxzy.boot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxzy.boot.bean.User;
import com.hxzy.boot.service.IMainService;

@Controller
@RequestMapping("/main")
public class MainController {
	
	@Autowired
	private IMainService mainService;

	@RequestMapping("/toMain")
	public String toMain(){
		return "main";
	}
	
	@RequestMapping("/toSolr")
	public String toSolr(HttpSession session,HttpServletRequest request){
		String value=request.getParameter("value");		
		session.setAttribute("value", value);
		return "solr";
	}
	
	@RequestMapping("/getMenu")
	@ResponseBody
	public String getMenu(HttpSession session){
		Object obj=session.getAttribute("userinfo");
		User user=null;
		if(obj!=null){
			user=(User)obj;
		}
		String json=null;
		try {
			json = mainService.getMenu(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	@RequestMapping("/getProduct")
	@ResponseBody
	public String getProduct() throws Exception{	
		String json=mainService.getProduct();
		return json;
	}
	@RequestMapping("/getPower")
	@ResponseBody
	public String getPower() throws Exception{	
		String json=mainService.getPower();
		return json;
	}
	@RequestMapping("/getMoney")
	@ResponseBody
	public String getMoney() throws Exception{	
		String json=mainService.getMoney();
		return json;
	}
	@RequestMapping("/getMoneys")
	@ResponseBody
	public String getMoneys() throws Exception{	
		String json=mainService.getMoneys();
		return json;
	}
	@RequestMapping("/getImages")
	@ResponseBody
	public String getImages() throws Exception{	
		String json=mainService.getImages();
		return json;
	}
}
