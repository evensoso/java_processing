/**
 * Bad Moon Rising.
 * draws wierd moving pattern with Archimedes's spiral.
 * 
 * @author @deconbatch
 * @version 0.1
 * Processing 3.2.1
 * created 19.11.07
 */

void setup() {
  size(720, 720);
  rectMode(CENTER);
  smooth();
  noStroke();
  noLoop();
}

void draw() {

  int   ptnMax    = 3;
  int   frmMax    = 24 * 6;
  float spiralGap = 0.5;
  float plotDivInit = random(1.0, 2.0);

  // fetch color pallet
  FetchColor fc = new FetchColor();
  color[][] bgColor = new color[3][2];
  color[][] fgColor = new color[3][3];
  for (int i = 0; i < 2; i++) {
    bgColor[0][i] = fc.getColor(i);
  }
  for (int i = 0; i < 3; i++) {
    fgColor[0][i] = fc.getColor(i + 2);
  }
  // make 2 addtion color BGR, BRG
  for (int i = 0; i < 2; i++) {
    bgColor[1][i] = color(blue(bgColor[0][i]), green(bgColor[0][i]), red(bgColor[0][i]));
    bgColor[2][i] = color(green(bgColor[0][i]), blue(bgColor[0][i]), red(bgColor[0][i]));
  }
  for (int i = 0; i < 3; i++) {
    fgColor[1][i] = color(blue(fgColor[0][i]), green(fgColor[0][i]), red(fgColor[0][i]));
    fgColor[2][i] = color(green(fgColor[0][i]), blue(fgColor[0][i]), red(fgColor[0][i]));
  }

  colorMode(RGB, 255, 255, 255, 100);
  blendMode(BLEND);
  translate(width / 2, height / 2);

  for (int ptnCnt = 0; ptnCnt < ptnMax; ++ptnCnt) {
    for (int frmCnt = 0; frmCnt < frmMax; ++frmCnt) {

      // animation moving parameters
      float fRatioUnit   = map(frmCnt, 0, frmMax, 0.0, 1.0);
      float fRatioWhole  = map(frmMax * ptnCnt + frmCnt, 0, frmMax * ptnMax, 0.0, 1.0);
      float ecRatioUnit  = easeInOutCubic(fRatioUnit);
      float ecRatioWhole = easeInOutCubic(fRatioWhole);
      float emRatioUnit  = easeMiddle(fRatioUnit);
      float ecRatioStep  = (ptnCnt + ecRatioUnit) / ptnMax;
      float animate01    = noise(0.1 * (1.0 + cos(TWO_PI * ecRatioStep)), 0.1 * (1.0 + sin(TWO_PI * fRatioWhole)));
      float animate02    = noise(0.1 * (5.0 + cos(TWO_PI * ecRatioStep)), 0.1 * (5.0 + sin(TWO_PI * fRatioWhole)));
      float animate03    = sin(PI * ecRatioStep);

      float plotDiv = plotDivInit + sin(TWO_PI * ecRatioWhole) * 0.005;
      int   pointMax = floor(10000 * plotDiv * plotDiv);

      color bgc = lerpColor(bgColor[ptnCnt][0], bgColor[(ptnCnt+1) % 3][0], ecRatioUnit);
      background(bgc, 100.0);
      noStroke();

      for (int pointCnt = 0; pointCnt < pointMax; ++pointCnt) {

        float pointRatio = map(pointCnt, 0, pointMax, 0.0, 1.0);

        float radianDiv = 1.0 + sqrt(pointCnt) * plotDiv;
        float radian = TWO_PI / radianDiv  * pointCnt;
        float pX = spiralGap * radian * cos(radian);
        float pY = spiralGap * radian * sin(radian);
    
        float brushAlp = 100.0;
        float brushSiz = constrain(map(cos(PI * spiralGap * radian / width), 1.0, 0.0, 2.3, 0.2), 0.0, 1.0) * constrain(map(sin(TWO_PI * noise(pX / width * 3.0, pY / height * 3.0) * (3.0 + 5.0 * animate01)), -1.0, 1.0, 0.0, 20.0), 0.0, 1.0) * spiralGap * 8.0 / plotDiv;
        float currIndex = 6.0 * (sin(TWO_PI * noise(animate03 * 0.05, spiralGap * radian / width) * 10.0) + sin(TWO_PI * noise(pX / width, pY / height) * (5.0 + animate02 * 3.0)));
        int colIndex = floor(abs(currIndex)) % 3;
      
        color fgc = lerpColor(fgColor[ptnCnt][colIndex], fgColor[(ptnCnt+1) % 3][colIndex], ecRatioUnit);
        fill(fgc, brushAlp);
        ellipse(pX, pY, brushSiz, brushSiz);

      }
      casing();
      
      
      //add----
      String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\41_Lissajous\\1\\41_Penrose-30_######.png";
      saveFrame(outP);
      //----
  
    }
  }
  //exit();
}

