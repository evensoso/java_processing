ArrayList<Star> constellation = new ArrayList<Star>();
int n;
int count = 1;
float d;

void setup() {
  size(640, 640);
  pixelDensity(1);
  //pixelDensity(displayDensity());
  n = 600;

  for (int i = 0; i <= n; i++) {
    constellation.add(new Star());
  }
  strokeWeight(0.4);
  stroke(255, 40);
}
          
void draw() {

  String root = "C:\\Fujitsu\\Taihi\\00_Proc\\000_Save\\";
  String folName = "02-NNN";
  String pPath = root + folName + "\\1\\" + folName + "-######.png";
  print(count + ";");

  if (count >= 1800) { noLoop(); exit();}
  
  background(0);

  for (int i = 0; i < constellation.size(); i++) {
    Star star1 = constellation.get(i);
    star1.update();
    for (int j = 0; j < constellation.size(); j++) {
      if (i > j) {
        Star star2 = constellation.get(j);
        d = star1.loc.dist(star2.loc);
        if (d <= width / 8) { // if d is less than width/10 px, we draw a line between the two stars
          line(star1.loc.x, star1.loc.y, star2.loc.x, star2.loc.y);
        }
      }
    }
  }

  count++;
  
  saveFrame(pPath);
}

class Star {
  PVector loc;
  PVector speed;
  PVector bam;
  
  float a = random(5 * TAU);
  float r = random(width * 0.2, width * 0.1);
  float m;
  
  Star(){
    loc = new PVector(width / 2 + sin(a) * r, height / 2 + cos(a) * r, 3);
    speed = new PVector(2, 4, 1);
    bam= new PVector(10, 0, 0); //yは下方向かな
    //speed = p5.Vector.random2D();
  }
  
  void update() {
      //bam = p5.Vector.random2D(); // movement of star will be a bit erractic
      bam = bam.mult(random(10));
      bam = bam.mult(0.1);
      speed.add(bam);
      
      m = constrain(map(dist(loc.x, loc.y, random(width), random(height)), 0, width, 8, 0.05), 0.05, 8);
      speed.normalize().mult(m);

      // No colision detection
      if (dist(loc.x, loc.y, width / 2, height / 2) > (width / 2) * 0.98) {
        
        if (loc.x < width / 2) {
          loc.x = width - loc.x - 4; // "-4" => avoid blinking stuff
        } else if (loc.x > width / 2) {
          loc.x = width - loc.x + 4; // "+4"  => avoid blinking stuff
        }
        
        if (loc.y < height / 2) {
          loc.y = width - loc.y - 4;
        } else if (loc.x > height / 2) {
          loc.y = width - loc.y + 4;
        }
      }
      
      loc = loc.add(speed);
    }
}