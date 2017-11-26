package com.iot.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.plaf.IconUIResource;

import com.iot.domain.Score;
import com.iot.domain.Student;
import com.iot.domain.Teacher;
import com.mysql.jdbc.PreparedStatement;

public class JDBCUtils {
	/*
	 * JDBC������
	 * ��Ҫ�������ӵĽ�������Դ���ͷ� , �Լ����������ݱ������ɾ���ġ���
	 * mysql�����ݱ� student�� �洢ѧ����Ϣ
	 * 			   login : �û���¼��Ϣ
	 * 			   score �� �ɼ���Ϣ
	 * 			   teacher: 
	 */
	public static Connection getConnection(){
		Connection connection = null;
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;
		
		// ��ȡ��·���µ�properti�ļ�
		try {
			InputStream in =
			JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
			Properties properties = new Properties();
			properties.load(in);
			driverClass = properties.getProperty("driver");
			jdbcUrl = properties.getProperty("jdbcUrl");
			user = properties.getProperty("user");
			password=properties.getProperty("password");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//ͨ�����䴴��Driver����
		Driver driver;
		try {
			driver = (Driver)Class.forName(driverClass).newInstance();
			Properties info = new Properties();
			info.put("user", user);
			info.put("password", password);
		    
			//ͨ���˷�����ȡ���ݿ�����
			connection = driver.connect(jdbcUrl, info);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return connection;
	}
	// �ر����ݿ���Դ��

	public static void release(ResultSet set, Statement statement, Connection con)
	{
		if(set!=null)
		{
			try {
				set.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(statement!=null){
			try {
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// �������ѧ����Ϣ
	public static List<Student> getAllStudent(){
		List<Student> list = new ArrayList<>();
		String sql = "select * from student order by id asc";
		Connection con = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			//ps.setString(1, name);  // ���ռλ��
			rs = ps.executeQuery();  // ��ѯ
			while(rs.next()){
				Student student = null;
				String name1 = rs.getString("name");
				String id = rs.getString("id");
				int age = rs.getInt("age");
				int sex = rs.getInt("sex");
				String grade = rs.getString("grade");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				student = new Student(name1, id, age, phone, email, sex, grade); // ��ѯ�����װΪһ������
				list.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.release(rs, ps, con); // �ͷ���Դ
		}
		return list;
	}
	
	// ͨ��ѧ���������ѧ����Ϣ
	public static Student getStudent(String name){
		String sql = "select * from student where name = ?";
		Connection con = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Student student = null;
		
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, name);  // ���ռλ��
			rs = ps.executeQuery();  // ��ѯ
			
			if(rs.next()){
				String name1 = rs.getString("name");
				String id = rs.getString("id");
				//System.out.println(id + "-----------");
				int age = rs.getInt("age");
				int sex = rs.getInt("sex");
				String grade = rs.getString("grade");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				student = new Student(name1, id, age, phone, email, sex, grade); // ��ѯ�����װΪһ������
			}
			return student;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.release(rs, ps, con); // �ͷ���Դ
		}
		return student;
	}
	
	// ͨ���������������
	public static String getPassword(String name){
		String password = null;
		String sql = "select * from login where username = ?";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = JDBCUtils.getConnection();
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			
			if(rs.next()){
				password = rs.getString("password");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, ps, connection);
		}
		
		return password;
	}
	
	// ��Ŀ
	public static int getCount(String name){
		String sql = "select count(*) from student where name = ?";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int res = 0;
		try {
			connection = JDBCUtils.getConnection();
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if(rs.next()){
				//res = rs.getInt("count");
				res = rs.getInt(1); // �±��һ��ʼ
			}
			//ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, ps, connection);
		}
		return res;
	}
	
	// ��¼ʱ�� �鿴login table ����֤���û��Ƿ����
	public static int getCountFromLoginTable(String name){
		String sql = "select count(*) from login where username = ?";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int res = 0;
		try {
			connection = JDBCUtils.getConnection();
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if(rs.next()){
				res = rs.getInt(1); // �±��һ��ʼ
			}
			//ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, ps, connection);
		}
		return res;
	}
	
	public static int getCountFromTeacherTable(String name){
		String sql = "select count(*) from teacher where name = ?";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int res = 0;
		try {
			connection = JDBCUtils.getConnection();
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if(rs.next()){
				res = rs.getInt(1); // �±��һ��ʼ
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, ps, connection);
		}
		return res;
	}
	
	public static void delete(String name){
		String sql = "delete from student where name = ?";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = JDBCUtils.getConnection();
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, name);
			//ps.executeQuery();
			// SQL ���������update,insert�ĸ�����䣬Ӧ����statement��execute()����
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, ps, connection);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		//Connection con = JDBCUtils.getConnection();
		//System.out.println(con);
		//Student s = getStudent("xiekui");
		//System.out.println(s);
		//System.out.println(getPassword("xiekui"));
		
//		List<Student> list = getAllStudent();
//		for(Student e : list){
//			System.out.println(e.getName());
//		}
		System.out.println(getCount("xieku"));
		
	}
	
	public static void add(Student s) {
		String sql = "insert into student values (?, ?, ?, ?, ?, ?, ?)";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = JDBCUtils.getConnection();
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, s.getName());
			ps.setString(2, s.getId());
			ps.setString(3, s.getGrade());
			ps.setInt(4, s.getAge());
			ps.setInt(5, s.getSex());
			ps.setString(6, s.getPhone());
			ps.setString(7, s.getEmail());
			//ps.executeQuery();
			// SQL ���������update,insert�ĸ�����䣬Ӧ����statement��execute()����
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, ps, connection);
		}
	}
	
	// ��ӽ�ʦ��Ϣ
	public static void addTeacher(Teacher s) {
		String sql = "insert into teacher values (?, ?, ?)";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = JDBCUtils.getConnection();
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, s.getName());
			ps.setString(2, s.getJno());
			ps.setString(3, s.getDepartment());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, ps, connection);
		}
	}
	
	// ���û��������룬 ��ݣ� ��ӵ�login Mysql��Table
	public static void addToLoginTable(String name, String password, String pri){
		String sql = "insert into login values (?, ?, ?)";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = JDBCUtils.getConnection();
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setString(3, pri);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, ps, connection);
		}
	}
	
	// ͨ����������ѯ�ɼ�
	public static Score getScore(String studentName) {
		Score score = null;
		String sql = "select * from score where name = ?";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = JDBCUtils.getConnection();
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, studentName);
			rs = ps.executeQuery();
			
			if(rs.next()){
				int os = rs.getInt("operatesystem");
				int ds = rs.getInt("datastruct");
				int cn = rs.getInt("computernet");
				int co = rs.getInt("computerorgan");
				score = new Score(studentName, os, ds, cn, co);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, ps, connection);
		}
		return score;
	}
	//  name  | id   | grade  | age  | sex  | phone  | email
	public static void updateStudent(Student s) {
		String sql = "update student set id = ?,  grade = ?, age = ?, sex = ?, phone = ?, email = ? where name = ?";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = JDBCUtils.getConnection();
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1,s.getId());
			ps.setString(2, s.getGrade());
			ps.setInt(3, s.getAge());
			ps.setInt(4, s.getSex());
			ps.setString(5, s.getPhone());
			ps.setString(6, s.getEmail());
			ps.setString(7, s.getName());
			//ps.executeQuery();
			// SQL ���������update,insert�ĸ�����䣬Ӧ����statement��execute()����
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, ps, connection);
		}
	}
	// | name   | operatesystem | datastruct | computernet | computerorgan
	public static void updateScore(Score s) {
		String sql = "update score set  operatesystem = ?,  datastruct = ?,computernet = ?, computerorgan = ? where name = ?";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = JDBCUtils.getConnection();
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, s.getOs());
			ps.setInt(2, s.getDs());
			ps.setInt(3, s.getCn());
			ps.setInt(4, s.getCo());
			ps.setString(5, s.getName());
			//ps.executeQuery();
			// SQL ���������update,insert�ĸ�����䣬Ӧ����statement��execute()����
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, ps, connection);
		}
	}

}
