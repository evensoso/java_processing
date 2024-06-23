int N=800;                                              
float x,y,z,W,t = 0;                                              
int jVal = 6; //9                                              
float tVal = 0.03; //0.03                               
int count = 0;                           
float size = 0.4;                          
int lifeTime = 20;

 String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\25_Lissajous\\4\\25_Penrose-30_######.png";

//各設定値をフォルダ名に入れるのありだね
                          
float j = 0.09;                          
float cX, cY, cZ, cS;                          
                          
ArrayList<Circle> circles = new ArrayList<Circle>();                          
                          
PVector gravity;   // Gravity acts at the shape's acceleration                          
PVector gravity2;   // Gravity acts at the shape's acceleration                          
PVector velocity;  // Velocity of shape                          
                          
float tPIE = 0;       

float eyeX;
float eyeY;
float eyeZ;
float centerX;
float centerY;
float centerZ;

void setup(){                                              
    size(1080,1920,P3D);                                              
    background(0);     
    lights();
    
 eyeX = width/2.0;
 eyeY = height/2.0;
 eyeZ = (height/2.0) / tan(PI*30.0 / 180.0);
 centerX = width/2.0;
 centerY = height/2.0;
 centerZ = 0;
                              
    noStroke();                              
                              
    //velocity = new PVector(1.5,2.1);                          
    gravity = new PVector(0,0.2);                          
                              
}                                              
                                              
void draw(){  
  //eyeZ +=10;
  eyeZ -=10;
  eyeY -=6;
  eyeX -=6;
  //centerX -=6;
  //if (eyeZ < 200) eyeZ +=40;
  //else eyeZ -=40;
  //eyeY +=10;
  //eyeX +=10;
  //beginCamera();
camera(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, 0, 1, 0);
//rotateX(-PI/2);
//endCamera();

  //camera(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, 0, 1, 0);
  
  //print(count);
    if (count > 1800){
        noLoop();
    }                        
    background(0);                          
    //print(count);                          
                            
    //新規追加------------------------------------------                           
    translate(width/2, height/2);              
    rotate((PIE/20)*count);
    
    t  += 0.004; //speed

    for (int i= 0; i < N; i++ ) {                          
        for (int j= 320; j < 340; j+=jVal) { //N一つずつ抽出

        float u = sin((i+y))+sin((j+x));
        float v = cos((i+y))+cos((i+x));
        x = u + t;
        y = v;

        cX=size*2*(u*N/2+W/2);
        cY=size*2*(y*N/2+W/2);
        cZ=size*4*(u*y*N/2+W/2);
                   
                                     
//-------------------------------------                   
                                            
        float o = (z+0.5);                          
                                                     
        cS = 2*cos(j);                          
                                  
        Color colAdd = new Color(cX, cY, cZ, o*10); //-cZ/10);                          
        int l = lifeTime;                          
                                  
        PVector location = new PVector(cX, cY, cZ);                          
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
    //rotateX(-PI/2);
    //描画------------------------------------------                          
    for (Circle c : circles) {                          
        fill(c.col.R, c.col.B, c.col.G, (c.col.O)*10000); //-c.life/20
        
        
        push();  //大事
        translate(c.pos.x, c.pos.y, c.pos.z);
        sphere(c.size);
        pop();

        //circle(c.pos.x,c.pos.y,c.size);                                 
        c.life -= 1;        
    }                          
                              
    //後処理------------------------------------------                          
    count  += 1;        
    //print(count + "--");
                                                  
    //add----                                              
                                                 
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
