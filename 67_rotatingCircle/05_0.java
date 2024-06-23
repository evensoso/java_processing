// by SamuelYAN
// more works //
// https://twitter.com/SamuelAnn0924
// https://www.instagram.com/samuel_yan_1990/

var seed = Math.random() * 12643;
var t;
var num, vNum;
var radius, mySize, margin;
var sizes = [];

let colors = [];
let colors0 = "281914-1a1a1a-202020-242e30".split("-").map((a) => "#" + a);
let colors22 = "070A0D-171F26-4A5259-7B848C-AEB7BF".split("-").map((a) => "#" + a);
let colors23 = "D94389-4D578C-3791A6-3DF2D1-F28080".split("-").map((a) => "#" + a);
let colors24 = "F28D35-D96A29-A66641-D9B0A7-F2DAD8".split("-").map((a) => "#" + a);
let colors25 = "F2A7D8-473959-655A8C-9F8FD9-5979D9".split("-").map((a) => "#" + a);
let colors26 = "025951-012623-21BF92-73D9BC-0D0D0D".split("-").map((a) => "#" + a);
let colors7 = "fefefe-fffffb-fafdff-fef9fb-f7fcfe".split("-").map((a) => "#" + a);
let colors8 = "8c75ff-c553d2-2dfd60-2788f5-23054f-f21252-8834f1-c4dd92-184fd3-f9fee2-2E294E-541388-F1E9DA-FFD400-D90368-e9baaa-ffa07a-164555-ffe1d0-acd9e7-4596c7-6d8370-e45240-21d3a4-3303f9-cd2220-173df6-244ca8-a00360-b31016".split("-").map((a) => "#" + a);
let colors11 = "025159-3E848C-7AB8BF-C4EEF2-A67458".split("-").map((a) => "#" + a);
let colors12 = "10454F-506266-818274-A3AB78-BDE038".split("-").map((a) => "#" + a);
let colors13 = "D96690-F28DB2-F2C9E0-89C2D9-88E8F2".split("-").map((a) => "#" + a);
var color_setup1, color_setup2;
let color_bg;
let v_planet = [];

function setup() {
	randomSeed(seed);
	frameRate(50);
	// pixelDensity(5);
	mySize = min(windowWidth, windowHeight);
	margin = mySize / 100;
	createCanvas(windowWidth, windowHeight, WEBGL);
	color_setup1 = colors7;
	color_setup2 = random([colors22, colors23, colors24, colors25, colors26, colors11, colors12, colors13]);
	color_bg = "#202020";
	background(color_bg);
	// num = 50;
	num = int(random(10, 20));
	radius = mySize * 0.75;
	for (let a = 0; a < TAU; a += TAU / num) {
		sizes.push(random(0.1, 0.5))
	}
	t = 0;
}

function draw() {
	randomSeed(seed);
	// background(color_bg);

	for (let i = 0; i < num; i++) {
		let a = (TAU / num) * i;
		let x = radius * sin(a + t) / random(5, 2) / 2.0;
		let y = radius * cos(a + t) / random(2, 5) / 2.0;
		v_planet[i] = createVector(x, y);
	}
	push();

	for (let q = 0; q < 1 / 5; q += 4 * random(0.01, 0.02)) {
		for (let j = 0; j < 1; j++) {

			rotateX(t / 100 + q);
			rotateY(t / 100 + q);
			rotateZ(random(TAU) + t + q);
			// noFill();
			strokeWeight(2 * random(0.2, 0.6));
			fill(str(color_bg)+"33");

			for (let i = 0; i < num; i += 2) {
				let d = random(radius / 2, radius / 3);
				let x_plus = 1.25 * random(-d, d) / 5;
				let y_plus = 1.25 * random(-d, d) / 5;
				let z_plus = 1.25 * random(-d, d) / 2;
				push();
				translate(v_planet[i].x, v_planet[i].y, z_plus);
				rotateX(sin(-t) / 20);
				rotateY(cos(5*t) / 20);
				rotateZ(sin(5 * t + q));
				stroke(random(color_setup2));
				// stroke(random(colors7));
				rectMode(CENTER);
				square(x_plus, y_plus, random(z_plus));
				// strokeWeight(1 * random(0.2, 0.6));
				line(x_plus, y_plus, random(-z_plus, 0) / 4, x_plus, y_plus, random(-0, z_plus) / 4);
				pop();
			}

			for (let i = 0; i < num; i += 20) {
				let d = (1.0 + sin(t)) * random(radius / 2, radius / 1);
				let x_plus = 0.75 * random(-d, d) / 1;
				let y_plus = 0.75 * random(-d, d) / 1;
				let z_plus = 0.75 * random(-d, d) / 1;
				noStroke();
				// strokeWeight(random(0.1));
				// stroke(random(color_setup2));
				fill(random(color_setup2));
				push();
				translate(v_planet[i].x + x_plus, v_planet[i].y + y_plus, z_plus);
				rotateX(t);
				rotateY(t);
				rotateZ(t);
				sphere(random(2));
				pop();
			}
		}
	}
	pop();

	t += 2 * random(0.001, 0.005);
	if(frameCount == 2000){
		noLoop();
	}
}


function keyTyped() {
	if (key === "s" || key === "S") {
		saveCanvas("0711_Emotional lines_11_2022", "png");
	}
}