/**
 * Just Another Nervous Wreck.
 * 
 * A creative coding that draws De Jong attractors on polar coordinates.
 * 
 * Processing 3.5.3
 * @author @deconbatch
 * @version 0.1
 * created 0.1 2020.01.26
 */

void setup() {

  size(980, 980);
  colorMode(HSB, 360, 100, 100, 100);
  rectMode(CENTER);
  smooth();
  noLoop();

}

void draw() {

  int   frmMax  = 3;
  float hueBase = random(360);

  translate(width / 2, height / 2);
  for (int frmCnt = 0; frmCnt < frmMax; frmCnt++) {

    blendMode(DIFFERENCE);
    background(0.0, 0.0, 90.0, 100.0);
    hueBase += 90.0;

    pushMatrix();
    rotate(random(PI));
    drawWreck(hueBase);
    popMatrix();  
    
    blendMode(BLEND);
    casing(hueBase);
    
    //add----
      String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\44_Lissajous\\1\\44_Penrose-30_######.png";
      saveFrame(outP);
      //----
  }
  //exit();
}

/**
 * drawWreck : draws De Jong attractors on polar coordinates
 * @param  _hueBase : drawing color.
 */
void drawWreck(float _hueBase) {

  float hueBase = _hueBase;

  // De Jong Attractor parameters
  float djA   = 1.0;
  float djB   = random(-1.0, 1.0);
  float djC   = 1.0;
  float djD   = random(-1.0, 1.0);
  float prevX = random(-2.0, 2.0);
  float prevY = random(-2.0, 2.0);

  float rBase = random(0.2, 0.5) * HALF_PI;
  float sBase = 0.25;
  int   pMax  = 12000000;

  noStroke();
  for (int pCnt = 0; pCnt < pMax; pCnt++) {

    // for avoid convergence (as much as possible)
    if (pCnt == floor(pMax * 0.6)){
      djA = random(-1.0, 1.0);
      djB = 1.0;
      djC = random(-1.0, 1.0);
      djD = 1.0;
      hueBase += 30.0;
    }

    // De Jong Attractors
    float currX = sin(TWO_PI * djA * prevY) - cos(TWO_PI * djB * prevX);
    float currY = sin(TWO_PI * djC * prevX) - cos(TWO_PI * djD * prevY);

    // polar coordinates
    float dX = width  * 0.15 * currX;
    float dY = height * 0.15 * currX;
    float dR = rBase * currY;
    float px = dX * cos(dR) - dY * sin(dR);
    float py = dY * cos(dR) + dX * sin(dR);
    float ps = sBase * (1.0 + dist(px, py, 0.0, 0.0) * 2.0 / width);

    fill(hueBase % 360.0, 40.0, 15.0, 100.0);
    ellipse(px, py, ps, ps);

    prevX = currX;
    prevY = currY;

  }
}


/**
 * casing : draw fancy casing
 * @param  _hueBase : casing color.
 */
private void casing(float _hueBase) {
  
  fill(0.0, 0.0, 0.0, 0.0);
  strokeWeight(40.0);
  stroke(_hueBase, 60.0, 20.0, 100.0);
  rect(0.0, 0.0, width, height);
  strokeWeight(30.0);
  stroke(0.0, 0.0, 95.0, 100.0);
  rect(0.0, 0.0, width, height);
    
}