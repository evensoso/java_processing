let _bgWidth;
let _minWidth;
let _obj;
let _xy1;
let _xy2;
let _xy3;
let _xy4;
let _palette;
let _initPalette;
let _bgCol;

function setup() {
  createCanvas(windowWidth, windowHeight);
  _bgWidth = min(width, height);
  _minWidth = _bgWidth * 0.95;

  noStroke();
  _xy1 = createVector(-_minWidth / 2, -_minWidth / 2);
  _xy2 = createVector(+_minWidth / 2, -_minWidth / 2);
  _xy3 = createVector(+_minWidth / 2, +_minWidth / 2);
  _xy4 = createVector(-_minWidth / 2, +_minWidth / 2);

  setObject();
	
	background(_bgCol);
  translate(width / 2, height / 2);
  _obj.update(_xy1, _xy2, _xy3, _xy4);
  _obj.draw();
}

function selectColor() {
  let palette;
  palette = ["#F85A3E", "#29339B", "#B7990D", "#44001A", "#51A3A3"];
  let numCol = 3; //except black and white
  let newPalette = [];
  while (newPalette.length < numCol && palette.length > 0) {
    let index = int(random(palette.length));
    newPalette.push(palette[index]);
    palette.splice(index, 1);
  }

  return newPalette;
}

function setObject() {
  _palette = selectColor();
  _palette.push("#FFF4EC"); //white
  _bgCol = color(random(_palette));

  _obj = new Obj(1);
}

class Obj {
  constructor(splitNumCurrent) {
    this.splitNumCurrent = splitNumCurrent;
    this.splitNumLimit = 19;
    this.shiftAmp = _minWidth / 1600;
    this.clearanceRatio = 0.9;
    this.arySubObject = [];

    this.addCol();

    let splitProbability = 1;
    if ((splitNumCurrent < this.splitNumLimit && random() < splitProbability) || this.splitNumCurrent == 1) { this.splitObject(); }
  }
  splitObject() {
    let rnd = random();
    if (rnd < 1/2) {
      this.splitDirection = "vertical";
    } else {
      this.splitDirection = "horizontal";
    }
    this.noiseSeed = [random(1000)];
    this.noiseSpeed = 0.0015;
    this.freq = 6;
    this.minNoiseVal = 0.2;
    this.maxNoiseVal = 0.8;
    this.gapNoiseVal = this.maxNoiseVal - this.minNoiseVal;
    this.count = 0;
    this.arySubObject = [
      new Obj(this.splitNumCurrent + 1),
      new Obj(this.splitNumCurrent + 1)];
  }
  update(xy1, xy2, xy3, xy4) {
    this.xy1 = xy1;
    this.xy2 = xy2;
    this.xy3 = xy3;
    this.xy4 = xy4;

    this.subDisplay = [true, true];

    if (this.arySubObject.length > 0) {
      let noiseVal =[
        (sin(2*PI * this.freq * noise(this.noiseSeed[0] + this.noiseSpeed * this.count)) + 1) * this.gapNoiseVal / 2 + this.minNoiseVal];

      if (this.splitDirection == "vertical") {
        let sub1_xy1 = xy1;
        let sub1_xy2 = p5.Vector.lerp(xy1, xy2, noiseVal[0]);
        let sub1_xy3 = p5.Vector.lerp(xy4, xy3, noiseVal[0]);
        let sub1_xy4 = xy4;

        let aryXy_s1 = this.resizeObjectFixedShift(sub1_xy1, sub1_xy2, sub1_xy3, sub1_xy4, this.shiftAmp);
        this.subDisplay[0] = aryXy_s1[4];
        if (this.subDisplay[0] == true) {
          this.arySubObject[0].update(aryXy_s1[0], aryXy_s1[1], aryXy_s1[2], aryXy_s1[3]);
        }

        let sub2_xy1 = sub1_xy2;
        let sub2_xy2 = xy2;
        let sub2_xy3 = xy3;
        let sub2_xy4 = sub1_xy3;

        let aryXy_s2 = this.resizeObjectFixedShift(sub2_xy1, sub2_xy2, sub2_xy3, sub2_xy4, this.shiftAmp);
        this.subDisplay[1] = aryXy_s2[4];
        if (this.subDisplay[1] == true) {
          this.arySubObject[1].update(aryXy_s2[0], aryXy_s2[1], aryXy_s2[2], aryXy_s2[3]);
        }

      } else if (this.splitDirection == "horizontal") {
        let sub1_xy1 = xy1;
        let sub1_xy2 = xy2;
        let sub1_xy3 = p5.Vector.lerp(xy2, xy3, noiseVal[0]);
        let sub1_xy4 = p5.Vector.lerp(xy1, xy4, noiseVal[0]);

        let aryXy_s1 = this.resizeObjectFixedShift(sub1_xy1, sub1_xy2, sub1_xy3, sub1_xy4, this.shiftAmp);
        this.subDisplay[0] = aryXy_s1[4];
        if (this.subDisplay[0] == true) {
          this.arySubObject[0].update(aryXy_s1[0], aryXy_s1[1], aryXy_s1[2], aryXy_s1[3]);
        }

        let sub2_xy1 = sub1_xy4;
        let sub2_xy2 = sub1_xy3;
        let sub2_xy3 = xy3;
        let sub2_xy4 = xy4;

        let aryXy_s2 = this.resizeObjectFixedShift(sub2_xy1, sub2_xy2, sub2_xy3, sub2_xy4, this.shiftAmp);
        this.subDisplay[1] = aryXy_s2[4];
        if (this.subDisplay[1] == true) {
          this.arySubObject[1].update(aryXy_s2[0], aryXy_s2[1], aryXy_s2[2], aryXy_s2[3]);
        }
      }
    }
    this.count++;
  }
  draw() {
    fill(this.col);
    if (this.drawCheck() == true) { this.drawOuterLine(); }

    if (this.arySubObject.length != 0) {
      if (this.subDisplay[0] == true) {
        this.arySubObject[0].draw();
      }
      if (this.subDisplay[1] == true) {
        this.arySubObject[1].draw();
      }
    }
  }

