package com.simplemvc.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simple.dao.UserDao;
import com.simple.dao.UserDaoImpl;
import com.simplemvc.domain.CriteriaUser;
import com.simplemvc.domain.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	UserDao userDao = new UserDaoImpl();
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡservletPath����÷�����
		String servletPath = request.getServletPath();
		System.out.println(servletPath + "----------------");
		int s = servletPath.lastIndexOf('/');
		String methodName = servletPath.substring(s);
		methodName = methodName.substring(1, methodName.length() - 3);
		
		try {
			// ͨ�������õ�ǰ������Ķ�Ӧ���������ҵ��ø÷���
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("./page.jsp");
		}
	}
	
	protected void query(HttpServletRequest request, HttpServletResponse response){
		// ����������
		String userName = request.getParameter("name");
		String age = request.getParameter("age"); System.out.println(age);
		String address = request.getParameter("address");
		Integer age1 = (age == "" || age == null) ? null : Integer.parseInt(age);
		// ����ģ����ѯ����
		CriteriaUser cuser = new CriteriaUser(userName, age1, address);
		// ��ò�ѯ���
		List<User> users =  userDao.getForListWithCriteriaCustomer(cuser);
		// ת��
		request.setAttribute("users", users);
		try {
			request.getRequestDispatcher("/pages/welcome.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void addUser(HttpServletRequest request, HttpServletResponse response){
		String userName = request.getParameter("username");
		String age = request.getParameter("age");
		String address = request.getParameter("address");
		Integer age1 = (age == "" || age == null) ? null : Integer.parseInt(age);
		String id = request.getParameter("id");
		long numOfUser = userDao.getCountWithName(userName);
		try{
			if(numOfUser > 0){
				request.setAttribute("message", "" + userName + "�Ѿ���ʹ�ã�����������");
				request.getRequestDispatcher("/pages/addUser.jsp").forward(request, response);
				System.out.println("-----------------------------------");
				return ;
			}
			
			User user = new User(address, age1, userName);
			
			userDao.add(user);
			response.sendRedirect("welcome.jsp");
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response){
		String userName = request.getParameter("username");
		String age = request.getParameter("age");
		String address = request.getParameter("address");
		String id = request.getParameter("id");
		String oldName = request.getParameter("oldName");
		
		Integer age1 = (age == "" || age == null) ? null : Integer.parseInt(age);
		
		if(!oldName.equalsIgnoreCase(userName)){
			long numOfUser = userDao.getCountWithName(userName);
			if(numOfUser > 0){
				request.setAttribute("message", "" + userName + "�Ѿ���ʹ�ã�����������");
				try {
					request.getRequestDispatcher("/pages/update.jsp").forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return ;
			}	
		}
		System.out.println(id);
		System.out.println(userName);
		System.out.println(age);
		System.out.println(address);

		User user = new User(address, age1, userName);
		user.setId(Integer.parseInt(id));
		System.out.println("----------------");
		userDao.update(user);
		try {
			response.sendRedirect("welcome.jsp");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	protected void delete(HttpServletRequest request, HttpServletResponse response){
		int id = 0;
		String idStr = request.getParameter("id");
		try{
			id = Integer.parseInt(idStr);
			userDao.delete(id);
			response.sendRedirect("query.do");
		}catch (Exception e) {
			
		}
	}
	
	protected void edit(HttpServletRequest request, HttpServletResponse response){
		String idStr = request.getParameter("id");
		
		try{
			User user = userDao.get(Integer.parseInt(idStr));
			if(user!=null){
				request.setAttribute("user", user);
				request.getRequestDispatcher("/pages/update.jsp").forward(request, response);
				return;
			}
		}catch (NumberFormatException | ServletException | IOException e) {
			
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
