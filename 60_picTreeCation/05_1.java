/*

Based on...

RANDOM GROWTH
Jeff Thompson | 2019/20 | jeffreythompson.org
https://editor.p5js.org/jeffThompson/sketches/XjwLIdEiU


Mycelium growth simulation - opacity based on a source image

*/

int maxAge = 90; // tendrils older than this will pruned

PImage img;
ArrayList<Fungus> fungi = new ArrayList<Fungus>();
int count = 1;

void setup() {
  size(1920, 1920, P3D);
  background(0, 0, 0);
  img = loadImage("D:\\09_music_create\\03_PICTURE\\99_Render\\000_Asset\\2\\demo1.png");
}


void draw() {

    //add----
    String root = "D:\\09_music_create\\03_PICTURE\\99_Render\\";
    String fileName = "60_Wave-FF_" + "c_" + count + "_######.png";
    String outP = root + "60_Wave_ver2\\" + fileName;
    //----
     
    //add----
    print(count + ";");
    if (count >= 1800) noLoop();
    //----

    //camera(width/2.0, height/2.0, ((height/2.0) / tan(PI*30.0 / 180.0)), width/2.0, height/2.0, 0, 0, 1, 0);

    for (int i = fungi.size() - 1; i >= 0; i -= 1) {

      // get the current object
      Fungus f = fungi.get(i);

      // update and, if it has reached a
      // certain radius or is too old, remove it
      f.update();
      if (f.distFromCenter >= height / 2.5 || f.age > maxAge) {
        //fungi = splice(fungi, i, 1); //寿命超えたやつを消すか
      }
      // draw it onscreen
      f.display();
    }

    if (fungi.size() <5) {
      Fungus f = new Fungus(randomGaussian(), randomGaussian(), 0);
      fungi.add(f);
    }
    
 count++;
 saveFrame(outP);
}

class Fungus {
  float angleChangeAmt = radians(2);
  float speed;
  float distFromCenter;
  float x, y, prevX, prevY;
  float angle;
  float age = 0;
  int chanceSplit = 2;
  float splitAngle = radians(20);
  
  Fungus(float xx, float yy, float gAngle) {

    // values common to all Fungus objects
    angleChangeAmt = radians(2);
    speed = 1;

    // passed in from the arguments
    prevX = x;
    prevY = y;
    x = xx;
    y = yy;

    // if the angle variable is passed in, use that
    // otherwise, set to a random angle
    //angle = angle || random(0, TWO_PI);
    angle = gAngle; //random(0, TWO_PI);

  }


  void update() {
    // increase age of this tendril
    age += 1;

    // calculate the distance from the center of the sketch
    distFromCenter = dist(width / 2, height / 2, x, y);

    // move in a random direction
    angle += random(-angleChangeAmt, angleChangeAmt);
    prevX = x;
    prevY = y;
    x += cos(angle) * speed;
    y += sin(angle) * speed;

    // randomly split into two Fungus objects
    // new one will split off in one direction,
    // current goes in the opposite direction

    if (fungi.size() < 1000) {  // keep it from slowing down too much,,,
      if (random(100) < chanceSplit) {
        Fungus f = new Fungus(x, y, angle + splitAngle);
        fungi.add(f);
        angle -= splitAngle;
      }
    }
  }


  void display() {
    strokeWeight(random(0.5,2));
    // base the opacity on the image, colour on the age
    int xPix = int(map(x, 0, width, 0, img.width));
    int yPix = int(map(y, 0, height, 0, img.height));
    float val1 = red(img.get(xPix, yPix ))/255;
    float val2 = green(img.get(xPix, yPix ))/255;
    float val3 = blue(img.get(xPix, yPix ))/255;
    age = age/maxAge;
    
    stroke(color(lerp(200,255,age),lerp(245,100,age),lerp(200,235,age),lerp(100,8,val1)));
    line(prevX, prevY, x, y);
    
    /*
    stroke(color(lerp(100,255,age),lerp(245,100,age),lerp(200,235,age),lerp(100,8,val2)));
    line(prevX, prevY, x-1, y);
    stroke(color(lerp(40,255,age),lerp(245,100,age),lerp(100,235,age),lerp(100,8,val3)));
    line(prevX, prevY, x, y-1);
    */
  }
}

// this void fires after the mouse has been
// clicked anywhere
void mouseClicked() {
  setup();
}