package io.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import io.model.Cell;
import io.model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameViewStreaming implements Initializable{

    @FXML
    private Label cancionTxt;

    @FXML
    private Label scoreTxt;

    @FXML
    private Button enviar;

    @FXML
    private Canvas myCanvas;

    @FXML
    private ComboBox<String> songsList;

    @FXML
    private Label chatTxt;

    @FXML
    private Button elegir;

    @FXML
    private TextArea ChatArea;

    @FXML
    private TextField mensaje;

	private Game game;
	
	private Stage stage;
	
	private Parent parent;
    
    @FXML
    void enviarBut(ActionEvent event) {

    }

    @FXML
    void elegirBut(ActionEvent event) {
    	
    	

    }

    public void dibujar() {

		GraphicsContext canvas2 = myCanvas.getGraphicsContext2D();
		drawShapes(canvas2);
	}
    
    private void drawShapes(GraphicsContext gc) {

		gc.clearRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());

		List<Cell> cells = game.getCells();
		ArrayList<Cell> userCells = new ArrayList<Cell>();
		for (int i = 0; i < cells.size(); i++) {

			int pos1 = cells.get(i).getX();
			int pos2 = cells.get(i).getY();
			int color1 = cells.get(i).getR();
			int color2 = cells.get(i).getG();
			int color3 = cells.get(i).getB();
			double radius = cells.get(i).getRadius();
			Color color = Color.rgb(color1, color2, color3);

			if (cells.get(i).getUser() != null) {
				userCells.add(cells.get(i));

			} else {
				gc.setFill(color);
				gc.fillOval(pos1, pos2, radius, radius);
			}

		}

		for (Cell userCell : userCells) {

			if (userCell != null) {
				int pos1 = userCell.getX();
				int pos2 = userCell.getY();
				int color1 = userCell.getR();
				int color2 = userCell.getG();
				int color3 = userCell.getB();
				double radius = userCell.getRadius();
				Color color = Color.rgb(color1, color2, color3);

				gc.setFill(color);

				gc.fillOval(pos1, pos2, radius, radius);

				gc.setFill(Color.BLACK);

				gc.fillText(userCell.getUser(), pos1, pos2 + (radius), radius);

			}
		}

		scoreTxt.setText("Score: " + game.getScore());

	}
	
	public void init(Parent parent, Game game) {
		this.parent = parent;

		
		
		
		for (int i = 0; i < parent.getChildrenUnmodifiable().size(); i++) {

			Node node = parent.getChildrenUnmodifiable().get(i);

			if (node != null && node.getId() != null) {
				if (node.getId().equals("myCanvas")) {
					myCanvas = (Canvas) node;
				}
			}

		}

		AnchorPane content = (AnchorPane) parent.getChildrenUnmodifiable().get(1);

		for (int i = 0; i < content.getChildren().size(); i++) {
			Node node = content.getChildrenUnmodifiable().get(i);

			if (node != null && node.getId() != null) {
				if (node.getId().equals("scoreTxt")) {
					scoreTxt = (Label) node;
				}
			}
		}

		this.game = game;
	}
	
	public void setStage(Stage stage) {
		// TODO Auto-generated method stub
		this.stage = stage;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		songsList.getItems().add("Barbie girl");
		
		
	}
    
}

