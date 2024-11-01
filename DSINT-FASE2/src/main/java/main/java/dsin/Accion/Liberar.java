package main.java.dsin.Accion;

import main.java.dsin.Personaje.Personaje;

public class Liberar extends Accion {

    private Personaje liberado;

    public Liberar(Personaje sujeto, Personaje liberado){
        super(sujeto);
        this.liberado = liberado;
    }

    @Override
    public String toString() {
        return super.toString() + " Liberar{" +
                "liberado=" + liberado +
                '}';
    }
}
