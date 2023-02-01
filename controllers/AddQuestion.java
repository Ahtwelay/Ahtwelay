package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import loader.Fxloader;

public class AddQuestion implements Initializable{
	static int totallength=0;

	  @FXML
	    private TextField ques;

	    @FXML
	    private Button btnBack;

	    @FXML
	    private Button btnAdd;

	    @FXML
	    private TextField first;

	    @FXML
	    private TextField second;

	    @FXML
	    private TextField third;

	    @FXML
	    private TextField fourth;
	    @FXML
	    private TextField rightans;
	    
	    @FXML
	    void rightansOnAction(ActionEvent event) {

	    }


	    @FXML
	    void btnAddOnAction(ActionEvent event) {

	    }
	    

	    @FXML
	    void btnBackOnAction(ActionEvent event) {
	    	new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "/view/home.fxml");
	    }

	    @FXML
	    void firstonaction(ActionEvent event) {

	    }

	    @FXML
	    void fourthonaction(ActionEvent event) {

	    }

	    @FXML
	    void quesonaction(ActionEvent event) {

	    }

	    @FXML
	    void secondonaction(ActionEvent event) {

	    }

	    @FXML
	    void thirdonaction(ActionEvent event) {

	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			A_Account acc=new A_Account();
	    	acc.Questions();
	    	btnAdd.setOnAction(e->{
	    		if (ques.getText().isEmpty()||first.getText().isEmpty()||second.getText().isEmpty()||third.getText().isEmpty()||fourth.getText().isEmpty()) {
					new Alert(AlertType.WARNING,"Fill all fields, empty is not allowed",ButtonType.OK).showAndWait();
				}else {
	    		acc.save(ques.getText(),rightans.getText(), first.getText(), second.getText(), third.getText(), fourth.getText());
				new Alert(AlertType.INFORMATION,"Input Successfully",ButtonType.OK).showAndWait();

				}
	    	});
	    	
			
		}
		}
