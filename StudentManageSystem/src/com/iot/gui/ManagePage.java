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
		JFrame frame = new JFrame("ѧ����Ϣ����ϵͳ");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(1600, 800);
		frame.setLocation(200, 100);
		
		// ������ͼ
		// �����ݿ��л������ѧ����Ϣ
		List<Student> list = JDBCUtils.getAllStudent();
				
		JPanel j = (JPanel)frame.getContentPane();
		j.setOpaque(false);
		JPanel c = new JPanel();
		c.setLayout(new BorderLayout()); // ���岼�� 
		c.setOpaque(false);

		// �������� ��һ�� Button ���ı�����ɣ� ֻ�ܸ������ֽ�������
		JPanel searchPanel = new JPanel(new FlowLayout(0));
		searchPanel.setOpaque(false);
		JTextField searchText = new JTextField("                                ");
		searchText.setBounds(5, 5, 15, 5);
		searchPanel.add(searchText);
		JButton serachButton = new JButton("����");
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
					JOptionPane.showMessageDialog(null, "����������������");
					return;
				}
				if(JDBCUtils.getCount(searchName)==0){
					JOptionPane.showMessageDialog(null, "���޴��ˣ� ���������룡");
					return;
				}
				StudentPage.showStudentPage(searchName, "teacher");
			}
		});
		
		// ���
		JTable table = new JTable();
		table.setOpaque(false);
		String[] tableHead = {"ѧ��", "����", "�꼶","����", "�Ա�", "phone", "email"};
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
			arr[4] = e.getSex() == 0 ? "��" : "Ů";
			arr[5] = e.getPhone();
			arr[6] = e.getEmail() ;
			model.addRow(arr);
		}
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0,25,350,200);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setBackground(Color.WHITE);
		c.add(scrollPane, "Center");
		
		// �ײ���ť
		JPanel bottomPanel = new JPanel(new FlowLayout(2));
		JButton add = new JButton("ADD");
		JButton delete = new JButton("delete");
		bottomPanel.add(add);
		bottomPanel.add(delete);
		
		// �¼�����
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterPage.regiter("teacher");
				frame.dispose(); // �Ƚ���ǰҳ������
			}
		});
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowIndex = table.getSelectedRow();
				Object o = table.getValueAt(rowIndex, 1); // �����ѡ��Ԫ���ѧ������
				String name = (String)o;
				if(rowIndex != -1){
					model.removeRow(rowIndex); // �ӽ�����ɾ��
					JDBCUtils.delete(name); // �����ݿ���ɾ��
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
