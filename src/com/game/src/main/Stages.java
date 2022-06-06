package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Stages {
	
	public Rectangle continueButton = new Rectangle(Game.WIDTH / 3 + 480, 405, 240, 80);
	public Rectangle returnButton = new Rectangle(Game.WIDTH / 3 + 480, 515, 240, 80);
	
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;	
		
//		Font fnt0 = new Font("arial", Font.BOLD, 50);
//		g.setFont(fnt0);
//		g.setColor(Color.white);
//		g.drawString("SPACE INVADERS", Game.WIDTH / 2, 100);
		
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.setColor(Color.white);
		g.drawString("continue", continueButton.x + 20, continueButton.y + 40);
		g2d.draw(continueButton);
		g.drawString("back to menu", returnButton.x + 20, returnButton.y + 40);
		g2d.draw(returnButton);
	}
	
}
