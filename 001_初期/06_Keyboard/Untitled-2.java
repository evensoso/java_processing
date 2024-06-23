int W=1080;
int N=200; //sizeなど
float r,t,u,v,x,y=0;
int cl = 99;

void setup(){
  size(1080,1080); 
  noStroke();
  background(0);
}

void draw(){

  //clear();
  background(0);
  
  for (int i = 0; i < 200; i++){
    for (int j = 0; j < 100; j++){
      
        r=TAU/N;

        if ((keyPressed == true) && (key == 'a')) {
            //t+=.10;
            //cl=1;
            u=sin(i+y)+sin(r*i+x);
            v=cos(i+y)+cos(r*i+x);
            x= (u + t)*(t%3);
            y= (v/u)*(t%3);
            fill(i,j,cl, N*10);
            circle(u*N/2+W/2, y*N/2+W/2,0.4*(t%3));
        }

        if ((keyPressed == true) && (key == 's')) {
          //rotate(PIE/2); //全体を回転はどうする？
            u=sin(i+y)+sin(r*i+x);
            v=cos(i+y)+cos(r*i+x);
            x= (u + t)*(t%3);
            y=(v/u)*(t%3);
            fill(i,j,cl);
            circle(u*N/2+W/2,y*N/2+W/2,2);
        }
        else {
            u=sin(i+y)+sin(r*i+x);
            v=cos(i+y)+cos(r*i+x);
            x= u + t;
            y=v;
            fill(i,j,cl);
            circle(u*N/2+W/2,y*N/2+W/2,2);
        }
        
    }  
  }
  
  t+=.1;
 

  
  saveFrame("C:\\Users\\nagayama.shohei\\Downloads\\processing-4.3-windows-x64\\processing-4.3\\99_save\\3\\######.png");
}

