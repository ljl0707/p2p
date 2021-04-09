package com.hxzy.boot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hxzy.boot.bean.UserInfo;
import com.hxzy.boot.service.IUserInfoService;

@Controller
public class UserInfoController {
	
    @Autowired
	private IUserInfoService userInfoService;
	
	public UserInfoController(){
		System.err.println("UserController 构造方法...");
	}
	
	@RequestMapping("/query")
	public String userlist(String qname,ModelMap modelMap){
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("name", qname);
		List<UserInfo> list=userInfoService.query(map);
		//将数据放到模型中
		modelMap.put("ulist", list);
		modelMap.put("map", map);
		return "userinfolist";
	}
	
	@RequestMapping("/toAdd")
	public String toAdd(){
		return "userAdd";
	}
	
	@RequestMapping("/save")
	public String save(UserInfo user){
		int k=userInfoService.save(user);
		return "redirect:/query";
	}
	
	@RequestMapping("/modelsave")
	public String modelsave(UserInfo user){
		int k=userInfoService.save(user);
		return "redirect:/query";
	}
	
	@RequestMapping("/del")
	public String del(String id){
		userInfoService.del(id);
		return "redirect:/query";
	}
	@RequestMapping("/getObjByid")
	public String getObjByid(String id,Model model){
		UserInfo user=userInfoService.getObjByid(id);
		model.addAttribute("user", user);
		return "userEdit";
	}
	
	@RequestMapping("/ajaxGetObjByid")
	@ResponseBody
	public String ajaxGetObjByid(String id){
		UserInfo user=userInfoService.getObjByid(id);
        String json=JSON.toJSONString(user);
		return json;
	}
}
