// This is the work for the #WCCChallenge topic 'Fungi'
//
let AWAIT_LINES_PER_TICK = 6;
let mainHue = 35;

async function setup() {
  createCanvas(windowWidth, windowHeight);
  colorMode(HSB);
  strokeCap(SQUARE);
  background(0, 0, 3);;

  let treeCount = int(random(2, 6));

  let themeHue = random(0, 360);

  for (let i = 0; i < treeCount; i++) {
    await drawTree();
  }
}

async function drawTree() {

  dotTrailDensity = 0.2;
  dotNoiseZ += random(100, 300);
  areaNoiseZ += random(100, 300);
  treeTurnNoiseZ += random(100, 300);

  let pointStartX = random(-0.1, 1.1) * width;
  let pointStartY = 1.1 * height;

  let pointEndX = random(-0.1, 1.1) * width;
  let pointEndY = -0.1 * height;

  let endRandom = random();
  if (endRandom < 0.3) {
    pointEndX = -0.1 * width;
    pointEndY = random(-0.1, 0.3) * height;
  }
  else if (endRandom < 0.6) {
    pointEndX = 1.1 * width;
    pointEndY = random(-0.1, 0.3) * height;
  }

  let widthFrom = random(0.1, 0.4) * min(width, height);
  let widthEnd = random(0.3, 0.8) * widthFrom;

  let colorFrom = new NYColor(31, 54, 61, 0.6);
  let colorEnd = new NYColor(42, 27, 57, 0.6);

  let fromPoint = {
    x: pointStartX,
    y: pointStartY,
    width: widthFrom,
    color: colorFrom
  };

  let toPoint = {
    x: pointEndX,
    y: pointEndY,
    width: widthEnd,
    color: colorEnd
  };

  mainHue = random(30, 60);

  // layer 1: tree dark back
  {
    noStroke();
    dotSize = 6;
    areaNoiseDrawMax = 1.0;
    areaNoiseDrawMin = 0.0;
    skipChance = 0.0;

    fromPoint.color = new NYColor(mainHue, 56, 17, 0.6);
    fromPoint.color.slightRandomize(30, 20, 20);

    toPoint.color = new NYColor(mainHue + 60, 63, 17, 0.6);
    toPoint.color.slightRandomize(30, 20, 20);
    await drawStick(fromPoint, toPoint);
    await sleep(1);
  }

  // layer 2: some random middle layer color
  for (let i = 0; i < 6; i++) {

    let nowHue = mainHue + random(-10, 10);
    let nowSat = random(40, 60);
    let nowBri = random(20 + 6 * 5, 60 + 6 * 5);
    let colorA = new NYColor(nowHue, nowSat, nowBri, 0.6);
    let colorB = colorA.copy();
    colorB.slightRandomize(20, 20, 20, 0);

    dotNoiseZ += random(0.1, 0.6);

    dotLength = random(10, 40);

    areaNoiseScale = random(0.006, 0.02);
    areaNoiseDrawMax = 0.8 - i * 0.05;
    areaNoiseDrawMin = 0.2;
    skipChance = random(0.4, 0.8);
    fromPoint.color = colorA;
    toPoint.color = colorB;
    await drawStick(fromPoint, toPoint);

    await sleep(1);
  }

  // layer 3: dark layer
  blendMode(MULTIPLY);
  {
    let nowHue = mainHue + random(-20, 20);
    let nowSat = random(30, 50);
    let nowBri = random(10, 40);
    let colorA = new NYColor(nowHue, nowSat, nowBri, 0.2);
    let colorB = colorA.copy();
    colorB.slightRandomize(20, 20, 20, 0);

    dotNoiseZ += random(0.1, 0.6);

    dotLength = 20;
    dotNoiseScale = 0.003;
    areaNoiseScale = 0.06;
    areaNoiseDrawMax = 0.6;
    areaNoiseDrawMin = 0.0;
    skipChance = 0.1;
    fromPoint.color = colorA;
    toPoint.color = colorB;
    await drawStick(fromPoint, toPoint);

    await sleep(1);
  }

  {
    let nowHue = mainHue + random(-20, 20);
    let nowSat = random(30, 50);
    let nowBri = random(10, 40);
    let colorA = new NYColor(nowHue, nowSat, nowBri, 0.3);
    let colorB = colorA.copy();
    colorB.slightRandomize(20, 20, 20, 0);

    dotNoiseZ += 30

    dotLength = 20;
    dotNoiseScale = 0.003;
    areaNoiseScale = 0.02;
    areaNoiseDrawMax = 0.4;
    areaNoiseDrawMin = 0.0;
    skipChance = 0.1;
    fromPoint.color = colorA;
    toPoint.color = colorB;
    await drawStick(fromPoint, toPoint);

    await sleep(1);
  }

  {
    let nowHue = mainHue + random(-30, 30);
    let nowSat = random(30, 50);
    let nowBri = random(10, 40);
    let colorA = new NYColor(nowHue, nowSat, nowBri, 0.3);
    let colorB = colorA.copy();
    colorB.slightRandomize(20, 20, 20, 0);

    dotNoiseZ += 0.2;
    areaNoiseZ += 1;

    dotLength = 20;
    dotNoiseScale = 0.003;
    areaNoiseScale = 0.02;
    areaNoiseDrawMax = 0.4;
    areaNoiseDrawMin = 0.0;
    skipChance = 0.1;
    fromPoint.color = colorA;
    toPoint.color = colorB;
    await drawStick(fromPoint, toPoint);

    await sleep(1);
  }

  {
    let nowHue = mainHue + random(-30, 30);
    let nowSat = random(30, 50);
    let nowBri = random(10, 40);
    let colorA = new NYColor(nowHue, nowSat, nowBri, 0.3);
    let colorB = colorA.copy();
    colorB.slightRandomize(20, 20, 20, 0);

    dotNoiseZ += 0.2;
    areaNoiseZ += 1;

    dotLength = 20;
    dotNoiseScale = 0.003;
    areaNoiseScale = 0.02;
    areaNoiseDrawMax = 0.4;
    areaNoiseDrawMin = 0.0;
    skipChance = 0.1;
    fromPoint.color = colorA;
    toPoint.color = colorB;
    await drawStick(fromPoint, toPoint);

    await sleep(1);
  }

  {
    let nowHue = mainHue + random(-30, 30);
    let nowSat = random(30, 50);
    let nowBri = random(10, 40);
    let colorA = new NYColor(nowHue, nowSat, nowBri, 0.3);
    let colorB = colorA.copy();
    colorB.slightRandomize(20, 20, 20, 0);

    dotNoiseZ += 0.2;
    areaNoiseZ += 2;

    dotLength = 20;
    dotNoiseScale = 0.003;
    areaNoiseScale = 0.02;
    areaNoiseDrawMax = 0.4;
    areaNoiseDrawMin = 0.0;
    skipChance = 0.1;
    fromPoint.color = colorA;
    toPoint.color = colorB;
    await drawStick(fromPoint, toPoint);

    await sleep(1);
  }

  mainHue = 138;
  blendMode(BLEND);
  areaNoiseZ += 10;

  // start spawn some green
  {
    smoothSkipChanceMultiplier = 0.6;
    smoothSkipChanceRange = 0.2;

    let nowHue = mainHue + random(-10, 10);
    let nowSat = random(30, 60);
    let nowBri = random(20, 40);
    let colorA = new NYColor(nowHue, nowSat, nowBri, 0.8);
    let colorB = colorA.copy();
    colorB.slightRandomize(20, 20, 20, 0);

    dotSize = 6;
    dotLength = 40;
    dotNoiseScale = 0.02;
    areaNoiseScale = 0.01;
    areaNoiseDrawMax = 0.4;
    areaNoiseDrawMin = 0.0;
    skipChance = 0.3;
    fromPoint.color = colorA;
    toPoint.color = colorB;
    await drawStick(fromPoint, toPoint);

    await sleep(1);
  }

  // start spawn some green
  {
    dotHDiff = 120;

    smoothSkipChanceMultiplier = 0.2;
    smoothSkipChanceRange = 0.1;

    let nowHue = mainHue + random(-10, 10);
    let nowSat = random(60, 80);
    let nowBri = random(60, 80);
    let colorA = new NYColor(nowHue, nowSat, nowBri, 0.8);
    let colorB = colorA.copy();
    colorB.slightRandomize(20, 20, 20, 0);

    dotSize = 6;
    dotLength = 40;
    dotNoiseScale = 0.02;
    areaNoiseScale = 0.01;
    areaNoiseDrawMax = 0.4;
    areaNoiseDrawMin = 0.0;
    skipChance = 0.9;
    fromPoint.color = colorA;
    toPoint.color = colorB;
    await drawStick(fromPoint, toPoint);

    await sleep(1);
  }

  // start spawn some dark green
  {
    dotHDiff = 120;

    smoothSkipChanceMultiplier = 0.2;
    smoothSkipChanceRange = 0.1;

    let nowHue = mainHue + random(-10, 10);
    let nowSat = random(20, 30);
    let nowBri = random(10, 20);
    let colorA = new NYColor(nowHue, nowSat, nowBri, 0.8);
    let colorB = colorA.copy();
    colorB.slightRandomize(20, 20, 20, 0);

    dotSize = 8;
    dotLength = 20;
    dotNoiseScale = 0.02;
    areaNoiseScale = 0.01;
    areaNoiseDrawMax = 0.4;
    areaNoiseDrawMin = 0.0;
    skipChance = 0.9;
    fromPoint.color = colorA;
    toPoint.color = colorB;
    await drawStick(fromPoint, toPoint);

    await sleep(1);
  }

  // light screen
  dotTrailDensity = 0.6;
  {
    dotHDiff = 120;

    smoothSkipChanceMultiplier = 0.2;
    smoothSkipChanceRange = 0.1;

    let nowHue = mainHue + random(-10, 10);
    let nowSat = random(40, 60);
    let nowBri = random(60, 100);
    let colorA = new NYColor(nowHue, nowSat, nowBri, 0.8);
    let colorB = colorA.copy();
    colorB.slightRandomize(20, 20, 20, 0);

    dotSize = 3;
    dotLength = 20;
    dotNoiseScale = 0.001;
    dotNoiseZ += 30;
    areaNoiseScale = 0.01;
    areaNoiseDrawMax = 0.4;
    areaNoiseDrawMin = 0.0;
    skipChance = 0.9;
    fromPoint.color = colorA;
    toPoint.color = colorB;
    await drawStick(fromPoint, toPoint);

    await sleep(1);
  }

  // spawn fungis
  {
    let fungiCount = 200;

    areaNoiseDrawMax = 1.0;
    areaNoiseDrawMin = 0.5;

    while (fungiCount > 0) {
      let xPosRatio = random(0.0, 1.0);

      let yPosRatio = random(0.03, 0.97);

      if (drawStickFungi(fromPoint, toPoint, xPosRatio, yPosRatio))
        fungiCount--;

      await sleep(1);
    }
  }
}

