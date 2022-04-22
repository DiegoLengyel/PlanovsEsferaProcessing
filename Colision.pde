class Colision {
  Bola Bola;
  Plano Plano;
  
	
  Colision() {
    Bola = new Bola(60, new PVector(width/2-200, height/2-150), new PVector(3, 2));
    Plano = new Plano(new PVector(width/2-150, height/2+150), new PVector(width/2+150, height/2));
  }

  void ejecutar() {		
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

  boolean preColision() {
    PVector sc = PVector.sub(Bola.pos, Plano.inicio);
    float distBolaCenterAndPlano = abs(sc.dot(Plano.normalUnit));
    if (distBolaCenterAndPlano <= Bola.d/2) {
      return true;
    } else {
      return false;
    }
  }

  boolean ColisionJ() {
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
	
  PVector rePositionBola(PVector previousPos) {
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
	
  PVector reVelocidadBola() {
    float n = -1 * Bola.vel.dot(Plano.normalUnit);
    PVector reVel = PVector.mult(Plano.normalUnit, n);
		reVel.mult(2);
    reVel.add(Bola.vel);
    return reVel;
  }

  void CPlano(int count) {
    Plano = new Plano(new PVector(width/2-150 + (40*count), height/2+150), new PVector(width/2+150, height/2));
  }
	
  void insertBola() {
    Bola = new Bola(60, new PVector(width/2-200, height/2-150), new PVector(3, 2));
  }	
}
