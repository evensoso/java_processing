/*

Based on...

RANDOM GROWTH
Jeff Thompson | 2019/20 | jeffreythompson.org
https://editor.p5js.org/jeffThompson/sketches/XjwLIdEiU


Mycelium growth simulation - opacity based on a source image

*/

let maxAge = 90; // tendrils older than this will pruned

let fungi; // list of objects

let img;

function preload() {
	img = loadImage('birb.jpg');
}

function setup() {
	createCanvas(1000, 1000);
	background(150, 110, 100);

	fungi = [];

}


function draw() {

		for (let i = fungi.length - 1; i >= 0; i -= 1) {

			// get the current object
			let f = fungi[i];

			// update and, if it has reached a
			// certain radius or is too old, remove it
			f.update();
			if (f.distFromCenter >= height / 2.5 || f.age > maxAge) {
				fungi.splice(i, 1);
			}


			// draw it onscreen
			f.display();
		}

		if (fungi.length <5) {
			let f = new Fungus(randomGaussian(width/2,width/8), randomGaussian(height/2,width/8));
			fungi.push(f);
		}

}

// this function fires after the mouse has been
// clicked anywhere
function mouseClicked() {
  setup();
}

class Fungus {

	constructor(x, y, angle) {

		// values common to all Fungus objects
		this.angleChangeAmt = radians(2);
		this.speed = 1;

		this.chanceSplit = 2;
		this.splitAngle = radians(20);

		// passed in from the arguments
		this.x = x;
		this.y = y;
		this.prevX = x;
		this.prevY = y;

		// if the angle variable is passed in, use that
		// otherwise, set to a random angle
		this.angle = angle || random(0, TWO_PI);

		// variables updated every frame
		this.distFromCenter;
		this.age = 0;
	}


	update() {

		// increase age of this tendril
		this.age += 1;

		// calculate the distance from the center of the sketch
		this.distFromCenter = dist(width / 2, height / 2, this.x, this.y);

		// move in a random direction
		this.angle += random(-this.angleChangeAmt, this.angleChangeAmt);
		this.prevX = this.x;
		this.prevY = this.y;
		this.x += cos(this.angle) * this.speed;
		this.y += sin(this.angle) * this.speed;

		// randomly split into two Fungus objects
		// new one will split off in one direction,
		// current goes in the opposite direction

		if (fungi.length < 1000) {  // keep it from slowing down too much,,,
			if (random(100) < this.chanceSplit) {
				let f = new Fungus(this.x, this.y, this.angle + this.splitAngle);
				fungi.push(f);
				this.angle -= this.splitAngle;
			}
		}
	}


	display() {
		strokeWeight(random(0.5,2));
		// base the opacity on the image, colour on the age
		var val = red(img.get(map(this.x, 0, width, 0, img.width), map(this.y, 0, height, 0, img.height)))/255;
    var age = this.age/maxAge;
		
		stroke(color(lerp(200,255,age),lerp(245,100,age),lerp(200,235,age),lerp(50,8,val)));
								 
		line(this.prevX, this.prevY, this.x, this.y);
	}
}