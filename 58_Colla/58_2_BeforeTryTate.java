//For the WccChallange on theme of "Digital to Physical/ Physcial to Digital".
//I thought about going the 'bits to atoms' route, since I have a plotter and 3D printer and have done many
//projects like that w my students.
//But I really wanted to explore more generative photomontage after using that idea in the Dada challenge.
//The photos are all from mine, mostly taken w my phone on forest hikes.

ArrayList<PImage> images = new ArrayList<PImage>();
int rs;
int simplex;
int count = 1;

void setup() {
  
  size(1080, 1920);
  //simplex= new OpenSimplexNoise(Date.now());
  background(255);
  imageMode(CENTER);
  
  //hard_light is a pretty good alternative take, uncomment to try it
  //blendMode(HARD_LIGHT)

  String root = "D:\\09_music_create\\03_PICTURE\\99_Render\\000_Asset\\4\\";
  PImage cosmo= loadImage(root + "demo1.png");
  PImage dtree= loadImage(root + "demo2.png");
  PImage branch= loadImage(root + "demo3.png");
  PImage ginko= loadImage(root + "demo4.png");
  PImage handup= loadImage(root + "demo5.png");
  PImage inhand= loadImage(root + "demo6.png");
  PImage jelly= loadImage(root + "demo7.png");
  PImage opentree= loadImage(root + "demo8.png");
  PImage shroom= loadImage(root + "demo9.png");
  PImage yshroom= loadImage(root + "demo10.png");
  PImage leaf= loadImage(root + "demo11.png");
  PImage i12= loadImage(root + "demo12.png");
  PImage i13= loadImage(root + "demo13.png");
  PImage i14= loadImage(root + "demo14.png");
  PImage i15= loadImage(root + "demo15.png");
  PImage i16= loadImage(root + "demo16.png");
  PImage i17= loadImage(root + "demo17.png");
  PImage i18= loadImage(root + "demo18.png");
  
  PImage[] listS = {
    cosmo, dtree, branch, branch, ginko, 
    handup, inhand, jelly, opentree, shroom, 
    yshroom, leaf, i12, i13, i14, 
    i15, i16, i17, i18
  };
  
  for (PImage pI: listS){
    images.add(pI);
  }

  //images.shuffle();
  
}

void draw() {
  
  background(255);
  
  //add----
  String root = "D:\\09_music_create\\03_PICTURE\\99_Render\\";
  String fileName = "58_Moon-FF_" + "c_" + count + "_######.png";
  String outP = root + "58_Moon\\" + "4\\" + fileName;
    //----
   
  //add----
  print(count + ";");
  if (count >= 1800) noLoop();
  //----
  
  float noOfImages= (400); // *1080)/800;
  for(int i=0; i<noOfImages; i++){
    //print(i + ";");
    float x= random(width);
    float y= random(height);
  int chooser = floor(map(noise(x/400, y/500), 0, 1, 0, images.size()));
  PImage p= images.get(chooser);
  //p.filter(INVERT);
  
  push()  ;
  translate(x, y);
  //float r = map(simplex.noise2D(x/400, y/500), -1, 1, 0, TAU);
  float r = map(noise(x/400, y/500), -1, 1, 0, TAU);
  rotate(r);
  fill(0);
  float sizer= map(dist(width/2, height/2, x, y), 0, height, 0.1, 1);
  if(p.width*sizer < 200){
    //print("INVERT");
    //p.filter(INVERT);
    image(p, 0, 0, p.width*sizer, p.height*sizer);
  }
  else image(p, 0, 0, p.width*sizer, p.height*sizer);
  pop();
  
  }
//noLoop();
print("done.");
saveFrame(outP);
count++;
}