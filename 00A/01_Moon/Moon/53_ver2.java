import processing.opengl.*;

float count = 1;
float initHue   = 0.0;
int _radius = 400;

void setup(){
  size(960, 960, OPENGL); // OPENGLで3D描画
  background(0);
}

void draw(){
  
  //drawSandWall(initHue + 90.0);
  
  //add------------------------------
  String rendID = "01_Moon";
  //------------------------------
  String root = "D:\\09_music_create\\03_PICTURE\\99_Render\\00A\\";
  String fileName = rendID + "-FF_" + "c_" + count + "_######.png";
  String outP = root + rendID + "\\" + "1\\" + fileName;
  //------------------------------
   
  //add----
  print(count + ";");
  if (count >= 1800) noLoop();
  //----

  translate(width/2, height/2, 0);
  rotateX(frameCount * 0.04);
  rotateY(frameCount * 0.04);
  
  float s = 0;
  float t = 0;
  float lastX = 0;
  float lastY = 0;
  float lastZ = 0;
  
  stroke(255, 255, 255, 60);
  strokeWeight(0.2);

  //while ( t < 180){ // OUTPUT1枚目はwhile条件 t<90
  for(t = 0; t<1200; t++ ){
    s += 18;
    t += 1;
    float radianS = radians(s);
    float radianT = radians(t);
    
    float thisX = _radius * cos(radianS) * sin(radianT); // ここら辺を変えると多様なパターンが見られる
    float thisY = _radius * sin(radianS) * sin(radianT);
    float thisZ = _radius * cos(radianT);
    
    if (lastX != 0){
      line(thisX, thisY, thisZ, lastX, lastY, lastZ);
    }
    lastX = thisX;
    lastY = thisY;
    lastZ = thisZ;
  }
  
  //drawSandWall(initHue + 90.0);
  
  saveFrame(outP);
  //----
  count += 1;
}

void keyPressed(){
  if (keyCode == ENTER){
  }
  if (keyCode == BACKSPACE){
  }
}

/*
void drawSandWall(float _baseHue) {
  float half = 1;
  //background(_baseHue % 360.0, 30.0, 90.0, 100.0);
  pushMatrix();
  translate(width * half, height * half);
  for (int x = 0; x < width * half; x += 2) {
    for (int y = 0; y < height * half; y += 2) {
  
      float pSize = random(0.5, 1.0);
      float pDiv  = random(-2.0, 2.0);
      float pSat = 0.0;
      if ((x + y) % 3 == 0) {
        pSat = 80.0;
      }
      strokeWeight(pSize);
      stroke(_baseHue % 360.0, pSat, 30.0, 70.0);
      point(x + pDiv, y + pDiv);
      point(-x + pDiv, y + pDiv);
      point(x + pDiv, -y + pDiv);
      point(-x + pDiv, -y + pDiv);
    }
  }
  popMatrix();
}
*/