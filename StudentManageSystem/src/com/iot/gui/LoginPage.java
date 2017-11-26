package com.iot.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.iot.jdbc.JDBCUtils;



public class LoginPage {
	
	public static void showLoginPage(){
		
		//界面外面是一个JFrame框架， 
		JFrame frame = new JFrame("StudentsManageSystem");
		frame.setSize(600,400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false); // 窗体大小保持不变
		GuiUtils.setUIFont(); // 设置字体
		
		// 贴背景图
		ImageIcon bg = new ImageIcon("image/b.png");
		JLabel imageLabel = new JLabel(bg);
		imageLabel.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());
		// 把背景Label放在最底层，并且把上层控件，都设置为透明 setOpaque(false)
		frame.getLayeredPane().add(imageLabel,new Integer(Integer.MIN_VALUE)); 
		
		JPanel j = (JPanel) frame.getContentPane();
		j.setOpaque(false);
		JPanel c = new JPanel();
		c.setOpaque(false);
		c.setLayout(new BorderLayout()); // 整体布局 
		
		JTextField username = new JTextField();
		JPasswordField password = new JPasswordField();
		
		// 底部的三个按钮
		JButton ok = new JButton("确定");
		JButton cancel = new JButton("取消");
		JButton register = new JButton("注册");
		
		//顶部标题
		JPanel titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(new JLabel("系统登录"));
		c.add(titlePanel,"North");
		
		// 用户输入区域
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(null);
		fieldPanel.setOpaque(false);
		JLabel a1 = new JLabel("用户名:");
		a1.setBounds(140,40,120,40);
		JLabel a2 = new JLabel("密   码:");
		a2.setBounds(140,100,120,40);
		fieldPanel.add(a1);
		fieldPanel.add(a2);
		username.setBounds(250,45,140,30);
		password.setBounds(250,105,140,30);
		fieldPanel.add(username);
		fieldPanel.add(password);
		c.add(fieldPanel,"Center");	
		
		// 底部单选框， 用户身份选择 
		JRadioButton b1 = new JRadioButton("student");
		b1.setOpaque(false);
		JRadioButton b2 = new JRadioButton("teacher");
		b1.setSelected(true); // 默认情况下 选中学生
		b2.setOpaque(false);
		ButtonGroup buttonGroup = new ButtonGroup(); // 将JRadioButton加入这里可以使选择互斥
		buttonGroup.add(b1);
		buttonGroup.add(b2);
		
		JPanel choice = new JPanel(); // 单选框Panel
		choice.setOpaque(false);
		choice.setLayout(new FlowLayout());
		choice.add(b1);
		choice.add(b2);
		// 底部的三个按钮构成一个Panel
		JPanel buttons = new JPanel(); 
		buttons.setOpaque(false);
		buttons.setLayout(new FlowLayout());
		
		//buttons 和 choice构成一个Panel，放在最下面
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(choice, "North");
		
		// Button事件监听机制
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_okButton_actionPerformed(e);
			}
			private void do_okButton_actionPerformed(ActionEvent e){
				String UserName = new String(username.getText());
				String PassWord = new String(password.getPassword());
				
				if(UserName.isEmpty() || PassWord.isEmpty()){
					JOptionPane.showMessageDialog(null, "账号或密码不得为空！");
					return;
				}

				// 学生登录
				if(b1.isSelected()){
					
					int numOfStu = JDBCUtils.getCount(UserName);
					if(numOfStu == 0){
						JOptionPane.showMessageDialog(null, "查无此生！");
						return;
					}	
					String passwordFromSql = JDBCUtils.getPassword(UserName);
					if(passwordFromSql == null || !passwordFromSql.equals(PassWord)){
						JOptionPane.showMessageDialog(null, "账号或密码错误！");
					}else if(passwordFromSql.equals(PassWord)){ // 密码相符， 登录成功
						frame.dispose();
						StudentPage.showStudentPage(UserName, "student");
					}else{
						
					}
					
				}
				// 教室登录
				if(b2.isSelected()){
					int numOfTea = JDBCUtils.getCountFromTeacherTable(UserName);
					if(numOfTea == 0){
						JOptionPane.showMessageDialog(null, "查无此师！");
						return;
					}
					frame.dispose();
					ManagePage.showManagePage();
				}
			}
		});
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_cancelButton_actionPerformed(e);
			}
			private void do_cancelButton_actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		register.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_registerButton_actionPerformed(e);
			}
			private void do_registerButton_actionPerformed(ActionEvent e){
				// 学生注册
				if(b1.isSelected()){
					frame.dispose(); // 释放资源
					RegisterPage.regiter("student");
				}
				// 老师注册
				if(b2.isSelected()){
					frame.dispose();
					RegisterPage.teacherRegister();
				}
			}
		});
		buttons.add(ok);
		buttons.add(cancel);
		buttons.add(register);
		buttonPanel.add(buttons, "South");
		c.add(buttonPanel,"South");
		c.setOpaque(false);
		
		frame.add(c);
		frame.setLocation(600, 300);
		frame.setVisible(true);
	}	
	
	// 单元测试
	public static void main(String[] args) {
		LoginPage.showLoginPage();
//		try {
//			
//			FileInputStream is = new FileInputStream("image/a.png");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
	}
	

}


