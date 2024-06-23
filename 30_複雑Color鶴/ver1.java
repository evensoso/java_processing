/**
 * Illusions of My Childhood.
 * Drawing some figures with the calculation of the Lissajous figure.
 * 
 * @author @deconbatch
 * @version 0.1
 * @license GPL Version 3 http://www.gnu.org/licenses/
 * Processing 3.5.3
 * 2021.02.06
 */

void setup() {
  size(980, 980, P2D);
  colorMode(HSB, 360.0, 100.0, 100.0, 100.0);
  rectMode(CENTER);
  smooth();
  noLoop();
}

void draw() {

  int   frmMax   = 3; // draw image count 
  int   pointMax = 3; // points to calculate
  float hueBase  = random(360.0);

  translate(width * 0.5, height * 0.5);
  for (int frmCnt = 0; frmCnt < frmMax; frmCnt++) {

    hueBase += 90.0;
    int cycleX  = ceil(random(6));
    int cycleY  = ceil(random(6));
    int culcMax = 800 * (cycleX + cycleY);
  
    PVector[] p      = new PVector[pointMax]; // points location
    float[]   adjust = new float[pointMax];   // ratio to fit canvas size
    float[]   maxX   = new float[pointMax];
    float[]   maxY   = new float[pointMax];
    for (int i = 0; i < pointMax; i++) {
      p[i] = new PVector(0.0, 0.0);
      adjust[i] = 0.0;
      maxX[i] = 0.0;
      maxY[i] = 0.0;
    }

    // pre calculation to fix adjust[] value
    for (int culcCnt = 0; culcCnt < culcMax; culcCnt++) {
      float culcRatio = map(culcCnt, 0, culcMax, 0.0, 1.0);
      p[0].x = cos(TWO_PI * culcRatio * cycleX);
      p[0].y = sin(TWO_PI * culcRatio * cycleY);
      maxX[0] = max(maxX[0], abs(p[0].x));
      maxY[0] = max(maxY[0], abs(p[0].y));
      for (int i = 1; i < pointMax; i++) {
        p[i].add(p[i - 1]);
        maxX[i] = max(maxX[i], abs(p[i].x));
        maxY[i] = max(maxY[i], abs(p[i].y));
      }
    }
    for (int i = 0; i < pointMax; i++) {
      adjust[i] = min(width / maxX[i], height / maxY[i]) * 0.35;
      p[i] = new PVector(0.0, 0.0);
    }

    // draw background
    blendMode(BLEND);
    background(hueBase, 20.0, 90.0, 100.0);
    pushMatrix();
    rotate(TWO_PI / (cycleX + cycleY));
    noStroke();
    fill((hueBase + 90.0) % 360.0, 20.0, 90.0, 100.0);
    rect(width * 0.5, 0.0, width * 0.5, height * 2.0);
    fill((hueBase + 120.0) % 360.0, 30.0, 80.0, 100.0);
    rect(0.0, height * 0.5, width * 2.0, height * 0.5);
    popMatrix();

    // draw foreground
    blendMode(SUBTRACT);
    for (int culcCnt = 0; culcCnt < culcMax; culcCnt++) {
      // It's the same calculation before.
      // Mindless dissipation of CPU resources!
      float culcRatio = map(culcCnt, 0, culcMax, 0.0, 1.0);
      p[0].x = cos(TWO_PI * culcRatio * cycleX);
      p[0].y = sin(TWO_PI * culcRatio * cycleY);
      for (int i = 1; i < pointMax; i++) {
        p[i].add(p[i - 1]);
      }

      noFill();
      strokeWeight(0.5);
      beginShape();
      for (int i = 0; i < pointMax; i++) {
        float pRatio = map(i, 0, pointMax, 0.0, 1.0);
        stroke(
               (hueBase + 90.0 * culcRatio) % 360.0,
               60.0,
               2.0 + 20.0 * (pRatio) * culcRatio / (cycleX + cycleY),
               100.0
               );
        vertex(p[i].x * adjust[i], p[i].y * adjust[i]);
      }
      endShape(CLOSE);

      noStroke();
      fill(0.0, 0.0, 20.0 * culcRatio, 100.0);
      for (int i = 0; i < pointMax; i++) {
        float pRatio = map(i, 0, pointMax, 0.0, 1.0);
        float eSiz = (1.0 - pRatio) * sin(PI * culcRatio) * 2.0;
        ellipse(p[i].x * adjust[i], p[i].y * adjust[i], eSiz, eSiz);
      }
    }

    // draw fnacy casing
    blendMode(BLEND);
    casing();  

    //add----
    String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\30_Lissajous\\1\\30_Penrose-30_######.png";
    saveFrame(outP);
    //----
    
  }

}

/**
 * casing : draw fancy casing
 */
private void casing() {
  fill(0.0, 0.0, 0.0, 0.0);
  strokeWeight(54.0);
  stroke(0.0, 0.0, 0.0, 100.0);
  rect(0.0, 0.0, width, height);
  strokeWeight(50.0);
  stroke(0.0, 0.0, 100.0, 100.0);
  rect(0.0, 0.0, width, height);
  noStroke();
  noFill();
  noStroke();
}