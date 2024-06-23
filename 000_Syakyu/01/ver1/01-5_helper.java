void NYLerpHue(_hueA, _hueB, _t) {
  int hueA = _hueA;
  int hueB = _hueB;

  int hueDiff = abs(hueB - hueA);

  if (abs((hueB - 360) - hueA) < hueDiff) {
    hueB -= 360;
  }
  else if (abs((hueB + 360) - hueA) < hueDiff) {
    hueB += 360;
  }
  else {
    return lerp(_hueA, _hueB, _t);
  }

  int resultHue = lerp(hueA, hueB, _t);

  if (resultHue < 0) {
    resultHue += 360;
  }
  else if (resultHue > 360) {
    resultHue -= 360;
  }

  return resultHue;
}

void NYLerpColor(_colorA, _colorB, _t) {
  int _hue = NYLerpHue(_colorA.h, _colorB.h, _t);
  int _sat = lerp(_colorA.s, _colorB.s, _t);
  int _bri = lerp(_colorA.b, _colorB.b, _t);
  int _alpha = lerp(_colorA.a, _colorB.a, _t);

  return new NYColor(_hue, _sat, _bri, _alpha);
}

void NYLerpP5Color(_colorA, _colorB, _t) {
  int hueA = hue(_colorA);
  int hueB = hue(_colorB);

  int hueDiff = abs(hueB - hueA);

  if (abs((hueB - 360) - hueA) < hueDiff) {
    hueB -= 360;
  }
  else if (abs((hueB + 360) - hueA) < hueDiff) {
    hueB += 360;
  }
  else {
    return lerpColor(_colorA, _colorB, _t);
  }

  int satA = saturation(_colorA);
  int briA = brightness(_colorA);
  int alphaA = alpha(_colorA);

  int satB = saturation(_colorB);
  int briB = brightness(_colorB);
  int alphaB = alpha(_colorB);

  int resultHue = lerp(hueA, hueB, _t);
  int resultSat = lerp(satA, satB, _t);
  int resultBri = lerp(briA, briB, _t);
  int resultAlpha = lerp(alphaA, alphaB, _t);

  if (resultHue < 0) {
    resultHue += 360;
  }
  else if (resultHue > 360) {
    resultHue -= 360;
  }

  return color(resultHue, resultSat, resultBri, resultAlpha);
}

void processHue(_hue) {
  int result = _hue % 360;
  if (result < 0) {
    result += 360;
  }
  return result;
}

// get angle between two points and return in degrees
void getAngle(_x1, _y1, _x2, _y2) {
  int xDiff = _x2 - _x1;
  int yDiff = _y2 - _y1;
  return atan2(yDiff, xDiff) * 180 / PI + 90;
}