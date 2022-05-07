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

    int n = 1000;
    float [] alpha=new float[n]; 
    float [] alphaVel=new float[n]; 
    float [] radianti=new float[n]; 
    float [] r=new float[n];
    float [] x1=new float[n];
    float [] y1=new float[n];
        int mode = 0;
    int time = 0;
    float n4;
    float n6;
    // final means that the value cannot be changed
    final int H = 100;
    float x = 0, y = 0;

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

        for(int i=0; i<n; i++){
            alpha[i]=random(30,100);
            alphaVel[i]=random(-2,2);
            r[i]=random(20,330);
        }
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
            break;

            case 3:
            {
                background(0);
                float f = (float)(frameCount*.01);
          
                // H is 100 so these  two for loops are carried out 100 times each
                // i goes from 0 to 99
                for (int i = 0; i < H; i++)
                {
                  for (int j = 0; j < H; j++)
                  {
                    float a = i * 2 - y + f;
                    float b = x + a / H + f;
                    x = sin(b) - cos(a);
                    y = cos(b) - sin(a);
                    fill (map(i, 0, H, 0, 255), map(j, 0, H, 0, 255), (x + y)*256);
                    rect (x * width/4 + width/2, y * height/4 + height/2, ap.right.get(i)*20, ap.left.get(i)*20);
                  } // end for
                } // end for 
            }
            break;

            case 4:
            {
                background(0);
                for(int i=0; i < n; i++){
                    radianti[i]=radians(alpha[i]);
                    x1[i]=width/2+r[i] * ap.left.get(i) * cos(radianti[i] * 100);
                    y1[i]=height/2+r[i] * ap.left.get(i) * sin(radianti[i] * 100);
                    strokeWeight(2);
                        stroke(i, 43, 104);
                        fill(237, 145, 183);
                    line(width/2,height/2,x1[i],y1[i]);
                    noStroke();
                    ellipse(x1[i],y1[i],10,10);
                    alpha[i]=alpha[i]+alphaVel[i];
                }
            }

            
        }
    }   
}

