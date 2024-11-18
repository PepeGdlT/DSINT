package main.java.dsin.Objeto;


public class Objeto{
	
    private String nombre;
    private String capacidadDe;

	
    public Objeto(String nombre, String capacidadDe) {
    	this.nombre = nombre;
    	this.capacidadDe = capacidadDe;
    }
    
    public boolean matchesName(String name) {
        return this.nombre.equals(name);
    }
	
    public Objeto(String nombre) {
    	this.nombre = nombre;
    }
    
    public String getCapacidadDe() {
		return capacidadDe;
	}
	public void setCapacidadDe(String capacidadDe) {
		this.capacidadDe = capacidadDe;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Objeto [capacidadDe=" + capacidadDe + ", nombre=" + nombre + "]";
	}






    
}
