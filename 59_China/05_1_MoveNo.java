// By Roni Kaufman
// https://ronikaufman.github.io

ArrayList<float[]> paths = new ArrayList<float[]>();
ArrayList<FloatDict> points = new ArrayList<FloatDict>();

ArrayList<float[]> pp = new ArrayList<float[]>();

FloatDict inventory = new FloatDict();
FloatDict aa = new FloatDict();

int pointIdx = 0;
int pointsPerFrame = 1;
int MAX_DEPTH = 2, N = 5;

int count = 1;

void setup() {
  size(600, 600);
  noFill();
  strokeWeight(2.5);
  
  float[] p1 = {random(10), 0};
  
  paths.add(p1);
  paths.add(p1);

  pp.add(p1);
  
  findPaths(p1, pp); //([0, 0], [[0, 0]]);
  
  rsfc(0, 0, 1, true, true, 0);
  float delta = random(1); //1-points[points.length-1][0]; ★
  
  inventory.set("x",1-delta);
  inventory.set("y",1+delta);
  
  points.add(inventory);
  
  //points:
  //[{x: 1-delta, y: 1+delta}, {x: 1-delta, y: 1+delta}, ..]
  
  background(5);
}

void draw() {
  background(5);
  print(count);
  int r0 = 50;
  int rDelta = 200 + count;
  
  for (int k = 0; k < pointsPerFrame; k++) {
    FloatDict p1 = points.get(points.size()-2);
    FloatDict p2 = points.get(points.size()-1);
    
    float r1 = p1.get("x")*rDelta + r0;
    float r2 = p2.get("x")*rDelta + r0;
    float theta1 = p1.get("y")*PI;
    float theta2 = p2.get("y")*PI;
    float x1 = width/2 + r1*cos(theta1);
    float y1 = height/2 + r1*sin(theta1);
    float x2 = width/2 + r2*cos(theta2);
    float y2 = height/2 + r2*sin(theta2);
    stroke(rainbow()); //pointIdx/(2*points.size())));
    if (p1.get("y") == p2.get("y")) {
      line(x1, y1, x2, y2);
    } else {
      if (theta1 > theta2){
        float tmp = theta1;
        theta1 = theta2;
        theta2 = tmp;
      } 
      arc(width/2, height/2, 2*r1, 2*r2, theta1, theta2);
    }
    
    r1 = (1-p1.get("x"))*rDelta + r0;
    r2 = (1-p2.get("x"))*rDelta + r0;
    theta1 = p1.get("y")*PI + PI;
    theta2 = p2.get("y")*PI + PI;
    x1 = width/2 + r1*cos(theta1);
    y1 = height/2 + r1*sin(theta1);
    x2 = width/2 + r2*cos(theta2);
    y2 = height/2 + r2*sin(theta2);
    stroke(rainbow()); //1/2+pointIdx/(2*points.size())));
    if (p1.get("y") == p2.get("y")) {
      line(x1, y1, x2, y2);
    } else {
      if (theta1 > theta2){
        float tmp = theta1;
        theta1 = theta2;
        theta2 = tmp;
      } 
      arc(width/2, height/2, 2*r1, 2*r2, theta1, theta2);
    }
    
    pointIdx++;
    /*
    if (pointIdx == points.size()-1) {
      noLoop();
      break;
    }
    */
  }
  
  pointsPerFrame += 0.01;
  count++;
}

ArrayList<float[]> possibleNeighbors(float[] p) {
  float i = p[0];
  float j = p[1];
  
  float[] i1 = {i, j+1};
  float[] i2 = {i, j-1};
  float[] i3 = {i+1, j};
  float[] i4 = {i-1, j};
  
  ArrayList<float[]> possibilities = new ArrayList<float[]>();
  if (i % 2 == 0 && j < N-1) possibilities.add(i1);
  if (i % 2 == 1 && j > 0) possibilities.add(i2);
  if (j % 2 == 0 && i < N-1) possibilities.add(i3);
  if (j % 2 == 1 && i > 0) possibilities.add(i4);
  return possibilities;
}

Boolean inArray(float[] p, ArrayList<float[]> arr) {
  float i = p[0];
  float j = p[0];
  
  for (float[] e : arr) {
    if (e[0] == i && e[1] == j) return true;
  }
  return false;
}

// find all paths in a N*N grid, going from top-left to bottom-right and through all points
void findPaths(float[] p, ArrayList<float[]> visited) {
  ArrayList<float[]> neighbors = possibleNeighbors(p);
  if (neighbors.size() == 0) {
    if (visited.size() == sq(N)) {
      for (float[] a: visited) paths.add(a);
    }
    return;
  }
  for (float[] neigh : neighbors) {
    visited.add(neigh);
    if (!inArray(neigh, visited)) findPaths(neigh, visited);
  }
}

// random space-filling curve
void rsfc(float x0, float y0, float s, Boolean topToBottom, Boolean leftToRight, float depth) {
  if (depth == MAX_DEPTH) {
    
      aa.set("x",x0+s/2);
    aa.set("y",y0+s/2);
    points.add(aa);
    return;
  }
  
  float newS = s/N;
  int idx1 = topToBottom ? 0 : 1;
  int idx2 = leftToRight ? 0 : 1;
  float[] path = paths.get(int(random(paths.size())));
  

    float i = path[0];
    float j = path[1];
    float x = leftToRight ? i*newS : (N-i-1)*newS;
    float y = topToBottom ? j*newS : (N-j-1)*newS;
    rsfc(x0+x, y0+y, newS, i % 2 == idx1, j % 2 == idx2, depth+1);
  
}

int rainbow() {
  //int paintte = ["#ef562f", "#f9d531", "#a7cc51", "#4bafdd", "#bd4ee5"];
  //int i = floor(paintte.length*t);
  //int amt = fract(paintte.length*t);
  return lerpColor(color(10, 20, 30), color(40, 30, 4), 60);
}