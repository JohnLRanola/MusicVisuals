package c123456;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector; 
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;

public class PUTFILENAMEHERE extends PApplet {

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
    }

    public void settings() {
        size(1024, 1000, P3D);
    }

    public void draw() {
   
    for (int i = 0; i < ap.bufferSize() - 1; i++) {


    }

}
}
