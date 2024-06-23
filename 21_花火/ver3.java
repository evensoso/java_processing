int N=400;                    
float x,y,z,W,t = 0;                    
int jVal = 9; //9                    
float tVal = 0.03; //0.03     
int count = 0; 
int size = 200;

float j = 0.03;

                    
void setup(){                    
    size(1080,1920);                    
    background(0);                    
    noStroke();                    
}                    
                    
void draw(){
  
  t  += 0.03; 
    for(int i=0; i<N; i++ ){
      
      j = 0.03 * random(255);

        //if (count % 14 == 0) 
        rotate(PI/12);
                              
        x = cos(W) + sin(i + x);                    
        y = sin(W) + cos(i + y);                    
        z = sin(i + x) + cos(i + y);                    
        W = cos(i + z) + t;                    
        float o = abs(z-0.5);
        
        fill(i, z * N, j, 100 * W * exp(-o * 3));
        //fill(i, z, j, 100 * W * exp(-o * 3)); //100を透明度にかけた
        //rotate(PI/6);
        circle(x * size + N, y * size + N, (exp(o * 2) + 1));   
    }           
    
    count  += 1;               
                        
    //add----                    
    String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\21_Lissajous\\3\\22_Penrose-30_######.png";                    
    saveFrame(outP);                    
    //----                    
                    
}                    
