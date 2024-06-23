/**
 * Space Junk  
 * by Ira Greenberg (zoom suggestion by Danny Greenberg).
 * 
 * Rotating cubes in space using a custom Cube class. 
 * Color controlled by light sources. Move the mouse left
 * and right to zoom.
 */

//add----
import oscP5.*;
//OSC receive
OscP5 oscP5;
//----

float pos;
float rot;
float col;
float back;
int addBool;

// Used for oveall rotation
float angle;

// Cube count-lower/raise to test performance
int limit = 500;

// Array for all cubes
Cube[] cubes = new Cube[limit];

void setup() {
  //add----
  oscP5 = new OscP5(this,12000);
  //----

  size(900, 900, P3D); 
  background(0); 
  noStroke();

  // Instantiate cubes, passing in random vals for size and postion
  for (int i = 0; i < cubes.length; i++){
    cubes[i] = new Cube(int(random(-10, 10)), int(random(-10, 10)), 
                        int(random(-10, 10)), int(random(-140, 140)), 
                        int(random(-140, 140)), int(random(-140, 140)));
  }
}

void draw(){
  background(back); 
  
  if (addBool == 1) {
    noFill();
    stroke(200);
  }
  else {
    fill(200);
    noStroke();
  }

  // Set up some different colored lights
  pointLight(51, 102, col, 65, 60, 100); 
  pointLight(200, 40, 60, -65, -60, -150);

  // Raise overall light in scene 
  ambientLight(70, 70, 10); 

  // Center geometry in display windwow.
  // you can changlee 3rd argument ('0')
  // to move block group closer(+) / further(-)
  translate(width/2, height/2, -200 + pos * 0.65);

  // Rotate around y and x axes
  rotateY(radians(angle));
  rotateX(radians(angle));

  // Draw cubes
  for (int i = 0; i < cubes.length; i++){
    cubes[i].drawCube();
  }
  
  // Used in rotate function calls above
  angle += rot*10;
}

//add----
void oscEvent(OscMessage theOscMessage) {
    float value = theOscMessage.get(0).floatValue();
    int value2 = theOscMessage.get(1).intValue();
    float value3 = theOscMessage.get(2).floatValue();
    float value4 = theOscMessage.get(3).floatValue();
    float value5 = theOscMessage.get(4).floatValue();
    
    back = value*100-10; //exp(value*100);
    rot = value;
    addBool = value2;
    pos = value5;
    col = value3*0.1;
    
    //print("amplitude: " + value);
    print("theOscMessage: " + theOscMessage);
    print("\n");
    print("value: " + value);
    print("value2: " + value2);
    print("value3: " + value3);
    print("value4: " + value4);
    print("value5: " + value5);
    print("\n");
    print("back: " + back);
    print("rot: " + rot);
    print("pos: " + pos);
    print("col: " + col);
    print("value5: " + value5);
    //print("theOscMessageCheck: " + theOscMessage.checkAddrPattern("/info"));
    print("\n");
    
    if (theOscMessage.checkAddrPattern("/info")) {
    }
}
//----
