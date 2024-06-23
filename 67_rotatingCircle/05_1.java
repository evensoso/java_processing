// by SamuelYAN
// more works //
// https://twitter.com/SamuelAnn0924
// https://www.instagram.com/samuel_yan_1990/


float t;
float num, vNum;
float radius, mySize, margin;
ArrayList<Float> sizes = new ArrayList<Float>();

//int colors = [];
color[] colors0 = {color(0, 100, 20, 10), color(80, 100, 30, 20), color(40, 100, 40, 60), color(60, 100, 50, 200)};
String[] colors22 = {"#070A0D", "#171F26", "#4A5259", "#7B848C", "#AEB7BF"};
String[] colors23 = {"#D94389", "#4D578C", "#3791A6", "#3DF2D1", "#F28080"};
String[] colors24 = {"#F28D35", "#D96A29", "#A66641", "#D9B0A7", "#F2DAD8"};
String[] colors25 = {"#F2A7D8", "#473959", "#655A8C", "#9F8FD9", "#5979D9"};
String[] colors26 = {"#025951", "#012623", "#21BF92", "#73D9BC", "#0D0D0D"};
String[] colors7 = {"#fefefe", "#fffffb", "#fafdff", "#fef9fb", "#f7fcfe"};
String[] colors8 = {"#8c75ff", "#c553d2", "#2dfd60", "#2788f5", "#23054f", "#f21252", "#8834f1", "#c4dd92", "#184fd3", "#f9fee2", "#2E294E", "#541388", "#F1E9DA", "#FFD400", "#D90368", "#e9baaa", "#ffa07a", "#164555", "#ffe1d0", "#acd9e7", "#4596c7", "#6d8370", "#e45240", "#21d3a4", "#3303f9", "#cd2220", "#173df6", "#244ca8", "#a00360", "#b31016"};
String[] colors11 = {"#025159", "#3E848C", "#7AB8BF", "#C4EEF2", "#A67458"};
String[] colors12 = {"#10454F", "#506266", "#818274", "#A3AB78", "#BDE038"};
String[] colors13 = {"#D96690", "#F28DB2", "#F2C9E0", "#89C2D9", "#88E8F2"};
color[] color_setup1, color_setup2;
float color_bg;
ArrayList<PVector> v_planet = new ArrayList<PVector>();
long seed;
int count = 1;

void setup() {
  //seed = random(1) * 12643;
  randomSeed(12643);
  frameRate(50);
  // pixelDensity(5);
  mySize = min(640, 640);
  margin = mySize / 100;
  size(1080, 1920, P3D);
  color_setup1 = colors0;
  color_setup2 = colors0;
  color_bg = color(32, 32, 32);
  background(color_bg);
  num = 50;
  //num = 2;
  radius = mySize * 0.75;
  for (int a = 0; a < TAU; a += 1) {
    sizes.add(random(0.1, 0.5));
  }
  t = 0;
}

void draw() {
  
  //add----
  String root = "D:\\09_music_create\\03_PICTURE\\99_Render\\";
  String fileName = "67_rotatingCircle-FF_" + "c_" + count + "_######.png";
  String outP = root + "67_rotatingCircle_ver1\\" + fileName;
    //----
   
  //add----
  print(count + ";");
  if (count >= 1800) noLoop();
  //----
  
  randomSeed(seed);
  //background(color_bg);
  translate(width/2, height/2);

  for (int i = 0; i < num; i++) {
    float a = (TAU / num) * i;
    float x = radius * sin(a + t) / random(5, 2) / 2.0;
    float y = radius * cos(a + t) / random(2, 5) / 2.0;
    v_planet.add(new PVector(x, y));
    
  }
  push();

  for (int q = 0; q < 5; q += 1) {
    for (int j = 0; j < 1; j++) {

      rotateX(t / 100 + q/100);
      rotateY(t / 100 + q/100);
      rotateZ(random(TAU) + t + q/100);
      // noFill();
      strokeWeight(2 * random(0.2, 0.6));
      fill(colors0[int(random(1,4))]);
      

      for (int i = 0; i < num; i += 2) {
        float d = random(radius / 2, radius / 3);
        float x_plus = 1.25 * random(-d, d) / 5;
        float y_plus = 1.25 * random(-d, d) / 5;
        float z_plus = 1.25 * random(-d, d) / 2;
        push();
        translate(v_planet.get(i).x, v_planet.get(i).y, z_plus);
        rotateX(sin(-t) / 20);
        rotateY(cos(5*t) / 20);
        rotateZ(sin(5 * t + q));
        stroke(color(255, 255, 255, 255));
        // stroke(random(colors7));
        rectMode(CENTER);
        square(x_plus, y_plus, random(z_plus));
        strokeWeight(1 * random(0.2, 0.6));
        line(x_plus, y_plus, random(-z_plus, 0) / 4, x_plus, y_plus, random(-0, z_plus) / 4);
        pop();
        
      }

      for (int i = 0; i < num; i += 20) {
        float d = (1.0 + sin(t)) * random(radius / 2, radius / 1);
        float x_plus = 0.75 * random(-d, d) / 1;
        float y_plus = 0.75 * random(-d, d) / 1;
        float z_plus = 0.75 * random(-d, d) / 1;
        noStroke();
        strokeWeight(random(0.1));
        stroke(random(colors0[int(random(1,4))]));
        fill(random(colors0[int(random(1,4))]));
        push();
        translate(v_planet.get(i).x + x_plus, v_planet.get(i).y + y_plus, z_plus);
        rotateX(t);
        rotateY(t);
        rotateZ(t);
        sphere(random(0.2, 0.4));
        pop();
        
      }
    }
  }
  pop();

  t += 2 * random(0.001, 0.005);
  if(frameCount == 2000){
    //noLoop();
  }
  saveFrame(outP);
  count++;
}

/*
void keyTyped() {
  if (key == "s" || key == "S") {
    //saveCanvas("0711_Emotional lines_11_2022", "png");
  }
}
*/