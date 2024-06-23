import oscP5.*;
//OSC receive
OscP5 oscP5;
// This value is set by the OSC event handler
float amplitude = 0;
float colorVal = 0;
float regVal = 0;

// Declare a scaling factor
float scale=6;
// Declare a smooth factor
float smooth_factor=0.1;
// Used for smoothing
float sum;
void setup() {
    //fullScreen();
    size(900,540);
    rectMode(CENTER);
    
    // Initialize an instance listening to port 12000
    oscP5 = new OscP5(this,12000);
    
}
void draw() {
    background(0);
    // smooth the amplitude data by the smoothing factor
    sum += (amplitude - sum) * smooth_factor;
    // scaled to height/2 and then multiplied by a scale factor
    float amp_scaled=sum*(height/2)*scale;
    //float mappedColorR = map(colorVal * scale, 0, 1, 0, 255);
    float mappedColorG = map(colorVal/200, 0, 1, 0, 255);
    float mappedColorB = map(colorVal/250, 0, 1, 0, 255);
    print("mappedColorG: " + mappedColorG);
    //draw a dynamically-sized/colored circle
    //noStroke();
    fill(100, mappedColorG, 55);
    
    //noFill();
    noStroke();
    rect(width/2, height/2, colorVal/2, colorVal/2);
    fill(100, mappedColorB, mappedColorB);
    arc(width/2, height/2, colorVal, colorVal, HALF_PI/regVal, PI);
    
    for (int i = 0; i < 5; i++) {
      fill(100, mappedColorG/i, 55);
      ellipse(width/2, height/2, amp_scaled / i, amp_scaled / i);
      
    }
    
}
void oscEvent(OscMessage theOscMessage) {
    float value = theOscMessage.get(0).floatValue();
    int value2 = theOscMessage.get(1).intValue();
    int value3 = theOscMessage.get(2).intValue();
    float value4 = theOscMessage.get(3).floatValue();
    float value5 = theOscMessage.get(4).floatValue();
    //print("amplitude: " + value);
    print("theOscMessage: " + theOscMessage);
    print("\n");
    print("value: " + value);
    print("value2: " + value2);
    print("value3: " + value3);
    print("value4: " + value4);
    print("value5: " + value5);
    print("\n");
    print("theOscMessageCheck: " + theOscMessage.checkAddrPattern("/info"));
    print("\n");
    
    if (theOscMessage.checkAddrPattern("/info")) {
        if (value > 0.02) {
            amplitude = value/1; //adjust
            
        } else {
            amplitude = 0.0;
        }
        
        colorVal = value2;
        regVal = value3/1.5;
    }
}
