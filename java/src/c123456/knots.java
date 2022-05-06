package c123456;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector; 
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;

public class knots extends PApplet {

    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    float angle = 0;
    FFT fft;

    ArrayList<PVector> vectors = new ArrayList<PVector>();
    // r(beta) = 0.8 + 1.6 * sin(6 * beta)
    // theta(beta) = 2 * beta
    // phi(beta) = 0.6 * pi * sin(12 * beta)

    //x = r * cos(phi) * cos(theta)
    //y = r * cos(phi) * sin(theta)
    //z = r * sin(phi)


    float beta = 0;

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
    }

    public void settings() {
        size(1024, 1000, "processing.opengl.PGraphics3D");
    }
    int i;
    public void draw() {
    background(0);
    translate(width/2, height/2);
    rotateY(angle);
    angle += ap.left.get(i) + 0.03;

    for (int i = 0; i < ap.bufferSize() - 1; i++) {

        float r = (float) (100*(0.8 + 1.6 * sin(6 * beta)));
        float theta = 2 * beta;
        float phi = (float) (0.6 * PI * sin(12 * beta));
        float x = r * cos(phi) * cos(theta);
        float y = r * cos(phi) * sin(theta);
        float z = r * sin(phi);
        stroke(255, r, 255);

        vectors.add(new PVector(x,y,z));

    }


    beta += 0.01;


    noFill();
    stroke(255);
    strokeWeight(8);
    beginShape();
    for (PVector v : vectors) {
        vertex(v.x, v.y, v.z);
    }
    endShape();
}
}
