int _num = 50; // １クリックで生成するCircleの数
Circle[] _circleArr = {};


void setup(){
    size(1080,1920);
    background(255);
    smooth();
    strokeWeight(1);

    addCircles(); // はじめにCircleを生成
}


void draw(){
    // 配列に格納されたCircleすべてをupdate

    for (int i=0; i<_circleArr.length; i++){
        Circle thisCircle = _circleArr[i];
        thisCircle.updateMe();
    }
    
  //フォルダは自動生成
  String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\10_sq\\10_sq-NN_######.png";
  saveFrame(outP);
  //----
}


void keyPressed(){
    /*
    ENTERキー押下: 画像を保存する
    BACKSPACEキー押下: setup()を呼ぶ
    */
    
    if (keyCode == ENTER){
        saveFrame("generative_5_####.png");
    }
    if (keyCode == BACKSPACE){
        setup();
    }
}


void mouseReleased(){
    // マウスクリックでCircleを追加生成
    addCircles();
}


void addCircles() {
    // Circleを生成、配列に加える

    for (int i=0; i< _num; i++){
        Circle thisCircle = new Circle();
        _circleArr = (Circle[])append(_circleArr, thisCircle);
    }
}


class Circle{
    /*
    このCircle自身は描画されない
    他のCircleとの重複部分に円(または四角形)を描画する
    */

    float x, y;
    float xMove, yMove;
    float radius;
    float alpha;
  
    Circle(){
        x = random(width);
        y = random(height);

        xMove = random(10) - 5;
        yMove = random(10) - 5;

        radius = random(500) + 50;
    }


    void updateMe(){
        x += xMove;
        y += yMove;

        // 画面外に出たときは反対側に移動
        if (x > (width + radius)){x = 0 - radius;}
        if (x < (0 - radius)){x = width + radius;}
        if (y > (height + radius)){y = 0 - radius;}
        if (y < (0 - radius)){y = height + radius;}

        // 他の円と接触しているとき、その接触範囲に円(または四角)を描画する
        for (int i=0; i<_circleArr.length; i++){
            Circle otherCircle = _circleArr[i];

            if (otherCircle != this){
                float distance = dist(x, y, otherCircle.x, otherCircle.y);
                float overLap = distance - radius - otherCircle.radius;

                if ((overLap < 0)){ // 他の円と接触している場合
                    float midX, midY;
                    midX = (x + otherCircle.x) / 2;
                    midY = (y + otherCircle.y) / 2;

                    // 円または四角の描画
                    alpha = random(3);
                    stroke(0, alpha);
                    // stroke(random(255), random(255), random(255), alpha); // 微妙に色をつける
                    noFill();
                    overLap *= -1; // overLapを正の値に
                    rect(midX, midY, overLap, overLap); // 重複分の幅を持った四角を描画
                    // ellipse(midX, midY, overLap, overLap); // 重複分の幅を持った円を描画
                }
            }
        }
    }
}
