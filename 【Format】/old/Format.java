float count = 0; //floatやないとダメ見たい

PImage img;
boolean onetime = true;
int[][] values1, values2;
float angle;
float size = 4;

int speed = 10;
int pix = 4;
Boolean tran = true;
int lerpDiv = 12;

float eyeX, eyeY, eyeZ, centerX, centerY, centerZ;

String pPath1 = "D:\\09_music_create\\03_PICTURE\\demo1.png";
String pPath2 = "D:\\09_music_create\\03_PICTURE\\demo2.png";

ArrayList<Circle> morphs = new ArrayList<Circle>(); 

//OSC接続
//add----
import oscP5.*;
OscP5 oscP5;
//----

void setup() {
  //add----
  oscP5 = new OscP5(this,12000);
  //----

 size(1080, 1080, P3D);
  
 eyeX = (width/2.0)-1000;
 eyeY = (height/2.0)-200;
 eyeZ = (height/2.0) / tan(PI*30.0 / 180.0) + 1200;
 centerX = width/2.0;
 centerY = height/2.0;
 centerZ = 0;
 
 //camera(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, 0, 1, 0);
  
  values2 = new int[width][height];
  noFill();

  values1 = imgLoad(pPath1);
  values2 = imgLoad(pPath2);
    
  //circles1.clear();
  //Calc(circles1, values1);  
  //Calc(circles2, values2);
  //両方正しく描写はされている★
  
  if (circles1 == circles2) print("Same");
  
  //noLoop();
  
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

//ファイル出力
void draw() {
  background(0);
  ds.render();
  //add----
  String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\01\\Penrose-30_######.png";
  saveFrame(outP);
  //----

  eyeX += 1;
  camera(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, 0, 1, 0);
  

  
  background(0);
  translate(width/2, height/2, -height/2);
  scale(size);
  
  Calc(circles1, values1, values2); 
  
  //morphs = CalcNew(circles1, circles2);
  
  Paint(circles1);
  
  //add----
    saveFrame(outP);
    //----
    
    count += 1;

}



void setup() {

}



void Paint(ArrayList<Circle> circles){
    //描画------------------------------------------                          
    for (Circle c : circles) { 
      push(); //これを入れないと点が一つしかでてこない★ 加えて回転しなくなったな★
      stroke(c.col.R, c.col.G, c.col.B, 80); //stroke(c.col.G, 80); //*sin(count/10*(i*j)));
      translate(c.pos.x, c.pos.y, c.pos.z);
      sphere(c.size);
      pop();
    }
}

class Circle {              
    PVector pos;                                                   
    float size; //, life; 
    Color col;    

    Circle (PVector xyz, float s, Color c) { //, int l) {                          
        pos = xyz; col = c; size = s; //life = l;                          
    }                                                      
}

class Color {                           
                            
  float R, B, G, O;                           
                            
  Color (float r, float b, float g, float o) {                            
    R = r; B = b; G = g; O = o;                           
  }                           
                            
  void update() {                           
  }                          
                            
} 