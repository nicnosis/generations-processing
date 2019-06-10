Bug[] bugs;
int N = 200;

void setup() {
    //display stuff
    // size(1080, 1080);
    size(1080, 1080);
    smooth(8);
    colorMode(RGB, 100);
    strokeWeight(1);
    stroke(0, 10);
    noFill();
    background(0);

    bugs = new Bug[N];
    for (int i = 0; i < N; i++) {
        bugs[i] = new Bug();
    }
}

void draw() {
    noFill();
    for (Bug b : bugs) {
        if (b.t > 0) {
            b.update();
            b.display();
        }
    }

    // saveFrame("renders/iso####.png");
}

class Bug {
    float w, h;
    PVector origin;
    float x, y;
    float xoff, yoff;
    int t = 6000;

    Bug() {
        w = random(50, 100);
        h = random(100, 200);
        origin = new PVector(random(-200, width), random(height));
        // x = random(width);
        // y = random(height);
        xoff = random(0, 100);
        yoff = random(0, 100);
    }
    void update() {
        t--;
        xoff += 0.01;
        yoff += 0.01;
        x = noise(xoff)*w;
        x = map(noise(xoff), 0, 1, -w, w);
        // y = random(height/2f, height);
        y = random(0, h);
        // y += 100 * sin(map(x, 0, w, PI, 0));
    }
    void display() {
        strokeWeight(map(y, 0, h, 2.5, 1));
        stroke(100, map(y, 0, h, 80, 0));
        push();
        rotate(-PI/6);
        translate(-200, 0);
        translate(origin.x, origin.y);
        point(x, y);

        if (random(1) > 0.995) {
            fill(100, random(80));
            strokeWeight(1);
            float rndm = random(25);
            ellipse(x, y, rndm, rndm);
            int times = floor(random(5, 25));
            for (int i = 0; i < times; i++) {
                float xx = random(-5, 5);
                line(xx, y, xx, y + random(100));
            }
        }
        pop();
    }

}
