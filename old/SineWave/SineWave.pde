/**
 * Sine Wave
 * by Daniel Shiffman.  
 * 
 * Render a simple sine wave. 
 */
 
int xspacing = 1;   // How far apart should each horizontal location be spaced
int w;              // Width of entire wave
int ramdom;  
float onOff;

float theta = 0.0;  // Start angle at 0
float amplitude = 75.0;  // Height of wave
float period = 200.0;  // How many pixels before the wave repeats
float dx;  // Value for incrementing X, a function of period and xspacing
float[] yvalues;  // Using an array to store height values for the wave

float speed = 0.0;
float highW = 0.0;

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
  size(955, 1040);
  w = width+16;
  dx = (TWO_PI / period) * xspacing;
  yvalues = new float[w/xspacing];
  
  //add----
  oscP5 = new OscP5(this,12000);
  //----
}

void draw() {
  background(0);
  calcWave();
  renderShape();
}

void calcWave() {
  // Increment theta (try different values for 'angular velocity' here
  theta += 0.06;// * speed;

  // For every x value, calculate a y value with sine function
  float x = theta;
  for (int i = 0; i < yvalues.length; i++) {
    yvalues[i] = sin(x)*amplitude*highW;
    x+=dx;
  }
}

void renderShape() {
  noStroke();
  fill(255);
  // A simple way to draw the wave with an ellipse at each location
  
  pushMatrix();
  for (int x = 0; x < yvalues.length; x++) {
    stroke(120,ramdom/2+x,0,ramdom);
    ellipse(x*xspacing, (height/2 + yvalues[x])/100 * x, 2, 2);
    ellipse(x*xspacing, (height/2 + yvalues[x]), 3, 3);
  }
  popMatrix();
  
  noFill();
  stroke(255);
  ellipse(width/2,height/2, speed*100, speed*100);
  ellipse(width/2,height/2, speed*1*ramdom, speed*ramdom/2);
  stroke(255,ramdom);
  
  pushMatrix();
  for (int x = 0; x < 5; x++) {
    translate(-100,100);
    stroke(40,ramdom+100,ramdom,ramdom);
    line(0, 0, ramdom*x+1000, ramdom*x*ramdom/100);
  }
  popMatrix();

  stroke(255,ramdom+10);
  translate(width/2-300,height/2-300);
  rect(0, 0, 600,600);
  
  
  /*
  rotate(PI/(4 * random(0, 50)));
  translate(width/2,-600);
  rect(0, 0, 1000,1000);
  */

}


//add----
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
    //print("theOscMessageCheck: " + theOscMessage.checkAddrPattern("/info"));
    print("\n");
    
    if (theOscMessage.checkAddrPattern("/info")) {
      speed=value*10;
      ramdom=0;
      
      highW=0; //=value2/160;
      onOff=0.0;
      
      if (value> 0.2) {
        highW=value*10;; //=value2/160;
        ramdom=value2;
        onOff=1.0;
      }
    }
}
//----
