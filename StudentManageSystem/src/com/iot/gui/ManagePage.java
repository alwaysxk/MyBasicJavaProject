package com.iot.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.iot.domain.Student;
import com.iot.jdbc.JDBCUtils;

public class ManagePage {
	
	
	public static void showManagePage(){
		JFrame frame = new JFrame("学生信息管理系统");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(1600, 800);
		frame.setLocation(200, 100);
		
		// 贴背景图
		// 从数据库中获得所有学生信息
		List<Student> list = JDBCUtils.getAllStudent();
				
		JPanel j = (JPanel)frame.getContentPane();
		j.setOpaque(false);
		JPanel c = new JPanel();
		c.setLayout(new BorderLayout()); // 整体布局 
		c.setOpaque(false);

		// 搜索功能 由一个 Button 和文本框组成， 只能根据名字进行搜索
		JPanel searchPanel = new JPanel(new FlowLayout(0));
		searchPanel.setOpaque(false);
		JTextField searchText = new JTextField("                                ");
		searchText.setBounds(5, 5, 15, 5);
		searchPanel.add(searchText);
		JButton serachButton = new JButton("搜索");
		serachButton.setBounds(30, 5, 10, 10);
		searchPanel.add(serachButton);
		c.add(searchPanel, "North");
		
		
		serachButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				doSomeThing(e);
			}
			public void doSomeThing(ActionEvent e){
				String searchName = new String(searchText.getText());
				searchName = searchName.trim();
				if(searchName.isEmpty() || searchName==null){
					JOptionPane.showMessageDialog(null, "请重新输入姓名！");
					return;
				}
				if(JDBCUtils.getCount(searchName)==0){
					JOptionPane.showMessageDialog(null, "查无此人， 请重新输入！");
					return;
				}
				StudentPage.showStudentPage(searchName, "teacher");
			}
		});
		
		// 表格
		JTable table = new JTable();
		table.setOpaque(false);
		String[] tableHead = {"学号", "姓名", "年级","年龄", "性别", "phone", "email"};
		DefaultTableModel model = new DefaultTableModel(null, tableHead);
		table.setModel(model);
		table.setFont(new Font("Serif", Font.PLAIN, 28));
		table.setRowHeight(28);
		for(Student e : list){
			String[] arr = new String[7];
			arr[0] = e.getId();
			arr[1] = e.getName();
			arr[2] = e.getGrade();
			arr[3] = e.getAge() + "";
			arr[4] = e.getSex() == 0 ? "男" : "女";
			arr[5] = e.getPhone();
			arr[6] = e.getEmail() ;
			model.addRow(arr);
		}
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0,25,350,200);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setBackground(Color.WHITE);
		c.add(scrollPane, "Center");
		
		// 底部按钮
		JPanel bottomPanel = new JPanel(new FlowLayout(2));
		JButton add = new JButton("ADD");
		JButton delete = new JButton("delete");
		bottomPanel.add(add);
		bottomPanel.add(delete);
		
		// 事件监听
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterPage.regiter("teacher");
				frame.dispose(); // 先将当前页面销毁
			}
		});
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowIndex = table.getSelectedRow();
				Object o = table.getValueAt(rowIndex, 1); // 获得所选单元格的学生姓名
				String name = (String)o;
				if(rowIndex != -1){
					model.removeRow(rowIndex); // 从界面中删除
					JDBCUtils.delete(name); // 从数据库中删除
				}
			}
		});
		c.add(bottomPanel, "South");

		frame.add(c);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		showManagePage();
	}

}
