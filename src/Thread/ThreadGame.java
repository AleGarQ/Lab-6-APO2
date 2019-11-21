package Thread;
import Controller.MainController;
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
				Thread.sleep(balls.getEspera());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
