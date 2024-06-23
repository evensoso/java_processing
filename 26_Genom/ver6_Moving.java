int N=800;
float tSpeed = 0.002;
int jVal = 1; //9                                              
float tVal = 0.03; //0.03                               
int count = 0;                           
float size = 0.4;                          
int lifeTime = 2;
int jS = 460; 
int jF1 = 480; 
int jF2 = 520; 

//---------------------

//各設定値をフォルダ名に入れるのありだね
                          
float x,y,z,W,t = 0;     
float cX, cY, cS;                          
                          
ArrayList<Circle> circles = new ArrayList<Circle>();                          
                          
PVector gravity;   // Gravity acts at the shape's acceleration                          
PVector gravity2;   // Gravity acts at the shape's acceleration                          
PVector velocity;  // Velocity of shape                          
                          
float tPIE = 0;    

PImage leftHalf;
                              
void setup(){                                              
    size(1080,1080);                                              
    background(0);                                 
                              
    noStroke();                              
                              
    velocity = new PVector(1.5,2.1);                          
    gravity = new PVector(0,0.2);     
    
    smooth();
                              
}                                              
                                              
void draw(){  

   if (count > 1800){
        noLoop();
    }                        
    background(0);                          
    //print(count);                          
                            
    //新規追加------------------------------------------                           
    translate(width/2, height/2);              
    rotate((PIE/20)*count);
    
    t += tSpeed; //speed

    for(int i= 0; i < N; i++ ){                          
        for(int j= jS; j < jF2; j+=jVal){ //N一つずつ抽出

        float u = sin((i+y))+sin((j+x)/2);
        float v = cos((i+y)/2)+cos((i+x)/2);
        x = u + t;
        y = v;
        
        //後半を変える
        if (j > jF1){
          y += sin(j);
        }
          
        cX=size*(u*N/2+W/2)*log(x)*3;
        
        //if (cX < 100){
        //  cX=size*(u*N/2+W/2)*exp(x)*3;
        //}
        
        cY=size*(y*N/2+W/2);

        //float o = abs(cX-width);                          
                                                     
        cS = 2*cos(j);                          
                                  
        Color colAdd = new Color(j, abs(y*100), 150, abs(cS*300));                          
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
      
            // Add velocity to the location.
        c.pos.add(c.vel);
      // Add gravity to velocity
        c.vel.add(gravity);


        //fill(c.col.R, c.col.B, c.col.G, (c.col.O)*10000); //-c.life/20
        fill(abs(c.pos.x+20), c.col.B, c.col.G, (c.col.O)-c.life); //反映したいなんとか
        circle(c.pos.x,c.pos.y,c.size);                                 
        c.life -= 1;        
    }                          
                              
    //後処理------------------------------------------             
   
   
   flipHalf();
     
  
    count  += 1;        
    //print(count + "--");
                                                  
    //add----                                              
    String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\26_Lissajous\\6\\26_Penrose-30_######.png";                                              
    saveFrame(outP);                                              
    //----                                              
                                              
}  

void flipHalf() {
  loadPixels();
  for (int x = width/2; x < width; x++) {
    for (int y=0; y < height; y++) {
      pixels[x + y*width] = pixels[(width-x) + y*width];
    }
  }
  updatePixels();
}

void flipHalf2() {
  loadPixels();
      for (int x=0; x < width; x++) {
  for (int y = height/2; y < height; y++) {

      pixels[y + x*height] = pixels[(height-y) + x*height];
    }
  }
  updatePixels();
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
