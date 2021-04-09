package com.hxzy.boot.controller;

import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.hxzy.boot.bean.User;
import com.hxzy.boot.service.IRoleServcie;
import com.hxzy.boot.service.IUserServcie;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserServcie userService;
	
	@Autowired
	private IRoleServcie roleServcie;
	
	@RequestMapping("/userManage")
    public String toUserList(){
    	return "userlist";
    }
	@RequestMapping("/query")
	@ResponseBody
	public String query(HttpServletRequest req){
		String name=req.getParameter("name");
		//分页参数
		String pageNum=req.getParameter("page");//当前第几页
		String pageSize=req.getParameter("rows");//每页记录数
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("name",name);
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		String json="";
		try {
			json=userService.query(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(User user,HttpServletRequest req){
		MultipartRequest mreq=(MultipartRequest)req;
		MultipartFile file = mreq.getFile("userfile");
		int k=0;
		try {
			if(file!=null){
				String filename=file.getOriginalFilename();
				String filepath="E:\\upload";
				//上传
				if(!new File(filepath).exists()){
					new File(filepath).mkdir();
				}
				File f=new File(filepath,filename);
				file.transferTo(f);
				//将表单基本信息保存到数据库
				user.setFilepath(filepath+"\\"+filename);
			}
			k=userService.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(k);
	}
	
	//下载附件
	@RequestMapping("/downfile")
	public void downfile(String id,HttpServletResponse response){
		try {
			User user=userService.getObjById(id);
			String filepatname=user.getFilepath();
			
			String filename=filepatname.substring(filepatname.lastIndexOf("\\")+1,filepatname.length());
			File file=new File(filepatname);
			if(file==null || !file.exists()){
				return;
			}
			filename = new String(filename.getBytes(),"ISO8859-1");
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ filename);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            OutputStream out = response.getOutputStream();
            out.write(FileUtils.readFileToByteArray(file));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public String del(String ids){
		int k=0;
		try {
			k = userService.del(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return k+"";
	}
	
	//导出excel
	@RequestMapping("/exportExcel")
	@ResponseBody
	public void exportExcel(HttpServletRequest req,HttpServletResponse response){
		//查询条件
		String qname=req.getParameter("name");
		//logger.info("查询条件qname=="+qname);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("name", qname);
		HSSFWorkbook wb=userService.exportExcel(map);
		
		String fileName = "用户信息表"+System.currentTimeMillis()+".xls";
        try {
			fileName = new String(fileName.getBytes(),"ISO8859-1");
			response.setContentType("application/octet-stream;charset=ISO8859-1");
	        response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
	        response.addHeader("Pargam", "no-cache");
	        response.addHeader("Cache-Control", "no-cache");
	        OutputStream os = response.getOutputStream();
	        wb.write(os);
	        os.flush();
	        os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//分配角色
	@RequestMapping("/getRoles")
	@ResponseBody
	public String getRoles(String userid){
		String json="";
		try {
			json=roleServcie.getRoles(userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	//保存用户 与 角色 到用户角色表
	@RequestMapping("/saveUserRole")
	@ResponseBody
	public String saveUserRole(String userid,String roleid){
		int k=0;
		try {
			k=userService.saveUserRole(userid,roleid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(k);
	}
}
