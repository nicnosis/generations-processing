Bug[] bugs;
int N = 100;

void setup() {
    //display stuff
    size(1080, 1080);
    smooth(8);
    colorMode(RGB, 100);
    strokeWeight(1);
    stroke(0, 10);
    noFill();
    background(100);

    bugs = new Bug[N];
    for (int i = 0; i < N; i++) {
        bugs[i] = new Bug();
    }
}

void draw() {
    for (Bug c : bugs) {
        c.update();
        c.display();
    }
    // saveFrame("renders/iso####.png");
}

class Bug {
    float x, y;

    Bug() {
        x = random(width);
        y = random(width);
    }
    void update() {
        x += random(-2, 2);
        y += random(-2, 2);
    }
    void display() {
        point(x, y);
    }

}
