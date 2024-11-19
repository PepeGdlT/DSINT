package main.java.dsin.Estado;

import java.util.LinkedList;

import main.java.dsin.Personaje.Personaje;

public class Muerto extends Estado{
	
	
	
    public Muerto(Personaje afectado){
    	super(afectado);
    }

	@Override
	public String toString() {
		return "Muerto [getAfectado()=" + getAfectado() + ", toString()=" + super.toString() + "]";
	}
    
    
}
