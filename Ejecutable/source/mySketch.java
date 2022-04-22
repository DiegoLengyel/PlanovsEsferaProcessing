/* autogenerated by Processing revision 1282 on 2022-04-22 */
import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class mySketch extends PApplet {

Colision obj;

 public void setup() {
  /* size commented out by preprocessor */; 
  obj = new Colision();
}

 public void draw() {
  background(255);
  fill(0);
  textSize(25);
  text("Pelota vs Línea (ángulo de rebote)", 0 , 20);
  obj.ejecutar();
  //Refresco de la esfera y linea
  if(frameCount % 120 == 0) {
    obj.insertBola();
    obj.CPlano((frameCount/120)%10); //Aquí vamos moviendo de a pocos la línea
  }
}
//Creamos la clase de la bola

class Bola {

  float d;
  PVector pos;
  PVector vel;

  Bola(float d_, PVector pos_, PVector vel_) {

    d = d_;
    pos = pos_.get();
    vel = vel_.get();
  }

   public void update() {
    pos.add(vel);
  }

   public void display() {
    stroke(0);
    strokeWeight(2);
    fill(255, 150, 0);
    ellipse(pos.x, pos.y, d, d);
  }
	
}
class Colision {
  Bola Bola;
  Plano Plano;
  
	
  Colision() {
    Bola = new Bola(60, new PVector(width/2-200, height/2-150), new PVector(3, 2));
    Plano = new Plano(new PVector(width/2-150, height/2+150), new PVector(width/2+150, height/2));
  }

   public void ejecutar() {		
    PVector previousPos = Bola.pos.get();
    Bola.update();    
	  boolean preColision = preColision();    
    if (preColision) {      
      boolean Collision = ColisionJ();      
      if (Collision) {        
        Bola.pos = rePositionBola(previousPos);
        Bola.vel = reVelocidadBola();
      }			
    }    
    Bola.display();
    Plano.display();
  }

   public boolean preColision() {
    PVector sc = PVector.sub(Bola.pos, Plano.inicio);
    float distBolaCenterAndPlano = abs(sc.dot(Plano.normalUnit));
    if (distBolaCenterAndPlano <= Bola.d/2) {
      return true;
    } else {
      return false;
    }
  }

   public boolean ColisionJ() {
    float a = Plano.se.magSq();
    PVector cs = PVector.sub(Plano.inicio, Bola.pos);
    float b = Plano.se.x * cs.x + Plano.se.y * cs.y;
    float c = cs.magSq() - (Bola.d/2 * Bola.d/2);
    float t1 = (-b + sqrt(b*b - a*c))/a;
    float t2 = (-b - sqrt(b*b - a*c))/a;
    if((t1 >= 0 && t1 <= 1) || (t2 >=0 && t2 <= 1)) {
      return true;
    } else {
      return false;
    }		
  }
	
   public PVector rePositionBola(PVector previousPos) {
    PVector se = PVector.sub(Bola.pos, previousPos);
		println("Bola.pos = " + Bola.pos);
		println("previousPos = " + previousPos);
		println("se = " + se);
    float a = se.dot(Plano.normalUnit);
    PVector BolaAPlano = PVector.sub(previousPos, Plano.inicio);
    float b = Bola.d/2 - (BolaAPlano.dot(Plano.normalUnit));
    float t = b/a;

    if ((t < 0) || (t > 1)) {
      b = -1 * Bola.d/2 - (BolaAPlano.dot(Plano.normalUnit));
      t = b/a;
    }

    se.mult(b/a);
    PVector rePos = PVector.add(previousPos, se);
    return rePos;
  }
	
   public PVector reVelocidadBola() {
    float n = -1 * Bola.vel.dot(Plano.normalUnit);
    PVector reVel = PVector.mult(Plano.normalUnit, n);
		reVel.mult(2);
    reVel.add(Bola.vel);
    return reVel;
  }

   public void CPlano(int count) {
    Plano = new Plano(new PVector(width/2-150 + (40*count), height/2+150), new PVector(width/2+150, height/2));
  }
	
   public void insertBola() {
    Bola = new Bola(60, new PVector(width/2-200, height/2-150), new PVector(3, 2));
  }	
}
class Plano {
  PVector inicio;
  PVector fin;
  PVector se;
  //Vector normal
  PVector normalUnit;
  
  //Sacamos el vector normal de la linea
  Plano(PVector s, PVector e) {
    inicio = s;
    fin = e;
    se = PVector.sub(fin, inicio);
    normalUnit = makeNormalUnit(se);   
  }
  
  //Usamos el vector normal para la colisión
   public PVector makeNormalUnit(PVector se) {
    PVector nu = se.get();
    nu.normalize();
    nu.rotate(-PI/2);
    return nu;
  }
  
  //Dibujar la linea de colisión
   public void display() {
    stroke(0);
    strokeWeight(2);
    line(inicio.x, inicio.y, fin.x, fin.y);
  }
}


  public void settings() { size(400, 400); }

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "mySketch" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}