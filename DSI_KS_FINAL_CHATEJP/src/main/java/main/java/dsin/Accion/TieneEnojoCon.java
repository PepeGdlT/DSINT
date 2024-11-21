package main.java.dsin.Accion;

import main.java.dsin.Personaje.Personaje;

public class TieneEnojoCon extends Accion{
    

	private Personaje afectado;
    

	
    public TieneEnojoCon(Personaje sujeto, Personaje afectado) {
		super(sujeto);
		this.afectado = afectado;
	}


    // M�todo para obtener el afectado
    public Personaje getAfectado() {
        return afectado;
    }

	@Override
    public String toString() {
        return super.toString() + " TieneEnojoCon{" +
                "afectado=" + afectado +
                '}';
    }
}