/**
 * easeInOutCubic easing function.
 * @param  t     0.0 - 1.0 : linear value.
 * @return float 0.0 - 1.0 : eased value.
 */
float easeInOutCubic(float t) {
  t *= 2.0;
  if (t < 1.0) {
    return pow(t, 3) / 2.0;
  }
  t -= 2.0;
  return (pow(t, 3) + 2.0) / 2.0;
}

/**
 * easeMiddle easing function.
 * @param  t     0.0 - 1.0 : linear value.
 * @return float 0.0 - 1.0 : eased value.
 */
float easeMiddle(float t) {
  t *= 2.0;
  if (t < 1.0) {
    return sin(HALF_PI * t) * 0.5;
  }
  return 1.0 - sin(HALF_PI * t) * 0.5;
}

/**
 * casing : draw fancy casing
 */
private void casing() {
  fill(0.0, 0.0, 0.0, 0.0);
  strokeWeight(54.0);
  stroke(0.0, 0.0, 0.0, 100.0);
  rect(0.0, 0.0, width, height);
  strokeWeight(50.0);
  stroke(255.0, 255.0, 255.0, 100.0);
  rect(0.0, 0.0, width, height);
  noStroke();
  noFill();
}

/**
 * FetchColor : get 5 RGB color infomation from args and sort descending them by brightness.
 * 
 */
import java.util.Arrays;
import java.util.Comparator;

class FetchColor {

  int colorCnt = 5;
  color[] colorAry = new color[colorCnt];
  
  FetchColor() {
    colorMode(RGB, 255);
    for (int i = 0; i < colorCnt; i++) {
      colorAry[i] = color(255, 255, 255);
    }
  
    // get 5 RGB color infomation from args.
    // and translate RGB to HSB
    color[] argColor = new color[colorCnt];
    float[] baseHue  = new float[colorCnt];
    float[] baseSat  = new float[colorCnt];
    float[] baseBri  = new float[colorCnt];
    if (args == null) {
      for (int i = 0; i < colorCnt; i++) {
        colorMode(HSB, 360, 100, 100, 100);
        baseHue[i]  = random(360.0);
        baseSat[i] = 40.0;
        baseBri[i] = 10.0 + i * 10.0;
        argColor[i] = color(baseHue[i], baseSat[i], baseBri[i], 100.0);
      }
    } else {
      int cnt = 0;
      int i = 0;
      float r = 0.0;
      float g = 0.0;
      float b = 0.0;
      for (String s : args[0].split(",", 0)) {
        if (cnt % 3 == 0) r = float(s);
        if (cnt % 3 == 1) g = float(s);
        if (cnt % 3 == 2) {
          b = float(s);
          colorMode(RGB, 255);
          argColor[i] = color(r, g, b);
          colorMode(HSB, 360, 100, 100, 100);
          baseHue[i] = hue(argColor[i]);
          baseSat[i] = saturation(argColor[i]);
          baseBri[i] = brightness(argColor[i]);
          i++;
        }
        cnt++;
      }
    }

    // sort descending by brightness
    colorMode(RGB, 255);
    float[][] sortBri = new float[colorCnt][2];
    for (int i = 0; i < colorCnt; i++) {
      sortBri[i][0] = baseBri[i];
      sortBri[i][1] = i;
    }
    Arrays.sort(sortBri, new Comparator<float[]>() {
        @Override
        public int compare(float[] a, float[] b) {
          if(a[0] > b[0]) {
            return 1;
          } else if(a[0] < b[0]) {
            return -1;
          }
          return 0;
        }
      }
      );
    for (int i = 0; i < colorCnt; i++) {
      colorAry[i] = argColor[int(sortBri[i][1])];
    }
  }  

  color getColor(int i) {
    return colorAry[i];
  }

}