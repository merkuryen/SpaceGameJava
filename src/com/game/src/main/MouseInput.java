package com.game.src.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener{

	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int mouseB = -1;
	
	public static int getX() {
		return mouseX;
	}
	
	public static int getY() {
		return mouseY;
	}
	
	public static int getButton() {
		return mouseB;
	}
	
	

	public void mouseClicked(MouseEvent e) {


		
		if(Game.State == Game.State.MENU) {
		
			//PLAY BUTTON
		if(mouseX >= Game.WIDTH / 3 + 480 && mouseX <= Game.WIDTH / 3 + 720) {
				if(mouseY >= 405 && mouseY <= 485) {
					//Pressed play button
					Game.State = Game.STATE.GAME_1;
				}
			}
		
		//QUIT BUTTON
		if(mouseX >= Game.WIDTH / 3 + 480 && mouseX <= Game.WIDTH / 3 + 720) {
			if(mouseY >= 515 && mouseY <= 595) {
				System.exit(1);
			}
		}
		}	
		
		////////////////////////////////////////
		
		if(Game.State == Game.State.STAGE_1) {

			//CONTINUE BUTTON
			if(mouseX >= Game.WIDTH / 3 + 480 && mouseX <= Game.WIDTH / 3 + 720) {
				if(mouseY >= 405 && mouseY <= 485) {
					//Pressed play button
					Game.State = Game.STATE.GAME_2;
				}
			}
			//RETURN BUTTON
			if(mouseX >= Game.WIDTH / 3 + 480 && mouseX <= Game.WIDTH / 3 + 720) {
				if(mouseY >= 515 && mouseY <= 595) {
					Game.State = Game.State.MENU;
				}
			}
		}
		
		///////////////////////////////////////
		
		if(Game.State == Game.State.STAGE_2) {
				
			//CONTINUE BUTTON
			if(mouseX >= Game.WIDTH / 3 + 480 && mouseX <= Game.WIDTH / 3 + 720) {
				if(mouseY >= 405 && mouseY <= 485) {
					//Pressed play button
					Game.State = Game.STATE.GAME_3;
				}
			}
		
			//RETURN BUTTON
			if(mouseX >= Game.WIDTH / 3 + 480 && mouseX <= Game.WIDTH / 3 + 720) {
				if(mouseY >= 515 && mouseY <= 595) {
					Game.State = Game.State.MENU;
				}
			}
		}
		
		//////////////////////////////////////
				
		if(Game.State == Game.State.YOU_WIN) {
		

		//PLAY BUTTON
		if(mouseX >= Game.WIDTH / 3 + 480 && mouseX <= Game.WIDTH / 3 + 720) {
			if(mouseY >= 515 && mouseY <= 595) {
				//Pressed play button
				Game.State = Game.STATE.GAME_1;
			}
		}
			
			//RETURN BUTTON
			if(mouseX >= Game.WIDTH / 3 + 480 && mouseX <= Game.WIDTH / 3 + 720) {
				if(mouseY >= 625 && mouseY <= 705) {
					Game.State = Game.STATE.MENU;
				}
			}
		}
		
		
		////////////////////////////////////////////
		
		if(Game.State == Game.State.GAME_OVER) {

			//PLAY BUTTON
			if(mouseX >= Game.WIDTH / 3 + 480 && mouseX <= Game.WIDTH / 3 + 720) {
				if(mouseY >= 515 && mouseY <= 595) {
					//Pressed play button
					Game.State = Game.STATE.GAME_1;
				}
			}
				
				//RETURN BUTTON
				if(mouseX >= Game.WIDTH / 3 + 480 && mouseX <= Game.WIDTH / 3 + 720) {
					if(mouseY >= 625 && mouseY <= 705) {
						Game.State = Game.STATE.MENU;
					}
				}
		}
	}

	public void mousePressed(MouseEvent e) {
		mouseB = e.getButton();		
	}


	public void mouseReleased(MouseEvent e) {
		mouseB = -1;
		
	}


	public void mouseEntered(MouseEvent e) {

		
	}


	public void mouseExited(MouseEvent e) {

		
	}

	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
	}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
	}

}
