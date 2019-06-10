float noiz = 0;
Band bands[];
int N = 350;

void setup() {
    size(1080, 1080);
    smooth(8);

    colorMode(RGB, 100);
    noFill();

    bands = new Band[N];
    for (int i = 0; i < N; i++) {
        bands[i] = new Band(i);
    }
}

void draw() {
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

void windowResized() {
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

void keyPressed() {
    if(key == ' ') {

      setup();
    }
}
