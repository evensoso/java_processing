// This is the work for the #WCCChallenge topic 'Fungi'
//
int AWAIT_LINES_PER_TICK = 6;
int mainHue = 35;

async void setup() {
  createCanvas(windowWidth, windowHeight);
  colorMode(HSB);
  strokeCap(SQUARE);
  background(0, 0, 3);;

  int treeCount = int(random(2, 6));

  float themeHue = random(0, 360);

  for (int i = 0; i < treeCount; i++) {
    await drawTree();
  }
}

async void drawTree() {

  dotTrailDensity = 0.2;
  dotNoiseZ += random(100, 300);
  areaNoiseZ += random(100, 300);
  treeTurnNoiseZ += random(100, 300);

  int pointStartX = random(-0.1, 1.1) * width;
  int pointStartY = 1.1 * height;

  int pointEndX = random(-0.1, 1.1) * width;
  int pointEndY = -0.1 * height;

  int endRandom = random();
  if (endRandom < 0.3) {
    pointEndX = -0.1 * width;
    pointEndY = random(-0.1, 0.3) * height;
  }
  else if (endRandom < 0.6) {
    pointEndX = 1.1 * width;
    pointEndY = random(-0.1, 0.3) * height;
  }

  int widthFrom = random(0.1, 0.4) * min(width, height);
  int widthEnd = random(0.3, 0.8) * widthFrom;

  int colorFrom = new NYColor(31, 54, 61, 0.6);
  int colorEnd = new NYColor(42, 27, 57, 0.6);

  int fromPoint = {
    x: pointStartX,
    y: pointStartY,
    width: widthFrom,
    color: colorFrom
  };

  int toPoint = {
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
  for (int i = 0; i < 6; i++) {

    int nowHue = mainHue + random(-10, 10);
    int nowSat = random(40, 60);
    int nowBri = random(20 + 6 * 5, 60 + 6 * 5);
    int colorA = new NYColor(nowHue, nowSat, nowBri, 0.6);
    int colorB = colorA.copy();
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
    int nowHue = mainHue + random(-20, 20);
    int nowSat = random(30, 50);
    int nowBri = random(10, 40);
    int colorA = new NYColor(nowHue, nowSat, nowBri, 0.2);
    int colorB = colorA.copy();
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
    int nowHue = mainHue + random(-20, 20);
    int nowSat = random(30, 50);
    int nowBri = random(10, 40);
    int colorA = new NYColor(nowHue, nowSat, nowBri, 0.3);
    int colorB = colorA.copy();
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
    int nowHue = mainHue + random(-30, 30);
    int nowSat = random(30, 50);
    int nowBri = random(10, 40);
    int colorA = new NYColor(nowHue, nowSat, nowBri, 0.3);
    int colorB = colorA.copy();
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
    int nowHue = mainHue + random(-30, 30);
    int nowSat = random(30, 50);
    int nowBri = random(10, 40);
    int colorA = new NYColor(nowHue, nowSat, nowBri, 0.3);
    int colorB = colorA.copy();
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
    int nowHue = mainHue + random(-30, 30);
    int nowSat = random(30, 50);
    int nowBri = random(10, 40);
    int colorA = new NYColor(nowHue, nowSat, nowBri, 0.3);
    int colorB = colorA.copy();
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

    int nowHue = mainHue + random(-10, 10);
    int nowSat = random(30, 60);
    int nowBri = random(20, 40);
    int colorA = new NYColor(nowHue, nowSat, nowBri, 0.8);
    int colorB = colorA.copy();
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

    int nowHue = mainHue + random(-10, 10);
    int nowSat = random(60, 80);
    int nowBri = random(60, 80);
    int colorA = new NYColor(nowHue, nowSat, nowBri, 0.8);
    int colorB = colorA.copy();
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

    int nowHue = mainHue + random(-10, 10);
    int nowSat = random(20, 30);
    int nowBri = random(10, 20);
    int colorA = new NYColor(nowHue, nowSat, nowBri, 0.8);
    int colorB = colorA.copy();
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

    int nowHue = mainHue + random(-10, 10);
    int nowSat = random(40, 60);
    int nowBri = random(60, 100);
    int colorA = new NYColor(nowHue, nowSat, nowBri, 0.8);
    int colorB = colorA.copy();
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
    int fungiCount = 200;

    areaNoiseDrawMax = 1.0;
    areaNoiseDrawMin = 0.5;

    while (fungiCount > 0) {
      int xPosRatio = random(0.0, 1.0);

      int yPosRatio = random(0.03, 0.97);

      if (drawStickFungi(fromPoint, toPoint, xPosRatio, yPosRatio))
        fungiCount--;

      await sleep(1);
    }
  }
}

async void draw() {

}

// async sleep
void sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms))
}

