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

public class s07square_scale extends PApplet {

Node[] nodes;
int N = 8; // rows and columns
float spacing;
float t;

public void setup() {
    // display stuff
    
    
    colorMode(RGB, 100);
    // rectMode(CENTER);

    // noStroke();
    strokeWeight(1);
    stroke(0, 10);
    fill(0, 10);
    background(100);

    // setup vars
    spacing = width/N;
    nodes = new Node[N*N];
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            nodes[j+ i * N] = new Node(spacing*i, spacing*j);
        }
    }

}

public void draw() {
    background(100);
    for (Node n : nodes) {
        n.update();
        n.display();
    }

    // saveFrame("renders/iso####.png");
}

class Node {
    float x, y, w;
    float xoff, yoff;
    Node(float _x, float _y) {
        x = _x;
        y = _y;
        xoff = random(100);
        yoff = random(100);
        w = spacing;
    }

    public void update() {
        xoff += 0.02f;
        yoff += 0.02f;
    }
    public void display() {
        for(float i = 1; i > 0; i -= 0.05f) {
            push();
            translate(x, y);
            scale(abs(i - noise(xoff)), abs(i - noise(yoff)));

            rect(0, 0, w, w);
            pop();
        }
    }
}
  public void settings() {  size(1080, 1080);  smooth(8); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "s07square_scale" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
