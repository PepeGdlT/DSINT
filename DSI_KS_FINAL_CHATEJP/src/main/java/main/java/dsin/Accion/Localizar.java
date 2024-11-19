package main.java.dsin.Accion;

import main.java.dsin.Objeto.Objeto;
import main.java.dsin.Personaje.Personaje;

public class Localizar extends Accion {

    private Objeto objLocalizado;
    private Personaje persLocalizado;

    public Localizar(Personaje sujeto, Objeto objLocalizado){
        super(sujeto);
        this.objLocalizado = objLocalizado;
    }

    public Localizar(Personaje sujeto, Personaje persLocalizado){
        super(sujeto);
        this.persLocalizado = persLocalizado;
    }
    
    
    

    public Objeto getObjLocalizado() {
		return objLocalizado;
	}

	public Personaje getPersLocalizado() {
		return persLocalizado;
	}

	@Override
    public String toString() {
        if (persLocalizado != null)
            return super.toString() + " Localizar{" +
                    ", persLocalizado=" + persLocalizado +
                    '}';
        else
            return super.toString() + " Localizar{" +
                    "objLocalizado=" + objLocalizado +
                    '}';
    }
}
