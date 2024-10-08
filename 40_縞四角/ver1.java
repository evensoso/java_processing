/**
 * Going In Circles.
 * draw many circles with vector field using no noise.
 * 
 * @author @deconbatch
 * @version 0.1
 * Processing 3.2.1
 * created 2019.11.
 */

void setup() {

  size(980, 980);
  colorMode(HSB, 360, 100, 100, 100);
  rectMode(CENTER);
  smooth();
  noStroke();
  noLoop();

}

void draw() {

  int   frmMax   = 3; // draw 3 images
  int   plotMax  = 1000;
  float plotDiv  = 0.001;
  float plotMult = random(0.0, 5.0);
  float initDiv  = 0.02;
  float initHue  = random(360.0);
  float baseBri  = 5.0;
  translate(width * 0.5, height * 0.5);

  for (int frmCnt = 1; frmCnt <= frmMax; frmCnt++) {

    // for different looks
    plotMult %= 5.0;
    plotMult += 1.5;
    initHue += 90.0;

    float frmRatio    = map(frmCnt, 1, frmMax, 0.0, 1.0);
    float vfldFactorX = random(0.5, 2.0);
    float vfldFactorY = random(0.5, 2.0);
    float baseSiz     = random(1.0, 1.5);
    float baseHue     = initHue;

    background(0.0, 0.0, 90.0, 100);
    blendMode(DIFFERENCE);

    pushMatrix();
    rotate(random(TWO_PI));
    for (float xInit = -0.5; xInit <= 0.5; xInit += initDiv) {
      for (float yInit = -0.5; yInit <= 0.5; yInit += initDiv) {
        float xCurr = xInit;
        float yCurr = yInit;
        for (int plotCnt = 0; plotCnt < plotMax; plotCnt++) {
          float plotRatio = map(plotCnt, 0, plotMax, 0.0, 1.0);
          float eHue      = baseHue + plotRatio * 60.0 + floor(((xInit + yInit) * 10000.0) % 4.0) * 10.0;
          float eSat      = map(sin(PI * plotRatio), 0.0, 1.0, 40.0, 80.0);
          float eBri      = baseBri * (1.0 + sin(PI * plotRatio));
          float eSiz      = baseSiz * (1.0 + sin(PI * plotRatio));

          float xPrev = xCurr;
          float yPrev = yCurr;
          xCurr += plotDiv * cos((atan2(yPrev * vfldFactorX, yPrev) + TWO_PI * yPrev) * plotMult);
          yCurr += plotDiv * sin((atan2(xPrev * vfldFactorY, xPrev) + TWO_PI * xPrev) * plotMult);

          fill(eHue % 360.0, eSat, eBri, 100.0);
          ellipse(xCurr * width, yCurr * height, eSiz, eSiz);
        }
      }
    }
    popMatrix();

    casing();
    //add----
  String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\40_Lissajous\\1\\40_Penrose-30_######.png";
  saveFrame(outP);
  //----
  }
  //exit();
}

/**
 * casing : draw fancy casing
 */
private void casing() {
  blendMode(BLEND);
  fill(0.0, 0.0, 0.0, 0.0);
  strokeWeight(54.0);
  stroke(0.0, 0.0, 0.0, 100.0);
  rect(0.0, 0.0, width, height);
  strokeWeight(50.0);
  stroke(0.0, 0.0, 100.0, 100.0);
  rect(0.0, 0.0, width, height);
  noStroke();
  noFill();
}