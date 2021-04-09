package com.hxzy.boot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxzy.boot.bean.SolrBean;
import com.hxzy.boot.service.ISolrServcie;


@Controller
@RequestMapping("/solr")
public class SolrController {
    
	@Autowired
	private ISolrServcie solrServcie;
	
	@RequestMapping("/solrManage")
	public String messManage(){
		return "solrlist";
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(HttpServletRequest request) {
		// 查询条件
		/*String loancode = request.getParameter("loancode");*/
		String text = request.getParameter("text");
		String hi = request.getParameter("hi");
		// 当前第几页
		String page = request.getParameter("page");
		// 每页记录数
		String rows = request.getParameter("rows");
		
		// 查询条件 与 分页参数同一放到map中
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("text", text);
		map.put("hi", hi);
		map.put("pageNum", page);
		map.put("pageSize", rows);
		String json = "";
		try {
			json = solrServcie.query(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	@RequestMapping("/save")
	@ResponseBody
	public String save(SolrBean solrbean){
		int k=0;
		try {
			k=solrServcie.save(solrbean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return k+"";
	}
	
	
}
