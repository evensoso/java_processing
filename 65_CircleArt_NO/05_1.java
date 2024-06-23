// licensed with CC BY-NC-SA 4.0 https://creativecommons.org/licenses/by-nc-sa/4.0/
// @garabatospr


int colors = ["#226699", "#dd2e44", "#ffcc4d","#FFFFFF"];

int shapes = [];

int border = 0;

void setup() {
	createCanvas(1000,1000);
	rectMode(CENTER);
	ellipseMode(CENTER);
	colorMode(HSB, 360, 100, 100);
	
	fill(0);
	square(width/2,height/2,width);
	
	int mySize = 200;
	


	for(int x = mySize; x <= width - mySize;x+=mySize)
	{
		for(int y=mySize;y <= height - mySize;y+=mySize)
		{
		for(int i=0;i < 5;i++)
		{
			//if (random(0,1) > 0.5)
			{
			 shapes.push(new shape(x,y,4*mySize));
			}
		}
		}
	}
	
	// for(int i=0;i < 5;i++)
	// 	{
	// 		//if (random(0,1) > 0.5)
	// 		{
	// 		 shapes.push(new shape(width/2,height/2,mySize*2));
	// 		}
	// 	}
	

	

	
}

void draw() {
	
	for(int i=0;i < shapes.length-1;i++)
	{
	  shapes[i].display();
    shapes[i].move();
	}
	
	if (frameCount > 500)
	{
	 noLoop();
	}
}

void shape(centerX,centerY,range)
{
  this.x = centerX;
	this.y = centerY; 
	this.range = range;
	this.sizeX = 50;
	this.split = 50;
	this.sizeY = this.sizeX;
	this.dirX = random([-1,0,1]);
	this.dirY = random([-1,0,1]);
	this.rad = 7;
	this.speed = 0.5;
	this.color = generateColor();
	this.w = 0.1;
  //this.color = color("red");
	
	

	this.move = void()
	{

	  this.x += this.dirX*this.speed*this.rad*cos(frameCount*0.1);
	  this.y += this.dirY*this.speed*this.rad*sin(frameCount*0.1); 
		
		if ((frameCount % this.split) == 0)
		{
			//this.color = generateColor();
			if (this.sizeX > 1)
			{
			  this.sizeX /= 1.5;
		    this.sizeY /= 1.5;
			}
			this.dirX = random([-1,0,1]);
	    this.dirY = random([-1,0,1]);
		}
	}
		
	this.display = void()
	{
		this.x = constrain(this.x,centerX - this.range,centerX + this.range);
		this.y = constrain(this.y,centerY - this.range,centerY + this.range);
		noFill();
		strokeWeight(this.w);
		stroke(this.color);
		ellipse(this.x,this.y,this.sizeX,this.sizeY);
	}

	
}

void generateColor(){
	return colors[floor(random(0,colors.length))];
}



