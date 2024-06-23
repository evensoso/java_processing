/**
 * Twist in the Dark.
 * trying to use global variables to make an animation.
 * 
 * Processing 3.5.3
 * @author @deconbatch
 * @version 0.1
 * created 0.1 2020.06.27
 */

int   frmMax   = 24 * 3;
int   ptnMax   = 6;
int   initPlot = floor(random(100));
float initHue  = random(360.0);
float initRad  = 0.0;

public void setup() {
  size(720, 720);
  colorMode(HSB, 360, 100, 100, 100);
  rectMode(CENTER);
  smooth();
  frameRate(24);
}

public void draw() {

  int frmCnt = frameCount - 1; // because of frameCount start from 1
  if (frmCnt >= frmMax * ptnMax - 1) {
    exit();
  }

  if (frmCnt % frmMax == 0) {
    resetPtn();
  }

  // moving factor
  float radians = initRad + easeInOutCosine(map(frmCnt % frmMax, 0, frmMax, 0.0, 1.0)) * TWO_PI * 2;

  translate(width * 0.5, height * 0.5);
  background(initHue % 360.0, 40.0, 90.0, 100.0);
  strokeWeight(3.0);
  stroke(initHue % 360.0, 40.0, 90.0, 100.0);
  fill((initHue + 90) % 360.0, 90, 10, 100);

  // draw shapes
  beginShape(TRIANGLE_FAN);
  vertex(0.0, 0.0);
  int plotNum = initPlot + 120;
  for (int i = 0; i < plotNum; i++) {
    float iRatio = map(i, 0, plotNum, 0.0, 1.0);
    float fX = width * iRatio;
    float fY = height * (0.5 + 0.4 * waveCalc(iRatio, radians));
    // translate wave shape into polar coordinates
    float x = fY * cos(fX) * 0.5;
    float y = fY * sin(fX) * 0.5;
    vertex(x, y);
  }
  vertex(0.0, 0.0);
  endShape(CLOSE);

  // draw casing
  casing();
  
  //add----
  String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\27_Lissajous\\1\\27_Penrose-30_######.png";
  saveFrame(outP);
  //----

}


/**
 * waveCalc calculate the wave shape.
 * @param  _ratio  : any value.
 * @param  _rad    : any value.
 * @return float  -1.0 - 1.0 : calculated value.
 */
public float waveCalc(float _ratio, float _rad) {
  return cos(_ratio * _rad) * sin(_ratio + _rad * 0.5);
}


/**
 * resetPtn reset pattern parameters (global variables).
 */
public void resetPtn() {
  initPlot += 39; // 
  initPlot %= 100;
  initHue += 90.0;
  initRad = random(-TWO_PI, TWO_PI) * 2.0;
}


/**
 * casing : draw fancy casing
 */
public void casing() {
  fill(0.0, 0.0, 0.0, 0.0);
  strokeWeight(26.0);
  stroke(0.0, 0.0, 0.0, 100.0);
  rect(0.0, 0.0, width, height);
  strokeWeight(20.0);
  stroke(0.0, 0.0, 100.0, 100.0);
  rect(0.0, 0.0, width, height);
}

/**
 * easeInOutCosine easing function.
 * @param  _t    0.0 - 1.0 : linear value.
 * @return float 0.0 - 1.0 : eased value.
 */
public float easeInOutCosine(float _t) {
  return 0.5 - cos(PI * _t) * 0.5;
}