async function draw() {

}

// async sleep
function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms))
}

let treeTurnNoiseZ = 1234;

let sketchDensity = 0.2;

let dotDensity = 0.6;
let dotSize = 6;
let dotLength = 60;

let dotTrailDensity = 0.2;

let dotNoiseScale = 0.01;
let dotNoiseZ = 6345;

let dotHDiff = 30;
let dotSDiff = 20;
let dotBDiff = 20;

let areaNoiseScale = 0.001;
let areaNoiseZ = 1234;
let areaNoiseDrawMax = 1.0;
let areaNoiseDrawMin = 0.0;

let smoothSkipChanceMultiplier = 0.1;
let smoothSkipChanceRange = 0.0;
let skipChance = 0.0;


async function drawStick(_fromPoint, _toPoint) {

    let sliceCount = dist(_fromPoint.x, _fromPoint.y, _toPoint.x, _toPoint.y) * sketchDensity;

    // let slope = getAngle(_fromPoint.x, _fromPoint.y, _toPoint.x, _toPoint.y) - 90;

    for (let i = 0; i < sliceCount; i++) {

        let t = i / (sliceCount - 1);

        let nowX = lerp(_fromPoint.x, _toPoint.x, t);
        let nowY = lerp(_fromPoint.y, _toPoint.y, t);

        let nowWidth = lerp(_fromPoint.width, _toPoint.width, t);
        let nowColor = NYLerpColor(_fromPoint.color, _toPoint.color, t);

        let xNoiseValue = noise(nowX * 0.001, nowY * 0.001, treeTurnNoiseZ);

        nowX += lerp(-0.5, 0.5, xNoiseValue) * 300;

        spawnDotLine(nowX - 0.5 * nowWidth, nowY, nowX + 0.5 * nowWidth, nowY, nowColor);

        if (i % AWAIT_LINES_PER_TICK == 0)
            await sleep(1);
    }
    // NYLine(_fromPoint.x, _fromPoint.y, _toPoint.x, _toPoint.y, _fromPoint.color, _toPoint.color);
}

