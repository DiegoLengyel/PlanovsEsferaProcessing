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

  void update() {
    pos.add(vel);
  }

  void display() {
    stroke(0);
    strokeWeight(2);
    fill(255, 150, 0);
    ellipse(pos.x, pos.y, d, d);
  }
	
}
