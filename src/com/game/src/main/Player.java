package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityD;
import com.game.src.main.classes.EntityE;



public class Player extends GameObject implements EntityA{

	private double velX = 0;
	private double velY = 0;
	
	private Textures tex;
	
	Game game;
	Controller controller;
	
	public Player(double x, double y, double HP, int speed, Textures tex, Game game, Controller controller) {
		super(x, y, HP, speed);
		this.tex = tex;
		this.game = game;
		this.controller = controller;
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(x <= 0)
			x = 0;
		if(x >= 1440 - 64)
			x = 1440 - 64;
		if(y <= 50)
			y = 50;
		if(y >= 810 - 64)
			y = 810 - 64;
		
//		if(Game.State == Game.STATE.GAME_1) {
//			if(y <= 50 && y >= 465) Game.HEALTH -= 10;
//		}
		
		if(HP <= 0) Game.alive = false;
		
		for(int i = 0; i < game.eb.size(); i++) {
			EntityB Entity_B = game.eb.get(i);
			
			if(Physics.Collision(this, Entity_B)) {
				controller.removeEntity(Entity_B);
				HP = HP - 10;
				game.setEnemy_killed(game.getEnemy_killed() + 1);
			}
		}
		
		for(int i = 0; i < game.ec.size(); i++) {
			EntityC Entity_C = game.ec.get(i);
			
			if(Physics.Collision(this, Entity_C)) {
				controller.removeEntity(Entity_C);
				HP = HP - 10;
			}
		}
		
		for(int i = 0; i < game.ed.size(); i++) {
			EntityD Entity_D = game.ed.get(i);
			
			if(Physics.Collision(this, Entity_D)) {
				controller.removeEntity(Entity_D);
				HP = HP - 10;
			}
		}
		for(int i = 0; i < game.ee.size(); i++) {
			EntityE Entity_E = game.ee.get(i);
			
			if(Physics.Collision(this, Entity_E)) {
				controller.removeEntity(Entity_E);
				HP = HP - 50;
			}
		}

		updateShooting();

	}


	private void updateShooting() {
		if(MouseInput.getButton() == 1) {
			double speed = 10;
			double xVel = 0;
			double yVel = 0;
			
			double dx = (MouseInput.getX() - x);
			double dy = (MouseInput.getY() - y);
			double dir = Math.atan2(dy, dx);
//			dir *= 180 / Math.PI;
			
			System.out.println("Angle: " + dir);
			xVel = speed * Math.cos(dir);
			yVel = speed * Math.sin(dir);
			
//			xVel = speed * dir;
//			yVel = speed * dir;
			 
			
//			if (dir < 0)
//		        dir = Math.abs(dir);
//		    else
//		        dir = 2 * Math.PI - dir;
			controller.shooting(x, y, xVel, yVel, dir);
		}
	}
	
	
	public void render(Graphics g) {
		g.drawImage(tex.player, (int)x, (int)y, null);
	}
	
//	protected void shoot(double x, double y, double dir) { 
//

//		
//	}
	
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 64, 64);
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setVelX(double velX) {
		this.velX = velX;
	}
	public void setVelY(double velY) {
		this.velY = velY;
	}
	
}
