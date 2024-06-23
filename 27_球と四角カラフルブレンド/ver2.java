Wall    wl;
Puncher pc;
PImage img ;
float layerRatio;
int count=1;

void setup() {
  size(640, 1040);
  colorMode(HSB, 360.0, 100.0, 100.0, 100.0);
  smooth();
  doFirstDraw();
}

void draw() {

  doDraw();

  //add----
  String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\27_ColBall_ver2\\27_ColBall-30_######.png";
  saveFrame(outP);
  //----
  print(count);
  count++;

}

void doFirstDraw(){
  int   layerMax = 5;
  float hueBase = random(360.0);

  // bottom layer
  blendMode(BLEND);
  wl = getRandomWall(hueBase);
  image(wl.drawPattern(90.0), 0.0, 0.0);

  // middle layers
  blendMode(BLEND);
  for (int layerCnt = 0; layerCnt < layerMax; layerCnt++) {
    layerRatio = map(layerCnt, 0, layerMax, 0.0, 1.0);
    wl = getRandomWall(hueBase + 30.0 + 150.0 * layerRatio);
    pc = getRandomPuncher();
    image(pc.punch(wl.drawPattern(90.0 - 50.0 * layerRatio)), 0.0, 0.0);
  }

  // reverse punch layer
  blendMode(BLEND);
  pc = new WordPuncher();
  img = pc.punch(get());
  wl = getRandomWall(hueBase + 60.0);
  image(wl.drawPattern(10.0), 0.0, 0.0);
  image(img, 0.0, 0.0);

  // top layer
  blendMode(BLEND);
  wl = getRandomWall(hueBase + 60.0);
  pc = new PackCirclePuncher();
  image(pc.punch(wl.drawPattern(30.0)), 0.0, 0.0);

  casing();
  
}

void doDraw(){
  int   layerMax = 5;

  // bottom layer
  blendMode(BLEND);
  //image(wl.drawPattern(90.0), 0.0, 0.0);

  // middle layers
  blendMode(BLEND);
  for (int layerCnt = 0; layerCnt < layerMax; layerCnt++) {
    image(pc.punch(wl.drawPattern(90.0 - 50.0 * layerRatio)), 0.0, 0.0);
  }
  // reverse punch layer
  blendMode(BLEND);
  img = pc.punch(get());
  image(wl.drawPattern(10.0), 0.0, 0.0);
  image(img, 0.0, 0.0);

  // top layer
  blendMode(BLEND);
  //image(pc.punch(wl.drawPattern(30.0)), 0.0, 0.0);

  casing();
  
}

/**
 * getRandomWall : returns random class
 */
Wall getRandomWall(float _hue) {
  float sel = random(1.0);
  if (sel < 0.3) {
    return new CircleCenter(_hue);
  } else if (sel < 0.6) {
    return new RectCenter(_hue);
  } else {
    return new RectCorner(_hue);
  }
}

/**
 * getRandomPuncher : returns random class
 */
Puncher getRandomPuncher() {
  float sel = random(1.0);
  if (sel < 0.25) {
    return new PackCirclePuncher();
  } else if (sel < 0.5) {
    return new PackRectPuncher();
  } else if (sel < 0.75) {
    return new MatrixPackCirclePuncher();
  } else {
    return new MatrixPackRectPuncher();
  }
}

/**
 * casing : draws fancy casing
 */
