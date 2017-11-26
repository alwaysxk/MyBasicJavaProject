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
		
		//����������һ��JFrame��ܣ� 
		JFrame frame = new JFrame("StudentsManageSystem");
		frame.setSize(600,400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false); // �����С���ֲ���
		GuiUtils.setUIFont(); // ��������
		
		// ������ͼ
		ImageIcon bg = new ImageIcon("image/b.png");
		JLabel imageLabel = new JLabel(bg);
		imageLabel.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());
		// �ѱ���Label������ײ㣬���Ұ��ϲ�ؼ���������Ϊ͸�� setOpaque(false)
		frame.getLayeredPane().add(imageLabel,new Integer(Integer.MIN_VALUE)); 
		
		JPanel j = (JPanel) frame.getContentPane();
		j.setOpaque(false);
		JPanel c = new JPanel();
		c.setOpaque(false);
		c.setLayout(new BorderLayout()); // ���岼�� 
		
		JTextField username = new JTextField();
		JPasswordField password = new JPasswordField();
		
		// �ײ���������ť
		JButton ok = new JButton("ȷ��");
		JButton cancel = new JButton("ȡ��");
		JButton register = new JButton("ע��");
		
		//��������
		JPanel titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(new JLabel("ϵͳ��¼"));
		c.add(titlePanel,"North");
		
		// �û���������
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(null);
		fieldPanel.setOpaque(false);
		JLabel a1 = new JLabel("�û���:");
		a1.setBounds(140,40,120,40);
		JLabel a2 = new JLabel("��   ��:");
		a2.setBounds(140,100,120,40);
		fieldPanel.add(a1);
		fieldPanel.add(a2);
		username.setBounds(250,45,140,30);
		password.setBounds(250,105,140,30);
		fieldPanel.add(username);
		fieldPanel.add(password);
		c.add(fieldPanel,"Center");	
		
		// �ײ���ѡ�� �û����ѡ�� 
		JRadioButton b1 = new JRadioButton("student");
		b1.setOpaque(false);
		JRadioButton b2 = new JRadioButton("teacher");
		b1.setSelected(true); // Ĭ������� ѡ��ѧ��
		b2.setOpaque(false);
		ButtonGroup buttonGroup = new ButtonGroup(); // ��JRadioButton�����������ʹѡ�񻥳�
		buttonGroup.add(b1);
		buttonGroup.add(b2);
		
		JPanel choice = new JPanel(); // ��ѡ��Panel
		choice.setOpaque(false);
		choice.setLayout(new FlowLayout());
		choice.add(b1);
		choice.add(b2);
		// �ײ���������ť����һ��Panel
		JPanel buttons = new JPanel(); 
		buttons.setOpaque(false);
		buttons.setLayout(new FlowLayout());
		
		//buttons �� choice����һ��Panel������������
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(choice, "North");
		
		// Button�¼���������
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_okButton_actionPerformed(e);
			}
			private void do_okButton_actionPerformed(ActionEvent e){
				String UserName = new String(username.getText());
				String PassWord = new String(password.getPassword());
				
				if(UserName.isEmpty() || PassWord.isEmpty()){
					JOptionPane.showMessageDialog(null, "�˺Ż����벻��Ϊ�գ�");
					return;
				}

				// ѧ����¼
				if(b1.isSelected()){
					
					int numOfStu = JDBCUtils.getCount(UserName);
					if(numOfStu == 0){
						JOptionPane.showMessageDialog(null, "���޴�����");
						return;
					}	
					String passwordFromSql = JDBCUtils.getPassword(UserName);
					if(passwordFromSql == null || !passwordFromSql.equals(PassWord)){
						JOptionPane.showMessageDialog(null, "�˺Ż��������");
					}else if(passwordFromSql.equals(PassWord)){ // ��������� ��¼�ɹ�
						frame.dispose();
						StudentPage.showStudentPage(UserName, "student");
					}else{
						
					}
					
				}
				// ���ҵ�¼
				if(b2.isSelected()){
					int numOfTea = JDBCUtils.getCountFromTeacherTable(UserName);
					if(numOfTea == 0){
						JOptionPane.showMessageDialog(null, "���޴�ʦ��");
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
				// ѧ��ע��
				if(b1.isSelected()){
					frame.dispose(); // �ͷ���Դ
					RegisterPage.regiter("student");
				}
				// ��ʦע��
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
	
	// ��Ԫ����
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


