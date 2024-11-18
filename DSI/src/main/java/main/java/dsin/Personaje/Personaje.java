package main.java.dsin.Personaje;

import java.util.LinkedList;

public abstract class Personaje {
	private String nombre;
	
	
	
	public Personaje(String nombre) {
		this.nombre = nombre;
	}
	

	
	//metodos get y set
	public String getNombre() {
		return nombre;
	}
	
	public boolean matchesName(String name) {
	    return this.nombre.equals(name);
	}



	@Override
	public String toString() {
		return "Personaje [nombre=" + nombre + "]";
	}
	
	
	
}
