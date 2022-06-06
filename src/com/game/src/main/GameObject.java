package com.game.src.main;

import java.awt.Rectangle;

public class GameObject {
	
	public double x;
	public double y;
	public double HP;
	public int speed;
	
	public GameObject(double x, double y, double hp, int speed) {
		this.x = x;
		this.y = y;
		this.HP = hp;
		this.speed = speed;
	}
	
	public Rectangle getBounds(int width, int height) {
		return new Rectangle((int)x, (int)y, width, height);
	}
	
}
