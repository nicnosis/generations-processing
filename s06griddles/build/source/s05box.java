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

public class s05box extends PApplet {

Box[] boxes;
int N = 50;
float t;

public void setup() {
    //display stuff
    
    
    colorMode(RGB, 100);

    strokeWeight(1);
    stroke(0, 10);
    noFill();
    background(100);
    rectMode(CENTER);

    boxes = new Box[N];
    for (int i = 0; i < N; i++) {
        boxes[i] = new Box();
    }
}

public void draw() {
    for (Box b : boxes) {
        if (b.life >= 0) {

            b.update();
            b.display();
        }
    }
    t = frameCount/100f;

    // push();
    // translate(width/2, height/2 - frameCount);
    // rotate(t);
    // rect(0, 0 - t*10, 100+t*20, 100+t*20);
    // pop();

    // saveFrame("renders/iso####.png");
}

class Box {
    float x, y, w;
    float xoff;
    float xjerk;
    float twist;
    float scl;
    int life;

    Box() {
        x = random(width);
        y = random(height);
        w = random(10, 25);
        life = floor(random(100, 250));
        xoff = random(100);
        twist = random(100);
        scl = 1;
    }
    public void update() {
        xoff += 0.01f;
        twist += 0.01f;
        xjerk = map(noise(xoff), 0, 1, -20, 20);
        y--;
        scl += 0.01f;
        life--;
    }
    public void display() {
        push();
        rotate(PI/6);
        translate(x + xjerk, y);
        rotate(t + noise(twist)*2*PI);
        scale(scl);
        rect(0, 0, w, w);
        pop();
    }

}
  public void settings() {  size(1080, 1080);  smooth(8); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "s05box" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
