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

public class s00example extends PApplet {

Bug[] bugs;
int N = 100;

public void setup() {
    //display stuff
    
    
    colorMode(RGB, 100);
    strokeWeight(1);
    stroke(0, 10);
    noFill();
    background(100);

    bugs = new Bug[N];
    for (int i = 0; i < N; i++) {
        bugs[i] = new Bug();
    }

    // saveFrames('a/iso', 'png', 2, 30);
}

public void draw() {
    for (Bug c : bugs) {
        c.update();
        c.display();
    }

}

class Bug {
    float x, y;

    Bug() {
        x = random(width);
        y = random(width);
    }
    public void update() {
        x += random(-2, 2);
        y += random(-2, 2);
    }
    public void display() {
        point(x, y);
    }

}
  public void settings() {  size(1080, 1080);  smooth(8); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "s00example" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
