package c123456;

import processing.core.PApplet;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;

public class balls extends PApplet {

    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    float angle = 0;
    FFT fft;

    int time = 0;

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
        background(0);
        for(int i = 0; i < ap.bufferSize(); i+= 10){
          float x = cos(radians(i)) * ap.left.get(i) * 500 + width / 2;
          float y = sin(radians(i))  * ap.right.get(i) * 340 * ap.left.get(i) + height / 2;
          float w = cos(radians(time + i)) * 200;
          w = abs(w );
          stroke(54, 32, 125);   
          fill(147, 207, 180, 99);
          ellipse(x, y, w, w);
        }
        time++;


}
}
