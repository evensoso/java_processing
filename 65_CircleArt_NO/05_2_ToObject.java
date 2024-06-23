// This is the work for the #WCCChallenge topic 'Fungi'
//async等をつかわない様に
//
float AWAIT_LINES_PER_TICK = 6;
float mainHue = 35;

void setup() {
  size(640, 640);
  colorMode(HSB);
  strokeCap(SQUARE);
  background(0, 0, 3);;

  float treeCount = int(random(2, 6));

  float themeHue = random(0, 360);

  for (int i = 0; i < treeCount; i++) {
    drawTree();
  }
}

void drawTree() {

  dotTrailDensity = 0.2;
  dotNoiseZ += random(100, 300);
  areaNoiseZ += random(100, 300);
  treeTurnNoiseZ += random(100, 300);

  float pointStartX = random(-0.1, 1.1) * width;
  float pointStartY = 1.1 * height;

  float pointEndX = random(-0.1, 1.1) * width;
  float pointEndY = -0.1 * height;

  float endRandom = random(1);
  if (endRandom < 0.3) {
    pointEndX = -0.1 * width;
    pointEndY = random(-0.1, 0.3) * height;
  }
  else if (endRandom < 0.6) {
    pointEndX = 1.1 * width;
    pointEndY = random(-0.1, 0.3) * height;
  }

  float widthFrom = random(0.1, 0.4) * min(width, height);
  float widthEnd = random(0.3, 0.8) * widthFrom;

  NYColor colorFrom = new NYColor(31, 54, 61, 0.6);
  NYColor colorEnd = new NYColor(42, 27, 57, 0.6);
  
  Point fromPoint = new Point(pointStartX, pointStartY, widthFrom, colorFrom);
  Point toPoint = new Point(pointStartX, pointStartY, widthEnd, colorEnd);
  
  mainHue = random(30, 60);

  // layer 1: tree dark back
  {
    noStroke();
    dotSize = 6;
    areaNoiseDrawMax = 1.0;
    areaNoiseDrawMin = 0.0;
    skipChance = 0.0;
    
    NYColor fromPP = new NYColor(mainHue, 56, 17, 0.6);
    fromPP.slightRandomize(30, 20, 20, 0);
    
    NYColor toPP = new NYColor(mainHue + 60, 63, 17, 0.6);
    toPP.slightRandomize(30, 20, 20, 0);

    fromPoint.col = fromPP;
    //fromPoint.color.slightRandomize(30, 20, 20);

    toPoint.col = toPP;
    //toPoint.color.slightRandomize(30, 20, 20);
    drawStick(fromPoint, toPoint);
    sleep(1);
  }

  // layer 2: some random middle layer color
  for (int i = 0; i < 6; i++) {

    float nowHue = mainHue + random(-10, 10);
    float nowSat = random(40, 60);
    float nowBri = random(20 + 6 * 5, 60 + 6 * 5);
    NYColor colorA = new NYColor(nowHue, nowSat, nowBri, 0.6);
    NYColor colorB = colorA.copy();
    colorB.slightRandomize(20, 20, 20, 0);

    dotNoiseZ += random(0.1, 0.6);

    dotLength = random(10, 40);

    areaNoiseScale = random(0.006, 0.02);
    areaNoiseDrawMax = 0.8 - i * 0.05;
    areaNoiseDrawMin = 0.2;
    skipChance = random(0.4, 0.8);
    //fromPoint.color = colorA;
    //toPoint.color = colorB;
    fromPoint.col = colorA;
    toPoint.col = colorB;
    
    drawStick(fromPoint, toPoint);

    sleep(1);
  }

  // layer 3: dark layer
  blendMode(MULTIPLY);
  {
    float nowHue = mainHue + random(-20, 20);
    float nowSat = random(30, 50);
    float nowBri = random(10, 40);
    NYColor colorA = new NYColor(nowHue, nowSat, nowBri, 0.2);
    NYColor colorB = colorA.copy();
    colorB.slightRandomize(20, 20, 20, 0);

    dotNoiseZ += random(0.1, 0.6);

    dotLength = 20;
    dotNoiseScale = 0.003;
    areaNoiseScale = 0.06;
    areaNoiseDrawMax = 0.6;
    areaNoiseDrawMin = 0.0;
    skipChance = 0.1;
    //fromPoint.color = colorA;
    //toPoint.color = colorB;
    fromPoint.col = colorA;
    toPoint.col = colorB;
    
    drawStick(fromPoint, toPoint);

    sleep(1);
  }

  {
    float nowHue = mainHue + random(-20, 20);
    float nowSat = random(30, 50);
    float nowBri = random(10, 40);
    NYColor colorA = new NYColor(nowHue, nowSat, nowBri, 0.3);
    NYColor colorB = colorA.copy();
    colorB.slightRandomize(20, 20, 20, 0);

    dotNoiseZ += 30;

    dotLength = 20;
    dotNoiseScale = 0.003;
    areaNoiseScale = 0.02;
    areaNoiseDrawMax = 0.4;
    areaNoiseDrawMin = 0.0;
    skipChance = 0.1;
    //fromPoint.color = colorA;
    //toPoint.color = colorB;
    fromPoint.col = colorA;
    toPoint.col = colorB;
    drawStick(fromPoint, toPoint);

    sleep(1);
  }

  {
    float nowHue = mainHue + random(-30, 30);
    float nowSat = random(30, 50);
    float nowBri = random(10, 40);
    NYColor colorA = new NYColor(nowHue, nowSat, nowBri, 0.3);
    NYColor colorB = colorA.copy();
    colorB.slightRandomize(20, 20, 20, 0);

    dotNoiseZ += 0.2;
    areaNoiseZ += 1;

    dotLength = 20;
    dotNoiseScale = 0.003;
    areaNoiseScale = 0.02;
    areaNoiseDrawMax = 0.4;
    areaNoiseDrawMin = 0.0;
    skipChance = 0.1;
    //fromPoint.color = colorA;
    //toPoint.color = colorB;
    fromPoint.col = colorA;
    toPoint.col = colorB;
    drawStick(fromPoint, toPoint);

    sleep(1);
  }

  {
    float nowHue = mainHue + random(-30, 30);
    float nowSat = random(30, 50);
    float nowBri = random(10, 40);
    NYColor colorA = new NYColor(nowHue, nowSat, nowBri, 0.3);
    NYColor colorB = colorA.copy();
    colorB.slightRandomize(20, 20, 20, 0);

    dotNoiseZ += 0.2;
    areaNoiseZ += 1;

    dotLength = 20;
    dotNoiseScale = 0.003;
    areaNoiseScale = 0.02;
    areaNoiseDrawMax = 0.4;
    areaNoiseDrawMin = 0.0;
    skipChance = 0.1;
    //fromPoint.color = colorA;
    //toPoint.color = colorB;
    fromPoint.col = colorA;
    toPoint.col = colorB;
    drawStick(fromPoint, toPoint);

    sleep(1);
  }

  {
    float nowHue = mainHue + random(-30, 30);
    float nowSat = random(30, 50);
    float nowBri = random(10, 40);
    NYColor colorA = new NYColor(nowHue, nowSat, nowBri, 0.3);
    NYColor colorB = colorA.copy();
    colorB.slightRandomize(20, 20, 20, 0);

    dotNoiseZ += 0.2;
    areaNoiseZ += 2;

    dotLength = 20;
    dotNoiseScale = 0.003;
    areaNoiseScale = 0.02;
    areaNoiseDrawMax = 0.4;
    areaNoiseDrawMin = 0.0;
    skipChance = 0.1;
    //fromPoint.color = colorA;
    //toPoint.color = colorB;
    fromPoint.col = colorA;
    toPoint.col = colorB;
    drawStick(fromPoint, toPoint);

    sleep(1);
  }

  mainHue = 138;
  blendMode(BLEND);
  areaNoiseZ += 10;

  // start spawn some green
  {
    smoothSkipChanceMultiplier = 0.6;
    smoothSkipChanceRange = 0.2;

    float nowHue = mainHue + random(-10, 10);
    float nowSat = random(30, 60);
    float nowBri = random(20, 40);
    NYColor colorA = new NYColor(nowHue, nowSat, nowBri, 0.8);
    NYColor colorB = colorA.copy();
    colorB.slightRandomize(20, 20, 20, 0);

    dotSize = 6;
    dotLength = 40;
    dotNoiseScale = 0.02;
    areaNoiseScale = 0.01;
    areaNoiseDrawMax = 0.4;
    areaNoiseDrawMin = 0.0;
    skipChance = 0.3;
    //fromPoint.color = colorA;
    //toPoint.color = colorB;
    fromPoint.col = colorA;
    toPoint.col = colorB;
    drawStick(fromPoint, toPoint);

    sleep(1);
  }

  // start spawn some green
  {
    dotHDiff = 120;

    smoothSkipChanceMultiplier = 0.2;
    smoothSkipChanceRange = 0.1;

    float nowHue = mainHue + random(-10, 10);
    float nowSat = random(60, 80);
    float nowBri = random(60, 80);
    NYColor colorA = new NYColor(nowHue, nowSat, nowBri, 0.8);
    NYColor colorB = colorA.copy();
    colorB.slightRandomize(20.0, 20.0, 20.0, 0.0);

    dotSize = 6;
    dotLength = 40;
    dotNoiseScale = 0.02;
    areaNoiseScale = 0.01;
    areaNoiseDrawMax = 0.4;
    areaNoiseDrawMin = 0.0;
    skipChance = 0.9;
    //fromPoint.color = colorA;
    //toPoint.color = colorB;
    fromPoint.col = colorA;
    toPoint.col = colorB;
    drawStick(fromPoint, toPoint);

    sleep(1);
  }

  // start spawn some dark green
  {
    dotHDiff = 120;

    smoothSkipChanceMultiplier = 0.2;
    smoothSkipChanceRange = 0.1;

    float nowHue = mainHue + random(-10, 10);
    float nowSat = random(20, 30);
    float nowBri = random(10, 20);
    NYColor colorA = new NYColor(nowHue, nowSat, nowBri, 0.8);
    NYColor colorB = colorA.copy();
    colorB.slightRandomize(20, 20, 20, 0);

    dotSize = 8;
    dotLength = 20;
    dotNoiseScale = 0.02;
    areaNoiseScale = 0.01;
    areaNoiseDrawMax = 0.4;
    areaNoiseDrawMin = 0.0;
    skipChance = 0.9;
    //fromPoint.color = colorA;
    //toPoint.color = colorB;
    fromPoint.col = colorA;
    toPoint.col = colorB;
    drawStick(fromPoint, toPoint);

    sleep(1);
  }

  // light screen
  dotTrailDensity = 0.6;
  {
    dotHDiff = 120;

    smoothSkipChanceMultiplier = 0.2;
    smoothSkipChanceRange = 0.1;

    float nowHue = mainHue + random(-10, 10);
    float nowSat = random(40, 60);
    float nowBri = random(60, 100);
    NYColor colorA = new NYColor(nowHue, nowSat, nowBri, 0.8);
    NYColor colorB = colorA.copy();
    colorB.slightRandomize(20, 20, 20, 0);

    dotSize = 3;
    dotLength = 20;
    dotNoiseScale = 0.001;
    dotNoiseZ += 30;
    areaNoiseScale = 0.01;
    areaNoiseDrawMax = 0.4;
    areaNoiseDrawMin = 0.0;
    skipChance = 0.9;
    //fromPoint.color = colorA;
    //toPoint.color = colorB;
    fromPoint.col = colorA;
    toPoint.col = colorB;
    drawStick(fromPoint, toPoint);

    sleep(1);
  }

  // spawn fungis
  {
    float fungiCount = 200;

    areaNoiseDrawMax = 1.0;
    areaNoiseDrawMin = 0.5;

    while (fungiCount > 0) {
      float xPosRatio = random(0.0, 1.0);

      float yPosRatio = random(0.03, 0.97);

      if (drawStickFungi(fromPoint, toPoint, xPosRatio, yPosRatio))
        fungiCount--;

      sleep(1);
    }
  }
}

 void draw() {

}

