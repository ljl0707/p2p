package com.hxzy.boot.service;

import java.util.List;
import java.util.Map;

import com.hxzy.boot.bean.Product;
import com.hxzy.boot.bean.Tree;
import com.hxzy.boot.bean.User;
import com.hxzy.boot.bean.UserInfo;

public interface IMainService {
	
	//通过父节点查询子节点
	public String getMenu(User user)throws Exception;
	//查询产品信息
	public String getProduct() throws Exception;
	//查询每个角色对应的菜单数量
	public String getPower() throws Exception;
	//查询每个人的投资
	public String getMoney() throws Exception;
	//查询每个人的投资
	public String getMoneys() throws Exception;
	//查询轮播图图片
	public String getImages() throws Exception;
}
