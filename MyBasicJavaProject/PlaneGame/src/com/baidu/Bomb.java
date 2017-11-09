package com.baidu;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Bomb extends GameObject{

	boolean isLive;
	public static BufferedImage image2;
	static{
		try {
			image2 = ImageIO.read(MyJFrame.class.getResource("bullet.png"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void draw(Graphics g)
	{
		g.drawImage(image2, x, y, null);
	}
	public Bomb(int x, int y)
	{
		this.x = x;
		this.y = y;
		width = 10;
		height = 15;
		isLive = true;
	}
	public boolean judgeLive()
	{
		if(y>700)
			return false;
		else
			return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void move(int x)
	{
		if(x>0)
		y-=3;
		else
			y+=3;
	}

}