//  sleep
void sleep(int ms) {
  //return new Promise(resolve => setTimeout(resolve, ms))
  //setTimeout(ms);
}

//-----------------------------------------------------------

float treeTurnNoiseZ = 1234;

float sketchDensity = 0.2;

float dotDensity = 0.6;
float dotSize = 6;
float dotLength = 60;

float dotTrailDensity = 0.2;

float dotNoiseScale = 0.01;
float dotNoiseZ = 6345;

float dotHDiff = 30;
float dotSDiff = 20;
float dotBDiff = 20;

float areaNoiseScale = 0.001;
float areaNoiseZ = 1234;
float areaNoiseDrawMax = 1.0;
float areaNoiseDrawMin = 0.0;

float smoothSkipChanceMultiplier = 0.1;
float smoothSkipChanceRange = 0.0;
float skipChance = 0.0;


 void drawStick(Point _fromPoint, Point _toPoint) {

    float sliceCount = dist(_fromPoint.x, _fromPoint.y, _toPoint.x, _toPoint.y) * sketchDensity;

    // int slope = getAngle(_fromPoint.x, _fromPoint.y, _toPoint.x, _toPoint.y) - 90;

    for (int i = 0; i < sliceCount; i++) {

        float t = i / (sliceCount - 1);

        float nowX = lerp(_fromPoint.x, _toPoint.x, t);
        float nowY = lerp(_fromPoint.y, _toPoint.y, t);

        float nowWidth = lerp(_fromPoint.wid, _toPoint.wid, t);
        NYColor nowColor = NYLerpColor(_fromPoint.col, _toPoint.col, t);

        float xNoiseValue = noise(nowX * 0.001, nowY * 0.001, treeTurnNoiseZ);

        nowX += lerp(-0.5, 0.5, xNoiseValue) * 300;

        spawnDotLine(nowX - 0.5 * nowWidth, nowY, nowX + 0.5 * nowWidth, nowY, nowColor);

        if (i % AWAIT_LINES_PER_TICK == 0)
            sleep(1);
    }
    // NYLine(_fromPoint.x, _fromPoint.y, _toPoint.x, _toPoint.y, _fromPoint.color, _toPoint.color);
}

