int w = 640;
int h = 640;
float[] pofloat1 = new float[2];
float[] pofloat2 = new float[2];
float d;
float r = 300;
float seed1;
float seed2;
float px, py, x, y;
int lifeTime = 800;

ArrayList<Line> Lines = new ArrayList<Line>();  

void setup() {
  size(640, 640);
  background(255);//white
  d = TWO_PI / 360;
  seed1 = frameCount;
  seed2 = frameCount;
}

void draw() {
  
  background(255);//white
  
  if(frameCount > 1800){
    noLoop();
  }
  
  
    float n1 = noise(seed1 + 0.02 * frameCount) * r;
  float n2 = noise(seed2 + 0.02 * frameCount) * r;
  px = pofloat1[0] = cos(d * frameCount) * n1;
  py = pofloat1[1] = sin(d * frameCount) * n1;
  x = pofloat2[0] = cos(d * frameCount + HALF_PI) * n2;
  y = pofloat2[1] = sin(d * frameCount + HALF_PI) * n2;
  
  Color colAdd = new Color(12, 100, 150, frameCount / 6);                            
      Line lAdd = new Line(px, py, x, y, lifeTime, colAdd); //lifeが聞いていない同じく変数か？
      Lines.add(lAdd);                          
  
  Lines.removeIf((Line l) ->{                          
        if(l.life <= 0) {                          
            return true;                          
        } else {                          
            return false;                          
        }                          
    });  
    
  //描画------------------------------------------                          
    for (Line l : Lines) {      
      push();
  translate(w / 2, h / 2);

  stroke(l.col.O);
  
  line(l.px, l.py, l.x, l.y);
  pop();   
  l.life -= 1;     
    }  
    
  
  
  //add----
  String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\04\\2\\Penrose-30_######.png";
  saveFrame(outP);
  //----
}

class Line {       
                        
                       
    float px, py, x, y;
    int life;       
    Color col;

    Line (float PX, float PY, float X,float Y,int li, Color c) {                          
        px = PX;       
        py = PY;       
        x = X;       
        y = Y;                                                     
        life = li;       
        col = c;
    }                               
                            
    void update() {                           
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
