float _noiseScale;


void setup(){
    _noiseScale = 0.005;

    size(1080, 1920);
    background(255);
}


void draw(){
    for (int i = 0; i < 10000; i++){
        float x = random(width);
        float y = random(height);
        float noiseFactor = noise(x*_noiseScale, y*_noiseScale); // 2次元noise
        float lineLength = noiseFactor * 40; // ここで線の長さを調整
        
        pushMatrix();
            translate(x, y);
            rotate(noiseFactor * radians(180)); // ここで線の流れ具合を調整
            stroke(0, 2);
            strokeWeight(1);
            line(0, 0, lineLength, lineLength);
        popMatrix();
    }
    
  //フォルダは自動生成
  String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\16_Mandala\\16_Mandala-NN_######.png";
  saveFrame(outP);
  //----

}


void keyPressed(){
    /*
    ENTERキー押下: 画像を保存する
    BACKSPACEキー押下: リセット
    */
    
    if (keyCode == ENTER){
        saveFrame("generative_9_####.png");
    }
    if (keyCode == BACKSPACE){
        setup();
    }
}
