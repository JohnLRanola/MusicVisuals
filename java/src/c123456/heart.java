package c123456;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector; 
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;

public class heart extends PApplet {

    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    float angle = 0;
    FFT fft;

    public void setup()
    {
        noCursor();
        smooth();
        background (0);
        frameRate(24);
        
        minim = new Minim(this);
        ap = minim.loadFile("surface.mp3", 1024);    
        ap.play();
        colorMode(RGB);
        frameRate(24);

        noStroke();
        fill(227, 30, 102);
    }

    public void settings()
    {
        size(1024, 1000, P3D);
    }

    public void draw()
    {
   
            background(0);
            translate(width/2, height/2);
            for (float angle = -90; angle < 90; angle += 0.05)
            {
                for (float q = 1; q < 2; q += 0.2)
                {
                    float a = q*180;
                    float t = (float) (angle + frameCount * 1.5 + a);
                    float x = 16*pow(sin(radians(t)), 3);
                    float y = -13*cos(radians(t)) + 5*cos(radians(2*t)) + ap.right.get((int) q) + 2*cos(radians(3*t)) + cos(radians(4*t))+ ap.right.get((int) q);
                    float s = cos(radians(angle))*q*7;
                    ellipse(x*q*15, y*q*15, s, s);
                }
            }
    }
}