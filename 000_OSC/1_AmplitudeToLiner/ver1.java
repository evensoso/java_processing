 * Linear Image. 
 * 
 * Click and drag mouse up and down to control the signal. 
 * Press and hold any key to watch the scanning. 
 */

PImage img;
int direction = 1;

float signal;
float amp;

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

Boolean check = false;


void setup() {
  size(640, 360);
  stroke(255);
  img = loadImage("sea.jpg");
  img.loadPixels();
  loadPixels();
  
  //add----
  oscP5 = new OscP5(this,12000);
  //----

}

void draw() {
  if (signal > img.height-1 || signal < 0) { 
    direction = direction * -1;
  }
  if (check == true) {
    signal = abs(mouseY % img.height);
  } 
  else {
    signal += (0.3*direction);
  }

  if (amp > 2.0) {
    set(0, 0, img);
    line(0, signal, img.width, signal);
  } 
  else {
    int signalOffset = int(signal)*img.width;
    for (int y = 0; y < img.height; y++) {
      arrayCopy(img.pixels, signalOffset, pixels, y*width, img.width);
    }
    updatePixels();
  }
}

//add----
void oscEvent(OscMessage theOscMessage) {
    float value = theOscMessage.get(0).floatValue();
    int value2 = theOscMessage.get(1).intValue();
    float value3 = theOscMessage.get(2).floatValue();
    float value4 = theOscMessage.get(3).floatValue();
    float value5 = theOscMessage.get(4).floatValue();
    
    value = value*exp(value);
    amp = value;
    
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
      if (value3 > 9.0){
        check = true;
      }
      else {
        check = false;
      }
    }
}
