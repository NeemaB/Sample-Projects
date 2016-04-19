/* need to change the methods for the pipe object namely pipe.prototype.update and pipe.prototype.draw so that they are of the right format and work for both the upPipes and downPipes arrays */


// draw bird
boolean press = false;
boolean click = false;
boolean endgame = false;
int time;
int difficulty = 2;
int h;
//tally for number of gates gone through
int num = 0;

public class bird {
  
  PVector position;
  PVector velocity;
  PVector acceleration;
  float angle;
  
  void Draw(){
    //adjusts the angle for each instance the bird is drawn
    
    resetMatrix();
    pushMatrix();
    translate(this.position.x, this.position.y);
    ellipseMode(CENTER);
    rotate(this.angle);
    stroke(0, 0, 0);
    fill(255, 200, 0);
    ellipse(0, 0, 50, 40);
    fill(100, 0, 0);
    ellipse( 15, -10, 5, 5);
    ellipse( -5,  -10, 5, 5);
    fill(0);
    ellipse(10, 10, 10, 10);
    popMatrix();
    
  }
  public bird() {
    
    this.position = new PVector(100, 200);
    this.velocity = new PVector(0, 0);
    this.acceleration = new PVector(0, 0.3);
    this.angle = 0;
    
  }
  
  void refresh(){
    
    this.position.set(100, 200);
    this.velocity.set(0, 0);
  }
  
  void update(){
    
    if (keyPressed && keyCode == UP && press == false){
        this.velocity.y = -5; 
        
        press = true;
    }
    this.angle = radians(this.velocity.y * 5);
    //constrains the angle within two values for all positions that the bird might be in
    this.angle = constrain(this.angle, -2, 2);
    this.velocity.add(this.acceleration);
    this.position.add(this.velocity);
    // every time the user "taps" the up arrow the y velocity changes
    
  }
}

bird Bird;

public class Pipe {
    
  int y;
  int x;
  int width;
  int height;
  
  public Pipe(int x, int y, int height){
    this.y = y;
    this.x = x;
    this.width = 20;
    this.height = height;
    
  }
  
  void update(){
    this.x -= 4;
    
    
     if(this.x + 50 <= 0){
        this.height = round(random(50, 180));
        h = this.height;
        this.x = 550;
     }
  }
   
  void downdate (int h){
    this.x -= 4;
    
     if (this.x + 50 <= 0){
         this.x = 550;
         this.y = h + 140;
         this.height = (400 - (h + 140));
     }
  }
  void updraw() {
    
    stroke(0);
    strokeWeight(5);
    fill(0, 200, 0);
    rectMode(CORNER);
     
    rect(this.x, this.y, 50, this.height);
    rect(this.x - 15, this.height, 80, 10);
  }
  
  void downdraw(){
   
    stroke(0);
    strokeWeight(5);
    fill(0, 200, 0);
    rectMode(CORNER);
    
    rect(this.x, this.y, 50, this.height);
    rect(this.x - 15, this.y - 10, 80, 10);
  }
  
 
}

void restartPipes(){
 
    float randomVal = random(0, 2);
    int Val = round(randomVal);
    
    for(int i = 0; i < 3; i++){
        
        if(Val > 2){
            Val = 0;
        }
        upPipes[Val].x = 400 + i*200; 
        downPipes[Val].x = 400 + i*200;
        Val++;

    }
}

Pipe [] upPipes = new Pipe[3];
Pipe [] downPipes = new Pipe[3];


public class Cloud{
  
  float x, y;
  
  Cloud(float x, float y){
   
    this.x = x;
    this.y = y;
  }
    
  void update(){
    
     this.x -= 3;
     if (this.x + 35 <= 0){
        this.x = 435;
      }
   }
  
  void draw(){
    
    noStroke();
    ellipseMode(CENTER);
    fill(255, 255, 255);
    ellipse(this.x, this.y, 20, 18);
    ellipse(this.x + 12, this.y, 17, 12);
    ellipse(this.x - 12, this.y, 17, 12);
    
  }

}

Cloud [] clouds = new Cloud[9];
    
public class StartWindow{
  
  int xSize;
  int ySize;
  int bright;
  
  //initial game window and text
  StartWindow(int xSize, int ySize, int brightness){
    
    this.xSize = xSize;
    this.ySize = ySize;
    this.bright = brightness;
  }
  