  drawCheck() {
    let drawCheck = false;
    let r = _minWidth / 2;
    let dist1 = this.xy1.mag();
    let dist2 = this.xy2.mag();
    let dist3 = this.xy3.mag();
    let dist4 = this.xy4.mag();
    if (dist1 < r && dist2 < r && dist3 < r && dist4 < r) { drawCheck = true; }

    return drawCheck;
  }

  calculateCross(xy1, xy2, xy3, xy4) { //cross point of line xy1-xy2 and line xy3-xy4
    //ax+by+c=0
    let a1 = xy1.y - xy2.y;
    let b1 = xy2.x - xy1.x;
    let c1 = xy1.x * xy2.y - xy2.x * xy1.y;
    let a2 = xy3.y - xy4.y;
    let b2 = xy4.x - xy3.x;
    let c2 = xy3.x * xy4.y - xy4.x * xy3.y;
    //cross point of line xy1-2 and line xy3-4
    let xcross = (b1*c2 - b2*c1) / (a1*b2 - a2*b1);
    let ycross = (a2*c1 - a1*c2) / (a1*b2 - a2*b1);
    let cross = createVector(xcross, ycross);

    return cross;
  }

  crossSign(xy1, xy2, xy3) { //if xy1 is above line xy2-xy3 or not
    //ax+by+c=0
    let a = xy2.y - xy3.y;
    let b = xy3.x - xy2.x;
    let c = xy2.x * xy3.y - xy3.x * xy2.y;
    let xy1PosSign = a * xy1.x + b * xy1.y + c;
    
    return xy1PosSign;
  }

