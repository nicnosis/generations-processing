Orb[] orbs;
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

    orbs = new Orb[N];
    for (int i = 0; i < N; i++) {
        orbs[i] = new Orb(width/float(N) * i);
    }
}

void draw() {
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
    void update() {
        y++;
        if (y < height/2) {
            r = (random(1) > 0.9) ? r *= random(0.9, 0.99) : r;
        } else {
            r = (random(1) > 0.9) ? r *= random(1.01, 1.05) : r;
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
    void display() {
        stroke(value, alpha);
        ellipse(x, y, r*2, r*2);
        ellipse(x, height-y, r*2, r*2);
    }

}
