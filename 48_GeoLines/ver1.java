int u, v, count;
void setup() {
  size(1080,1080);
  background(0);
  strokeWeight(0.75);
  stroke(255, 10);
  u = width/2;
  v = height/2;
  count = 0;
}
void draw() {
  if (count > 20){
    noLoop();
  }
  for(float i = 0; i < 100 * PI; i += PI / 24){
    float px = mouseX * cos(i) - mouseY * sin(i) - u * cos(i) + v * sin(i) + u;
    float py = mouseX * sin(i) + mouseY * cos(i) - u * sin(i) - v * cos(i) + v;
    float x = 300 * cos(i) - 300 * sin(i) - u * cos(i) + v * sin(i) + u;
    float y = 300 * sin(i) + 300 * cos(i) - u * sin(i) - v * cos(i) + v;
    line(px, py, x, y);
    //print(px);
  }
  count += 1;
  print(count);
  
  //フォルダは自動生成
  String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\48_GeoLines\\" + "1" + "\\48_GeoLines-NN_######.png";
  saveFrame(outP);
  //----

}
