var graph = function( p ) {
   var canvas1;
   p.setup = function() {
      p.createCanvas(650, 500);
      canvas1 = p.createGraphics(p.width, p.height);
   };

   p.draw = function(){
      canvas1.background(200);

      canvas1.line(170,50,100,170);
      canvas1.line(170,50,250,170);

      canvas1.line(250,170,180,290);
      canvas1.line(250,170,330,290);
      canvas1.line(250,170,480,290);

      canvas1.line(480,290,400,410);
      canvas1.line(480,290,550,410);

      canvas1.ellipse(170,50,100,100);

      canvas1.ellipse(100,170,100,100);
      canvas1.ellipse(250,170,100,100);

      canvas1.ellipse(180,290,100,100);
      canvas1.ellipse(330,290,100,100);
      canvas1.ellipse(480,290,100,100);

      canvas1.ellipse(400,410,100,100);
      canvas1.ellipse(550,410,100,100);

      canvas1.textSize(30);
      canvas1.text("World",130,60);
      canvas1.textSize(20);
      canvas1.text("Camera",60,180);
      canvas1.textSize(30);
      canvas1.text("Body",210,180);
      canvas1.text("Legs",145,300);
      canvas1.text("Tail",305,300);
      canvas1.text("Face",445,300);
      canvas1.text("Face",365,420);
      canvas1.text("Trunk",510,420);

      p.image(canvas1,0,0);
   };
};

var leg = function( p ) {
   var canvas1;
   p.setup = function() {
      p.createCanvas(650, 500);
      canvas1 = p.createGraphics(p.width, p.height);
   };

   p.draw = function(){
      canvas1.background(200);
   
      canvas1.fill(0);
      canvas1.line(200,100,300,250);
      canvas1.line(310,400,300,250);

      canvas1.noFill();

      canvas1.ellipse(200,100,20,20);
      canvas1.ellipse(300,250,20,20);
      canvas1.ellipse(310,400,20,20);

      
      p.image(canvas1,0,0);
   };
};

var motion = function(p){
   var canvas1;
   var clock;
   var state;
   p.setup = function(){
      p.createCanvas(400, 400);
      canvas1 = p.createGraphics(p.width, p.height);
      clock = 0;
      state = true;
   };

   p.draw = function(){
      canvas1.background(200);
      if( clock == 250 ){
         state = false;
      }else if( clock == 0 ){
         state = true;
      }
      if( state ){
         clock++;
      }else{
         clock--;
      }
      canvas1.ellipse(300-clock,300-f(clock),20,20);
      p.image(canvas1,0,0);
   }

   function f(x){
      return 60*Math.sin(x/80);
   };

};


var body = function( p ){

   p.setup = function(){
      p.createCanvas(400, 400,p.WEBGL);
   };

   p.draw = function(){
      p.background(200);
      p.ambientLight(200);
      p.pointLight(200,200,200,200,200,0);
      p.push();
      p.fill(200,50,50);
      p.specularMaterial(100);
      p.sphere(100);
      p.pop();

   };

};


var legs = function(p){

   p.setup = function(){
      p.createCanvas(400,400,p.WEBGL);
   }

   p.draw = function(){
      p.background(200);
      p.ambientLight(255);
      p.pointLight(200,200,200,200,200,0);
      p.specularMaterial(100);


      for(i=0;i<3;i++){
         p.push();
         p.translate(100,50+i*50,0);
         p.sphere(20);
         p.pop();

         p.push();
         p.translate(160,-50+i*50,-100);
         p.sphere(20);
         p.pop();

      p.push();
      p.translate(-100,50+i*50,0);
      p.sphere(20);
      p.pop();

      p.push();
      p.translate(-100,-50+i*50,-100);
      p.sphere(20);
      p.pop();
}

      
   }
};


var trunk = function( p ){

   p.setup = function(){
      p.createCanvas(400, 400,p.WEBGL);
   };

   p.draw = function(){
      p.background(200);
      p.ambientLight(255);
      p.pointLight(200,200,200,200,200,0);
      p.specularMaterial(100);
      p.rotateY( p.frameCount * 0.025 );

      p.push();
      p.translate(0,-100,0);
      p.sphere(30);
      p.pop();
      for(i=1;i<6;i++){
         p.push();
         p.translate(i*10,i*i*5-100,0);
         p.sphere(10);
         p.pop();
      }

   };

};

var tail = function( p ){

   p.setup = function(){
      p.createCanvas(400, 400,p.WEBGL);
   };

   p.draw = function(){
      p.background(200);
      p.ambientLight(255);
      p.pointLight(200,200,200,200,200,0);
      p.specularMaterial(100);
      p.rotateY( p.frameCount * 0.025 );

      for(i=1;i<6;i++){
         p.push();
         var y = Math.exp(i)-150;
         p.translate(i*20,y,0);
         p.sphere(10);
         p.pop();
      }

   };

};


var myp5_1 = new p5(graph, 'graph');
var myp5_2 = new p5(leg, 'leg_id');
var myp5_3 = new p5(tail, 'tail_id');
var myp5_4 = new p5(motion, 'motion_id');
var myp5_5 = new p5(legs, 'legs_id');
var myp5_6 = new p5(body, 'body_id');
var myp5_6 = new p5(trunk, 'trunk_id');
