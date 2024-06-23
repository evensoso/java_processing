int w = 640;
int h = 640;
float[] pofloat1 = new float[2];
float[] pofloat2 = new float[2];
float d;
float r = 300;
float seed1;
float seed2;

void setup() {
  size(640, 640);
  background(255);//white
  d = TWO_PI / 360;
  seed1 = frameCount;
  seed2 = frameCount;
}

void draw() {
  push();
  translate(w / 2, h / 2);
  float n1 = noise(seed1 + 0.02 * frameCount) * r;
  float n2 = noise(seed2 + 0.02 * frameCount) * r;
  pofloat1[0] = cos(d * frameCount) * n1;
  pofloat1[1] = sin(d * frameCount) * n1;
  pofloat2[0] = cos(d * frameCount + HALF_PI) * n2;
  pofloat2[1] = sin(d * frameCount + HALF_PI) * n2;
  stroke(frameCount / 6);
//stroke(0);
  if(frameCount > 1800){
    noLoop();
  }
  print(pofloat1[0]);
  //print(pofloat1[0]);
  //print(pofloat2[0]);
  //print(pofloat2[1]);
  
  line(pofloat1[0],pofloat1[1],pofloat2[0],pofloat2[1]);
  pop();
  
  //add----
  String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\04\\1\\Penrose-30_######.png";
  saveFrame(outP);
  //----
}
