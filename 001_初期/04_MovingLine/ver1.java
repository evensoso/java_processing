int w = 1080;
int h = 400;
int t = 150;
void setup() {
  size(1080, 400);
  background(0);
}

void draw() {
  background(0);
  for(int j = 0;j < 40;j++){
    float seed = (j - frameCount) * 0.02;
    float pre_y = noise(seed) * t - t/2 + h/4 * sin(0) + h / 2;
    int c = color(noise(seed) * 255,noise(seed + 1) * 255,noise(seed + 2) * 255); 
    stroke(c);
    for(int i = 0;i < w;i+=3){
      float y = noise(seed + 0.01 * (i + 1)) * t - t/2 + h/4 * sin(TWO_PI/360*i * 0.8) + h / 2;
      line(i, pre_y, i + 3, y);
      pre_y = y;
    }
  }
  //add----
  String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\04\\1\\Penrose-30_######.png";
  saveFrame(outP);
  //----
}
