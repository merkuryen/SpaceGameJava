package com.game.src.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityD;
import com.game.src.main.classes.EntityE;

public class Game extends Canvas implements Runnable {

	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 480;
	public static final int HEIGHT = WIDTH / 16 * 9;
	public static final int SCALE = 3;
	public final String TITLE = "2D SPACE GAME";
	
	private boolean running = false;
	public static boolean stage_done1 = false;
	public static boolean stage_done2 = false;
	public static boolean stage_done3 = false;
	public static boolean alive = true;
	
	public static int final_score = 0;
	
	private Thread thread;

	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage menuBackground = null;
	private BufferedImage background_stage1 = null;
	private BufferedImage background_stage2 = null;
	private BufferedImage background_stage3 = null;
	private BufferedImage background = null;
	private BufferedImage youWin_background = null;
	private BufferedImage gameOver_background = null;
	
	
	private int enemy_count = 5;
	private int enemy_count2 = 5;
	private int enemy_count3 = 1;
	public static int enemy_killed = 0;
	private int score = 0;
	private Random r = new Random();
	
	
	private int meteorQuantity = 10;
	private int destroyedMeteor = 0;
	
	private String scoreTxt = String.format("%05d",getScore());
	
	
	private Font fnt0;
	
	private Player p;
	private Controller c;

	
	private Barrier barrier1;
	private Barrier barrier2;
	private Barrier barrier3;
	private alienTYPE3 alienTYPE3;
	
	private Textures tex;
	private Menu menu;
	private Stages stages;
	private GameOver gameOver;
	private youWin youWin;

	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	public LinkedList<EntityC> ec;
	public LinkedList<EntityD> ed;
	public LinkedList<EntityE> ee;


	
	Random randomizer = new Random();
	
	public static enum STATE{
		MENU,
		GAME_1,
		STAGE_1,
		GAME_2,
		STAGE_2,
		GAME_3,
		YOU_WIN,
		GAME_OVER
	};
	
	public static STATE State = STATE.MENU;
	
	public void init() {
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("/SpriteSheet1.png");
			background_stage1 = loader.loadImage("/Backgroundtemp8.png");
			background_stage2 = loader.loadImage("/background_stage28.png");
			background_stage3 = loader.loadImage("/background_stage38.png");
			background = loader.loadImage("/background_stage.png");
			gameOver_background = loader.loadImage("/gameOver_background_stage38.png");
			youWin_background = loader.loadImage("/youWin_background_stage38.png");
			menuBackground = loader.loadImage("/MENUbackground8.png");
		}catch(IOException e) {
			e.printStackTrace();
		}
	
		tex = new Textures(this);
		fnt0 = new Font("arial", Font.BOLD, 30);
		
		c = new Controller(tex, this);	
		p = new Player(720, 700, 200, 5, tex, this, c);
		barrier1 = new Barrier(50, 465, 10000, 10, tex, this, c);
		barrier2 = new Barrier(710, 525, 10000, 10, tex, this, c);
		barrier3 =new Barrier(1380, 585, 10000, 10, tex, this, c);
	
		menu = new Menu();
		stages = new Stages();
		gameOver = new GameOver();
		youWin = new youWin();
		
		ea = c.getEntityA();
		eb = c.getEntityB();
		ec = c.getEntityC();
		ed = c.getEntityD();
		ee = c.getEntityE();
		
		MouseInput mouse = new MouseInput();
		
