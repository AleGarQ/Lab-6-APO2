package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import Exceptions.puntajeVacio;
import model.Balls.Direction;

public class game implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int nivel;
	private int puntaje;
	private ArrayList<Balls> bolas;
	private ArrayList<Score> puntajes;
	public game(int nivel, int puntaje) {
		super();
		this.nivel = nivel;
		this.puntaje = puntaje;
		bolas = new ArrayList<Balls>();
		puntajes =new ArrayList<Score>();
		deserializableGame();
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	public ArrayList<Balls> getBolas() {
		return bolas;
	}
	public void setBolas(ArrayList<Balls> bolas) {
		this.bolas = bolas;
	}
	public ArrayList<Score> getPuntajes() {
		return puntajes;
	}
	public void setPuntajes(ArrayList<Score> puntajes) {
		this.puntajes = puntajes;
	} 
	
	public void ballGame(Double x,Double y) {
		
		for(int i = 0; i < bolas.size();i++) {
			bolas.get(i).stopBolita(x, y); 
		}
		
	}  
	
	public Double calculateScore() {
		Double m = 0.0;
		
		if(countRebotes() < 10) {
			if(nivel == 1) {
			m = (double) countRebotes() * 10 + (Math.random() + 1 * 100);
		    }else if(nivel == 2) {
		    m = (double) countRebotes() * 100 + (Math.random() + 1 * 1000);
		    }else if(nivel == 3) {
		    m = (double) countRebotes() * 1000 + (Math.random() + 1 * 10000);
		    }
		}else if(countRebotes() > 20) {
			if(nivel == 1) {
			m = (double) countRebotes() * 1 + (Math.random() + 1 * 100);
		    }else if(nivel == 2) {
		    m = (double) countRebotes() * 10 + (Math.random() + 1 * 1000);
		    }else if(nivel == 3) {
		    m = (double) countRebotes() * 100 + (Math.random() + 1 * 10000);
		    }	
		}
		return m;
	}
	
	public void addScore(String score) throws puntajeVacio {
		
		if(score.isEmpty()) {
			throw new puntajeVacio("");
		}else {
				Score m = new Score(calculateScore(),score);
				puntajes.add(m);
		}
		
	}
	
	public void readGame() {
		File fl = new File("files\\Juego.txt");
		try {
			FileReader fr = new FileReader(fl);
			BufferedReader br = new BufferedReader(fr);
			br.readLine();
			int a = Integer.parseInt(br.readLine()); 
			setNivel(a);
			br.readLine();
			String a1;
			while((a1 = br.readLine()) != null) {
				String[] b = a1.split(",");
				Double radio = Double.parseDouble(b[0]);
				Double posX = Double.parseDouble(b[1]);
				Double posY = Double.parseDouble(b[2]);
				int espera = Integer.parseInt(b[3]); 
				Direction direccion = Direction.valueOf(b[4]);
				int rebotes = Integer.parseInt(b[5]);
				Boolean stopped = Boolean.parseBoolean(b[6]);
				Balls m = new Balls(radio,posX,posY,espera,direccion,rebotes,stopped);
				bolas.add(m);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void serializableGame() {
		File fl = new File("files/Juego.dat");
		

		try {
			FileOutputStream  fos = new FileOutputStream(fl);
			ObjectOutputStream  oos = new ObjectOutputStream(fos);
			
			oos.writeObject(puntajes);
			oos.close();
			  
		}catch(IOException e) {
			e.getCause();
		}
		
	}
	
	public void deserializableGame() {
		File fl = new File("files/Juego.restrepo");
		ArrayList<Score> score1;
		if(!puntajes.isEmpty()) {
		try {
			FileInputStream fls = new FileInputStream(fl);
			ObjectInputStream ois = new ObjectInputStream(fls);
			 score1 = (ArrayList<Score>) ois.readObject();
			 setPuntajes(score1);
			 ois.close();		
		}catch(IOException e) {
			e.getCause();
		}catch(ClassNotFoundException e) {
			e.getCause();
		}
		}
	}
	
	public Boolean countStopBalls() {
		Boolean t = false;
		int m = 0;
		for(int i = 0; i < bolas.size() && !t;i++) {
			
			if(bolas.get(i).isStop() == true) {
			  m++;	
			  System.out.println(m);
			}
		}
		if(m >= bolas.size()) {
			t = true;
		}
		return t;
	}
 
	public int countRebotes() {
		int m = 0;
		for(int i = 0; i < bolas.size();i++) {
			m += bolas.get(i).getRobetes();
		}
		return m;
	}
	

	public void compareAllScoreInGame() {
		
		
		
	}
	

	public void ordenarPuntajes() {
		
		for(int i = 0; i < puntajes.size();i++) {
			for(int j = 0; j < puntajes.size()-1-i;j++) {
				if(puntajes.get(j).getPuntaje() > (puntajes.get(j+1).getPuntaje())) {
					Score own = puntajes.get(j);
					puntajes.set(j,puntajes.get(j+1));
					puntajes.set(j+1, own);		
				}
			}
		}	
	}
	

}
