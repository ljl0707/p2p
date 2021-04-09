package com.hxzy.boot.dao;

import java.util.List;
import java.util.Map;

import com.hxzy.boot.bean.Message;


public interface IMessMapper {

	public List<Message> query(Map<String, Object> map) throws Exception;
}
