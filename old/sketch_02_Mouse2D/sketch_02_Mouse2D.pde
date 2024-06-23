/**
 * Mouse 2D. 
 * 
 * Moving the mouse changes the position and size of each box. 
 */
 
 //add----
import oscP5.*;
//OSC receive
OscP5 oscP5;
// This value is set by the OSC event handler
//float amplitude = 0;
//int colorVal = 0;

// Declare a scaling factor
//float scale=6;
// Declare a smooth factor
//float smooth_factor=0.1;
// Used for smoothing
//float sum;
//----
 
void setup() {
  size(640, 360); 
  noStroke();
  rectMode(CENTER);
  
  //add----
  oscP5 = new OscP5(this,12000);
  //----
  
}

void draw() {
  background(51); 
  fill(255, 204);
  rect(mouseX, height/2, mouseY/2+10, mouseY/2+10);
  fill(255, 204);
  int inverseX = width-mouseX;
  int inverseY = height-mouseY;
  rect(inverseX, height/2, (inverseY/2)+10, (inverseY/2)+10);
}

//add----
void oscEvent(OscMessage theOscMessage) {
    float value = theOscMessage.get(0).floatValue();
    int value2 = theOscMessage.get(1).intValue();
    float value3 = theOscMessage.get(2).floatValue();
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
    //print("theOscMessageCheck: " + theOscMessage.checkAddrPattern("/info"));
    print("\n");
    
    if (theOscMessage.checkAddrPattern("/info")) {
    }
}
//----
