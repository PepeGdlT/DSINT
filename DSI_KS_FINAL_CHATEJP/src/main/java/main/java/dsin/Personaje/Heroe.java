package main.java.dsin.Personaje;
import java.util.LinkedList;

public class Heroe extends Personaje{
	public Heroe(String nombre) {
		super(nombre);

	}

	@Override
	public String toString() {
		return "Heroe [getNombre()=" + getNombre() + ", toString()=" + super.toString() + "]";
	}


	
}
