int N=200;                                              
float x,y,z,W,t = 0;                                              
int jVal = 9; //9                                              
float tVal = 0.03; //0.03                               
int count = 0;                           
int size = 120;                          
int lifeTime = 10;

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

        float u = sin(i+y)+sin(i+x);
        float v = cos(i+y)+cos(i+x);
        x=u+t;
        y=v;

        cX=u*N/2+W/2;
        cY=y*N/2+W/2;
                   
                                     
//-------------------------------------                   
                                            
        float o = abs(z-0.5);                          
                                                     
        cS = sin(j);                          
                                  
        Color colAdd = new Color(j, i, 150);                          
        int l = lifeTime;                          
                                  
        PVector location = new PVector(cX, cY, 0);                          
        PVector va = new PVector(0, 0, 0);                          
                                  
        Circle cAdd = new Circle(location,va,cS,colAdd, l);                          
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
        fill(c.col.R, c.col.B, c.col.O);
        circle(c.pos.x,c.pos.y,c.size);                                 
        c.life -= 1;        
    }                          
                              
    //後処理------------------------------------------                          
    count  += 1;                          
                                                  
    //add----                                              
    String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\23_Lissajous\\1\\23_Penrose-30_######.png";                                              
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
                            
  float R, B, O;                           
                            
  Color (float r, float b, float o) {                            
    R = r;                           
    B = b;                                                    
    O = o;                           
  }                           
                            
  void update() {                           
  }                          
                            
}                          
