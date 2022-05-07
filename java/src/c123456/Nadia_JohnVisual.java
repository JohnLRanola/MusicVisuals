package c123456;

import ie.tudublin.*;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;


public class Nadia_JohnVisual extends Visual {

    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;
    int time = 0;
    float n4;
    float n6;

    public void settings()
    {
        fullScreen(P3D);
    }

    public void setup()
    {
        minim = new Minim(this);
        ap = minim.loadFile("surface.mp3", 1024);    
        ap.play();
        colorMode(RGB);
        frameRate(24);
    }

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

    public void draw()
    {
        // mode is taken from the keyPressed function
        switch (mode)
        {
			case 0:
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
            break;

            case 1:
            {
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
            break;

            case 2:
            {
                
            }
        }
    }   
}
