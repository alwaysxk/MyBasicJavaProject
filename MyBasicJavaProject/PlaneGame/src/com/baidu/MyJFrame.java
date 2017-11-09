package com.baidu;

import java.util.*;
import java.util.List;
import java.util.Timer;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import javax.swing.*;

// 游戏界面
public class MyJFrame extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener{

	private Image iBuffer;
	private Graphics gBuffer;
	int flag = 0;
	public static final int WIDTH = 400;  //窗口的宽
	public static final int HEIGHT = 654; //窗口的高
	// 定义战机
	HeroPlane heroPlane = null;

    // 子弹
    List<Bomb> bomb = new ArrayList<Bomb>();
    // 敌机数组

    List<EmenyPlane> emeny = new ArrayList<EmenyPlane>();
    List<Bomb> emenyBall = new ArrayList<Bomb>();

    List<Explosion> ex = new ArrayList<Explosion>();
	// 背景	
	public static BufferedImage background;
	// 加载背景
	static{
		try {
			background = ImageIO.read(MyJFrame.class.getResource("background.png"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 初始化
	public MyJFrame()
	{
		
		this.startgame();
		
	}
	// 启动游戏
	public void startgame(){
		heroPlane = new HeroPlane();

		for(int i = 0; i<7; i++)
		{
			this.produceEmeny();
		}
		if(emeny.size()!=0)
		{
			for(int i = 0; i<emeny.size(); i++)
			{
				EmenyPlane em = emeny.get(i);
				
				Bomb b = new Bomb(em.x+20, em.y);
				emenyBall.add(b);
				
			}
		}
	}
	// 画图
	public void paint(Graphics g)
	{
		if(iBuffer == null)
		{
			iBuffer = createImage(this.getSize().width, this.getSize().height);
			gBuffer = iBuffer.getGraphics();
		}
		
		gBuffer.drawImage(background, 0, 0, null);
		
		heroPlane.draw(gBuffer);
		// 画子弹
		for(int i = 0;i<bomb.size(); i++)
		{
			Bomb b = bomb.get(i);
			
			b.draw(gBuffer);
			b.move(1);
		}
		// 画敌人子弹
		for(int i = 0; i<emenyBall.size();i++)
		{
            Bomb b = emenyBall.get(i);
            b.move(-1);
			if(b.y>160){
			b.draw(gBuffer);
			}
			
		}
		// 画敌机
		for(int i = 0; i<emeny.size(); i++)
		{
			EmenyPlane em1 = emeny.get(i);
			em1.draw(gBuffer);
		    em1.move();
			
		}
		//ex = new Explosion();

		//ex.draw(gBuffer, i);
		for(int i = 0; i<ex.size(); i++)
		{
			Explosion x = ex.get(i);
			x.draw(gBuffer);
		}
		this.judge();
		this.emenyPlaneReproduce();
		g.drawImage(iBuffer, 0, 0, this);
	
		
	}
	
	// 产生敌机
	public void produceEmeny()
	{
		EmenyPlane em = new EmenyPlane();
		em.setLocation((int)(Math.random()*400), (int)(Math.random()*(-200)));
		emeny.add(em);
	}
	
	public void produceBall()
	{
		for(int i = 0; i<emeny.size(); i++)
		{
			EmenyPlane em1 = emeny.get(i);
			if(em1!=null && em1.y>10)
			{
				Bomb b = new Bomb(em1.x+20, em1.y);
				emenyBall.add(b);
			}
			if(em1!=null && em1.y>100)
			{
				Bomb b = new Bomb(em1.x+20, em1.y);
				emenyBall.add(b);
			}
			if(em1!=null && em1.y>300)
			{
				Bomb b = new Bomb(em1.x+20, em1.y);
				emenyBall.add(b);
			}
		}
	}
	//判断游戏对象是否与相撞
	public void judge()
	{
		for(int i = 0; i<bomb.size();i++)
		{
			Bomb b = bomb.get(i);
			for(int j = 0; j<emeny.size(); j++)
			{
				EmenyPlane em1 = emeny.get(j);
				if(b.getBounds().intersects(em1.getBounds()))
				{
					Explosion x = new Explosion();
					x.setLocation(em1.x, em1.y);
					x.setM(this);
					ex.add(x);
					emeny.remove(em1);
					this.produceEmeny();
				}
			}
		}
		
		// 判断敌机是否与战机相撞		
		for(int i = 0; i<emeny.size(); i++)
		{
			EmenyPlane em1 = emeny.get(i);
			if(em1.getBounds().intersects(heroPlane.getBounds()))
			{
				Explosion x = new Explosion();
				x.setLocation(em1.x, em1.y);
				x.setM(this);
				ex.add(x);
				emeny.remove(em1);
				this.produceEmeny();
			}
		}
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame jf = new JFrame("PLANEGAME");
		MyJFrame jp = new MyJFrame();
		jf.add(jp);
		jf.setSize(WIDTH+100, HEIGHT+200);
		jf.setAlwaysOnTop(true);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.addKeyListener(jp);
		jf.addMouseListener(jp);
		jf.addMouseMotionListener(jp);
		jf.setVisible(true);
		new Thread(jp).start();
		
	}
	// 子弹，飞机越界处理
	
	public void emenyPlaneReproduce()
	{
		// 飞机越界后删除该飞机，产生一个新的飞机加入容器中
		
		for(int i = 0; i<emeny.size();i++)
		{
			EmenyPlane em = emeny.get(i);
			if(!em.judgeLive()){
				emeny.remove(em);
			EmenyPlane em2 = new EmenyPlane();
			em2.setLocation((int)(Math.random()*400), (int)(Math.random()*(-200)));
			emeny.add(em2);
			
			Bomb b = new Bomb(em2.x+20, em2.y);
			Bomb b1 = new Bomb(em2.x+20, em2.y+40);
			emenyBall.add(b);
			emenyBall.add(b1);
			
			}
		}
		// 子弹越界后删除子弹
		for(int i = 0; i<emenyBall.size(); i++)
		{
			 Bomb b = emenyBall.get(i);
			 if(!b.judgeLive())
				 emenyBall.remove(b);
		}

	}
	// 键盘监听， 控制方向移动和发射子弹
//	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		 if(arg0.getKeyCode() == arg0.VK_UP)
    	 {
			 heroPlane.y-=10;
    	    heroPlane.makeDir();
    	    
    	 }
    	 else if(arg0.getKeyCode() == arg0.VK_DOWN)
    	 {
    		 heroPlane.y+=10;
    		 heroPlane.makeDir();
    	 }
    	 else if(arg0.getKeyCode() == arg0.VK_RIGHT)
        {
    		 heroPlane.x+=10;
    		 heroPlane.makeDir();
        }
    	 else if(arg0.getKeyCode() ==  arg0.VK_LEFT)
    	 {
    		  heroPlane.x-=10;
    		  heroPlane.makeDir();
    	 }
    	 else if(arg0.getKeyCode() == arg0.VK_A)
    	 {
    		 bomb.add(new Bomb(heroPlane.x+45, heroPlane.y));
    		
    		 
    	 }
    	 else if(arg0.getKeyCode() == arg0.VK_S)
    	 {
    		// e.setLocation(300, 300);
    		
    		 
    	 }
		 this.repaint();
	}

	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//this.produceBall();
			
			
			this.repaint();
		}
	}


	
	
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		heroPlane.setLocation(arg0.getX()-50, arg0.getY()-60);
		//this.repaint();
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	Bomb b=	new Bomb(heroPlane.x+45, heroPlane.y);
		bomb.add(b);
		//this.repaint();
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}