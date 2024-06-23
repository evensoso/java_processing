let _aryR = [];
let _limitCount;
let _aryTriangle = [];
let _aryTriCenter = [];
let _count;
let _aryInitRot = [];
let _aryCentRotYZ = [];
let _numSphere;
let _arySphereCenter = [];

let _aryInitNoiseXYZ = [];
let _aryNoiseRangeXYZ = [];
let _noiseStepT;
let _sphereR;
let _maxAreaR;

function setup() {
  let canvasSize;
  if (windowWidth <= windowHeight) {
    canvasSize = windowWidth;
  } else {
    canvasSize = windowHeight;
  }
  createCanvas(canvasSize, canvasSize, WEBGL);
	document.getElementsByTagName('main')[0].style.margin = 0;
  setAttributes('premultipliedAlpha', true);
  frameRate(30);
  noStroke();

  _limitCount = 3;
  _maxAreaR = width/2.5;
  _numSphere = 5;
  let maxSphereR = width/5;
  for (let i = 0; i < _numSphere; i++) {
    _aryR[i] = maxSphereR;
    let maxCentX = _maxAreaR - _aryR[i];
    let maxCentY = _maxAreaR - _aryR[i];
    let maxCentZ = _maxAreaR - _aryR[i];
    _arySphereCenter[i] = [maxCentX*random(-1, 1), maxCentY*random(-1, 1), maxCentZ*random(-1, 1)];
    let createTriangle = new BaseTriangle(_aryR[i], i, _arySphereCenter[i]);
  }
  
  for (let i = 0; i < 3; i++) {
    _aryInitNoiseXYZ[i] = random(100);
    _aryInitRot[i] = random(360);
  }
  _aryNoiseRangeXYZ[0] = 1.0/1.5/2;
  _aryNoiseRangeXYZ[1] = 1.0/1.5/2;
  _aryNoiseRangeXYZ[2] = 1.0/1.5/2;
  _noiseStepT = 0.008;

  _sphereR = width/1000;

  _count = 0;
}

function draw() {
  background(200);
  directionalLight(105, 105, 105, -1, 1, -1);
  specularMaterial(0);

  rotateX(_aryInitRot[0] + _count/300);
  rotateY(_aryInitRot[1] + _count/100);
  rotateZ(_aryInitRot[2] + _count/200);
  let freq = 4;
  let d = 2;
  for (let j = 0; j < _numSphere; j++) {
    for (let i = 0; i < _aryTriangle[j].length; i++) {
      let noiseVal = sin(freq * 2*PI * noise(
        _aryInitNoiseXYZ[0] + _aryNoiseRangeXYZ[0] * _aryTriCenter[j][i][0] / _maxAreaR,
        _aryInitNoiseXYZ[1] + _aryNoiseRangeXYZ[1] * _aryTriCenter[j][i][1] / _maxAreaR - _noiseStepT * _count,
        _aryInitNoiseXYZ[2] + _aryNoiseRangeXYZ[2] * _aryTriCenter[j][i][2] / _maxAreaR
      ))**d * 1 + 0;
      
      let threshold = 0.5;
      let ratio = (noiseVal-threshold)/(1-threshold);
      if (noiseVal > threshold) {
        push();
        translate(_arySphereCenter[j][0], _arySphereCenter[j][1], _arySphereCenter[j][2]);
        rotateZ(-PI/2);
        rotateX(_aryCentRotYZ[j][i][0]);
        rotateZ(_aryCentRotYZ[j][i][1]);
        let coneRatio = 0.07;
        let coneHeight = _sphereR * ratio;
        translate(0, _aryR[j], 0);
        translate(0, coneHeight/2, 0);
        cone(coneHeight / coneRatio, coneHeight);
        pop();
      }      
    }
  }

  _count ++;
}

