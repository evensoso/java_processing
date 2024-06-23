int u, v, count;
void setup() {
  size(1080,1080);
  background(0);
  u = width/2;
  v = height/2;
  count = 0;
}
void draw() {

  strokeWeight(0.25);
  stroke(255, 10);
  
  if (count > 40){
    noLoop();
  }
  for(float i = 0; i < 100 * PI; i += PI / 24){
    float px = mouseX * cos(i) - mouseX * sin(i) - u * cos(i) + v * sin(i) + u;
    float py = mouseY * sin(i) + mouseY * cos(i) - u * sin(i) - v * cos(i) + v;
    float x = 300 * cos(i) - 300 * sin(i) - u * cos(i) + v * sin(i) + u;
    float y = 300 * sin(i) + 300 * cos(i) - u * sin(i) - v * cos(i) + v;
    line(px, py, x, y);
    //print(px);
  }
  count += 1;
  print(count);
  
  //フォルダは自動生成
  String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\49_GeoLines2\\" + "1" + "\\49_GeoLines2-NN_######.png";
  saveFrame(outP);
  //----

}