function spawnDotLine(_x1, _y1, _x2, _y2, _color) {
    let spawnCount = dist(_x1, _y1, _x2, _y2) * dotDensity;
    for (let i = 0; i < spawnCount; i++) {
        let t = i / (spawnCount - 1);
        let nowX = lerp(_x1, _x2, t);
        let nowY = lerp(_y1, _y2, t);

        let areaNoiseValue = noise(nowX * areaNoiseScale, nowY * areaNoiseScale, areaNoiseZ);
        let closeRatio = min(abs(areaNoiseValue - areaNoiseDrawMin), abs(areaNoiseValue - areaNoiseDrawMax));

        let nowSkipChance = skipChance;

        if (smoothSkipChanceRange != 0 && closeRatio < smoothSkipChanceRange) {
            nowSkipChance += (1.0 - closeRatio / smoothSkipChanceRange) * smoothSkipChanceMultiplier;
        }

        if (areaNoiseValue < areaNoiseDrawMin || areaNoiseValue > areaNoiseDrawMax) {
            continue;
        }

        if (random() < nowSkipChance) {
            continue;
        }

        spawnDot(nowX, nowY, dotSize, dotLength, _color);
    }
}

function spawnDot(_x, _y, _size, _length, _color) {
    let dotCount = _length * dotTrailDensity;
    let dotDistance = _length / dotCount;

    let nowX = _x;
    let nowY = _y;
    let nowSize = _size;

    let fromColor = _color;
    let toColor = _color.copy();
    toColor.slightRandomize(dotHDiff, dotSDiff, dotBDiff);

    for (let i = 0; i < dotCount; i++) {
        let t = i / (dotCount - 1);

        nowSize = lerp(_size, 0, easeOutSine(t));

        let rotNoise = noise(nowX * dotNoiseScale, nowY * dotNoiseScale, dotNoiseZ);

        nowX += sin(radians(rotNoise * 720)) * dotDistance;
        nowY -= cos(radians(rotNoise * 720)) * dotDistance;

        let nowColor = NYLerpColor(fromColor, toColor, t);

        noStroke();
        fill(nowColor.h, nowColor.s, nowColor.b, nowColor.a);
        circle(nowX, nowY, nowSize);
    }
}

