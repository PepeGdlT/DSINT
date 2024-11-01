package main.java.dsin.Accion;

import main.java.dsin.Objeto.Objeto;
import main.java.dsin.Personaje.Personaje;

public class Poseer extends Accion {

    private Objeto poseido;

    public Poseer(Personaje sujeto, Objeto poseido){
        super(sujeto);
        this.poseido = poseido;

    }
    
    
    
    public Objeto getPoseido() {
		return poseido;
	}



	@Override
    public String toString() {
        return super.toString() + " Poseer{" +
                "poseido=" + poseido +
                '}';
    }
}