package c123456;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector; 
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;

public class windmill extends PApplet {

//alpha position
int n = 1000;
float [] alpha=new float[n]; 
float [] alphaVel=new float[n]; 
float [] radianti=new float[n]; 
float [] r=new float[n];
float [] x=new float[n];
float [] y=new float[n];

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
    for(int i=0; i<n; i++){
      alpha[i]=random(30,100);
      alphaVel[i]=random(-2,2);
      r[i]=random(20,330);
    }
}

public void settings() {
    size(1024, 1000, P3D);
}


//DINAMICHE
public void draw() {
        background(0);
        for(int i=0; i < n; i++){
            radianti[i]=radians(alpha[i]);
            x[i]=width/2+r[i] * ap.left.get(i) * cos(radianti[i] * 100);
            y[i]=height/2+r[i] * ap.left.get(i) * sin(radianti[i] * 100);
            strokeWeight(2);
                stroke(i, 43, 104);
                fill(237, 145, 183);
            line(width/2,height/2,x[i],y[i]);
            noStroke();
            ellipse(x[i],y[i],10,10);
            alpha[i]=alpha[i]+alphaVel[i];
        }

}
}


