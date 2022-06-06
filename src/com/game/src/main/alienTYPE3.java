package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityD;
		

public class alienTYPE3 extends GameObject implements EntityD{
	

	private Textures tex;
		Random r = new Random();
	 Game game;
	 Controller c; //removeBullet functions
	
	public alienTYPE3(double x, double y, double HP, int speed, Textures tex, Controller c, Game game) {
		super(x, y, HP, speed);
		this.tex = tex;
		this.c = c;
		this.game = game;
		this.HP = HP;
	}
	
	public void tick() {
		
		x += speed;
//		y += speed;
		

		if(x > (Game.WIDTH * Game.SCALE)) {
			speed = -3;			
			x = (Game.WIDTH * Game.SCALE) - 160;
		}
		
		if(x <= 0) {
			speed = 3;			
			x = 1;
		}
		
		//addEntity(new alienProjectile(alienTYPE3.getX(), alienTYPE3,getY(), 10, 10, tex, c, game));
		
		for(int i = 0; i < game.ea.size(); i++) {
			
			EntityA  Entity_A = game.ea.get(i);
			
			if(Physics.Collision(this, Entity_A)) {
				c.removeEntity(Entity_A);
				//hp azaltilacak hp 0 oldugunda remove edilecek
				HP--;
				if(HP <= 0) {
					c.removeEntity(this);
					game.setEnemy_killed(game.getEnemy_killed() + 1);
					game.setScore(game.getScore()+1000);
					game.setScoreTxt(String.format("%05d",game.getScore()));
					System.out.println(game.getScore());
					System.out.println(game.enemy_killed);
				}

			}
		}
		
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.alienTYPE3, (int)x, (int)y, null);
		
	}

	

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 160, 160);
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setX(double x) {
		this.x = x;
	}
}
