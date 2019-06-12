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

public class s06griddles extends PApplet {

Node[] nodes;
int N = 5; // rows and columns
float spacing;
float t;

public void setup() {
    // display stuff
    
    
    colorMode(RGB, 100);

    strokeWeight(2);
    stroke(0, 50);
    noFill();
    background(100);
    rectMode(CENTER);

    // setup vars
    spacing = width/N;
    println("spacing is " + spacing);
    nodes = new Node[N*N];
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            nodes[j+ i * N] = new Node(spacing*i, spacing*j);
        }
    }

}

public void draw() {
    background(100, 10);
    for (Node n : nodes) {
        n.display();
    }

    // saveFrame("renders/iso####.png");
}

class Node {
    float rotateSeed, noiseSeed;
    float x, y;
    float w;
    int[]arms = new int[4];
    int[]arcs = new int[4];
    int dot;

    Node(float _x, float _y) {
        rotateSeed = random(TWO_PI);
        noiseSeed = random(100);
        x = _x;
        y = _y;
        w = spacing * 0.9f / 2;

        // initialize features
        dot = round(random(1));
        for (int i = 0; i < arms.length; i++) {
            arms[i] = round(random(1));
            arcs[i] = round(random(1));
        }
    }

    public void display() {
        push();
        translate(x + spacing/2, y + spacing/2);
        // rotate(PI/4);
        rotate(frameCount/50f);

        // Do dot
        switch(dot) {
            case 0:
            // fill(0, 10);
            case 1:
            fill(0, 100);
            ellipse(0, 0, spacing/10, spacing/10);

            break;
        }
        noFill();

        // Do arms and arcs
        push();
        rotate(rotateSeed);
        for (int i = 0; i < arms.length; i++) {
            switch(arms[i]) {

                case 1:
                // line(0, 0, w, 0);
                case 0:
                break;
            }
            // switch(arcs[i]) {
                // case 1:
                // arc(0,0, w, w, 0, PI/4);
                // case 0:
                // break;
            // }
            rotate(PI/2);
        }
        // do arcs
        for (int i = 0; i < w ; i++) {
            // arc(0, 0, 10 + i*1, 10 + i*1, 0, 2*PI * noise(i/10f));
            float noiz = noise(noiseSeed*i) * PI;
            arc(0, 0, 10+i+w, 10+i+w, -noiz, noiz);
        }

        pop();


        pop();
    }

}
  public void settings() {  size(1080, 1080);  smooth(8); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "s06griddles" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
