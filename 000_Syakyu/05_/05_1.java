float _bgWidth;
float _minWidth;
Obj _obj;
PVector _xy1, _xy2, _xy3, _xy4;
ArrayList<int[]> _paintte;
ArrayList<int[]> _initPaintte;
int _bgCol;
int count = 1;

void setup() {
  size(640, 640);
  _bgWidth = min(width, height);
  _minWidth = _bgWidth * 0.95;

  noStroke();
  _xy1 = new PVector(-_minWidth / 2,  -_minWidth / 2, 0);
  _xy2 = new PVector(+_minWidth / 2, -_minWidth / 2, 0);
  _xy3 = new PVector(+_minWidth / 2,  +_minWidth / 2, 0);
  _xy4 = new PVector(-_minWidth / 2,  +_minWidth / 2, 0);
  
  setObject();
  
  background(_bgCol);
  translate(width / 2, height / 2);
  _obj.update(_xy1, _xy2, _xy3, _xy4);
  _obj.drawObj();
}

ArrayList<int[]> selectColor() {
  ArrayList<int[]> paintte = new ArrayList<int[]>();
  ArrayList<int[]> newPaintte = new ArrayList<int[]>();
  int[] c1 = {50, 20, 15};
  int[] c2 = {50, 20, 15};
  int[] c3 = {50, 20, 15};
  int[] c4 = {50, 20, 15};
  int[] c5 = {50, 20, 15};
  
  newPaintte.add(c1);
  newPaintte.add(c2);
  newPaintte.add(c3);
  newPaintte.add(c4);
  newPaintte.add(c5);
  
  int numCol = 3; //except black and white
  //int[] newPaintte = new int[2];
  
  
  while (newPaintte.size() < numCol && paintte.size() > 0) {
    int index = int(random(paintte.size()));
    newPaintte.add(paintte.get(index));
    //paintte.splice(index, 1);
  }

  return newPaintte;
}

void setObject() {
  _paintte = selectColor();
  int[] c = {50, 20, 15};
  _paintte.add(c); //white
  _bgCol = color(_paintte.get(int(random(4)))[0], _paintte.get(int(random(4)))[1], _paintte.get(int(random(4)))[2]);

  _obj = new Obj(1);
}

class Obj {
  
    float splitNumCurrent;
    float splitNumLimit = 19;
    float shiftAmp = _minWidth / 1600;
    float clearanceRatio = 0.9;
    ArrayList<Obj> arySubObject = new ArrayList<Obj>();
    int splitProbability = 1;
    
    int col;
    float freq = 6;
    float noiseSpeed = 0.0015;
    float minNoiseVal = 0.2;
    float maxNoiseVal = 0.8;
    float gapNoiseVal = maxNoiseVal - minNoiseVal;
    float count = 0;
    
    String splitDirection;
    
    PVector xy1, xy2, xy3, xy4;
    Boolean[] subDisplay = new Boolean[2];
    float[] noiseSeed = new float[2];
    
  Obj(float splitNumC) {
    splitNumCurrent = splitNumC;
    addCol();
    if ((splitNumCurrent < splitNumLimit && random(100) < splitProbability) || splitNumCurrent == 1) { splitObject(); }
  }
  
  void splitObject() {
    float rnd = random(100);
    if (rnd < 1/2) {
       splitDirection = "vertical";
    } else {
      splitDirection = "horizontal";
    }
    
    noiseSeed[0] = random(1000);

    arySubObject.add(new Obj(splitNumCurrent + 1));
    arySubObject.add(new Obj(splitNumCurrent + 1));
  }