function drawStickFungi(_fromPoint, _toPoint, _xRatio, _yRatio) {

    let nowX = lerp(_fromPoint.x, _toPoint.x, _yRatio);
    let nowY = lerp(_fromPoint.y, _toPoint.y, _yRatio);
    let nowWidth = lerp(_fromPoint.width, _toPoint.width, _yRatio);

    let xNoiseValue = noise(nowX * 0.001, nowY * 0.001, treeTurnNoiseZ);
    nowX += lerp(-0.5, 0.5, xNoiseValue) * 300;

    let leftX = nowX - 0.5 * nowWidth;
    let rightX = nowX + 0.5 * nowWidth;
    let spawnX = lerp(leftX, rightX, _xRatio);

    let areaNoiseValue = noise(nowX * areaNoiseScale, nowY * areaNoiseScale, areaNoiseZ);
    if (areaNoiseValue < areaNoiseDrawMin || areaNoiseValue > areaNoiseDrawMax) {
        return false;
    }

    let fungiLength = random(30, 90);
    let fungiThickness = random(1, 3);
    let headSize = random(6, 12);

    let stickAngle = getAngle(_fromPoint.x, _fromPoint.y, _toPoint.x, _toPoint.y);
    let startAngle = 0.0;

    if(_xRatio < 0.5)
        startAngle = lerp(-120, -40, _xRatio * 2) + random(-20, 20);
    else
        startAngle = lerp(40, 120, (_xRatio - 0.5) * 2) + random(-20, 20);

    startAngle += stickAngle;
    spawnFungi(spawnX, nowY, fungiLength, fungiThickness, headSize, startAngle);
    return true;
}

