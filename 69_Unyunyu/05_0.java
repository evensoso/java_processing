 // by SamuelYAN
 // more works //
 // https://twitter.com/SamuelAnn0924
 // https://www.instagram.com/samuel_yan_1990/

 let ito_num, point_num;
 let seed = Math.random() * 99999;
 let mySize, margin;
 let tile_x, tile_y;
 let tile_x_size, tile_y_size;

 // colors
 let colors_dark = "321E1E-27374D-116D6E-5F264A-2C3639-191A19".split("-").map((a) => "#" + a);
 let colors_light = "EEE3CB-F9F5F6-FBFFDC-F8E8EE-E3F4F4-C1D0B5-FEA1A1-FFD966-FADA9D".split("-").map((a) => "#" + a);

 let colors0, colors1;
 let colors2 = "f4c815-f9cad7-A57283-c1d5de-deede6-f7f6cf-b6d8f2-f4cfdf-9ac8eb-ccd4bf-e7cba9-eebab2-f5f3f7-f5e2e4-AAD9CD-E8D595-E9BBB5-E7CBA9-8DA47E".split("-").map((a) => "#" + a);
 let color1, color2;
 let plus;

 function setup() {
 	randomSeed(seed);
 	mySize = 500;
	margin = mySize / 100;
	createCanvas(mySize, mySize * 1);
 	// if (int(seed) % 2 == 0) {
 	// 	colors0 = "281914-1a1a1a-202020-242e30".split("-").map((a) => "#" + a);
 	// 	colors1 = "fef9fb-fafdff-ffffff-fcfbf4-f9f8f6".split("-").map((a) => "#" + a);
 	// }
 	// if (int(seed) % 2 == 1) {
 		colors1 = "281914-1a1a1a-202020-242e30".split("-").map((a) => "#" + a);
 		colors0 = "fef9fb-fafdff-ffffff-fcfbf4-f9f8f6".split("-").map((a) => "#" + a);
 	// }

 	ito_num = int(random(4, 1));
 	color1 = random(colors1);
 	color2 = random(colors0);
 	plus = 0;
 	makeFilter();
 	// pixelDensity(4);
 	point_num = int(random(14, 8));
 	background(random(colors0));
 }

 function draw() {
 	randomSeed(seed);
 	noiseSeed(seed);

 	push();
 	translate(width / 2+ plus / 30, height / 2);
 	rotate(random([0, PI / 2, PI, PI / 2 * 3]));
 	rotate(random(TAU));
 	for (let i = 0; i < ito_num; i++) {
 		randomSeed(seed * i);
 		push();
 		// translate(random(-mySize / 2, mySize / 2), random(-mySize / 2, mySize / 2));
 		translate(random(1, 50) * plus / point_num, random(-mySize / 4, mySize / 4));
 		// rotate(random([-1, 1]) * plus / random(2, 4) / point_num);
 		rotate(-random(TAU) + random([-1, 1]) * 0.5 * 1.5 * plus / point_num);
 		noFill();
 		stroke(str(random(colors1)) + "bf");
 		strokeWeight(random(0.5, 1.1) + plus / 30);
 		drawingContext.shadowColor = random(colors0);
 		drawingContext.shadowOffsetX = 1.5;
 		drawingContext.shadowOffsetY = 1.5;
 		drawingContext.shadowBlur = 0;
 		beginShape();
 		for (let x = -width * 2; x < width * 2; x += width / point_num) {
 			let n = noise(x * 0.1, i * 0.1, frameCount * 0.02);
 			let y = map(n, 0, 1, -mySize * 0.05, mySize * 0.15);
 			curveVertex(x + 1.0 * i * sin(random(0.1, 1) * plus + 0.1 * x * sin(random(1, 2) * plus - 1.5) + 1.5), y - plus);
 			// point(x + 1.0 * i * sin(random(0.1, 1) * plus + 0.1 * x * sin(random(1, 2) * plus - 1.5) + 1.5), y - plus);
 			// circle(x,y, width/point_num);
 		}
 		endShape();
 		pop();
 	}

 	pop();

 	plus += random(2, 1) * random(0.01, 0.05);
 	if (frameCount % 50 == 0) {
 		point_num++;
 	}
 	// if (frameCount < 100) {
 	// image(overAllTexture, 0, 0);
 	// }
 	// if (frameCount > 799) {
 	if (frameCount > random([399, 599, 1299])) {
 		noLoop();
 		blendMode(BLEND);
 		image(overAllTexture, 0, 0);
 		blendMode(SCREEN);
 		strokeWeight(random(0.10, 0.5) / 2);
 		stroke(str(random(colors1)) + "0d");
 		noFill();
 		drawingContext.setLineDash([1, 4, 1, 3]);
 		drawOverPattern();
 		drawingContext.setLineDash([]);
 		blendMode(BLEND);

 		noFill();
 		strokeWeight(margin);
 		rectMode(CORNER);
 		stroke("#202020");
 		rect(0, 0, width, height);
 	}
 }

