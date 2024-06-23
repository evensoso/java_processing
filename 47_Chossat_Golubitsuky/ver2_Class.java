float x = 0.1, y = 0.1;
 
//static final float a = -1.0, b = 0.05, c = 2.275,  d = -0.5;
//static final float a =  1.0, b = 0.0,  c = - 2.25, d = 0.2;
//static final float a =  1.0, b = 0.0,  c = - 1.9,  d = 0.4;
//static final float a = -1.0, b = 0.1,  c = 1.52,   d = -0.8;
//static final float a = -1.0, b = 0.1,  c = 1.6,    d = -0.8;
//static final float a =  2.,  b = -0.2, c = - 1.75, d = 1.;
float a = -2.0, b = 0, c = 2.6, d = -0.5;

ArrayList<Point> points = new ArrayList<Point>();     
int lifeTime = 120;

//add----
 float count = 1.0;
   PImage img;
 
void setup() {
  size(800, 800);
  blendMode(ADD);
  background(0);
  stroke(124, 155, 255, 100);
}
 
void draw() {
  
  background(0);
  
   //ファイル名
   String root = "D:\\09_music_create\\03_PICTURE\\99_Render\\";
   String fileName = "47_Chossat_Golubitsuky-30_" + "c_" + count + "_######.png";
   String outP = root + "47_Chossat_Golubitsuky\\" + "2\\" + fileName;
   
    //add----
  print(count);
  //background(0.0, 0.0, 90.0, 100.0);
  //if (img != null)image(img, 0, 0); //前のイメージを残す
  //if (count % 6 == 0) background(0.0, 0.0, 90.0, 100.0);
  if (count >= 1800) noLoop();
  //----
 
  float _x, _y;
  float A;
 
  for (int i = 0; i < 1600; i++) {
 
    A = a * (x * x + y * y) + b * x * (x * x - 3 * y * y) + c;
    _x = A * x + d * (x * x - y * y);
    _y = A * y - 2 * d * x * y;
    
    int l = lifeTime;
 
    //point(_x * 200 + width/2, - _y * 200 + height/2);
    
    PVector location = new PVector(_x, _y, 0);                          
    //PVector va = new PVector(0, 0, 0);                          
    //Point pAdd = new Point(location,va,cS,colAdd, l);      
    Point pAdd = new Point(location,l);      
    points.add(pAdd);                          
 
    x = _x;
    y = _y;
 
  }
  
  points.removeIf((Point p) ->{                          
        if(p.life<=0) {                          
            return true;                          
        } else {                          
            return false;                          
        }                          
    }); 
    
  //描画------------------------------------------                          
    for (Point c : points) {   
           
        point(c.pos.x * 200 + width/2, - c.pos.y * 200 + height/2);                               
        c.life -= 1;        
    }  
  
  //add----
    saveFrame(outP);
    //----
    
    count += 1;
    b += 0.01;
　//----
 
}

class Point {       
                        
    PVector pos;                          
    //PVector vel ;                          
    float life;                           
    //Color col;                          

    Point (PVector xyz, int l) {                          
        pos = xyz;                                                
        life = l;                          
    }                               
                            
    void update() {                           
    }                          
                            
}                          
                          
class Color {                           
                            
  float R, B, O;                           
                            
  Color (float r, float b, float g, float o) {                            
    R = r;                           
    B = b;                                                    
    O = o;                           
  }                           
                            
  void update() {                           
  }                          
                            
}