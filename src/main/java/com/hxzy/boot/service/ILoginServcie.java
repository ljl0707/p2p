package com.hxzy.boot.service;

import com.hxzy.boot.bean.User;


public interface ILoginServcie {
	
	public User login(User user) throws Exception;
	
	//重置密码
	public int reUpPwd(User user)throws Exception;

}