let fungiLineDensity = 0.8;
let fungiHeadLineDensity = 0.9;

function spawnFungi(_x, _y, _length, _thickness, _headSize, _startAngle) {
    let nowX = _x;
    let nowY = _y;

    let nowAngle = _startAngle;
    let smoothPower = random(0.01, 0.06);

    let lineCount = _length * fungiLineDensity;
    let stepLength = _length / lineCount;

    for (let i = 0; i < lineCount; i++) {
        let t = i / (lineCount - 1);

        if (t < 0.7) {
            let angleNoiseValue = noise(nowX * 0.01, nowY * 0.01, 3456)
            nowAngle += lerp(-12, 12, angleNoiseValue);
        }
        else {
            nowAngle = lerp(nowAngle, 0.0, smoothPower);
        }

        nowX += sin(radians(nowAngle)) * stepLength;
        nowY -= cos(radians(nowAngle)) * stepLength;

        fill(0, 0, 100, 0.6);
        noStroke();

        let dotSize = map(t, 0.0, 0.15, 0, 1, true);

        push();
        translate(nowX, nowY);
        rotate(radians(nowAngle));
        NYArc(0, 0, _thickness, _thickness * 0.6, random(dotSize), 0.6);
        pop();
    }

    // draw head
    let sizeAngleRandom = random(0, 720);
    let headSizeX = (sin(radians(sizeAngleRandom)) + 1) / 2 * _headSize;
    let headSizeY = (cos(radians(sizeAngleRandom)) + 1) / 2 * _headSize;

    push();
    translate(nowX, nowY);
    rotate(radians(nowAngle));
    drawFungiHead(0, 0, headSizeX, headSizeY);
    pop();
}

function drawFungiHead(_x, _y, _width, _length) {
    let arcCount = _length * fungiHeadLineDensity;
    let stepLength = _length / arcCount;

    let nowX = _x;
    let nowY = _y;
    let nowAngle = 0;
    for (let i = 0; i < arcCount; i++) {
        let t = i / (arcCount - 1);
        let curveT = easeInSine(t);

        nowX += sin(radians(nowAngle)) * stepLength;
        nowY += -cos(radians(nowAngle)) * stepLength;

        let nowWidth = lerp(1.0, 0.2, curveT) * _width;
        let nowHeight = 0.6 * nowWidth;

        NYArc(nowX, nowY, nowWidth, nowHeight, random(3), 0.6);
    }
}

function NYArc(_x, _y, _width, _height, _dotSize = 3, _density = 0.6) {
    let dotCount = (_width + _height) * _density;

    push();
    translate(_x, _y);

    for (let i = 0; i < dotCount; i++) {
        let t = i / (dotCount - 1);

        let nowDotAngle = lerp(120, 240, t);

        let nowX = sin(radians(nowDotAngle)) * _width * 0.5;
        let nowY = -cos(radians(nowDotAngle)) * _height * 0.5;

        circle(nowX, nowY, _dotSize);
    }
    pop();

}


function easeInSine(x) {
    return 1 - Math.cos((x * Math.PI) / 2);
}
function easeOutSine(x) {
    return Math.sin((x * Math.PI) / 2);
}
function easeInOutSine(x) {
    return -(Math.cos(Math.PI * x) - 1) / 2;
}


function easeInQuad(x) {
    return x * x;
}
function easeOutQuad(x) {
    return 1 - (1 - x) * (1 - x);
}
function easeInOutQuad(x) {
    return x < 0.5 ? 2 * x * x : 1 - Math.pow(-2 * x + 2, 2) / 2;
}


