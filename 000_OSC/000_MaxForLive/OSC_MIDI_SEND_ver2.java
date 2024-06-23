import oscP5.*;
//import netP5.*;
OscP5 o;
//NetAddress myRemoteLocation;

void setup() {

  size(640, 640);
  //stroke(255);
  //background(0);

  o = new OscP5(this, 12001);
  //myRemoteLocation = new NetAddress("127.0.0.1", 2346);

} 

//各OSCにて和音の場合複数のNoteで届く
// note: MIDIの番号
// velocity: 強度
int velocity1, velocity2, velocity3, velocity4, velocity5, velocity6, velocity7, velocity8 = 0;
int note1, note2, note3, note4, note5, note6, note7, note8  = 0;
int[] vels;
int[] notes;
float audio1, audio2, audio3, audio4, audio5, audio6, audio7, audio8  = 0;
int oscCount = 0;
int count = 0;

void draw() {
  
  //add----
  String root = "D:\\09_music_create\\03_PICTURE\\99_Render\\";
  String fileName = "69_Close-FF_" + "c_" + count + "_######.png";
  String outP = root + "000_OSC\01_ver1\\" + fileName;
    //----
   
  //add----
  print(count + ";");
  if (count >= 1800) noLoop();
  //----
  
  background(0);
  
  /*
  print(notes);
  for (int i=0; i < notes.length - 1; i++){
    print(notes[i]);
    //if ( notes[i] == 51 ) print("51");
  }
  */
  
  //基本1だけを利用する思考回路
  //fill(note2, note2*2, note2);
  noFill();
  stroke(255);
  if (note1 == 36) circle(300, 300, velocity1 * 10);
  if (note1 == 51) arc(50, 50, velocity1*2, 150, 0, HALF_PI);
  if (note1 == 52) square(350, 350, velocity1);
  
  count++;
  //saveFrame(outP);
}


void oscEvent(OscMessage theMsg) {
  
  oscCount++;
  //theMsg.print();

  //--Audio-------------------------------------------------
  if (theMsg.checkAddrPattern("/audio1")==true) {
    audio1 = theMsg.get(0).floatValue();
    audio1 = int(map(audio1, 0, 127, 0, 60));
  }
  if (theMsg.checkAddrPattern("/audio2")==true) {
      audio2 = theMsg.get(0).floatValue();
      audio2 = int(map(audio2, 0, 127, 0, 60));
  }
  if (theMsg.checkAddrPattern("/audio3")==true) {
      audio3 = theMsg.get(0).floatValue();
      audio3 = int(map(audio3, 0, 127, 0, 60));
  }

  if (theMsg.checkAddrPattern("/audio4")==true) {
      audio4 = theMsg.get(0).floatValue();
      audio4 = int(map(audio4, 0, 127, 0, 60));
  }

  //--MIDI-------------------------------------------------
  if(theMsg.checkAddrPattern("/Note1")==true) {
    //print("1");
    note1 = theMsg.get(0).intValue();
    //note1 = map(sq(note1), 1, sq(127), 0.05, 0.5);
    //note1 = int(map(note1, 0, 127, 0, 60));
  }

  if(theMsg.checkAddrPattern("/Note2")==true) {
      //print("2");
      note2 = theMsg.get(0).intValue();
      //note2 = int(map(note2, 0, 127, 0, 60));
  } 

  if(theMsg.checkAddrPattern("/Note3")==true) {
      note3 = theMsg.get(0).intValue();
      //note3 = int(map(note3, 0, 127, 0, 60));
  }

  if(theMsg.checkAddrPattern("/Note4")==true) {
      note4 = theMsg.get(0).intValue();
      //note4 = int(map(note4, 0, 127, 0, 60));
  } 

  if(theMsg.checkAddrPattern("/Note5")==true) {
      note5 = theMsg.get(0).intValue();
      //note5 = int(map(note5, 0, 127, 0, 60));
  }

  if(theMsg.checkAddrPattern("/Note6")==true) {
      note6 = theMsg.get(0).intValue();
      //note6 = int(map(note6, 0, 127, 0, 60));
  } 

  if(theMsg.checkAddrPattern("/Note7")==true) {
      note7 = theMsg.get(0).intValue();
      //note7 = int(map(note7, 0, 127, 0, 60));
  }

  if(theMsg.checkAddrPattern("/Note8")==true) {
      note8 = theMsg.get(0).intValue();
      //note8 = int(map(note8, 0, 127, 0, 60));
  } 
  
  /*
  notes = new int[8];
  notes[0] = note1;
  notes[1] = note2;
  notes[2] = note3;
  notes[3] = note4;
  notes[4] = note5;
  notes[5] = note6;
  notes[6] = note7;
  notes[7] = note8;
  print(notes + ";");
  */

  if(theMsg.checkAddrPattern("/Velocity1")==true) {
      velocity1 = theMsg.get(0).intValue();
      velocity1 = int(map(velocity1, 0, 127, 0, 60));
  }
  
  if(theMsg.checkAddrPattern("/Velocity2")==true) {
      velocity2 = theMsg.get(0).intValue();
      velocity2 = int(map(velocity2, 0, 127, 0, 60));
  }  
  
  if(theMsg.checkAddrPattern("/Velocity3")==true) {
      velocity3 = theMsg.get(0).intValue();
      velocity3 = int(map(velocity3, 0, 127, 0, 60));
  }  
  
    if(theMsg.checkAddrPattern("/Velocity4")==true) {
      velocity4 = theMsg.get(0).intValue();
      velocity4 = int(map(velocity4, 0, 127, 0, 60));
  }
  
  if(theMsg.checkAddrPattern("/Velocity5")==true) {
      velocity5 = theMsg.get(0).intValue();
      velocity5 = int(map(velocity5, 0, 127, 0, 60));
  }  
  
  if(theMsg.checkAddrPattern("/Velocity6")==true) {
      velocity6 = theMsg.get(0).intValue();
      velocity6 = int(map(velocity6, 0, 127, 0, 60));
  }  
  
  if(theMsg.checkAddrPattern("/Velocity7")==true) {
      velocity7 = theMsg.get(0).intValue();
      velocity7 = int(map(velocity7, 0, 127, 0, 60));
  }  
  
  if(theMsg.checkAddrPattern("/Velocity8")==true) {
      velocity8 = theMsg.get(0).intValue();
      velocity8 = int(map(velocity8, 0, 127, 0, 60));
  }  
  
  //vels = new int[8];
  
  
}

/*float log10 (int x) {
  return (log(x) / log(10));
}*/