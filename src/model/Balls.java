package model;



public class Balls {

	private Double radio;
	private Double posX;
	private Double posY;
	private int espera;
	private Direction direccion;
	private int robetes;
	private boolean stop;

public enum Direction{Derecha, Izquierda,Arriba,Abajo};

	public Balls(Double radio, Double posX, Double posY, int espera, Direction direccion, int robetes, boolean stop) {
	super();
	this.radio = radio;
	this.posX = posX;
	this.posY = posY;
	this.espera = espera;
	this.direccion = direccion;
	this.robetes = robetes;
	this.stop = stop;
}
	public Double getRadio() {
		return radio;
	}
	public void setRadio(Double radio) {
		this.radio = radio;
	}
	public Double getPosX() {
		return posX;
	}
	public void setPosX(Double posX) {
		this.posX = posX;
	}
	public Double getPosY() {
		return posY;
	}
	public void setPosY(Double posY) {
		this.posY = posY;
	}
	public int getEspera() {
		return espera;
	}
	public void setEspera(int espera) {
		this.espera = espera;
	}
	public Direction getDireccion() {
		return direccion;
	}
	public void setDireccion(Direction direccion) {
		this.direccion = direccion;
	}
	public int getRobetes() {
		return robetes;
	}
	public void setRobetes(int robetes) {
		this.robetes = robetes;
	}
	public boolean isStop() {
		return stop;
	}
	public void setStop(boolean stop) {
		this.stop = stop; 
	}
	
	public Double distance(Double x,Double y) {
    	double pow1 = Math.pow((posX - x), 2);
    	double pow2 = Math.pow((posY - y), 2);
    	pow1 = pow1 + pow2;
		return Math.sqrt(pow1);
	}

	public void stopBolita(Double x,Double y) {
		
		if(distance(x,y) <= radio) {
			stop = true;
		}
		
	}

	
	public void move(double maxX, double maxY) {
		double advance = 10;
		double radius = radio/2;
		switch(direccion) { 
			case Abajo:
				if(posY+advance+radius>maxY) {
					direccion = Direction.Arriba;
					robetes++;
					posY =  (maxY-radius);
				}else {
					posY =  (posY+advance);					
				}
				break;
			case Derecha:
				if(posX+advance+radius>maxX) {
					direccion = Direction.Izquierda;
					robetes++;
					posX =  (maxX-radius);
				}else {
					posX =  (posX+advance);					
				} 
				break;
			case Arriba:
				if(posY-advance-radius<0) {
					 
					direccion = Direction.Abajo;
					robetes++;
					posY =  radius;
				}else {
					posY =  (posY-advance);			
				}
				break;
			case Izquierda:
				if(posX-advance-radius<0) {
					direccion = Direction.Derecha;
					robetes++;
					posX =  radius;
				}else {
					posX =  (posX-advance);			
				}
				break;
			}
		}

	
}
