package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Balls;
import model.Balls.Direction;
import model.game;

class BallsTest {

	private game games;
	private ArrayList<Balls> nuevo  = new ArrayList<Balls>();
	
	private void setupEscenario() {
		games = new game(1,1);
		
		Direction m1 = Direction.Abajo;
		Direction m2 = Direction.Izquierda;
		Direction m3 = Direction.Derecha;
		Direction m4 = Direction.Arriba;
		Balls m = new Balls(12.5,13.5,15.6,0,m1,0,false);
		Balls m5 = new Balls(12.5,13.5,15.6,0,m2,0,false);
		Balls m6 = new Balls(1920.0,13.5,15.6,0,m3,0,false);
		Balls m7 = new Balls(12.5,1030.0,15.6,0,m4,0,false);
		nuevo.add(m);
		nuevo.add(m6);
		nuevo.add(m5);
		nuevo.add(m7);
		games.setBolas(nuevo);
	}
	
	@Test
	void testMovingBall() {
		setupEscenario();
		assertTrue(nuevo.get(0).isStop() == false);
		
	}
	
	@Test
	void testFuncional() {
		setupEscenario();
		nuevo.get(1).move(1920.01, 1030.0);
		assertTrue(nuevo.get(1).getPosX() < 1920.01);	
	}
	
	@Test
	void testStopBall() {
		setupEscenario();
		nuevo.get(0).stopBolita(nuevo.get(0).getPosX(), nuevo.get(0).getPosY());
		assertTrue(nuevo.get(0).isStop() == true);
	}
	
	@Test
	void testDistanceToBall() {
		setupEscenario();
		Double y = nuevo.get(1).distance(nuevo.get(1).getPosX(), nuevo.get(1).getPosY());
		Double z = nuevo.get(3).distance(nuevo.get(3).getPosX(), nuevo.get(3).getPosY());
		assertTrue(y <= z);
	}
	
	@Test
	void changeDirection() {
		setupEscenario();
		nuevo.get(2).move(1920.01, 1030.0);
		Direction m3 = Direction.Derecha;
		
		assertTrue(nuevo.get(2).getDireccion() == m3);

	}
	
	@Test
	void countBounces() {
		setupEscenario();
		nuevo.get(2).move(1920.01, 1030.0);
		assertTrue(games.countRebotes() == 1);
	}

	@Test
	void countStopBalls() {
		setupEscenario();
		assertTrue(games.countStopBalls() == false);
		
	}

}
