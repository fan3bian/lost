package razer;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloServlet implements Servlet {

	@Override
	public void destroy() {
		System.out.println("HelloServlet.destroy()");
	}

	@Override
	public ServletConfig getServletConfig() {
		System.out.println("HelloServlet.getServletConfig()");
		return null;
	}

	@Override
	public String getServletInfo() {
		System.out.println("HelloServlet.getServletInfo()");
		return null;
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		String user = servletConfig.getInitParameter("user");
		System.out.println(user);
		Enumeration<String> params = servletConfig.getInitParameterNames();//inverted order?
		while (params.hasMoreElements()) {
			String name = params.nextElement();
			String value = servletConfig.getInitParameter(name);
			System.out.println(name + ":" + value);
		}
		// System.out.println(params);
		System.out.println("HelloServlet.init()");
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		System.out.println("HelloServlet.service()");
	}

	public HelloServlet() {
		System.out.println("HelloServlet.HelloServlet()");

	}
}
