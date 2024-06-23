/**
 * Meowsh.
 * image manipulation with node garden.
 *
 * Processing 3.5.3
 * @author @deconbatch
 * @version 0.1
 * created 0.1 2020.05.09
 */

import java.util.Collections;

void setup() {

  size(1080, 1080);
  colorMode(HSB, 360.0, 100.0, 100.0, 100.0);
  smooth();
  noLoop();

}

void draw() {

  int caseWidth  = 30;
  int baseCanvas = width - caseWidth * 2;

  PImage img = loadImage("D:\\09_music_create\\03_PICTURE\\demo.jpg");
  float rateSize = baseCanvas * 1.0 / max(img.width, img.height);
  img.resize(floor(img.width * rateSize), floor(img.height * rateSize));

  background(0.0, 0.0, 90.0, 100.0);
  translate((width - img.width) / 2, (height - img.height) / 2);

  // node gardening with random points.
  ArrayList<Node> nodes = plotNodes(randomAdds(img.width, img.height), img);
  drawLines(nodes, 1.0, 0.3);
  drawLines(nodes, 2.0, 0.1);
  drawLines(nodes, 3.0, 0.05);

  // node gardening with detected edges.
  ArrayList<PVector> edges = detectEdge(img);
  Collections.shuffle(edges);
  drawLines(plotNodes(edges, img), 1.0, 1.0);

  casing(caseWidth, img.width, img.height);
  
  //add----
  String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\38_Lissajous\\1\\38_Penrose-30_######.png";
  saveFrame(outP);
  //----

  //exit();
  
}

/**
 * randomAdds : make random PVectors
 * @param  _width, _height : x, y scope of random location.
 * @return ArrayList<PVector> : random PVectors.
 */
public ArrayList<PVector> randomAdds(float _width, float _height) {

  ArrayList<PVector> rnds = new ArrayList<PVector>();
  int   rndsMax  = 50000;
  for (int rndsCnt = 0; rndsCnt < rndsMax; rndsCnt++) {
    rnds.add(new PVector(random(_width), random(_height)));
  }
  return rnds;

}

/**
 * plotNodes : locate Nodes with some distance each other.
 * @param ArrayList<PVector> _adds : location point candidates.
 * @param PImage             _img  : original image to get color.
 * @return ArrayList<Node> : holds nodes.
 */
public ArrayList<Node> plotNodes(ArrayList<PVector> _adds, PImage _img) {

  float gap = 6.0;
  ArrayList<Node> nodes = new ArrayList<Node>();
  _img.loadPixels();

  for (int i = 0; i < _adds.size(); i++) {

    int fX = floor(_adds.get(i).x);
    int fY = floor(_adds.get(i).y);
    
    // add new node
    boolean inner = false;
    for (Node f : nodes) {
      if (dist(fX, fY, f.x, f.y) < gap) {
        inner = true;
        break;
      }
    }
    if (!inner) {
      int pixIndex = floor(fY * _img.width + fX);
      nodes.add(new Node(
                         fX,
                         fY,
                         hue(_img.pixels[pixIndex]),
                         saturation(_img.pixels[pixIndex]),
                         brightness(_img.pixels[pixIndex])
                         ));
    }
  }

  return nodes;

}

/**
 * Node : draw and hold location and color.
 */
public class Node {

  public  int   x, y;   // coordinate of node
  private float hueVal; // hue value of node
  private float satVal; // saturation value of node
  private float briVal; // brightness value of node

  Node(int _x, int _y, float _c, float _s, float _b) {
    x = _x;
    y = _y;
    hueVal = _c;
    satVal = _s;
    briVal = _b;
  }

}

/**
 * drawLines : draw lines between nodes that have the same color and have some distance.
 * @param ArrayList<Node> _nodes  : holds Circle Packing results.
 * @param float           _range  : range ratio to draw line.
 * @param float           _weight : stroke weight ratio.
 */