  void update(PVector vxy1, PVector vxy2, PVector vxy3, PVector vxy4){
    xy1 = vxy1;
    xy2 = vxy2;
    xy3 = vxy3;
    xy4 = vxy4;
    
    subDisplay[0] = true;
    subDisplay[1] = true;

    if (arySubObject.size() > 0) {
      float[] noiseVal = new float[2];
      //ArrayList<float> noiseVal = new ArrayList<float>();
      float demo = (sin(2*PI * freq * noise(noiseSeed[0] + noiseSpeed * count)) + 1) * gapNoiseVal / 2 + minNoiseVal;
      //noiseVal.add(demo);
      noiseVal[0] = demo;

      if (splitDirection == "vertical") {
        PVector sub1_xy1 = xy1;
        PVector sub1_xy2 = PVector.lerp(xy1, xy2, noiseVal[0]);
        PVector sub1_xy3 = PVector.lerp(xy4, xy3, noiseVal[0]);
        PVector sub1_xy4 = xy4;

        PVector[] aryXy_s1 = resizeObjectFixedShift(sub1_xy1, sub1_xy2, sub1_xy3, sub1_xy4, shiftAmp);
        subDisplay[0] = aryXy_s1[4];
        if (subDisplay[0] == true) {
          arySubObject.get(0).update(aryXy_s1[0], aryXy_s1[1], aryXy_s1[2], aryXy_s1[3]);
        }

        PVector sub2_xy1 = sub1_xy2;
        PVector sub2_xy2 = xy2;
        PVector sub2_xy3 = xy3;
        PVector sub2_xy4 = sub1_xy3;

        PVector[] aryXy_s2 = resizeObjectFixedShift(sub2_xy1, sub2_xy2, sub2_xy3, sub2_xy4, shiftAmp);
        subDisplay[1] = aryXy_s2[4];
        if (subDisplay[1] == true) {
          arySubObject.get(1).update(aryXy_s2[0], aryXy_s2[1], aryXy_s2[2], aryXy_s2[3]);
        }

      } else if (splitDirection == "horizontal") {
        PVector sub1_xy1 = xy1;
        PVector sub1_xy2 = xy2;
        PVector sub1_xy3 = PVector.lerp(xy2, xy3, noiseVal[0]);
        PVector sub1_xy4 = PVector.lerp(xy1, xy4, noiseVal[0]);

        PVector[] aryXy_s1 = resizeObjectFixedShift(sub1_xy1, sub1_xy2, sub1_xy3, sub1_xy4, shiftAmp);
        subDisplay[0] = aryXy_s1[4];
        if (subDisplay[0] == true) {
          arySubObject.get(0).update(aryXy_s1[0], aryXy_s1[1], aryXy_s1[2], aryXy_s1[3]);
        }

        PVector sub2_xy1 = sub1_xy4;
        PVector sub2_xy2 = sub1_xy3;
        PVector sub2_xy3 = xy3;
        PVector sub2_xy4 = xy4;

        PVector[] aryXy_s2 = resizeObjectFixedShift(sub2_xy1, sub2_xy2, sub2_xy3, sub2_xy4, shiftAmp);
        subDisplay[1] = aryXy_s2[4];
        if (subDisplay[1] == true) {
          arySubObject.get(1).update(aryXy_s2[0], aryXy_s2[1], aryXy_s2[2], aryXy_s2[3]);
        }
      }
    }
    count++;
  }
  
  void drawObj() {
    fill(col);
    if (drawCheck() == true) { drawOuterLine(); }

    if (arySubObject.size() != 0) {
      if (subDisplay[0] == true) {
        arySubObject.get(0).drawObj();
      }
      if (subDisplay[1] == true) {
        arySubObject.get(1).drawObj();
      }
    }
  }

  Boolean drawCheck() {
    Boolean drawCheck = false;
    float r = _minWidth / 2;
    float dist1 = xy1.mag();
    float dist2 = xy2.mag();
    float dist3 = xy3.mag();
    float dist4 = xy4.mag();
    if (dist1 < r && dist2 < r && dist3 < r && dist4 < r) { drawCheck = true; }

    return drawCheck;
  }

  PVector calculateCross(PVector xy1, PVector xy2, PVector xy3, PVector xy4) { //cross point of line xy1-xy2 and line xy3-xy4
    //ax+by+c=0
    float a1 = xy1.y - xy2.y;
    float b1 = xy2.x - xy1.x;
    float c1 = xy1.x * xy2.y - xy2.x * xy1.y;
    float a2 = xy3.y - xy4.y;
    float b2 = xy4.x - xy3.x;
    float c2 = xy3.x * xy4.y - xy4.x * xy3.y;
    //cross point of line xy1-2 and line xy3-4
    float xcross = (b1*c2 - b2*c1) / (a1*b2 - a2*b1);
    float ycross = (a2*c1 - a1*c2) / (a1*b2 - a2*b1);
    PVector cross = new PVector(xcross, ycross, 0);

    return cross;
  }

  float crossSign(PVector xy1, PVector xy2, PVector xy3) { //if xy1 is above line xy2-xy3 or not
    //ax+by+c=0
    float a = xy2.y - xy3.y;
    float b = xy3.x - xy2.x;
    float c = xy2.x * xy3.y - xy3.x * xy2.y;
    float xy1PosSign = a * xy1.x + b * xy1.y + c;
    
    return xy1PosSign;
  }

