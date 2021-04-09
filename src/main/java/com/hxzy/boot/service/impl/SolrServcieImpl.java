package com.hxzy.boot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.hxzy.boot.bean.SolrBean;
import com.hxzy.boot.service.ISolrServcie;

@Service
public class SolrServcieImpl implements ISolrServcie{
	@Autowired
	private SolrClient solrClient;
	
	@Override
	public String query(Map<String, Object> map) throws Exception {
		//当前第几页
		int pageNum=Integer.parseInt((String)map.get("pageNum"));
		//每页记录数
		int pageSize=Integer.parseInt((String)map.get("pageSize"));
		//是否高亮显示 1 是 0 否
		String hi=map.get("hi")==null?"0":(String)map.get("hi");
		
		String json="";
        SolrQuery solrQuery = new SolrQuery();
        String qprame="*:*";
        if(map.get("text")!=null && !"".equals((String)map.get("text"))){
        	qprame="text:"+(String)map.get("text");
        }
        solrQuery.setQuery(qprame);
        QueryResponse countResponse = solrClient.query(solrQuery);
        //总记录数
    	long count = countResponse.getResults().getNumFound();
        //设置分页信息
        solrQuery.setStart((pageNum-1)*pageSize);
        solrQuery.setRows(pageSize);
        //solrQuery.setQuery("*:*");
        //设置高亮显示
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("text");
        solrQuery.setHighlightSimplePre("<font color='red'>");
        solrQuery.setHighlightSimplePost("</font>");
        try {
        	
            QueryResponse queryResponse = solrClient.query(solrQuery);
            if (queryResponse != null){
            	SolrDocumentList list = queryResponse.getResults();
            	//判断是否高亮显示
            	if("1".equals(hi) && !"*:*".equals(qprame)){
            		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
            		for (SolrDocument document : list){
            			document.replace("text", highlighting.get(document.get("id")).get("text"));
            		}
            	}
            	Map<String,Object> rmap=new HashMap<String,Object>();
        		rmap.put("total", count);
        		rmap.put("rows", list);
        		json=JSONObject.toJSONString(rmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 

		return json;
	}
	
	@Override
	public int save(SolrBean solrbean) throws Exception {
		return 0;
	}

}
