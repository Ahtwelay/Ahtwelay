package loader;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Fxloader {
	public Fxloader(Stage stage, String path) {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource(path));
			stage.setScene(new Scene(root));
			stage.centerOnScreen(); 
			stage.setResizable(false);
			stage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
