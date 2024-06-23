/**
 * Pearl on the Half Shell.
 * This code does not display any images on screen but generates image files in frames directory.
 * You can make an animation with these files. 
 * 
 * @author @deconbatch
 * @version 0.1
 * Processing 3.2.1
 * created 2019.09.04
 *
 */

void setup() {
  size(720, 720);
  colorMode(HSB, 360, 100, 100, 100);
  blendMode(SCREEN);
  smooth();
  noStroke();
  noLoop();
}

void draw() {

  int   frmMax   = 24 * 6; // 24fps x 6s frames
  int   ptnCnt   = 3000;
  float plotDiv  = 0.01;
  float plotMult = random(1.5, 2.5);
  float baseHue  = random(360.0);
  float divHue   = 90.0;
  float baseSat  = 60.0;
  float baseBri  = 30.0;
  float baseSiz  = 1.3;
  float xBase    = random(3.0);
  float yBase    = random(3.0);

  for (int frmCnt = 1; frmCnt <= frmMax; ++frmCnt) {
    float frmRatio = map(frmCnt, 1, frmMax, 0.0, 1.0);
    float rDiv     = PI / ptnCnt;
    
    background((baseHue + divHue * 0.5) % 360.0, 90.0, 10.0, 100);
    for (float rInit = 0.0; rInit < PI; rInit += rDiv) {
    
      float rRatio = map(rInit, 0.0, PI, 0.0, 1.0);

      // make start points cyclic
      float xInit  = 0.5 + 0.25 * cos(TWO_PI * frmRatio + rInit);
      float yInit  = 0.5 + 0.25 * sin(TWO_PI * frmRatio + rInit);
      float xPoint = xInit;
      float yPoint = yInit;

      int plotMax = 10 + floor(30 * sin(PI * (rRatio + frmRatio) % PI));
      for (int plotCnt = 0; plotCnt < plotMax; ++plotCnt) {

        float pRatio = map(plotCnt, 0, plotMax, 0.0, 1.0);
        float pHue   = (baseHue +  pRatio * 90.0) % 360.0;
        float pSat   = baseSat * (1.0 - pRatio + rRatio); //baseSat * rRatio;
        float pBri   = baseBri * sin(PI * rRatio);
        float pSiz   = baseSiz * baseSiz * (1.0 - pRatio);
        // highlight center circle
        if (plotCnt == 0) {
          pSiz = (pSiz - rRatio + 1.0) * 3.0;
        }

        float xPrev  = xPoint;
        float yPrev  = yPoint;
        // The Vector Field essence
        float plotRadian = sin(xPrev + yPrev) + sin(dist(xBase, yBase, xInit, yInit));
        xPoint += plotDiv * cos(TWO_PI * plotRadian * plotMult);
        yPoint += plotDiv * sin(TWO_PI * plotRadian * plotMult);

        // draw a symmetrical shape
        fill(pHue, pSat , pBri, 100.0);
        ellipse(xPrev * width, yPrev * height, pSiz, pSiz);
        fill((pHue + divHue) % 360.0, pSat , pBri, 100.0);
        ellipse((1.0-xPrev) * width, (1.0-yPrev) * height, pSiz, pSiz);

      }
    }      
    
    //add----
    String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\42_Lissajous\\1\\42_Penrose-30_######.png";
    saveFrame(outP);
    //----
  }

  //exit();

}