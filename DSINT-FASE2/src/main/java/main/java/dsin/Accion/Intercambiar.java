package main.java.dsin.Accion;

import main.java.dsin.Objeto.Objeto;
import main.java.dsin.Personaje.Personaje;

public class Intercambiar extends Accion {

    private Objeto intercambiado;

    public Intercambiar(Personaje sujeto, Objeto intercambiado){
        super(sujeto);
        this.intercambiado = intercambiado;
    }

    @Override
    public String toString() {
        return super.toString() + " Intercambiar{" +
                "intercambiado=" + intercambiado +
                '}';
    }
}
