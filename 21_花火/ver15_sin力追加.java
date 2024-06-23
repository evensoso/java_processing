int N=200;                                              
float x,y,z,W,t = 0;                                              
int jVal = 9; //9                                              
float tVal = 0.03; //0.03                               
int count = 0;                           
int size = 120;                          
int lifeTime = 120;

//各設定値をフォルダ名に入れるのありだね
                          
float j = 0.09;                          
float cX, cY, cS;                          
                          
ArrayList<Circle> circles = new ArrayList<Circle>();                          
                          
PVector gravity;   // Gravity acts at the shape's acceleration                          
PVector gravity2;   // Gravity acts at the shape's acceleration                          
PVector velocity;  // Velocity of shape                          
                          
float tPIE = 0;                          
                              
void setup(){                                              
    size(1080,1920);                                              
    background(0);                                 
                              
    noStroke();                              
                              
    //velocity = new PVector(1.5,2.1);                          
    gravity = new PVector(0,0.2);                          
                              
}                                              
                                              
void draw(){  

    if (count > 1800){
        noLoop();
    }                        
  background(0);                          
  //print(count);                          
                            
  //新規追加------------------------------------------                           
  translate(width/2, height/2);                          
  t  += 0.03;                           
    for(int i=0; i<N; i++ ){                          
        for(int j=0; j<N; j+=jVal){                          
                          
        //if (count % 14 == 0)                           
        //rotate(PI/12);                          
                                                        
        x = cos(W) + sin(i + x);                                              
        y = sin(W) + cos(i + y);                                              
        z = sin(i + x) + cos(i + y);                                              
        W = cos(i + z) + t;                                              
        float o = abs(z-0.5);                          
                                  
        //fill(i, z * N, j, 100 * W * exp(-o * 3));                          
        //fill(i, z, j, 100 * W * exp(-o * 3)); //100を透明度にかけた                          
        //rotate(PI/6);                          
                                  
        cX = (x * size + N) * random(1, 1.2);                          
        cY = (y * size + N) * random(1, 1.2);                          
        cS = (exp(o * 2) + 1);                          
                                  
        Color colAdd = new Color(i, z * N, j, 400 * W * exp(-o * 3));                          
        int l = lifeTime;                          
                                  
        PVector location = new PVector(cX, cY, 0);                          
        PVector v = new PVector(0, 0, 0);                          
                                  
        Circle cAdd = new Circle(location,v,cS,colAdd, l);                          
        circles.add(cAdd);                          
        //circle(cX, cY, cO);                             
        }                          
    }                          
                              
    circles.removeIf((Circle c) ->{                          
            if(c.life<=0) {                          
                return true;                          
            } else {                          
                return false;                          
            }                          
    });                          
                              
    //描画------------------------------------------                          
    for (Circle c : circles) {                          
                                
      if (abs(c.pos.x-width/2)<200){                          
        c.vel.x = -c.vel.x;                          
      }  
      
      if (abs(c.pos.y-height/2)<200){                          
        c.vel.y = -c.vel.y;                          
      }  
                                
      tPIE = 20 * sin(PIE*(count/12));                          
      gravity2 = new PVector(tPIE,tPIE);                          
                                
      // Add velocity to the location.                          
      c.pos.add(c.vel);                          
      // Add gravity to velocity                          
      c.vel.add(gravity);                          
      c.vel.add(gravity2);                          
                              
      rotate(PI/12);                          
                                
      fill(20, c.col.B, c.col.G, c.col.O-(lifeTime-c.life));                          
      circle(c.pos.x, c.pos.y, c.size);                          
                                
      //残像を追加 ver16                          
      fill(20, c.col.B, c.col.G, c.col.O-(lifeTime-c.life)-300);                          
      circle(c.pos.x, c.pos.y, c.size+3);                          
      c.life -= 1;                          
                                
    }                          
                              
    //後処理------------------------------------------                          
    count  += 1;   

    save();
                                           
                                              
}                                              
                          
class Circle {                           
  PVector pos;                          
  PVector vel ;                          
  float xPos, yPos, zPos, size, life;                           
  Color col;                          
                            
  Circle (PVector xyz, PVector v, float s, Color c, int l) {                          
    pos = xyz;                          
    vel = v;                          
    size = s;                           
    col = c;                           
    life = l;                          
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

void save(){

    String outP = "D:\\Rendered_Proc\\21\\15\\1\\21-30_####.png";
    saveFrame(outP);

}  