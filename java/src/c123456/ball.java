package c123456;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector; 
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;

public class ball extends PApplet {

    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    float angle = 0;
    FFT fft;
    float [] x;
    float [] y;
    float [] z;
    float [] c;
    float num = 100;
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
        colorMode(HSB, 360, 100, 100, 100);
      
        for (int i = 0; i < num; i = i + 1) {
          x[i] = random(-width / 2, width / 2);
          y[i] = random(-height / 2, width / 2);
          z[i] = random(-500, 500);
          c[i] = color(random(360), 100, 100, random(80));
        }
    }

    public void settings() {
        size(1024, 1000, P3D);
    }

    public void draw() {
        background(311, 100, 100);
      
        for (int i = 0; i < num; i = i + 1) {
          push();
          translate(x[i], y[i], z[i]);
          fill(c[i]);
          stroke(0, 0, 100); //nomalMaterial();
          rotateX((x[i] + frameCount) / 3);
          rotateY((y[i] + frameCount) / 4);
          rotateZ((z[i] + frameCount) / 5);
          strokeWeight(1);
          stroke(0, 0, 100);
          sphere(60);
          pop();
      
          z[i] = z[i] + 3;
          if (z[i] > 500) {
            z[i] = -500;
          }
        }

}
}
