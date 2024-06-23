/**
 * Birth of the Cool.
 * It draws some shape with meaningless mixed up formula.
 * 
 * Processing 3.5.3
 * @author @deconbatch
 * @version 0.1
 * created 0.1 2020.08.01
 */

Calculator cc;

int   frmMax    = 200;
int   ptnMax    = 3;
int   initPlot  = 0;
float initHue   = 0.0;
float initRad   = 0.0;
float initMultA = 0.0;
float initMultB = 0.0;

public void setup() {
  size(980, 980);
  colorMode(HSB, 360.0, 100.0, 100.0, 100.0);
  smooth();
}

public void draw() {

  resetPtn();
  PGraphics back  = initPG();
  PGraphics plane = initPG();
  PGraphics wire  = initPG();
  back.beginDraw();
  plane.beginDraw();
  wire.beginDraw();

  for (int frmCnt = 0; frmCnt < frmMax; frmCnt++) {
    
    float frmRatio = easeInOutCosine(map(frmCnt % frmMax, 0, frmMax, 0.0, 1.0));

    // moving factor
    float radVal = initRad + frmRatio * TWO_PI;

    back.pushMatrix();
    back.translate(width * 0.5, height * 0.5);
    back.rotate(initRad * 0.5);
    back.noStroke();
    back.fill((initHue + 90.0) % 360.0, 50.0, 90.0, 100.0);

    plane.pushMatrix();
    plane.translate(width * 0.5, height * 0.5);
    plane.rotate(initRad * 0.5);
    plane.noStroke();
    plane.fill((initHue + frmRatio * 180.0) % 360.0, 80.0, 50.0, 20.0);

    wire.pushMatrix();
    wire.translate(width * 0.5, height * 0.5);
    wire.rotate(initRad * 0.5);
    wire.noFill();
    wire.stroke((initHue + frmRatio * 180.0) % 360.0, 30.0, 80.0, 30.0);
    wire.strokeWeight(1.0);

    for (int i = 0; i < initPlot; i++) {
      float plotRatio = map(i, 0, initPlot, 0.0, 1.0);

      // calculate with meaningless wave formula
      float fX = plotRatio * width;
      float fY = cc.calc(plotRatio * initMultB, radVal * initMultA) * height;
      float pX = fY * cos(fX) * 0.5;
      float pY = fY * sin(fX) * 0.5;

      back.pushMatrix();
      back.translate(pX * 0.5, pY * 0.5);
      back.rotate(radVal);
      back.ellipse(0.0, 0.0, fY * 0.25, fX);
      back.popMatrix();

      plane.pushMatrix();
      plane.translate(pX * 0.5, pY * 0.5);
      plane.rotate(radVal);
      plane.ellipse(0.0, 0.0, fY * 0.25, fX);
      plane.popMatrix();

      wire.pushMatrix();
      wire.translate(pX * 0.5, pY * 0.5);
      wire.rotate(radVal);
      wire.ellipse(0.0, 0.0, fY * 0.25, fX);
      wire.popMatrix();

    }

    back.popMatrix();
    plane.popMatrix();
    wire.popMatrix();

  }

  back.endDraw();
  plane.endDraw();
  wire.endDraw();

  // draw results
  drawSandWall(initHue + 90.0);
  image(back, 0.0, 0.0);
  image(plane, 0.0, 0.0);
  image(wire, 0.0, 0.0);

  // draw casing
  casing();

  //add----
  //String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\36_Lissajous\\1\\36_Penrose-30_######.png";
  //saveFrame(outP);
  //----
  
  if (frameCount >= 3) {
    //exit();
  }

  
}

/**
 * initPG initialize PGraphics.
 */
public PGraphics initPG() {
  PGraphics pg = createGraphics(width, height);
  pg.beginDraw();
  pg.colorMode(HSB, 360, 100, 100, 100);
  pg.background(0.0, 0.0, 0.0, 0.0);
  pg.endDraw();
  return pg;
}


/**
 * resetPtn reset pattern parameters (global variables).
 */
