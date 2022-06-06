package com.game.src.main;

import java.awt.image.BufferedImage;

public class Textures {
	
	public BufferedImage player, missile, enemy, barrier, meteor, alienProjectile, alienTYPE3;
	
	
	private SpriteSheet ss;
	
	public Textures(Game game) {
		ss = new SpriteSheet(game.getSpriteImage());//sheet??
		getTextures();
	}
	
	private void getTextures() {
		player = ss.grabImage(1, 4, 64, 64);
		missile = ss.grabImage(3, 6, 32, 32);
		enemy = ss.grabImage(5, 6, 32, 32);
		barrier = ss.grabImage(2, 7, 64, 32);
		meteor = ss.grabImage(5, 7, 32, 32);
		alienTYPE3 = ss.grabImage(1, 1, 160, 160);
		alienProjectile = ss.grabImage(4, 6, 32, 32);
		
	}

}
