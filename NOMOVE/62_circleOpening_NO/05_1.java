let global_n = 0;
let palette;

void setup() {
	createCanvas(windowWidth, windowHeight);
	palette = shuffle(random(colorScheme).colors.concat());
}

void draw() {
	background(20);
	randomSeed(0);
	let w = sqrt(sq(width) + sq(height));
	let d2 = w + 200 * map(cos(frameCount / 50), -1, 1, 0, 1);
	let d1 = 0 + 100 * map(sin(frameCount / 50), -1, 1, 0, 1);
	map(sin(frameCount / 50), -1, 1, 0, 300);
	let depth = 3;

	let n = noise(frameCount / 400) * TWO_PI;

	push();
	translate(width / 2, height / 2);
	rotate(frameCount / 100);
	recursiveCircle(d1, d2, n, TWO_PI + n, depth, [color(0), color(255, 0)]);
	pop();

	// noLoop();
}

void recursiveCircle(minD, maxD, minA, maxA, depth, colors) {
	push();
	let aA = map(
		sin(abs(-minD + maxD) / 200 + frameCount / 250),
		-1,
		1,
		0.1,
		0.9
	);
	let dA = map(
		cos(degrees(minA + maxA) / 100 + (aA * PI) / 2 + frameCount / 200),
		-1,
		1,
		0.1,
		0.9
	);
	aA = global_n % 4 >= 2 ? aA : 1 - aA;
	dA = global_n % 2 == 0 ? dA : 1 - dA;

	let aB = 1 - aA;
	let dB = 1 - dA;
	let d = abs(maxD - minD);
	let a = abs(maxA - minA);

	if (depth > 0) {
		recursiveCircle(minD, minD + d * dA, minA, minA + a * aA, depth - 1, colors);
		recursiveCircle(minD + d * dA, maxD, minA + a * aA, maxA, depth - 1, colors);
		recursiveCircle(minD, minD + d * dA, minA + a * aA, maxA, depth - 1, colors);
		recursiveCircle(minD + d * dA, maxD, minA, minA + a * aA, depth - 1, colors);
	} else {
		drawArc(minD, minD + d * dA, minA, minA + a * aA, aB, colors);
		drawArc(minD + d * dA, maxD, minA + a * aA, maxA, dB, colors);
		drawArc(minD, minD + d * dA, minA + a * aA, maxA, aA, colors);
		drawArc(minD + d * dA, maxD, minA, minA + a * aA, dA, colors);
	}

	pop();
}

void drawArc(minD, maxD, startAngle, endAngle, n) {
	global_n++;
	let t = random() > 0.5 ? 0 : 1;
	let c = shuffle(palette.concat())
	let colors = [c[0], color(255, 0)];
	let colors2 = [c[1], color(255, 0)];
	let num = 2 * int(random(1, 5)) + 1;
	let isFront = random() > 0.5;
	if (
		startAngle < 0 ||
		endAngle - startAngle < 0 ||
		minD < 0 ||
		maxD - minD < 0
	)
		return;

	let g = drawingContext.createConicGradient(startAngle, 0, 0);
	let g2 = drawingContext.createRadialGradient(0, 0, minD, 0, 0, maxD);

	let iMax = (endAngle - startAngle) / TWO_PI;

	g.addColorStop(0, colors[t % 2]);
	for (let i = 0; i <= iMax; i += iMax / num) {
		g.addColorStop(constrain(i, 0, 1), colors[t % 2]);
		g.addColorStop(constrain(i + iMax / num - 1 / 1000, 0, 1), colors[t % 2]);
		t++;
	}

	t = random() > 0.5 ? 0 : 1;

	g2.addColorStop(0, colors2[t % 2]);
	for (let i = 0; i <= num; i += 1) {
		g2.addColorStop(i / num, colors2[t % 2]);
		g2.addColorStop(constrain(i / num + 1 / num - 1 / 1000, 0, 1), colors2[t % 2]);
		t++;
	}
	if (isFront) {
		push();
		drawingContext.fillStyle = g2;
		noStroke();
		push();
		beginShape();
		for (let a = startAngle; a <= endAngle; a += TWO_PI / 360 / 2) {
			vertex(cos(a) * maxD, sin(a) * maxD);
		}
		for (let a = endAngle; a >= startAngle; a -= TWO_PI / 360 / 2) {
			vertex(cos(a) * minD, sin(a) * minD);
		}
		endShape(CLOSE);
		pop();
	}

	let d = minD + (maxD - minD) / 2;
	push();
	noFill();
	strokeCap(SQUARE);
	strokeWeight((maxD - minD) / 2);
	drawingContext.strokeStyle = g;
	arc(0, 0, d, d, startAngle, endAngle);
	pop();
	if (!isFront) {
		push();
		drawingContext.fillStyle = g2;
		noStroke();
		push();
		beginShape();
		for (let a = startAngle; a <= endAngle; a += TWO_PI / 360 / 2) {
			vertex(cos(a) * maxD, sin(a) * maxD);
		}
		for (let a = endAngle; a >= startAngle; a -= TWO_PI / 360 / 2) {
			vertex(cos(a) * minD, sin(a) * minD);
		}
		endShape(CLOSE);
		pop();
	}
	pop();
}

let colorScheme = [{
		name: "Benedictus",
		colors: ["#F27EA9", "#366CD9", "#5EADF2", "#636E73", "#F2E6D8"],
	},
	{
		name: "Cross",
		colors: ["#D962AF", "#58A6A6", "#8AA66F", "#F29F05", "#F26D6D"],
	},
	{
		name: "Demuth",
		colors: ["#222940", "#D98E04", "#F2A950", "#BF3E21", "#F2F2F2"],
	},
	{
		name: "Hiroshige",
		colors: ["#1B618C", "#55CCD9", "#F2BC57", "#F2DAAC", "#F24949"],
	},
	{
		name: "Hokusai",
		colors: ["#074A59", "#F2C166", "#F28241", "#F26B5E", "#F2F2F2"],
	},
	{
		name: "Hokusai Blue",
		colors: ["#023059", "#459DBF", "#87BF60", "#D9D16A", "#F2F2F2"],
	},
	{
		name: "Java",
		colors: ["#632973", "#02734A", "#F25C05", "#F29188", "#F2E0DF"],
	},
	{
		name: "Kandinsky",
		colors: ["#8D95A6", "#0A7360", "#F28705", "#D98825", "#F2F2F2"],
	},
	{
		name: "Monet",
		colors: ["#4146A6", "#063573", "#5EC8F2", "#8C4E03", "#D98A29"],
	},
	{
		name: "Nizami",
		colors: ["#034AA6", "#72B6F2", "#73BFB1", "#F2A30F", "#F26F63"],
	},
	{
		name: "Renoir",
		colors: ["#303E8C", "#F2AE2E", "#F28705", "#D91414", "#F2F2F2"],
	},
	{
		name: "VanGogh",
		colors: ["#424D8C", "#84A9BF", "#C1D9CE", "#F2B705", "#F25C05"],
	},
	{
		name: "Mono",
		colors: ["#D9D7D8", "#3B5159", "#5D848C", "#7CA2A6", "#262321"],
	},
	{
		name: "RiverSide",
		colors: ["#906FA6", "#025951", "#252625", "#D99191", "#F2F2F2"],
	},
];

void windowResized() {
	resizeCanvas(windowWidth, windowHeight);
}