/**
 * Distance 2D. 
 * 
 * Move the mouse across the image to obscure and reveal the matrix.  
 * Measures the distance from the mouse to each square and sets the
 * size proportionally. 
 */

float max_distance;
float count;
float count2;
float rad;

float bool1;
int bool2;
float check;

//add----
import oscP5.*;
//OSC receive
OscP5 oscP5;
//----

PImage img1, img2;

void setup() {
  size(900, 1000); 
  frameRate(24);
  noStroke();
  max_distance = dist(0, 0, width, height);
  img1 = loadImage("C:\\Users\\shohe\\Downloads\\demo.png");

  
  //add----
  oscP5 = new OscP5(this,12000);
  //----

}

void draw() {
  background(0);

  rad = radians(count)+ PIE;
  count2 = exp(rad)*sin(rad) * 1;
  //print(count2);print("\n");
  
  //if (count2 < -100) {
  if ((bool1 > 0.3)&&(count2 < -20)) {
    count = 0;
  }
  else{
    count += 2;
  }
  
  if (bool2 == -1){
    fill(100);
    image(img1, -600, -200);
  }
  else{
    fill(255);
  }
  
  for(int i = 0; i <= width; i += 20) {
    for(int j = 0; j <= height; j += 20) {
      
      float size = dist(width/2, height/2, i, j);
      size = size/max_distance * 40;
      
      ellipse(i, j, size+count2, size+count2);
      
    }
  }
}

//add----
void oscEvent(OscMessage theOscMessage) {
    float value = theOscMessage.get(0).floatValue();
    int value2 = theOscMessage.get(1).intValue();
    //float value3 = theOscMessage.get(2).floatValue();
    //float value4 = theOscMessage.get(3).floatValue();
    float value5 = theOscMessage.get(4).floatValue();
    
    bool1 = value;
    bool2 = value2;
    check = value5;
    
    //print("amplitude: " + value);
    //print("theOscMessage: " + theOscMessage);
    //print("\n");
    print("value: " + value);
    print("value2: " + value2);
    //print("value3: " + value3);
    //print("value4: " + value4);
    print("value5: " + value5);
    print("\n");
    print("bool1: " + bool1);
    print("bool2: " + bool2);
    //print("theOscMessageCheck: " + theOscMessage.checkAddrPattern("/info"));
    print("\n");
    
    if (theOscMessage.checkAddrPattern("/info")) {
    }
}
//----
