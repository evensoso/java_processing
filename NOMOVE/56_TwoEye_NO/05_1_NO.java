//For the #WCCChallenge, theme: "eyes"

float dil = 0;
float tdil;
PVector ang, tang;
Boolean auto = true;
Boolean lids = true;
float lim, eyesep;
int colorA, colorB, tcolorA;
color[] colors;
float XX, YY;

int cindex = 0;

void setup() {
  size(640, 640);
  lim = min(width, height);
  tdil = 0;
  ang =  new PVector(0,0);
  tang = new PVector(0,0); // createVector(0, 0);
  color[] colors2 = {color(66, 140, 171, 128), color(150, 75, 0, 128), color(153, 121, 80, 128), color(73, 118, 101, 128), color(46, 83, 111, 128), color(161, 202, 241, 128)};
  colors = colors2;
  tcolorA = colors[cindex];
  colorA = color(0, 0, 0, 128);
  colorB = color(228,217,119,0.5);
  eyesep = lim / 1.6;
}

void draw() {
  if (auto) {
    XX = width * noise(frameCount / 100);
    YY = height * noise(1000 + frameCount / 50);
  }
  tdil = map(YY, 0, lim, lim / 3, 2 * lim / 3); //, true);
  dil = lerp(dil, tdil, 0.2);
  tang.x = map(XX, 0, width, -PI / 7.5, PI / 7.5);
  tang.y = map(YY, 0, height, -PI / 14, PI / 14);
  ang.lerp(tang, 0.2);
  colorA = lerpColor(colorA, tcolorA, frameCount / 150);
  background(0);
  translate(width / 2, height / 2);
  drawEye(lim, -eyesep, 0);
  drawEye(lim, eyesep, 0);
  
  //------
  drawEye(640, -400, 0);
  
  if (lids) {
    drawLid(-eyesep, 0, 1, 1, lim);
    drawLid(eyesep, 0, -1, 1, lim);
  }
}

void drawEye(float esize, float x, float y) {
  push();
  translate(x, y);
  push();
  noStroke();
  fill(255);
  ellipse(0, 0, esize / 1.5, esize / 1.5);
  translate(sin(ang.x) * esize / 3, sin(ang.y) * esize / 3);
  fill(0);
  ellipse(0, 0, (esize / 2.5) * cos(ang.x), (esize / 2.5) * cos(ang.y));
  fill(colorA);
  ellipse(0, 0, (esize / 2.4) * cos(ang.x), (esize / 2.4) * cos(ang.y));
  fill(0);
  translate(sin(ang.x) * esize / TAU, 0);
  ellipse(0, 0, (esize / 3 - dil / 3) * cos(ang.x), esize / 3 - dil / 3);
  translate(-sin(ang.x) * height / TAU, 0);
  for (int a = 0; a < TAU; a += TAU / 50) {
    push();
    translate((esize / 5.1) * cos(a), (esize / 5.1) * sin(a));
    rotate(a - PI / 12);
    for (int i = 0; i < 24; i++) {
      stroke(lerpColor(colorA, colorB, i / 24));
      strokeWeight(map(i, 24, 0, esize / 50, esize / 200));
      translate((-(esize / 2.1) - 1.15 * dil) / 180 + (sin(ang.x) * cos(a) * 5), -sin(ang.x) * sin(a) * 5);
      rotate(sin(i) / 4);
      point(0, 0);
    }
    pop();
  }
  pop();
  push();
  noStroke();
  fill(255, 150);
  rotate(-PI / 8);
  rect(esize / 20, -esize / 15, esize / 40, esize / 40);
  rotate(PI / 8);
  pop();
  pop();
}

void drawLid(float x, float y, float flipx, float flipy, float lsize) {
  push();
  translate(x, y - lsize / 20);
  scale(flipx, flipy);
  noStroke();
  fill(0);
  beginShape();
  curveVertex(0, -lsize / 2);
  curveVertex(lsize / 4, -lsize / 3);
  curveVertex(lsize / 2.9, lsize / 12);
  curveVertex(lsize / 12, -lsize / 2 + dil / 1.1);
  curveVertex(-lsize / 3, -lsize / 2 + dil / 1.1);
  curveVertex(-lsize / 3, -lsize / 12);
  curveVertex(-lsize / 4, -lsize / 3);
  curveVertex(0, -lsize / 2);
  endShape(CLOSE);
  pop();

}

/*
void mousePressed() {
  auto = !auto;
  cindex=(cindex+1)%colors.length;
  tcolorA = colors[cindex];
  frameCount = 0;
}

void keyPressed() {
  if (keyCode == 32) lids = !lids; //remove lids
  if (keyCode == 13) { // move eyes together for OP thumbnail
    if (eyesep == (lim / 1.6)) eyesep = lim / 2.5;
    else eyesep = lim / 1.6;
  }
}
*/