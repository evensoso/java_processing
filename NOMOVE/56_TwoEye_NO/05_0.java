//For the #WCCChallenge, theme: "eyes"

let dil = 0;
let tdil;
let ang, tang;
let auto = true;
let lids = true;
let lim, eyesep;
let colorA, colorB, tcolorA;
let colors = [];
let cindex = 0;

function setup() {
	createCanvas(windowWidth, windowHeight);
	lim = min(width, height);
	tdil = 0;
	ang = createVector(0, 0);
	tang = createVector(0, 0);
	colors = [color(66, 140, 171, 128), color(150, 75, 0, 128), color(153, 121, 80, 128), color(73, 118, 101, 128), color(46, 83, 111, 128), color(161, 202, 241, 128)];
	tcolorA = colors[cindex];
	colorA = color(0, 0, 0, 128);
	colorB = color('rgba(228,217,119,0.5)');
	eyesep = lim / 1.6;
}

function draw() {
	if (auto) {
		mouseX = width * noise(frameCount / 100);
		mouseY = height * noise(1000 + frameCount / 50);
	}
	tdil = map(mouseY, 0, lim, lim / 3, 2 * lim / 3, true);
	dil = lerp(dil, tdil, 0.2);
	tang.x = map(mouseX, 0, width, -PI / 7.5, PI / 7.5);
	tang.y = map(mouseY, 0, height, -PI / 14, PI / 14);
	ang.lerp(tang, 0.2);
	colorA = lerpColor(colorA, tcolorA, frameCount / 150);
	background(0);
	translate(width / 2, height / 2);
	drawEye(lim, -eyesep, 0);
	drawEye(lim, eyesep, 0);
	if (lids) {
		drawLid(-eyesep, 0, 1, 1, lim);
		drawLid(eyesep, 0, -1, 1, lim);
	}
}

function drawEye(esize, x, y) {
	push();
	translate(x, y);
	push();
	noStroke();
	fill(255);
	ellipse(0, 0, esize / 1.5, esize / 1.5);
	translate(sin(ang.x) * esize / 3, sin(ang.y) * esize / 3);
	fill(0);
	ellipse(0, 0, (esize / 2.5) * cos(ang.x), (esize / 2.5) * cos(ang.y));
	fill(colorA);
	ellipse(0, 0, (esize / 2.4) * cos(ang.x), (esize / 2.4) * cos(ang.y));
	fill(0);
	translate(sin(ang.x) * esize / TAU, 0);
	ellipse(0, 0, (esize / 3 - dil / 3) * cos(ang.x), esize / 3 - dil / 3);
	translate(-sin(ang.x) * height / TAU, 0);
	for (let a = 0; a < TAU; a += TAU / 50) {
		push();
		translate((esize / 5.1) * cos(a), (esize / 5.1) * sin(a));
		rotate(a - PI / 12);
		for (let i = 0; i < 24; i++) {
			stroke(lerpColor(colorA, colorB, i / 24));
			strokeWeight(map(i, 24, 0, esize / 50, esize / 200));
			translate((-(esize / 2.1) - 1.15 * dil) / 180 + (sin(ang.x) * cos(a) * 5), -sin(ang.x) * sin(a) * 5);
			rotate(sin(i) / 4);
			point(0, 0);
		}
		pop();
	}
	pop();
	push();
	noStroke();
	fill(255, 150);
	rotate(-PI / 8);
	rect(esize / 20, -esize / 15, esize / 40, esize / 40);
	rotate(PI / 8);
	pop();
	pop();
}

function drawLid(x, y, flipx, flipy, lsize) {
	push();
	translate(x, y - lsize / 20);
	scale(flipx, flipy)
	noStroke();
	fill(0);
	beginShape();
	curveVertex(0, -lsize / 2);
	curveVertex(lsize / 4, -lsize / 3);
	curveVertex(lsize / 2.9, lsize / 12);
	curveVertex(lsize / 12, -lsize / 2 + dil / 1.1);
	curveVertex(-lsize / 3, -lsize / 2 + dil / 1.1);
	curveVertex(-lsize / 3, -lsize / 12);
	curveVertex(-lsize / 4, -lsize / 3);
	curveVertex(0, -lsize / 2);
	endShape(CLOSE);
	pop();

}

function mousePressed() {
	auto = !auto;
	cindex=(cindex+1)%colors.length;
	tcolorA = colors[cindex];
	frameCount = 0;
}

function keyPressed() {
	if (keyCode == 32) lids = !lids; //remove lids
	if (keyCode == 13) { // move eyes together for OP thumbnail
		if (eyesep == (lim / 1.6)) eyesep = lim / 2.5;
		else eyesep = lim / 1.6;
	}
}