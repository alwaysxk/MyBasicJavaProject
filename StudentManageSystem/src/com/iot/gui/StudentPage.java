package com.iot.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.iot.domain.Score;
import com.iot.domain.Student;
import com.iot.jdbc.JDBCUtils;

public class StudentPage {
	
	/**
	 * 
	 * @param studentName
	 * @param who = student 表示学生访问学生页面
	 * 		  who = teacher 表示老师访问学生页面， 
	 * 		     主要是控制修改权限
	 */
	public static void showStudentPage(String studentName, String who){
		
		JFrame frame = new JFrame("student:" + studentName);
		GuiUtils.setUIFont();
		frame.setSize(1000,700);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false); // 窗体大小保持不变
		
		// 贴背景图
		ImageIcon bg = new ImageIcon("image/g.jpg");
		JLabel imageLabel = new JLabel(bg);
		imageLabel.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());
		frame.getLayeredPane().add(imageLabel,new Integer(Integer.MIN_VALUE)); 
		
		JPanel j = (JPanel) frame.getContentPane();
		j.setOpaque(false);
		Container c = frame.getContentPane();
		c.setLayout(null); // 整体布局
			
		Student student = JDBCUtils.getStudent(studentName);
		
		// 姓名
		JLabel nameLable = new JLabel("姓  名: ");
		nameLable.setBounds(80, 20, 100, 50);
		JLabel j1 = new JLabel(studentName);
		j1.setBounds(180, 20, 200, 50);
		c.add(nameLable);
		c.add(j1);
		
		// 学号
		JLabel idLable = new JLabel("学 号: ");
		idLable.setBounds(80, 70, 100, 50);		
		JLabel j2 = new JLabel(student.getId());
		j2.setBounds(180, 70, 200, 50);
		c.add(idLable);
		c.add(j2);
		
		// age 
		JLabel ageLable = new JLabel("年 龄: ");
		ageLable.setBounds(80, 120, 100, 50);
		JLabel j3 = new JLabel(""+student.getAge());
		j3.setBounds(180, 120, 200, 50);
		c.add(ageLable);
		c.add(j3);
		
		// 年级
		JLabel gradeLable = new JLabel("年 级 : ");
		gradeLable.setBounds(80, 170, 100, 50);
		JLabel jlabel = new JLabel(""+student.getGrade());
		jlabel.setBounds(180, 170, 200, 50);
		c.add(jlabel);
		c.add(gradeLable);
		
		// sex
		JLabel sexLable = new JLabel("性 别 : ");
		sexLable.setBounds(80, 220, 100, 50);
		JLabel jsex = new JLabel(""+ (student.getSex() == 0 ? "男" : "女"));
		jsex.setBounds(180, 220, 200, 50);
		c.add(jsex);
		c.add(sexLable);
		
		JLabel phoneLable = new JLabel("手 机 : ");
		phoneLable.setBounds(80, 270, 100, 50);
		JLabel jphone = new JLabel(student.getPhone());
		jphone.setBounds(180, 270, 200, 50);
		c.add(jphone);
		c.add(phoneLable);
		
		JLabel emailLable = new JLabel("email : ");
		emailLable.setBounds(80, 320, 100, 50);
		JLabel jemail = new JLabel(student.getEmail());
		jemail.setBounds(180, 320, 300, 50);
		c.add(jemail);
		c.add(emailLable);
		
		// score
		// 数据为空
		Score score = JDBCUtils.getScore(studentName);
		if(score == null) score = new Score(studentName, 0, 0, 0, 0);
		
		JLabel scoreLable = new JLabel("成 绩 : ");
		scoreLable.setBounds(80, 370, 100, 50);
		c.add(scoreLable);

		JLabel os = new JLabel("操作系统");
		os.setBounds(180, 370, 150, 50);
		c.add(os);
		JLabel a = new JLabel("" + score.getOs());
		a.setBounds(200, 420, 100, 50);
		a.setForeground(Color.RED);
		c.add(a);
		
		JLabel ds = new JLabel("数据结构");
		ds.setBounds(330, 370, 150, 50);
		c.add(ds);
		JLabel b = new JLabel("" + score.getDs());
		b.setBounds(350, 420, 100, 50);
		b.setForeground(Color.RED);
		c.add(b);
		
		JLabel cn = new JLabel("计算机网络");
		cn.setBounds(480, 370, 150, 50);
		c.add(cn);
		JLabel cc = new JLabel("" + score.getCn());
		cc.setBounds(500, 420, 100, 50);
		cc.setForeground(Color.RED);
		c.add(cc);
		
		JLabel co = new JLabel("计算机组成原理");
		co.setBounds(650, 370, 200, 50);
		c.add(co);
		JLabel d = new JLabel("" + score.getCo());
		d.setBounds(670, 420, 100, 50);
		d.setForeground(Color.RED);
		c.add(d);
		
		JButton alter = new JButton("修改信息");
		alter.setBounds(80, 570, 150, 50);
		alter.setOpaque(false);
		c.add(alter);
		
		alter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				AlterPage.teacherAlterPage(student, who);
			}
		});
		frame.setLocation(600, 100);
		frame.setVisible(true);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//showStudentPage("jarry", "student");
	}

}
