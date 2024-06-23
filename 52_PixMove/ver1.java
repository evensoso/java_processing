/**
 * Extrusion. 
 */

PImage img;
boolean onetime = true;
int[][] aPixels, aPixels2;
int[][] values, values1, values2;
float angle;
float size = 10;
float count = 1; //floatやないとダメ見たい

float eyeX;
float eyeY;
float eyeZ;
float centerX;
float centerY;
float centerZ;

String pPath1 = "D:\\09_music_create\\03_PICTURE\\demo1.png";
String pPath2 = "D:\\09_music_create\\03_PICTURE\\demo2.png";

int speed = 10;

 

void setup() {
 size(1080, 1080, P3D);
  
 eyeX = (width/2.0)-1000;
 eyeY = (height/2.0)-200;
 eyeZ = (height/2.0) / tan(PI*30.0 / 180.0) + 1200;
 centerX = width/2.0;
 centerY = height/2.0;
 centerZ = 0;
  
  aPixels = new int[width][height];
  values = new int[width][height];
  
  aPixels2 = new int[width][height];
  values2 = new int[width][height];
  noFill();

  values1 = imgLoad(pPath1);
  //values2 = imgLoad(pPath2);
  
  //noLoop();
}

void draw() {
  eyeX += 1;
  camera(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, 0, 1, 0);
  
   //ファイル名
   String root = "D:\\09_music_create\\03_PICTURE\\99_Render\\";
   String fileName = "52_Pixcel-30_" + "c_" + count + "_######.png";
   String outP = root + "52_Pixcel\\" + "1\\" + fileName;
   
  //add----
  print(count + ";");
  //if (count >= 1800) noLoop();
  //----
  
  background(0);
  translate(width/2, height/2, -height/2);
  scale(size);
  
  ArrayList<Circle> circles1 = new ArrayList<Circle>();    
  //ArrayList<Circle> circles2 = new ArrayList<Circle>();   
  
  //circles1.clear();

  Calc(circles1, values1);  
  //Calc(circles2, values2);  
  
  //add----
    saveFrame(outP);
    //----
    
    count += 1;
}

class Circle {              
    PVector pos;                                                   
    float size; //, life; 
    Color col;    

    Circle (PVector xyz, float s, Color c) { //, int l) {                          
        pos = xyz;                          
        col = c;                        
        size = s;                                                   
        //life = l;                          
    }                                                      
}

class Color {                           
                            
  float R, B, G, O;                           
                            
  Color (float r, float b, float g, float o) {                            
    R = r;                           
    B = b;  
    G = g;  
    O = o;                           
  }                           
                            
  void update() {                           
  }                          
                            
}      

void Calc (ArrayList<Circle> circles, int[][] values) {
    // Display the image mass
  for (int i = 0; i < img.height; i += 8) {
    for (int j = 0; j < img.width; j += 8) {
      
      float sizeR = 0.01; //*sin(count/1000);
      
      //push(); //これを入れないと点が一つしかでてこない★ 加えて回転しなくなったな★
      
      Color colAdd = new Color(j, i, values[j][i], 80);           
       PVector location = new PVector(j-img.width/2, i-img.height/2, -values[j][i]*10*sin(count/speed));                          
       //int l = 2;
                                  
       Circle cAdd = new Circle(location,sizeR,colAdd); //, l);                          
       circles.add(cAdd);
        
      //pop();
    }
  }
  
      //描画------------------------------------------                          
    for (Circle c : circles) { 
      push(); //これを入れないと点が一つしかでてこない★ 加えて回転しなくなったな★
      stroke(c.col.R, c.col.G, c.col.B, 80); //stroke(c.col.G, 80); //*sin(count/10*(i*j)));
      translate(c.pos.x, c.pos.y, c.pos.z);
      sphere(c.size);
      pop();
    }
}

int[][] imgLoad (String path){
  img = loadImage(path);
  img.loadPixels();
  
  //高さ
  for (int i = 0; i < img.height; i++) {
    
    //幅
    for (int j = 0; j < img.width; j++) {
      aPixels[j][i] = img.pixels[i*img.width + j];
      
      //この値で書いてるね
      values[j][i] = int(blue(aPixels[j][i]));
    }
  }
  
  return values;
}