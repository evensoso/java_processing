// All the code below is javascript that's compiled into a shader
// Find more info at www.shaderpark.com

export const shaderParkCode =  `
  int click = input();
	int buttonHover = input();
  
  void fbm(p, offset, rings) {
    return vec3(
      nsin(noise(p) * rings),
      nsin(noise(p+offset) * rings),
      nsin(noise(p+offset*2) * rings),
    )
  }

	float nscale = 1.2
	float nAmplitude = 0.02
	float hueOffset = 0.03
	float rings = 4
	float mixAmt = 1 - click

	float s = getSpace();
	float samplePos = s * nscale + vec3(0, 0, time) * .2
	float col = fbm(samplePos, hueOffset, rings);
	col = pow(col, vec3(4.2));
	color(col);
	rotateX(mouse.y*.1)
	rotateY(mouse.x*.1)
	shape(() => {
		rotateX(PI/2);
		torus(.8, .5);
		sphere(.28);
		mixGeo(mixAmt);
		mirrorN(3, .1);
		shape(() => {
			rotateX(time*.1)
			rotateZ(time*.1)
			boxFrame(vec3(.1), .005);
			sphere(.05);
		})()
	})();
`;