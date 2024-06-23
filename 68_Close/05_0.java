f = 0;
draw = (_) => {
  f || createCanvas(W = 400, W, WEBGL)
  noStroke(background(0))
  h=120
  P=PI
  i=P/32
  for(x=-P;x<P;x+=i){
    for(z=-P;z<P;z+=i){
      push(y = tan(W-mag(noise(x),noise(z))*f))
      scale(.75)
      translate(x*h,z*h,-y*h*3)
      pop(sphere(mag(noise(x),noise(z))*2))
    }
  }
  f+=i/8
}
