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

public class s01isometric_structures extends PApplet {

Cloud[] clouds;
int N = 100;

public void setup() {
    //display stuff
    
    
    rectMode(CENTER);
    colorMode(RGB, 100);
    strokeWeight(1);
    noFill();
    background(100);

    clouds = new Cloud[N];
    for (int i = 0; i < N; i++) {
        clouds[i] = new Cloud();
    }

    // saveFrames('a/iso', 'png', 2, 30);
}

public void draw() {
    for (Cloud c : clouds) {
        c.update();
        if (c.life > 0) c.display();
    }
    saveFrame("renders/iso####.png");
}

class Cloud {
    float x, y, proportion, vx, vy, w, h, life, seed;
    boolean hasFill;
    int stroke;


    Cloud() {
        seed = random(1000);
        x = random(width/2 - 20);
        // hasFill = (x > width/2) ? true : false;
        y = random(height);
        proportion = random(0.25f, 1);
        vx = random(-.2f, .2f);
        vy = random(-.5f, -1);
        w = random(10, 50);
        h = random(10, 50);
        life = random(250, 1000);
    }
    public void update() {
        y += vy;
        // x += vx;
        proportion += random(-0.05f, 0.05f);
        proportion = constrain(proportion, 0.25f, 1);
        life--;
        stroke = color(0, noise(frameCount/1000 + seed) * 50);
    }

    public void display() {
        stroke(stroke);
        noFill();
        push();
        translate(x, y);
        scale(proportion);
        rotate(-PI / 6);

        scale(1, .86062f); // scale vertical 86.062%
        shearX(PI / 6); // skew 30 degrees
        rotate(noise(frameCount/100f) * PI * 4);
        rect(0, 0, w, h);

        pop();
        push();
        fill(100);
        translate(width - x, y);
        scale(proportion);
        rotate(PI / 6);
        scale(1, .86062f); // scale vertical 86.062%
        shearX(-PI / 6); // skew 30 degrees
        rotate(-noise(frameCount/100f) * PI * 4);
        rect(0, 0, -w, h);
        pop();

    }

}
  public void settings() {  size(1080, 1080);  smooth(8); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "s01isometric_structures" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
