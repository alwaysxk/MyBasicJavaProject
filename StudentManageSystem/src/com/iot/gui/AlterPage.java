package com.iot.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iot.domain.Score;
import com.iot.domain.Student;
import com.iot.jdbc.JDBCUtils;

public class AlterPage {
	
	/**
	 * 修改页面， 两种操作共用一个页面，
	 * 学生 拥有部分修改权限
	 * 老师拥有所有修改权限
	 * who 代表哪一类人 
	 * if(who.equals("student")) j2.setEnabled(false); 来控制权限
	 */

	public static void teacherAlterPage(Student s, String who){
		JFrame frame = new JFrame("student:" + s.getName());
		GuiUtils.setUIFont();
		frame.setSize(1000,700);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				super.windowClosing(e);
				StudentPage.showStudentPage(s.getName(), who);
				frame.dispose();
			}
		
		});
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
			
		
		// 姓名
		JLabel nameLable = new JLabel("姓  名: ");
		nameLable.setBounds(80, 20, 100, 50);
		JLabel j1 = new JLabel(s.getName());
		j1.setBounds(180, 20, 200, 50);
		c.add(nameLable);
		c.add(j1);
		
		// 学号
		JLabel idLable = new JLabel("学 号: ");
		idLable.setBounds(80, 70, 100, 50);
		JTextField j2 = new JTextField(s.getId());
		if(who.equals("student")) j2.setEnabled(false); /////////////// 
		j2.setBounds(180, 70, 200, 50);
		c.add(idLable);
		c.add(j2);
		
		// age 
		JLabel ageLable = new JLabel("年 龄: ");
		ageLable.setBounds(80, 120, 100, 50);
		JTextField j3 = new JTextField(s.getAge() + "");
		j3.setBounds(180, 120, 200, 50);
		c.add(ageLable);
		c.add(j3);
		
		// 年级
		JLabel gradeLable = new JLabel("年 级 : ");
		gradeLable.setBounds(80, 170, 100, 50);
		JTextField jlabel = new JTextField(s.getGrade());
		jlabel.setBounds(180, 170, 200, 50);
		c.add(jlabel);
		c.add(gradeLable);
		
		// sex
		JLabel sexLable = new JLabel("性 别 : ");
		sexLable.setBounds(80, 220, 100, 50);
		JTextField jsex = new JTextField(""+ (s.getSex() == 0 ? "男" : "女"));
		jsex.setBounds(180, 220, 200, 50);
		c.add(jsex);
		c.add(sexLable);
		
		JLabel phoneLable = new JLabel("手 机 : ");
		phoneLable.setBounds(80, 270, 100, 50);
		JTextField jphone = new JTextField("" + s.getPhone() == null ? "" : s.getPhone());
		jphone.setBounds(180, 270, 220, 50);
		c.add(jphone);
		c.add(phoneLable);
		
		JLabel emailLable = new JLabel("email : ");
		emailLable.setBounds(80, 320, 100, 50);
		JTextField jemail = new JTextField(s.getEmail() == null ? "" : s.getPhone());
		jemail.setBounds(180, 320, 300, 50);
		c.add(jemail);
		c.add(emailLable);
		
		
		// score
		Score score = JDBCUtils.getScore(s.getName());
		// 数据为空
		if(score == null) score = new Score(s.getName(), 0, 0, 0, 0);
		
		JLabel scoreLable = new JLabel("成 绩 : ");
		scoreLable.setBounds(80, 370, 100, 50);
		c.add(scoreLable);

		JLabel os = new JLabel("操作系统");
		os.setBounds(180, 370, 150, 50);
		c.add(os);
		JTextField a = new JTextField("" + score.getOs());
		if(who.equals("student")) a.setEnabled(false);
		a.setBounds(200, 420, 100, 50);
		a.setForeground(Color.RED);
		c.add(a);
		
		JLabel ds = new JLabel("数据结构");
		ds.setBounds(330, 370, 150, 50);
		c.add(ds);
		JTextField b = new JTextField("" + score.getDs());
		if(who.equals("student")) b.setEnabled(false);
		b.setBounds(350, 420, 100, 50);
		b.setForeground(Color.RED);
		c.add(b);
		
		
		JLabel cn = new JLabel("计算机网络");
		cn.setBounds(480, 370, 150, 50);
		c.add(cn);
		JTextField cc = new JTextField("" + score.getCn());
		if(who.equals("student")) cc.setEnabled(false);
		cc.setBounds(500, 420, 100, 50);
		cc.setForeground(Color.RED);
		c.add(cc);
		
		JLabel co = new JLabel("计算机组成原理");
		co.setBounds(650, 370, 200, 50);
		c.add(co);
		JTextField d = new JTextField("" + score.getCo());
		if(who.equals("student")) d.setEnabled(false);
		d.setBounds(670, 420, 100, 50);
		d.setForeground(Color.RED);
		c.add(d);
		
		JButton alter = new JButton("确认修改");
		alter.setBounds(80, 570, 150, 50);
		alter.setOpaque(false);
		c.add(alter);
		
		alter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = j2.getText();
				int age = 0;
				int sex = 0;
				int a1 = 0, a2 = 0, a3 = 0, a4 = 0; // 四科成绩
				try{
					age = Integer.parseInt(j3.getText());
					sex = Integer.parseInt(jsex.getText().equals("男") ? "0" : "1");
//	System.out.println(a.getText());
//System.out.println(sex);
					a1 = Integer.parseInt(a.getText());
					a2 = Integer.parseInt(b.getText());
					a3 = Integer.parseInt(cc.getText());
					a4 = Integer.parseInt(d.getText());
				}catch (NumberFormatException ee) {
					ee.printStackTrace();
				}
				String email = jemail.getText();
				String phone = jphone.getText();
				String grade = jlabel.getText();
				Student s1 = new Student(s.getName(), id, age, phone, email, sex, grade);
				// 数据库进行修改操作, 基本信息
				JDBCUtils.updateStudent(s1);
				// 成绩信息， 未做异常处理
//System.out.println(a1 + "---------");
				Score score1 = new Score(s.getName(), a1, a2, a3, a4);
				JDBCUtils.updateScore(score1);
				frame.dispose();
				StudentPage.showStudentPage(s.getName(), "teacher");
			}
		});

		
		frame.setLocation(600, 100);
		frame.setVisible(true);
	}
	
	
	public static void main(String[] args) {

	}

}
