PImage img;
boolean onetime = true;
int[][] values1, values2;
float angle;
float size = 4;
float count = 0; //floatやないとダメ見たい
int speed = 10;
int pix = 4;
Boolean tran = true;
int lerpDiv = 12;

float eyeX, eyeY, eyeZ, centerX, centerY, centerZ;

String pPath1 = "D:\\09_music_create\\03_PICTURE\\demo1.png";
String pPath2 = "D:\\09_music_create\\03_PICTURE\\demo2.png";

ArrayList<Circle> circles1 = new ArrayList<Circle>();    
ArrayList<Circle> circles2 = new ArrayList<Circle>();    
ArrayList<Circle> morphs = new ArrayList<Circle>(); 

void setup() {
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

void draw() {
  eyeX += 1;
  camera(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, 0, 1, 0);
  
   //ファイル名
   String root = "D:\\09_music_create\\03_PICTURE\\99_Render\\";
   String fileName = "52_Pixcel-30_" + "c_" + count + "_######.png";
   String outP = root + "52_Pixcel\\" + "6\\" + fileName;
   
  //add----
  print(count + ";");
  if (count >= 1800) noLoop();
  //----
  
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

int[][] imgLoad (String path){
  img = loadImage(path);
  img.loadPixels();
  
  int[][] values = new int[width][height];
  int[][] aPixels = new int[width][height];
  
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

void Calc (ArrayList<Circle> circles, int[][] values1, int[][] values2) {
  
  circles.clear();
  
    // Display the image mass
  for (int i = 0; i < img.height; i += pix) {
    for (int j = 0; j < img.width; j += pix) {
      
      float sizeR = 0.01; //*sin(count/1000);
      
      float lerpC = count/lerpDiv;
      float lerpVal;
      
      if (tran) lerpVal= lerp(values1[j][i], values2[j][i], lerpC) / 2;
      else lerpVal= lerp(values2[j][i], values1[j][i], lerpC) / 2;
      
      if ((lerpVal == values2[j][i]) || (lerpVal == values1[j][i])) tran = !tran;
      
      push(); //これを入れないと点が一つしかでてこない★ 加えて回転しなくなったな★
      
      Color colAdd = new Color(j, i, lerpVal, 80);           
       PVector location = new PVector(j-img.width/2, i-img.height/2, lerpVal); //*10*sin(count/speed));                          
       //int l = 2;
                                  
       Circle cAdd = new Circle(location,sizeR,colAdd); //, l);                          
       circles.add(cAdd);
        
      pop();
    }
  }
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