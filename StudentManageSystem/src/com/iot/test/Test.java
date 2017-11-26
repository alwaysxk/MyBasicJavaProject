package com.iot.test;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
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
public class Test {
	
	
	public static void tt(){
		JFrame frame = new JFrame("StudentsManageSystem");
		frame.setSize(600,400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false); // 窗体大小保持不变
		
		ImageIcon bg = new ImageIcon("image/b.png");
		JLabel imageLabel = new JLabel(bg);
		imageLabel.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());
		//imagePanel = (JPanel)frame.getContentPane();
		frame.add(imageLabel);
		JPanel j = new JPanel();
		//j.add(imageLabel);
		j.setOpaque(false);
		JLabel j2 = new JLabel("sssss");
		j2.setBounds(200, 200, 50, 50);
		JButton b1 = new JButton("hello");
		b1.setBounds(100, 100, 100, 100);
		
		//b1.setEnabled(false);
		b1.setBackground(Color.BLACK);
		j2.setBackground(Color.BLACK);
		j.add(b1);
		j.add(j2);
		
		frame.add(j);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		tt();
	}

}