void casing() {
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
 * Wall : draws various patterns
 */
abstract class Wall {

  int   stepDiv;
  float briMin;
  float briMax;
  float hueBase;

  Wall(float _hue) {
    // set pattern base parameters
    hueBase = _hue;
    if (random(1.0) < 0.5) {
      stepDiv = 12;
      briMin = 5.0;
      briMax = 11.0;
    } else {
      stepDiv = 20;
      briMin = 2.0;
      briMax = 8.0;
    }
  }

  /**
   * drawCell : draws pattern cell
   */
  abstract void drawCell(PGraphics _p, float _size);

  /**
   * setBlendMode : change blend mode randomly
   */
  void setBlendMode(PGraphics _p) {
    if (random(1.0) < 0.4) {
      _p.blendMode(DIFFERENCE);
    } else {
      _p.blendMode(SCREEN);
    }
  }
  
  /**
   * drawPattern : draws pattern
   */
  PImage drawPattern(float _briBack) {

    int stepMax = stepDiv * 4;
    noiseSeed(floor(random(100.0)));

    PGraphics p = createGraphics(width, height);
    p.beginDraw();
    p.colorMode(HSB, 360.0, 100.0, 100.0, 100.0);
    p.background((hueBase + 90.0) % 360.0, 40.0, _briBack, 100.0);
    p.noStroke();
    for (int step = stepDiv; step < stepMax; step += stepDiv) {
      for (int cellSiz = 1; cellSiz < step; cellSiz++) {
        for (int x = 0; x <= width; x += step) {
          for (int y = 0; y <= height; y += step) {
            
            //濃さを決めよう
            p.fill(
                   (hueBase + random(120.0)) % 360,
                   noise(10.0, x * 0.05, y * 0.05) * 50.0,
                   noise(90.0, x * 0.05, y * 0.05) * map(step, stepDiv, stepMax, briMax, briMin),
                   exp(count) % 100//100.0
                   );
                   
            p.pushMatrix();
            p.translate(x, y);
            setBlendMode(p);
            drawCell(p, cellSiz);
            p.popMatrix();
          }
        }
      }
    }
    p.endDraw();
    return p;
  }
}


/**
 * Wall classes
 */
class CircleCenter extends Wall {
  CircleCenter(float _hue) {
    super(_hue);
  }
  void drawCell(PGraphics _p, float _size) {
    _p.ellipse(0, 0, _size, _size);
  }
}

class RectCenter extends Wall {
  RectCenter(float _hue) {
    super(_hue);
  }
  void drawCell(PGraphics _p, float _size) {
    _p.rectMode(CENTER);
    _p.rect(-count, count, _size, _size);
  }
}

class RectCorner extends Wall {
  RectCorner(float _hue) {
    super(_hue);
  }
  void drawCell(PGraphics _p, float _size) {
    _p.rect(0, 0, _size, _size);
  }
}

/**
 * Puncher : returns punched image
 */
interface Puncher {
  PImage punch(PImage _img);
}

/**
 * Puncher classes
 */
class MatrixPackRectPuncher implements Puncher {
  PImage punch(PImage _img) {
    PGraphics pg = createGraphics(width, height);
    pg.beginDraw();
    pg.colorMode(HSB, 360.0, 100.0, 100.0, 100.0);
    pg.rectMode(CENTER);
    pg.background(0.0, 0.0, 0.0, 0.0);
    pg.image(_img, 0.0, 0.0);

    pg.blendMode(REPLACE);
    pg.noStroke();
    float div = min(width, height) / floor(random(3.0, 8.0));
    for (float x = div * 0.5; x < width; x += div) {
      for (float y = div * 0.5; y < height; y += div) {
        if (random(1.0) < 0.6) {
          float eBri = (random(1.0) < 0.8) ? 0.0 : 80.0;
          for (int i = 0; i < 50; i++) {
            float alp = constrain(map(i, 0, 50, 70.0, -15.0), 0.0, 100.0);
            float eR = div * map(i, 0, 50, 1.0, 0.0);
            pg.fill(0.0, 0.0, eBri, sin(count) + alp);
            punchShape(pg, x, y, eR);
          }
        }
      }
    }

    pg.endDraw();
    return pg;
  }

  void punchShape(PGraphics _p, float _x, float _y, float _s) {
    _p.rect(_x, _y, _s, _s);
  }
}

class MatrixPackCirclePuncher extends MatrixPackRectPuncher {
  void punchShape(PGraphics _p, float _x, float _y, float _s) {
    _p.ellipse(_x, _y, _s, _s);
  }
}  

class PackRectPuncher implements Puncher {

  ArrayList<PunchHole> pcs;
  class PunchHole {
    public float x, y, r;
    PunchHole(float _x, float _y, float _r) {
      x = _x;
      y = _y;
      r = _r;
    }
  }

  // circle packing
  PackRectPuncher() {

    pcs = new ArrayList<PunchHole>();
    
    int   punchNo = 200;
    int   tryMax  = 10000; // a trying count to add and grow circle.
    float gap     = 10.0;
    float allow   = 1; // allow how many collision

    pcs.add(new PunchHole(width * 0.5, height * 0.5, min(width, height) * 0.5));

    for (int tryCnt = 0; tryCnt < tryMax; tryCnt++) {
      float newX = random(width);
      float newY = random(height);
      Boolean addPuncher = false;
      if (tryCnt % 10 == 0) {
        addPuncher = true;
      }
      for (PunchHole pThis : pcs) {
        int collision = 0;
        // canvas edge
        float hr = pThis.r * 0.5;
        if (pThis.x + hr >= width) {
          pThis.x -= gap * 0.5;
        }
        if (pThis.x - hr <= 0.0) {
          pThis.x += gap * 0.5;
        }
        if (pThis.y + hr >= height) {
          pThis.y -= gap * 0.5;
        }
        if (pThis.y - hr <= 0.0) {
          pThis.y += gap * 0.5;
        }
        // collision
        for (PunchHole pThat : pcs) {
          if (pThis != pThat) {
            if (dist(pThis.x, pThis.y, pThat.x, pThat.y) < (pThis.r + pThat.r) * 0.5 + gap) {
              collision++;
            }
          }
        }
        if (collision < allow) {
          pThis.r += gap * 0.5;
        }
        // add new puncher
        if (
            pcs.size() > punchNo ||
            dist(pThis.x, pThis.y, newX, newY) < pThis.r * 0.5 + gap
            ) {
          addPuncher = false;
        }
      }
      if (addPuncher) {
        pcs.add(new PunchHole(newX, newY, 0.0));
      }

    }
  }

  PImage punch(PImage _img) {
    PGraphics pg = createGraphics(width, height);
    pg.beginDraw();
    pg.colorMode(HSB, 360.0, 100.0, 100.0, 100.0);
    pg.rectMode(CENTER);
    pg.background(0.0, 0.0, 0.0, 0.0);
    pg.image(_img, 0.0, 0.0);

    pg.blendMode(REPLACE);
    pg.noStroke();
    for (PunchHole pc : pcs) {
      float eBri = (random(1.0) < 0.8) ? 0.0 : 80.0;
      for (int i = 0; i < 50; i++) {
        float alp = constrain(map(i, 0, 50, 70.0, -15.0), 0.0, 100.0);
        float eR = pc.r * map(i, 0, 50, 1.0, 0.0);
        pg.fill(0.0, 0.0, eBri, cos(count) + alp);
        punchShape(pg, pc.x, pc.y, eR);
      }
    }
  
    pg.endDraw();
    return pg;
  }

  void punchShape(PGraphics _p, float _x, float _y, float _s) {
    _p.rect(_x, _y, _s, _s);
  }
  
}

class PackCirclePuncher extends PackRectPuncher {
  ArrayList<PunchHole> pcs;
  PackCirclePuncher() {
    super();
  }
  void punchShape(PGraphics _p, float _x, float _y, float _s) {
    
    int place = count%20;
    
    //大きい円
    _p.ellipse(_x+place*10, _y, _s, _s);
    if (_x+count*10 > width + 10) { _p.ellipse(0+place*10, _y, _s, _s); }
    _p.ellipse(0+place*10, _y, _s, _s);
  }
}

class WordPuncher implements Puncher {
  String crimes[] = {"", ""
    /* "Suppression",
    "Instigation",
    "Concealment",
    "Falsification",
    "Discrimination",
    "Invasion",
    "Oppression",*/
  };

  PImage punch(PImage _img) {
    PGraphics pg = createGraphics(width, height);
    pg.beginDraw();
    pg.colorMode(HSB, 360.0, 100.0, 100.0, 100.0);
    pg.background(0.0, 0.0, 0.0, 0.0);
    pg.image(_img, 0.0, 0.0);

    pg.blendMode(REPLACE);
    pg.noStroke();
    pg.fill(0.0, 0.0, 0.0, 0.0);
    pg.textSize(60.0);
    pg.textAlign(CENTER, CENTER);

    int   crimeNum = 5;
    float yDiv = height / crimeNum;
    for (int i = 0; i < crimeNum; i++) {
      pg.text(
              crimes[floor(random(crimes.length))],
              random(width),
              (0.5 + i) * yDiv
              );
    }
    
    pg.endDraw();
    return pg;
  }
}
