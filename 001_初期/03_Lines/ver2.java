int w = 300;
int h = 300;
int[] ar = new int[6];
float count = 0;

void setup() {
  size(1080, 1920);
  background(0);
}

void draw() {
  count+= 0.001;
  if (count > 0.01){
    //noLoop();
  }
  noFill();
  translate(width/2, height/2);
  rotate(count);
  for(int i=0; i<100; i++){
    
    for(int j = 0; j < 5; j++){
      ar[j] = int(random(1, 200));
    }
    stroke(color(ar[0],ar[1],ar[2], ar[0]/10));
    //stroke(255);
    bezier(
    ar[0], ar[0], 
    ar[1] * ar[1], 
    ar[2] * ar[1], 
    ar[3], 
    ar[4], 
    ar[4], ar[5]);
    
    rotate(PIE/2);
    
  }
  //add----
  String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\02\\Penrose-30_######.png";
  saveFrame(outP);
  //----

}
