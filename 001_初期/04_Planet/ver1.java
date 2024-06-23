float i,m,n,p,s,t,q;

void setup(){
  size(1080,1920);
  noStroke();
}

void draw(){
  background(0);
  translate(-0.4 * 1080,-1920 / 10); //-192
  for(i = 2e3; i > 0; i--){
    p = i % 2 == 0 ? 0 : 1;
    m = t / cos(t / i) + p * (t / 2 + i % t);
    n = t / 9 + i * i;
    s = 3 - cos(n) * 3;
    q = p == 0 ? 1 : 0;
    rect(960 + m * sin(n) * cos(q * i / t), 540 + m * cos(n + p * 2), s, s);
  }
  t += 0.05;
  
  //フォルダは自動生成
  String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\20_Space\\20_Space-NN_######.png";
  saveFrame(outP);
  //----

}
