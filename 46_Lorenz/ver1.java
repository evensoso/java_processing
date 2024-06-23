
float x = 0.1, y = 0.1;

//float a =  0.45, b = 1.9;
//static final float a =  1.0, b = 0.85;
//static final float a =  1.0, b = 0.9;
float a =  1.25, b = 0.75;
int size = 160;

//add----
 float count = 1.0;
   PImage img;

void setup() {
  size(1080, 1080);
  blendMode(ADD);
  background(0);
  stroke(124, 155, 255, 40);//200);
}

void draw() {
  //rotate(PIE);
  
  /*if (random(100)<2) {
    background(0);
    a =  1.0;
    b = 0.9;
    
  }*/
  
   //ファイル名
   String root = "D:\\09_music_create\\03_PICTURE\\99_Render\\";
   String fileName = "46_Lorenz-30_" + "c_" + count + "_######.png";
   String outP = root + "46_Lorenz\\" + "1\\" + fileName;
   
    //add----
  print(count);
  //background(0.0, 0.0, 90.0, 100.0);
  //if (img != null)image(img, 0, 0); //前のイメージを残す
  //if (count % 6 == 0) background(0.0, 0.0, 90.0, 100.0);
  
  if (count >= 1800) noLoop();
  //----
  
  float _x, _y;

  for (int i = 0; i < 100; i++) {

    _x = ((1 + a * b) * x - b * x * y); //* sin(count) * 
    _y = (1 - b) * y + b * x * x;

    point(_x * size + width * 0.5, - _y * size + height * 0.75);

    x = _x;
    y = _y;
  }
  //rotate(PIE/12);
  
  //add----
    saveFrame(outP);
    //----
    
    count += 1;
    //a += 0.001;
    //b -= 0.02;
}