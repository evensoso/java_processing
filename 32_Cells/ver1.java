/**
 * Worley noise : with the nearest point
 *
 * Processing 3.5.3
 * @author @deconbatch
 * @version 0.1
 * created 0.1 2021.08.07
 */

void setup() {
  size(780, 480);
  colorMode(HSB, 360.0, 100.0, 100.0, 100.0);
  smooth();
  noLoop();
}

void draw() {

  // number of the reference points
  int pointNum  = floor(random(5.0, 30.0));
    
  // get random location points
  ArrayList<PVector> points = randomPoints(pointNum, width, height);

  // draw
  background(0.0, 0.0, 0.0, 100.0);
  drawWorley(points);
  
  //add----
    String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\32_Lissajous\\1\\32_Penrose-30_######.png";
    saveFrame(outP);
    //----

}

/**
 * randomPoints : return PVector array of random location points
 */
public ArrayList<PVector> randomPoints(float _num, float _w, float _h) {

  ArrayList<PVector> rnds = new ArrayList<PVector>();
  for (int i = 0; i < _num; i++) {
    rnds.add(new PVector(random(_w), random(_h)));
  }
  return rnds;

}

/**
 * drawWorley : draws using the Worley noise
 */
public void drawWorley(ArrayList<PVector> _ps) {

  float range  = max(width, height);
  float nsStep = 0.005;
  float dsMult = 1.0;
  
  noStroke();
  for (int iX = 0; iX < width; iX++) {
    for (int iY = 0; iY < height; iY++) {
      // get the distance with the nearest point
      float minDist = range;
      for (int i = 0; i < _ps.size(); i++) {
        float distance = dist(iX, iY, _ps.get(i).x, _ps.get(i).y);
        if (minDist > distance) {
          minDist = distance;
        }
      }
      // calculate brightness with the distance
      float nVal = minDist * dsMult * nsStep;
      float pBri = (nVal * 100.0) % 90.0;
      fill(0.0, 0.0, pBri, 100.0);
      rect(iX, iY, 1.0, 1.0);
    }
  }
}

/*
  Copyright (C) 2021- deconbatch

  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation; either version 3 of the License, or
  (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with this program.  If not, see &lt;http://www.gnu.org/licenses/&gt;
*/