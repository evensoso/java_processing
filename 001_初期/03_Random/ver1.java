int R = 600;
int a = 3;
int b = 4;
float d = 1;
float u = 0.001; //Speed
void setup() {
  size(1080, 1080);
  background(0);
  colorMode(HSB, 250);
  d = PI / 6;
} 

void draw() {
  noStroke();
  push();
  translate(width/2,height/2);
  float t = frameCount * u;
  float s = (frameCount - 1) * u;
  float preX = R * sin(a * s + d)* sin(a * s + d)* cos(b * s + d) + frameCount*0.01;
  float preY = R * sin(b * s) * sin(a * s);
  float x = R * sin(a * t + d)* sin(a * t + d)* cos(b * t + d) + frameCount*0.01;
  float y = R * sin(b * t) * sin(a * t);
  stroke(color((frameCount/4) % 250, 200, 250));
  line(preX,preY,x,y);
  pop();
  
  //add----
  String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\06_Lissajous\\Penrose-30_######.png";
  saveFrame(outP);
  //----

}
