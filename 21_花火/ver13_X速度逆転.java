int N=300;                    
float x,y,z,W,t = 0;                    
int jVal = 9; //9                    
float tVal = 0.03; //0.03     
int count = 0; 
int size = 100;
int lifeTime = 120;

float j = 0.09;
float cX, cY, cS;

ArrayList<Circle> circles = new ArrayList<Circle>();

PVector gravity;   // Gravity acts at the shape's acceleration
PVector velocity;  // Velocity of shape


void setup(){                    
    size(1920,1920);                    
    background(0);       
    
    noStroke();    
    
    //velocity = new PVector(1.5,2.1);
    gravity = new PVector(0,0.2);
}                    
                    
void draw(){
  background(0);
  
  //新規追加------------------------------------------ 
  translate(width/2, height/2);
  t  += 0.03; 
    for(int i=0; i<N; i++ ){
      
      j = 0.03 * random(255);

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
        
        cX = x * size + N;
        cY = y * size + N;
        cS = (exp(o * 2) + 1);
        
        Color colAdd = new Color(i, z * N, j, 100 * W * exp(-o * 3));
        int l = lifeTime;
        
        PVector location = new PVector(cX, cY, 0);
        PVector v = new PVector(0, 0, 0);
        
        Circle cAdd = new Circle(location,v,cS,colAdd, l);
        circles.add(cAdd);
        //circle(cX, cY, cO);   
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
      
      // Add velocity to the location.
      c.pos.add(c.vel);
      // Add gravity to velocity
      c.vel.add(gravity);
    
      rotate(PI/12);
      
      fill(20, c.col.B, c.col.G, c.col.O-(lifeTime-c.life));
      circle(c.pos.x, c.pos.y, c.size);
      c.life -= 1;
      
    }
    
    //後処理------------------------------------------
    count  += 1;
                        
    //add----                    
    String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\21_Lissajous\\13\\22_Penrose-30_######.png";                    
    saveFrame(outP);                    
    //----                    
                    
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
