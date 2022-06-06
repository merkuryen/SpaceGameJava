package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityC;

public class Barrier extends GameObject implements EntityC {
	
	private Textures tex;
	private Game game;
	private Controller c;
	
	Random r = new Random();
//	private int speed = r.nextInt(3) + 1;

	
	public Barrier(double x, double y, double HP, int speed, Textures tex, Game game, Controller c) {
		super(x, y, HP, speed);
		this.tex = tex;
		this.c = c;
		this.game = game;
	}

	
	public void tick() {	
		
		x += speed;
		if(x >= (Game.WIDTH * Game.SCALE)- 64) {
			speed = -10;			
			x = (Game.WIDTH * Game.SCALE) - 64;
		}
		
		if(x <= 0) {
			speed = 10;			
			x = 1;
		}
		
		
		for(int i = 0; i < game.ea.size(); i++) {
			
			EntityA Entity_A = game.ea.get(i);
			
			if(Physics.Collision(this, Entity_A)) {
				c.removeEntity(Entity_A);
				
			}
		}

		
	}

	public void render(Graphics g) {	
		g.drawImage(tex.barrier, (int)x, (int)y, null);
		
	}

	
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 64, 32);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	
	
	

}
