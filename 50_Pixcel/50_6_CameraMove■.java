/**
 * Extrusion. 
 */

 PImage img;
 boolean onetime = true;
 int[][] aPixels;
 int[][] values;
 float angle;
 float size = 2;
 float count = 1; //floatやないとダメ見たい
 
 float eyeX;
 float eyeY;
 float eyeZ;
 float centerX;
 float centerY;
 float centerZ;
 
 String pPath = "D:\\09_music_create\\03_PICTURE\\demo.png";
 
 void setup() {
   size(1920, 1080, P3D);
   
  eyeX = width/2.0;
  eyeY = height/2.0;
  eyeZ = (height/2.0) / tan(PI*30.0 / 180.0);
  centerX = width/2.0;
  centerY = height/2.0;
  centerZ = 0;
   
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
       values[j][i] = int(brightness(aPixels[j][i]));
     }
   }
 }
 
 void draw() {
   if (count > 40.0) eyeX += log(1000 * 0.1 * count);
   //eyeY += 1000 * 0.1 * count;
   //eyeZ = width * cos(radians(count*10));
   //centerX +=100;
   //centerY +=60;
   //centerZ +=60;
   camera(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, 0, 1, 0);
 
    //ファイル名
    String root = "D:\\09_music_create\\03_PICTURE\\99_Render\\";
    String fileName = "50_Pixcel-30_" + "c_" + count + "_######.png";
    String outP = root + "50_Pixcel_ver6_2\\" + fileName;
    
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
   //angle += 0.001;
   //rotateY(angle);  
   
   // Display the image mass
   for (int i = 0; i < img.height; i += 6) {
     for (int j = 0; j < img.width; j += 6) { //ren: 6 //ここらセットでコメント変更する事にしよう
       
       float sizeR = 1 * log(count + 1); //ln100=4.6
       
       push(); //これを入れないと点が一つしかでてこない★ 加えて回転しなくなったな★
       
       stroke(values[j][i], 255); // * sin(count/10*(i*j)));
       //X,Y,Z
       //float wid = -values[j][i]; // - random(1);
       //float wid2 = -values[j][i]-10; // * sin(count/1000)*random(1);
       float zz = lerp(0, (values[j][i]*10), 0.01 * count); //ren: 0.005
       translate(j-img.width/2, i-img.height/2, zz); //平面に近い　exp(-values[j][i])
       sphere(sizeR);
       pop();
     }
   }
   
   if (count > 70.0) noLoop();
   
   //drawCoorinats();
   
   //add----
     saveFrame(outP);
     //----
     
     count += 1;
 }
 
 void drawCoorinats() {
   strokeWeight(1);
   stroke(255, 0, 0);
   line(0, 0, 0, 100, 0, 0);
 
   stroke(0, 255, 0);
   line(0, 0, 0, 0, 100, 0);
   
   stroke(0, 0, 255);
   line(0, 0, 0, 0, 0, 100);
 }