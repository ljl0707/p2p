package com.hxzy.boot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.hxzy.boot.bean.Product;
import com.hxzy.boot.bean.Tree;
import com.hxzy.boot.bean.User;
import com.hxzy.boot.dao.IMainMapper;
import com.hxzy.boot.service.IMainService;
import com.hxzy.boot.util.RedisUtil;
@Service
public class MainServcieImpl implements IMainService{

	@Autowired
	private IMainMapper mainMapper;
	
	@Autowired
	private RedisUtil redisUtil;
	
	private Jedis jedis=null;
	
	@Override
	public String getMenu(User user) throws Exception {
		String json="";
		if(user ==null || "admin".equals(user.getName())){
			try {
				//首先从缓存中取当前用户的菜单信息
				json=(String)redisUtil.get("admin");
				if(StringUtils.isEmpty(json)){
					//从mysql中取数据
					json=getAllMenu();
					//放到redis中
					redisUtil.set("admin", json, 7*24*60*60);
				}
			} catch (Exception e) {
				e.printStackTrace();
				json=getAllMenu();
			}
			
		}else{
			try {
				json=(String)redisUtil.get(user.getId());
				if(StringUtils.isEmpty(json)){
					//从mysql中取数据
					json=getAuthAllMenu(user);
					//放到redis中
					redisUtil.set(user.getId(), json, 7*24*60*60);
				}
			} catch (Exception e) {
				e.printStackTrace();
				json=getAuthAllMenu(user);
			}
		}
		return json;
	}
    
	//查询全部菜单
	public String getAllMenu()throws Exception{
		List<Tree> list=mainMapper.getMenuByPid("0");
		for(Tree tree:list){
			//通过一级菜单的ID，查询二级菜单
			List<Tree> children =mainMapper.getMenuByPid(tree.getId());
			tree.setChildren(children);
		}
		String json=JSON.toJSONString(list);
		return json;
	}
	
	//通过权限查询菜单
	public String getAuthAllMenu(User user)throws Exception{
		//通过用户->角色->菜单(先查询一级菜单)
		List<Tree> list=mainMapper.getAuthParentMenu(user.getId());
		for(Tree tree:list){
			//通过一节菜单查询二级菜单
			List<Tree> children=mainMapper.getAuthMenuByPid(user.getId(), tree.getId());
			tree.setChildren(children);
		}
		String json=JSON.toJSONString(list);
		return json;
	}
	//查询所有的商品信息
	@Override
	public String getProduct() throws Exception {		
		List<Product> list=mainMapper.getProduct();
		String json=JSON.toJSONString(list);
		return json;
	}

	@Override
	public String getPower() throws Exception {
		List list=mainMapper.getPower();
		String json=JSON.toJSONString(list);
		return json;
	}

	@Override
	public String getMoney() throws Exception {
		List list=mainMapper.getMoney();
		String json=JSON.toJSONString(list);
		return json;
	}
	@Override
	public String getMoneys() throws Exception {
		List<Map> list=mainMapper.getMoneys();
		Map<String,Object> map=list.get(0);
		map.put("sliced", true);
		map.put("selected", true);
		String json=JSON.toJSONString(list);
		return json;
	}
	@Override
	public String getImages() throws Exception {		
		String json="";
		List<Map> list=null;
		try {
			list=mainMapper.getImages();
			json=(String)redisUtil.get("image");
			if(StringUtils.isNotEmpty(json)){
				return json;
			}else{
				json=JSON.toJSONString(list);
				redisUtil.set("image",json,7*24*60*60);				
			}
		}  catch (Exception e) {
			e.printStackTrace();
			json=JSON.toJSONString(list);
		}
		
		return json;
	}
}
