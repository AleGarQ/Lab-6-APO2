package model;

import java.io.Serializable;

public class Score implements Serializable {
	private int puntaje;
	private String nombre;
	private int nivel;
	
	public Score(int puntaje, String nombre,int nivel) {
		super();
		this.puntaje = puntaje;
		this.nombre = nombre;
		this.setNivel(nivel);
	}

	public int getPuntaje() {
		return puntaje;
	}
	
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}


	public String toString() {
		String msj = "";
		
		msj += "El puntaje es:" + puntaje + " " + "\n";
		msj += "El nombre es:" + nombre + " " + "\n";
		msj += "El nivel es:" + nivel + " " + "\n";
		
		return msj;
		
	}

}
