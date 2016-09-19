//quadtrees <--- collision

package game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import audio.Sound;
import display.Display;
import gfx.Assets;
import gfx.GameCamera;
import input.KeyManager;
import input.MouseManager;
import states.GameState;
import states.MenuState;
import states.State;
import states.StateManager;

public class Crux implements Runnable{
	private Display display;
	
	private Sound sound;
	
	private int width;
	private int height;
	public String title;
	
	private boolean running = false;
	
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	public State gameState;
	public State menuState;

	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	private GameCamera gameCamera;
	
	private Handler handler;
	
	public Crux(String title, int width, int height){
		this.width=width;
		this.height=height;
		this.title=title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}

	private void init(){
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		
		handler = new Handler(this);
		gameCamera= new GameCamera(handler, 0, 0);
		
		
		gameState=new GameState(handler);
		menuState=new MenuState(handler);
		StateManager.setState(menuState);

	}
	
	private void tick(){
		keyManager.tick();
		
		if(StateManager.getState() != null)
			StateManager.getState().tick();
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs==null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		if(StateManager.getState() != null)
			StateManager.getState().render(g);		
		
		bs.show();
		g.dispose();
	}
	
	public void run(){
		init();
		
		int fps = 60;
		double timePerTick=1000000000/fps;
		double delta=0;
		long now;
		long lastTime=System.nanoTime();
		long timer=0;
		int ticks=0;
		
		while(running){
			now=System.nanoTime();
			delta+=(now-lastTime)/timePerTick;
			timer+=now-lastTime;
			lastTime=now;
			
			if (delta>=1){
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timer>=1000000000){
				System.out.println("fps:" + ticks);
				ticks=0;
				timer=0;
			}
		}
		
		stop();
	}
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	public MouseManager getMouseManager(){
		return mouseManager;
	}
	
	public GameCamera getGameCamera(){
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Display getDisplay() {
		return display;
	}

	public synchronized void start(){
		if (running)
			return;
		running=true;
		thread=new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running=false;
		try {
			thread.join();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
