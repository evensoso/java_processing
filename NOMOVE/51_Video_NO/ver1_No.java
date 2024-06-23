import processing.video.*; //ライブラリをインポート

Movie myMovie; //Movie変数「myMovie」を宣言

String mPath = "D:\\01_NotePC\\06_Video_Rendered\\0923_1_0000-0428.mp4";
PImage img;

int[][] aPixels;
int[][] values;

float count = 1;

void setup() {
  size(1080, 1920, P3D);
  
  aPixels = new int[width][height];
  values = new int[width][height];
  
 myMovie = new Movie(this, mPath); //動画を読み込みMovie変数「myMovie」に格納
 //myMovie.loop(); //動画を繰り返し再生
 myMovie.play();
}

void draw() {
  
   //ファイル名
   String root = "D:\\09_music_create\\03_PICTURE\\99_Render\\";
   String fileName = "51_Movie-30_" + "c_" + count + "_######.png";
   String outP = root + "51_Movie\\" + "1\\" + fileName;
   
  //if (myMovie.available()) {
    //myMovie.read(); //映像を読み取る
    
  //}

img = myMovie;
  
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
  
  // Display the image mass
  for (int i = 0; i < img.height; i += 4) {
    for (int j = 0; j < img.width; j += 4) {
      
      stroke(values[j][i], 255);
      
      //X,Y,Z
      float wid = -values[j][i]; // - random(1);
      float wid2 = -values[j][i]-10; // * sin(count/1000)*random(1);
    line(j-img.width/2, i-img.height/2, wid, j-img.width/2, i-img.height/2, wid2);
    }
  }
  
  image(img, 0, 0, 1080, 1920); //image関数で映像を表示
  
  saveFrame(outP);
  
  count += 1;
  
}

// Called every time a new frame is available to read
void movieEvent(Movie m) {
  m.read();
}