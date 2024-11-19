package main.java.dsin.Estado;

import main.java.dsin.Personaje.Personaje;

public class Preso extends Estado{


    public Preso(Personaje afectado){
        super(afectado);
    }

	@Override
	public String toString() {
		return "Preso [getAfectado()=" + getAfectado() + ", toString()=" + super.toString() + "]";
	}

    
}
