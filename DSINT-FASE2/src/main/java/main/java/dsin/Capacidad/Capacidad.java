package main.java.dsin.Capacidad;

public class Capacidad {

	//BORRAR
	public String nombre;
	
	public Capacidad(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Capacidad [nombre=" + nombre + "]";
	}
	
	
}
