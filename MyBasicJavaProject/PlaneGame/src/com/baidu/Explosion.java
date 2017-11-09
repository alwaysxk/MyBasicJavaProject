package com.baidu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Explosion extends GameObject{

//	public static BufferedImage image2;
//	public static BufferedImage image3;
//	public static BufferedImage image4;
//	public static BufferedImage image5;
//	static{
//		try {
//			
//			image2 = ImageIO.read(MyJFrame.class.getResource("2.png"));
//			image3 = ImageIO.read(MyJFrame.class.getResource("3.png"));
//			image4 = ImageIO.read(MyJFrame.class.getResource("4.png"));
//			image5 = ImageIO.read(MyJFrame.class.getResource("5.png"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void draw(Graphics g, int flag)
//	{
//		if(flag%4==0)
//		g.drawImage(image5, x, y, null);
//		if(flag%4==3)
//			g.drawImage(image4, x, y, null);
//		if(flag%4==2)
//			g.drawImage(image3, x, y, null);	
//		if(flag%4==1)
//			g.drawImage(image2, x, y, null);	
//	}
//	public void lifeDown()
//	{
//		if(flag>0)
//			flag--;
//	}
	private boolean isLive = true;
	MyJFrame m;
	int[] d  = {4, 10, 20, 36, 56, 24, 12, 6};
	int step = 0;
	public boolean getLive()
	{
		return isLive;
	}
	
	public void setLive(boolean l)
	{
		isLive = l;
	}
	public void draw(Graphics g)
	{
		if(!isLive)
		{
			m.ex.remove(this);
		}
		if(step == d.length)
		{
			isLive = false;
			step = 0;
		}
		
		Color c = g.getColor();
		g.setColor(Color.ORANGE);
		g.fillOval(x, y, d[step], d[step]);
		g.setColor(Color.BLUE);
		step++;
		
	}
	public Explosion()
	{
		x = 200;
		y = 600;
//		flag = 4;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}
	public void setM(MyJFrame m)
	{
		this.m = m;
	}
	public void setLocation(int i, int j) {
		// TODO Auto-generated method stub
		x = i;
		y = j;
	}

}
