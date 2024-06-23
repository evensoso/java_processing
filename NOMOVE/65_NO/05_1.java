int size0 = 4096;
//int urlParams = new URLSearchParams(windowthis.locationthis.search);
//int custom_size = get_parameter("res", size0)
//float wid = 640;
//int hei = windowthis.innerHeight;
//int canvas_size = Maththis.min(WIDTH, HEIGHT);
//int res = canvas_size / custom_size;


// Features
//int frame = fxrand() < 0.15;
Boolean colors;

// Editable parameters
float alpha = 0.4;
int times = 4;
float border = 0.2;
int max_frames = 128;
int amount = 1024;
Field field;

int scaling = 640 / size0;

////////////////////
// Base voids //
////////////////////


//let field

void setup() {
  
  colorMode(HSB, 1);
  pixelDensity(1 / res);
  size(640, 640);
  set_colors();
  noStroke();

  field = new Field();
}

void draw() {
  
  colors = fxrand() < 0.05;

  if (((frameCount % max_frames) == 0) || (frameCount == 1)) {
    //field.points = {};
    field.initialize_points(amount);
  }
  field.update();

  if (frameCount > max_frames * times) {
    noLoop();
    //fxpreview();
  }
}


//////////////////////
// Custom voids //
//////////////////////

float fxrand() {
  return random(100);
}

/*
float get_parameter(float param, float base) {
  if (urlParams.get(param)) {
    return Number(urlParams.get(param));
  } else {
    return base;
  }
}
*/

Particle[] get_random_array(float min, float max, float amount) {
  Particle[] ar = {};
  for (int i = 0; i < amount; i++) {
    append(ar, min + fxrand() * (max - min));
  }
  return ar;
}

void custom_circle(float x, float y, float s) {
  circle(640 * (border + x * (1 - 2 * border)), 640 * (border + y * (1 - 2 * border)), 4 * s);
}

void set_colors() {
  float hue = fxrand();
  float f_max = 0.1;
  float f = colors ? f_max * (fxrand() + 1) / 2 : 1 - f_max * (fxrand() + 1) / 2;

  background(hue, 1 - f, f);
  fill((hue + f) % 1, 1 - f, 1 - f, alpha);
}


////////////////////
// Custom classes //
////////////////////


class Field {
  
    Particle[] points = {};
    float m = round(8 + fxrand() * 4);
    Particle[] a = get_random_array(- this.m, this.m, 32);
    Particle[] b = get_random_array(- this.m, this.m, 32);
    Particle[] c = get_random_array(- this.m, this.m, 32);
    
  Field() {
    //points = [];
    m = round(8 + fxrand() * 4);
    a = get_random_array(- this.m, this.m, 32);
    b = get_random_array(- this.m, this.m, 32);
    c = get_random_array(- this.m, this.m, 32);
  }

  void update() {
    for (int i = 0; i < this.points.length; i++) {
      this.points[i].update();
    }
  }

  void initialize_points(float n) {
    Particle[] points = {};
    for (int i = 0; i < n; i++) {
      float x = fxrand() * 2 - 1;
      float y = fxrand() * 2 - 1;
      this.points = append(this.points, new Particle(x, y, this, true));
      this.points = append(this.points, new Particle(x, y, this, false));
    }
  }
}


class Particle {
  float x,  y, parent;
  Boolean forward;
  V2 point;
  float step;
  Particle(float xx, float yy, float getparent, Boolean getforward) {
    this.point = new V2(x, y);
    forward = getforward;
    parent = getparent;
    this.step = 0.01 / this.parent.m;
    this.step *= getforward ? 1 : -1;
  }

  void update() {
    let point = this.move_point();
    if (frame) {
      this.point = fxrand() < 0.5 ? point : new V2(min(max(-1, point.x), 1), min(max(-1, point.y), 1)); 
    } else {
      this.point = new V2(min(max(-1, point.x), 1), min(max(-1, point.y), 1)) ;
    }

    this.point.plot(1);
  }

  void move_point() {
    let dx = this.get_delta(0);
    let dy = this.get_delta(1);
    
    return this.point.translate(dx * this.step, dy * this.step);
  }

  float get_delta(float i) {
    let value = 0;
    let n = 8;
    let x = this.point.x;
    let y = this.point.y;
    value += this.parent.a[0 + n * i] * sin(x * this.parent.b[0 + n * i] + this.parent.c[0 + n * i]);
    value += this.parent.a[1 + n * i] * sin(y * this.parent.b[1 + n * i] + this.parent.c[1 + n * i]);
    value += this.parent.a[2 + n * i] * cos(x * this.parent.b[2 + n * i] + this.parent.c[2 + n * i]);
    value += this.parent.a[3 + n * i] * cos(y * this.parent.b[3 + n * i] + this.parent.c[3 + n * i]);
    value += this.parent.a[4 + n * i] * x + this.parent.b[4 + n * i] * x*x + this.parent.c[4 + n * i] * x*x*x;
    value += this.parent.a[5 + n * i] * y + this.parent.b[5 + n * i] * y*y + this.parent.c[5 + n * i] * y*y*y;
    value += this.parent.a[6 + n * i] * x * y + this.parent.b[6 + n * i] * x*x * y + this.parent.c[6 + n * i] * x * y*y;
    value += this.parent.a[7 + n * i] * x*x * y*y + this.parent.b[7 + n * i] * x*x * y*y*y + this.parent.c[7 + n * i] * x*x*x * y*y;
    return value;
  }
}

class V2 {
  int x, y;
  V2 (float xx, float yy) {
    this.x = xx;
    this.y = yy;
  }

  void plot(float s) {
    custom_circle((1 + this.x) / 2, (1 + this.y) / 2, s * scaling);
  }

  V2 translate(float dx, float dy) {
    return new V2(this.x + dx, this.y + dy);
  }

  float distance(float v1) {
    return sqrt((this.x - v1.x)*(this.x - v1.x) + (this.y - v1.y)*(this.y - v1.y));
  }
}