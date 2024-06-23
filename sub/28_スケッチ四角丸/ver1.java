/**
 * Cool The Engines.
 * It draws significant mechanical design drawing.
 *
 * @author @deconbatch
 * @version 0.1
 * @license GPL Version 3 http://www.gnu.org/licenses/
 * Processing 3.5.3
 * 2021.04.29
 */

void setup() {
  size(1080, 1080);
  colorMode(HSB, 360.0, 100.0, 100.0, 100.0);
  smooth();
  noLoop();
}

void draw() {

  int   imgMax  = 3;
  float hueBase = random(360.0);
  for (int imgCnt = 0; imgCnt < imgMax; imgCnt++) {

    hueBase += 150.0;
    
    ArrayList<Panel> panels = getPanels(width, height, imgCnt);
    image(getBackground(width, height), 0.0, 0.0);
    image(getBluePrint(panels, width, height), 0.0, 0.0);
    image(getModel(panels, width, height, hueBase), 0.0, 0.0);
    casing();

    //add----
    String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\28_Lissajous\\1\\28_Penrose-30_######.png";
    saveFrame(outP);
    //----

  }
  //exit();
}

/**
 * getPanels : returns panels array
 */
private ArrayList<Panel> getPanels(int _w, int _h, int _f) {

  ArrayList<Panel> ps = new ArrayList<Panel>();
  
  int   divNum   = 5 + _f;
  int   divSiz   = floor(divNum * 0.3 + 300 / divNum);
  int   shapeSiz = divSiz * divNum;
  float pattern  = floor(random(1.0, 10.0)) + random(0.005, 0.010);

  for (int x = -shapeSiz; x <= shapeSiz; x += divSiz) {
    for (int y = -shapeSiz; y <= shapeSiz; y += divSiz) {

      float s = abs((((abs(x * x) + abs(y * x)) / divSiz) % pattern) / pattern);
      float t = abs((((x * y + y) / divSiz) % pattern) / pattern);

      float sizMin   = divSiz / 20.0 + abs(PI * t);
      float sizPlate = constrain(s * divSiz, sizMin, divSiz) * 4.0;
      float radPlate = abs(divSiz * 2.0 - sizPlate) * 0.5;

      ps.add(new Panel(x, y, sizPlate, radPlate));
    }
  }

  return ps;
}

/**
 * getBackground : returns background image
 */
private PImage getBackground(int _w, int _h) {

  PGraphics p = createGraphics(_w, _h);
  p.beginDraw();
  p.colorMode(HSB, 360.0, 100.0, 100.0, 100.0);
  p.background(40.0, 10.0, 85.0, 100.0);

  // random tiles
  int divNum  = 20;
  int divSizX = floor(width / divNum);
  int divSizY = floor(height / divNum);

  p.noStroke();
  for (int x = 0; x < divNum; x++) {
    for (int y = 0; y < divNum; y++) {
      p.fill(40.0, 5.0, random(80.0, 90.0), 100.0);
      p.rect(x * divSizX, y * divSizY, divSizX * 0.9, divSizY * 0.9);
    }
  }

  p.endDraw();
  return p;
}

/**
 * getBluePrint : returns magnified panels image
 */
private PImage getBluePrint(ArrayList<Panel> _ps, int _w, int _h) {

  PGraphics pg = createGraphics(_w, _h);
  pg.beginDraw();
  pg.colorMode(HSB, 360.0, 100.0, 100.0, 100.0);
  pg.rectMode(CENTER);
  pg.background(0.0, 0.0, 0.0, 0.0);
  pg.noFill();
  pg.stroke(250.0, 60.0, 30.0, 100.0);
  pg.strokeWeight(1.0);

  pg.pushMatrix();
  pg.translate(width * 0.5, height * 0.5);
  pg.rotate(PI * 0.25);
  for (Panel p : _ps) {
    pg.rect(p.x * 2.0, p.y * 2.0, p.size * 2.0, p.size * 2.0, p.radi);
  }
  pg.popMatrix();
  
  pg.endDraw();
  return pg;

}

/**
 * getModel : returns colored panels image
 */
private PImage getModel(ArrayList<Panel> _ps, int _w, int _h, float _hue) {

  float maxLen    = max(width, height);
  
  PGraphics pg = createGraphics(_w, _h);
  pg.beginDraw();
  pg.colorMode(HSB, 360.0, 100.0, 100.0, 100.0);
  pg.rectMode(CENTER);
  pg.background(0.0, 0.0, 0.0, 0.0);

  pg.pushMatrix();
  pg.translate(width * 0.5, height * 0.5);
  
  // paint model black
  pg.blendMode(BLEND);
  pg.fill(0.0, 0.0, 0.0, 100.0);
  pg.noStroke();
  for (Panel p : _ps) {
    pg.rect(p.x, p.y, p.size, p.size, p.radi);
  }

  // model foreground
  pg.blendMode(SCREEN);
  pg.noStroke();
  for (Panel p : _ps) {
    float sRatio = p.size * 10.0 / maxLen;
    float xRatio = p.x * 2.0 / width;
    float yRatio = p.y * 2.0 / height;
    float rHue   = _hue + 360.0 + sRatio * 90.0 + (xRatio - yRatio) * 30.0;
    pg.fill(
            rHue % 360.0,
            40.0 + (xRatio + yRatio) * 10.0,
            30.0 - (xRatio * yRatio) * 5.0,
            100.0
            );
    pg.rect(p.x, p.y, p.size, p.size, p.radi);
  }

  // draw panel outline
  pg.blendMode(BLEND);
  pg.noFill();
  pg.stroke(0.0, 0.0, 0.0, 50.0);
  pg.strokeWeight(3.0);
  for (Panel p : _ps) {
    pg.rect(p.x, p.y, p.size, p.size, p.radi);
    // flavoring with double stroke
    if (
        p.size < maxLen * 0.02 &&
        abs(p.x) < width * 0.1 &&
        abs(p.y) < height * 0.1
        ) {
      pg.rect(p.x * 2.0, p.y * 2.0, p.size * 2.0, p.size * 2.0, p.radi);
    }
  }

  pg.popMatrix();

  pg.endDraw();
  return pg;

}

/**
 * casing : draw fancy casing
 */
private void casing() {
  float w = min(width, height) * 0.05;
  fill(0.0, 0.0, 0.0, 0.0);
  strokeWeight(w + 4.0);
  stroke(0.0, 0.0, 0.0, 100.0);
  rect(0.0, 0.0, width, height);
  strokeWeight(w);
  stroke(0.0, 0.0, 100.0, 100.0);
  rect(0.0, 0.0, width, height);
}

/**
 * Panel : holds panel attributes
 */
private class Panel {
  float x, y;
  float size;
  float radi;
  
  Panel(float _x, float _y, float _s, float _r) {
    x = _x;
    y = _y;
    size = _s;
    radi = _r;
  }
}