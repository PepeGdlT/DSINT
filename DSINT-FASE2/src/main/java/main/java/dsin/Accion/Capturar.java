package main.java.dsin.Accion;

import main.java.dsin.Personaje.Personaje;

public class Capturar extends Accion {

    private Personaje capturado;

    public Capturar(Personaje sujeto, Personaje capturado){
        super(sujeto);
        this.capturado = capturado;
    }


    @Override
    public String toString() {
        return super.toString() + " Capturar{" +
                "capturado=" + capturado +
                '}';
    }
}
