package main.java.dsin.Accion;

import main.java.dsin.Personaje.Personaje;

public class Asesinar extends Accion {

    private Personaje asesinado;

    public Asesinar(Personaje sujeto, Personaje asesinado){
        super(sujeto);
        this.asesinado = asesinado;
     
    }

    @Override
    public String toString() {
        return super.toString() + " Asesinar{" +
                "asesinado=" + asesinado +
                '}';
    }

	public Personaje getAsesinado() {
		return asesinado;
	}

	public void setAsesinado(Personaje asesinado) {
		this.asesinado = asesinado;
	}
    
    
}
