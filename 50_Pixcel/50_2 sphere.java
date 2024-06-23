/**
 * Extrusion. 
 */

PImage img;
boolean onetime = true;
int[][] aPixels;
int[][] values;
float angle;
float size = 10;
float count = 1; //floatやないとダメ見たい

String pPath = "D:\\09_music_create\\03_PICTURE\\demo.jpeg";

void setup() {
  size(1080, 1080, P3D);
  
  aPixels = new int[width][height];
  values = new int[width][height];
  noFill();

  // Load the image into a new array
  // Extract the values and store in an array
  img = loadImage(pPath);
  img.loadPixels();
  
  //高さ
  for (int i = 0; i < img.height; i++) {
    
    //幅
    for (int j = 0; j < img.width; j++) {
      aPixels[j][i] = img.pixels[i*img.width + j];
      
      //この値で書いてるね
      values[j][i] = int(blue(aPixels[j][i]));
    }
  }
}

void draw() {
  
   //ファイル名
   String root = "D:\\09_music_create\\03_PICTURE\\99_Render\\";
   String fileName = "50_Pixcel-30_" + "c_" + count + "_######.png";
   String outP = root + "50_Pixcel\\" + "2\\" + fileName;
   
  //add----
  print(count + ";");
  
  //background(0.0, 0.0, 90.0, 100.0);
  //if (img != null)image(img, 0, 0); //前のイメージを残す
  //if (count % 6 == 0) background(0.0, 0.0, 90.0, 100.0);
  
  if (count >= 1800) noLoop();
  //----
  
  background(0);
  translate(width/2, height/2, -height/2);
  scale(size);
  
  //視野の変更
  angle += 0.001;
  rotateY(angle);  
  
  // Display the image mass
  for (int i = 0; i < img.height; i += 4) {
    for (int j = 0; j < img.width; j += 4) {
      
      float sizeR = 10*sin(count/1000);
      
      push(); //これを入れないと点が一つしかでてこない★ 加えて回転しなくなったな★
      
      stroke(values[j][i], 255*sin(count/10*(i*j)));
      //X,Y,Z
      //float wid = -values[j][i]; // - random(1);
      //float wid2 = -values[j][i]-10; // * sin(count/1000)*random(1);
      translate(j-img.width/2, i-img.height/2, -values[j][i]);
      sphere(sizeR);
      pop();
    }
  }
  
  //add----
    saveFrame(outP);
    //----
    
    count += 1;
}
