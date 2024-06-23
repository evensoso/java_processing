int h=120;
int Wsize = 640;
int P= 200;
int i= 1;//P/32;
int count = 1;
float f = 0;
float sc = 0.2;
  
void setup() {
  size(640, 640, P3D);
}
void draw() {

  //add----
  String root = "D:\\09_music_create\\03_PICTURE\\99_Render\\";
  String fileName = "68_Close-FF_" + "c_" + count + "_######.png";
  String outP = root + "68_Close_ver1\\" + fileName;
    //----
   
  //add----
  print(count + ";");
  if (count >= 1800) noLoop();
  //----
  
  background(0);
  noStroke();

  for(int x = -P; x < P; x += 1){
    for(float z = -P; z < P; z += 1){
      push();
      fill(255);
      float y = tan(Wsize - mag(noise(x), noise(z)) * f);
      scale(sc);
      translate(x * h, z * h, -y * h * 3);
      float rr = mag(noise(x), noise(z)) * 2;
      sphere(rr);
      pop();
    }
  }
  f+=i/8;
  count++;
  saveFrame(outP);
}