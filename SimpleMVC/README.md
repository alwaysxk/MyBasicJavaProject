基于MVC+Servlet+JSP增删改查
## MVC
```java
MVC全名是Model View Controller，是模型(model)－视图(view)－控制器(controller)的缩写，一种软件设计典范，用一种业务逻辑、数据、界面显示分离的方法组织代码，将业务逻辑聚集到一个部件里面，在改进和个性化定制界面及用户交互的同时，不需要重新编写业务逻辑。

模型：模型是应用程序的主体部分，模型表示业务数据和业务逻辑，一个模型能为多个视图提供数据，由于应用于模型的代码只需写一次就可以被多个试图调用，提高了代码的可重用性。

视图：是用户看到的并与之交互的界面，视图能够接受用户的输入，向用户显示相关的数据，在这里不会进行业务处理。

控制器：接受用户的输入并调用模型和视图去完成用户的需求，控制器接受请求并决定调用哪个模型组件去处理请求，然后调用那个视图来显示模型处理返回的数据。
```
MVC工作的大致流程如下：
![mvc][1]

### 基于MVC+Servlet+JSP增删改查案例
#### 概述
首先该案例会涉及到这些技术或知识点: MVC设计模式, JSP+Servlet, MySQL+JDBC+C3P0, java反射机制，在这样一个简单的增删改查的案例里，这些技术设计的可能不是很深,并且没有涉及业务逻辑。
功能，大致功能就是将数据库的一些简单操作(简单的增删改查)搬到页面上，再具体一点就是，可以通过模糊查询(极其简单的)，查找符合的用户，或者全部用户；删除用户；增加用户；更新用户信息。
#### DAO
DAO在javaweb开发中一般属于model层，DAO即Data Access Object(数据存取对象),一般用来对数据库进行最基本的操作，如增删改查。在此案例中定义了DAO<T>，实现的方法就是基本的增删改查，以便子类继承，提高了代码的可重用性。

#### 关于多个请求对应一个Servlet
刚开始接触Servlet，总是一个请求对应的一个Servet，比如一个query操作对应相应的QueryServlet,一个Update操作对应一个相应的UpdateServlet, 这样做不仅会造成代码大量冗余，还需要在xml文件多次映射。因此，一个Servlet处理多个请求会更加的方便。
##### xml 文件配置
```xml
  <servlet>
    <servlet-name>userServlet</servlet-name>
    <servlet-class>com.simplemvc.servlet.UserServlet</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>UserServlet</servlet-name>
    <url-pattern>*.do</url-pattern> // 可以映射到以".do"结尾的多个URL
  </servlet-mapping>
```
##### UserServlet

上述xml文件中映射的URL都是以".do"结尾的，进而以具体的操作对应"*"。 例如：query.do, 先在UserServlet定义query方法， 当我们点击一个链接后，URL参数传到request里面，"query"就可通过截取字符串servletPath得到，进而我们通过反射来执行先前定义的query()；

```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   // 截取servletPath来获得方法名
		String servletPath = request.getServletPath();
		String methodName = servletPath.substring(1);
		methodName = methodName.substring(0, methodName.length() - 3);
		
		try {    
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class); // 通过反射获得对应方法
                        method.invoke(this, request, response); // 执行方法
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
	protected void query(HttpServletRequest request, HttpServletResponse response){
		// 获得请求参数
		String userName = request.getParameter("name");
		String age = request.getParameter("age");
		String address = request.getParameter("address");
		Integer age1 = (age == "" || age == null) ? null : Integer.parseInt(age);
		// 创造模糊查询对象
		CriteriaUser cuser = new CriteriaUser(userName, age1, address);
		// 获得查询结果
		List<User> users =  userDao.getForListWithCriteriaCustomer(cuser);
		// 转发
		request.setAttribute("users", users);
		try {
			request.getRequestDispatcher("/pages/welcome.jsp").forward(request, response);
		} catch (ServletException e) {
                } catch (IOException e) {}
	}
```

  [1]: http://www.alwaysxk.cn/usr/uploads/2017/10/822713290.png
