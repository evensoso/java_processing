import processing.opengl.*;

float count = 0;

int _radius = 400;

void setup(){
  size(1080, 1920, OPENGL); // OPENGLで3D描画
  background(255);
  stroke(0, 10);
  strokeWeight(0.01);
}


void draw(){
  
  //add----
  String root = "D:\\09_music_create\\03_PICTURE\\99_Render\\";
  String fileName = "52_Pixcel-30_" + "c_" + count + "_######.png";
  String outP = root + "52_Pixcel\\" + "6\\" + fileName;
    //----
   
  //add----
  print(count + ";");
  if (count >= 1800) noLoop();
  //----

  translate(width/2, height/2, 0); // 出力範囲の中心を描画位置にする
  rotateX(frameCount * 0.04);
  rotateY(frameCount * 0.04);
  
  float s = 0;
  float t = 0;
  float lastX = 0;
  float lastY = 0;
  float lastZ = 0;

  while ( t < 180){ // OUTPUT1枚目はwhile条件 t<90
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
  
  //フォルダは自動生成
  String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\13_Mandala\\13_Mandala-NN_######.png";
  saveFrame(outP);
  //----
  
}


void keyPressed(){
  if (keyCode == ENTER){
  }
  if (keyCode == BACKSPACE){
  }
}
