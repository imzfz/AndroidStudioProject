package cn.zfz;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Qipan extends Application{
	Pane p = new Pane();
	BorderPane bp = new BorderPane();
	private final static double length = 15.0;
	private double x = 50.0, y = 50.0;
	private int i = 0, j = 0;
	final static double X = length * Math.cos(Math.PI * (1/6.0));
	final static double Y = length * Math.sin(Math.PI * (1/6.0));
	Scene scene = new Scene(p,800,600);


	public void start(Stage primaryStage) {
		draw();

		bp.setOnMouseDragged(e->{

		});


//		Scene scene = new Scene(p,800,600);
		p.getChildren().add(bp);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void draw(){
		Polygon polygon;
		for(j = 0; j < 11; j++){
			x = scene.getWidth() * 0.25;
			y = scene.getHeight() * 0.25;
			y = y + (j + 1) * (Math.sin(Math.PI * (1/6.0)) * length + length);
			x = x + (j + 1) * (Math.cos(Math.PI * (1/6.0)) * length );
			for(i = 0; i < 11; i++){
				polygon = new Polygon();
				polygon.getPoints().addAll(new Double[]{
						x, y,
						x, y+length,
						x+X, y + length + Y,
						x+2 * length * Math.cos(Math.PI * (1/6.0)), y+length,
						x+2 * length * Math.cos(Math.PI * (1/6.0)), y,
						x+X, y-Y
				});
				polygon.setStroke(Color.BLACK);
				polygon.setFill(null);
				x = x + length * 2 * Math.cos(Math.PI * (1/6.0));
				bp.getChildren().add(polygon);
			}
		}
		polygon = new Polygon();
		x = scene.getWidth() * 0.25;
		y = scene.getHeight() * 0.25;
		polygon.getPoints().addAll(new Double[]{x, y});
		polygon.setStroke(Color.BLACK);
		polygon.setFill(null);
		bp.getChildren().add(polygon);
	}

	public static void main(String[] args){
		launch();
	}
}
