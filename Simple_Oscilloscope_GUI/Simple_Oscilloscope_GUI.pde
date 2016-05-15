import processing.serial.*;

Serial myPort;        // The serial port        // starting horizontal position of the graph           
float x; 
float y;
float large_space =  700/10;
float small_space =  700/100;
float voltage;
float displayVoltage; // location on screen to display voltage reading
int count = 0; // interrupt counter
PFont f;
int cursor1_yPos = height/2; // cursor 1 position
int cursor2_yPos = height/2; // cursor 2 position
int Cursor1 = 0;
int Cursor2 = 0;
int waitForRelease = 0; 
float [] voltageReadings = new float [101]; // array of voltage readings
boolean maskInterrupt = false; //masks interrupts from the serial port
int offset = 0; //offset of wave signal
int last_time;
boolean start_Reading = true; // boolean to indicate that we are reading the first
                              // voltage value
float frequency; 
float seconds;
boolean zero = false; // flag that is set when we read a 0 voltage signal


public void setup () {
  // set the window size:
  size(1000, 700);
  //initialize pFont variable to use Arial font and have size 20

  // List all the available serial ports
  // if using Processing 2.1 or later, use Serial.printArray()
  println(Serial.list());
  
  f = createFont("Arial", 30, true);

  // I know that the first port in the serial list on my mac
  // is always my  Arduino, so I open Serial.list()[0].
  // Open whatever port is the one you're using.
 // myPort = new Serial(this, Serial.list()[0], 9600);

  // don't generate a serialEvent() unless you get a newline character:
 // myPort.bufferUntil('\n');

  // set inital background:
  background(0);
}


public void draw () {
 
  drawGrid();
  //if(maskInterrupt){
  if (Cursor1 == 1|| Cursor2 == 1){ // if cursor 1 or cursor 2 is active, erase what's
                                    // on screen and render image continuously
   background(0);
   drawGrid();
   drawLines();
  }
  //when we want to clear the last array of voltage readings in preparation for
  //new readings
  if(Cursor1 == 0 && Cursor2 == 0 && maskInterrupt == true ){ 
              
    background(0);
    drawGrid();
    drawLines();
    maskInterrupt = false;
  }
  // display menu box
  rect(700, 0, 300, height);
  displayMenu();
  if(Cursor1 == 1){
    drawCursor1();
  }
  if(Cursor2 == 1){
    drawCursor2();
  }

}

/*************************************************************************************

function to draw the main oscilloscope grid 

*************************************************************************************/
public void drawGrid(){
  
    fill(255, 255, 0);
    stroke(0);
    strokeWeight(1);
    
    for(int i = 0; i < 10; i++){
      x = i*large_space;
      if(abs(350 - x) <= 10){
        x = 420;
      }
  
    for(int j = 0; j < 100; j++){
      y = j*small_space;
      if(abs(350 - y) <= 10){
        y = 420;
      }
      ellipse(x, y, 2, 2);
     }
   }
  
  
  for(int i = 0; i < 100; i++){
    x = i*small_space;
    if(abs(350 - x) <= 10){
      x = 420;
    }
    for(int j = 0; j < 10; j++){
      y = j*large_space;
      if(abs(350 - y) <= 10){
        y = 420;
      }
      ellipse(x, y, 2, 2);
    }
  }
  
  stroke(255, 255, 0);
  for(int i = 0; i < 100; i++){
    x = i*small_space;
    line(700/2 - 3, x, 700/2 + 3, x);
  }
  
  for(int i = 0; i < 100; i++){
    x = i*small_space;
    line(x, height/2 - 3, x, height/2 + 3);
  }
  
}

/*************************************************************************************

Function to draw the menu with cursor buttons 

*************************************************************************************/

