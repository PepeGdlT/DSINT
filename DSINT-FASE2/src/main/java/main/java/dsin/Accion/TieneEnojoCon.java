package main.java.dsin.Accion;

import main.java.dsin.Personaje.Personaje;

public class TieneEnojoCon extends Accion{
    

	private Personaje enojado;
    

	
    public TieneEnojoCon(Personaje sujeto, Personaje enojado) {
		super(sujeto);
		this.enojado = enojado;
	}


    // Método para obtener el afectado
    public Personaje getEnojado() {
        return enojado;
    }

	@Override
    public String toString() {
        return super.toString() + " TieneEnojoCon{" +
                "afectado=" + enojado +
                '}';
    }
}
