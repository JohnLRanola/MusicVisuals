
package c123456;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet; 

public class ourVisual extends PApplet
{
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;

    float[] lerpedBuffer;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    float n4;
    float n6;

    FFT fft;

    public void keyPressed() {
		if (key >= '0' && key <= '9') {
			mode = key - '0';
		}
		if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
	}

    public void settings()
    {
        size(1024, 1000, P3D);
        //fullScreen(P3D, SPAN);
        
    }
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
    }



    public void draw()
    {
        fill(0,50);  
        noStroke();
        rect(0, 0, width, height);
        translate(width/2, height/2);
       
        for (int i = 0; i < ap.bufferSize() - 1; i++) {
       
          float angle = sin(i+n4)* 10; 
          float angle2 = sin(i+n6)* 300; 
       
          float x = sin(radians(i))*(angle2+30); 
          float y = cos(radians(i))*(angle2+30);
       
          float x3 = sin(radians(i))*(500/angle); 
          float y3 = cos(radians(i))*(500/angle);
       
            fill (255, 255, 153); //yellow
            ellipse(x, y, ap.left.get(i)*10, ap.left.get(i)*10);
        
            fill (255, 102, 217); //pink
            rect(x3, y3, ap.left.get(i)*20, ap.left.get(i)*10);
        
            stroke (204, 102, 0); //orange
            rect(x, y, ap.right.get(i)*10, ap.left.get(i)*10);
        
        
            fill(255, 0, 102); //wt
            rect(x3, y3, ap.right.get(i)*10, ap.right.get(i)*20);
            
            //use this for when song explodes after drip drip drip
            //line(ap.left.get(i)*x, ap.left.get(i)*y, y3, x3);

            
            var z = i;
            var z1 = floor(map(i,0,width,0,ap.length())) * 300 + height / 2;
            point(z,(float) z1);
        }   

    n4 += 0.008;
    n6 += 0.04;
}

}