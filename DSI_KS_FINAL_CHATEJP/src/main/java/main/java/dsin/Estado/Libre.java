package main.java.dsin.Estado;

import main.java.dsin.Personaje.Personaje;

public class Libre extends Estado{
	
	
	
    public Libre(Personaje afectado){
    	super(afectado);
    }

	@Override
	public String toString() {
		return "Libre [getAfectado()=" + getAfectado() + ", toString()=" + super.toString() + "]";
	}
    
    
}