  void update(){
   
    if (mouseX <= (width/2 + this.xSize/2) && mouseX >= (width/2 - this.xSize/2) && mouseY <= (height/2 +this.ySize/2) && mouseY >= (height/2 - this.ySize/2)){
        this.xSize += 40;
        this.xSize = constrain(this.xSize, 250, 250);
        this.ySize += 40;
        this.ySize = constrain(this.ySize, 250, 250);
        this.bright += 30;
        this.bright = constrain(this.bright, 200, 230);
   
    }else {
        this.xSize = 200;
        this.ySize = 200;
        this.bright = 200;
    }
    
    if (mouseX <= (width/2 + this.xSize/2) && mouseX >= (width/2 - this.xSize/2) && mouseY <= (height/2 +this.ySize/2) && mouseY >= (height/2 - this.ySize/2) && mousePressed){
        click = true;
    }
    
  }
  
  void draw(){
    
    fill(this.bright, this.bright, this.bright);
    rectMode(CENTER);
    rect(width/2, height/2, this.xSize, this.ySize);
    fill(255, 100, 0);
    textSize(24);
    textAlign(LEFT, BOTTOM);
    text("START", width/2 - 40 , height/2 - 27, 100, 100);
  }
    
}

StartWindow start;


void drawTally() {
    fill(255, 56, 56);
    textSize(40);
    text(num, width/2, 70);
}

//end game condition
public void endGame(){
   
    for(int i = 0; i < upPipes.length; i++){
        if (((Bird.position.x + 25) >= upPipes[i].x && (Bird.position.y - 20) <= upPipes[i].height && (Bird.position.x + 25) <= (upPipes[i].x + 50)) 
        || ((Bird.position.x - 25) >= upPipes[i].x && (Bird.position.x - 25) <= (upPipes[i].x + 50) && (Bird.position.y - 20) <= upPipes[i].height) 
        || Bird.position.y >= 350)
        {
            //playSound(getSound("rpg/hit-whack"));
            endgame = true;
        }
        
        if ((Bird.position.x + 25) >= downPipes[i].x && (Bird.position.y + 20) >= downPipes[i].y && (Bird.position.x + 25) <= (downPipes[i].x + 50) || ((Bird.position.x - 25) >= downPipes[i].x && (Bird.position.x - 25) <= (downPipes[i].x + 50) && (Bird.position.y + 20) >= downPipes[i].y) ||               Bird.position.y >= 350)
        {
            //playSound(getSound("rpg/hit-whack"));
            endgame = true;
        }
    }
}



//main game draw function

public void keyReleased(){
    press = false;
};


void setup(){
  
  size(400, 400);
  
  for(int i = 0; i < 3; i++){
    
    upPipes[i] = new Pipe(400 + i*200, 0, round(random(50, 180)));
    downPipes[i] = new Pipe(400 + i*200, upPipes[i].height + 160, 400 - (upPipes[i].height + 160));
  }
  
  for (int i = 0; i <= 8; i++){
    float x = lerp(0, 400, i/8.0);
    float y = 50;
    clouds[i] = new Cloud(x, y);
  }
  
  start = new StartWindow(300, 200, 200);
  Bird = new bird();
  PFont font = createFont("cursive", 30);
  textFont(font);
  
}

void draw() {
        
         background(0, 150, 255);
         rectMode(CORNER);
         for(int i = 0; i < clouds.length; i++){
             clouds[i].update();
             clouds[i].draw();
             
         }
         fill(200, 150, 50);
         rect(0, 350, 400, 50);
         
         if (click == false && endgame == false){
             start.draw();
             start.update();
             
         }
         if (click == true && endgame == false){
             Bird.update();
             Bird.Draw();
             for(int i = 0; i < upPipes.length; i++){
                 
                 
                 upPipes[i].update();
                 upPipes[i].updraw();
                 downPipes[i].downdate(h);
                 downPipes[i].downdraw();
                 if(Bird.position.x <= downPipes[i].x && abs(Bird.position.x - downPipes[i].x) <= 2 && Bird.position.y < downPipes[i].y && Bird.position.y > upPipes[i].height){
                    num++;
                    //playSound(getSound("retro/coin"));
                 
                }
    
                 
            }
            endGame();
            
            drawTally();
         
         }//end game window and text
         if (endgame){
            endScreen();
         }
};   
    
void endScreen(){
  
  press = true;
  Bird.update();
  Bird.Draw();
  fill(200, 200, 200);
            
  rectMode(CENTER);
  rect(width/2, height/2, 350, 270);
  fill(255, 255, 255);
            
  rect(width/2, height/2 + 60, 130, 100);
  fill(255, 0, 0);
           
  textSize(30);
  textAlign(CENTER);
  text("Your Score: " + num, width/2, height/2 - 50);
            
  textSize(20);
  text("Press UP", width/2, height/2 + 58);
  text("to Restart", width/2, height/2 + 80);
            
            
  if(keyPressed && keyCode == UP){
                
      Bird.refresh();
      restartPipes();
      num = 0;
      endgame = false;
      click = false;
                
  }
     
}