void spawnDotLine(float _x1, float _y1, float _x2, float _y2, NYColor _color) {
    float spawnCount = dist(_x1, _y1, _x2, _y2) * dotDensity;
    for (int i = 0; i < spawnCount; i++) {
        float t = i / (spawnCount - 1);
        float nowX = lerp(_x1, _x2, t);
        float nowY = lerp(_y1, _y2, t);

        float areaNoiseValue = noise(nowX * areaNoiseScale, nowY * areaNoiseScale, areaNoiseZ);
        float closeRatio = min(abs(areaNoiseValue - areaNoiseDrawMin), abs(areaNoiseValue - areaNoiseDrawMax));

        float nowSkipChance = skipChance;

        if (smoothSkipChanceRange != 0 && closeRatio < smoothSkipChanceRange) {
            nowSkipChance += (1.0 - closeRatio / smoothSkipChanceRange) * smoothSkipChanceMultiplier;
        }

        if (areaNoiseValue < areaNoiseDrawMin || areaNoiseValue > areaNoiseDrawMax) {
            continue;
        }

        if (random(1) < nowSkipChance) {
            continue;
        }

        spawnDot(nowX, nowY, dotSize, dotLength, _color);
    }
}

void spawnDot(float _x, float _y, float _size, float _length, NYColor _color) {
    float dotCount = _length * dotTrailDensity;
    float dotDistance = _length / dotCount;

    float nowX = _x;
    float nowY = _y;
    float nowSize = _size;

    NYColor fromColor = _color;
    NYColor toColor = _color.copy();
    toColor.slightRandomize(dotHDiff, dotSDiff, dotBDiff, 0.0);

    for (int i = 0; i < dotCount; i++) {
        float t = i / (dotCount - 1);

        nowSize = lerp(_size, 0, easeOutSine(t));

        float rotNoise = noise(nowX * dotNoiseScale, nowY * dotNoiseScale, dotNoiseZ);

        nowX += sin(radians(rotNoise * 720)) * dotDistance;
        nowY -= cos(radians(rotNoise * 720)) * dotDistance;

        NYColor nowColor = NYLerpColor(fromColor, toColor, t);

        noStroke();
        fill(nowColor.h, nowColor.s, nowColor.b, nowColor.a);
        circle(nowX, nowY, nowSize);
    }
}

