package model;

public class Score  {

	
	private double puntaje;
	private String nombre;
	public Score(double puntaje, String nombre) {
		super();
		this.puntaje = puntaje;
		this.nombre = nombre;
	 
	}

	public double getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(double puntaje) {
		this.puntaje = puntaje;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
