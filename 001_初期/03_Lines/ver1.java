int w = 300;
int h = 300;
int[] ar = new int[4];

void setup() {
  size(640, 360);
  background(0);
}

void draw() {
  //noLoop();
  noFill();
  translate(width/2, height/2);
  for(int i=0; i<6; i++){
    
    for(int j = 0; j < 4; j++){
      ar[j] = int(random(255));
    }
    stroke(color(ar[0],ar[1],ar[2], 30));
    //stroke(255);
    bezier(
    0, 0, 
    ar[0] * width, 
    ar[1] * height, 
    ar[2] * width, 
    ar[3] * height, 
    0, 0);
    
    rotate(PIE/2);
    
  }
}