Boolean drawStickFungi(Point _fromPoint, Point _toPoint, float _xRatio, float _yRatio) {

    float nowX = lerp(_fromPoint.x, _toPoint.x, _yRatio);
    float nowY = lerp(_fromPoint.y, _toPoint.y, _yRatio);
    float nowWidth = lerp(_fromPoint.wid, _toPoint.wid, _yRatio);

    float xNoiseValue = noise(nowX * 0.001, nowY * 0.001, treeTurnNoiseZ);
    nowX += lerp(-0.5, 0.5, xNoiseValue) * 300;

    float leftX = nowX - 0.5 * nowWidth;
    float rightX = nowX + 0.5 * nowWidth;
    float spawnX = lerp(leftX, rightX, _xRatio);

    float areaNoiseValue = noise(nowX * areaNoiseScale, nowY * areaNoiseScale, areaNoiseZ);
    if (areaNoiseValue < areaNoiseDrawMin || areaNoiseValue > areaNoiseDrawMax) {
        return false;
    }

    float fungiLength = random(30, 90);
    float fungiThickness = random(1, 3);
    float headSize = random(6, 12);

    float stickAngle = getAngle(_fromPoint.x, _fromPoint.y, _toPoint.x, _toPoint.y);
    float startAngle = 0.0;

    if(_xRatio < 0.5)
        startAngle = lerp(-120, -40, _xRatio * 2) + random(-20, 20);
    else
        startAngle = lerp(40, 120, (_xRatio - 0.5) * 2) + random(-20, 20);

    startAngle += stickAngle;
    spawnFungi(spawnX, nowY, fungiLength, fungiThickness, headSize, startAngle);
    return true;
}

