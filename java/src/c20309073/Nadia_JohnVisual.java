package c20309073;

import ie.tudublin.Visual;
import ddf.minim.*;

public class Nadia_JohnVisual extends Visual
{

    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    // the mode is for the menu
    // it contains the key from 0 to 5 entered by the user
    // the default is 0, which causes the menu to be displayed
    int mode = 0;

    // this is used for the rotation for one of our visuals (fireworks, case 1)
    int spin1;
    float spin2;

    float x = 0, y = 0;

    // the screen size is set to the full screen of the user
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
    }

    // the keyPressed function is activated whenever a key is pressed
    // the mode is registered from keys 0 to 5, as 0 is our menu and 1-5 are our visualisers
    // 0 is the default pressed key
    // when other keys are pressed, nothing happens
    public void keyPressed()
    {
		if (key >= '0' && key <= '5')
        {
			mode = key - '0';
		}

        // when space is pressed, the music stops
        // when it is pressed again, the music plays again from the start
		if (keyCode == ' ')
        {
            if (ap.isPlaying())
            {
                ap.pause();
            }

            else
            {
                ap.rewind();
                ap.play();
            }
        }
	}

    public void draw()
    {
        // mode is taken from the keyPressed function
        // a switch function is used to alternate between the different visualisers
        switch (mode)
        {
            // when the user presses 0, a menu of available visualisers is displayed
            // this is also what the user sees when they run the program
            case 0:
            {
                background(247, 156, 244);

                // this code forms a black shadow behind the title of our chosen song
                fill(0);
                textAlign(CENTER, CENTER);
                textSize(width/15.0f);
                text("Surface Pressure from Encanto", width/2.02f, height/3.02f);

                // this code prints out the title of our chosen song
                fill(143, 0, 0);
                textAlign(CENTER, CENTER);
                textSize(width/15.0f);
                text("Surface Pressure from Encanto", width/2.0f, height/3.0f);

                textSize(width/35.0f);

                // this code lists our available visualisers so that the user can choose which they want to display
                fill(143, 0, 0);
                text("Press 1 for Fireworks Visualiser ", width/2.0f, (height/3.0f) + 100);
                text("Press 2 for Spheres Visualiser", width/2.0f, (height/3.0f) + 150);
                text("Press 3 for Heart Visualiser", width/2.0f, (height/3.0f) + 200);
                text("Press 4 for Space Visualiser ", width/2.0f, (height/3.0f) + 250);
                text("Press 5 for Spikes Visualiser", width/2.0f, (height/3.0f) + 300);

                // this code informs the user that pressing the spacebar can stop or start the music
                // it also lets the user know they can return to the menu by pressing 0 on their keyboard
                textSize(width/60.0f);
                text("Press SPACEBAR to pause/start ", width/2.0f, (height/3.0f) + 450);
                text("Press 0 to return back to MENU", width/2.0f, (height/3.0f) + 500);
            }

            break;

            // this case is used to depict our "fireworks" visual
			case 1:
            {
                fill(0, 50);  
                noStroke();

                rect(0, 0, width, height);

                //This centers our visual on the screen
                translate(width/2, height/2);
                
                for (int i = 0; i < ap.bufferSize() - 1; i++)
                {    
                    //This variable is used to make the inner circle 
                    float circle1 = sin(i + spin1) * 10; 

                    //This variable is used to make the outer circle
                    float circle2 = sin(i + spin2) * 300; 
                    
                    //This float is used to spread particles in the x axis
                    float x = sin(radians(i)) * (circle2 + 30); 

                    //This float is used to spread particles in the y axis
                    float y = cos(radians(i)) * (circle2 + 30);
                    
                    //This float is used to make the inner circle
                    float x3 = sin(radians(i)) * (500/circle1); 

                    //This float is used to make the outer circle
                    float y3 = cos(radians(i)) * (500/circle1);
                    
                    // light yellow
                    fill (255, 255, 153); 

                    //This ellipse makes the inner circle using our x and y values from earlier. This then reacts using our audio
                    ellipse(x, y, ap.left.get(i)*10, ap.left.get(i)*10);
                    
                    // pink
                    fill (255, 102, 217); 
                    
                    //This is used to make the particles 
                    rect(x3, y3, ap.left.get(i)*20, ap.left.get(i)*10);
                
                    // burnt orange
                    stroke(204, 102, 0); 

                    //This is used to make the particles
                    rect(x, y, ap.right.get(i)*10, ap.left.get(i)*10);
                
                    // blush pink
                    fill(255, 0, 102); 

                    //This is used to make the particles
                    rect(x3, y3, ap.right.get(i)*10, ap.right.get(i)*20);
        
                    var z = i;
                    var z1 = floor(map(i ,0, width, 0, ap.length())) * 300 + height / 2;
                    point(z, (float) z1);
                }   
                
            //This rotates the whole visual using sound and a constant variable
            spin1 += 0.008;
            spin2 += ap.right.get(spin1);
            } // end case 1

            break;

            // this case depicts our second visual - spheres
            case 2:
            {
                // the background is set to black
                background(0);
                
                int time = 0;

                //This makes the circle that are made depending on the bufferSize
                for (int i = 0; i < ap.bufferSize(); i+= 20)
                {
                    
                  //This centers our visual in the x axis and at the same time uses our audio to react on the x axis  
                  float x = cos(radians(i)) * ap.left.get(i) * 500 + width / 2;

                  //This centers our visual in the y axis and at the same time uses our audio to react on the y axis
                  float y = sin(radians(i))  * ap.right.get(i) * 340 * ap.left.get(i) + height / 2;
                  
                  float w = cos(radians(time + i)) * 200;

                  w = abs(w);

                  //Colours of our lines
                  // dark purple/blue
                  stroke(54, 32, 125);  

                  //Colours of the circle
                  // pale mint
                  fill(147, 207, 180, 99);

                  // this is the code that creates the circular shapes
                  // it uses previously initialised variables that cause reactions to sound
                  ellipse(x, y, w, w);
                } // end for

                time++;
            } // end case 2

            break;

            // this case displays our heart visual
            case 3:
            {
                // the background is set to black
                background(0);
                noStroke();

                // cerise pink
                fill(186, 54, 147);

                //This centers our visualiser on the screen
                translate(width/2, height/2);

                // code using mathematical formulas for creating a heart shape
                for (float angle = -90; angle < 90; angle += 0.05)
                {
                    for (float q = 1; q < 2; q += 0.2)
                    {
                        // q calculates how much the line has to curve
                        // a circle is 360 degrees
                        // q makes sure the shape on both sides is equal
                        float a = q*180;
                        float t = (float) (angle + frameCount * 1.5 + a);
                        float x = 16*pow(sin(radians(t)), 3);
                        // float is reactive to the music
                        float y = -13*cos(radians(t)) + 5*cos(radians(2*t)) + ap.right.get((int) q) + 2*cos(radians(3*t)) + cos(radians(4*t))+ ap.right.get((int) q);
                        float s = cos(radians(angle))*q*7;
                        // a shape is made using the variables calculated above
                        ellipse(x*q*15, y*q*15, s, s);

                    } // end for
                } // end for
            } // end case 3

            break;

            // this case displays our rainbow galaxy visualiser
            case 4:
            {
                // the background is set to black
                background(0);

                // final means that the value cannot be changed
                final int H = 100;
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
			
			//The map() function took the incrementing values of the for loops 
                        fill (map(i, 0, H, 0, 255), map(j, 0, H, 0, 255), (x + y)*256);
			
			//The rect() uses the calculated x and y values earlier. The width and height is then made to react to the audio
                        rect (x * width/4 + width/2, y * height/4 + height/2, ap.right.get(i)*20, ap.left.get(i)*20);
                    } // end for
                } // end for 
            } // end case 4
            break;

            // case 5 shows our fireworks visual
            case 5:
            {
                // the background is set to black
                background(0);

                int n = 1000;

                // the arrays used for this visual are initialised
                float [] a = new float[n]; 
                // rad refers to each radius
                float [] rad = new float[n]; 
                float [] r = new float[n];
                // vel refers to the velocity
                float [] vel = new float[n]; 
                float [] x1 = new float[n];
                float [] y1 = new float[n];
                
                // this for loop runs until i reaches 999, as n is 1000
                for (int i = 0; i < n; i++)
                {
                    // the random() java method generates a random number between the specified range
                    // i.e. random(30, 100) generates a number from 30 to 100
                    // the code generates random lengths and speeds for the lines
                    a[i] = random(30, 100);
                    rad[i] = radians(a[i]);
                    r[i] = random(20, 330);
                    // a random speed at which each line spins is generated
                    vel[i] = random(-2, 2);
                    // the x1 and y1 variables make the lines reactive to music
                    x1[i] = width/2+r[i] * ap.left.get(i) * cos(rad[i] * 100);
                    y1[i] = height/2+r[i] * ap.left.get(i) * sin(rad[i] * 100);
                    
                    // strokeWeight() sets the width of the stroke
                    strokeWeight(2);
                    stroke(i, 43, 104);
                    // pale pink
                    fill(237, 145, 183);
                    line(width/2, height/2, x1[i], y1[i]);
                    noStroke();
                    // a circular shape in which the lines are created is made
                    ellipse(x1[i], y1[i], 10, 10);
                    a[i] = a[i] + vel[i];

                } // end for
            } // end case 5
            break;
            
        } // end switch
    } // end draw
} // end
