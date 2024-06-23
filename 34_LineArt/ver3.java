/**
 * New Frontier.
 * draws some interesting shapes with meaningless mixed up formulas.
 */
 
 float count = 1.0;
   PImage img;

public void setup() {
  size(1080, 1050, P2D);
  colorMode(HSB, 360, 100, 100, 100);
  rectMode(CENTER);
  smooth();
  //noLoop();
}

public void draw() {

   //ファイル名
   String root = "D:\\09_music_create\\03_PICTURE\\99_Render\\";
   String fileName = "34_LineArt-30_" + "c_" + count + "_######.png";
   String outP = root + "34_LineArt\\" + "3\\" + fileName;
   
    //add----
  print(count);
  //background(0.0, 0.0, 90.0, 100.0);
  //if (img != null)image(img, 0, 0); //前のイメージを残す
  //if (count % 6 == 0) background(0.0, 0.0, 90.0, 100.0);
  
  if (count >= 1800) noLoop();
  //----
  
  int   frmMax = 4; // draw 4 images
  float hueBase = 360 * sin(count); //random(360.0);

  NiceCalculators ncs = new NiceCalculators();
  NiceParameters nps = new NiceParameters();

  for (int frmCnt = 0; frmCnt < frmMax; frmCnt++) {

    hueBase += 90.0;
    float boldBase  = 4 * sin(count); //random(2.0, 4.0);
    float canvasRot = floor(4 * sin(count)) * HALF_PI * 0.5; //random(5.0)
  
    ncs.choose();
    Calculator calcX = ncs.getFirstPart();
    Calculator calcY = ncs.getSecondPart();

    nps.choose();
    int morphMax = nps.getMorph();
    int plotMax  = nps.getPlots();

    // calculate 3 different patterns and hold whole point
    ArrayList<PVector> pvs =  new ArrayList<PVector>();
    pvs = calcPoints(calcX, calcY, morphMax, plotMax);
    pvs.addAll(calcPoints(calcX, calcY, morphMax, plotMax));
    pvs.addAll(calcPoints(calcX, calcY, morphMax, plotMax));

    // calculate size parameter to adjust with the sketch size
    float minX = width;
    float minY = height;
    float maxX = 0.0;
    float maxY = 0.0;
    for (PVector p : pvs) {
      minX = min(minX, p.x);
      minY = min(minY, p.y);
      maxX = max(maxX, p.x);
      maxY = max(maxY, p.y);
    }
    float adjustSiz = 0.85 * min(width / (maxX - minX), height / (maxY - minY));

    // draw background
    blendMode(BLEND);
    strokeWeight(height / 100.0);
    for (int i = 0; i < 100; i++) {
      stroke((hueBase + 60.0) % 360.0, 90.0, 50.0 - i * 0.2, 100.0);
      line(0, i * height/ 100.0, width, i * height/ 100.0);
    }

    blendMode(SCREEN);
    pushMatrix();
    translate(width * 0.5, height * 0.5);
    rotate(canvasRot);
    translate( -(maxX + minX) * adjustSiz * 0.5, -(maxY + minY) * adjustSiz * 0.5 );

    // draw main shape
    noFill();
    strokeCap(SQUARE);
    strokeJoin(ROUND);
    
    beginShape();
    for (int i = 0; i < pvs.size(); i++) {
      float iRatio = map(i, 0, pvs.size(), 0.0, 1.0);
      float lHue = (hueBase + iRatio * 120.0) % 360.0;
      float lSat = 60.0 + 20.0 * iRatio;
      float lBri = 30.0 - iRatio * 20.0;
      strokeWeight(map(iRatio, 0.0, 1.0, boldBase, boldBase * 0.1));
      stroke(lHue, lSat, lBri, 100.0 * iRatio);
      curveVertex(pvs.get(i).x * adjustSiz, pvs.get(i).y * adjustSiz);
    }
    endShape();

    // draw sub lines
    for (int i = 0; i < pvs.size() - 1; i++) {
      int   cons = 0;
      float iRatio = map(i, 0, pvs.size() - 1, 0.0, 1.0);
      float lHue = (hueBase + iRatio * 120.0) % 360.0;
      float lSat = 90.0;
      float lBri = 60 * iRatio;
      noFill();
      stroke(lHue, lSat, lBri, 80.0);
      // vertical, horizontal lines
      for (int j = i + 1; j < pvs.size(); j++) {
        
        if(abs(pvs.get(i).x - pvs.get(j).x) * adjustSiz < 0.02 ||
           abs(pvs.get(i).y - pvs.get(j).y) * adjustSiz < 0.02) {
          float d = dist(pvs.get(i).x, pvs.get(i).y, pvs.get(j).x, pvs.get(j).y) * adjustSiz;
          if (d > 50.0 && d < 250.0) {
            strokeWeight(boldBase * 0.5);
            line(pvs.get(i).x * adjustSiz, pvs.get(i).y * adjustSiz, pvs.get(j).x * adjustSiz, pvs.get(j).y * adjustSiz);
            cons++;
          }
        }
        // slanting lines
        float slant = atan2(pvs.get(i).y - pvs.get(j).y, pvs.get(i).x - pvs.get(j).x);
        
        if (abs(slant - HALF_PI * 0.5) < 0.01 || abs(slant - HALF_PI * 1.5) < 0.01) {
          float d = dist(pvs.get(i).x, pvs.get(i).y, pvs.get(j).x, pvs.get(j).y) * adjustSiz;
          if (d > 150.0 && d < 151.0) {
            strokeWeight(boldBase * 0.3);
            line(pvs.get(i).x * adjustSiz, pvs.get(i).y * adjustSiz, pvs.get(j).x * adjustSiz, pvs.get(j).y * adjustSiz);
            cons++;
          }
        }
      }

      // terminal objects
      if (cons > 0 && cons < 10) {
        float fAlp = random(30.0, 90.0); //90 * sin(count); //random(30.0, 90.0); //注意★
        float fSiz = cons * 240.0 / fAlp;
        noStroke();
        fill(lHue, lSat * 2.0, lBri, fAlp);
        if (cons % 2 == 0) {
          rect(pvs.get(i).x * adjustSiz, pvs.get(i).y * adjustSiz, fSiz, fSiz, 4);
        } else {
          //ellipse(pvs.get(i).x * adjustSiz, pvs.get(i).y * adjustSiz, fSiz, fSiz);
        }
      }
    }
    popMatrix();
  
    // draw casing
    blendMode(BLEND);
    //casing();

    //add----
    saveFrame(outP);
    //----
  
  }
  count += 1;
}

