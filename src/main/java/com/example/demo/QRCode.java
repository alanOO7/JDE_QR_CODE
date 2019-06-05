package com.example.demo;

import java.io.ByteArrayOutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alan.util.QRCodeUtil;

@RestController
public class QRCode {
	
	@GetMapping(value="/getQRCode")
    public void show(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取前台传入的信息，此处可操作性很大，大家可以通过自己查询数据库，或者通过判断填入其他信息
	//    request.setCharacterEncoding("UTF-8");
	    System.out.println("222");

	 // String content = URLDecoder.decode(request.getParameter("content"), "utf-8");
	    String content =request.getParameter("content");
	 //   String content=new String(request.getParameter("content").getBytes("ISO-8859-1"), "UTF-8"); 
	    System.out.println("1111"+content);
	    //二维码图片中间logo
	    String imgPath = "";
	    Boolean needCompress = true; 
	    //通过调用我们的写的工具类，拿到图片流
	    ByteArrayOutputStream out = QRCodeUtil.encodeIO(content, imgPath, needCompress);
	    //定义返回参数
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("image/jpeg;charset=UTF-8");
	    response.setContentLength(out.size());
	    ServletOutputStream outputStream = response.getOutputStream();
	    outputStream.write(out.toByteArray());
	    outputStream.flush();
	    outputStream.close();
	    
	}
	
	
}
	
