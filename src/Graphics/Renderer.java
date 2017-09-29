package Graphics;

import java.awt.*;
import java.awt.image.BufferStrategy;


public class Renderer implements Runnable {
	
	public Display display;
	private Thread thread;
	
	/**
	 * Creates a game with a new display with the given parameters. 
	 * Fps will be equal to the defqult value of 60.
	 * 
	 * @param title
	 * @param width
	 * @param height
	 */
	public Renderer(String title, int width, int height) {
		this(title, width, height, 60);
	}
	
	/**
	 * Creates a game with a new display with the given parameters.
	 * 
	 * @param title
	 * @param width
	 * @param height
	 * @param fps
	 */
	public Renderer(String title, int width, int height, int fps) {
		display = new Display(title, width, height);
		setFps(fps);
	}
	
	/**
	 * Initializes this game.
	 */
	private void initialize() {
		
	}

	/**
	 * Method containing the main game loop of this game.
	 */
	@Override
	public void run() {
		initialize();
		
		while(running) {
			render();
			checkFps();
		}
		stop();
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		// Get graphics object to start drawing.
		g = bs.getDrawGraphics();
		
		// Clear the screen.
		g.clearRect(0, 0, display.getWidth(), display.getHeight());
		//Beginning of the drawing.
		g.drawRect(20, 20, 50, 50);
		//End of the drawing.
		
		//Draw fps information over everything else:
		drawFpsInformation(g);
		
		// Show the actual drawn items.
		bs.show();
		// Dispose of the Graphics object after done drawing.
		g.dispose();
	}
	
	/**
	 * Makes the game run at its current fps. If calculations are too slow, fps is reduced.
	 */
	private void checkFps() {
		long currentFrameTime = System.currentTimeMillis() - lastTime;
		if (getTimePerFrame() > currentFrameTime) {
			try {
				Thread.sleep(getTimePerFrame() - currentFrameTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			long frameSkips = currentFrameTime/getTimePerFrame() + 1;
			setSkippedFrames(getSkippedFrames() + frameSkips);
			try {
				Thread.sleep(getTimePerFrame()*frameSkips - currentFrameTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private long lastTime = 0;
	
	/** 
	 * Initializes a new thread for this game. If this game is already running, does nothing.
	 */
	public synchronized void start() {
		if (!running) {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}
	
	/**
	 * Stops this games thread. If the game is not currently running, does nothing.
	 */
	public synchronized void stop() {
		if (running) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private boolean running = false;
	
	private Graphics g;
	private BufferStrategy bs;
	
	
	/**
	 * Returns the time one frame will be drawn to the screen.
	 * @return
	 */
	public long getTimePerFrame() {
		return timePerFrame;
	}
	
	
	/**
	 * Returns the current fps of this game.
	 * @return
	 */
	public long getFps() {
		return this.fps;
	}
	
	private void drawFpsInformation(Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.BOLD, 12));
		g.drawString("FPS: " + Long.toString(getFps()), 5, 15);
	}
	
	/**
	 * Sets the fps for this game to the given fps.
	 * @param fps
	 */
	private void setFps(int fps) {
		this.fps = fps;
		timePerFrame = (long) (1000/fps);
	}
	
	private long timePerFrame;
	private int fps;
	
	
	public long getSkippedFrames() {
		return skippedFrames;
	}

	public void setSkippedFrames(long skips) {
		this.skippedFrames = skips;
	}
	
	private long skippedFrames = 0;
	

}