/**
 * calcPoints calculate whole points location and return PVectors.
 */
public ArrayList<PVector> calcPoints(Calculator _calcX, Calculator _calcY, int _morphMax, int _plotMax) {

  float radStart = TWO_PI * sin(count); //random(TWO_PI);
  
  //大きく変化
  float fX       =  sin(count); //random(1.0);
  float fY       =  sin(count); //random(1.0);
 
  ArrayList<PVector> pvs = new ArrayList<PVector>();
  for (int morphCnt = 0; morphCnt < _morphMax; morphCnt++) {
  
    float morphRatio = easeInOutCubic(map(morphCnt, 0, _morphMax, 0.0, 1.0));

    // moving factor
    float radVal = radStart + morphRatio * TWO_PI;

    for (int i = 0; i < _plotMax; i++) {
      float plotRatio = map(i, 0, _plotMax, 0.0, 1.0);
      // meaningless wave formula
      fX += _calcX.calc(plotRatio, radVal) * _calcX.mult;
      fY += _calcY.calc(plotRatio, radVal) * _calcY.mult;
      fX %= width * _calcX.mod;
      fY %= height * _calcY.mod;
    
      // wave shape in normal coordinate
      float nX = (fX - 0.5);
      float nY = (fY - 0.5);

      // wave shape in polar coordinate
      float pX = fY * cos(fX) * 0.5;
      float pY = fY * sin(fX) * 0.5;

      // morphing
      float x = nX * (1.0 - morphRatio) + pX * morphRatio;
      float y = nY * (1.0 - morphRatio) + pY * morphRatio;

      pvs.add(new PVector(x, y));
    }
  }
  return pvs;
}

/**
 * easeInOutCubic easing function.
 * @param  _t    0.0 - 1.0 : linear value.
 * @return float 0.0 - 1.0 : eased value.
 */
public float easeInOutCubic(float _t) {
  _t *= 2.0;
  if (_t < 1.0) {
    return pow(_t, 3) / 2.0;
  }
  _t -= 2.0;
  return (pow(_t, 3) + 2.0) / 2.0;
}

/**
 * NiceCalculators
 * choose a pair of Calculators that create a nice result.
 *
 */
public class NiceCalculators {
  private Calculator cFirst, cSecond;
  private ArrayList<Pair> pairs = new ArrayList<Pair>();

