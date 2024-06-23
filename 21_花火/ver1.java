int N=540;										
float x,y,z,W,t = 0;										
int jVal = 9; //9										
float tVal = 0.03; //0.03										
										
void setup(){										
    size(1080,1080);										
    background(0);										
    noStroke();										
}										
										
void draw(){										
										
    t += 0.03;										
										
    for(int i=0; i<N; i++){										
        for(int j=0; j<N; j+=jVal){										
          										
            x = cos(W) + sin(i+x);										
            y = sin(W) + cos(i+y);										
            z = sin(i+x) + cos(i+y);										
            W = cos(i+z) + t;										
            float o = abs(z-0.5);										
            fill(i, z * N, j, W * exp(-o * 3));										
            circle(x * 99 + N, y * 99 + N, exp(o * 2) + 1);										
            										
        }										
    }										
    										
    //add----										
    String outP = "D:\\09_music_create\\03_PICTURE\\99_Render\\21_Lissajous\\1\\21_Penrose-30_######.png";										
    saveFrame(outP);										
    //----										
										
}										
