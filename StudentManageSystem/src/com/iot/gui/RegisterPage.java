package com.iot.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.iot.domain.Student;
import com.iot.domain.Teacher;
import com.iot.jdbc.JDBCUtils;

public class RegisterPage {
	/**
	 * 整合添加学生和注册学生的功能
	 * @param args
	 */
	// operate 指明是添加学生操作还是学生注册操作
	public static void regiter(String who){
		JFrame frame = new JFrame("注册");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				super.windowClosing(e);
				if(who.equals("teacher")) ManagePage.showManagePage();
				else LoginPage.showLoginPage();
				frame.dispose();
			}
		
		});
		
		frame.setResizable(false);
		frame.setSize(600, 800);
		GuiUtils.setUIFont(); // 设置字体
		frame.setLocation(600, 100);
		
		// 贴背景图
		ImageIcon bg = new ImageIcon("image/f.jpg");
		JLabel imageLabel = new JLabel(bg);
		imageLabel.setBounds(0, 0, 600, 800);
		// 把背景Label放在最底层，并且把上层控件，都设置为透明 setOpaque(false)
		frame.getLayeredPane().add(imageLabel,new Integer(Integer.MIN_VALUE)); 
		
		JPanel j = (JPanel) frame.getContentPane();
		j.setOpaque(false);
		JPanel c = new JPanel();
		c.setLayout(new BorderLayout());
		c.setOpaque(false);
		
		// name
		JPanel panel = new JPanel(null);
		panel.setOpaque(false);
		JLabel nameLabel = new JLabel("*姓名 :");
		JTextField nameField = new JTextField();
		nameLabel.setBounds(80, 40, 100, 30);
		nameField.setBounds(200, 40, 150, 30);
		nameLabel.setOpaque(false);
		panel.add(nameLabel);
		panel.add(nameField);
			
		// 学号
		JLabel idLabel = new JLabel("*学号 :");
		JTextField idField = new JTextField();
		idLabel.setBounds(80, 100, 100, 30);
		idField.setBounds(200, 100, 150, 30);
		idLabel.setOpaque(false);
		panel.add(idLabel);
		panel.add(idField);

		// 年级
		JLabel gradeLabel = new JLabel("*年级 :");
		JTextField gradeField = new JTextField();
		gradeLabel.setBounds(80, 160, 100, 30);
		gradeField.setBounds(200, 160, 150, 30);
		gradeLabel.setOpaque(false);
		panel.add(gradeLabel);
		panel.add(gradeField);
		
		// age
		JLabel ageLabel = new JLabel("*年龄 :");
		JTextField ageField = new JTextField();
		ageLabel.setBounds(80, 220, 100, 30);
		ageField.setBounds(200, 220, 150, 30);
		ageLabel.setOpaque(false);
		panel.add(ageLabel);
		panel.add(ageField);
		
		// sex
		JLabel sexLabel = new JLabel("*性别 :");
		JTextField sexField = new JTextField();
		sexLabel.setBounds(80, 280, 100, 30);
		sexField.setBounds(200, 280, 150, 30);
		sexLabel.setOpaque(false);
		panel.add(sexLabel);
		panel.add(sexField);
		
		// phone
		JLabel phoneLabel = new JLabel(" 手机 :");
		JTextField phoneField = new JTextField();
		phoneLabel.setBounds(80, 340, 100, 30);
		phoneField.setBounds(200, 340, 180, 30);
		phoneLabel.setOpaque(false);
		panel.add(phoneLabel);
		panel.add(phoneField);
		
		// email 
		JLabel emailLabel = new JLabel(" email :");
		JTextField emailField = new JTextField();
		emailLabel.setBounds(80, 400, 100, 30);
		emailField.setBounds(200, 400, 180, 30);
		emailLabel.setOpaque(false);
		panel.add(emailLabel);
		panel.add(emailField);
		
		// 密码
		JLabel firstPasswdLabel = new JLabel(" 密码 :");
		JPasswordField firstPasswdField = new JPasswordField();
		firstPasswdLabel.setBounds(80, 460, 100, 30);
		firstPasswdField.setBounds(200, 460, 180, 30);
		firstPasswdLabel.setOpaque(false);
		panel.add(firstPasswdLabel);
		panel.add(firstPasswdField);
		
		// 确认密码
		JLabel secondPasswdLabel = new JLabel("确认密码 :");
		JPasswordField secondPasswdField = new JPasswordField();
		secondPasswdLabel.setBounds(80, 520, 1600, 30);
		secondPasswdField.setBounds(220, 520, 180, 30);
		secondPasswdLabel.setOpaque(false);
		panel.add(secondPasswdLabel);
		panel.add(secondPasswdField);
		
		JButton sure = new JButton(who.equals("student") ? "注册" : "添加" );
		sure.setBounds(80, 600, 140, 30);
		sure.setOpaque(false);
		panel.add(sure);
		
		JButton clear = new JButton("重置");
		clear.setBounds(280, 600, 140, 30);
		clear.setOpaque(false);
		panel.add(clear);
		// 重置
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				nameField.setText("");
				idField.setText("");
				gradeField.setText("");
				ageField.setText("");
				phoneField.setText("");
				emailField.setText("");
				sexField.setText("");
				firstPasswdField.setText("");
				secondPasswdField.setText("");
			}
		});
		
		//  注册
		sure.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText().trim();
				String id = idField.getText().trim();
				String grade = gradeField.getText().trim();
				String age = ageField.getText().trim();
				String sex = sexField.getText().trim();
				String phone = phoneField.getText().trim();
				String email = emailField.getText().trim();
				if(name == null || name.isEmpty() || id == null || id.isEmpty() || 
						grade == null || grade.isEmpty() || age == null || age.isEmpty() || sex==null || sex.isEmpty()){
					JOptionPane.showMessageDialog(null, "这些内容必填！");
					return;
				}
				String firstPasswd = new String(firstPasswdField.getPassword()).trim();
				String secondPasswd = new String(secondPasswdField.getPassword()).trim();
				// 密码为空
				if(firstPasswd == null || firstPasswd.isEmpty() || secondPasswd == null || secondPasswd.isEmpty()){
					JOptionPane.showMessageDialog(null, "请输入密码！");
					return;
				}
				// 检查用户名是否存在
				if(JDBCUtils.getCount(name) > 0){
					JOptionPane.showMessageDialog(null, "该用户名已存在， 请重新输入姓名！");
					return;
				}
				
				if(!firstPasswd.equals(secondPasswd)){
					JOptionPane.showMessageDialog(null, "两次密码不同， 请重新输入密码！");
					return;
				}
				System.out.println(sex.equals("男"));
				int sexToInt = (sex.equals("男") ? 0 : 1);
				int ageToInt = 0;
				// 数字转string异常，控制一下
				try{
					ageToInt = Integer.parseInt(age);
				}catch(NumberFormatException e1){
					//e1.printStackTrace();
				}
				
				// 存入student表中
				Student s = new Student(name, id, ageToInt, phone, email, sexToInt, grade);
				JDBCUtils.add(s);
				
				JDBCUtils.addToLoginTable(name, firstPasswd, "student");
				
				// 添加成功, 以operate来决定下面的操作
				// 如果是注册的话，重新回到login页面
				if(who.equals("student")){
					JOptionPane.showMessageDialog(null, "注册成功！");
					frame.dispose();
					LoginPage.showLoginPage();
				}
				if(who.equals("teacher")){
					JOptionPane.showMessageDialog(null, "添加成功！");
					frame.dispose();
					ManagePage.showManagePage(); // 再创建一个新的页面，就是为了显示新添加的学生信息
				}
			}
		});
		c.add(panel);
		frame.add(panel);
		frame.setVisible(true);
	}
	
	/**
	 * 下面是一个简单的教室注册页面
	 */
	public static void teacherRegister(){
		//界面外面是一个JFrame框架， 
		JFrame frame = new JFrame("TeacherRegister");
		frame.setSize(600,420);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				super.windowClosing(e);
				LoginPage.showLoginPage();
				frame.dispose();
			}
		
		});
		frame.setResizable(false); // 窗体大小保持不变
		GuiUtils.setUIFont(); // 设置字体
		// 贴背景图
		ImageIcon bg = new ImageIcon("image/b.png");
		JLabel imageLabel = new JLabel(bg);
		imageLabel.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());
		frame.getLayeredPane().add(imageLabel,new Integer(Integer.MIN_VALUE)); 
		
		JPanel j = (JPanel) frame.getContentPane();
		j.setOpaque(false);
		JPanel c = new JPanel();
		c.setLayout(new BorderLayout());
		c.setOpaque(false);
		
		// name
		JPanel panel = new JPanel(null);
		panel.setOpaque(false);
		JLabel nameLabel = new JLabel("姓名 :");
		JTextField nameField = new JTextField();
		nameLabel.setBounds(80, 40, 100, 30);
		nameField.setBounds(200, 40, 150, 30);
		nameLabel.setOpaque(false);
		panel.add(nameLabel);
		panel.add(nameField);
			
		// 学号
		JLabel idLabel = new JLabel("教工号 :");
		JTextField idField = new JTextField();
		idLabel.setBounds(80, 100, 140, 30);
		idField.setBounds(200, 100, 150, 30);
		idLabel.setOpaque(false);
		panel.add(idLabel);
		panel.add(idField);

		// 年级
		JLabel gradeLabel = new JLabel("系 :");
		JTextField gradeField = new JTextField();
		gradeLabel.setBounds(80, 160, 100, 30);
		gradeField.setBounds(200, 160, 150, 30);
		gradeLabel.setOpaque(false);
		panel.add(gradeLabel);
		panel.add(gradeField);
		
		// 密码
		JLabel firstPasswdLabel = new JLabel(" 密码 :");
		JPasswordField firstPasswdField = new JPasswordField();
		firstPasswdLabel.setBounds(80, 220, 100, 30);
		firstPasswdField.setBounds(200, 220, 180, 30);
		firstPasswdLabel.setOpaque(false);
		panel.add(firstPasswdLabel);
		panel.add(firstPasswdField);
		
		// 确认密码
		JLabel secondPasswdLabel = new JLabel("确认密码 :");
		JPasswordField secondPasswdField = new JPasswordField();
		secondPasswdLabel.setBounds(80, 280, 1600, 30);
		secondPasswdField.setBounds(220, 280, 180, 30);
		secondPasswdLabel.setOpaque(false);
		panel.add(secondPasswdLabel);
		panel.add(secondPasswdField);
		
		JButton sure = new JButton("注册");
		sure.setBounds(80, 340, 140, 30);
		sure.setOpaque(false);
		panel.add(sure);
		
		JButton clear = new JButton("重置");
		clear.setBounds(280, 340, 140, 30);
		clear.setOpaque(false);
		panel.add(clear);
		
		// 重置
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				nameField.setText("");
				idField.setText("");
				gradeField.setText("");
				firstPasswdField.setText("");
				secondPasswdField.setText("");
			}
		});
		
		sure.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText().trim();
				String id = idField.getText().trim();
				String grade = gradeField.getText().trim();
				if(name == null || name.isEmpty() || id == null || id.isEmpty() || 
						grade == null || grade.isEmpty()){
					JOptionPane.showMessageDialog(null, "这些内容必填！");
					return;
				}
				String firstPasswd = new String(firstPasswdField.getPassword()).trim();
				String secondPasswd = new String(secondPasswdField.getPassword()).trim();
				// 密码为空
				if(firstPasswd == null || firstPasswd.isEmpty() || secondPasswd == null || secondPasswd.isEmpty()){
					JOptionPane.showMessageDialog(null, "请输入密码！");
					return;
				}
				// 检查用户名是否存在
				if(JDBCUtils.getCount(name) > 0){
					JOptionPane.showMessageDialog(null, "该用户名已存在， 请重新输入姓名！");
					return;
				}
				
				if(!firstPasswd.equals(secondPasswd)){
					JOptionPane.showMessageDialog(null, "两次密码不同， 请重新输入密码！");
					return;
				}
				
				Teacher teacher = new Teacher(name, id, grade);
				// 存入teacher表中
				JDBCUtils.addTeacher(teacher);
				
				// 存入 管理password表
				JDBCUtils.addToLoginTable(name, firstPasswd, "teacher");
				
				// 添加成功,
				// 如果是注册的话，重新回到login页面
				JOptionPane.showMessageDialog(null, "注册成功！");
				frame.dispose();
				LoginPage.showLoginPage();
				
			}
		});
		c.add(panel);
		frame.add(c);
		frame.setLocation(600, 300);
		frame.setVisible(true);
		
	}
	public static void main(String[] args) {
		//regiter("添加");
		teacherRegister();
	}

}
