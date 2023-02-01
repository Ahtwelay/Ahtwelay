package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

//import ClientServer.ChatServer.ClientHandler;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import loader.Fxloader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class N1 extends Application {

	    private Button back = new Button("_back");
	    private Button next=new Button("→");
		private Button pre=new Button("←");
		private Button save=new Button("_Save");
		private Button Home=new Button("");
	    private Button showhistory = new Button("_History");
	    private Label  Title=new Label();
	    private static Label currentlocation=new Label("Current :");
	    private Label main = null;
	    private static Button m1 = null;
	    private static Button m2 = null;
	    private static Button m3 = null;
	    private static Button m4 = null;
	    private  static String QuesAnsMulti[] = null;
	    private static String Questionss[] = null;
	    private static String Answers[] = null;
	    private static String fourans[][] = null;
	    private static String AllFile[] = null;
	    private static String ques = null;
	    private static String ans = null;
	    private static String multi = null;
	    private static int i = 0, j = 0,b = 0,
			    		mark = 0,wrongClick = 0, wrongMark = 0, 
			    		queslength=0,curques=1;
	    String right[] = null;
	    static String mainarr = null;
	    static List<String> lines = null;
	    static String level="N 3";
	    static int addsense=0;
	    static String getStudentResult=null;
	    static String Teachernames[]=null;
	    static String Teacheripadds[]=null;
	    static String currentipadd=null;
	    static String allwrongQuestions=null;
	    static String mainans=null;
	    static Scene sc=null;
	    static int cur=1;
	    static int qlength=0;
	    static int totallength=0;
	    static int arrsizeincrement=0;
	    static String quearr[]=null;
	    static String ans1arr[]=null;
	    static String ans2arr[]=null;
	    static String ans3arr[]=null;
	    static String ans4arr[]=null;
	    static String rigntansarr[]=null;
	    
	    

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage st) throws Exception {
        BorderPane root = new BorderPane();
       
        Title.setText("N1"); 
        length();
        setarraydata();
        getData();

        Button btnarr[] = {m1, m2, m3, m4};
        for (i = 0; i < btnarr.length; i++) {
            btnarr[i].setOnAction(ee -> {
                play(ee);
            });
        }
        
        
        currentlocation.setText("Current :"+(cur)+"/"+totallength);
        main.setAlignment(Pos.CENTER);
        Button allbtnarr[]= {Home,back,next,pre,save,showhistory,m1,m2,m3,m4};
        for (int i = 0; i < allbtnarr.length; i++) {
			allbtnarr[i].setStyle("-fx-background-color:blue;-fx-text-fill:green;");
		}

//        box.setStyle("-fx-spacing:30;-fx-padding:0;-fx-font-weight:bold;-fx-font-size:1.8em;");
        for (int i = 0; i < btnarr.length; i++) {
            btnarr[i].setPrefWidth(140);
            btnarr[i].setPadding(new Insets(1));

        }

        //UI view
        RotateTransition rt = new RotateTransition(Duration.seconds(.5), save);
        rt.setByAngle(5);
        rt.setAutoReverse(true);
        rt.setCycleCount(RotateTransition.INDEFINITE);
        rt.play();
        TranslateTransition sct = new TranslateTransition(Duration.seconds(1), save);
        sct.setToX(10);
        sct.setAutoReverse(true);
        sct.setCycleCount(ScaleTransition.INDEFINITE);
        sct.play();
        

        //Define presize
        pre.setPrefWidth(50);
        next.setPrefWidth(50);
        showhistory.setPrefWidth(100);
        save.setPrefWidth(60);

		A_Account acc=new A_Account();
        HBox homebox = new HBox(Home);
      
       

        HBox showsavebtn= new HBox(15, save, showhistory);
        
        VBox rightbox=new VBox(25,showsavebtn,currentlocation);
        GridPane center = new GridPane();
        HBox btnbox = new HBox(30, m1, m2, m3, m4);
        btnbox.setStyle("-fx-padding:50;");
        center.add(Title, 1, 0);
        center.add(main, 1, 1);
        center.add(btnbox, 1, 2);
        center.add(pre, 0, 3);
        center.add(next, 2, 3);
        center.add(rightbox, 3, 0);
        GridPane.setHalignment(main, HPos.CENTER);
        GridPane.setHalignment(Title, HPos.CENTER);

        pre.setPrefWidth(60);
        next.setPrefWidth(60);
        //style start
        style();
        homebox.setStyle("-fx-font-weight:bold;-fx-font-size:1.2em;"
                + "-fx-padding:10 0 0 15;"
                + "");
        center.setStyle("-fx-hgap:20;-fx-vgap:20;"
                + "-fx-padding:30 0 50 170;");
        rightbox.setStyle("-fx-padding:0 0 0 0;");
        for (int i = 0; i < btnarr.length; i++) {
            btnarr[i].setStyle("-fx-font-size:1.5em;"
                    + "-fx-border-color:linear-gradient(from 0% 0% to 100% 100%,orange,aqua);"
                    + "-fx-border-radius:10%;"
                    + "-fx-background-radius:10%;"
                    + "-fx-background-color:transparent;"
                    + "-fx-text-fill:white;"
                    + "-fx-cursor:Hand;");
        }
        root.setStyle("-fx-background-color:black;-fx-text-fill:red;");
        //style end #343A40
        
        
       

        //import to BorderPane	and Show Stage
        HBox bottom = new HBox(back);
        bottom.setStyle("-fx-padding:10 30;");
        root.setBottom(bottom);
        root.setLeft(homebox);
        root.setCenter(center);
        homeController h=new homeController();
        h.mylocationclassToback="hira";
        st.getIcons().add(new Image(this.getClass().getResourceAsStream("japanese-culture.jpg")));
         sc = new Scene(root);
        st.sizeToScene();
        st.centerOnScreen();
        st.setScene(sc);
        st.setResizable(false);
        st.setTitle("Japanese Learning System");
        root.setPrefSize(1400, 700);
        st.show();
        shortcutkey();
       

        //buttom part start
        back.setOnAction(e -> {
        	new Fxloader((Stage)((Node)e.getSource()).getScene().getWindow(), "/view/home.fxml");
        });
        save.setOnAction(e -> {
            int right = (totallength - wrongMark);

            double percentage = (right  / (totallength * .01));
            System.out.println(percentage+"percentage");

            String quality = "Perfect";
            if (right == totallength) {
                quality = "perfect";
            } else{
                 if (percentage < 100 && percentage>80) {
                quality = "Good";
	            }
	            else if (percentage < 80 && percentage>50) {
	                quality = "Normal";
	            } 
	            else if (percentage < 50 && percentage>=0) {
	                quality = "bad";
	            } 

            }
            A_Account.saveMarkHistory("You ", right + " marks in " + totallength + " Questionss", percentage  + "%", quality, "N1","Kanji");
            
        });

        showhistory.setOnAction(e -> {
        	curques=1;
            A_MarkHistory m = new A_MarkHistory();
            try {
                m.start(st);
                st.sizeToScene();
                st.centerOnScreen();
            } catch (Exception e3) {
                System.out.println(e3.getMessage());
            }

        });
        Home.setOnAction(e -> {
        	curques=1;
        	totallength=0;
        	qlength=0;
        	arrsizeincrement=0;
            new Fxloader((Stage)((Node)e.getSource()).getScene().getWindow(), "/view/home.fxml");
        });
        next.setOnAction(e -> {
        	cur++;
        	qlength++;
            currentlocation.setText("Current :"+(cur)+"/"+totallength);
            System.out.println(cur+":"+totallength);
            
            if (cur==totallength) {
            	currentlocation.setStyle("-fx-text-fill:green;");
			}
            if (cur == (totallength+1)) {
            	
         		new Alert(AlertType.WARNING,"Do you want to replay!",ButtonType.OK).showAndWait();
         		cur=1;
         		qlength=0;
         		setdata();
//         		 currentlocation.setText("Current :"+(cur)+"/"+totallength);
         		currentlocation.setStyle("-fx-text-fill:white;");
                }{
            setdata();
            for (i = 0; i < btnarr.length; i++) {
                btnarr[i].setOnAction(ee -> {	
                    play(ee);
                });
            }
                }  
        });

        pre.setOnAction(e -> {
        	cur--;
        	qlength--;
        	currentlocation.setStyle("-fx-text-fill:white;");
        	 if (cur==0) {
         		new Alert(AlertType.WARNING,"You are from start point.Can Reverse!",ButtonType.OK).showAndWait();
           return;
         }{
            
//            currentlocation.setText("Current :"+(cur)+"/"+totallength);
         
           
            setdata();
            for (i = 0; i < btnarr.length; i++) {
                btnarr[i].setOnAction(ee -> {
                    play(ee);
                });
            }
           
         }
        });
       
    }
   
    public void play(ActionEvent e) {
        Button clicked = (Button) e.getSource();
        String data = (String) clicked.getText();
        if (data.equalsIgnoreCase(rigntansarr[cur-1])) {
        	System.out.println(cur);
            Alert a = new Alert(AlertType.INFORMATION, "Congratulations!Do you want to Continue next quiz?", ButtonType.YES, ButtonType.NO);
            if (a.showAndWait().get() == ButtonType.YES) {
            	 if (wrongClick > 0) {
                     wrongMark++;
                     wrongClick = 0;
                     Path p=Paths.get("C:/Users/Lenovo/Documents/wrong.txt");
                     allwrongQuestions=(quearr[qlength]+"="+mainans+" "+LocalDate.now()).toString();
                 	try {
                 			Files.write(p,
                 					Arrays.asList(allwrongQuestions),
                 					Charset.forName("UTF-8"),
                 					StandardOpenOption.CREATE,
                 					StandardOpenOption.APPEND
                 					);
                         
 					} catch (Exception e2) {
 					}
                 }
            	qlength++;
            	cur++;
                currentlocation.setText("Current :"+(cur)+"/"+totallength);
                if (cur== totallength) {
                	currentlocation.setStyle("-fx-text-fill:green;");
                }
                if (cur == (totallength+1)) {
                	
             		
                	new Alert(AlertType.WARNING,"Do you want to replay!",ButtonType.OK).showAndWait();
             		cur=1;
             		qlength=0;
             		setdata();
//             		 currentlocation.setText("Current :"+(cur)+"/"+totallength);
             		currentlocation.setStyle("-fx-text-fill:white;");
                }
            }
        } else {
            new Alert(AlertType.WARNING, "Sorry,Your choose Wrong", ButtonType.OK).showAndWait();

            wrongClick++;

        }
    }
    
    public void getData() {
    	
	        main = new Label(quearr[qlength]);
	        m1 = new Button(ans1arr[qlength]);
	        m2 = new Button(ans2arr[qlength]);
	        m3 = new Button(ans3arr[qlength]);
	        m4 = new Button(ans3arr[qlength]);
	        mainans=main.getText(); 
	      
    }
    public void setdata() {
    	main.setText(quearr[qlength]);
    	m1.setText(ans1arr[qlength]);
    	m2.setText(ans2arr[qlength]);
    	m3.setText(ans3arr[qlength]);
    	m4.setText(ans4arr[qlength]);
    	  mainans=main.getText(); 
        currentlocation.setText("Current :"+(cur)+"/"+totallength);
    }
    public void setarraydata() {
    	 A_Account ac=new A_Account();
         ac.Questions();
     	String sql="select * from Questions ";
 		try(PreparedStatement pst=A_DBcontrol.createconnection().prepareStatement(sql)){
 			ResultSet rs=pst.executeQuery();
 			int count=rs.getMetaData().getColumnCount();
 			while (rs.next()) {
 				System.out.println(totallength);
 				quearr[arrsizeincrement]=rs.getString(1);
 				rigntansarr[arrsizeincrement]=rs.getString(2);
 				ans1arr[arrsizeincrement]=rs.getString(3);
 				ans2arr[arrsizeincrement]=rs.getString(4);
 				ans3arr[arrsizeincrement]=rs.getString(5);
 				ans4arr[arrsizeincrement]=rs.getString(6);
 				
 				arrsizeincrement++;
 			
 				 
 			}
 			
 		} catch (Exception exception) {
 			exception.printStackTrace();
 		}
 		
    }
    public void style() {
    	 currentlocation.setStyle("-fx-text-fill:white;-fx-font-size:1.2em;");
        
         Title.setStyle("-fx-padding:0 0 80 0;-fx-font-weight:bold;"
         		+"-fx-text-fill:#b1fb17;"
         		+ "-fx-font-size:2em;");
         Home.setStyle("-fx-font-weight:bold;-fx-font-size:1.2em;"
                 + "-fx-border-radius:55%;"
                 + "-fx-background-radius:50%;"
                 + "-fx-padding:0;"
                 + "-fx-background-color:black;"
                 + "-fx-text-fill:linear-gradient(from 0% 0% to 100% 100%,orange,#3bb9ff);;"
                 + "-fx-border-color:skyblue;"
                 + "-fx-cursor:Hand;");
//         st.sets
        

         main.setStyle("-fx-font-weight:bold;-fx-font-size:2em;"
         		+ "-fx-text-fill:white;"
                 + "-fx-cursor:Hand;");
        
         next.setStyle("-fx-font-size:2em;-fx-font-weight:bold;"
                 + "-fx-padding:-8 10;"
                 + "-fx-border-color:aqua;"
                 + "-fx-border-radius:10%;"
                 + "-fx-background-radius:10%;"
                 + "-fx-background-color:transparent;"
                 + "-fx-text-fill:white;"
                 + "-fx-cursor:Hand;");
         pre.setStyle("-fx-font-size:2em;-fx-font-weight:bold;"
                 + "-fx-border-color:aqua;"
                 + "-fx-border-radius:10%;"
                 + "-fx-background-radius:10%;"
                 + "-fx-background-color:transparent;"
                 + "-fx-text-fill:white;"
                 + "-fx-padding:-8 10;"
                 + "-fx-cursor:Hand;");
         showhistory.setStyle("-fx-font-size:1.2em;"
                 + "-fx-font-weight:bold;"
                 + "-fx-spacing:50;"
                 +"-fx-border-color:aqua;"
                 + "-fx-border-radius:60%;"
                 + "-fx-background-radius:10%;"
                 + "-fx-background-color:transparent;"
                 + "-fx-text-fill:white;"
                 + "-fx-cursor:Hand;");
         save.setStyle("-fx-font-size:1.4em;-fx-font-weight:bold;"
                 + "-fx-font-size:16;"
                 + "-fx-text-fill:green;"
                 +"-fx-border-color:aqua;"
                 + "-fx-spacing:50;"
                 + "-fx-background-color:transparent;"
                 + "-fx-text-fill:white;"
                 + "-fx-cursor:Hand;");
         back.setStyle("-fx-font-size:16;"
         		 + "-fx-background-color:transparent;"
         		 +"-fx-border-color:aqua;"
                  + "-fx-text-fill:white;"
                 + "-fx-padding:0 10;"
                 + "-fx-cursor:Hand;");
    }
    public void shortcutkey() {
    	 KeyCombination home=new KeyCodeCombination(KeyCode.M,KeyCombination.ALT_DOWN);
         Mnemonic mh=new Mnemonic(Home,home);
         sc.addMnemonic(mh);
         KeyCombination kc=new KeyCodeCombination(KeyCode.N,KeyCombination.ALT_DOWN);
         Mnemonic mn=new Mnemonic(next,kc);
         sc.addMnemonic(mn);
         KeyCombination kc2=new KeyCodeCombination(KeyCode.P,KeyCombination.ALT_DOWN);
         Mnemonic mn2=new Mnemonic(pre,kc2);
         sc.addMnemonic(mn2);
         KeyCombination km1=new KeyCodeCombination(KeyCode.DIGIT1,KeyCombination.ALT_DOWN);
         Mnemonic mm1=new Mnemonic(m1,km1);
         sc.addMnemonic(mm1);
         KeyCombination km2=new KeyCodeCombination(KeyCode.DIGIT2,KeyCombination.ALT_DOWN);
         Mnemonic mm2=new Mnemonic(m2,km2);
         sc.addMnemonic(mm2);
         KeyCombination km3=new KeyCodeCombination(KeyCode.DIGIT3,KeyCombination.ALT_DOWN);
         Mnemonic mm3=new Mnemonic(m3,km3);
         sc.addMnemonic(mm3);
         KeyCombination km4=new KeyCodeCombination(KeyCode.DIGIT4,KeyCombination.ALT_DOWN);
         Mnemonic mm4=new Mnemonic(m4,km4);
         sc.addMnemonic(mm4);
    }
    
    public int length() throws SQLException, Exception {
    	 int length=0;
     	String sql="select * from Questions ";
 		try(PreparedStatement pst=A_DBcontrol.createconnection().prepareStatement(sql)){
 			ResultSet rs=pst.executeQuery();
 			while(rs.next()) {
 				totallength++;
			}
 			}
 		length=totallength;
 			quearr=new String[totallength];
			ans1arr=new String[totallength];
			ans2arr=new String[totallength];
			ans3arr=new String[totallength];
			ans4arr=new String[totallength];
			rigntansarr=new String[totallength];
			System.out.println(totallength);
			
 		System.out.println(length);
 		return length;
    }
}
