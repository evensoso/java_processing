int W=1080;
int N=400;
float x,y,t = 0;

void setup(){

    size(1080,1080);
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
  String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\17_Lissajous\\2\\17_Penrose-30_######.png";
  saveFrame(outP);
  //----

}
