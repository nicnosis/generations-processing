int NUMSINES = 6; // how many of these things can we do at once?
float[] sines = new float[NUMSINES];
float rad;
int i;

float fund = 0.01; // the speed of the central sine
float ratio = 0.5; // what multiplier for speed is each additional sine?
float alpha = 20; // how opaque is the tracing system

boolean trace = false; // are we tracing?

void setup() {
    size(720, 720);
    smooth();
    colorMode(RGB, 100);
    rad = height / 4f; // compute radius for central circle
    background(100); // clear the screen
    strokeWeight(2);

    for (int i = 0; i < sines.length; i++) {
        sines[i] = PI; // start EVERYBODY facing NORTH
    }
}

void draw() {
    noFill();
    stroke(0, 20);

    // MAIN ACTION
    push(); // start a transformation matrix
    translate(width / 2, height / 2); // move to middle of screen

    for (int i = 0; i < sines.length; i++) {
        float erad = 0; // radius for small "point" within circle... this is the 'pen' when tracing
        // setup for tracing

        float radius = rad / (i + 1); // radius for circle itself
        rotate(sines[i]); // rotate circle
        // ellipse(0, 0, radius * 2, radius * 2); // if we're simulating, draw the sine
        push(); // go up one level
        translate(0, radius); // move to sine edge

        arc(10, 0, radius, radius, PI, TAU);

        pop(); // go down one level
        translate(0, radius); // move into position for next sine
        sines[i] = (sines[i] + (fund + (fund * i * ratio))) % TWO_PI; // update angle based on fundamental
    }

    pop(); // pop down final transformation

}

void keyReleased() {
    if (key==' ') {
        trace = !trace;
        background(100);
    }
}
