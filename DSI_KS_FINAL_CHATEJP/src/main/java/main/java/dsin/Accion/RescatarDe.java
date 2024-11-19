package main.java.dsin.Accion;

import main.java.dsin.Personaje.Personaje;

public class RescatarDe extends Accion{
	private Personaje apresador;

	public RescatarDe(Personaje sujeto, Personaje apresador) {
		super(sujeto);
		this.apresador = apresador;
	}

	public Personaje getApresador() {
		return apresador;
	}

	public void setApresador(Personaje apresador) {
		this.apresador = apresador;
	}
	
	
	

}