  NiceCalculators() {
    pairs.add(new Pair(new JustATwoOfUs(), new PieceOfMyHeart()));
    pairs.add(new Pair(new JustATwoOfUs(), new AndThen()));
    pairs.add(new Pair(new JustATwoOfUs(), new Mountains()));
    pairs.add(new Pair(new JustATwoOfUs(), new SeeSaw()));
    pairs.add(new Pair(new PieceOfMyHeart(), new JustATwoOfUs()));
    pairs.add(new Pair(new PieceOfMyHeart(), new Mountains()));
    pairs.add(new Pair(new Mountains(), new BigWave()));
    pairs.add(new Pair(new Mountains(), new TwistedSister()));
    pairs.add(new Pair(new SeeSaw(), new AndThen()));
    pairs.add(new Pair(new SeeSaw(), new Mountains()));
    pairs.add(new Pair(new TwistedSister(), new BigWave()));
    pairs.add(new Pair(new ThreeStars(), new Mountains()));

    choose();
  }

  public Calculator getFirstPart() {
    return cFirst;
  }

  public Calculator getSecondPart() {
    return cSecond;
  }

  public void choose() {
    Pair choice = pairs.get(floor(random(pairs.size())));
    cFirst = choice.cFirst;
    cSecond = choice.cSecond;
  }

  /**
   * Pair
   * holds pair of Calculators.
   */
  private class Pair {
    public Calculator cFirst, cSecond;
    Pair(Calculator _a, Calculator _b) {
      cFirst = _a;
      cSecond = _b;
    }
  }
}


/**
 * NiceParameters
 * choose a pair of parameters to create a nice result.
 *
 */
public class NiceParameters {
  private int pMorph, pPlots;
  private ArrayList<Pair> pairs = new ArrayList<Pair>();

  NiceParameters() {
    pairs.add(new Pair(5, 1000));
    pairs.add(new Pair(30, 50));
    pairs.add(new Pair(125, 17));
    pairs.add(new Pair(200, 5));
    pairs.add(new Pair(500, 3));
    choose();
  }

  public int getMorph() {
    return pMorph;
  }

  public int getPlots() {
    return pPlots;
  }

  public void choose() {
    Pair choice = pairs.get(floor(random(pairs.size())));
    pMorph = choice.pMorph;
    pPlots = choice.pPlots;
  }

  /**
   * Pair
   * holds pair of parameters.
   */
  private class Pair {
    public int pMorph, pPlots;
    Pair(int _a, int _b) {
      pMorph = _a;
      pPlots = _b;
    }
  }
}

/**
 * Calculator
 * collection of mixed up functions.
 */
public abstract class Calculator {

  public float mod;
  public float mult;

  Calculator() {
    mod  = 0.2 * sin(count); //random(0.2, 1.0);
    mult = 0.2 * sin(count); //random(0.1, 1.0);
  }
  
  public float calc(float _a, float _b) {
    return 0.0;
  };
}

class Mountains extends Calculator {
  Mountains() {
    super();
  }
  public float calc(float _a, float _b) {
    return cos(_a * _b) * sin(_a + _b * 0.5);
  }
}


class SeeSaw extends Calculator {
  SeeSaw() {
    super();
  }
  public float calc(float _a, float _b) {
    return cos(_b) * cos(_a * PI);
  }
}

class ThreeStars extends Calculator {
  ThreeStars() {
    super();
  }
  public float calc(float _a, float _b) {
    return sin(_a * TWO_PI + _b);
  }
}

class BigWave extends Calculator {
  BigWave() {
    super();
  }
  public float calc(float _a, float _b) {
    return sin(_a * TWO_PI + _b) * (cos(_b) + cos(_a * PI));
  }
}

class JustATwoOfUs extends Calculator {
  JustATwoOfUs() {
    super();
  }
  public float calc(float _a, float _b) {
    return sin(_a * TWO_PI) * sin(_b);
  }
}

class PieceOfMyHeart extends Calculator {
  PieceOfMyHeart() {
    super();
  }
  public float calc(float _a, float _b) {
    return sin(0.05 * _a * sin(_b));
  }
}

class AndThen extends Calculator {
  AndThen() {
    super();
  }
  public float calc(float _a, float _b) {
    return log(1.0 + _a + pow(sin(_b), 2)) * 0.1;
  }
}

class TwistedSister extends Calculator {
  TwistedSister() {
    super();
  }
  public float calc(float _a, float _b) {
    return (sin((_a * TWO_PI) % _b) * cos(_b % sqrt(_b))) * log(1.0 + _a);
  }
}