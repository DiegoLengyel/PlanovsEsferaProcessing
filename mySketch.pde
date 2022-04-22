Colision obj;

void setup() {
  size(400, 400); 
  obj = new Colision();
}

void draw() {
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
