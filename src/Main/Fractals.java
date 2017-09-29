package Main;

import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import ComplexMath.*;

import java.util.Random;

public class Fractals {
	
	// Voor runtime:		x1: 10 sec
	//						x2: 56 sec
	//						x4: 180 sec
	//						x8: 700 sec
	//	
	private static double scale = 4;
	private static final int mod = 8;
	private static int width = mod*1366; //(int) Math.pow(2, 12);
	private static int height = mod*768; //(int) Math.pow(2, 12);
	
	// Max iterations and thus amount of colors for Newton method:
	private static float iterations = 50;
	
	static Random RNG = new Random();
	
	public static void main(String[] args) {
		double start = System.currentTimeMillis();
		File outputfile = new File("C:\\Users\\Yrjo Koyen\\Pictures\\java\\image.png");
		Converging res;
		
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		
		for (int x=0;x<width;x++) {
			for (int y=0;y<height;y++) {
				Complex startPoint = new Complex((-(double)(width/2)+x)/(width/scale),
												(-(double)(height/2)+y)/(height/scale));
				res = NewtonRaphson.findZero(startPoint, (int)iterations);
				float hue = (float)((res.iterations) / iterations);
				bufferedImage.setRGB(x, y, Color.HSBtoRGB((float)(1.0- hue), 1, 1));
			}
			System.out.println(x + "/" + width);
		}
	
		try {
			ImageIO.write(bufferedImage, "jpg", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Finished!");
		System.out.println("Time to calculate: " + (System.currentTimeMillis()-start)/1000 +" seconds");
	}

}
