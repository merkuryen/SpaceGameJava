package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public Rectangle playButton = new Rectangle(Game.WIDTH / 3 + 480, 405, 240, 80);
	public Rectangle quitButton = new Rectangle(Game.WIDTH / 3 + 480, 515, 240, 80);
		
	
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;	
		
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.setColor(Color.white);
		g.drawString("PLAY", playButton.x + 20 , playButton.y + 40);
		g2d.draw(playButton);
		g.drawString("QUIT", quitButton.x + 20 , quitButton.y + 40);
		g2d.draw(quitButton);
	}

}
