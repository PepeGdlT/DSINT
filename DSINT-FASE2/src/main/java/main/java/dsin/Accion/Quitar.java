package main.java.dsin.Accion;

import main.java.dsin.Objeto.Objeto;
import main.java.dsin.Personaje.Personaje;

public class Quitar extends Accion{
    private Objeto quitado;

    public Quitar(Personaje sujeto, Objeto quitado){
        super(sujeto);
        this.quitado = quitado;

    }
    
    
    
    public Objeto getQuitado() {
		return quitado;
	}



	@Override
    public String toString() {
        return super.toString() + " Quitar{" +
                "quitado=" + quitado +
                '}';
    }
}

