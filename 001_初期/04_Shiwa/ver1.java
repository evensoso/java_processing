/*
Modified from:

Daniel Shiffman
Coding Challenge #24: Perlin Noise Flow  Field
https://youtu.be/BjoM9oKOAKY
https://github.com/CodingTrain/website/tree/master/CodingChallenges/CC_024_PerlinNoiseFlowField
*/


FlowField flowfield;
ArrayList<Particle> particles;


void setup() {
  size(1080, 1920);
  
  flowfield = new FlowField(10); // 引数からflowfieldを構成するベクターの数が算出・定義される
  flowfield.update();

  particles = new ArrayList<Particle>();
  for (int i = 0; i < 10000; i++) { // 生成するParticlesの数を指定
    PVector start = new PVector(random(width), random(height));
    particles.add(new Particle(start, random(2, 8)));
  }
  background(255);
}


void draw() {
  flowfield.update();
  
  for (Particle p : particles) {
    p.follow(flowfield);
    p.run();
  }
  
//フォルダは自動生成
  String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\11_Kyu\\11_Kyu-NN_######.png";
  saveFrame(outP);
  //----

}


void keyPressed(){
    /*
    ENTERキー押下: 画像を保存する
    BACKSPACEキー押下: リセット
    */
    
    if (keyCode == ENTER){
        saveFrame("generative_10_####.png");
    }
    if (keyCode == BACKSPACE){
        setup();
    }
}


public class FlowField {
  /*
  画面に配置されるベクターの集合体
  Particlesの動きに力を加える
  */
  
  PVector[] vectors;
  int cols, rows;
  float inc = 0.1;
  float zoff = 0;
  int scl;
  
  FlowField(int res) {
    scl = res;
    cols = floor(width / res) + 1;
    rows = floor(height / res) + 1;
    vectors = new PVector[cols * rows];
  }

  void update() {
    // flowfield状態を定義
    // フレームごとに呼び出される

    float xoff = 0;
    for (int y = 0; y < rows; y++) { 
      float yoff = 0;
      for (int x = 0; x < cols; x++) {
        float angle = noise(xoff, yoff, zoff) * radians(180); // ここで各ベクターの角度を定義
        
        PVector v = PVector.fromAngle(angle); // Flowfieldを構成するベクター
        v.setMag(1); // 各ベクターの大きさを定義
        int index = x + y * cols;
        vectors[index] = v;
       
        xoff += inc;
      }
      yoff += inc;
    }
    zoff += 0.004;
  }
}


public class Particle {
  /*
  ひとつの粒子
  動きはflowfieldのベクターから影響を受ける
  */

  PVector pos;
  PVector vel;
  PVector acc;
  PVector previousPos;
  float maxSpeed;
   
  Particle(PVector start, float maxspeed) {
    maxSpeed = maxspeed;
    pos = start;
    vel = new PVector(0, 0);
    acc = new PVector(0, 0);
    previousPos = pos.copy();
  }

  void run() {
    update();
    edges();
    show();
  }

  void update() {
    pos.add(vel);
    vel.limit(maxSpeed);
    vel.add(acc);
    acc.mult(0);
  }

  void applyForce(PVector force) {
    acc.add(force); 
  }

  void show() {
    // 描画処理

    stroke(10, 2); // ここで線の色調整
    strokeWeight(1); // ここで線の太さ調整
    line(pos.x, pos.y, previousPos.x, previousPos.y);
    updatePreviousPos();
  }

  void edges() {
    // 画面端の処理

    if (pos.x > width) {
      pos.x = 0;
      updatePreviousPos();
    }
    if (pos.x < 0) {
      pos.x = width;    
      updatePreviousPos();
    }
    if (pos.y > height) {
      pos.y = 0;
      updatePreviousPos();
    }
    if (pos.y < 0) {
      pos.y = height;
      updatePreviousPos();
    }
  }

  void updatePreviousPos() {
    this.previousPos.x = pos.x;
    this.previousPos.y = pos.y;
  }

  void follow(FlowField flowfield) {
    // Flowfieldから受ける力を適用

    int x = floor(pos.x / flowfield.scl);
    int y = floor(pos.y / flowfield.scl);
    int index = x + y * flowfield.cols;
    
    PVector force = flowfield.vectors[index];
    applyForce(force);
  }
}
