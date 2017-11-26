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
	 * �������ѧ����ע��ѧ���Ĺ���
	 * @param args
	 */
	// operate ָ�������ѧ����������ѧ��ע�����
	public static void regiter(String who){
		JFrame frame = new JFrame("ע��");
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
		GuiUtils.setUIFont(); // ��������
		frame.setLocation(600, 100);
		
		// ������ͼ
		ImageIcon bg = new ImageIcon("image/f.jpg");
		JLabel imageLabel = new JLabel(bg);
		imageLabel.setBounds(0, 0, 600, 800);
		// �ѱ���Label������ײ㣬���Ұ��ϲ�ؼ���������Ϊ͸�� setOpaque(false)
		frame.getLayeredPane().add(imageLabel,new Integer(Integer.MIN_VALUE)); 
		
		JPanel j = (JPanel) frame.getContentPane();
		j.setOpaque(false);
		JPanel c = new JPanel();
		c.setLayout(new BorderLayout());
		c.setOpaque(false);
		
		// name
		JPanel panel = new JPanel(null);
		panel.setOpaque(false);
		JLabel nameLabel = new JLabel("*���� :");
		JTextField nameField = new JTextField();
		nameLabel.setBounds(80, 40, 100, 30);
		nameField.setBounds(200, 40, 150, 30);
		nameLabel.setOpaque(false);
		panel.add(nameLabel);
		panel.add(nameField);
			
		// ѧ��
		JLabel idLabel = new JLabel("*ѧ�� :");
		JTextField idField = new JTextField();
		idLabel.setBounds(80, 100, 100, 30);
		idField.setBounds(200, 100, 150, 30);
		idLabel.setOpaque(false);
		panel.add(idLabel);
		panel.add(idField);

		// �꼶
		JLabel gradeLabel = new JLabel("*�꼶 :");
		JTextField gradeField = new JTextField();
		gradeLabel.setBounds(80, 160, 100, 30);
		gradeField.setBounds(200, 160, 150, 30);
		gradeLabel.setOpaque(false);
		panel.add(gradeLabel);
		panel.add(gradeField);
		
		// age
		JLabel ageLabel = new JLabel("*���� :");
		JTextField ageField = new JTextField();
		ageLabel.setBounds(80, 220, 100, 30);
		ageField.setBounds(200, 220, 150, 30);
		ageLabel.setOpaque(false);
		panel.add(ageLabel);
		panel.add(ageField);
		
		// sex
		JLabel sexLabel = new JLabel("*�Ա� :");
		JTextField sexField = new JTextField();
		sexLabel.setBounds(80, 280, 100, 30);
		sexField.setBounds(200, 280, 150, 30);
		sexLabel.setOpaque(false);
		panel.add(sexLabel);
		panel.add(sexField);
		
		// phone
		JLabel phoneLabel = new JLabel(" �ֻ� :");
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
		
		// ����
		JLabel firstPasswdLabel = new JLabel(" ���� :");
		JPasswordField firstPasswdField = new JPasswordField();
		firstPasswdLabel.setBounds(80, 460, 100, 30);
		firstPasswdField.setBounds(200, 460, 180, 30);
		firstPasswdLabel.setOpaque(false);
		panel.add(firstPasswdLabel);
		panel.add(firstPasswdField);
		
		// ȷ������
		JLabel secondPasswdLabel = new JLabel("ȷ������ :");
		JPasswordField secondPasswdField = new JPasswordField();
		secondPasswdLabel.setBounds(80, 520, 1600, 30);
		secondPasswdField.setBounds(220, 520, 180, 30);
		secondPasswdLabel.setOpaque(false);
		panel.add(secondPasswdLabel);
		panel.add(secondPasswdField);
		
		JButton sure = new JButton(who.equals("student") ? "ע��" : "���" );
		sure.setBounds(80, 600, 140, 30);
		sure.setOpaque(false);
		panel.add(sure);
		
		JButton clear = new JButton("����");
		clear.setBounds(280, 600, 140, 30);
		clear.setOpaque(false);
		panel.add(clear);
		// ����
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
		
		//  ע��
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
					JOptionPane.showMessageDialog(null, "��Щ���ݱ��");
					return;
				}
				String firstPasswd = new String(firstPasswdField.getPassword()).trim();
				String secondPasswd = new String(secondPasswdField.getPassword()).trim();
				// ����Ϊ��
				if(firstPasswd == null || firstPasswd.isEmpty() || secondPasswd == null || secondPasswd.isEmpty()){
					JOptionPane.showMessageDialog(null, "���������룡");
					return;
				}
				// ����û����Ƿ����
				if(JDBCUtils.getCount(name) > 0){
					JOptionPane.showMessageDialog(null, "���û����Ѵ��ڣ� ����������������");
					return;
				}
				
				if(!firstPasswd.equals(secondPasswd)){
					JOptionPane.showMessageDialog(null, "�������벻ͬ�� �������������룡");
					return;
				}
				System.out.println(sex.equals("��"));
				int sexToInt = (sex.equals("��") ? 0 : 1);
				int ageToInt = 0;
				// ����תstring�쳣������һ��
				try{
					ageToInt = Integer.parseInt(age);
				}catch(NumberFormatException e1){
					//e1.printStackTrace();
				}
				
				// ����student����
				Student s = new Student(name, id, ageToInt, phone, email, sexToInt, grade);
				JDBCUtils.add(s);
				
				JDBCUtils.addToLoginTable(name, firstPasswd, "student");
				
				// ��ӳɹ�, ��operate����������Ĳ���
				// �����ע��Ļ������»ص�loginҳ��
				if(who.equals("student")){
					JOptionPane.showMessageDialog(null, "ע��ɹ���");
					frame.dispose();
					LoginPage.showLoginPage();
				}
				if(who.equals("teacher")){
					JOptionPane.showMessageDialog(null, "��ӳɹ���");
					frame.dispose();
					ManagePage.showManagePage(); // �ٴ���һ���µ�ҳ�棬����Ϊ����ʾ����ӵ�ѧ����Ϣ
				}
			}
		});
		c.add(panel);
		frame.add(panel);
		frame.setVisible(true);
	}
	
	/**
	 * ������һ���򵥵Ľ���ע��ҳ��
	 */
	public static void teacherRegister(){
		//����������һ��JFrame��ܣ� 
		JFrame frame = new JFrame("TeacherRegister");
		frame.setSize(600,420);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				super.windowClosing(e);
				LoginPage.showLoginPage();
				frame.dispose();
			}
		
		});
		frame.setResizable(false); // �����С���ֲ���
		GuiUtils.setUIFont(); // ��������
		// ������ͼ
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
		JLabel nameLabel = new JLabel("���� :");
		JTextField nameField = new JTextField();
		nameLabel.setBounds(80, 40, 100, 30);
		nameField.setBounds(200, 40, 150, 30);
		nameLabel.setOpaque(false);
		panel.add(nameLabel);
		panel.add(nameField);
			
		// ѧ��
		JLabel idLabel = new JLabel("�̹��� :");
		JTextField idField = new JTextField();
		idLabel.setBounds(80, 100, 140, 30);
		idField.setBounds(200, 100, 150, 30);
		idLabel.setOpaque(false);
		panel.add(idLabel);
		panel.add(idField);

		// �꼶
		JLabel gradeLabel = new JLabel("ϵ :");
		JTextField gradeField = new JTextField();
		gradeLabel.setBounds(80, 160, 100, 30);
		gradeField.setBounds(200, 160, 150, 30);
		gradeLabel.setOpaque(false);
		panel.add(gradeLabel);
		panel.add(gradeField);
		
		// ����
		JLabel firstPasswdLabel = new JLabel(" ���� :");
		JPasswordField firstPasswdField = new JPasswordField();
		firstPasswdLabel.setBounds(80, 220, 100, 30);
		firstPasswdField.setBounds(200, 220, 180, 30);
		firstPasswdLabel.setOpaque(false);
		panel.add(firstPasswdLabel);
		panel.add(firstPasswdField);
		
		// ȷ������
		JLabel secondPasswdLabel = new JLabel("ȷ������ :");
		JPasswordField secondPasswdField = new JPasswordField();
		secondPasswdLabel.setBounds(80, 280, 1600, 30);
		secondPasswdField.setBounds(220, 280, 180, 30);
		secondPasswdLabel.setOpaque(false);
		panel.add(secondPasswdLabel);
		panel.add(secondPasswdField);
		
		JButton sure = new JButton("ע��");
		sure.setBounds(80, 340, 140, 30);
		sure.setOpaque(false);
		panel.add(sure);
		
		JButton clear = new JButton("����");
		clear.setBounds(280, 340, 140, 30);
		clear.setOpaque(false);
		panel.add(clear);
		
		// ����
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
					JOptionPane.showMessageDialog(null, "��Щ���ݱ��");
					return;
				}
				String firstPasswd = new String(firstPasswdField.getPassword()).trim();
				String secondPasswd = new String(secondPasswdField.getPassword()).trim();
				// ����Ϊ��
				if(firstPasswd == null || firstPasswd.isEmpty() || secondPasswd == null || secondPasswd.isEmpty()){
					JOptionPane.showMessageDialog(null, "���������룡");
					return;
				}
				// ����û����Ƿ����
				if(JDBCUtils.getCount(name) > 0){
					JOptionPane.showMessageDialog(null, "���û����Ѵ��ڣ� ����������������");
					return;
				}
				
				if(!firstPasswd.equals(secondPasswd)){
					JOptionPane.showMessageDialog(null, "�������벻ͬ�� �������������룡");
					return;
				}
				
				Teacher teacher = new Teacher(name, id, grade);
				// ����teacher����
				JDBCUtils.addTeacher(teacher);
				
				// ���� ����password��
				JDBCUtils.addToLoginTable(name, firstPasswd, "teacher");
				
				// ��ӳɹ�,
				// �����ע��Ļ������»ص�loginҳ��
				JOptionPane.showMessageDialog(null, "ע��ɹ���");
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
		//regiter("���");
		teacherRegister();
	}

}
