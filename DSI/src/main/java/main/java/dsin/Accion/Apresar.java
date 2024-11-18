package main.java.dsin.Accion;

import main.java.dsin.Personaje.Personaje;

public class Apresar extends Accion {

    private Personaje apresado;

    public Apresar(Personaje sujeto, Personaje apresado){
        super(sujeto);
        this.apresado = apresado;
    }

    

    public Personaje getApresado() {
		return apresado;
	}

    public String getNombre() {
    	return apresado.getNombre();
    }


	@Override
    public String toString() {
        return super.toString() + " Apresar{" +
                "apresado=" + apresado +
                '}';
    }
}
