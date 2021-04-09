package com.hxzy.boot.service;

import java.util.Map;

import com.hxzy.boot.bean.SolrBean;



public interface ISolrServcie {
	
	//查询
	public String query(Map<String,Object> map)throws Exception;
	
	//保存
	public int save(SolrBean solrbean)throws Exception;
	
}
