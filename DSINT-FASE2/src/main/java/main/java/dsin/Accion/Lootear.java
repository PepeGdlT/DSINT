package main.java.dsin.Accion;

import main.java.dsin.Personaje.Personaje;

public class Lootear {

    private Personaje loteado;

    public Lootear(Personaje sujeto, Personaje loteado){
    	super();
        this.loteado = loteado;
     
    }

	@Override
	public String toString() {
		return "Lootear [loteado=" + loteado + "]";
	}

	public Personaje getLoteado() {
		return loteado;
	}

	public void setLoteado(Personaje loteado) {
		this.loteado = loteado;
	}


    
}