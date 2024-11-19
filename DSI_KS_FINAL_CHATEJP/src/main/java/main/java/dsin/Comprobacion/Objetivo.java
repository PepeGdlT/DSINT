package main.java.dsin.Comprobacion;

import main.java.dsin.Objeto.Objeto;
import main.java.dsin.Personaje.Personaje;

public class Objetivo {
    private Personaje sujeto;
    private Personaje afectado;
    private Objeto objeto;
    private String capacidadDe;

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
    
    //EL HERORE TIENE QUE POSEER CIERTA CAPACIDAD
    public Objetivo(Personaje sujeto, String capacidadDe){
        this.sujeto = sujeto;
        this.capacidadDe = capacidadDe;
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

	
	public String getCapacidadDe() {
		return capacidadDe;
	}

	
	@Override
	public String toString() {
		return "Objetivo [sujeto=" + sujeto + ", afectado=" + afectado + ", objeto=" + objeto + ", capacidadDe="
				+ capacidadDe + "]";
	}



	

	
	
    
	
}