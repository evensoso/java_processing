int bufferSize = 300;
int BPM = 140;
int count = 1;

void setup() {
  size(1080, 1920, P3D);
  background(0);
}

void draw() {
   //add----
  String root = "D:\\09_music_create\\03_PICTURE\\99_Render\\";
  String fileName = "71-FF_" + "c_" + count + "_######.png";
  String outP = root + "71_Build_ver1\\" + fileName;
    //----
   
  //add----
  print(count + ";");
  if (count >= 1800) noLoop();
  //----
  
  //background(255);
  createBuilding();
  count++;
  saveFrame(outP);
}

void createBuilding() {
  int step = frameCount % BPM;
  //print("Step: " + step + ";");
  int w = 1080;
  int h = 1920;
  
  //--------------------------------------
  if (step == 1) {
    print("done; ");
  }
  
  //--------------------------------------
  if (step == 2) {
    blendMode(DIFFERENCE);
    strokeWeight(0.4);
    line(0, h / 2, w, h / 2);
    line(w / 2, 0, w / 2, h);
  }
  
  //--------------------------------------
  if (step == 3) {
    blendMode(ADD);
    stroke(255);
    noFill();
    //fill(100, 0, 100);
    for (int i = 5; i > 0; i--) {
      float x = random(w);
      float y = random(h);
      ellipse(x, y, 100, 100);
      ellipse(x+10, y+10, 30, 50);
    }
  }
  //--------------------------------------
  if (step > 3 && step < 54) {
    stroke(0);
    int x1 = int(random(w));
    int y1 = int(random(h));
    int w1 = int(random(w));
    int h1 = int(random(h));
    int x2 = int(random(w));
    int y2 = int(random(h));
    int w2 = int(random(w));
    int h2 = int(random(h));
    if (random(1) > 0.2){
      blendMode(DIFFERENCE);
    }
    else if (random(1) > 0.8){
      blendMode(ADD);
    }
    copy(x1, y1, w1, h1, x2, y2, w2, h2);
  }
  
  //Down
  //--------------------------------------
  if (step > 53 && step < 63) {
    blendMode(DIFFERENCE);
    copy(0, 0, w, h, 0, 10, w, h + 10);
  }
  if (step > 64) {
    for (int i = 5; i > 0; i--) {
      blendMode(DIFFERENCE);
      stroke(random(255), random(255), random(255));
      float x = random(w);
      float y = random(h);
      float size = random(100);
      ellipse(x, y, size, size);
      ellipse(x, y, size, size);
    }
  }
}