function easeInCubic(x) {
    return x * x * x;
}
function easeOutCubic(x) {
    return 1 - Math.pow(1 - x, 3);
}
function easeInOutCubic(x) {
    return x < 0.5 ? 4 * x * x * x : 1 - Math.pow(-2 * x + 2, 3) / 2;
}


function easeInQuart(x) {
    return x * x * x * x;
}
function easeOutQuart(x) {
    return 1 - Math.pow(1 - x, 4);
}
function easeInOutQuart(x) {
    return x < 0.5 ? 8 * x * x * x * x : 1 - Math.pow(-2 * x + 2, 4) / 2;
}


function easeInQuint(x) {
    return x * x * x * x * x;
}
function easeOutQuint(x) {
    return 1 - Math.pow(1 - x, 5);
}
function easeInOutQuint(x) {
    return x < 0.5 ? 16 * x * x * x * x * x : 1 - Math.pow(-2 * x + 2, 5) / 2;
}


function easeInExpo(x) {
    return x === 0 ? 0 : Math.pow(2, 10 * x - 10);
}
function easeOutExpo(x) {
    return x === 1 ? 1 : 1 - Math.pow(2, -10 * x);
}
function easeInOutExpo(x) {
    return x === 0
        ? 0
        : x === 1
            ? 1
            : x < 0.5 ? Math.pow(2, 20 * x - 10) / 2
                : (2 - Math.pow(2, -20 * x + 10)) / 2;
}


function easeInCirc(x) {
    return 1 - Math.sqrt(1 - Math.pow(x, 2));
}
function easeOutCirc(x) {
    return Math.sqrt(1 - Math.pow(x - 1, 2));
}
function easeInOutCirc(x) {
    return x < 0.5
        ? (1 - Math.sqrt(1 - Math.pow(2 * x, 2))) / 2
        : (Math.sqrt(1 - Math.pow(-2 * x + 2, 2)) + 1) / 2;
}


function easeInBack(x) {
    const c1 = 1.70158;
    const c3 = c1 + 1;

    return c3 * x * x * x - c1 * x * x;
}
function easeOutBack(x) {
    const c1 = 1.70158;
    const c3 = c1 + 1;

    return 1 + c3 * Math.pow(x - 1, 3) + c1 * Math.pow(x - 1, 2);
}
function easeInOutBack(x) {
    const c1 = 1.70158;
    const c2 = c1 * 1.525;

    return x < 0.5
        ? (Math.pow(2 * x, 2) * ((c2 + 1) * 2 * x - c2)) / 2
        : (Math.pow(2 * x - 2, 2) * ((c2 + 1) * (x * 2 - 2) + c2) + 2) / 2;
}


function easeInBounce(x) {
    return 1 - easeOutBounce(1 - x);
}
function easeOutBounce(x) {
    const n1 = 7.5625;
    const d1 = 2.75;

    if (x < 1 / d1) {
        return n1 * x * x;
    } else if (x < 2 / d1) {
        return n1 * (x -= 1.5 / d1) * x + 0.75;
    } else if (x < 2.5 / d1) {
        return n1 * (x -= 2.25 / d1) * x + 0.9375;
    } else {
        return n1 * (x -= 2.625 / d1) * x + 0.984375;
    }
}
function easeInOutBounce(x) {
    return x < 0.5
        ? (1 - easeOutBounce(1 - 2 * x)) / 2
        : (1 + easeOutBounce(2 * x - 1)) / 2;
}


function easeInElastic(x) {
    const c4 = (2 * Math.PI) / 3;

    return x === 0
        ? 0
        : x === 1
            ? 1
            : -Math.pow(2, 10 * x - 10) * Math.sin((x * 10 - 10.75) * c4);
}

function easeOutElastic(x) {
    const c4 = (2 * Math.PI) / 3;

    return x === 0
        ? 0
        : x === 1
            ? 1
            : Math.pow(2, -10 * x) * Math.sin((x * 10 - 0.75) * c4) + 1;
}

