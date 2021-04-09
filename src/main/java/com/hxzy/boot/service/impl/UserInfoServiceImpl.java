package com.hxzy.boot.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hxzy.boot.bean.UserInfo;
import com.hxzy.boot.dao.IUserInfoMapper;
import com.hxzy.boot.service.IUserInfoService;

@Service
public class UserInfoServiceImpl implements IUserInfoService{
	
    @Autowired
	private IUserInfoMapper userInfoMapper;
	
	@Override
	public List<UserInfo> query(Map<String, Object> map) {
		return userInfoMapper.query(map);
	}

	@Override
	public int save(UserInfo user) {
		int k=0;
		if(user.getId()!=null && !"".equals(user.getId())){
			//修改
			k=userInfoMapper.update(user);
		}else{
			//新增
			k=userInfoMapper.add(user);
		}
		return k;
	}

	@Override
	public int del(String id) {
		return userInfoMapper.del(id);
	}

	@Override
	public UserInfo getObjByid(String id) {
		return userInfoMapper.getObjById(id);
	}

}