public void drawLines(ArrayList<Node> _nodes, float _range, float _weight) {

  float rangeShort = min(width, height) * 0.005 * _range;
  float rangeLong  = rangeShort + min(width, height) * 0.005;

  noFill();
  for (Node nFrom : _nodes) {
    for (Node nTo : _nodes) {
      float divDist = dist(nFrom.x, nFrom.y, nTo.x, nTo.y);
      float divHue  = abs(nFrom.hueVal - nTo.hueVal);
      if (
          divDist > rangeShort && divDist < rangeLong  // within range
          && (divHue > 355.0 || divHue < 5.0)          // nealy same color(hue)
          ) {
        strokeWeight(map(divDist, rangeShort, rangeLong, 2.0, 0.1) * _weight);
        stroke(
               nFrom.hueVal,
               nFrom.satVal,
               nFrom.briVal,
               100.0
               );
        line(
             nFrom.x,
             nFrom.y,
             nTo.x,
             nTo.y
             );
      }
    }
  }
}

/**
 * detectEdge : detect edges of photo image.
 * @param  _img : detect edges of this image.
 * @return ArrayList<PVector> : edges locations.
 */
public ArrayList<PVector> detectEdge(PImage _img) {

  ArrayList<PVector> edges = new ArrayList<PVector>();

  _img.loadPixels();
  for (int idxW = 1; idxW < _img.width - 1; ++idxW) {  
    for (int idxH = 1; idxH < _img.height - 1; ++idxH) {

      int pixIndex = idxH * _img.width + idxW;

      // saturation difference
      float satCenter = saturation(_img.pixels[pixIndex]);
      float satNorth  = saturation(_img.pixels[pixIndex - _img.width]);
      float satWest   = saturation(_img.pixels[pixIndex - 1]);
      float satEast   = saturation(_img.pixels[pixIndex + 1]);
      float satSouth  = saturation(_img.pixels[pixIndex + _img.width]);
      float lapSat = pow(
                         - satCenter * 4.0
                         + satNorth
                         + satWest
                         + satSouth
                         + satEast
                         , 2);

      // brightness difference
      float briCenter = brightness(_img.pixels[pixIndex]);
      float briNorth  = brightness(_img.pixels[pixIndex - _img.width]);
      float briWest   = brightness(_img.pixels[pixIndex - 1]);
      float briEast   = brightness(_img.pixels[pixIndex + 1]);
      float briSouth  = brightness(_img.pixels[pixIndex + _img.width]);
      float lapBri = pow(
                         - briCenter * 4.0
                         + briNorth
                         + briWest
                         + briSouth
                         + briEast
                         , 2);

      // hue difference
      float hueCenter = hue(_img.pixels[pixIndex]);
      float hueNorth  = hue(_img.pixels[pixIndex - _img.width]);
      float hueWest   = hue(_img.pixels[pixIndex - 1]);
      float hueEast   = hue(_img.pixels[pixIndex + 1]);
      float hueSouth  = hue(_img.pixels[pixIndex + _img.width]);
      float lapHue = pow(
                         - hueCenter * 4.0
                         + hueNorth
                         + hueWest
                         + hueSouth
                         + hueEast
                         , 2);

      // bright and saturation difference
      if (
          brightness(_img.pixels[pixIndex]) > 30.0
          && lapSat > 20.0
          ) edges.add(new PVector(idxW, idxH));

      // bright and some saturation and hue difference
      if (
          brightness(_img.pixels[pixIndex]) > 30.0
          && saturation(_img.pixels[pixIndex]) > 10.0
          && lapHue > 100.0
          ) edges.add(new PVector(idxW, idxH));

      // just brightness difference
      if (lapBri > 100.0) edges.add(new PVector(idxW, idxH));

    }
  }

  return edges;
}

/**
 * casing : draw fancy casing
 */
public void casing(int _casing, float _w, float _h) {
  fill(0.0, 0.0, 0.0, 0.0);
  strokeWeight(_casing + 4.0);
  stroke(0.0, 0.0, 30.0, 100.0);
  rect(-_casing * 0.5, -_casing * 0.5, _w + _casing, _h + _casing);
  strokeWeight(_casing);
  stroke(0.0, 0.0, 100.0, 100.0);
  rect(-_casing * 0.5, -_casing * 0.5, _w + _casing, _h + _casing);
  noStroke();
  noFill();
}