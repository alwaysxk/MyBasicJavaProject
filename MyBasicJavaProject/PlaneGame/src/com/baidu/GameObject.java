package com.baidu;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class GameObject {
	// ×ø±ê
	int x, y;
	
	int direction; //·½Ïò
	
	int width;
	
	int height;
	
	
	public GameObject()
	{
		x = 0;
		y = 0;
		width = 0;
		height = 0;
		direction = 0;
	}
	public Rectangle getBounds()
	{
		return new Rectangle(x, y, width,height);
	}
	
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
