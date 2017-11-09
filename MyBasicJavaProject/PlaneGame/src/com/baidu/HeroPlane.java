package com.baidu;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class HeroPlane extends GameObject{


	public static BufferedImage image1;
	static{
		try {
			image1 = ImageIO.read(MyJFrame.class.getResource("hero1.png"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void draw(Graphics g)
	{
		g.drawImage(image1, x, y, null);

	}

	public HeroPlane()
	{
		x = 200;
		y = 600;
		width = 97;
		height = 124;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	// 控制战机在区域内
	public void makeDir()
	{
		if(x<0)
			x = 0;
		if(y>700)
			y = 700;
		if(x>400)
			x=400;
		if(y<0)
			y=0;
	}


}
