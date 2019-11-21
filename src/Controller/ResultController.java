package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import model.game;

public class ResultController implements Initializable  {

	private game m;
	
	@FXML
	private Button regresar;
	@FXML
	private TextArea area;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		m = new game(m.getNivel(),0);
		
	}


}
