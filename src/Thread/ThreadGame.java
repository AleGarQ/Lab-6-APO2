package Thread;
import Controller.MainController;
import javafx.application.Platform;
import model.*;

public class ThreadGame extends Thread {

	private Balls balls;
	private MainController m;
	
	
	public ThreadGame(Balls balls, MainController m) {
		this.balls = balls;
		this.m = m; 
		setDaemon(true);
	}  

	public void moveBall() {
		
		double maxX = m.darMayorWithd();
		double maxY = m.darMayorHeight(); 
		balls.move(maxX, maxY);	
	}


	@Override
	public void run() {

		while(balls.isStop() == false) {
			try {
				moveBall();
				if(m.getJuego().getNivel() == 1) {
				Thread.sleep(balls.getEspera());
				}else if(m.getJuego().getNivel() == 2) {
				Thread.sleep(balls.getEspera() -3);	
				}else if(m.getJuego().getNivel() == 3) {
				Thread.sleep(balls.getEspera() - 6);	
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
