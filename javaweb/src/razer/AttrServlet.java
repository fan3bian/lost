package razer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AttrServlet
 */
@WebServlet("/attrServlet")
public class AttrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AttrServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1.pageContext 无法获取
		// 2.request
		PrintWriter out = response.getWriter();
		Object requestAttr = request.getAttribute("requestAttr");
		out.println("requestAttr:" + requestAttr);
		// 3.session
		Object sessionAttr = request.getSession().getAttribute("sessionAttr");
		out.println("sessionAttr:" + sessionAttr);
		// 4.application
		Object applicationAttr = getServletContext().getAttribute("applicationAttr");
		out.println("applicationAttr:" + applicationAttr);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
