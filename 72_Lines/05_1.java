ArrayList<Block> blocks = new ArrayList<Block>();  
//int paintte;
int count;

void setup() {
  size(800, 800);
  //paintte = shuffle(random(colorScheme).colors.concat());
  int num = 30;
  float offset = width / 10;
  int margin = 0;
  float d = (width - offset * 2 - margin * (num - 1)) / num;
  
  for (int j = 0; j < num; j++) {
    for (int i = 0; i < num; i++) {
      float x = offset + i * (d + margin) + d / 2;
      float y = offset + j * (d + margin) + d / 2;
      blocks.add(new Block(x, y, d));
    }
  }
}

void draw() {
  print(count + ";");
  background(240);
  //randomSeed(230914);
  for (Block block: blocks) {
    block.update();
    block.display();
  }
  // if(frameCount%100==0){
  //   save();
  // }
  count++;
}

class Block {
  PVector center;
  int stroke_color;
  
  float size;
  float t, ld, step;
  Boolean direction_horizontal, direction_vertical;
  
  Block(float x, float y, float d) {
    this.center = new PVector(x, y);
    this.size = d;
    this.direction_horizontal = random(1) > 0.5;
    this.direction_vertical = random(1) > 0.5;
    this.t = (x + y * width) / (width * height);
    this.ld = sqrt(2) * d * 2;
    this.step = 1 / 150;
    this.stroke_color = color(0) ; //random(paintte);
  }
  void update() {
    
    this.t += this.step;
    
    if (abs((this.t % 2) - 1) < this.step * 0.9) {
      //this.direction = !this.direction;
      this.direction_horizontal = random(1) > 0.5;
      this.direction_vertical = random(1) > 0.5;
      this.stroke_color = color(0) ; //random(paintte);
    }
    
  }
  void display() {
    push();
    //drawingContext.setLineDash([this.ld]);
    //drawingContext.lineDashOffset = this.ld * (this.t % 2);
    stroke(this.stroke_color);
    translate(this.center.x, this.center.y);
    strokeWeight(this.ld / 25);
    // text(round(this.t, 1), 0, 0);
    if (this.direction_horizontal) {
      scale(1, 1);
    } else {
      scale(-1, 1);
    }
    if (this.direction_vertical) {
      scale(1, 1);
    } else {
      scale(1, -1);
    }
    translate(-this.size / 2, -this.size / 2);
    strokeCap(PROJECT);
    line(0, 0, this.size, this.size);
    pop();
  }
}