class BaseTriangle {
  constructor(r, index, aryCentXYZ) {
    let triangles = [];
    triangles[0] = [[r+aryCentXYZ[0], 0+aryCentXYZ[1], 0+aryCentXYZ[2]], [0+aryCentXYZ[0], 0+aryCentXYZ[1], r+aryCentXYZ[2]], [0+aryCentXYZ[0], -r+aryCentXYZ[1], 0+aryCentXYZ[2]]];
    triangles[1] = [[-r+aryCentXYZ[0], 0+aryCentXYZ[1], 0+aryCentXYZ[2]], [0+aryCentXYZ[0], 0+aryCentXYZ[1], r+aryCentXYZ[2]], [0+aryCentXYZ[0], -r+aryCentXYZ[1], 0+aryCentXYZ[2]]];
    triangles[2] = [[r+aryCentXYZ[0], 0+aryCentXYZ[1], 0+aryCentXYZ[2]], [0+aryCentXYZ[0], 0+aryCentXYZ[1], -r+aryCentXYZ[2]], [0+aryCentXYZ[0], -r+aryCentXYZ[1], 0+aryCentXYZ[2]]];
    triangles[3] = [[-r+aryCentXYZ[0], 0+aryCentXYZ[1], 0+aryCentXYZ[2]], [0+aryCentXYZ[0], 0+aryCentXYZ[1], -r+aryCentXYZ[2]], [0+aryCentXYZ[0], -r+aryCentXYZ[1], 0+aryCentXYZ[2]]];
    triangles[4] = [[r+aryCentXYZ[0], 0+aryCentXYZ[1], 0+aryCentXYZ[2]], [0+aryCentXYZ[0], 0+aryCentXYZ[1], r+aryCentXYZ[2]], [0+aryCentXYZ[0], r+aryCentXYZ[1], 0+aryCentXYZ[2]]];
    triangles[5] = [[-r+aryCentXYZ[0], 0+aryCentXYZ[1], 0+aryCentXYZ[2]], [0+aryCentXYZ[0], 0+aryCentXYZ[1], r+aryCentXYZ[2]], [0+aryCentXYZ[0], r+aryCentXYZ[1], 0+aryCentXYZ[2]]];
    triangles[6] = [[r+aryCentXYZ[0], 0+aryCentXYZ[1], 0+aryCentXYZ[2]], [0+aryCentXYZ[0], 0+aryCentXYZ[1], -r+aryCentXYZ[2]], [0+aryCentXYZ[0], r+aryCentXYZ[1], 0+aryCentXYZ[2]]];
    triangles[7] = [[-r+aryCentXYZ[0], 0+aryCentXYZ[1], 0+aryCentXYZ[2]], [0+aryCentXYZ[0], 0+aryCentXYZ[1], -r+aryCentXYZ[2]], [0+aryCentXYZ[0], r+aryCentXYZ[1], 0+aryCentXYZ[2]]];
    _aryTriangle[index] = [];
    _aryTriCenter[index] = [];
    _aryCentRotYZ[index] = [];
    let countObj = 0;
    for (let i = 0; i < triangles.length; i++) {
      let newSubTriangle = new SubTriangle(triangles[i], countObj + 1, r, index);
    }
  }
}