public void resetPtn() {

  cc = getCalculator();

  initPlot  = cc.initPlot();
  initHue   = random(360.0);
  initRad   = random(TWO_PI);
  initMultA = cc.multA();
  initMultB = cc.multB();

}

/**
 * getCalculator returns random calculator.
 */
public Calculator getCalculator() {
  int calcNum = 6;
  float r = random(1.0);

  if (r < 1.0 / calcNum) {
    return new MagicWand();
  } else if (r < 2.0 / calcNum) {
    return new Mountains();
  } else if (r < 3.0 / calcNum) {
    return new WindBlow();
  } else if (r < 4.0 / calcNum) {
    return new SeeSaw();
  } else if (r < 5.0 / calcNum) {
    return new ThreeStars();
  } else if (r < 6.0 / calcNum) {
    return new BigWave();
  }

  // fail safe
  return new BigWave();
}

/**
 * drawCanvas : draw sand wall
 * @param  _baseHue : sand color.
 */
void drawSandWall(float _baseHue) {
  background(_baseHue % 360.0, 30.0, 90.0, 100.0);
  pushMatrix();
  translate(width * 0.5, height * 0.5);
  for (int x = 0; x < width * 0.5; x += 2) {
    for (int y = 0; y < height * 0.5; y += 2) {
  
      float pSize = random(0.5, 1.0);
      float pDiv  = random(-2.0, 2.0);
      float pSat = 0.0;
      if ((x + y) % 3 == 0) {
        pSat = 80.0;
      }
      strokeWeight(pSize);
      stroke(_baseHue % 360.0, pSat, 30.0, 70.0);
      point(x + pDiv, y + pDiv);
      point(-x + pDiv, y + pDiv);
      point(x + pDiv, -y + pDiv);
      point(-x + pDiv, -y + pDiv);
    }
  }
  popMatrix();
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

/**
 * waveCalc calculate the wave shape.
 * @param  _ratio  : any value.
 * @param  _rad    : any value.
 * @return float   : calculated value.
 */
public float waveCalc(float _ratio, float _rad) {
  return sin(_ratio * TWO_PI * cos(_rad));
}

interface Calculator {
  float calc(float _a, float _b);
  int   initPlot();
  float multA();
  float multB();
}

class MagicWand implements Calculator {
  float calc(float _a, float _b) {
    return _a * (sin(_b) + cos(_b));
  }
  
  int initPlot() {
    return floor(random(2.0, 4.0));
  }

  float multA() {
    return random(0.3, 0.6);
  }

  float multB() {
    return random(0.3, 1.0);
  }
}

class Mountains implements Calculator {
  float calc(float _a, float _b) {
    return cos(_a * _b) * sin(_a + _b * 0.5);
  }
  
  int initPlot() {
    return 2;
  }

  float multA() {
    return random(0.2, 1.0);
  }

  float multB() {
    return random(1.0);
  }
}

class WindBlow implements Calculator {
  float calc(float _a, float _b) {
    return sin(_a + _b) * cos(_a);
  }
  
  int initPlot() {
    return 3;
  }

  float multA() {
    return random(0.3, 0.4);
  }

  float multB() {
    return random(0.5, 3.0);
  }
}

class SeeSaw implements Calculator {
  float calc(float _a, float _b) {
    return cos(_b) * cos(_a * PI);
  }
  
  int initPlot() {
    return floor(random(2.0, 4.0));
  }

  float multA() {
    return random(0.2, 0.6);
  }

  float multB() {
    return random(0.5, 0.9);
  }
}

class ThreeStars implements Calculator {
  float calc(float _a, float _b) {
    return log(_a + sin(_b));
  }
  
  int initPlot() {
    return floor(random(2.0, 4.0));
  }

  float multA() {
    return random(0.2, 0.4);
  }

  float multB() {
    return random(0.8);
  }
}

class BigWave implements Calculator {
  float calc(float _a, float _b) {
    return cos(HALF_PI * (log(_a) +  cos(_b)));
  }
  
  int initPlot() {
    return floor(random(2.0, 4.0));
  }

  float multA() {
    return random(0.2, 0.4);
  }

  float multB() {
    return random(1.0, 4.0);
  }
}