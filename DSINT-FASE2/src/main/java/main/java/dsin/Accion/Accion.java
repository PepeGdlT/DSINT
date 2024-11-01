package main.java.dsin.Accion;

import main.java.dsin.Personaje.Personaje;

public abstract class Accion {
    private Personaje sujeto;

    public Accion(Personaje sujeto){
        this.sujeto = sujeto;
    }

    public Personaje getSujeto(){
        return sujeto;
    }
    public void setSujeto(Personaje sujeto){
        this.sujeto = sujeto;
    }

	@Override
	public String toString() {
		return "Accion [sujeto=" + sujeto + "]";
	}

    
}