// by SamuelYAN
// more works //
// https://twitter.com/SamuelAnn0924
// https://www.instagram.com/samuel_yan_1990/

function keyTyped() {
	if (key === "s" || key === "S") {
		saveCanvas("0606_ito IV-2-BW-2_2023", "png");
	}
}

function makeFilter() {
	let filterNum = int(Math.random() * 982);
	randomSeed(seed);
	// noiseのフィルターをつくる
	colorMode(HSB, 360, 100, 100, 100);
	drawingContext.shadowColor = color(0, 0, 5, 10);
	overAllTexture = createGraphics(width, height);
	overAllTexture.loadPixels();
	for (var i = 0; i < width; i += 4) { // noprotect
		for (var j = 0; j < height; j += 4) {
			if (filterNum % 4 == 0) {
				overAllTexture.set(i, j, color(random(60), 5, 95, noise(i / 3, j / 3, (i * j) / 50) * 12)); // white
			} else if (filterNum % 4 == 1) {
				overAllTexture.set(i, j, color(48, 25, 98, noise(i / 3, j / 3, (i * j) / 50) * 12)); // yellow
			} else if (filterNum % 4 == 2) {
				overAllTexture.set(i, j, color(random(5, 8), 25, 98, noise(i / 3, j / 3, (i * j) / 50) * 12));
			} else if (filterNum % 4 == 3) {
				overAllTexture.set(i, j, color(random(200, 210), 10, 86, noise(i / 3, j / 3, (i * j) / 50) * 12));
			}
		}
	}
	overAllTexture.updatePixels();
}

function drawOverPattern() {
	push();
	translate(width / 2, height / 2);
	//rotate(-PI / 2);

	let s = max(width, height) / 1 * sqrt(3) - 2;
	let n = 6;

	for (let theta = TWO_PI / 6; theta < TWO_PI; theta += TWO_PI / 6) { // noprotect
		divideOP(0, 0, s * cos(theta), s * sin(theta), s * cos(theta + TWO_PI / 6), s * sin(theta + TWO_PI / 6), n);
	}
	pop();
}

function prop(x1, y1, x2, y2, k) {
	let x3 = (1 - k) * x1 + k * x2;
	let y3 = (1 - k) * y1 + k * y2;
	return [x3, y3];
}

function divideOP(x1, y1, x2, y2, x3, y3, n) {
	if (n > 1) {
		let [xA, yA] = prop(x1, y1, x2, y2, 1 / 3);
		let [xB, yB] = prop(x1, y1, x2, y2, 2 / 3);
		let [xC, yC] = prop(x2, y2, x3, y3, 1 / 3);
		let [xD, yD] = prop(x2, y2, x3, y3, 2 / 3);
		let [xE, yE] = prop(x3, y3, x1, y1, 1 / 3);
		let [xF, yF] = prop(x3, y3, x1, y1, 2 / 3);
		let [xG, yG] = prop(xF, yF, xC, yC, 1 / 2);
		divideOP(x1, y1, xA, yA, xF, yF, n - 1);
		divideOP(xA, yA, xB, yB, xG, yG, n - 1);
		divideOP(xB, yB, x2, y2, xC, yC, n - 1);
		divideOP(xG, yG, xF, yF, xA, yA, n - 1);
		divideOP(xC, yC, xG, yG, xB, yB, n - 1);
		divideOP(xF, yF, xG, yG, xE, yE, n - 1);
		divideOP(xG, yG, xC, yC, xD, yD, n - 1);
		divideOP(xD, yD, xE, yE, xG, yG, n - 1);
		divideOP(xE, yE, xD, yD, x3, y3, n - 1);
	} else {
		makeTriangle([x1, y1], [x2, y2], [x3, y3]);
	}
}

function makeTriangle(v1, v2, v3) {
	let points = shuffle([v1, v2, v3]);
	let [x1, y1] = points[0];
	let [x2, y2] = points[1];
	let [x3, y3] = points[2];
	let iStep = 1 / (pow(2, floor(random(4, 2))));
	for (let i = 0; i < 1; i += iStep) { // noprotect
		let [x4, y4] = prop(x1, y1, x2, y2, 1 - i);
		let [x5, y5] = prop(x1, y1, x3, y3, 1 - i);
		triangle(x1, y1, x4, y4, x5, y5);
	}
}