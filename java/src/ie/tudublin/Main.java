package ie.tudublin;

import example.CubeVisual;
import c123456.ourVisual;
import c123456.knots;
import c123456.balls;
import example.RotatingAudioBands;

// lol i am doing cool code

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new balls());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startUI();			
	}
}