float fungiLineDensity = 0.8;
float fungiHeadLineDensity = 0.9;

void spawnFungi(float _x, float _y, float _length, float _thickness, float _headSize, float _startAngle) {
    float nowX = _x;
    float nowY = _y;

    float nowAngle = _startAngle;
    float smoothPower = random(0.01, 0.06);

    float lineCount = _length * fungiLineDensity;
    float stepLength = _length / lineCount;

    for (int i = 0; i < lineCount; i++) {
        float t = i / (lineCount - 1);

        if (t < 0.7) {
            float angleNoiseValue = noise(nowX * 0.01, nowY * 0.01, 3456);
            nowAngle += lerp(-12, 12, angleNoiseValue);
        }
        else {
            nowAngle = lerp(nowAngle, 0.0, smoothPower);
        }

        nowX += sin(radians(nowAngle)) * stepLength;
        nowY -= cos(radians(nowAngle)) * stepLength;

        fill(0, 0, 100, 0.6);
        noStroke();

        float dotSize = map(t, 0.0, 0.15, 0, 1);

        push();
        translate(nowX, nowY);
        rotate(radians(nowAngle));
        NYArc(0, 0, _thickness, _thickness * 0.6, random(dotSize), 0.6);
        pop();
    }

    // draw head
    float sizeAngleRandom = random(0, 720);
    float headSizeX = (sin(radians(sizeAngleRandom)) + 1) / 2 * _headSize;
    float headSizeY = (cos(radians(sizeAngleRandom)) + 1) / 2 * _headSize;

    push();
    translate(nowX, nowY);
    rotate(radians(nowAngle));
    drawFungiHead(0, 0, headSizeX, headSizeY);
    pop();
}

void drawFungiHead(float _x, float _y, float _width, float _length) {
    float arcCount = _length * fungiHeadLineDensity;
    float stepLength = _length / arcCount;

    float nowX = _x;
    float nowY = _y;
    float nowAngle = 0;
    for (int i = 0; i < arcCount; i++) {
        float t = i / (arcCount - 1);
        float curveT = easeInSine(t);

        nowX += sin(radians(nowAngle)) * stepLength;
        nowY += -cos(radians(nowAngle)) * stepLength;

        float nowWidth = lerp(1.0, 0.2, curveT) * _width;
        float nowHeight = 0.6 * nowWidth;

        NYArc(nowX, nowY, nowWidth, nowHeight, random(3), 0.6);
    }
}

void NYArc(float _x, float _y, float _width, float _height, float _dotSize, float _density) {
    float dotCount = (_width + _height) * _density;

    push();
    translate(_x, _y);

    for (int i = 0; i < dotCount; i++) {
        float t = i / (dotCount - 1);

        float nowDotAngle = lerp(120, 240, t);

        float nowX = sin(radians(nowDotAngle)) * _width * 0.5;
        float nowY = -cos(radians(nowDotAngle)) * _height * 0.5;

        circle(nowX, nowY, _dotSize);
    }
    pop();

}


//-----------------------------

