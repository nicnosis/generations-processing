Box[] boxes;
int N = 50;
float t;

void setup() {
    //display stuff
    size(1080, 1080);
    smooth(8);
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

void draw() {
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
    void update() {
        xoff += 0.01;
        twist += 0.01;
        xjerk = map(noise(xoff), 0, 1, -20, 20);
        y--;
        scl += 0.01;
        life--;
    }
    void display() {
        push();
        rotate(PI/6);
        translate(x + xjerk, y);
        rotate(t + noise(twist)*2*PI);
        scale(scl);
        rect(0, 0, w, w);
        pop();
    }

}
