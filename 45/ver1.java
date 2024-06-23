/**
 * Beyond The Universe.
 * draw vector field with no noise.
 * 
 * @author @deconbatch
 * @version 0.1
 * Processing 3.2.1
 * created 2019.10.31
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

  int   frmMax  = 3; // draw 3 images
  float initHue = random(360.0);
  translate(width * 0.5, height * 0.5);

  for (int frmCnt = 1; frmCnt <= frmMax; frmCnt++) {
    float frmRatio    = map(frmCnt, 1, frmMax, 1.0, 0.0);
    int   vfldMax     = floor(map(frmRatio * frmRatio, 0.0, 1.0, 3.0, 30.0)); // draw vfldMax count vector fields
    float vfldFactorX = random(1.0);
    float vfldFactorY = random(1.0);
    initHue += 90.0;

    background((initHue + 180.0) % 360.0, 5.0, 90.0, 100);
    blendMode(DIFFERENCE);

    pushMatrix();
    rotate(random(TWO_PI));
    for (int vfldCnt = 1; vfldCnt <= vfldMax; vfldCnt++) {
      float vfldRatio = map(vfldCnt, 1, vfldMax, 0.0, 1.0);
      int   plotMax   = floor(500 - vfldRatio * 400);
      float plotDiv   = 0.002 + vfldRatio * 0.001;
      float plotMult  = 1.0 + vfldRatio * map(frmRatio, 0.0, 1.0, 2.0, 15.0);
      float initDiv   = 0.02;
      float baseSiz   = map(frmRatio, 0.0, 1.0, 1.2, 0.8) - vfldRatio * 0.5;
      float baseHue   = initHue + vfldRatio * 30.0;
      float baseBri   = 1.0 + vfldRatio * 2.0;

      for (float xInit = -0.5; xInit <= 0.5; xInit += initDiv) {
        for (float yInit = -0.5; yInit <= 0.5; yInit += initDiv) {
          float xCurr = xInit;
          float yCurr = yInit;
          for (int plotCnt = 0; plotCnt < plotMax; plotCnt++) {
            float plotRatio = map(plotCnt, 0, plotMax, 0.0, 1.0);
            float eHue      = baseHue + plotRatio * 60.0 + floor(((xInit * yInit) * 10000.0) % 4.0) * 10.0;
            float eSat      = map(sin(PI * plotRatio), 0.0, 1.0, 40.0, 70.0);
            float eBri      = baseBri * (1.0 + sin(PI * plotRatio));
            float eSiz      = baseSiz * (1.0 + sin(PI * plotRatio));

            float xPrev = xCurr;
            float yPrev = yCurr;
            xCurr += plotDiv * cos((atan2(xPrev, yPrev) + (TWO_PI * (vfldFactorX + yPrev))) * plotMult);
            yCurr += plotDiv * sin((atan2(yPrev, xPrev) + (TWO_PI * (vfldFactorY + xPrev))) * plotMult);

            fill(eHue % 360.0, eSat, eBri, 100.0);
            ellipse(xCurr * width, yCurr * height, eSiz, eSiz);
          }
        }
      }
    }
    popMatrix();

    casing();
    //add----
    String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\45_Lissajous\\1\\45_Penrose-30_######.png";
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