float easeInSine(float x) {
    return 1 -cos((x *PI) / 2);
}
float easeOutSine(float x) {
    return sin((x *PI) / 2);
}
float easeInOutSine(float x) {
    return -(cos(PI * x) - 1) / 2;
}


float easeInQuad(float x) {
    return x * x;
}
float easeOutQuad(float x) {
    return 1 - (1 - x) * (1 - x);
}
float easeInOutQuad(float x) {
    return x < 0.5 ? 2 * x * x : 1 -pow(-2 * x + 2, 2) / 2;
}


float easeInCubic(float x) {
    return x * x * x;
}
float easeOutCubic(float x) {
    return 1 -pow(1 - x, 3);
}
float easeInOutCubic(float x) {
    return x < 0.5 ? 4 * x * x * x : 1 -pow(-2 * x + 2, 3) / 2;
}


float easeInQuart(float x) {
    return x * x * x * x;
}
float easeOutQuart(float x) {
    return 1 -pow(1 - x, 4);
}
float easeInOutQuart(float x) {
    return x < 0.5 ? 8 * x * x * x * x : 1 -pow(-2 * x + 2, 4) / 2;
}


float easeInQuint(float x) {
    return x * x * x * x * x;
}
float easeOutQuint(float x) {
    return 1 -pow(1 - x, 5);
}
float easeInOutQuint(float x) {
    return x < 0.5 ? 16 * x * x * x * x * x : 1 -pow(-2 * x + 2, 5) / 2;
}


float easeInExpo(float x) {
    return x == 0 ? 0 :pow(2, 10 * x - 10);
}
float easeOutExpo(float x) {
    return x == 1 ? 1 : 1 -pow(2, -10 * x);
}
float easeInOutExpo(float x) {
    return x == 0
        ? 0
        : x == 1
            ? 1
            : x < 0.5 ?pow(2, 20 * x - 10) / 2
                : (2 -pow(2, -20 * x + 10)) / 2;
}


float easeInCirc(float x) {
    return 1 -sqrt(1 -pow(x, 2));
}
float easeOutCirc(float x) {
    return sqrt(1 -pow(x - 1, 2));
}
float easeInOutCirc(float x) {
    return x < 0.5
        ? (1 -sqrt(1 -pow(2 * x, 2))) / 2
        : (sqrt(1 -pow(-2 * x + 2, 2)) + 1) / 2;
}


float easeInBack(float x) {
    float c1 = 1.70158;
    float c3 = c1 + 1;

    return c3 * x * x * x - c1 * x * x;
}
float easeOutBack(float x) {
    float c1 = 1.70158;
    float c3 = c1 + 1;

    return 1 + c3 *pow(x - 1, 3) + c1 *pow(x - 1, 2);
}
float easeInOutBack(float x) {
    float c1 = 1.70158;
    float c2 = c1 * 1.525;

    return x < 0.5
        ? (pow(2 * x, 2) * ((c2 + 1) * 2 * x - c2)) / 2
        : (pow(2 * x - 2, 2) * ((c2 + 1) * (x * 2 - 2) + c2) + 2) / 2;
}


