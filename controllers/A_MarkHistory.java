/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.time.LocalDate;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import loader.Fxloader;

/**
 *
 * @author MCMITS5-055-USER2
 */
public class A_MarkHistory extends  Application{
    private TextField search=new TextField();
    private Button back=new Button("_Back");
    private TableView<A_ShowMarkHistory> tbShowMarkHistory=new TableView<>();
//    
//    private TableColumn<A_ShowMarkHistory,TextField> name=new TableColumn<>("Name");
//    private TableColumn<A_ShowMarkHistory,TextField> dob=new TableColumn<>("Played Date");
//    private TableColumn<A_ShowMarkHistory,TextField> mark=new TableColumn<>("Mark");
//    private TableColumn<A_ShowMarkHistory,TextField> percen=new TableColumn<>("Percentage");
//    private TableColumn<A_ShowMarkHistory,TextField> quality=new TableColumn<>("Grade");
//    private TableColumn<A_ShowMarkHistory,TextField> Level=new TableColumn<>("Level");
//    private TableColumn<A_ShowMarkHistory,TextField> category=new TableColumn<>("Category");
//    
    private TableColumn<A_ShowMarkHistory,String> name=new TableColumn<>("Name");
    private TableColumn<A_ShowMarkHistory,LocalDate> dob=new TableColumn<>("Played Date");
    private TableColumn<A_ShowMarkHistory,String> mark=new TableColumn<>("Mark");
    private TableColumn<A_ShowMarkHistory,String> percen=new TableColumn<>("Percentage");
    private TableColumn<A_ShowMarkHistory,String> quality=new TableColumn<>("Grade");
    private TableColumn<A_ShowMarkHistory,String> Level=new TableColumn<>("Level");
    private TableColumn<A_ShowMarkHistory,String> category=new TableColumn<>("Category");
    private A_ShowMarkHistory ppoint=null;
    
    
    
   
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage st) throws Exception {
    	VBox backbox=new VBox(back);
    	
        BorderPane root=new BorderPane();
      
       
        root.setBottom(backbox);
        KanjiPractice k=new KanjiPractice();
			 root.setCenter(tbShowMarkHistory);
        search.setPromptText("Enter name to search");
        
        
        tbShowMarkHistory.getColumns().addAll(name,dob,mark,percen,quality,Level,category);
        
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        dob.setCellValueFactory(new PropertyValueFactory<>("date"));
        mark.setCellValueFactory(new PropertyValueFactory<>("mark"));
        percen.setCellValueFactory(new PropertyValueFactory<>("percen"));
        quality.setCellValueFactory(new PropertyValueFactory<>("quality"));
        Level.setCellValueFactory(new PropertyValueFactory<>("level"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));

        name.setCellFactory(TextFieldTableCell.<A_ShowMarkHistory>forTableColumn());
        
//        dob.setCellFactory(TextFieldTableCell.<A_ShowMarkHistory>forTableColumn());
        mark.setCellFactory(TextFieldTableCell.<A_ShowMarkHistory>forTableColumn());
        percen.setCellFactory(TextFieldTableCell.<A_ShowMarkHistory>forTableColumn());
        quality.setCellFactory(TextFieldTableCell.<A_ShowMarkHistory>forTableColumn());
        Level.setCellFactory(TextFieldTableCell.<A_ShowMarkHistory>forTableColumn());
        category.setCellFactory(TextFieldTableCell.<A_ShowMarkHistory>forTableColumn());
        A_Account a=new A_Account();
          
          tbShowMarkHistory.getItems().addAll(a.showall());
        root.setPrefSize(600, 600);
        Scene sc=new Scene(root);
        st.setScene(sc);
        st.setResizable(false);
        st.setTitle("Mark History");
        st.getIcons().add(new Image(this.getClass().getResourceAsStream("japanese-culture.jpg")));
        st.show();
        
        
        backbox.setStyle("-fx-hgap:50;-fx-Vgap:50;-fx-spacing:50;-fx-padding:10 0 10 5;");
       
        homeController h=new homeController();
        String backlocaltion=h.mylocationclassToback;
//        name.setEditable(false);
        name.setEditable(true);
        back.setOnAction(e->{
        	name.setEditable(true);
			try {
        	name.setVisible(false);
				st.setTitle("Japanese Learning System");
				
				
				 if (backlocaltion.equalsIgnoreCase("home")) {
					 System.out.println(h.mylocationclassToback);
	                new Fxloader((Stage)((Node)e.getSource()).getScene().getWindow(), "/view/home.fxml");
					}
				 else if (backlocaltion.equalsIgnoreCase("kanji1")) {
					KanjiPractice kanji=new KanjiPractice();
					kanji.start(new Stage());
					((Node)e.getSource()).getScene().getWindow().hide();
					}
				
				
			} catch (Exception e4) {
				System.out.println(e4);
			}
        	
        	
		});
        back.setStyle("-fx-font-size:16;"
       		 + "-fx-background-color:transparent;"
       		 +"-fx-border-color:aqua;"
                + "-fx-text-fill:white;"
               + "-fx-padding:0 10;"
               + "-fx-cursor:Hand;");
        if(tbShowMarkHistory.getSelectionModel().getSelectedCells().equals(mark)){
        	System.out.println("Hello");
        	
        }
      TableColumn tarr[]= {name,dob,mark,percen,quality,Level,category};
      for (int i = 0; i < tarr.length; i++) {
		tarr[i].setStyle("-fx-text-fill:aqua;-fx-background-color:black;");
	}
      tbShowMarkHistory.setStyle("-fx-background-color:black");
        root.setStyle("-fx-background:black;-fx-border:1px;");
        
    }
    
    
}
