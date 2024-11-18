package main.java.dsin.Accion;

import main.java.dsin.Personaje.Personaje;

public class Lootear extends Accion{

    private Personaje looteado;

    public Lootear(Personaje sujeto, Personaje looteado){
    	super(sujeto);
        this.looteado = looteado;
     
    }

	@Override
	public String toString() {
		return "Lootear [looteado=" + looteado + "]";
	}

	public Personaje getLooteado() {
		return looteado;
	}

	public void setLooteado(Personaje looteado) {
		this.looteado = looteado;
	}


    
}