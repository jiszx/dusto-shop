package com.hhnz.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.hhnz.dto.RespMsg;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("accessDeniedHandler")
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException exception) throws IOException, ServletException {
		ServletOutputStream writer=response.getOutputStream();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		StringBuffer sb=new StringBuffer();
		if(StringUtils.hasLength(request.getHeader("X-Requested-With"))){
			Gson gson = new Gson();
			RespMsg msg = new RespMsg();
			msg.setErrorCode(403);
			response.setContentType("application/json");
			msg.setErrorMessage("你没有权限访问该资源");
			writer.write(gson.toJson(msg).getBytes("UTF-8"));
			
		}else{
			sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"><title>哎呀，找不到</title>");
			sb.append("</head><body>");
			sb.append("您没有权限访问此资源");
			sb.append("</body></html>");
			writer.write(sb.toString().getBytes("UTF-8"));
		}
		writer.close();
	}
}