float easeInBounce(float x) {
    return 1 - easeOutBounce(1 - x);
}
float easeOutBounce(float x) {
    float n1 = 7.5625;
    float d1 = 2.75;

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
float easeInOutBounce(float x) {
    return x < 0.5
        ? (1 - easeOutBounce(1 - 2 * x)) / 2
        : (1 + easeOutBounce(2 * x - 1)) / 2;
}


float easeInElastic(float x) {
    float c4 = (2 * PI) / 3;

    return x == 0
        ? 0
        : x == 1
            ? 1
            : -pow(2, 10 * x - 10) *sin((x * 10 - 10.75) * c4);
}

float easeOutElastic(float x) {
    float c4 = (2 *PI) / 3;

    return x == 0
        ? 0
        : x == 1
            ? 1
            :pow(2, -10 * x) *sin((x * 10 - 0.75) * c4) + 1;
}

float easeInOutElastic(float x) {
    float c5 = (2 * PI) / 4.5;

    return x == 0
        ? 0
        : x == 1
            ? 1
            : x < 0.5
                ? -(pow(2, 20 * x - 10) *sin((20 * x - 11.125) * c5)) / 2
                : (pow(2, -20 * x + 10) *sin((20 * x - 11.125) * c5)) / 2 + 1;
}

//------------------------
  /*
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
  */
  
class Point {
  float x, y, wid;
  NYColor col;
  
  Point(float xx, float yy, float wi, NYColor co){
    x = xx;
    y = yy;
    wid = wi;
    col = co;
  }
  
}
class NYColor {
  
        float h;
        float s;
        float b;
        float a;
        
    NYColor(float _h, float _s, float _b, float _a) {
        this.h = _h;
        this.s = _s;
        this.b = _b;
        this.a = _a;
    }

    NYColor copy() {
        return new NYColor(this.h, this.s, this.b, this.a);
    }
    
    //slightRandomize(_hDiff = 10, _sDiff = 12, _bDiff = 12, _aDiff = 0.0) {
    void slightRandomize(float _hDiff, float _sDiff, float _bDiff, float _aDiff) {
        this.h += random(-0.5 * _hDiff, 0.5 * _hDiff);
        this.s += random(-0.5 * _sDiff, 0.5 * _sDiff);
        this.b += random(-0.5 * _bDiff, 0.5 * _bDiff);
        this.a += random(-0.5 * _aDiff, 0.5 * _aDiff);

        this.h = processHue(this.h);
    }

    float setColor() { //color()から書き換え
        return color(this.h, this.s, this.b, this.a);
    }

    NYColor newRandomColor(float _mainHue) {
        float h = processHue(_mainHue + random(-80, 80));
        float s = random(40, 100);
        float b = random(60, 100);

        return new NYColor(h, s, b, 1);
    }
}



//---------------------------------

float NYLerpHue(float _hueA, float _hueB, float _t) {
  float hueA = _hueA;
  float hueB = _hueB;
  
  float hueDiff = abs(hueB - hueA);
  
  if (abs((hueB - 360) - hueA) < hueDiff) {
    hueB -= 360;
  }
  else if (abs((hueB + 360) - hueA) < hueDiff) {
    hueB += 360;
  }
  else {
    return lerp(_hueA, _hueB, _t);
  }
  
  float resultHue = lerp(hueA, hueB, _t);
  
  if (resultHue < 0) {
    resultHue += 360;
  }
  else if (resultHue > 360) {
    resultHue -= 360;
  }
  
  return resultHue;
  }
  
  NYColor NYLerpColor(NYColor _colorA, NYColor _colorB, float _t) {
  float _hue = NYLerpHue(_colorA.h, _colorB.h, _t);
  float _sat = lerp(_colorA.s, _colorB.s, _t);
  float _bri = lerp(_colorA.b, _colorB.b, _t);
  float _alpha = lerp(_colorA.a, _colorB.a, _t);
  
  return new NYColor(_hue, _sat, _bri, _alpha);
  }
  
  int NYLerpP5Color(int _colorA, int _colorB, float _t) { //ここらの定義きれいにしたい
  float hueA = hue(_colorA);
  float hueB = hue(_colorB);
  
  float hueDiff = abs(hueB - hueA);
  
  if (abs((hueB - 360) - hueA) < hueDiff) {
    hueB -= 360;
  }
  else if (abs((hueB + 360) - hueA) < hueDiff) {
    hueB += 360;
  }
  else {
    return lerpColor(_colorA, _colorB, _t);
  }
  
  float satA = saturation(_colorA);
  float briA = brightness(_colorA);
  float alphaA = alpha(_colorA);
  
  float satB = saturation(_colorB);
  float briB = brightness(_colorB);
  float alphaB = alpha(_colorB);
  
  float resultHue = lerp(hueA, hueB, _t);
  float resultSat = lerp(satA, satB, _t);
  float resultBri = lerp(briA, briB, _t);
  float resultAlpha = lerp(alphaA, alphaB, _t);
  
  if (resultHue < 0) {
    resultHue += 360;
  }
  else if (resultHue > 360) {
    resultHue -= 360;
  }
  
  return color(resultHue, resultSat, resultBri, resultAlpha);
  }
  
  float processHue(float _hue) {
  float result = _hue % 360;
  if (result < 0) {
    result += 360;
  }
  return result;
  }
  
  // get angle between two points and return in degrees
  float getAngle(float _x1, float _y1, float _x2, float _y2) {
  float xDiff = _x2 - _x1;
  float yDiff = _y2 - _y1;
  return atan2(yDiff, xDiff) * 180 / PI + 90;
  }