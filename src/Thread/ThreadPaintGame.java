package Thread;


import Controller.MainController;

public class ThreadPaintGame extends Thread{

	private MainController m;
	public ThreadPaintGame( MainController m) {
		super(); 
		this.m = m;
		setDaemon(true);
	}
	
	public void run() {
		try {
		while(!m.checkFinishGame()) {
			m.paintBall();
			Thread.sleep(2);
		}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
	}
	
	
}
