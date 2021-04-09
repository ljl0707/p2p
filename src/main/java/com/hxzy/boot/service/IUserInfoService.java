package com.hxzy.boot.service;

import java.util.List;
import java.util.Map;

import com.hxzy.boot.bean.UserInfo;

public interface IUserInfoService {
	
	public List<UserInfo> query(Map<String,Object> map);
	
	public int save(UserInfo user);
	
	public int del(String id);
	
	public UserInfo getObjByid(String id);

}
