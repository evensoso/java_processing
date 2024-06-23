
import oscP5.*;
//import netP5.*;
OscP5 o;
//NetAddress myRemoteLocation;


//各OSCにて和音の場合複数のNoteで届く
// note: MIDIの番号
// velocity: 強度
int velocity1, velocity2, velocity3, velocity4, velocity5, velocity6, velocity7, velocity8 = 0;
int note1, note2, note3, note4, note5, note6, note7, note8  = 0;

int pos;
int rot;
int col;
int back;
int addBool;

// Used for oveall rotation
float angle;

// Cube count-lower/raise to test performance
int limit = 500;

// Array for all cubes
Cube[] cubes = new Cube[limit];

//float audio1, audio2, audio3, audio4, audio5, audio6, audio7, audio8  = 0;
float eyeX, eyeY, eyeZ, centerX, centerY, centerZ;

void setup() {

  //size(640, 360);
  //stroke(255);
  //background(0);

  o = new OscP5(this, 10030);
  //myRemoteLocation = new NetAddress("127.0.0.1", 2346);
  
    size(900, 900, P3D); 
  background(0); 
  noStroke();

  // Instantiate cubes, passing in random vals for size and postion
  for (int i = 0; i < cubes.length; i++){
    cubes[i] = new Cube(int(random(-10, 100)), int(random(-10, 100)), 
                        int(random(-10, 100)), int(random(-1400, 1400)), 
                        int(random(-1400, 1400)), int(random(-1400, 1400)));
  }
  
  eyeX = (width/2.0)-200;
 eyeY = (height/2.0)-600;
 eyeZ = (height/2.0) / tan(PI*30.0 / 180.0) +200;
 centerX = width/2.0+200;
 centerY = height/2.0;
 centerZ = 0;

}


void draw() {
  //eyeZ -= 10;

  camera(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, 0, 1, 0);


  background(color(back, 0, 0)); 
  
  /*
  if (addBool == 1) {
    noFill();
    stroke(200);
    noStroke();
  }
  else {
    fill(200);
    */
    
    noFill();
    stroke(200);
  //}

  // Set up some different colored lights
  pointLight(51, 102, col, 65, 60, 100); 
  pointLight(200, 40, 60, -65, -60, -150);

  // Raise overall light in scene 
  ambientLight(70, 70, 10); 
  
  translate(width/2, height/2, -200 + pos * 0.65);

  rotateY(radians(angle));
  rotateX(radians(angle));

  for (int i = 0; i < cubes.length; i++){
    cubes[i].drawCube();
  }
  
  // Used in rotate function calls above
  angle += rot*10;
}


void oscEvent(OscMessage theMsg) {
  
  theMsg.print();
  
  /*
  //--Audio-------------------------------------------------
  if (theMsg.checkAddrPattern("/audio1")==true) {
    audio1 = theMsg.get(0).floatValue();
    audio1 = int(map(audio1, 0, 127, 0, 60));
  }
  if (theMsg.checkAddrPattern("/audio2")==true) {
      audio2 = theMsg.get(0).floatValue();
      audio2 = int(map(audio2, 0, 127, 0, 60));
  }
  if (theMsg.checkAddrPattern("/audio3")==true) {
      audio3 = theMsg.get(0).floatValue();
      audio3 = int(map(audio3, 0, 127, 0, 60));
  }

  if (theMsg.checkAddrPattern("/audio4")==true) {
      audio4 = theMsg.get(0).floatValue();
      audio4 = int(map(audio4, 0, 127, 0, 60));
  }
  */

  //--MIDI-------------------------------------------------
  if(theMsg.checkAddrPattern("/Note1")==true) {
    note1 = theMsg.get(0).intValue();
    //note1 = map(sq(note1), 1, sq(127), 0.05, 0.5);
    note1 = int(map(note1, 0, 127, 0, 60));
  }

  if(theMsg.checkAddrPattern("/Note2")==true) {
      note2 = theMsg.get(0).intValue();
      note2 = int(map(note2, 0, 127, 0, 60));
  } 

  if(theMsg.checkAddrPattern("/Note3")==true) {
      note3 = theMsg.get(0).intValue();
      note3 = int(map(note3, 0, 127, 0, 60));
  }

  if(theMsg.checkAddrPattern("/Note4")==true) {
      note4 = theMsg.get(0).intValue();
      note4 = int(map(note4, 0, 127, 0, 60));
  } 

  if(theMsg.checkAddrPattern("/Note5")==true) {
      note5 = theMsg.get(0).intValue();
      note5 = int(map(note5, 0, 127, 0, 60));
  }

  if(theMsg.checkAddrPattern("/Note6")==true) {
      note6 = theMsg.get(0).intValue();
      note6 = int(map(note6, 0, 127, 0, 60));
  } 

  if(theMsg.checkAddrPattern("/Note7")==true) {
      note7 = theMsg.get(0).intValue();
      note7 = int(map(note7, 0, 127, 0, 60));
  }

  if(theMsg.checkAddrPattern("/Note8")==true) {
      note8 = theMsg.get(0).intValue();
      note8 = int(map(note8, 0, 127, 0, 60));
  } 

  if(theMsg.checkAddrPattern("/Velocity1")==true) {
      velocity1 = theMsg.get(0).intValue();
      velocity1 = int(map(velocity1, 0, 127, 0, 60));
  }
  
  if(theMsg.checkAddrPattern("/Velocity2")==true) {
      velocity2 = theMsg.get(0).intValue();
      velocity2 = int(map(velocity2, 0, 127, 0, 60));
  }  
  
  if(theMsg.checkAddrPattern("/Velocity3")==true) {
      velocity3 = theMsg.get(0).intValue();
      velocity3 = int(map(velocity3, 0, 127, 0, 60));
  }  
  
    if(theMsg.checkAddrPattern("/Velocity4")==true) {
      velocity4 = theMsg.get(0).intValue();
      velocity4 = int(map(velocity4, 0, 127, 0, 60));
  }
  
  if(theMsg.checkAddrPattern("/Velocity5")==true) {
      velocity5 = theMsg.get(0).intValue();
      velocity5 = int(map(velocity5, 0, 127, 0, 60));
  }  
  
  if(theMsg.checkAddrPattern("/Velocity6")==true) {
      velocity6 = theMsg.get(0).intValue();
      velocity6 = int(map(velocity6, 0, 127, 0, 60));
  }  
  
  if(theMsg.checkAddrPattern("/Velocity7")==true) {
      velocity7 = theMsg.get(0).intValue();
      velocity7 = int(map(velocity7, 0, 127, 0, 60));
  }  
  
  if(theMsg.checkAddrPattern("/Velocity8")==true) {
      velocity8 = theMsg.get(0).intValue();
      velocity8 = int(map(velocity8, 0, 127, 0, 60));
  }
  back = note1; //*100-10; //exp(value*100);
    rot = velocity1/10;
    addBool = note1;
    pos = velocity1;
    col = int(note2 * 0.1);
    eyeZ = velocity1*100;
  
  
}

