package com.hxzy.boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hxzy.boot.bean.User;
import com.hxzy.boot.dao.ILoginMapper;
import com.hxzy.boot.service.ILoginServcie;
import com.hxzy.boot.util.MD5;

@Service
public class LoginServiceImpl implements ILoginServcie {

	@Autowired
	private ILoginMapper loginMapper;
	
	@Override
	public User login(User user) throws Exception {
		return loginMapper.login(user);
	}

	@Override
	public int reUpPwd(User user) throws Exception {
		//对密码加密
		String md5pwd=MD5.encryptPassword(user.getName(), user.getPassword(), null);
		user.setPassword(md5pwd);
		return loginMapper.reUpPwd(user);
	}

}
