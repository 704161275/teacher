package org.teach.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.teach.service.LoginService;
import org.teach.vo.Menu;
import org.teach.vo.User;

public class LoginServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LoginService loginService = new LoginService();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("---LoginServlet---");
		String method = req.getParameter("method");
		if(method != null){
			try {
				Method declaredMethod = this.getClass().getDeclaredMethod(method, HttpServletRequest.class,HttpServletResponse.class);
				declaredMethod.invoke(this, req,resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	protected void login(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		try {
			User user = loginService.findUser(username, password);
			
			if(user!=null){
				req.getSession().setAttribute("username", username);
				req.getSession().setAttribute("admin", "ASD546sdf45wesdf");
				//输出到登录成功的页面
				req.getSession().setAttribute("userinfo", user);
				//跳转
				req.getRequestDispatcher("main/main.jsp").forward(req, resp);
			}else{
				resp.sendRedirect("errorServlet");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	protected void exit(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getSession().invalidate();
		resp.sendRedirect("index.jsp");
	}

	protected void getLeft(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		User user = (User) req.getSession().getAttribute("userinfo");
		
		List<Menu> list = loginService.getManage(user);
		JSONArray ja = JSONArray.fromObject(list);
		
		String json = ja.toString();
		
		req.setAttribute("json", json);
		req.getRequestDispatcher("main/menu.jsp").forward(req, resp);
	}

}
