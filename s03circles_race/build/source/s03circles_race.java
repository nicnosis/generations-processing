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

public class s03circles_race extends PApplet {

Orb[] orbs;
int N = 100;

public void setup() {
    //display stuff
    
    
    colorMode(RGB, 100);
    strokeWeight(1);
    stroke(0, 10);
    noFill();
    background(100);

    orbs = new Orb[N];
    for (int i = 0; i < N; i++) {
        orbs[i] = new Orb(width/PApplet.parseFloat(N) * i);
    }
}

public void draw() {
    for (Orb o : orbs) {
        o.update();
        o.display();
    }
    // saveFrame("renders/circles####.png");
}

class Orb {
    float value, alpha;
    float x, y, r;
    Orb(float startX) {
        value = 50;
        alpha = 50;
        y = 0;
        x = startX;
        r = random(20, 30);
    }
    public void update() {
        y++;
        if (y < height/2) {
            r = (random(1) > 0.9f) ? r *= random(0.9f, 0.99f) : r;
        } else {
            r = (random(1) > 0.9f) ? r *= random(1.01f, 1.05f) : r;
            r = constrain(r, 0, 20);
        }
        value += random(-10, 10);
        value = (value < 0) ? 100 : value;
        value = (value > 100) ? 0 : value;
        alpha = noise(frameCount)*100;
        // this.alpha += random(-10, 10);
        // this.alpha = (this.alpha < 0) ? 255 : this.alpha;
        // this.alpha = (this.alpha > 255) ? 0 : this.alpha;

    }
    public void display() {
        stroke(value, alpha);
        ellipse(x, y, r*2, r*2);
        ellipse(x, height-y, r*2, r*2);
    }

}
  public void settings() {  size(1080, 1080);  smooth(8); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "s03circles_race" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
