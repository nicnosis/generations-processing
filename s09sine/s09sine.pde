int N = 300;
float spacing;
float y = 0;
float period;
float amp = 0;
float t = 0;
float w = 0;

void setup() {
    //display stuff
    size(400, 400);
    smooth(8);
    colorMode(RGB, 100);
    background(100);
    // noFill();
    stroke(0, 30);
    fill(0, 50);

    //setup vars
    spacing = width/N;
    period = width/4;
    amp = height/2;
}

void draw() {
    background(100);
    amp = (width/2 - mouseY);
    w = abs(width/2 - mouseX);
    for (float x = 0; x <= width; x += spacing) {
        t = TWO_PI*x/period;
        y = amp*sin(t) + width/2;

        push();
        translate(x, y);
        rotate(-cos(t));
        rotate(frameCount/100f);
        ellipse(-w, 0, 5, 5);
        ellipse(w, 0, 5, 5);
        rotate(PI + frameCount/100f);
        ellipse(-w, 0, 5, 5);
        ellipse(w, 0, 5, 5);
        // arc(0, 0, 20, height/2, 0, PI);
        pop();
    }
    // saveFrame("renders/iso####.png");
}
