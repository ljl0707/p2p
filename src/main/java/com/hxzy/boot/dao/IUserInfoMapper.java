package com.hxzy.boot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;

import com.hxzy.boot.bean.UserInfo;

public interface IUserInfoMapper {
	
    //增加
	public int add(UserInfo userinfo);
	
	//修改
    public int update(UserInfo userinfo);
	
	//删除 
    @Delete(value="delete from t_u  where id=#{id}")
	public int del(String id);
	
	//通过ID查询对象
	public UserInfo getObjById(String id);
	
	//查询
	public List<UserInfo> query(Map<String,Object> map);

}
