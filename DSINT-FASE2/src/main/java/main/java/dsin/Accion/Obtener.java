package main.java.dsin.Accion;

import main.java.dsin.Objeto.Objeto;
import main.java.dsin.Personaje.Personaje;

public class Obtener extends Accion {

    private Objeto obtenido;

    public Obtener(Personaje sujeto, Objeto obtenido){
        super(sujeto);
        this.obtenido = obtenido;
    }

    @Override
    public String toString() {
        return super.toString() + " Obtener{" +
                "obtenido=" + obtenido +
                '}';
    }
}
