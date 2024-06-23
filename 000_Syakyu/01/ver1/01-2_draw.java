int treeTurnNoiseZ = 1234;

int sketchDensity = 0.2;

int dotDensity = 0.6;
int dotSize = 6;
int dotLength = 60;

int dotTrailDensity = 0.2;

int dotNoiseScale = 0.01;
int dotNoiseZ = 6345;

int dotHDiff = 30;
int dotSDiff = 20;
int dotBDiff = 20;

int areaNoiseScale = 0.001;
int areaNoiseZ = 1234;
int areaNoiseDrawMax = 1.0;
int areaNoiseDrawMin = 0.0;

int smoothSkipChanceMultiplier = 0.1;
int smoothSkipChanceRange = 0.0;
int skipChance = 0.0;


async void drawStick(_fromPoint, _toPoint) {

    int sliceCount = dist(_fromPoint.x, _fromPoint.y, _toPoint.x, _toPoint.y) * sketchDensity;

    // int slope = getAngle(_fromPoint.x, _fromPoint.y, _toPoint.x, _toPoint.y) - 90;

    for (int i = 0; i < sliceCount; i++) {

        int t = i / (sliceCount - 1);

        int nowX = lerp(_fromPoint.x, _toPoint.x, t);
        int nowY = lerp(_fromPoint.y, _toPoint.y, t);

        int nowWidth = lerp(_fromPoint.width, _toPoint.width, t);
        int nowColor = NYLerpColor(_fromPoint.color, _toPoint.color, t);

        int xNoiseValue = noise(nowX * 0.001, nowY * 0.001, treeTurnNoiseZ);

        nowX += lerp(-0.5, 0.5, xNoiseValue) * 300;

        spawnDotLine(nowX - 0.5 * nowWidth, nowY, nowX + 0.5 * nowWidth, nowY, nowColor);

        if (i % AWAIT_LINES_PER_TICK == 0)
            await sleep(1);
    }
    // NYLine(_fromPoint.x, _fromPoint.y, _toPoint.x, _toPoint.y, _fromPoint.color, _toPoint.color);
}

