import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class s08sine extends PApplet {

float t = 0; // theta
float h = 0; // height of line
public void setup() {
    //display stuff
    
    
    colorMode(RGB, 100);

}

public void draw() {
    background(100);
    fill(0);
    rect(0, height/2, width, height/2);
    translate(0, height/2);

    line(0, 0, width, 0);
    for (int x = 0; x < width; x+= 10) {
        for (float scale = 1; scale < 6; scale += 0.5f) {
            push();

            scale(scale, scale);
            h = sin(t + map(x, 0, width, 0, TWO_PI)) * 50;


            // stroke(0, map(scale, 6, 1, 0, 50));
            // line(x, 0, x, h);

            fill(map(h, -20, 20, 0, 100), map(abs(h), 0, 20, 0, 70));
            stroke(0);
            ellipse(x, h, 5, 5);
            pop();
        }
    }

    t += 0.02f;


    // saveFrame("renders/iso####.png");
}
  public void settings() {  size(720, 720);  smooth(8); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "s08sine" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
