Particle particles;
String[] colors = "#4059ad-#6b9ac4-#97d8c4-#eff2f1-#f4b942-#fff05a-#ff785a-#f24-#fff-#e01a4f-#f15946".split("-");

void setup() {
  pixelDensity(2)
  size(1400, 1400);
  fill(0)
  rect(0,0,width,height);
  //setTimeout(()=>{
  for(let i=0;i<200;i++){
    particles.push(new Particle({
      p: createVector(width/2,height/2),
      v: p5.Vector.random2D().mult(20)
    }))
  } 
  
  //},500)
}

void draw() {
  for (Particle p : particles) {
    p.update();
    p.draw();
  }
  particles = particles.filter(p=>p.alive);
  // circle(mouseX, mouseY, 20);
}