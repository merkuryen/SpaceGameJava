package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityC;

public class Meteor extends GameObject implements EntityC {
	
	private Textures tex;
	private Game game;
	private Controller c;
	
	Random r = new Random();

	public Meteor(double x, double y, double HP, int speed, Textures tex, Game game, Controller c) {
		super(x, y, HP, speed);
		this.tex = tex;
		this.c = c;
		this.game = game;
	}
	int speed = 1;
	public void tick() {
//		x += speed;
//		y += speed;
		
		//x -= speed;
		y += speed;
		

		if(x > (Game.WIDTH * Game.SCALE)) {
					
			y = 60;
			x = r.nextInt(640 - 400) + 400;
		}
		
		for(int i = 0; i < game.ec.size(); i++) {
			EntityC Entity_C = game.ec.get(i);

//			if(x <= 0) {
//				c.removeEntity(Entity_C);
//				game.setMeteorQuantity(game.getMeteorQuantity() - 1);
//				game.setDestroyedMeteor(game.getDestroyedMeteor() + 1);
//			}
//			
			if(y >= 480) {
			c.removeEntity(Entity_C);
			game.setMeteorQuantity(game.getMeteorQuantity() - 1);
			game.setDestroyedMeteor(game.getDestroyedMeteor() + 1);
			}
		
		}
		
		//REMOVE BULLET WHEN COLLIDE
		for(int i = 0; i < game.ea.size(); i++) {
			
			EntityA Entity_A = game.ea.get(i);
			
			if(Physics.Collision(this, Entity_A)) {
				c.removeEntity(Entity_A);
				c.removeEntity(this);

				
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.meteor, (int)x, (int)y, null);
	}


	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}


	public double getX() {
		return x;
	}


	public double getY() {
		return y;
	}

}
