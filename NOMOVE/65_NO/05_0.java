const size0 = 4096
const urlParams = new URLSearchParams(window.location.search);
const custom_size = get_parameter("res", size0)
const WIDTH = window.innerWidth
const HEIGHT = window.innerHeight
const canvas_size = Math.min(WIDTH, HEIGHT)
const res = canvas_size / custom_size
const scaling = canvas_size / size0

// Features
const frame = fxrand() < 0.15
const colors = fxrand() < 0.05

// Editable parameters
const alpha = 0.4
const times = 4
const border = 0.2
const max_frames = 128
const amount = 1024


////////////////////
// Base Functions //
////////////////////


let field

window.setup = function() {
  print("Undulated by MathBird")
  print("https://twitter.com/MathBird_")
  colorMode(HSB, 1)
  pixelDensity(1 / res);
  createCanvas(canvas_size, canvas_size);
  set_colors()
  noStroke()

  field = new Field()
}

window.draw = function() {

  if (((frameCount % max_frames) == 0) || (frameCount == 1)) {
    field.points = []
    field.initialize_points(amount)
  }
  field.update()

  if (frameCount > max_frames * times) {
    noLoop()
    fxpreview()
  }
}


//////////////////////
// Custom functions //
//////////////////////

function fxrand() {
	return Math.random()
}

function get_parameter(param, base) {
  if (urlParams.get(param)) {
    return Number(urlParams.get(param))
  } else {
    return base
  }
}

function get_random_array(min, max, amount) {
  let ar = []
  for (let i = 0; i < amount; i++) {
    append(ar, min + fxrand() * (max - min))
  }
  return ar
}

function custom_circle(x, y, s) {
  circle(canvas_size * (border + x * (1 - 2 * border)), canvas_size * (border + y * (1 - 2 * border)), 4 * s)
}

function set_colors() {
  let hue = fxrand()
  let f_max = 0.1
  let f = colors ? f_max * (fxrand() + 1) / 2 : 1 - f_max * (fxrand() + 1) / 2

  background(hue, 1 - f, f)
  fill((hue + f) % 1, 1 - f, 1 - f, alpha)
}


////////////////////
// Custom classes //
////////////////////


class Field {
  constructor() {
    this.points = []
    this.m = round(8 + fxrand() * 4)
    this.a = get_random_array(- this.m, this.m, 32)
    this.b = get_random_array(- this.m, this.m, 32)
    this.c = get_random_array(- this.m, this.m, 32)
  }

  update() {
    for (let i = 0; i < this.points.length; i++) {
      this.points[i].update()
    }
  }

  initialize_points(n) {
    let points = []
    for (let i = 0; i < n; i++) {
      let x = fxrand() * 2 - 1
      let y = fxrand() * 2 - 1
      append(this.points, new Particle(x, y, this, true))
      append(this.points, new Particle(x, y, this, false))
    }
  }
}


class Particle {
  constructor(x, y, parent, forward) {
    this.point = new V2(x, y)
    this.forward = forward
    this.parent = parent
    this.step = 0.01 / this.parent.m
    this.step *= forward ? 1 : -1
  }

  update() {
    let point = this.move_point()
    if (frame) {
      this.point = fxrand() < 0.5 ? point : new V2(min(max(-1, point.x), 1), min(max(-1, point.y), 1)) 
    } else {
      this.point = new V2(min(max(-1, point.x), 1), min(max(-1, point.y), 1)) 
    }

    this.point.plot(1)
  }

  move_point() {
    let dx = this.get_delta(0)
    let dy = this.get_delta(1)
    
    return this.point.translate(dx * this.step, dy * this.step)
  }

  get_delta(i) {
    let value = 0
    let n = 8
    let x = this.point.x
    let y = this.point.y
    value += this.parent.a[0 + n * i] * sin(x * this.parent.b[0 + n * i] + this.parent.c[0 + n * i])
    value += this.parent.a[1 + n * i] * sin(y * this.parent.b[1 + n * i] + this.parent.c[1 + n * i])
    value += this.parent.a[2 + n * i] * cos(x * this.parent.b[2 + n * i] + this.parent.c[2 + n * i])
    value += this.parent.a[3 + n * i] * cos(y * this.parent.b[3 + n * i] + this.parent.c[3 + n * i])
    value += this.parent.a[4 + n * i] * x + this.parent.b[4 + n * i] * x**2 + this.parent.c[4 + n * i] * x**3
    value += this.parent.a[5 + n * i] * y + this.parent.b[5 + n * i] * y**2 + this.parent.c[5 + n * i] * y**3
    value += this.parent.a[6 + n * i] * x * y + this.parent.b[6 + n * i] * x**2 * y + this.parent.c[6 + n * i] * x * y**2
    value += this.parent.a[7 + n * i] * x**2 * y**2 + this.parent.b[7 + n * i] * x**2 * y**3 + this.parent.c[7 + n * i] * x**3 * y**2
    return value
  }
}

class V2 {
  constructor(x, y) {
    this.x = x;
    this.y = y;
  }

  plot(s) {
    custom_circle((1 + this.x) / 2, (1 + this.y) / 2, s * scaling)
  }

  translate(dx, dy) {
    return new V2(this.x + dx, this.y + dy)
  }

  distance(v1) {
    return sqrt((this.x - v1.x)**2 + (this.y - v1.y)**2)
  }
}