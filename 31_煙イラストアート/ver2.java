/**
 * Mandrake Root.
 * Shuffle the n-th of morphing nodes.
 */

int frmRate  = 24;
int cycles   = 5;                 // animation cycle no
int count = 1;
     
String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\31_Smoke\\2\\31_Smoke-30_######.png";
    
void setup() {
  size(720, 720);
  colorMode(HSB, 360.0, 100.0, 100.0, 100.0);
  smooth();
  //noLoop();
}

void draw() {
  print(count);
  cycles += sin(count);

  int   frmMorph = floor(frmRate * 2.5); // morphing duration frames
  int   frmMax   = frmMorph * cycles; // whole frames
  float hueBase  = random(360.0);

  // Array of various shape of roots.
  //   Array of roots.
  //     Array of the locations of the nodes of the root.
  ArrayList<ArrayList<ArrayList<PVector>>> roots = new ArrayList<ArrayList<ArrayList<PVector>>>();
  for (int i = 0; i < cycles; i++) {
    roots.add(digOut());
  }
  int rootMax = roots.get(0).size();  //Index 0 out of bounds for length 0
  
  ArrayList<ArrayList<PVector>> rootsFrom = new ArrayList<ArrayList<PVector>>();
  ArrayList<ArrayList<PVector>> rootsTo   = new ArrayList<ArrayList<PVector>>();
  IntList rootNo = new IntList(); // for shuffling the root from to
  for (int i = 0; i < rootMax; i++) {
    rootNo.append(i);
  }
  int cycleCnt = 0;
  for (int c = 0; c < frmMax; c++) {
    // morphing start
    if (c % frmMorph == 0) {
      cycleCnt = c / frmMorph;
      rootsFrom = roots.get(cycleCnt);
      rootsTo   = roots.get((cycleCnt + 1) % cycles);
      rootNo.shuffle();
    }

    // easing calculation
    float frmRatio   = map(c % frmMorph, 0, frmMorph - 1, 0.0, 1.0);
    float morphRatio = easeInOutCubic(frmRatio);
    float sizeRatio  = InFourthPow(frmRatio);

    // draw roots
    background(0.0, 0.0, 90.0, 100.0);
    strokeWeight(1.0);
    stroke(0.0, 0.0, 10.0, 100.0);
    for (int i = 0; i < rootMax; i++) {
      // j is for choose random root
      float rootRatio = map(i, 0, rootMax, 0.0, 1.0);
      int j = rootNo.get(i);
      drawRoot(rootsFrom.get(i), rootsTo.get(j), rootRatio, morphRatio, sizeRatio, hueBase);
    }

    // for pretty casing
    //casing();
    
    //add----
    saveFrame(outP);
    //----
  }
  count += 1;
}

/**
 * drawRoot : draw root with morphing calculation.
 * @param  _rootF, _rootT : roots to draw, morphing from and to
 * @param  _ratioR : n-th root of the roots ratio
 * @param  _ratioM : morphing ratio
 * @param  _ratioS : size ratio
 * @param  _hue    : base color
 */
void drawRoot(ArrayList<PVector> _rootF, ArrayList<PVector> _rootT, float _ratioR, float _ratioM, float _ratioS, float _hue) {

  float rT = _ratioM * constrain(_ratioR + _ratioM * _ratioM, 0.0, 1.0);
  float rF = 1.0 - rT;

  int nodeLen = _rootF.size();
  for (int i = 0; i < nodeLen; i++) {
    PVector nF = _rootF.get(i);
    PVector nT = _rootT.get(i);
    float nX = (nF.x * rF + nT.x * rT);
    float nY = (nF.y * rF + nT.y * rT);

    float nRti = map(i, 0, nodeLen, 0.0, 1.0);
    float nHue = _hue + nRti * 60.0 + floor((_ratioR * 800.0) % 4.0) * 20.0;;
    float nSat = map(nRti, 0.0, 1.0, 40.0, 80.0);
    float nBri = map(nRti, 0.0, 1.0, 80.0, 60.0);
    float nSiz = 40.0 * sin(PI * nRti) * (1.0 + sin(PI * _ratioS) * 0.8);

    pushMatrix();
    // for up side down
    // translate(width * 0.5, width * 1.25);
    // rotate(-PI * 0.75);
    translate(width * 0.5, -height * 0.25);
    rotate(PI * 0.25);
    fill(nHue % 360.0, nSat, nBri, 100.0);
    ellipse(nX * width, nY * height, nSiz, nSiz);
    popMatrix();
  }
}

/**
 * digOut : calculate the nodes of the roots
 */
private ArrayList<ArrayList<PVector>> digOut() {
  // shape parameters
  int   plotMax  = 15;
  float plotDiv  = 0.02;
  float plotMult = abs(3 * sin(count));//random(1.0, 3.0);
  float initS    = 0.18;
  float initE    = 0.85;
  float initDiv  = 0.1;
  float paramA   = abs(2 * sin(count)); //random(1.0, 2.0);
  float paramB   = abs(3.5 * sin(count)); //random(3.5, 4.5);
  
  ArrayList<ArrayList<PVector>> lines = new ArrayList<ArrayList<PVector>>();
  float npX = 0.0;
  float npY = 0.0;
  for (float yInit = initS; yInit <= initE; yInit += initDiv) {
    for (float xInit = initS; xInit <= initE; xInit += initDiv) {
      float div = 0.2 + pow(xInit - yInit, 2) * 0.1;
      float xPoint = xInit + npX * div;
      float yPoint = yInit + npY * div;

      ArrayList<PVector> line = new ArrayList<PVector>();
      for (int plotCnt = 0; plotCnt < plotMax; ++plotCnt) {
        float xPrev = xPoint;
        float yPrev = yPoint;
        xPoint += plotDiv * cos(TWO_PI * customFunc(xPrev * paramA, yPrev * paramB) * plotMult);
        yPoint += plotDiv * cos(TWO_PI * customFunc(yPrev * paramA, xPrev * paramB) * plotMult);
        line.add(new PVector(xPoint, yPoint));
      }

      lines.add(line);
    }
  }
  return(lines);
}

/**
 * customFunc : returns almost random but interesting value
 */
float customFunc(float _x, float _y) {
  return pow(sin(_x), 3) * cos(pow(_y, 2));
}

/**
 * easeInOutCubic easing function.
 * @param  t     0.0 - 1.0 : linear value.
 * @return float 0.0 - 1.0 : eased value.
 */
float easeInOutCubic(float t) {
  t *= 2.0;
  if (t < 1.0) {
    return pow(t, 3) / 2.0;
  }
  t -= 2.0;
  return (pow(t, 3) + 2.0) / 2.0;
}
  
/**
 * InFourthPow : easing function.
 * @param  _t    0.0 - 1.0 : linear value.
 * @return       0.0 - 1.0 : eased value.
 */
private float InFourthPow(float _t) {
  return 1.0 - pow(1.0 - _t, 4);
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