public void displayMenu(){
  
  //set colour and thickness of edges
  fill(255);
  stroke(0);
  strokeWeight(10);
  rect(750, 50, 200, 100);
  fill(255, 100, 0);
  textSize(25);
  text("Cursor 1", 800, 105);
  
  fill(255);
  stroke(0);
  strokeWeight(10);
  rect(750, 300, 200, 100);
  fill(255, 100, 0);
  textSize(25);
  text("Cursor 2", 800, 355);
  
  // if mouse is hovering over the cursor 1 button it will change colour 
  
  if(mouseX >= 750 && mouseX <= 950 && mouseY >= 50 && mouseY <= 150){
  
    //set colour and thickness of edges
    fill(100);
    stroke(0);
    strokeWeight(10);
    rect(750, 50, 200, 100);
    fill(255, 100, 0);
    textSize(25);
    text("Cursor 1", 800, 105);
    //if the mouse is pressed, cursor 1 is activated and will be displayed
    if(mousePressed && (Cursor1 == 0) && waitForRelease == 0){
      Cursor1 = 1;
      waitForRelease = 1;
   }
   
  }
  // if the mouse is hovering over the second cursor button then it will change colour
  if(mouseX >= 750 && mouseX <= 950 && mouseY >= 300 && mouseY <= 400){
   
    //set colour and thickness of edges
    fill(100);
    stroke(0);
    strokeWeight(10);
    rect(750, 300, 200, 100);
    fill(255, 100, 0);
    textSize(25);
    text("Cursor 2", 800, 355);
    //if the mouse is pressed here it will activate cursor 2 and display it
    if(mousePressed && (Cursor2 == 0) && waitForRelease == 0){
       Cursor2 = 1;
       waitForRelease = 1;
    }
  }
  
  strokeWeight(1);
}

/*************************************************************************************

Funtion to display cursor 1, will display the associated voltage value along side
a line that extends horizontally across the oscilloscope grid, the cursor position
may be adjusted with the use of the up and down keys

*************************************************************************************/

public void drawCursor1(){
  
  //set colour and thickness
  fill(100);
  stroke(0);
  strokeWeight(10);
  rect(750, 50, 200, 100);
  fill(255, 100, 0);
  textSize(25);
  text("Cursor 1", 800, 105);
  if(keyPressed && key == BACKSPACE){
    offset += 2;
  }
  if(keyPressed && key == ENTER){
    offset -= 2;
  }
  if(keyPressed && keyCode == UP){
      cursor1_yPos -= 4;
   }else if(keyPressed && keyCode == DOWN){
      cursor1_yPos += 4;
   }
   //if the mouse is pressed here, the cursor will be deactivated 
   if(mousePressed == true && waitForRelease == 0 && mouseX >= 750 
   && mouseX <= 950 && mouseY >= 50 && mouseY <= 150){
      waitForRelease = 1;
      Cursor1 = 0;  
     
   }
   stroke(255, 255, 0);
   strokeWeight(3);
   //draw the cursor on screen
   line(0, cursor1_yPos, 700, cursor1_yPos);
   fill(255, 255, 0);
   //voltage value at cursor height
   text(String.format("%.2f", ( height/2 - cursor1_yPos)/35.0) + "V", 550, cursor1_yPos - 20);
   fill(255, 50, 0);
   text("Use", 770, 200);
   text("Keys To Move", 770, 270);
   stroke(255, 50, 0);
   
   line(850, 220, 850, 180);
   line(835, 200, 850, 180);
   line(865, 200, 850, 180);
   line(890, 220, 890, 180);
   line(875, 200, 890, 220);
   line(905, 200, 890, 220);
   
   //if both cursors activated, display the difference 
   if(Cursor2 == 1){
     drawDeltaVoltage();
   }
   
   strokeWeight(1);
   
}
/*************************************************************************************

Funtion to display cursor 1, will display the associated voltage value along side
a line that extends horizontally across the oscilloscope grid, the cursor position
may be adjusted with the use of the up and down keys

*************************************************************************************/

