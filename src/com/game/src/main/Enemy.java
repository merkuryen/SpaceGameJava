package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.Game.STATE;
import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Enemy extends GameObject implements EntityB {
	
	private Textures tex;
	Random r = new Random();
	private Game game;
	private Controller c; //removeBullet functions

	private int speed= r.nextInt(10) + 1;
//	private int angleX = 1, angleY = 1;
	
	public Enemy(double x, double y, double HP, int speed, Textures tex, Controller c, Game game) {
		super(x, y, HP, speed);
		this.tex = tex;
		this.c = c;
		this.game = game;
		this.HP = HP;
	}

	
//	public void AlienLocationRandomizer() {
//		
//		for(int i = 400; i < 640; i++) {
//			int alienRandomNumber = (int)Math.random();
//			System.out.println(alienRandomNumber);
//		}
//	}
	

	public void tick() {

		if(Game.State == STATE.GAME_1) {
			x += speed;
//			y += speed;
			

			if(x > (Game.WIDTH * Game.SCALE)) {
				speed = r.nextInt(10) + 1;			
				
				y = r.nextInt(405 - 60) + 60;
				x = 0;
			}
		}
		
		if(Game.State == STATE.GAME_2) {
		
			x += speed +1;
			y += speed +1;
			
			
			if(x >= (Game.WIDTH * Game.SCALE)- 32) {
				speed = -(r.nextInt(10)+1);			
				x = (Game.WIDTH * Game.SCALE) - 32;
			}
			if(x <= 0) {
				speed = (r.nextInt(10)+1);;			
				x = 1;
			}
			if(y >= (Game.HEIGHT * Game.SCALE) - 32) {
				speed = -(r.nextInt(10)+1);;
				y = (Game.HEIGHT * Game.SCALE) - 32;
			}
			
			if(y <= 60) {
				speed = (r.nextInt(10)+1);;
				y = 61;
			}
			
			
		}
		
		for(int i = 0; i < game.ea.size(); i++) {
			
			EntityA  Entity_A = game.ea.get(i);
			
			if(Physics.Collision(this, Entity_A)) {
				c.removeEntity(Entity_A);
				c.removeEntity(this);
				game.setEnemy_killed(game.getEnemy_killed() + 1);
				game.setScore(game.getScore()+100);
				game.setScoreTxt(String.format("%05d",game.getScore()));
				System.out.println(game.getScore());
				System.out.println("Enemy killed = " + Game.enemy_killed
								+ "Enemy count = " + game.getEnemy_count());
			}
		}
		
	
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.enemy, (int)x, (int)y, null);
		
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
	public void setY(double y) {
		this.y = y;
	}
	public void setX(double x) {
		this.x = x;
	}


//	public void spawn() {
//		double mx = x;
//		double my = y;
//		
//		int angleX = 1;
//		int angleY = 1;
//		
//	    if(mx + angleX < 0) angleX = speed;
//	    else if(mx + angleX > Game.WIDTH - 32) angleX = -speed;
//	    else if(my + angleY < 0) angleY = speed;
//	    else if(my + angleY > Game.WIDTH - 32) angleY = -speed;
//		
//	    x += angleX;
//	    y += angleY;
//	}

	
}
