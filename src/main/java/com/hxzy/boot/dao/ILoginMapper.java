package com.hxzy.boot.dao;

import com.hxzy.boot.bean.User;


public interface ILoginMapper {
	//登录
	public User login(User user) throws Exception;
	
	//重置密码
	public int reUpPwd(User user) throws Exception;
    

}