public void drawCursor2(){
 
  fill(100);
  stroke(0);
  strokeWeight(10);
  rect(750, 300, 200, 100);
  fill(255, 100, 0);
  textSize(25);
  text("Cursor 2", 800, 355); 
  if(keyPressed && key == BACKSPACE){
    offset += 4;
  }else if(keyPressed && key == ENTER){
    offset -= 4;
  }
  if(keyPressed && keyCode == RIGHT){
      cursor2_yPos -= 4;
  }else if(keyPressed && keyCode == LEFT){
      cursor2_yPos += 4;
  }
  //if mouse is pressed, cursor 2 is deactivated 
  if(mousePressed == true && waitForRelease == 0 && mouseX >= 750 
  && mouseX <= 950 && mouseY >= 300 && mouseY <= 400){
    waitForRelease = 1;
     Cursor2 = 0;
    
  }
  stroke(255, 255, 0);
  strokeWeight(3);
  //draw cursor at cursor2_yPos position on screen
  line(0, cursor2_yPos, 700, cursor2_yPos);
  fill(255, 255, 0);
  //display voltage reading on screen
  text(String.format("%.2f", ( height/2 - cursor2_yPos)/35.0) + "V", 550, cursor2_yPos - 20);
  
   fill(255, 50, 0);
   text("Use", 770, 450);
   text("Keys To Move", 770, 520);
   stroke(255, 50, 0);
   
   line(850, 440, 890, 440);
   line(870, 455, 890, 440);
   line(870, 425, 890, 440);
   line(850, 475, 890, 475); 
   line(870, 460, 850, 475);
   line(870, 490, 850, 475);
   
   
  
  strokeWeight(1);
  
}
/*************************************************************************************

Function to display the voltage difference on screen 

*************************************************************************************/

void drawDeltaVoltage(){
  
  textSize(25);
  
  strokeWeight(10);
  fill(170);
  stroke(0);
  rect(715, 570, 270, 100);
  fill(255, 50, 0);
  text("V1 - V2 = " + String.format("%.2f", ( height/2 - cursor1_yPos)/35.0 - ( height/2 - cursor2_yPos)/35.0) + "V", 740, 630);  
}
/*************************************************************************************

This Function is called whenever the mouse button is released after being pressed, 

changes the value of waitForRelease (used for debouncing)

*************************************************************************************/



public void mouseReleased(){
  
  waitForRelease = 0;
  
}
/*************************************************************************************

Function to draw the recorded voltage values as a sequence of lines on the screen
Also outputs the computed frequency

*************************************************************************************/


public void drawLines(){
  stroke(255, 255, 0);
   
   // for all values in voltageReadings[], displays a line connecting two 
   // consecutive voltage readings
   for(int i = 1; i < 101; i++){
     
     line((i-1)*small_space, voltageReadings[i - 1] - offset, (i)*small_space, voltageReadings[i] - offset);
   }
  
  fill(0, 0, 255);
  //outputs the calculated frequency on screen
  text(String.format("%.2f", frequency) + "Hz", 50, height - 50);
  fill(255, 255, 0);
  
}

/*************************************************************************************

Function that executes whenever there's an interr

*************************************************************************************/

void serialEvent (Serial myPort) {
  // if the interrupts aren't masked
  if(!maskInterrupt){
    //read until new input
    String inString = myPort.readStringUntil('\n');
    
    //parse the incoming string into a float
    voltage = (parseFloat(inString));

    //start the timer once a voltage reading above 0 is received
    if(voltage > 0.00 && start_Reading){
      last_time = millis();
      start_Reading = false;
      //if a zero is read, set the zero flag and wait till we receive a non-zero input
    } else if(voltage == 0.00)
        zero = true;
        //once we receive the next non-zero reading, use it to calculate the frequency by measuring the time difference
        //with millis();
      if(zero == true && voltage > 0.00){ 
        zero = false;
        if(millis() - last_time != 0){
        seconds = ((float) millis() - (float) last_time)*0.001;
        frequency = 1.0/seconds;
        }
        last_time = millis();
      }
    
    
    //map the voltage value within the range 0 - 5 to an appropriate range on screen
    
    displayVoltage = map(voltage, 0.0, 5.0, height/2, 3*height/4);
   
    //assign the next value in the array voltageReadings[count]
    voltageReadings[count] = height - displayVoltage;
   
   //once we have received 100 inputs, we need to render a new image on screen so set maskInterrupt to true
    if(count == 100){
      maskInterrupt = true;
    }
    if(count >= 100){
     //set count back to 0
     count = 0;
     }
    if (count == 0){
     voltageReadings[count] = height - displayVoltage;
    }
    //increment interrupt counter 
    count++;
    }
 }