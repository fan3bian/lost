package razer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LoginServlet implements Servlet {

	@Override
	public void destroy() {
	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet.service()");
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		System.out.println("user:[" + user + "] password:[" + password + "]");

		String[] interests = request.getParameterValues("interests");
		for (String interest : interests) {
			System.out.println(interest);
		}
		Enumeration<String> params = request.getParameterNames();
		while(params.hasMoreElements()){
			String name = params.nextElement();
			String value = request.getParameter(name);
			System.out.println(name +" : "+value);
		}
		Map<String,String[]> map =request.getParameterMap();
		for(Map.Entry<String, String[]> entry:map.entrySet()){
			System.out.println(entry.getKey()+"::"+Arrays.asList(entry.getValue()));
		}
		
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
	
		String requestURI = httpServletRequest.getRequestURI();
		System.out.println(requestURI);
		StringBuffer requestURL = httpServletRequest.getRequestURL();
		System.out.println(requestURL);
		String method =httpServletRequest.getMethod();
		System.out.println(method);
		//修改为GET
		String queryString =httpServletRequest.getQueryString();
		System.out.println(queryString);
		
		String servletPath =httpServletRequest.getServletPath();
		System.out.println(servletPath);
		//设置响应的内容类型
		response.setContentType("application/msword");//word格式，tomcat的web.xml中有提供
		PrintWriter out = response.getWriter();
		out.print("PrintWriter out");
		
	}
	public static void main(String[] args) {
		Date d =new Date();
	}
}
