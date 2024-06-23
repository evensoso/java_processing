/**
 * Distance 2D. 
 * 
 * Move the mouse across the image to obscure and reveal the matrix.  
 * Measures the distance from the mouse to each square and sets the
 * size proportionally. 
 */

float max_distance;
int count;

void setup() {
  size(900, 1000); 
  noStroke();
  max_distance = dist(0, 0, width, height);
}

void draw() {
  background(0);
  if (count > width+800) {
    count = 0;
  }
  else{
    count += 8;
  }
  
  for(int i = 0; i <= width; i += 20) {
    for(int j = 0; j <= height; j += 20) {
      float size = dist(-500+count, 200, i, j);
      size = size/max_distance * 66;
      ellipse(i, j, size+2, size+2);
    }
  }
}
