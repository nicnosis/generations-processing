float t = 0; // theta
float h = 0; // height of line
void setup() {
    //display stuff
    size(720, 720);
    smooth(8);
    colorMode(RGB, 100);

}

void draw() {
    background(100);
    fill(0);
    rect(0, height/2, width, height/2);
    translate(0, height/2);

    line(0, 0, width, 0);
    for (int x = 0; x < width; x+= 10) {
        for (float scale = 1; scale < 6; scale += 0.5) {
            push();

            scale(scale, scale);
            h = sin(t + map(x, 0, width, 0, TWO_PI)) * 50;


            // stroke(0, map(scale, 6, 1, 0, 50));
            // line(x, 0, x, h);

            fill(map(h, -20, 20, 0, 100), map(abs(h), 0, 20, 0, 70));
            stroke(0);
            ellipse(x, h, 5, 5);
            pop();
        }
    }

    t += 0.02;


    // saveFrame("renders/iso####.png");
}
