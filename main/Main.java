package main;

import controllers.A_Account;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
//	javafx.cont
	
	public static void main(String[] args) { 
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/view/home.fxml"));
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("jls.jpeg")));
		Scene scene = new Scene(root);  
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.setResizable(false);
		primaryStage.setTitle("Japanese Learning System");         
		primaryStage.show();
		
		A_Account.MarkHistory();
		
	}

}