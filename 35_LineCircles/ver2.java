/**
 * Johnny on the Monorail.
 * It's an animation of a strange kind of Node Garden.
 */
 
 int count = 1;
  PImage img;
   int nodeCnt  = 30;
  int frmMax   = 24;// * 12; // 24fps x 12sec
  int frmMorph = 24 * 4;  // morphing duration frames
  

  
  String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\35_LineArt\\2\\35_LineArt-30_######.png";

void setup() {
  size(720, 720);
  colorMode(HSB, 360.0, 100.0, 100.0, 100.0);
  rectMode(CENTER);
  smooth();
}

void draw() {
  
  //add----
  print(count);
  background(0.0, 0.0, 90.0, 100.0);
  if (img != null)image(img, 0, 0); //前のイメージを残す
  if (count % 6 == 0) background(0.0, 0.0, 90.0, 100.0);
  
  if (count >= 1800) noLoop();;
  //----
  
  nodeCnt += 1;
  ArrayList<PVector> nodesFrom = new ArrayList<PVector>();
  ArrayList<PVector> nodesTo = setNodes(nodeCnt);

  for (int c = 0; c < frmMax; c++) {
    rotate(PIE/8);
    float easeRatio = InFourthPow(map(c % frmMorph, 0, frmMorph - 1, 0.0, 1.0));
      
    // copy to -> from
    if (c % frmMorph == 0) {
      nodesFrom = new ArrayList<PVector>(nodesTo);
      nodesTo = setNodes(nodeCnt);
    }
 
    blendMode(BLEND);
    

    fill(0.0, 0.0, 90.0, 100.0);
    stroke(0.0, 0.0, 10.0, 100.0);
    strokeWeight(2.0);
    construct(nodesFrom, nodesTo, easeRatio);

    //blendMode(BLEND);
    //casing();

    //add----
    
    saveFrame(outP);
    //----

  }
  
  
  img = get();
  count += 1;
  
}

/**
 * setNodes : set nodes to draw.
 * @param  _cnt  any : node count.
 * @return           : nodes in ArrayList<PVector>.
 */
private ArrayList<PVector> setNodes(int _cnt) {
  ArrayList<PVector> n = new ArrayList<PVector>();
  for (int i = 0; i < _cnt; i++) {
    n.add(new PVector(
                      floor(random(2.0, 9.0)) / 10.0,
                      floor(random(2.0, 9.0)) / 10.0
                      ));
  }
  return n;
}

/**
 * construct : calculate morphing location and draw.
 * @param  _from : nodes of start location of morphing.
 * @param  _to   : nodes of end location of morphing.
 * @param  _rate 0.0 - 1.0 : start(0.0) - end(1.0)
 */
private void construct(ArrayList<PVector> _from, ArrayList<PVector> _to, float _rate) {
  for (int f = 0; f < _from.size() - 1; f++) {
    int cons = 0;
    float xF = _to.get(f).x * _rate + _from.get(f).x * (1.0 - _rate);
    float yF = _to.get(f).y * _rate + _from.get(f).y * (1.0 - _rate);
    for (int t = f + 1; t < _to.size(); t++) {
      float xT = _to.get(t).x * _rate + _from.get(t).x * (1.0 - _rate);
      float yT = _to.get(t).y * _rate + _from.get(t).y * (1.0 - _rate);
      if (abs(xF - xT) < 0.01 || abs(yF - yT) < 0.01) {
        line(xF * width, yF * height, xT * width, yT * height);
        cons++;
      }
    }
    if (cons % 2 == 0) {
      rect(xF * width, yF * height, cons * 5.0, cons * 5.0, 4);
    } else {
      ellipse(xF * width, yF * height, cons * 5.0, cons * 5.0);
    }
  }
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
public void casing() {
  fill(0.0, 0.0, 0.0, 0.0);
  strokeWeight(30.0);
  stroke(0.0, 0.0, 0.0, 100.0);
  rect(width * 0.5, height * 0.5, width, height);
  strokeWeight(28.0);
  stroke(0.0, 0.0, 100.0, 100.0);
  rect(width * 0.5, height * 0.5, width, height);
}