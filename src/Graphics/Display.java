package Graphics;

import java.awt.*;
import javax.swing.JFrame;

public class Display {
	
	/**
	 * Creates a new display with the given title, width and height.
	 * 
	 * @param title		The title for this display
	 * @param width		The width of the display
	 * @param height	The height of the display
	 */
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDisplay();
	}
	
	private JFrame frame;
	
	
	public Canvas getCanvas() {
		return this.canvas;
	}
	
	private Canvas canvas;
	
	/**
	 * Initializes this Display, setting the title and all needed attributes.
	 */
	public void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);	
		
		Dimension dim = new Dimension(width, height);
		canvas = new Canvas();
		canvas.setPreferredSize(dim);
		canvas.setMaximumSize(dim);
		canvas.setMinimumSize(dim);
		
		frame.add(canvas);
		}
	
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
	}
	
	private int width, height;
	
	
	public void setTitle(String title) {
		if (title != null)
			this.title = title;
	}
	
	public String getTitle(String title) {
		return this.title;
	}
	
	private String title;

}
