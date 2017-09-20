package com.yiyou.repast.merchant.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.yiyou.repast.merchant.base.Constants;
import com.yiyou.repast.merchant.base.RspResult;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * 图片上传读取公用接口
 */
@Controller
public class ImageController{
	
	/**
	 * 上传图片
	 * */
	@ResponseBody
    @RequestMapping(value={"/common/img/upload.do"},method=RequestMethod.POST)
    public RspResult uploadImg(HttpServletRequest request)throws Exception{
		RspResult data=new RspResult();
		boolean isMultipart=ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			Map<String,String> object=new HashMap<>();
			MultipartHttpServletRequest req = (MultipartHttpServletRequest) request; 
			Map<String, MultipartFile> fileMap  = req.getFileMap();
			if(!fileMap.isEmpty()) {
				Map.Entry<String, MultipartFile> e = fileMap.entrySet().iterator().next(); 
				MultipartFile file = e.getValue();
				String dir = "";
				switch (e.getKey()) {
				case "goods":
					dir = Constants.GOODS_IMG_PATH;
					break;
				default:
					break;
				}
				String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				String fileName=UUID.randomUUID().toString()+suffix;
				FileOutputStream os=new FileOutputStream(new File(dir + fileName));
				FileCopyUtils.copy(file.getInputStream(), os);
				object.put("fileKey", e.getKey());
				object.put("fileName", fileName);
				data.setData(object);
			}
		}else {
			data.setStatus(0);
			data.setMessage("不是一个真实的文件请求");
		}
        return data;
    }
    
	/**
	 * 图片访问
	 */
	@RequestMapping(value = "/common/img/{filename}/{type}/")
	public void headImg(@PathVariable String filename,@PathVariable Integer type,
			HttpServletRequest request, HttpServletResponse response) {
		if (StringUtils.isEmpty(filename)||type==null)
			return;
		String pathString=typeTpPath(type);
		File srcFile = new File(pathString + filename);
		if (srcFile == null || !srcFile.exists())
			return;
		OutputStream out;
		try {
			out = response.getOutputStream();
			byte[] b = getBytes(srcFile);
			out.write(b);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    
	
	/**
	 * 图片类型转换对应目录
	 * */
	private String typeTpPath(Integer type){
		String pathString = "";
		switch (type) {
		case 0:
			pathString = Constants.GOODS_IMG_PATH;
			break;
		default:
			break;
		}
		return pathString;
	}
	
	/** 
     * 获得指定文件的byte数组 
     */  
    private byte[] getBytes(File srcFile){  
        byte[] buffer = null;  
        try {  
            FileInputStream fis = new FileInputStream(srcFile);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer;  
    }
    
}

