package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.TimerTask;

import com.game.src.main.classes.EntityE;

public class alienProjectile extends GameObject implements EntityE {

	private Textures tex;
	private Game game;
	private Controller c;
	
	public alienProjectile(double x, double y, double HP, int speed, Textures tex, Controller c, Game game) {
		super(x, y, HP, speed);
		this.tex = tex;
		this.c = c;
		this.game = game;

	}
	

	public void tick() {
		y += 10;
		
		if(y >= 810) {
			for(int i = 0; i < game.ee.size(); i++) {
				EntityE Entity_E = game.ee.get(i);
					c.removeEntity(Entity_E);
				}
			}
	}
		
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.alienProjectile, (int)x, (int)y, null);
	}

	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
}
