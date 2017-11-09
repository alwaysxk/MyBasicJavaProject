package com.baidu;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class EmenyPlane extends GameObject{
	

	public static BufferedImage image3;
	public EmenyPlane()
	{
		x = 150;
		y = 0;
		width = 50;
		height = 40;
	}
	static{
		try {
			image3 = ImageIO.read(EmenyPlane.class.getResource("1.png"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void move()
	{
		y+=2;
	}

	public void draw(Graphics g)
	{
		g.drawImage(image3, x, y, null);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public boolean judgeLive()
	{
		if(y>700)
			return false;
		else
			return true;
	}

//	public void run()
//	{
//		while(true){
//			try {
//				Thread.sleep(10);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		    y+=1;
//		
//		}
//	}

}
