package com.hxzy.boot.service;

import java.util.Map;

import com.hxzy.boot.bean.Product;



public interface IProductServcie {
	
	//保存
	public int save(Product product) throws Exception;
	
	//删除 
	public int del(String id)throws Exception;
	
	//查询
	public String query(Map<String,Object> map)throws Exception;
	
	//查询全部产品
	public String getProducts()throws Exception; 

}
