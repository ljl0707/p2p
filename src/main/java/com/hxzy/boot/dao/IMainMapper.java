package com.hxzy.boot.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hxzy.boot.bean.Product;
import com.hxzy.boot.bean.Tree;
import com.hxzy.boot.bean.User;

public interface IMainMapper {
	
    //通过父节点查询子节点
	public List<Tree> getMenuByPid(String pid)throws Exception;
	
	//通过权限查询菜单
	public List<Tree> getAuthParentMenu(String userid)throws Exception;
	
	//查询授权后的子菜单
	public List<Tree> getAuthMenuByPid(String userid,String pid)throws Exception;
	
	//查询所有的产品信息
	public List<Product> getProduct()throws Exception;
	
	//查询所有的产品信息
	public List<Map> getPower()throws Exception;
	
	//查询所有的产品信息
	public List<Map> getMoney()throws Exception;
	
	//查询所有的产品信息
	public List<Map> getMoneys()throws Exception;
	
	//查询轮播图
	public List<Map> getImages()throws Exception;
}
