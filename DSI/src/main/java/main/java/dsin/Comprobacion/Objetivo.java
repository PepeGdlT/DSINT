package main.java.dsin.Comprobacion;

import main.java.dsin.Objeto.Objeto;
import main.java.dsin.Personaje.Personaje;

public class Objetivo {
    private Personaje sujeto;
    private Personaje afectado;
    private Objeto objeto;
    
    //EL HEROE TIENE QUE LIBERAR
    public Objetivo(Personaje sujeto, Personaje afectado){
        this.sujeto = sujeto;
        this.afectado = afectado;
    }
	
    //EL HERORE TIENE QUE POSEER CIERTO OBJETO
    public Objetivo(Personaje sujeto, Objeto objeto){
        this.sujeto = sujeto;
        this.objeto = objeto;
    }

	public Personaje getSujeto() {
		return sujeto;
	}


	public Personaje getAfectado() {
		return afectado;
	}
	
	public Objeto getObjeto() {
		return objeto;
	}

	
	@Override
	public String toString() {
		return "Objetivo [sujeto=" + sujeto + ", afectado=" + afectado + ", objeto=" + objeto + "]";
	}

	

	
	
    
	
}