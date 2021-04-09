package com.hxzy.boot.service;

import java.util.Map;



public interface IAuditServcie {
	
	//通过ID查询对象
	public String getObjById(String id)throws Exception;
	
	//查询
	public String query(Map<String,Object> map)throws Exception;
	
	//审核
	public int toAudit(Map<String,Object> map)throws Exception;

}
