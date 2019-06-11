Node[] nodes;
int N = 10; // rows and columns
float spacing;
float t;

void setup() {
    // display stuff
    size(400, 400);
    smooth(8);
    colorMode(RGB, 100);

    strokeWeight(1);
    stroke(0, 10);
    noFill();
    background(100);
    rectMode(CENTER);

    // setup vars
    spacing = width/N;
    nodes = new Node[N];
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            nodes[i] = new Node(i, j);
        }
    }
}

void draw() {
    for (Node n : nodes) {
        n.display();
    }

    // saveFrame("renders/iso####.png");
}

class Node {
    float x, y;
    byte[] arms, arcs;
    byte dot;

    Node(float _x, float _y) {
        x = _x;
        y = _y;
    }

    void display() {
        push();
        translate(x, y);

        // Do dot
        switch(dot):
        break;

        // Do arms
        push();
        for (int i = 0; i < arms.length; i++) {
            switch(arms[i]):
            case 1:
            line(0, 0, spacing, 0)
            case 0:
            break;
            rotate(PI/2);
        }
        pop();

        // Do arcs
        switch(arcs):
        break;


        pop();
    }

}
