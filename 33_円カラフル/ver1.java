/**
 * In admiration of Robert Delaunay.
 * Fixed unit arcs
 *
 * @author @deconbatch
 * @version 0.1
 * @license CC0
 * Processing 3.5.3
 * created 2023.02.15
 */

public void setup() {
   size(640, 640);
  colorMode(HSB, 360.0, 100.0, 100.0, 100.0);
  smooth();
  noLoop();

  int   circleNum = 9;
  int   arcNum    = 8;
  float baseR     = width * 1.3;

  translate(width * 0.5, height * 0.5);

  blendMode(BLEND);
  background(0.0, 0.0, 100.0, 100.0);
    
  // concentric circles
  blendMode(BLEND);
  noStroke();
  for (int cCnt = 0; cCnt < circleNum; cCnt++) {
    fill(0.0, 0.0, random(60.0, 80.0), random(60.0));
    circle(0.0, 0.0, baseR * (circleNum - cCnt) * 1.0 / circleNum);
  }

  // arcs
  blendMode(SUBTRACT);
  noFill();
  strokeCap(SQUARE);
  for (int aCnt = 0; aCnt < arcNum; aCnt++) {
    float arcR = baseR * floor(random(1, circleNum)) * 1.0 / circleNum;
    float arcW = baseR * floor(random(1, circleNum)) * 1.0 / circleNum;
    float tFr = TWO_PI * floor(random(24)) / 24.0;
    float tTo = tFr + PI * floor(random(1, 13)) / 12.0;

    strokeWeight(arcW);
    stroke(random(360.0), 40.0, random(60.0, 80.0), random(60.0, 100.0));
    arc(
        0.0, 0.0,
        arcR + arcW, arcR + arcW,
        tFr, tTo
        );
  }

//add----
  String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\33_Lissajous\\1\\33_Penrose-30_######.png";
  saveFrame(outP);
  //----

}