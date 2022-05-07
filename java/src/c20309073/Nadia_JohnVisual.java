package c123456;

import ie.tudublin.Visual;
import ddf.minim.*;


public class Nadia_JohnVisual extends Visual {

    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    //Initialises arrays for our visualiser to use
    int n = 1000;
    float [] a=new float[n]; 
    float [] vel=new float[n]; 
    float [] rad=new float[n]; 
    float [] r=new float[n];
    float [] x1=new float[n];
    float [] y1=new float[n];

    //this is for the menu
    int mode = 0;

    int time = 0;

    //Used for the rotation for one of our visuals
    int spin1;
    float spin2;

    // final means that the value cannot be changed
    final int H = 100;
    float x = 0, y = 0;

    public void settings()
    {
        fullScreen(P3D, SPAN);
    }

    public void setup()
    {
        minim = new Minim(this);
        ap = minim.loadFile("surface.mp3", 1024);    
        ap.play();
        colorMode(RGB);
        frameRate(24);

        for(int i=0; i<n; i++){
            a[i]=random(30,100);
            vel[i]=random(-2,2);
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
                background(247, 156, 244);

                fill(0);
                textAlign(CENTER, CENTER);
                textSize(width/15.0f);
                text("Surface Pressure from Encanto", width/2.02f, height/3.02f);

                //This shows the title of our song
                fill(143, 0, 0);
                textAlign(CENTER, CENTER);
                textSize(width/15.0f);
                text("Surface Pressure from Encanto", width/2.0f, height/3.0f);

                textSize(width/35.0f);
                //This lists our visualisers
                fill(143, 0, 0);
                text("Press 1 for Fireworks Visualiser ", width/2.0f, (height/3.0f) + 100);
                text("Press 2 for Balls Visualiser", width/2.0f, (height/3.0f) + 150);
                text("Press 3 for Heart Visualiser", width/2.0f, (height/3.0f) + 200);
                text("Press 4 for Space Visualiser ", width/2.0f, (height/3.0f) + 250);
                text("Press 5 for Spikes Visualiser", width/2.0f, (height/3.0f) + 300);

                textSize(width/60.0f);
                text("Press SPACEBAR to pause/start ", width/2.0f, (height/3.0f) + 450);
                text("Press 0 to return back to MENU", width/2.0f, (height/3.0f) + 500);
            }

            break;

			case 1:
            {
                fill(0,50);  
                noStroke();


                rect(0, 0, width, height);

                //This centers our visual
                translate(width/2, height/2);
                
                for (int i = 0; i < ap.bufferSize() - 1; i++) {
                    
                    //This variable is used to make the inner circle 
                    float angle = sin(i+spin1)* 10; 

                    //This variable is used to make the outer circle
                    float angle2 = sin(i+spin2)* 300; 
                    
                    //This float is used to spread particles in the x axis
                    float x = sin(radians(i))*(angle2+30); 

                    //This float is used to spread particles in the y axis
                    float y = cos(radians(i))*(angle2+30);
                    
                    //This float is used to make the inner circle
                    float x3 = sin(radians(i))*(500/angle); 

                    //This float is used to make the outer circle
                    float y3 = cos(radians(i))*(500/angle);
                    
                    fill (255, 255, 153); 

                    //This ellipse makes the inner circle using our x and y values from earlier. This then reacts using our audio
                    ellipse(x, y, ap.left.get(i)*10, ap.left.get(i)*10);
                    
                    fill (255, 102, 217); 
                    
                    //This is used to make the particles 
                    rect(x3, y3, ap.left.get(i)*20, ap.left.get(i)*10);
                
                    stroke (204, 102, 0); 

                    //This is used to make the particles
                    rect(x, y, ap.right.get(i)*10, ap.left.get(i)*10);
                
                
                    fill(255, 0, 102); 

                    //This is used to make the particles
                    rect(x3, y3, ap.right.get(i)*10, ap.right.get(i)*20);
        
                    
                    var z = i;
                    var z1 = floor(map(i,0,width,0,ap.length())) * 300 + height / 2;
                    point(z,(float) z1);
                }   
                
            //This rotates the whole visual using sound and a constant vairable
            spin1 += 0.008;
            spin2 += ap.right.get(spin1);
            }
            break;

            case 2:
            {
                background(0);
                
                //This makes the circle that are made depending on the bufferSize
                for(int i = 0; i < ap.bufferSize(); i+= 20){
                    
                  //This centers our visual in the x axis and at the same time uses our audio to react on the x axis  
                  float x = cos(radians(i)) * ap.left.get(i) * 500 + width / 2;

                  //This centers our visual in the y axis and at the same time uses our audio to react on the y axis
                  float y = sin(radians(i))  * ap.right.get(i) * 340 * ap.left.get(i) + height / 2;
                  
                  float w = cos(radians(time + i)) * 200;

                  w = abs(w);

                  //Colours of our lines
                  stroke(54, 32, 125);  

                  //Colours of the circle
                  fill(147, 207, 180, 99);

                  //This is the shape using our varaibles earlier to make it react to sound
                  ellipse(x, y, w, w);
                }
                time++;
            }
            break;

            case 3:
            {
                background(0);
                noStroke();
                fill(186, 54, 147);

                //This centers our visualiser
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

            case 4:
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

            case 5:
            {
                background(0);
                for(int i=0; i < n; i++)
                {
                    rad[i] = radians(a[i]);
                    x1[i] = width/2+r[i] * ap.left.get(i) * cos(rad[i] * 100);
                    y1[i] = height/2+r[i] * ap.left.get(i) * sin(rad[i] * 100);
                    strokeWeight(2);
                    stroke(i, 43, 104);
                    fill(237, 145, 183);
                    line(width/2, height/2, x1[i], y1[i]);
                    noStroke();
                    ellipse(x1[i], y1[i] ,10 , 10);
                    a[i] = a[i] + vel[i];
                }
            }
            break;
            
        }
    }   
}

