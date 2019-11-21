package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Exceptions.puntajeVacio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.*;
import Thread.*;

public class MainController implements Initializable {

	private game juego;

	private Canvas canvas;
	@FXML
	private MenuItem m;
	@FXML
	private MenuItem m1;
	@FXML
	private MenuItem m2;
	@FXML
	private BorderPane pane;
	@FXML
	private Pane pane1;

	
	private GraphicsContext gt;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
	}
	
	public void loadGame(ActionEvent e) {
		pane1.getChildren().clear();
		juego = new game(0,0);
		juego.readGame();
		Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
		pane1.setPrefSize(visualBounds.getWidth(), visualBounds.getHeight());
		canvas = new Canvas(pane1.getPrefWidth(),pane1.getPrefHeight());
		gt = canvas.getGraphicsContext2D();
		pane1.getChildren().add(canvas);
		createBalls(juego.getBolas());
		canvas.setOnMouseClicked(f -> ballPosition(f));
	}
	public void ballPosition(MouseEvent e) {
		try {
		Double x = e.getSceneX();
		Double y = e.getSceneY();
		
		juego.ballGame(x, y);
		
		if(juego.countStopBalls() == true) {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Ganaste!!");
			dialog.setHeaderText("Detuviste todas las bolas en record");
			dialog.setContentText("Por favor escribe tu nombre:");
			dialog.showAndWait();
			TextField msj = dialog.getEditor();
			String msj1 = msj.getText();
			System.out.println(msj1);
			juego.addScore(msj1);
		}
		}catch(puntajeVacio e1) {
			Alert gameOver = new Alert(AlertType.INFORMATION);
			gameOver.setTitle("Game Over!");
			gameOver.setHeaderText("No puedes poner el puntaje vacio!");
			gameOver.showAndWait();
		}
	}
	
	public void createBalls(ArrayList<Balls> m) {
		
		for(int i = 0; i < m.size();i++) {
			ThreadGame e = new ThreadGame(m.get(i), this);
			e.start();			
		}
		
		
			ThreadPaintGame e1 = new ThreadPaintGame(this);
			e1.start();
		
	}
	
	public void paintBall() {
		ArrayList<Balls> m1 = juego.getBolas();
		gt.clearRect(0,0,pane1.getPrefWidth(),pane1.getPrefHeight());
		for(int i = 0; i < m1.size();i++) {
			Double x =  (m1.get(i).getPosX() - m1.get(i).getRadio());
			Double y =  (m1.get(i).getPosY() - m1.get(i).getRadio());
			Double radio = (m1.get(i).getRadio()*2);
			gt.setFill(Color.TOMATO);
			gt.fillOval(x, y,radio , radio);
		}
		   
		
	}
	
	public game getJuego() {
		return juego;
	}

	public void setJuego(game juego) {
		this.juego = juego;
	}


	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public Pane getPane1() {
		return pane1;
	}

	public void setPane1(Pane pane1) {
		this.pane1 = pane1;
	}

	public GraphicsContext getGt() {
		return gt;
	}

	public void setGt(GraphicsContext gt) {
		this.gt = gt;
	}

	public Boolean checkFinishGame() {
		Boolean t = false;
		ArrayList<Balls> bolas1 = juego.getBolas();
		int j = 0;
		for(int i = 0; i < bolas1.size();i++) {
			if(bolas1.get(i).isStop() == true) {
				j++;
			}	
		}
		
		if(j == bolas1.size()) {
			t = true;
		}
		return t;
	}

	public Double darMayorHeight() {
		
		return pane1.getPrefHeight();
		
	}
	
	public Double darMayorWithd() {
	
		return pane1.getPrefWidth();
		
	}
	
	public void salir(ActionEvent e) {
		juego.serializableGame();
		System.exit(0);
	}
	
	public void irPuntaje(ActionEvent e) {
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Controller/MejoresPuntajes.fxml"));
		Parent root = (Parent)loader.load();

		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
		
		}catch(IOException e1){
			e1.getCause();
		}
		
		
		
		
		
	}
	
}
