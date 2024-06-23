 // by SamuelYAN
 // more works //
 // https://twitter.com/SamuelAnn0924
 // https://www.instagram.com/samuel_yan_1990/

 int ito_num, point_num;
 float seed = random(1) * 99999;
 int mySize, margin;
 int tile_x, tile_y;
 int tile_x_size, tile_y_size;
 int count=1;

 // colors
 //int colors_dark = "#321E1E-#27374D-#116D6E-#5F264A-#2C3639-#191A19".split("-");
 //int colors_light = "#EEE3CB-#F9F5F6-#FBFFDC-#F8E8EE-#E3F4F4-#C1D0B5-#FEA1A1-#FFD966-#FADA9D".split("-");

 int colors0, colors1;
 //int colors2 = "#f4c815-#f9cad7-#A57283-#c1d5de-#deede6-#f7f6cf-#b6d8f2-#f4cfdf-#9ac8eb-#ccd4bf-#e7cba9-#eebab2-#f5f3f7-#f5e2e4-#AAD9CD-#E8D595-#E9BBB5-#E7CBA9-#8DA47E".split("-");
 int color1, color2;
 int plus;

 void setup() {
   randomSeed(1);
   mySize = 640;
  margin = mySize / 100;
  size(1080, 1920);
   // if (int(seed) % 2 == 0) {
   //   colors0 = "281914-1a1a1a-202020-242e30".split("-").map((a) => "#" + a);
   //   colors1 = "fef9fb-fafdff-ffffff-fcfbf4-f9f8f6".split("-").map((a) => "#" + a);
   // }
   // if (int(seed) % 2 == 1) {
     //colors1 = "#281914-#1a1a1a-#202020-#242e30".split("-");
     //colors0 = "#fef9fb-#fafdff-#ffffff-#fcfbf4-#f9f8f6";
   // }

   ito_num = int(random(4, 1));
   //color1 = random(colors1);
   //color2 = random(colors0);
   plus = 0;
   //makeFilter();
   // pixelDensity(4);
   point_num = int(random(14, 8));
   background(random(colors0));
 }

 void draw() {
   
   //add----
  String root = "D:\\09_music_create\\03_PICTURE\\99_Render\\";
  String fileName = "69_Close-FF_" + "c_" + count + "_######.png";
  String outP = root + "69_Unyu_ver2\\" + fileName;
    //----
   
  //add----
  print(count + ";");
  if (count >= 1800) noLoop();
  //----
  
   randomSeed(1);
   noiseSeed(1);

   push();
   translate(width / 2+ plus / 30, height / 2);
   rotate(random(0, 6)); //[0, PI / 2, PI, PI / 2 * 3]
   rotate(random(TAU));
   for (int i = 0; i < ito_num; i++) {
     randomSeed(1 * i);
     push();
     // translate(random(-mySize / 2, mySize / 2), random(-mySize / 2, mySize / 2));
     translate(random(1, 50) * plus / point_num, random(-mySize / 4, mySize / 4));
     // rotate(random([-1, 1]) * plus / random(2, 4) / point_num);
     rotate(-random(TAU) + random(-1, 1) * 0.5 * 1.5 * (plus + count) / point_num); //random([-1, 1])
     noFill();
     stroke(20, 100, sin(count)*100, sin(count)*100);
     //stroke(str(random(colors1)) + "bf");
     strokeWeight(random(0.5, 1.1) + plus / 30);
     //drawingContext.shadowColor = random(colors0);
     //drawingContext.shadowOffsetX = 1.5;
     //drawingContext.shadowOffsetY = 1.5;
     //drawingContext.shadowBlur = 0;
     beginShape();
     for (int x = -width * 2; x < width * 2; x += width / point_num) {
       float n = noise(x * (0.1), i * 0.1, frameCount * 0.02);
       float y = map(n, 0, 1, -mySize * 0.05, mySize * 0.15);
       curveVertex(x + 1.0 * i * sin(random(0.1, 1) * plus + 0.1 * x * sin(random(1, 2) * plus - 1.5) + 1.5), y - plus);
       // point(x + 1.0 * i * sin(random(0.1, 1) * plus + 0.1 * x * sin(random(1, 2) * plus - 1.5) + 1.5), y - plus);
       // circle(x,y, width/point_num);
     }
     endShape();
     pop();
   }

   pop();

   plus += random(2, 1) * random(0.01, 0.05);
   if (frameCount % 50 == 0) {
     point_num++;
   }
   // if (frameCount < 100) {
   // image(overAllTexture, 0, 0);
   // }
   // if (frameCount > 799) {
   if (frameCount > random(399, 1299)) { //random([399, 599, 1299])
     noLoop();
     blendMode(BLEND);
     //image(overAllTexture, 0, 0);
     blendMode(SCREEN);
     strokeWeight(random(0.10, 0.5) / 2);
     stroke(200);
     //stroke(str(random(colors1)) + "0d");
     noFill();
     //drawingContext.setLineDash([1, 4, 1, 3]);
     //drawOverPattern();
     //drawingContext.setLineDash([]);
     blendMode(BLEND);

     noFill();
     strokeWeight(margin);
     rectMode(CORNER);
     //stroke("#202020");
     stroke(100, sin(count));
     rect(0, 0, width, height);
   }
   count++;
   saveFrame(outP);
 }
