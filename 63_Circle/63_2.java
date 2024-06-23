int global_n = 0;
float[] rand = {1, 3, 5, 7, 9, 11, 13};
int count=1;

void setup() {
  size(800, 800);
  colorMode(HSB, 360, 100, 100, 100);
  //angleMode(DEGREES);
}

void draw() {
  
        //add----
      String root = "D:\\09_music_create\\03_PICTURE\\99_Render\\";
      String fileName = "63_Wave-FF_" + "c_" + count + "_######.png";
      String outP = root + "63_Wave\\" + "2\\" + fileName;
      //----
       
      //add----
      print(count + ";");
      if (count >= 1800) noLoop();
      //----
      
  blendMode(BLEND);
  background(0, 0, 20);
  randomSeed(0);
  blendMode(ADD);
  // randomSeed(random(10000));
  // drawingContext.shadowColor = color(0, 0, 0, 15);
  // drawingContext.shadowBlur = width / 30;
  // drawingContext.shadowOffsetX = width / 30 / 2;
  // drawingContext.shadowOffsetY = width / 30 / 2;
  consecutiveHollowedOutArc(
    width / 2,
    height / 2,
    50,
    300,
    0,
    360
  );
  // noLoop();
   count++;
 saveFrame(outP);
}

void consecutiveHollowedOutArc(
  float center_x,
  float center_y,
  float r_min,
  float r_max,
  float start_angle,
  float end_angle
) {
  push();
  translate(center_x, center_y);
  float angle = start_angle;
  float angle_step;
  Boolean mode = random(10) > 0.5;
  float r = r_max;
  int r_step = 10;
  while (r > r_min) {
    if (mode == false) {
      r_step = int(random(3, 10)) * 3;
    } else {
      r_step = int(random(3, 10)) * 10;
    }
    angle = start_angle;
    while (angle < end_angle) {
      if (mode == true) {
        angle_step = int(random(random(10)) * 4 + 1) * 5;
      } else {
        angle_step = int(random(1, 5)) * 15;
      }
      if (random(1) > 0.95) mode = !mode;
      if (angle + angle_step > end_angle) angle_step = end_angle - angle;
      // arc(0, 0, r_max, r_max, angle, angle + angle_step,PIE);
      hollowedOutArc(
        0,
        0,
        r,
        max(r / 4, r - r_step),
        angle,
        angle + angle_step,
        true,
        1
      );
      angle += angle_step;
    }
    r -= r_step;
  }

  pop();
}

void hollowedOutArc(
  float x,
  float y,
  float maxD,
  float minD,
  float startAngle,
  float endAngle,
  Boolean bool,
  float angleStep
) {
  float dir;
  
  if (random(1) > 0.5) dir = -1;
  else dir = 1;
  
  push();
  translate(x, y);
  global_n++;
  if (global_n % 2 == 0) stroke(0, 29, 30, 20);
  else stroke(20, 0, 30, 10);
  
  noFill();
  if (bool) {
    if (global_n++ % 2 == 0) {
      float angle = min(startAngle, endAngle);
      float angle_plus = (max(endAngle, startAngle) - min(endAngle, startAngle)) / (rand[int(random(6))]);
      while (angle < endAngle) {
        hollowedOutArc(0, 0, maxD, minD, angle, angle + angle_plus, false, 1);
        angle += angle_plus;
      }
    } else {
      float d = minD;
      float d_plus = (maxD - minD) / (rand[int(random(6))]);
      while (d < maxD) {
        hollowedOutArc(0, 0, d, d + d_plus, startAngle, endAngle, false, 1);
        d += d_plus;
      }
    }
  } else {
    float t = (1 + ((maxD + startAngle / 360 + (dir * frameCount) / 100) % 1)) % 1;
    t = easeInOutCirc(t);
    float sw = max(maxD, minD) - min(maxD, minD);
    strokeCap(SQUARE);
    strokeWeight((1 - t) * sw);
    float d = minD + sw / 2;
    //float ld = (2 * d * PIE * (endAngle - startAngle)) / 360;
    //drawingContext.setLineDash([ld]);
    //drawingContext.lineDashOffset = ld * t * 2;
    beginShape();
    for (int a = int(startAngle); a <= endAngle; a += angleStep) {
      vertex(cos(a) * d, sin(a) * d);
    }
    endShape();
  }
  pop();
}

float easeInOutCirc(float x) {
  if (x < 0.5) return (1 - sqrt(1 - pow(2 * x, 2))) / 2;
  else return (sqrt(1 - pow(-2 * x + 2, 2)) + 1) / 2;
}

float easeInOutElastic(float x) {
  float c5 = (2 * PIE) / 4.5;
  
  if (x == 0)return 0;
  else if (x == 1) return 1;
  else if (x < 0.5) return -(pow(2, 20 * x - 10) * sin((20 * x - 11.125) * c5)) / 2;
  else return (pow(2, -20 * x + 10) * sin((20 * x - 11.125) * c5)) / 2 + 1;
}