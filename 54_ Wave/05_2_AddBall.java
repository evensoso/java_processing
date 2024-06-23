int ranges = 200;
int signal = 2;
float count = 1;

void setup() {
  size(1080, 1080, P3D); //, P2D);
  background(0);
}

void draw() {
  
  background(0);
  directionalLight(51, 102, 126, -1, 0, 0);

  //add----
  String root = "D:\\09_music_create\\03_PICTURE\\99_Render\\";
  String fileName = "54_Wave-FF_" + "c_" + count + "_######.png";
  String outP = root + "54_Wave\\" + "2\\" + fileName;
  //----
   
  //add----
  print(count + ";");
  if (count >= 1800) noLoop();
  //----

  //描画
  paintBall();
  paintWave();
  
  
  flipHalf();

  saveFrame(outP);
  //----
  count += 1;
  
}

void flipHalf() {
  loadPixels();
  for (int x=width/2; x<width; x++) {
    for (int y=0; y<height; y++) {
      pixels[x+y*width] = pixels[(width-x)+y*width];
    }
  }
  updatePixels();
}

void paintWave() {
  
  
  noFill();
  strokeWeight(1);
  
  for (int i = 0; i < ranges; i++) {
    float paint = map(i, 0, ranges, 0, 255);
    stroke(paint, 40);
    
    beginShape();
    if ( i !=50 ){
      for (int x = -10; x < width + 11; x += 10) {
        float n = noise(x * 0.001, i * 0.01, frameCount * 0.02);
        float y = map(n, 0, 1, 0, height); // * signal
        vertex(x, y);
      }
    }
    else if ( i == 50 ) {
      for (int x = -10; x < width + 11; x += 10) {
        //float n = noise(x * 0.001, i * 0.01, frameCount * 0.02);
        //float y = map( - n * signal + sin(x/10), 0, 1, 0, height);
        //vertex(x, y);
      }
    }
    endShape();
  }
}

void paintBall(){
  
  push();
  
  translate(width/2, height/2);
  noFill();
  rotate(count * PIE/40);
  stroke(255, 0, 0, 20);
  strokeWeight(1);
  
  float sizeR = 20 + (count/10);
  sphere(sizeR);
  //translate(-width/2, -height/2);
  
  pop();
  
}