		this.addKeyListener(new KeyInput(this));
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
		c.createEnemy(enemy_count);
		
	}
	
	private synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
		
	}
	
	
	public void run(){
		init();
		
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running) {
			//game loop in here
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++; 
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + "Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	
	private void tick() {
		
	//	while(alive) {
		if(State == STATE.GAME_1) {
			p.tick();
			c.tick();
			barrier1.tick();
			barrier2.tick();
			barrier3.tick();
			
			if(enemy_killed >= enemy_count) {
				if(enemy_count < 20) {
				
					if(enemy_count <= 20) {
						enemy_count += 5;
					}
					enemy_killed = 0;
					c.createEnemy(enemy_count);	
				}
				else stage_done1 = true;
			}
			if(stage_done1) Game.State = Game.State.STAGE_1;	
				
		}
		
		else if(State == STATE.MENU) {
			ingameUpdater();
			
			c = new Controller(tex, this);
			p = new Player(720, 700, 200, 5, tex, this, c);

			ea = c.getEntityA();
			eb = c.getEntityB();
			ec = c.getEntityC();
			ed = c.getEntityD();
			ee = c.getEntityE();
			
			c.createEnemy(enemy_count);
			
			p.tick();
			c.tick();
			
		}
		
		else if(State == STATE.STAGE_1) {
			Game.stage_done1 = false;
			enemy_count = 5;
			
			c = new Controller(tex, this);
			p = new Player(720, 700, 200, 5, tex, this, c);

			ea = c.getEntityA();
			eb = c.getEntityB();
			ec = c.getEntityC();
			ed = c.getEntityD();
			ee = c.getEntityE();
			
			c.createEnemylvl2(enemy_count2);
			
			p.tick();
			c.tick();
				
		}
		////////////////////////
		else if(State == STATE.GAME_2) {
			p.tick();
			c.tick();
			
			if(enemy_killed >= enemy_count2) {
				if(enemy_count2 < 20) {
				
					if(enemy_count2 <= 20) {
						enemy_count2 += 5;
					}
					enemy_killed = 0;
					c.createEnemylvl2(enemy_count2);	
				}
				else stage_done2 = true;
			}
			if(stage_done2) Game.State = Game.State.STAGE_2;
			
		}
		
		else if(State == STATE.STAGE_2) {
			Game.stage_done2 = false;
			enemy_count2 = 5;
			enemy_killed = 0;
			
			c = new Controller(tex, this);
			p = new Player(720, 700, 200, 5, tex, this, c);
			alienTYPE3 = new alienTYPE3(710, 100, 500, 10, tex, c, this);

			
			ea = c.getEntityA();
			eb = c.getEntityB();
			ec = c.getEntityC();
			ed = c.getEntityD();
			ee = c.getEntityE();
			
			c.createalienTYPE3(alienTYPE3);
			p.tick();
			c.tick();
			
		}
	
		////////////////////////////////	
		else if(State == STATE.GAME_3) {
			p.tick();
			c.tick();
 
			int rNumber = 0;
			rNumber = r.nextInt(200);
			
			if(rNumber <= 10) {
				c.addEntity(new alienProjectile(alienTYPE3.getX()+ 80,alienTYPE3.getY()+80, 20, 20,tex, c, this));
			}

			if(enemy_killed > 0) {
				
				if(enemy_killed > 0)
				stage_done3 = true;
				}
			
			if(stage_done3) Game.State = Game.State.YOU_WIN;
		}
		
		////////////////////////////
		else if(State == STATE.YOU_WIN) {

			ingameUpdater();
			setScoreTxt(null);
			
			c = new Controller(tex, this);
			p = new Player(720, 700, 200, 5, tex, this, c);
		
			p.HP = 200;
			
			ea = c.getEntityA();
			eb = c.getEntityB();
			ec = c.getEntityC();
			ed = c.getEntityD();
			ee = c.getEntityE();
			c.createEnemy(enemy_count);	
			p.tick();
			c.tick();
		}
		/////////////////////////////////////////
		else if(State == STATE.GAME_OVER) {
			
			Game.stage_done3 = false;
			enemy_count3 = 1;
			enemy_killed = 0;
			setScoreTxt(null);
			
			c = new Controller(tex, this);
			p = new Player(720, 700, 200, 5, tex, this, c);
			
			p.HP = 200;
			
			ea = c.getEntityA();
			eb = c.getEntityB();
			ec = c.getEntityC();
			ed = c.getEntityD();
			ee = c.getEntityE();
			c.createEnemy(enemy_count);	
			p.tick();
			c.tick();
		}
		
		if(p.HP <= 0) State = STATE.GAME_OVER;
	
		
	//}
		
	}
	
	private void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		//////////////////////////////
		
		if(State == STATE.MENU) {
			g.drawImage(menuBackground, 0, 0, null);
			menu.render(g);
			
		}
		
//		else if(State == STATE.STAGE_3) {
//			g.drawImage(background_stage2, 0, 0, null);
//			stages.render(g);			
//		}
//		
		//////////////////////////////
		else if(State == STATE.GAME_1) {
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			g.drawImage(background_stage1, 0, 0, null);
			
			p.render(g);
			c.render(g);
			barrier1.render(g);
			barrier2.render(g);
			barrier3.render(g);

			g.setFont(fnt0);
			g.setColor(Color.black);
			g.drawString("SCORE : " + scoreTxt, (WIDTH * SCALE) - 300, 30);
			
			g.setColor(Color.gray);
			g.fillRect(60, 5, 200, 20);
			
			g.setColor(Color.red);
			g.fillRect(60, 5, (int)p.HP, 20);
			
			g.setColor(Color.black);
			g.drawRect(60, 5, 200, 20);
		}
		
		else if(State == STATE.STAGE_1) {
			g.drawImage(background, 0, 0, null);
			stages.render(g);			
		}
		
		/////////////////////////////////
		else if(State == STATE.GAME_2) {
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			g.drawImage(background_stage2, 0, 0, null);
			
			p.render(g);
			c.render(g);
//			meteor.render(g);
			
			g.setFont(fnt0);
			g.setColor(Color.black);
			g.drawString("SCORE : " + scoreTxt, (WIDTH * SCALE) - 300, 30);
			
			g.setColor(Color.gray);
			g.fillRect(60, 5, 200, 20);
			
			g.setColor(Color.red);
			g.fillRect(60, 5, (int)p.HP, 20);
			
			g.setColor(Color.black);
			g.drawRect(60, 5, 200, 20);
			
		}
		
		else if(State == STATE.STAGE_2) {
			g.drawImage(background, 0, 0, null);
			stages.render(g);			
		}
		
		////////////////////////////////////
		else if(State == STATE.GAME_3) {
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			g.drawImage(background_stage3, 0, 0, null);
			
			p.render(g);
			c.render(g);
			//alienTYPE3.render(g);
			g.setFont(fnt0);
			g.setColor(Color.black);
			g.drawString("SCORE : " + scoreTxt, (WIDTH * SCALE) - 300, 30);
			
			g.setColor(Color.gray);
			g.fillRect(60, 5, 200, 20);
			
			g.setColor(Color.red);
			g.fillRect(60, 5, (int)p.HP, 20);
			
			g.setColor(Color.black);
			g.drawRect(60, 5, 200, 20);
			///////////////////////////////
			g.setColor(Color.gray);
			g.fillRect(60, 30, 500, 20);
			
			g.setColor(Color.black);
			g.fillRect(60, 30, (int)alienTYPE3.HP, 20);
			
			g.setColor(Color.black);
			g.drawRect(60, 30, 500, 20);
		}
		
//		else if(State == STATE.STAGE_3) {
//			g.drawImage(background_stage1, 0, 0, null);
//			stages.render(g);	
//		
//		}
	
		
		/////////////////////////////////////
		else if(State == STATE.YOU_WIN) {
			g.drawImage(youWin_background, 0, 0, null);
			youWin.render(g);
			
			g.setFont(fnt0);
			g.setColor(Color.black);
			g.drawString("SCORE : " + scoreTxt, 650, 465);
		}
		
		else if(State == STATE.GAME_OVER) {
			g.drawImage(gameOver_background, 0, 0, null);
			gameOver.render(g);
			
		}
		//////////////////////////////////
		g.dispose();
		bs.show();
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
			if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D ) {
				p.setVelX(5);
			}
			else if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
				p.setVelX(-5);
			}
			else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
				p.setVelY(5);
			}
			else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
				p.setVelY(-5);
			}

		
	}
	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			p.setVelX(0);
		}
		else if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			p.setVelX(0);
		}
		else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			p.setVelY(0);
		}
		else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			p.setVelY(0);
		}
	}
	
	
	
	
	public static void main(String args[]) {
		
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
	
	public void ingameUpdater() {
		Game.stage_done1 = false;
		Game.stage_done2 = false;
		Game.stage_done3 = false;
		enemy_count = 5;
		enemy_count2 = 5;
		enemy_count3 = 1;
		enemy_killed = 0;
		setScoreTxt(null);
	}
	
	public BufferedImage getSpriteImage() {
		return spriteSheet;
	}

	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	public int getEnemy_killed() {
		return enemy_killed;
	}

	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}

	public int getMeteorQuantity() {
		return meteorQuantity;
	}

	public void setMeteorQuantity(int meteorQuantity) {
		this.meteorQuantity = meteorQuantity;
	}

	public int getDestroyedMeteor() {
		return destroyedMeteor;
	}

	public void setDestroyedMeteor(int destroyedMeteor) {
		this.destroyedMeteor = destroyedMeteor;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getScoreTxt() {
		return scoreTxt;
	}

	public void setScoreTxt(String scoreTxt) {
		this.scoreTxt = scoreTxt;
	}

	
	

	
}