function easeInOutElastic(x) {
    const c5 = (2 * Math.PI) / 4.5;

    return x === 0
        ? 0
        : x === 1
            ? 1
            : x < 0.5
                ? -(Math.pow(2, 20 * x - 10) * Math.sin((20 * x - 11.125) * c5)) / 2
                : (Math.pow(2, -20 * x + 10) * Math.sin((20 * x - 11.125) * c5)) / 2 + 1;
}

class NYColor {
    constructor(_h, _s, _b, _a = 1.0) {
        this.h = _h;
        this.s = _s;
        this.b = _b;
        this.a = _a;
    }

    copy() {
        return new NYColor(this.h, this.s, this.b, this.a);
    }

    slightRandomize(_hDiff = 10, _sDiff = 12, _bDiff = 12, _aDiff = 0.0) {
        this.h += random(-0.5 * _hDiff, 0.5 * _hDiff);
        this.s += random(-0.5 * _sDiff, 0.5 * _sDiff);
        this.b += random(-0.5 * _bDiff, 0.5 * _bDiff);
        this.a += random(-0.5 * _aDiff, 0.5 * _aDiff);

        this.h = processHue(this.h);
    }

    color() {
        return color(this.h, this.s, this.b, this.a);
    }

    static newRandomColor(_mainHue) {
        let h = processHue(_mainHue + random(-80, 80));
        let s = random(40, 100);
        let b = random(60, 100);

        return new NYColor(h, s, b);
    }
}



function NYLerpHue(_hueA, _hueB, _t) {
    let hueA = _hueA;
    let hueB = _hueB;
  
    let hueDiff = abs(hueB - hueA);
  
    if (abs((hueB - 360) - hueA) < hueDiff) {
      hueB -= 360;
    }
    else if (abs((hueB + 360) - hueA) < hueDiff) {
      hueB += 360;
    }
    else {
      return lerp(_hueA, _hueB, _t);
    }
  
    let resultHue = lerp(hueA, hueB, _t);
  
    if (resultHue < 0) {
      resultHue += 360;
    }
    else if (resultHue > 360) {
      resultHue -= 360;
    }
  
    return resultHue;
  }
  
  function NYLerpColor(_colorA, _colorB, _t) {
    let _hue = NYLerpHue(_colorA.h, _colorB.h, _t);
    let _sat = lerp(_colorA.s, _colorB.s, _t);
    let _bri = lerp(_colorA.b, _colorB.b, _t);
    let _alpha = lerp(_colorA.a, _colorB.a, _t);
  
    return new NYColor(_hue, _sat, _bri, _alpha);
  }
  
  function NYLerpP5Color(_colorA, _colorB, _t) {
    let hueA = hue(_colorA);
    let hueB = hue(_colorB);
  
    let hueDiff = abs(hueB - hueA);
  
    if (abs((hueB - 360) - hueA) < hueDiff) {
      hueB -= 360;
    }
    else if (abs((hueB + 360) - hueA) < hueDiff) {
      hueB += 360;
    }
    else {
      return lerpColor(_colorA, _colorB, _t);
    }
  
    let satA = saturation(_colorA);
    let briA = brightness(_colorA);
    let alphaA = alpha(_colorA);
  
    let satB = saturation(_colorB);
    let briB = brightness(_colorB);
    let alphaB = alpha(_colorB);
  
    let resultHue = lerp(hueA, hueB, _t);
    let resultSat = lerp(satA, satB, _t);
    let resultBri = lerp(briA, briB, _t);
    let resultAlpha = lerp(alphaA, alphaB, _t);
  
    if (resultHue < 0) {
      resultHue += 360;
    }
    else if (resultHue > 360) {
      resultHue -= 360;
    }
  
    return color(resultHue, resultSat, resultBri, resultAlpha);
  }
  
  function processHue(_hue) {
    let result = _hue % 360;
    if (result < 0) {
      result += 360;
    }
    return result;
  }
  
  // get angle between two points and return in degrees
  function getAngle(_x1, _y1, _x2, _y2) {
    let xDiff = _x2 - _x1;
    let yDiff = _y2 - _y1;
    return atan2(yDiff, xDiff) * 180 / PI + 90;
  }