class SubTriangle {
  constructor(triangle, countObj, r, index) { //triangle = [[x1, y1, z1], [x2, y2, z2], [x3, y3, z3]]
    this.countObj = countObj;
    if (this.countObj <= _limitCount) {
      this.XYZ1 = triangle[0];
      this.XYZ2 = triangle[1];
      this.XYZ3 = triangle[2];
      this.divide(this.XYZ1, this.XYZ2, this.XYZ3, r, index);
      let newTriangle1 = [this.XYZ1, this.newMidXYZ_1_2, this.newMidXYZ_3_1];
      let newTriangle2 = [this.newMidXYZ_1_2, this.XYZ2, this.newMidXYZ_2_3];
      let newTriangle3 = [this.newMidXYZ_3_1, this.newMidXYZ_2_3, this.XYZ3];
      let newTriangle4 = [this.newMidXYZ_1_2, this.newMidXYZ_2_3, this.newMidXYZ_3_1];
      this.triangles = [newTriangle1, newTriangle2, newTriangle3, newTriangle4];
      for (let i = 0; i < this.triangles.length; i++) {
        let newSubTriangle = new SubTriangle(this.triangles[i], this.countObj + 1, r, index);
      }
    } else {
      this.addCenter(triangle, index); //[[x1, y1, z1], [x2, y2, z2], [x3, y3, z3]] -> [ave.x, ave.y, ave.z]
      this.addCentRotYZ(index); //angle X-Z plane, angle X-Y plane
      _aryTriangle[index].push(triangle);
    }
  }
  addCenter(triangle, index) {
    let centX = 0;
    let centY = 0;
    let centZ = 0;
    for (let i = 0; i < triangle.length; i++) {
      centX += triangle[i][0];
      centY += triangle[i][1];
      centZ += triangle[i][2];
    }
    centX /= triangle.length;
    centY /= triangle.length;
    centZ /= triangle.length;
    _aryTriCenter[index].push([centX, centY, centZ]);
  }
  addCentRotYZ(index) {
    for(let i = 0; i < _aryTriCenter[index].length; i++) {
      let rotY = atan2(
        _aryTriCenter[index][i][2] - _arySphereCenter[index][2],
        _aryTriCenter[index][i][0] - _arySphereCenter[index][0]);
      let rx = (
        (_aryTriCenter[index][i][2] - _arySphereCenter[index][2])**2 +
        (_aryTriCenter[index][i][0] - _arySphereCenter[index][0])**2
        )**0.5;
      let ry = _aryTriCenter[index][i][1] - _arySphereCenter[index][1];
      let rotZ = atan2(ry, rx);
      _aryCentRotYZ[index][i] = [rotY, rotZ];
    }
  }
  divide(XYZ1, XYZ2, XYZ3, r, index) {
    let midXYZ_1_2 = [
      (XYZ1[0] + XYZ2[0])/2,
      (XYZ1[1] + XYZ2[1])/2,
      (XYZ1[2] + XYZ2[2])/2
    ];
    let midXYZ_2_3 = [
      (XYZ2[0] + XYZ3[0])/2,
      (XYZ2[1] + XYZ3[1])/2,
      (XYZ2[2] + XYZ3[2])/2
    ];
    let midXYZ_3_1 = [
      (XYZ3[0] + XYZ1[0])/2,
      (XYZ3[1] + XYZ1[1])/2,
      (XYZ3[2] + XYZ1[2])/2
    ];
    let distMid_1_2 = (
      (midXYZ_1_2[0] - _arySphereCenter[index][0])**2 +
      (midXYZ_1_2[1] - _arySphereCenter[index][1])**2 +
      (midXYZ_1_2[2] - _arySphereCenter[index][2])**2)**0.5;
    let distMid_2_3 = (
      (midXYZ_2_3[0] - _arySphereCenter[index][0])**2 +
      (midXYZ_2_3[1] - _arySphereCenter[index][1])**2 +
      (midXYZ_2_3[2] - _arySphereCenter[index][2])**2)**0.5;
    let distMid_3_1 = (
      (midXYZ_3_1[0] - _arySphereCenter[index][0])**2 +
      (midXYZ_3_1[1] - _arySphereCenter[index][1])**2 +
      (midXYZ_3_1[2] - _arySphereCenter[index][2])**2)**0.5;
    this.newMidXYZ_1_2 = [
      (midXYZ_1_2[0] - _arySphereCenter[index][0]) / distMid_1_2 * r + _arySphereCenter[index][0],
      (midXYZ_1_2[1] - _arySphereCenter[index][1]) / distMid_1_2 * r + _arySphereCenter[index][1],
      (midXYZ_1_2[2] - _arySphereCenter[index][2]) / distMid_1_2 * r + _arySphereCenter[index][2]
    ];
    this.newMidXYZ_2_3 = [
      (midXYZ_2_3[0] - _arySphereCenter[index][0]) / distMid_2_3 * r + _arySphereCenter[index][0],
      (midXYZ_2_3[1] - _arySphereCenter[index][1]) / distMid_2_3 * r + _arySphereCenter[index][1],
      (midXYZ_2_3[2] - _arySphereCenter[index][2]) / distMid_2_3 * r + _arySphereCenter[index][2]
    ];
    this.newMidXYZ_3_1 = [
      (midXYZ_3_1[0] - _arySphereCenter[index][0]) / distMid_3_1 * r + _arySphereCenter[index][0],
      (midXYZ_3_1[1] - _arySphereCenter[index][1]) / distMid_3_1 * r + _arySphereCenter[index][1],
      (midXYZ_3_1[2] - _arySphereCenter[index][2]) / distMid_3_1 * r + _arySphereCenter[index][2]
    ];
  }
}

function mouseReleased() {
  _aryR = [];
  _aryTriangle = [];
  _aryTriCenter = [];
  _aryInitRot = [];
  _aryCentRotYZ = [];
  _arySphereCenter = [];
  _aryInitNoiseXYZ = [];
  _aryNoiseRangeXYZ = [];

  _limitCount = 3;
  _maxAreaR = width/2.5;
  _numSphere = 5;
  let maxSphereR = width/5;
  for (let i = 0; i < _numSphere; i++) {
    _aryR[i] = maxSphereR;
    let maxCentX = _maxAreaR - _aryR[i];
    let maxCentY = _maxAreaR - _aryR[i];
    let maxCentZ = _maxAreaR - _aryR[i];
    _arySphereCenter[i] = [maxCentX*random(-1, 1), maxCentY*random(-1, 1), maxCentZ*random(-1, 1)];
    let createTriangle = new BaseTriangle(_aryR[i], i, _arySphereCenter[i]);
  }
  
  for (let i = 0; i < 3; i++) {
    _aryInitNoiseXYZ[i] = random(100);
    _aryInitRot[i] = random(360);
  }
  _aryNoiseRangeXYZ[0] = 1.0/1.5/2;
  _aryNoiseRangeXYZ[1] = 1.0/1.5/2;
  _aryNoiseRangeXYZ[2] = 1.0/1.5/2;
  _noiseStepT = 0.008;

  _sphereR = width/1000;

  _count = 0;
}