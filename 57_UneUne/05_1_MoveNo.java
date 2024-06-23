int f = 0;
float count = 1;

void setup() {
  size(1000, 1000, P3D);
  noStroke();
}

void draw() {
  
  //add----
  String root = "D:\\09_music_create\\03_PICTURE\\99_Render\\";
  String fileName = "57_Moon-FF_" + "c_" + count + "_######.png";
  String outP = root + "57_Moon\\" + "1\\" + fileName;
    //----
   
  //add----
  print(count + ";");
  if (count >= 1800) noLoop();
  //----
  
  print(count + ";");
  int n = 0;
  int m = 0;
  float i = TAU * count;
  background(0);
  rotateX(f);

  while (i > 0) {
    push();
    i -= PI / 256;
    float C = (f - i) * 2;
    float Q = abs(sin(C + f)) * 400;
    rotate(i);
    translate(90, 0, 0);
    rotateY(C);
    fill(Q, 10);
    n++;
    if (n % 4 == 0) {
      sphere(40);
      fill(20);
      sphere(35);
    }
    fill(Q, 20);
    m++;
    if (m % 99 == 0) sphere(1000); // Dynamic background
    pop();
  }

  saveFrame(outP);
  
  f += 0.01;
  count++;
  
}