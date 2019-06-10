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

public class s02rotary_thing extends PApplet {

float noiz = 0;
Band bands[];
int N = 350;

public void setup() {
    
    

    colorMode(RGB, 100);
    noFill();

    bands = new Band[N];
    for (int i = 0; i < N; i++) {
        bands[i] = new Band(i);
    }
}

public void draw() {
    background(100);

    push();
    translate(width/2, height/2);
    noiz = noise(frameCount/100f);

    ellipse(0, 0, 3, 3); //center
    for(int i = 0; i < N; i++) {
        push();
        rotate(radians((i*frameCount)/50f));
        // stroke(100, 20, 20, bands[i].alpha);
        stroke(0, bands[i].alpha);
        strokeWeight(bands[i].weight + 2);

        arc(0, 0, i*width/N, i*width/N, bands[i].start, bands[i].start+bands[i].length);
        pop();
    }
    pop();
}

public void windowResized() {
    // resizeCanvas(windowWidth, windowHeight);
}

class Band {
    float seed1, seed2;
    float weight, alpha;
    float start, length;
    Band(int index) {
        seed1 = random(-10, 10);
        seed2 = random(-10, 10);
        weight = width/N/2 - 1;
        // weight = random(1, 10);
        // alpha = random(10, 50);
        alpha = noise(index/10f)*100;
        start = radians(noise(index/100f)*360 + seed1);
        length = radians(noise(index/100f)*360) - radians(noise(index/100f)*seed2);
    }
}

public void keyPressed() {
    if(key == ' ') {

      setup();
    }
}
  public void settings() {  size(1080, 1080);  smooth(8); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "s02rotary_thing" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
