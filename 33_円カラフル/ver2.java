/** 
 * In admiration of Robert Delaunay.
 * Put arcs randonly
 * 
 * @author @deconbatch
 * @version 0.1
 * @license CC0
 * p5.js 1.5.0
 * created 2023.02.15
 */

int w = 640;
int h = w;
int circleNum = 9;
int arcNum = 8;
float baseR = w * 1.3;

void setup() {
  size(640, 640);
  colorMode(HSB, 360, 100, 100, 100);
  noLoop();
}

void draw() {
  translate(w * 0.5, h * 0.5);

  blendMode(BLEND);
  background(0, 0, 100, 100);

  // concentric circles
  blendMode(BLEND);
  noStroke();
  for (int cCnt = 0; cCnt < circleNum; cCnt++) {
    fill(0, 0, random(60, 80), random(60));
    circle(0, 0, baseR * (circleNum - cCnt) / circleNum);
  }

  // arcs
  blendMode(BURN);
  noFill();
  strokeCap(SQUARE);
  for (int aCnt = 0; aCnt < arcNum; aCnt++) {
    float arcR = random(baseR);
    float arcW = random(baseR);
    float tFr = random(TWO_PI);
    float tTo = tFr + random(PI);

    strokeWeight(arcW);
    stroke(random(360), 40, random(60, 80), random(60, 100));
    arc(
      0.0, 0.0,
      arcR + arcW, arcR + arcW,
      tFr, tTo
    );
  }
  
  //add----
  String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\33_Lissajous\\2\\33_Penrose-30_######.png";
  saveFrame(outP);
  //----
  
}

void mouseClicked() {
  redraw();
}