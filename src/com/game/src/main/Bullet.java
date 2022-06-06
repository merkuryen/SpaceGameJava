package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Bullet extends GameObject implements EntityA{

	private Textures tex;
	private Game game;
	private Controller c;
	public static double dir = 0;
	public static double xVel;
	public static double yVel;
	
	public Bullet(double x, double y, double xVel, double yVel, double dir, double HP, int speed, Textures tex, Controller c, Game game) {
		super(x, y, HP, speed);
		this.xVel = xVel;
		this.yVel = yVel;
		this.dir = dir;
		this.tex = tex;
		this.c = c;
		this.game = game;
	}
	
	public void tick() {
		x += xVel;
		y += yVel;
//		y -= 10;
		
		
		//deletes when out of bounds
		if(y <= 50 || y >= 810) {
			for(int i = 0; i < game.ea.size(); i++) {
				EntityA Entity_A = game.ea.get(i);
					c.removeEntity(Entity_A);
				}
		}
		
		if(x >= 1440 || x <= 0) {
			for(int i = 0; i < game.ea.size(); i++) {
				EntityA Entity_A = game.ea.get(i);
					c.removeEntity(Entity_A);
				}
		}
		
		
		
	}
	
//		if(Physics.Collision(this, game.eb)) {
//			System.out.println("COLLISION DETECTED");
//		}
		
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.missile, (int)x, (int)y, null);
	}

	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}


}
