float[] prev_steps = new float[800];
float[] current_steps = new float[802];
float[] results = new float[800];

float c = 0.9;

void setup() {
  size(800, 800);
  current_steps[0] = 0;
  for(int i = 0; i<800; i++){
    int y = initForm(i);
    prev_steps[i] = y;
    current_steps[i + 1] = y;
  }

  current_steps[401] = 0;
}

void draw() {
  stroke(255);
  background(0);
  for(int i=0; i<800; i++){
    results[i] = c * (current_steps[i] + current_steps[i + 2] - 2 * current_steps[i + 1]) + 2 * current_steps[i + 1] - prev_steps[i];
    if(i > 0){
      line(i - 1, results[i -1] + 200, i, results[i] + 200);
    }
  }
  for(int i = 0; i < 800; i++){
    prev_steps[i] = current_steps[i + 1];
    current_steps[i + 1] = results[i];
  }
  //add----
  String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\05\\Penrose-30_######.png";
  saveFrame(outP);
  //----
}

int initForm(int x){
  int radias = 360 * x / width;
  return parseInt(pow(sin(radians(radias)), 10) * 200);
}
