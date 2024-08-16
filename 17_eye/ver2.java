int W=1920;
int N=960;
float x,y,t = 0;

void setup(){

    size(1920,1920);
    background(0);
    noStroke();
    
}

void draw(){
    clear();
    background(0);
    
    //add
    for(int i = 0; i < N; i++){
        for(int j = 0; j < N; j++){

            float r=TAU/N;
            float u=sin(i+y +t)+sin(r*i+x + t);
            float v=cos(i+y)+cos(r*i+x);
            x=u+t;
            y=v;
            fill(i,j,99);
            circle(u*N/2+W/2,y*N/2+W/2,2);

        }
      }

      t+=0.1;
      
  //add----
  String rootP = "E:\\Rendered_Proc\\";
  String outP = rootP + "17-1\\17-1_30_######.png";
  saveFrame(outP);
  //----

}
