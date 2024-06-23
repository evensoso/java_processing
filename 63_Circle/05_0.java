let global_n = 0;

function setup() {
	createCanvas(800, 800);
	colorMode(HSB, 360, 100, 100, 100);
	angleMode(DEGREES);
}

function draw() {
	blendMode(BLEND);
	background(0, 0, 20);
	randomSeed(0);
	blendMode(ADD);
	// randomSeed(random(10000));
	// drawingContext.shadowColor = color(0, 0, 0, 15);
	// drawingContext.shadowBlur = width / 30;
	// drawingContext.shadowOffsetX = width / 30 / 2;
	// drawingContext.shadowOffsetY = width / 30 / 2;
	consecutiveHollowedOutArc(
		width / 2,
		height / 2,
		50,
		300,
		0,
		360
	);
	// noLoop();
}

function consecutiveHollowedOutArc(
	center_x,
	center_y,
	r_min,
	r_max,
	start_angle,
	end_angle
) {
	push();
	translate(center_x, center_y);
	let angle = start_angle;
	let angle_step;
	let mode = random() > 0.5;
	let r = r_max;
	let r_step = 10;
	while (r > r_min) {
		if (mode == false) {
			r_step = int(random(3, 10)) * 3;
		} else {
			r_step = int(random(3, 10)) * 10;
		}
		angle = start_angle;
		while (angle < end_angle) {
			if (mode == true) {
				angle_step = int(random(random()) * 4 + 1) * 5;
			} else {
				angle_step = int(random(1, 5)) * 15;
			}
			if (random() > 0.95) mode = !mode;
			if (angle + angle_step > end_angle) angle_step = end_angle - angle;
			// arc(0, 0, r_max, r_max, angle, angle + angle_step,PIE);
			hollowedOutArc(
				0,
				0,
				r,
				max(r / 4, r - r_step),
				angle,
				angle + angle_step,
				true,
				1
			);
			angle += angle_step;
		}
		r -= r_step;
	}

	pop();
}

function hollowedOutArc(
	x,
	y,
	maxD,
	minD,
	startAngle,
	endAngle,
	bool,
	angleStep = 1
) {
	let dir = random() > 0.5 ? -1 : 1;
	push();
	translate(x, y);
	global_n++ % 2 == 0 ? stroke(0, 0, 50) : stroke(0, 0, 100);
	noFill();
	if (bool) {
		if (global_n++ % 2 == 0) {
			let angle = min(startAngle, endAngle);
			let angle_plus =
				(max(endAngle, startAngle) - min(endAngle, startAngle)) /
				int(random([1, 3, 5, 7, 9, 11, 13]));
			while (angle < endAngle) {
				hollowedOutArc(0, 0, maxD, minD, angle, angle + angle_plus, false, 1);
				angle += angle_plus;
			}
		} else {
			let d = minD;
			let d_plus = (maxD - minD) / int(random([1, 3, 5, 7, 9, 11, 13]));
			while (d < maxD) {
				hollowedOutArc(0, 0, d, d + d_plus, startAngle, endAngle, false, 1);
				d += d_plus;
			}
		}
	} else {
		let t =
			(1 + ((maxD + startAngle / 360 + (dir * frameCount) / 100) % 1)) % 1;
		t = easeInOutCirc(t);
		let sw = max(maxD, minD) - min(maxD, minD);
		strokeCap(SQUARE);
		strokeWeight((1 - t) * sw);
		let d = minD + sw / 2;
		let ld = (2 * d * PI * (endAngle - startAngle)) / 360;
		drawingContext.setLineDash([ld]);
		drawingContext.lineDashOffset = ld * t * 2;
		beginShape();
		for (let a = startAngle; a <= endAngle; a += angleStep) {
			vertex(cos(a) * d, sin(a) * d);
		}
		endShape();
	}
	pop();
}

function easeInOutCirc(x) {
	return x < 0.5 ?
		(1 - Math.sqrt(1 - Math.pow(2 * x, 2))) / 2 :
		(Math.sqrt(1 - Math.pow(-2 * x + 2, 2)) + 1) / 2;
}

function easeInOutElastic(x) {
	const c5 = (2 * Math.PI) / 4.5;

	return x === 0 ?
		0 :
		x === 1 ?
		1 :
		x < 0.5 ?
		-(Math.pow(2, 20 * x - 10) * Math.sin((20 * x - 11.125) * c5)) / 2 :
		(Math.pow(2, -20 * x + 10) * Math.sin((20 * x - 11.125) * c5)) / 2 + 1;
}