/*float log10 (int x) {
  return (log(x) / log(10));
}*/


class Cube {

  // Properties
  int w, h, d;
  int shiftX, shiftY, shiftZ;

  // Constructor
  Cube(int w, int h, int d, int shiftX, int shiftY, int shiftZ){
    this.w = w;
    this.h = h;
    this.d = d;
    this.shiftX = shiftX;
    this.shiftY = shiftY;
    this.shiftZ = shiftZ;
  }

  // Main cube drawing method, which looks 
  // more confusing than it really is. It's 
  // just a bunch of rectangles drawn for 
  // each cube face
  void drawCube(){
    beginShape(QUADS);
    // Front face
    vertex(-w/2 + shiftX, -h/2 + shiftY, -d/2 + shiftZ); 
    vertex(w + shiftX, -h/2 + shiftY, -d/2 + shiftZ); 
    vertex(w + shiftX, h + shiftY, -d/2 + shiftZ); 
    vertex(-w/2 + shiftX, h + shiftY, -d/2 + shiftZ); 

    // Back face
    vertex(-w/2 + shiftX, -h/2 + shiftY, d + shiftZ); 
    vertex(w + shiftX, -h/2 + shiftY, d + shiftZ); 
    vertex(w + shiftX, h + shiftY, d + shiftZ); 
    vertex(-w/2 + shiftX, h + shiftY, d + shiftZ);

    // Left face
    vertex(-w/2 + shiftX, -h/2 + shiftY, -d/2 + shiftZ); 
    vertex(-w/2 + shiftX, -h/2 + shiftY, d + shiftZ); 
    vertex(-w/2 + shiftX, h + shiftY, d + shiftZ); 
    vertex(-w/2 + shiftX, h + shiftY, -d/2 + shiftZ); 

    // Right face
    vertex(w + shiftX, -h/2 + shiftY, -d/2 + shiftZ); 
    vertex(w + shiftX, -h/2 + shiftY, d + shiftZ); 
    vertex(w + shiftX, h + shiftY, d + shiftZ); 
    vertex(w + shiftX, h + shiftY, -d/2 + shiftZ); 

    // Top face
    vertex(-w/2 + shiftX, -h/2 + shiftY, -d/2 + shiftZ); 
    vertex(w + shiftX, -h/2 + shiftY, -d/2 + shiftZ); 
    vertex(w + shiftX, -h/2 + shiftY, d + shiftZ); 
    vertex(-w/2 + shiftX, -h/2 + shiftY, d + shiftZ); 

    // Bottom face
    vertex(-w/2 + shiftX, h + shiftY, -d/2 + shiftZ); 
    vertex(w + shiftX, h + shiftY, -d/2 + shiftZ); 
    vertex(w + shiftX, h + shiftY, d + shiftZ); 
    vertex(-w/2 + shiftX, h + shiftY, d + shiftZ); 

    endShape(); 

    // Add some rotation to each box for pizazz.
    rotateY(radians(1));
    rotateX(radians(1));
    rotateZ(radians(1));
  }
}
