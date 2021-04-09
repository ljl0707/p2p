package com.hxzy.boot.controller;


import java.util.HashMap;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;


@Controller
@RequestMapping("/wx")
public class WxController {

	@RequestMapping("/getImages")
	@ResponseBody
	public String getImages(){
		String[][] gfimages = {
				{
						"https://qpic.y.qq.com/music_cover/xiabfMZAmQ0PYUzgCvOicArIoGLzqL3n6q3fDiawWkhTTVWgGNM52HBNA/300?n=1",
						"https://qpic.y.qq.com/music_cover/xiabfMZAmQ0PYUzgCvOicArIoGLzqL3n6q4X4NiaWS01Fvtn063nqHY2Q/300?n=1",
						"https://qpic.y.qq.com/music_cover/MKjEtF7diatibd6B0iaeF5KguYHTWhScOADLtR9xjUjEEz5uYMCCKOA9w/300?n=1" 
				},
				{
						"https://qpic.y.qq.com/music_cover/4pmnRu5sL5QbtO8OS8NKJfZrk8BAWrrpjoXd2UIvyBMlQpiaQpf8MaQ/300?n=1",
						"https://qpic.y.qq.com/music_cover/7vlTTvwBiaibKJpyXffTHicMpDXK5xPJiaPoPSy3PBexF9zRK9veKIXuxw/300?n=1",
						"https://qpic.y.qq.com/music_cover/7vlTTvwBiaibKJpyXffTHicMpDXK5xPJiaPobp8KZDDeCVsjiaePJqnMzXA/300?n=1" 
				},
				{
						"http://qpic.y.qq.com/music_cover/JBDCVgqXWXaYUvcsElqcicRfJWrIbyCnYVNMcQRZs6rwdJDlNoCINzA/300?n=1",
						"http://qpic.y.qq.com/music_cover/CJ35Z2cnZA0kWwtIMrxsbgkibz8iamGyO7ogibBibHyA7qCqa4cGOHiccOw/300?n=1",
						"http://qpic.y.qq.com/music_cover/xiabfMZAmQ0PYUzgCvOicArBOcyt7ez6RXedb0y3USAlgraIsCQyuhkg/300?n=1" 
				} 
		};
		String[][] drimages = {
				{
						"https://qpic.y.qq.com/music_cover/Z89aLA93LOSOicz0QOnMboqgLaiaFohjweglHh6JSoL8hrjOfFOC6DXw/300?n=1",
						"https://qpic.y.qq.com/music_cover/INS3Wn20gAtrNdUzZ5YA2vtsvnN2GASfjc530B4ffGa2ZUJgOrd5uw/300?n=1",
						"https://qpic.y.qq.com/music_cover/MhQ4bJBPt3Yt5icXyBGNhyA75KH7nTYziamtcq57209ickxHveTia5c4Yg/300?n=1" 
				},
				{
						"https://qpic.y.qq.com/music_cover/ZfWLEKXs0aUDM9Rl8E4k7CzIGeF28O602XtuKmZtcD5S2NzffacicOA/300?n=1",
						"https://qpic.y.qq.com/music_cover/fzXhTq8RRx3VXX4AqUuhg7rnRY3Qia7WeicqOsVtgACEl8ckumsaNIYA/300?n=1",
						"https://qpic.y.qq.com/music_cover/7hNs7Pn2A4gvlSzdOUicPZ3Vg2ToTggCEhKD7voW0EjE8PicRPzdoN7g/300?n=1" 
				} 
		};
		String[] zqimages = {
				"https://y.gtimg.cn/music/common/upload/category_area/2262937.jpg?max_age=2592000",
				"https://y.gtimg.cn/music/common/upload/category_area/2240182.jpg?max_age=2592000",
				"https://y.gtimg.cn/music/common/upload/category_area/2173224.png?max_age=2592000",
				"https://y.gtimg.cn/music/common/upload/t_area/2132578.jpg",
				"https://y.gtimg.cn/music/common/upload/category_area/2234971.jpg",
				"https://y.gtimg.cn/music/common/upload/category_area/2205139.jpg" 
		};
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("gfimages", gfimages);
		map.put("drimages", drimages);
		map.put("zqimages", zqimages);
		String json=JSONObject.toJSONString(map);
	    return json;
	}
}
