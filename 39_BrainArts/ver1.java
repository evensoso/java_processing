/**
 * The Colour Out of Space.
 * A creative coding that draws some shape on polar coordinates.
 * 
 * Processing 3.5.3
 * @author @deconbatch
 * @version 0.1
 * created 0.1 2020.03.10
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
  float baseHue = random(360);
  Dna   dna     = new Dna();
  
  translate(width / 2, height / 2);
  for (int frmCnt = 0; frmCnt < frmMax; frmCnt++) {

    float rndRotate = random(PI);
    ArrayList<PVector> pvs = calcShape(dna);
    baseHue += 120.0;

    noiseSeed(floor(baseHue + rndRotate));
    
    blendMode(ADD);
    background(0.0, 0.0, 0.0, 100.0);
    pushMatrix();
    rotate(rndRotate);
    drawPolar(baseHue, pvs);
    popMatrix();  
    
    blendMode(BLEND);
    casing();
    
    //add----
    String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\39_Lissajous\\1\\39_Penrose-30_######.png";
    saveFrame(outP);
    //----

  }
  //exit();
}

/**
 * calcShape : calculate some shape on normal coodinate system and stock these in PVector ArrayList.
 * @param  _dns     : shape parameters.
 * @return calculated results as ArrayList<PVector>.
 */
ArrayList calcShape(Dna _dna) {

  int     pMax = 10000;
  float[] dnas = _dna.getRandomDna();
  float   djA  = dnas[0];
  float   djB  = dnas[1];
  float   djC  = dnas[2];
  float   djD  = dnas[3];

  ArrayList<PVector> pvs = new ArrayList<PVector>();
  for (int pCnt = 0; pCnt < pMax; pCnt++) {

    // It's not De Jong Attractors!
    float prevX = random(-2.0, 2.0);
    float prevY = random(-2.0, 2.0);
    float currX = sin(djA * prevY) - cos(djB * prevX);
    float currY = sin(djC * prevX) - cos(djD * prevY);
    pvs.add(new PVector(currX, currY));

  }
  return pvs;
}

/**
 * drawPolar : draws shape on polar coordinate system.
 * @param  _baseHue : drawing color.
 * @param  _pvs     : calculated coodinates.
 */
void drawPolar(float _baseHue, ArrayList<PVector> _pvs) {

  float baseWeight = 0.0001;
  float baseRadius = min(width, height) / 6.5;

  float pRadius = random(0.25, 1.25) * HALF_PI;
  float pOrigin = random(-1.0, 1.0);
  float pMulti  = map(abs(pOrigin), 0.0, 1.0, 1.3, 1.0);

  noFill();
  for (PVector ppv : _pvs) {
    for (PVector fpv : _pvs) {
      float distance = dist(ppv.x, ppv.y, fpv.x, fpv.y);
      if (distance > 0.05 && distance < 0.08) {
        float pHue = _baseHue + noise(ppv.x * 0.5, ppv.y * 0.5) * 360.0;
        float pWgt = baseWeight * (2.0 + dist(ppv.x, ppv.y, 0.0, 0.0) / baseRadius) / (distance * distance) * noise(20.0, fpv.x, fpv.y);
        strokeWeight(pWgt);
        stroke(
               pHue % 360.0,
               noise(10.0, fpv.x, fpv.y) * 100.0,
               noise(ppv.x, ppv.y) * 60.0,
               100.0
               );
        line(
             baseRadius * (ppv.x + pOrigin) * cos(ppv.y * pRadius) * pMulti,
             baseRadius * (ppv.x + pOrigin) * sin(ppv.y * pRadius) * pMulti,
             baseRadius * (fpv.x + pOrigin) * cos(fpv.y * pRadius) * pMulti,
             baseRadius * (fpv.x + pOrigin) * sin(fpv.y * pRadius) * pMulti
             );
      }
    }
  }

}

/**
 * casing : draw fancy casing
 */
private void casing() {
  
  fill(0.0, 0.0, 0.0, 0.0);
  strokeWeight(40.0);
  stroke(0.0, 0.0, 0.0, 100.0);
  rect(0.0, 0.0, width, height);
  strokeWeight(30.0);
  stroke(0.0, 0.0, 90.0, 100.0);
  rect(0.0, 0.0, width, height);
    
}

/**
 * Dna : hold nice shape parameters.
 */
class Dna {

  ArrayList<float[]> dnaList;

  Dna() {

    float[][] dnas = {

      {1.573155, -0.97604156, -0.49153805, -1.7673008},
      {-1.5568858, -1.5821334, -1.2908077, -1.0470141},
      { 1.5649674 , 1.2574794 , -1.3866975 , 1.173096 },
      { 0.9671733 , 1.4473511 , -1.5457076 , 0.95127994 },
      { 1.0348322 , 0.81519204 , -1.1046926 , 1.5648632 },
      { 1.4077392 , -0.9176125 , -1.53935 , -1.5119051 },
      { 1.3408806 , -0.9036783 , 1.3128784 , 0.79536873 },
      {-0.6002734, 1.6509838, 1.8099892, -1.0964627},
      {-1.0119417, 0.9379139, 1.3950387, 1.7682558},
      {-1.2209034, -1.9364693, 1.624037, -0.89463496},
      {-5.6786957, -0.93300486, -4.7100835, -0.86868095},
      {3.0192666, 5.01946, 0.23271036, 4.283874},
      { 1.5101616 , 1.3572946 , -0.89910567 , 1.4545844 },
      { 1.0751489 , 1.3426301 , -1.2923251 , -1.3146731 },
      { 1.196357 , -1.3442354 , -1.0326693 , -1.5694741 },
      { 1.526092 , 1.3366599 , -1.5353475 , 1.3622558 },
      { -1.9639189, -1.0532432, -1.9515417, -1.8730514 },
      {0.17204833, -1.7023778, 0.56470084, 1.963506},

    };

    dnaList = new ArrayList<float[]>();
    for (float[] dna : dnas) {
      dnaList.add(dna);
    };

  }

  float[] getRandomDna() {
    int choice = floor(random(dnaList.size()));
    float[] dnaChose = dnaList.get(choice);
    dnaList.remove(choice);
    return dnaChose;
  }

}