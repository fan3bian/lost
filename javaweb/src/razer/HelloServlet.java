package razer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.print.DocFlavor.INPUT_STREAM;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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
		String servletName = servletConfig.getServletName();
		System.out.println(servletName);
		ServletContext servletContext = servletConfig.getServletContext();
		Enumeration<String> contextParams =servletContext.getInitParameterNames();
		while (contextParams.hasMoreElements()) {
			String name = contextParams.nextElement();
			String value = servletContext.getInitParameter(name);
			System.out.println(name + ":" + value);
		}
		
		String realPath = servletContext.getRealPath("index.jsp");
		System.out.println(realPath);
		
		String context = servletContext.getContextPath();
		System.out.println(context);
		//获取当前WEB应用某一文件对应输入流
		InputStream inStream =servletContext.getResourceAsStream("/WEB-INF/classes/jdbc.properties");//path为当前web的根目录。
		System.out.println(inStream);
		ClassLoader classLoader = this.getClass().getClassLoader();
		InputStream ins =classLoader.getResourceAsStream("jdbc.properties");
		System.out.println(ins);
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
