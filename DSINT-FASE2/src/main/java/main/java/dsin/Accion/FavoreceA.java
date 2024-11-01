package main.java.dsin.Accion;

import main.java.dsin.Personaje.Personaje;

public class FavoreceA extends Accion{
    private Personaje favorecido;


    public FavoreceA(Personaje sujeto, Personaje favorecido){
        super(sujeto);
        this.favorecido = favorecido;
      
    }
    


    public Personaje getFavorecido() {
		return favorecido;
	}



	@Override
    public String toString() {
        return 	" FavoreceA{" +
        		"sujeto=" + getSujeto() + 
                "favorecido=" + favorecido + 
                '}';
    }
}
