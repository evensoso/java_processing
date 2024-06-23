int[] colors = {color(40, 100, 40), color(247, 57, 57, 1), color(255, 218, 51, 1), color(12, 72, 150, 1), color(72, 183, 247, 1), color(43, 43, 43, 1), color(40, 100, 40), color(247, 57, 57, 1), color(255, 218, 51, 1)};
//["#f73939", "#ffda33", "#0c4896", "#48b7f7", "#2b2b2b", "#f78e2c", "#3BD89F", "#a4459f"];
int count = 0;

void setup() {
  size(1080, 1080);
  rectMode(CENTER);
  background(255);
 
  rectRec(width / 2, height / 2, width, height, 11);
  noLoop();
  
  print("Setuped;");
}

void draw() {
  print(count + ";");
  count++;
}

void rectRec(float x, float y, float w, float h, float n) {
  float ww = random(0.1, 0.9) * w;
  float hh = random(0.1, 0.9) * h;
  n--;

  if (n >= 0) {
    if (w < h) {
      rectRec(x, y - (h / 2) + (hh / 2), w, hh, n);
      rectRec(x, y + (h / 2) - (h - hh) / 2, w, (h - hh), n);
    } else {
      rectRec(x - (w / 2) + (ww / 2), y, ww, h, n);
      rectRec(x + (w / 2) - (w - ww) / 2, y, (w - ww), h, n);
    }
  } else {
    takeYourHands(x, y, w, h);
  }
}

void takeYourHands(float x, float y, float w, float h) {
  float side = max(w, h);
  int rnd = int(random(6));
  int bgCol = color(random(248, 255), random(248, 255), random(248, 255));
  
  print(rnd + ";");
  //rndで何を描くか決める★
  
  if (random(1) < 0.2) rnd = 0;
  
  push();
  
  translate(x, y);
  strokeWeight(1);
  stroke(0);
  noStroke();
  rect(0, 0, w, h);
  //drawingContext.clip();
  noStroke();
  //background(bgCol);
  translate(- side / 2, - side / 2);
  
  
  //--circle-----------------------------------
  if (rnd == 0) {
    int c = int(random(5, 20));
    float s = side / c;
    noStroke();
    fill(0);
    for (int i = 0; i < c; i++) {
      for (int j = 0; j < c; j++) {
        float xx = i * s + s / 2;
        float yy = j * s + s / 2;

        circle(xx, yy, s / 3);
      }
    }
    
  //--square-----------------------------------
  } else if (rnd == 1) {
    int c = int(random(5, 10));
    float s = side / c;
    noStroke();
    fill(0);
    for (int i = 0; i < c; i++) {
      for (int j = 0; j < c; j++) {
        float xx = i * s + s / 2;
        float yy = j * s + s / 2;
        if ((i + j) % 2 == 0) {
          square(xx, yy, s);
        }
      }
    }
    
  //--rect-----------------------------------
  } else if (rnd == 2) {
    int c = int(random(10, 30));
    float s = side / c;
    int n = int(random(2));
    int col1 = 777;
    int col2 = col1;
    /*
    while (col1 == col2) {
      col1 = colors[0];
      col2 = colors[0];
    }
    */
    if(random(1) < 0.25){
      col1 = bgCol;
      col2 = color(0, 0, 0);
    }
    noStroke();
    fill(0);
    for (int i = 0; i < c; i++) {
      float xx = i * s + s / 2;

      if (i % 2 == 0) {
        fill(col1);
      } else {
        fill(col2);
      }
      if (n == 0) {
        rect(xx, h / 2, s, side);
      } else {
        rect(w / 2, xx, side, s);
      }

    }
    
  //--circle-----------------------------------
  } else if (rnd == 3) {
    if(random(1) < 0.5){
      noFill();
      stroke(colors[int(random(0,7))]);
      strokeWeight(side/20);
    }else{
      fill(colors[int(random(1,7))]);
      noStroke();
    }
    circle(side / 2, side / 2, min(w, h) * random(0.7, 0.9));
    
  //--circles-----------------------------------
  } else if (rnd == 4) {
    int n = int(random(15, 30));
    float offx = random(-0.5, 1.5) * side;
    float offy = random(-0.5, 1.5) * side;
    for (int i = 0; i < n; i++) {
      float d = map(i, 0, n, max(w, h) * 3, 0);
      if (i % 2 == 0) fill(bgCol);
      else fill(0);
      circle(offx, offy, d);
    }
  }
  
  //-------------------------------------
  else if (rnd == 5) {
    fill(colors[int(random(1,7))]);
    translate((side - w) / 2, (side - h) / 2);
    beginShape();
    vertex(w / 2, 0);
    vertex(w, h / 2);
    vertex(w / 2, h);
    vertex(0, h / 2);
    endShape();
  }
  pop();
}