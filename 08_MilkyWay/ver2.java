int u, v, count;                          

ArrayList<Line> Lines = new ArrayList<Line>();       
int lifeTime = 2;


void setup() {                          
  size(1080,1920);                          
  background(0);                          
  u = width/2;                          
  v = height/2;                          
  count = 0;                          
}                          
void draw() {                          
                          
  strokeWeight(0.25);                          
  stroke(255, 10);                          
                            
  if (count > 1800){                          
    noLoop();                          
  }    
  
  for(float i = 0; i < 100 * PI; i += PI / 24){                          
    float px = random(width) * cos(i) - random(width) * sin(i) - u * cos(i) + v * sin(i) + u;                          
    float py = random(height) * sin(i) + random(height) * cos(i) - u * sin(i) - v * cos(i) + v;                          
    float x = (300 * cos(i) - 300 * sin(i) - u * cos(i) + v * sin(i) + u)*sin(count);                          
    float y = 300 * sin(i) + 300 * cos(i) - u * sin(i) - v * cos(i) + v;       
    
    Color colAdd = new Color(12-10*x, 100-x, 150-x, i-x);                          

    //line(px, py, x, y);                          
    //print(px);   
      Line lAdd = new Line(px, py, x, y, lifeTime, colAdd); //lifeが聞いていない同じく変数か？
      Lines.add(lAdd);                          
        //Line(cX, cY, cO);   
  }
    Lines.removeIf((Line l) ->{                          
        if((l.life <= 0) || (l.px < 200)|| (l.py < 200)) {                          
            return true;                          
        } else {                          
            return false;                          
        }                          
    });                          


    //描画------------------------------------------                          
    for (Line l : Lines) {      
      //translate(width/2, height/2);              
        stroke(l.col.R, l.col.B, l.col.G, l.col.O); //strokeを使わないと
        rotate(random(10));
        line(l.px, l.py, l.x, l.y);
        l.life -= 1;        
    }      
    
  count += 1;                          
  //print(count);                          
                            
  //フォルダは自動生成                          
  String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\08_MilkyWay\\3\\08_MilkyWay-NN_######.png";                          
  saveFrame(outP);
                          
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
