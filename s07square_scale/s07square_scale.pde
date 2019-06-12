Node[] nodes;
int N = 8; // rows and columns
float spacing;
float t;

void setup() {
    // display stuff
    size(1080, 1080);
    smooth(8);
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

void draw() {
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

    void update() {
        xoff += 0.02;
        yoff += 0.02;
    }
    void display() {
        for(float i = 1; i > 0; i -= 0.05) {
            push();
            translate(x, y);
            scale(abs(i - noise(xoff)), abs(i - noise(yoff)));

            rect(0, 0, w, w);
            pop();
        }
    }
}