  resizeObjectFixedShift(xy1, xy2, xy3, xy4, shiftAmp) {
    let display = true;

    let vertVec12 = p5.Vector.sub(xy2, xy1);
    vertVec12.setHeading(vertVec12.heading() + PI / 2);
    vertVec12.setMag(shiftAmp);
    let vertVec23 = p5.Vector.sub(xy3, xy2);
    vertVec23.setHeading(vertVec23.heading() + PI / 2);
    vertVec23.setMag(shiftAmp);
    let vertVec34 = p5.Vector.sub(xy4, xy3);
    vertVec34.setHeading(vertVec34.heading() + PI / 2);
    vertVec34.setMag(shiftAmp);
    let vertVec41 = p5.Vector.sub(xy1, xy4);
    vertVec41.setHeading(vertVec41.heading() + PI / 2);
    vertVec41.setMag(shiftAmp);

    let xy1_shiftVert12 = p5.Vector.add(xy1, vertVec12);
    let xy2_shiftVert12 = p5.Vector.add(xy2, vertVec12);
    let xy2_shiftVert23 = p5.Vector.add(xy2, vertVec23);
    let xy3_shiftVert23 = p5.Vector.add(xy3, vertVec23);
    let xy3_shiftVert34 = p5.Vector.add(xy3, vertVec34);
    let xy4_shiftVert34 = p5.Vector.add(xy4, vertVec34);
    let xy4_shiftVert41 = p5.Vector.add(xy4, vertVec41);
    let xy1_shiftVert41 = p5.Vector.add(xy1, vertVec41);

    let xy1s = this.calculateCross(xy4_shiftVert41, xy1_shiftVert41, xy1_shiftVert12, xy2_shiftVert12);
    let xy2s = this.calculateCross(xy1_shiftVert12, xy2_shiftVert12, xy2_shiftVert23, xy3_shiftVert23);
    let xy3s = this.calculateCross(xy2_shiftVert23, xy3_shiftVert23, xy3_shiftVert34, xy4_shiftVert34);
    let xy4s = this.calculateCross(xy3_shiftVert34, xy4_shiftVert34, xy4_shiftVert41, xy1_shiftVert41);

    if (xy1s.x >= xy2s.x || xy1s.y >= xy4s.y) { display = false; }

    return [xy1s, xy2s, xy3s, xy4s, display];
  }

  drawOuterLine() {
    let aryCorner1 = this.calcOuterCorner(this.xy4, this.xy1, this.xy2);
    let aryCorner2 = this.calcOuterCorner(this.xy1, this.xy2, this.xy3);
    let aryCorner3 = this.calcOuterCorner(this.xy2, this.xy3, this.xy4);
    let aryCorner4 = this.calcOuterCorner(this.xy3, this.xy4, this.xy1);
    beginShape();
    for (let i = 0; i < aryCorner1.length; i++) {
      vertex(aryCorner1[i].x, aryCorner1[i].y);
    }
    for (let i = 0; i < aryCorner2.length; i++) {
      vertex(aryCorner2[i].x, aryCorner2[i].y);
    }
    for (let i = 0; i < aryCorner3.length; i++) {
      vertex(aryCorner3[i].x, aryCorner3[i].y);
    }
    for (let i = 0; i < aryCorner4.length; i++) {
      vertex(aryCorner4[i].x, aryCorner4[i].y);
    }
    endShape(CLOSE);
  }

  calcOuterCorner(xy1, xy2, xy3) { //corner = xy2
    let xy21 = p5.Vector.sub(xy1, xy2);
    let xyCent21 = xy21.setMag(this.shiftAmp * this.clearanceRatio).rotate(PI/2);
    let xy23 = p5.Vector.sub(xy3, xy2);
    let xyCent23 = xy23.setMag(this.shiftAmp * this.clearanceRatio).rotate(-PI/2);
    let angOuterXy2 = abs(xyCent21.angleBetween(xyCent23));
    let aryCornerXy = [];
    let numAngSplit = 32;
    let stepAng = angOuterXy2 / numAngSplit;
    for (let i = 0; i < numAngSplit + 1; i++) {
      aryCornerXy.push(p5.Vector.add(xy2, xyCent21));
      xyCent21.rotate(stepAng);
    }

    return aryCornerXy;
  }

  addCol() {
    this.col = color(random(_palette));
  }
}