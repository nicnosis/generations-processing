Cloud[] clouds;
int N = 100;

void setup() {
    //display stuff
    size(1080, 1080);
    smooth(8);
    rectMode(CENTER);
    colorMode(RGB, 100);
    strokeWeight(1);
    noFill();
    background(100);

    clouds = new Cloud[N];
    for (int i = 0; i < N; i++) {
        clouds[i] = new Cloud();
    }
}

void draw() {
    for (Cloud c : clouds) {
        c.update();
        if (c.life > 0) c.display();
    }
    saveFrame("renders/iso####.png");
}

class Cloud {
    float x, y, proportion, vx, vy, w, h, life, seed;
    boolean hasFill;
    color stroke;


    Cloud() {
        seed = random(1000);
        x = random(width/2 - 20);
        // hasFill = (x > width/2) ? true : false;
        y = random(height);
        proportion = random(0.25, 1);
        vx = random(-.2, .2);
        vy = random(-.5, -1);
        w = random(10, 50);
        h = random(10, 50);
        life = random(250, 1000);
    }
    void update() {
        y += vy;
        // x += vx;
        proportion += random(-0.05, 0.05);
        proportion = constrain(proportion, 0.25, 1);
        life--;
        stroke = color(0, noise(frameCount/1000 + seed) * 50);
    }

    void display() {
        stroke(stroke);
        noFill();
        push();
        translate(x, y);
        scale(proportion);
        rotate(-PI / 6);

        scale(1, .86062); // scale vertical 86.062%
        shearX(PI / 6); // skew 30 degrees
        rotate(noise(frameCount/100f) * PI * 4);
        rect(0, 0, w, h);

        pop();
        push();
        fill(100);
        translate(width - x, y);
        scale(proportion);
        rotate(PI / 6);
        scale(1, .86062); // scale vertical 86.062%
        shearX(-PI / 6); // skew 30 degrees
        rotate(-noise(frameCount/100f) * PI * 4);
        rect(0, 0, -w, h);
        pop();

    }

}