void spawnDotLine(_x1, _y1, _x2, _y2, _color) {
    int spawnCount = dist(_x1, _y1, _x2, _y2) * dotDensity;
    for (int i = 0; i < spawnCount; i++) {
        int t = i / (spawnCount - 1);
        int nowX = lerp(_x1, _x2, t);
        int nowY = lerp(_y1, _y2, t);

        int areaNoiseValue = noise(nowX * areaNoiseScale, nowY * areaNoiseScale, areaNoiseZ);
        int closeRatio = min(abs(areaNoiseValue - areaNoiseDrawMin), abs(areaNoiseValue - areaNoiseDrawMax));

        int nowSkipChance = skipChance;

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

void spawnDot(_x, _y, _size, _length, _color) {
    int dotCount = _length * dotTrailDensity;
    int dotDistance = _length / dotCount;

    int nowX = _x;
    int nowY = _y;
    int nowSize = _size;

    int fromColor = _color;
    int toColor = _color.copy();
    toColor.slightRandomize(dotHDiff, dotSDiff, dotBDiff);

    for (int i = 0; i < dotCount; i++) {
        int t = i / (dotCount - 1);

        nowSize = lerp(_size, 0, easeOutSine(t));

        int rotNoise = noise(nowX * dotNoiseScale, nowY * dotNoiseScale, dotNoiseZ);

        nowX += sin(radians(rotNoise * 720)) * dotDistance;
        nowY -= cos(radians(rotNoise * 720)) * dotDistance;

        int nowColor = NYLerpColor(fromColor, toColor, t);

        noStroke();
        fill(nowColor.h, nowColor.s, nowColor.b, nowColor.a);
        circle(nowX, nowY, nowSize);
    }
}

void drawStickFungi(_fromPoint, _toPoint, _xRatio, _yRatio) {

    int nowX = lerp(_fromPoint.x, _toPoint.x, _yRatio);
    int nowY = lerp(_fromPoint.y, _toPoint.y, _yRatio);
    int nowWidth = lerp(_fromPoint.width, _toPoint.width, _yRatio);

    int xNoiseValue = noise(nowX * 0.001, nowY * 0.001, treeTurnNoiseZ);
    nowX += lerp(-0.5, 0.5, xNoiseValue) * 300;

    int leftX = nowX - 0.5 * nowWidth;
    int rightX = nowX + 0.5 * nowWidth;
    int spawnX = lerp(leftX, rightX, _xRatio);

    int areaNoiseValue = noise(nowX * areaNoiseScale, nowY * areaNoiseScale, areaNoiseZ);
    if (areaNoiseValue < areaNoiseDrawMin || areaNoiseValue > areaNoiseDrawMax) {
        return false;
    }

    int fungiLength = random(30, 90);
    int fungiThickness = random(1, 3);
    int headSize = random(6, 12);

    int stickAngle = getAngle(_fromPoint.x, _fromPoint.y, _toPoint.x, _toPoint.y);
    int startAngle = 0.0;

    if(_xRatio < 0.5)
        startAngle = lerp(-120, -40, _xRatio * 2) + random(-20, 20);
    else
        startAngle = lerp(40, 120, (_xRatio - 0.5) * 2) + random(-20, 20);

    startAngle += stickAngle;
    spawnFungi(spawnX, nowY, fungiLength, fungiThickness, headSize, startAngle);
    return true;
}

int fungiLineDensity = 0.8;
int fungiHeadLineDensity = 0.9;

void spawnFungi(_x, _y, _length, _thickness, _headSize, _startAngle) {
    int nowX = _x;
    int nowY = _y;

    int nowAngle = _startAngle;
    int smoothPower = random(0.01, 0.06);

    int lineCount = _length * fungiLineDensity;
    int stepLength = _length / lineCount;

    for (int i = 0; i < lineCount; i++) {
        int t = i / (lineCount - 1);

        if (t < 0.7) {
            int angleNoiseValue = noise(nowX * 0.01, nowY * 0.01, 3456)
            nowAngle += lerp(-12, 12, angleNoiseValue);
        }
        else {
            nowAngle = lerp(nowAngle, 0.0, smoothPower);
        }

        nowX += sin(radians(nowAngle)) * stepLength;
        nowY -= cos(radians(nowAngle)) * stepLength;

        fill(0, 0, 100, 0.6);
        noStroke();

        int dotSize = map(t, 0.0, 0.15, 0, 1, true);

        push();
        translate(nowX, nowY);
        rotate(radians(nowAngle));
        NYArc(0, 0, _thickness, _thickness * 0.6, random(dotSize), 0.6);
        pop();
    }

    // draw head
    int sizeAngleRandom = random(0, 720);
    int headSizeX = (sin(radians(sizeAngleRandom)) + 1) / 2 * _headSize;
    int headSizeY = (cos(radians(sizeAngleRandom)) + 1) / 2 * _headSize;

    push();
    translate(nowX, nowY);
    rotate(radians(nowAngle));
    drawFungiHead(0, 0, headSizeX, headSizeY);
    pop();
}

void drawFungiHead(_x, _y, _width, _length) {
    int arcCount = _length * fungiHeadLineDensity;
    int stepLength = _length / arcCount;

    int nowX = _x;
    int nowY = _y;
    int nowAngle = 0;
    for (int i = 0; i < arcCount; i++) {
        int t = i / (arcCount - 1);
        int curveT = easeInSine(t);

        nowX += sin(radians(nowAngle)) * stepLength;
        nowY += -cos(radians(nowAngle)) * stepLength;

        int nowWidth = lerp(1.0, 0.2, curveT) * _width;
        int nowHeight = 0.6 * nowWidth;

        NYArc(nowX, nowY, nowWidth, nowHeight, random(3), 0.6);
    }
}

void NYArc(_x, _y, _width, _height, _dotSize = 3, _density = 0.6) {
    int dotCount = (_width + _height) * _density;

    push();
    translate(_x, _y);

    for (int i = 0; i < dotCount; i++) {
        int t = i / (dotCount - 1);

        int nowDotAngle = lerp(120, 240, t);

        int nowX = sin(radians(nowDotAngle)) * _width * 0.5;
        int nowY = -cos(radians(nowDotAngle)) * _height * 0.5;

        circle(nowX, nowY, _dotSize);
    }
    pop();

}
