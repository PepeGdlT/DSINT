package main.java.dsin.Estado;

import main.java.dsin.Personaje.Personaje;

public abstract class Estado {
	
    private Personaje afectado;
    
    
    public Estado(Personaje afectado){
        this.afectado = afectado;
    }



	public Personaje getAfectado() {
		return afectado;
	}


	public void setAfectado(Personaje afectado) {
		this.afectado = afectado;
	}



	@Override
	public String toString() {
		return "Estado [afectado=" + afectado + "]";
	}


    

	
}

