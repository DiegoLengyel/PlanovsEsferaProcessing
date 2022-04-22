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
  PVector makeNormalUnit(PVector se) {
    PVector nu = se.get();
    nu.normalize();
    nu.rotate(-PI/2);
    return nu;
  }
  
  //Dibujar la linea de colisión
  void display() {
    stroke(0);
    strokeWeight(2);
    line(inicio.x, inicio.y, fin.x, fin.y);
  }
}