  PVector[] resizeObjectFixedShift(PVector xy1, PVector xy2, PVector xy3, PVector xy4, float shiftAmp){
    Boolean display = true;
    
    PVector vertVec12 = PVector.sub(xy2, xy1);
    vertVec12.setHeading(vertVec12.heading() + PI / 2);
    vertVec12.setMag(shiftAmp);
    
    PVector vertVec23 = PVector.sub(xy3, xy2);
    vertVec23.setHeading(vertVec23.heading() + PI / 2);
    vertVec23.setMag(shiftAmp);
    
    PVector vertVec34 = PVector.sub(xy4, xy3);
    vertVec34.setHeading(vertVec34.heading() + PI / 2);
    vertVec34.setMag(shiftAmp);
    
    PVector vertVec41 = PVector.sub(xy1, xy4);
    vertVec41.setHeading(vertVec41.heading() + PI / 2);
    vertVec41.setMag(shiftAmp);

    PVector xy1_shiftVert12 = PVector.add(xy1, vertVec12);
    PVector xy2_shiftVert12 = PVector.add(xy2, vertVec12);
    PVector xy2_shiftVert23 = PVector.add(xy2, vertVec23);
    PVector xy3_shiftVert23 = PVector.add(xy3, vertVec23);
    PVector xy3_shiftVert34 = PVector.add(xy3, vertVec34);
    PVector xy4_shiftVert34 = PVector.add(xy4, vertVec34);
    PVector xy4_shiftVert41 = PVector.add(xy4, vertVec41);
    PVector xy1_shiftVert41 = PVector.add(xy1, vertVec41);

    PVector xy1s = calculateCross(xy4_shiftVert41, xy1_shiftVert41, xy1_shiftVert12, xy2_shiftVert12);
    PVector xy2s = calculateCross(xy1_shiftVert12, xy2_shiftVert12, xy2_shiftVert23, xy3_shiftVert23);
    PVector xy3s = calculateCross(xy2_shiftVert23, xy3_shiftVert23, xy3_shiftVert34, xy4_shiftVert34);
    PVector xy4s = calculateCross(xy3_shiftVert34, xy4_shiftVert34, xy4_shiftVert41, xy1_shiftVert41);

    if (xy1s.x >= xy2s.x || xy1s.y >= xy4s.y) { display = false; }
    
    PVector[] ret = new PVector[5];
    ret[0] = xy1s;
    ret[1] = xy2s;
    ret[2] = xy3s;
    ret[3] = xy4s;
    ret[4] = display;
    
    return ret;
  }

  void drawOuterLine() {
    ArrayList<PVector> aryCorner1 = calcOuterCorner(xy4, xy1, xy2);
    ArrayList<PVector> aryCorner2 = calcOuterCorner(xy1, xy2, xy3);
    ArrayList<PVector> aryCorner3 = calcOuterCorner(xy2, xy3, xy4);
    ArrayList<PVector> aryCorner4 = calcOuterCorner(xy3, xy4, xy1);
    
    beginShape();
    for (PVector ar : aryCorner1) {
    //for (int i = 0; i < aryCorner1.size(); i++) {
      vertex(ar.x, ar.y);
    }
    for (PVector ar : aryCorner2) {
      vertex(ar.x, ar.y);
    }
    for (PVector ar : aryCorner3) {
      vertex(ar.x, ar.y);
    }
    for (PVector ar : aryCorner4) {
      vertex(ar.x, ar.y);
    }
    endShape(CLOSE);
  }

  ArrayList<PVector> calcOuterCorner(PVector xy1, PVector xy2, PVector xy3) {
    PVector xy21 = PVector.sub(xy1, xy2);
    PVector xyCent21 = xy21.setMag(shiftAmp * clearanceRatio).rotate(PI/2);
    PVector xy23 = PVector.sub(xy3, xy2);
    PVector xyCent23 = xy23.setMag(shiftAmp * clearanceRatio).rotate(-PI/2);
    float angOuterXy2 = abs(PVector.angleBetween(xyCent21, xyCent23)); 
    int numAngSplit = 32;
    float stepAng = angOuterXy2 / numAngSplit;
    
    ArrayList<PVector> aryCornerXy = new ArrayList<PVector>();
    
    for (int i = 0; i < numAngSplit + 1; i++) {
      aryCornerXy.add(PVector.add(xy2, xyCent21));
      xyCent21.rotate(stepAng);
    }

    return aryCornerXy;
  }

  void addCol() {
    col = color(random(_paintte)); //パレットちょっと考える★　→色を数字に
  }
}