package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.plaf.metal.MetalBorders.PaletteBorder;


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

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class KanjiPractice extends Application {
	    private Button back = new Button("_back");
	    private Button next=new Button("→");
		private Button pre=new Button("←");
		private Button save=new Button("_Save");
		private Button Home=new Button("");
	    private Button showhistory = new Button("_History");
		private TextArea textshowarea=new TextArea();
	    private Label  Title=new Label();
	    private static Label currentlocation=new Label("Current :");
	    private static Button search=new Button("Translate");
	    private Label main = null;
	    private static Button m1 = null;
	    private static Button m2 = null;
	    private static Button m3 = null;
	    private static Button m4 = null;
	    private  static String QuesAnsMulti[] = null;
	    private static String Questions[] = null;
	    private static String Answers[] = null;
	    private static String fourans[][] = null;
	    private static String MultipleChoice[] = null;
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
	    static Button stroke=new Button("Stroke");
	    static ComboBox<String> strokebox=new ComboBox<>();
	    static ComboBox<String> meaningbox=new ComboBox<>();
	    static String quesdata[]=null;
	    static int strokedata=0;
	    private static Button hideans=new Button("Hide Ans");
	    static String allwrongquestion= null;
	    //http://www.chinesehideout.com/tools/strokeorder.php?c=%E7%B1%B3

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage st) throws Exception {
        BorderPane root = new BorderPane();
        homeController sechome=new homeController();
        if (sechome.level().equals("N 5")||sechome.level().equals("N 4")) {
			level="N5";
			root.setTop(MenubarForN4N5());
		}else if (sechome.level().equals("N 3")) {
        	level="N3";
        	root.setTop(menubarForN3());
        }else  if (sechome.level().equals("N 2")) {
        	level="N2";
        	root.setTop(MenubarForN2());
        }
        
       
        Title.setText(level); 		
        //Menu part  is finished
        quesdata= Questions[b].split("");
        strokebox.getSelectionModel().select("漢字");
        for ( i = 0; i < quesdata.length; i++){
        	 strokedata=i;
			strokebox.getItems().addAll(quesdata[i]);
			
		}
        
        main = new Label(Questions[b]);
        m1 = new Button(fourans[b][0]);
        m2 = new Button(fourans[b][1]);
        m3 = new Button(fourans[b][2]);
        m4 = new Button(fourans[b][3]);
//        HBox box = new HBox(m1, m2, m3, m4);
        Button btnarr[] = {m1, m2, m3, m4};
        for (i = 0; i < btnarr.length; i++) {
            btnarr[i].setOnAction(ee -> {
                play(ee, Answers[b]);
            });
        }

        

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
//		VBox v=new VBox(20,search,stroke,strokebox);
        HBox homebox = new HBox(Home);
        HBox strokeHbox=new HBox(15,strokebox,stroke);
        VBox left=new VBox(homebox);
        
       

        HBox showsavebtn= new HBox(20, save, showhistory);
        
        HBox meaninghbox=new HBox(7,meaningbox,search);
        HBox cur=new HBox(10,currentlocation,hideans);
        VBox rightbox=new VBox(25,showsavebtn,cur,strokeHbox,meaninghbox);
        GridPane center = new GridPane();
        HBox btnbox = new HBox(30, m1, m2, m3, m4);
        btnbox.setStyle("-fx-padding:50;");
        center.add(Title, 1, 0);
        center.add(main, 1, 1);
        center.add(btnbox, 1, 2);
        center.add(pre, 0, 3);
        center.add(next, 2, 3);
        center.add(rightbox, 3, 0,1,2);
        GridPane.setHalignment(main, HPos.CENTER);
        GridPane.setHalignment(Title, HPos.CENTER);

        pre.setPrefWidth(60);
        next.setPrefWidth(60);
        //style start
        hideans.setOnAction(e->{
        	if (hideans.getText().equals("Hide Ans")) {
        		hideans.setText("Show Ans");
				for (int i = 0; i < btnarr.length; i++) {
					btnarr[i].setVisible(false);
				}
				
			}else {
				hideans.setText("Hide Ans");
				for (int i = 0; i < btnarr.length; i++) {
					btnarr[i].setVisible(true);
				}
				
			}
        });
        stroke.setOnAction(e->{
        	try {
				Desktop.getDesktop().browse(
			new URL("http://www.chinesehideout.com/tools/strokeorder.php?c="+strokebox.getValue().toString()).toURI());
			} catch (IOException | URISyntaxException e1) {
				e1.printStackTrace();
			}
        });
//       	
        
        meaningbox.getItems().addAll("Google","English");
        meaningbox.getSelectionModel().selectFirst();
        search.setOnAction(e->{
			try {
				String URL=null;
				if (meaningbox.getValue().toString().equals("English")) {
					URL="https://dictionary.cambridge.org/dictionary/japanese-english/"+main.getText();
				}else if (meaningbox.getValue().toString().equals("Google")) {
					URL="https://translate.google.com/#view=home&op=translate&sl=ja&tl=my&text="+main.getText();
				}
				Desktop.getDesktop().browse(
			new URL(URL).toURI());
			} catch (IOException | URISyntaxException e1) {
				e1.printStackTrace();
			}
        });
        stroke.setStyle(
        		"-fx-border-color:linear-gradient(from 0% 0% to 100% 100%,aqua,aqua);"
        				+ "-fx-border-radius:10%;"
        				+ "-fx-background-radius:10%;"
        				+ "-fx-background-color:transparent;"
        				+ "-fx-text-fill:white;"
        				+ "-fx-cursor:Hand;"
        				+"-fx-font-weight:bold;"
        				+ "-fx-font-size:1.1em;");
        stroke.setPrefWidth(70);
        strokebox.setStyle(
        		"-fx-border-color:linear-gradient(from 0% 0% to 100% 100%,blue,blue);"
        				+ "-fx-background-color:aqua;"
        				+ "-fx-text-fill:white;"
        				+ "-fx-cursor:Hand;"
        				+"-fx-text-color:white;"
        				+"-fx-foreground-color:white;"
        				+ "-fx-font-size:1.2em;");
        meaningbox.setStyle(
        		"-fx-border-color:linear-gradient(from 0% 0% to 100% 100%,orange,aqua);"
				+ "-fx-background-color:aqua;"
				+ "-fx-text-fill:white;"
				+ "-fx-cursor:Hand;"
				+"-fx-text-color:white;"
				        				+"-fx-font-weight:bold;"
				+"-fx-foreground-color:white;"
				+ "-fx-font-size:1em;");
        search.setStyle(
        		"-fx-border-color:linear-gradient(from 0% 0% to 100% 100%,aqua,aqua);"
        				+ "-fx-border-radius:10%;"
        				+ "-fx-background-radius:10%;"
        				+ "-fx-background-color:transparent;"
        				+ "-fx-text-fill:white;"
        				+ "-fx-cursor:Hand;"
        				+"-fx-font-weight:bold;"
        				+ "-fx-font-size:1.1em;");
        hideans.setStyle(
        		"-fx-border-color:linear-gradient(from 0% 0% to 100% 100%,aqua,aqua);"
        				+ "-fx-border-radius:10%;"
        				+ "-fx-background-radius:10%;"
        				+ "-fx-background-color:transparent;"
        				+ "-fx-text-fill:white;"
        				+ "-fx-cursor:Hand;"
        				+"-fx-font-weight:bold;"
        				+ "-fx-font-size:1.1em;");
        currentlocation.setStyle("-fx-text-fill:white;-fx-font-size:1em;");
        center.setStyle("-fx-hgap:20;-fx-vgap:20;"
                + "-fx-padding:30 0 0 170;");
        rightbox.setStyle("-fx-padding:0 0 0 10;");
        Title.setStyle("-fx-padding:0 0 80 0;-fx-font-weight:bold;"
        		+"-fx-text-fill:linear-gradient(from 0% 80% to 100% 100%,aqua,aqua);"
        		+ "-fx-font-size:2em;");
        Home.setStyle("-fx-font-weight:bold;-fx-font-size:1.2em;"
                + "-fx-border-radius:55%;"
                + "-fx-background-radius:50%;"
                + "-fx-padding:0;"
                + "-fx-background-color:black;"
                + "-fx-text-fill:linear-gradient(from 0% 0% to 100% 100%,orange,aqua);"
                + "-fx-border-color:skyblue;"
                + "-fx-cursor:Hand;");
//        st.sets
        left.setStyle("-fx-font-weight:bold;-fx-font-size:1.2em;"
                + "-fx-padding:10 0 0 15;"
        		
                + "");

        main.setStyle("-fx-font-weight:bold;-fx-font-size:2em;"
        		+ "-fx-text-fill:white;"
                + "-fx-cursor:Hand;");
        for (int i = 0; i < btnarr.length; i++) {
            btnarr[i].setStyle("-fx-font-size:1.5em;"
                    + "-fx-border-color:linear-gradient(from 0% 0% to 100% 100%,orange,aqua);"
                    + "-fx-border-radius:10%;"
                    + "-fx-background-radius:10%;"
                    + "-fx-background-color:transparent;"
                    + "-fx-text-fill:white;"
                    + "-fx-cursor:Hand;");
        }

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
        textshowarea.setStyle("-fx-font-size:1em;"
//        		+ "-fx-font-weight:bold;"
        		+ "-fx-spacing:50;"
        		+"-fx-border-color:aqua;"
        		+ "-fx-background-color:transparent !important;"
        		+ "-fx-text-fill:black;"
        		+ "-fx-cursor:Hand;");
        showhistory.setStyle("-fx-font-size:1.2em;"
                + "-fx-font-weight:bold;"
//                + "-fx-hgap:750;-fx-vgap:50;"
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
//                + "-fx-hgap:230;-fx-vgap:50;"
                + "-fx-cursor:Hand;");
        back.setStyle("-fx-font-size:16;"
        		 + "-fx-background-color:transparent;"
        		 +"-fx-border-color:aqua;"
                 + "-fx-text-fill:white;"
                + "-fx-padding:0 10;"
                + "-fx-cursor:Hand;");
        
        root.setStyle("-fx-background-color:black;-fx-text-fill:red;");
        
        //style end #343A40
        st.centerOnScreen();
        
        
       

        //import to BorderPane	and Show Stage
        HBox bottom = new HBox(back);
        bottom.setStyle("-fx-padding:10 30;");
        root.setBottom(bottom);
        root.setLeft(left);
        root.setCenter(center);

        homeController h=new homeController();
        h.mylocationclassToback="kanji1";
        
        st.getIcons().add(new Image(this.getClass().getResourceAsStream("japanese-culture.jpg")));
        Scene sc = new Scene(root);
        
        st.sizeToScene();
        st.centerOnScreen();
        st.setScene(sc);
        st.setResizable(false);
        st.setTitle("Japanese Learning System");
        root.setPrefSize(1400, 700);
        st.show();
        
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

        //buttom part start
        back.setOnAction(e -> {
        	new Fxloader((Stage)((Node)e.getSource()).getScene().getWindow(), "/view/home.fxml");
        });
       
        save.setOnAction(e -> {
           
            int right = (mark - wrongMark);

            System.out.println(right);
            System.out.println(mark);
            double percentage = (right  / (mark * .01));
            System.out.println(percentage+"percentage");

            String quality = "perfect";
            if (mark==0&&right==0) {
				quality="bad";
			}else {
            if (right == mark) {
                quality = "perfect";
            } else{
                 if (percentage < 100 && percentage>=80) {
                quality = "Good";
	            }
	            else if (percentage < 80 && percentage>=50) {
	                quality = "Normal";
	            } 
	            else if (percentage < 50 && percentage>=0) {
	                quality = "bad";
	            }
                 
            }
			}
//            secHomen5Controller schome=new secHomen5Controller();
//            String category=schome.getcategory();
//            A_Account.saveMarkHistory(name, right + " marks in " + mark + " Questions", percentage  + "%", quality, "N3",category);
            A_Account.saveMarkHistory("You", right + " marks in " + mark + " Questions", percentage  + "%", quality, "N3","Kanji");
            
          
    			
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
            new Fxloader((Stage)((Node)e.getSource()).getScene().getWindow(), "/view/home.fxml");
        });
      //next button action
        next.setOnAction(e -> {
        	strokebox.getItems().clear();
            b++;
            if (b == AllFile.length) {
            	Alert end=new Alert(AlertType.WARNING,"You reached End.Do you want to Replay",ButtonType.YES,ButtonType.NO);
            	if (end.showAndWait().get()==ButtonType.YES) {
            		 b = 0;
            		 
            		 currentlocation.setStyle("-fx-text-fill:white;");
				}
            }
            currentlocation.setText("Current :"+curques+"/"+queslength);
            curques++;
            if (b == AllFile.length-1) {
            	 curques=1;
            	currentlocation.setStyle("-fx-text-fill:green;");
                }
            main.setText(Questions[b]);
            m1.setText(fourans[b][0]);
            m2.setText(fourans[b][1]);
            m3.setText(fourans[b][2]);
            m4.setText(fourans[b][3]);
            
            quesdata= Questions[b].split("");
            strokebox.getSelectionModel().select("漢字");
            for ( i = 0; i < quesdata.length; i++){
            	 strokedata=i;
    			strokebox.getItems().addAll(quesdata[i]);
    			
    		}
            for (i = 0; i < btnarr.length; i++) {
                btnarr[i].setOnAction(ee -> {
                    play(ee, Answers[b]);
                });
            }
           
        });

        pre.setOnAction(e -> {
        	  if (b == 0) {
          		new Alert(AlertType.WARNING,"You are from start point.Can Reverse!",ButtonType.OK).showAndWait();
          		return;
        	  }
        	strokebox.getItems().clear();
            b--;
            curques--;
            currentlocation.setText("Current :"+(curques-1)+"/"+queslength);
   		 currentlocation.setStyle("-fx-text-fill:white;");

          
            main.setText(Questions[b]);
            m1.setText(fourans[b][0]);
            m2.setText(fourans[b][1]);
            m3.setText(fourans[b][2]);
            m4.setText(fourans[b][3]);
            quesdata= Questions[b].split("");
            strokebox.getSelectionModel().select("漢字");
            for ( i = 0; i < quesdata.length; i++){
            	 strokedata=i;
    			strokebox.getItems().addAll(quesdata[i]);
    			
    		}
            for (i = 0; i < btnarr.length; i++) {
                btnarr[i].setOnAction(ee -> {
                    play(ee, Answers[b]);
                });
            }
           
           
        });
      
    }

    public void play(ActionEvent e, String r) {
        Button clicked = (Button) e.getSource();
        String data = (String) clicked.getText();
        if (data.equalsIgnoreCase(r)) {
            Alert a = new Alert(AlertType.INFORMATION, "Congratulations!Do you want to Continue next quiz?", ButtonType.YES, ButtonType.NO);
            if (a.showAndWait().get() == ButtonType.YES) {
                mark++;
                currentlocation.setText("Current :"+curques+"/"+queslength);
                
               
                b++;
                if (b == AllFile.length) {
//                	curques=1;
                	Alert end=new Alert(AlertType.WARNING,"You reached End.Do you want to Replay",ButtonType.YES,ButtonType.NO);
                	if (end.showAndWait().get()==ButtonType.YES) {
                		 b = 0;
                		
                		 currentlocation.setStyle("-fx-text-fill:white;");
    				}
                }
                curques++;
                if (b == AllFile.length-1) {
                	currentlocation.setStyle("-fx-text-fill:green;");
                	 curques=1;
                	 
                }
               
                
                main.setText(Questions[b]);
                m1.setText(fourans[b][0]);
                m2.setText(fourans[b][1]);
                m3.setText(fourans[b][2]);
                m4.setText(fourans[b][3]);
                strokebox.getItems().clear();
                quesdata= Questions[b].split("");
                strokebox.getSelectionModel().select("漢字");
                for ( i = 0; i < quesdata.length; i++){
                	 strokedata=i;
        			strokebox.getItems().addAll(quesdata[i]);
        			
        		}

                System.out.println(AllFile.length);
                if (wrongClick > 0) {
                    wrongMark++;
                    
                    wrongClick = 0;
                    Path p=Paths.get("C:/Users/Lenovo/Documents/wrong.txt");
                    allwrongquestion=(Questions[b-1]+"="+r+" "+LocalDate.now()).toString();
                	try {
                			Files.write(p,
                					Arrays.asList(allwrongquestion),
                					Charset.forName("UTF-8"),
                					StandardOpenOption.CREATE,
                					StandardOpenOption.APPEND
                					);
                        
					} catch (Exception e2) {
						// TODO: handle exception
					}
                }
            }
        } else {
            new Alert(AlertType.WARNING, "Sorry,Your choose Wrong", ButtonType.OK).showAndWait();
            
            wrongClick++;

        }
    }
    private void load(String c) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(c) , "UTF-8"));
            b=0;
            String line = null;
            while ((line = br.readLine()) != null) {                
                mainarr = line;
            }
            br.close();
        } catch (Exception ee) {
            System.err.println("Error: " + ee);
        }
        AllFile = new String[mainarr.length()];
        AllFile = mainarr.split("/");
        List<String> Alllist = new ArrayList<String>();
        for (int i = 0; i < AllFile.length; i++) {
            Alllist.add(AllFile[i]);
        }
        Collections.shuffle(Alllist);
        AllFile = (String[]) Alllist.toArray(new String[Alllist.size()]);
        Questions = new String[AllFile.length];
        Answers = new String[AllFile.length];
        MultipleChoice = new String[AllFile.length];
        fourans = new String[AllFile.length][];
      queslength=AllFile.length;
      currentlocation.setText("Current :"+curques+"/"+queslength);
      curques++;
        for (i = 0; i < AllFile.length; i++) {
            QuesAnsMulti = AllFile[i].split("-");
            for (int j = 0; j < 3; j++) {
                ques = QuesAnsMulti[0];
                ans = QuesAnsMulti[1];
                multi = QuesAnsMulti[2];
            }
            Questions[i] = ques;
            Answers[i] = ans;
            MultipleChoice[i] = multi;
            //test question and answer is right or not
            System.out.println(Questions[i]);
//            System.out.println(Answers[i]);
//            System.out.println(MultipleChoice[i]);
            fourans[i] = MultipleChoice[i].split(",");
           
            
        }
        System.out.println("=========================");
        for ( i = 0; i < AllFile.length; i++) {
            ArrayList<String> al = new ArrayList<String>();
            for (int j = 0; j < fourans[i].length; j++) {
                al.add(fourans[i][j]);
            }
            Collections.shuffle(al);
            fourans[i] = (String[]) al.toArray(new String[al.size()]);
            al = new ArrayList<String>();
        }

    }
    public MenuBar menubarForN3() {
    	 MenuBar bar = new MenuBar();
         load("N3TextFile/N3.txt");
         for ( i = 1; i <8; i++) {
        	 if (i==7) {
				Menu m=new Menu("All");
				MenuItem playAll=new MenuItem("All chapters");
				m.getItems().add(playAll);
				playAll.setOnAction(e->{
					 curques=1;
                	 currentlocation.setStyle("-fx-text-fill:white;");
                 	Title.setText("N3 All Chapters ");
                     load("N3TextFile/N3.txt"); 
                     main.setText(Questions[b]);
                     m1.setText(fourans[b][0]);
                     m2.setText(fourans[b][1]);
                     m3.setText(fourans[b][2]);
                     m4.setText(fourans[b][3]);
				});
				bar.getMenus().add(m);
			} else {
        	 Menu m = new Menu(String.format("第%d週", i));
             for ( j = 1; j <=8; j++) {
             	int week=i; 	
             	int day=j;
             	if (j==8) {
					MenuItem itm=new MenuItem(String.format("第%d週All", i));
					 m.getItems().add(itm);
					 itm.setUserData(String.format("N3TextFile/N3Kanji%d.txt", i));
					 itm.setOnAction(e->{
	                	 curques=1;
	                	 currentlocation.setStyle("-fx-text-fill:white;");
//	                	 currentlocation.setText(value);
//	                	 currentlocation.setText("Current :"+curques+"/"+queslength);
	                 	Title.setText(String.format("N3 第%d週 All Chapters ", week));
	                 	System.out.println(itm.getUserData());
	                     load(itm.getUserData().toString()); 
	                     main.setText(Questions[b]);
	                     m1.setText(fourans[b][0]);
	                     m2.setText(fourans[b][1]);
	                     m3.setText(fourans[b][2]);
	                     m4.setText(fourans[b][3]);
	                    
	                 });
					 itm.setStyle(""
	                      		+ "-fx-text-fill:blue;");
					 
				}else {
                 MenuItem itm = new MenuItem(String.format("%d日目", j));
                 m.getItems().add(itm);
                 itm.setUserData(String.format("N3TextFile/N3kanji%dChapter%d.txt", i, j));
                 itm.setOnAction(e->{
                	 curques=1;
                	 currentlocation.setStyle("-fx-text-fill:white;");
                 	Title.setText(String.format("N3 第%d週  %d日目", week,day));
                 	System.out.println(itm.getUserData());
                     load(itm.getUserData().toString()); 
                     main.setText(Questions[b]);
                     m1.setText(fourans[b][0]);
                     m2.setText(fourans[b][1]);
                     m3.setText(fourans[b][2]);
                     m4.setText(fourans[b][3]);
                 });
				
                 itm.setStyle(""
                 		+ "-fx-text-fill:blue;");
				}
             }
             m.setStyle("-fx-text-fill:blue;");
             
            
             bar.getMenus().add(m);
         }
       
         }
         return bar;
         
    }
//    public MenuBar MenubarForN2() {
//    	 MenuBar bar = new MenuBar();
//         System.out.println("SchoolProject.ClassFileN2.KanjiPractice.start()");
//         load("N2TextFile/1-1.txt");     
//         for ( i = 1; i <=8; i++) {
//        	 if (i==8) {
//        		 Menu m=new Menu("All");
// 				MenuItem playAll=new MenuItem("All chapters");
// 				m.getItems().add(playAll);
// 				playAll.setOnAction(e->{
// 					 curques=1;
//                 	 currentlocation.setStyle("-fx-text-fill:white;");
//                  	Title.setText("N2 All Chapters ");
//                      load("N2TextFile/N2.txt"); 
//                      main.setText(Questions[b]);
//                      m1.setText(fourans[b][0]);
//                      m2.setText(fourans[b][1]);
//                      m3.setText(fourans[b][2]);
//                      m4.setText(fourans[b][3]);
// 				});
// 				bar.getMenus().add(m);
//			}else {
//             Menu m = new Menu(String.format("第%d週", i));
//             
//             for ( j = 1; j <=8; j++) {
//             	int week=i; 	
//             	int day=j;
//             	if (i==1&&j==7) {
//					continue;
//				}
//             	if (j==8) {
//             		MenuItem itm=new MenuItem(String.format("第%d週All", i));
//					 m.getItems().add(itm);
//					 itm.setUserData(String.format("N2TextFile/%d.txt", i));
//					 itm.setOnAction(e->{
//	                	 curques=1;
//	                	 currentlocation.setStyle("-fx-text-fill:white;");
//	                 	Title.setText(String.format("N2第%d週 All Chapters ", i));
//	                     load(itm.getUserData().toString()); 
//	                     main.setText(Questions[b]);
//	                     m1.setText(fourans[b][0]);
//	                     m2.setText(fourans[b][1]);
//	                     m3.setText(fourans[b][2]);
//	                     m4.setText(fourans[b][3]);
//	                    
//	                 });
//					 itm.setStyle(""
//	                      		+ "-fx-text-fill:blue;");
//				}else {
//                 MenuItem itm = new MenuItem(String.format("%d日目", j));
//                 m.getItems().add(itm);
//                 itm.setUserData(String.format("N2TextFile/%d-%d.txt", i, j));
//                 itm.setOnAction(e->{
//                	 curques=1;
//                	 currentlocation.setStyle("-fx-text-fill:white;");
//                	 Title.setText(String.format("N2 第%d週  %d日目", week,day));
//                     System.out.println(itm.getUserData());
//                         try { 
//                             load(itm.getUserData().toString());
//                         } catch (Exception ex) {
//                             Logger.getLogger(KanjiPractice.class.getName()).log(Level.SEVERE, null, ex);
//                         }
//                     main.setText(Questions[b]);
//                     m1.setText(fourans[b][0]);
//                     m2.setText(fourans[b][1]);
//                     m3.setText(fourans[b][2]);
//                     m4.setText(fourans[b][3]);
//                 });
//                 itm.setStyle(""
//                   		+ "-fx-text-fill:blue;");
//             }
//             }
//             bar.getMenus().add(m);
//             bar.setStyle("-fx-font-weight:bold;-fx-background-color:blue;");
//             
//         }
//         }
//          return bar;
//    }
//   
    public MenuBar MenubarForN2() {
   	 MenuBar bar = new MenuBar();
        System.out.println("SchoolProject.ClassFileN2.KanjiPractice.start()");
        load("N2TextFile/N2.txt");     
        for ( i = 1; i <=9; i++) {
       	 if (i==9) {
       		 Menu m=new Menu("All");
				MenuItem playAll=new MenuItem("All chapters");
				m.getItems().add(playAll);
				playAll.setOnAction(e->{
					 curques=1;
                	 currentlocation.setStyle("-fx-text-fill:white;");
                 	Title.setText("N2 All Chapters ");
                     load("N2TextFile/N2.txt"); 
                     main.setText(Questions[b]);
                     m1.setText(fourans[b][0]);
                     m2.setText(fourans[b][1]);
                     m3.setText(fourans[b][2]);
                     m4.setText(fourans[b][3]);
				});
				bar.getMenus().add(m);
			}else {
            Menu m = new Menu(String.format("第%d週", i));
            
            for ( j = 1; j <=8; j++) {
            	int week=i; 	
            	int day=j;
            	
            	if (j==8) {
            		MenuItem itm=new MenuItem(String.format("第%d週All", week));
					 m.getItems().add(itm);
					 itm.setUserData(String.format("N2TextFile/N2Kanji%d.txt", week));
					 itm.setOnAction(e->{
	                	 curques=1;
	                	 currentlocation.setStyle("-fx-text-fill:white;");
	                 	Title.setText(String.format("N2第%d週 All Chapters ", week));
	                     load(itm.getUserData().toString()); 
	                     main.setText(Questions[b]);
	                     m1.setText(fourans[b][0]);
	                     m2.setText(fourans[b][1]);
	                     m3.setText(fourans[b][2]);
	                     m4.setText(fourans[b][3]);
	                    
	                 });
					 itm.setStyle(""
	                      		+ "-fx-text-fill:blue;");
				}else {
                MenuItem itm = new MenuItem(String.format("%d日目", j));
                m.getItems().add(itm);
                itm.setUserData(String.format("N2TextFile/%d-%d.txt", i, j));
                itm.setOnAction(e->{
               	 curques=1;
               	 currentlocation.setStyle("-fx-text-fill:white;");
               	 Title.setText(String.format("N2 第%d週  %d日目", week,day));
                    System.out.println(itm.getUserData());
                        try { 
                            load(itm.getUserData().toString());
                        } catch (Exception ex) {
                            Logger.getLogger(KanjiPractice.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    main.setText(Questions[b]);
                    m1.setText(fourans[b][0]);
                    m2.setText(fourans[b][1]);
                    m3.setText(fourans[b][2]);
                    m4.setText(fourans[b][3]);
                });
                itm.setStyle(""
                  		+ "-fx-text-fill:blue;");
            }
            }
            bar.getMenus().add(m);
            bar.setStyle("-fx-font-weight:bold;;");
            
        }
        }
         return bar;
   }
 
    public MenuBar MenubarForN4N5()
    {
    	MenuBar bar = new MenuBar();
        homeController sechome=new homeController();
        if (sechome.level().equals("N 5")) {
			level="N5";
		}else  if (sechome.level().equals("N 4")) {
			level="N4";
		}
        load(String.format("%sTextFile/%s.txt", level,level));
        Menu mm45 = new Menu("Chapters");
        for (int i = 1; i <=11; i++) {
       	int lesson=i;
            if (i==11) {
            	MenuItem itm=new MenuItem(String.format("All Chapter"));
				 mm45.getItems().add(itm);
				 itm.setOnAction(e->{
               	 curques=1;
               	 currentlocation.setStyle("-fx-text-fill:white;");
                	Title.setText(String.format("%s All Chapters ", level));
                    load(level.toString()+".txt"); 
                    main.setText(Questions[b]);
                    m1.setText(fourans[b][0]);
                    m2.setText(fourans[b][1]);
                    m3.setText(fourans[b][2]);
                    m4.setText(fourans[b][3]);
                   
                   
                });
				 itm.setStyle("-fx-text-fill:blue;");
				
				
			}else {
                MenuItem itm = new MenuItem(String.format("Chapters%d", i));
                mm45.getItems().add(itm);
                itm.setUserData(String.format("%sTextFile/%sChapter%d.txt",level,level, i));
                itm.setOnAction(e->{
                	 curques=1;
                	 currentlocation.setStyle("-fx-text-fill:white;");
                	Title.setText(String.format("%s Chapter %d",level,lesson));
                    System.out.println(itm.getUserData());
                    load(itm.getUserData().toString()); 
                    main.setText(Questions[b]);
                    m1.setText(fourans[b][0]);
                    m2.setText(fourans[b][1]);
                    m3.setText(fourans[b][2]);
                    m4.setText(fourans[b][3]);
                });
                itm.setStyle(""
                 		+ "-fx-text-fill:blue;");
        }
        }
        bar.getMenus().add(mm45);
        bar.setStyle("-fx-font-weight:bold;");
        return bar;
    }
    
}

//package controllers;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Iterator;
//import java.util.List;
//
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//
////import ClientServer.ChatServer.ClientHandler;
//import javafx.animation.RotateTransition;
//import javafx.animation.ScaleTransition;
//import javafx.animation.TranslateTransition;
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.geometry.HPos;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.ButtonType;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.Hyperlink;
//import javafx.scene.control.Label;
//import javafx.scene.control.ListView;
//import javafx.scene.control.Menu;
//import javafx.scene.control.MenuBar;
//import javafx.scene.control.MenuItem;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.RadioMenuItem;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import javafx.scene.control.ToggleGroup;
//import javafx.scene.image.Image;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyCodeCombination;
//import javafx.scene.input.KeyCombination;
//import javafx.scene.input.Mnemonic;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//import loader.Fxloader;
//
//import java.awt.Desktop;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.lang.reflect.Array;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.net.URISyntaxException;
//import java.net.URL;
//import java.nio.charset.Charset;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardOpenOption;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//public class KanjiPractice extends Application {
//	    private Button back = new Button("_back");
//	    private Button next=new Button("→");
//		private Button pre=new Button("←");
//		private Button save=new Button("_Save");
//		private Button Home=new Button("");
//	    private Button showhistory = new Button("_History");
//		private TextArea textshowarea=new TextArea();
//	    private Label  Title=new Label();
//	    private static Label currentlocation=new Label("Current :");
//	    private static Button search=new Button("Translate");
//	    private Label main = null;
//	    private static Button m1 = null;
//	    private static Button m2 = null;
//	    private static Button m3 = null;
//	    private static Button m4 = null;
//	    private  static String QuesAnsMulti[] = null;
//	    private static String Questions[] = null;
//	    private static String Answers[] = null;
//	    private static String fourans[][] = null;
//	    private static String MultipleChoice[] = null;
//	    private static String AllFile[] = null;
//	    private static String ques = null;
//	    private static String ans = null;
//	    private static String multi = null;
//	    private static int i = 0, j = 0,b = 0,
//			    		mark = 0,wrongClick = 0, wrongMark = 0, 
//			    		queslength=0,curques=1;
//	    String right[] = null;
//	    static String mainarr = null;
//	    static List<String> lines = null;
//	    static String level="N 3";
//	    static Button stroke=new Button("Stroke");
//	    static ComboBox<String> strokebox=new ComboBox<>();
//	    static ComboBox<String> meaningbox=new ComboBox<>();
//	    static String quesdata[]=null;
//	    static int strokedata=0;
//	    private static Button hideans=new Button("Hide Ans");
//	    static String allwrongquestion= null;
//	    int total=2200;
//	    private  String kanjiarr[]=new String[total];
//	    private  String kataarr[]=new String[total];
//		private  String myanmararr[]=new String[total];
//		private  String N2arr[]=null;
//		
//		static int rowno=0;
//		static int increamarr=0;
//		static int increamnum=0;
//	    //http://www.chinesehideout.com/tools/strokeorder.php?c=%E7%B1%B3
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage st) throws Exception {
//    
//        BorderPane root = new BorderPane();
//        homeController sechome=new homeController();
//        if (sechome.level().equals("N 5")||sechome.level().equals("N 4")) {
//			level="N5";
//			root.setTop(MenubarForN4N5());
//		}else if (sechome.level().equals("N 3")) {
//        	level="N3";
//        	root.setTop(menubarForN3());
//        }
//       
//        Title.setText(level); 		
//        //Menu part  is finished
//        
//        strokebox.getSelectionModel().select("漢字");
//        if (level!="N2") {
//        	  for ( i = 0; i < quesdata.length; i++){
//             	 strokedata=i;
//     			strokebox.getItems().addAll(quesdata[i]);
//     			
//     		}
//		}
//      
//        
//        main = new Label(Questions[b]);
//        m1 = new Button(fourans[b][0]);
//        m2 = new Button(fourans[b][1]);
//        m3 = new Button(fourans[b][2]);
//        m4 = new Button(fourans[b][3]);
////        HBox box = new HBox(m1, m2, m3, m4);
//        Button btnarr[] = {m1, m2, m3, m4};
//        for (i = 0; i < btnarr.length; i++) {
//            btnarr[i].setOnAction(ee -> {
//                play(ee, Answers[b]);
//            });
//        }
//
//        
//
//        main.setAlignment(Pos.CENTER);
//        Button allbtnarr[]= {Home,back,next,pre,save,showhistory,m1,m2,m3,m4};
//        for (int i = 0; i < allbtnarr.length; i++) {
//			allbtnarr[i].setStyle("-fx-background-color:blue;-fx-text-fill:green;");
//		}
//
////        box.setStyle("-fx-spacing:30;-fx-padding:0;-fx-font-weight:bold;-fx-font-size:1.8em;");
//        for (int i = 0; i < btnarr.length; i++) {
//            btnarr[i].setPrefWidth(140);
//            btnarr[i].setPadding(new Insets(1));
//
//        }
//
//        //UI view
//        RotateTransition rt = new RotateTransition(Duration.seconds(.5), save);
//        rt.setByAngle(5);
//        rt.setAutoReverse(true);
//        rt.setCycleCount(RotateTransition.INDEFINITE);
//        rt.play();
//        TranslateTransition sct = new TranslateTransition(Duration.seconds(1), save);
//        sct.setToX(10);
//        sct.setAutoReverse(true);
//        sct.setCycleCount(ScaleTransition.INDEFINITE);
//        sct.play();
//        
//
//        //Define presize
//        pre.setPrefWidth(50);
//        next.setPrefWidth(50);
//        showhistory.setPrefWidth(100);
//        save.setPrefWidth(60);
//
//		A_Account acc=new A_Account();
////		VBox v=new VBox(20,search,stroke,strokebox);
//        HBox homebox = new HBox(Home);
//        HBox strokeHbox=new HBox(15,strokebox,stroke);
//        VBox left=new VBox(homebox);
//        
//       
//
//        HBox showsavebtn= new HBox(20, save, showhistory);
//        
//        HBox meaninghbox=new HBox(7,meaningbox,search);
//        HBox cur=new HBox(10,currentlocation,hideans);
//        VBox rightbox=new VBox(25,showsavebtn,cur,strokeHbox,meaninghbox);
//        GridPane center = new GridPane();
//        HBox btnbox = new HBox(30, m1, m2, m3, m4);
//        btnbox.setStyle("-fx-padding:50;");
//        center.add(Title, 1, 0);
//        center.add(main, 1, 1);
//        center.add(btnbox, 1, 2);
//        center.add(pre, 0, 3);
//        center.add(next, 2, 3);
//        center.add(rightbox, 3, 0,1,2);
//        GridPane.setHalignment(main, HPos.CENTER);
//        GridPane.setHalignment(Title, HPos.CENTER);
//
//        pre.setPrefWidth(60);
//        next.setPrefWidth(60);
//        //style start
//        hideans.setOnAction(e->{
//        	if (hideans.getText().equals("Hide Ans")) {
//        		hideans.setText("Show Ans");
//				for (int i = 0; i < btnarr.length; i++) {
//					btnarr[i].setVisible(false);
//				}
//				
//			}else {
//				hideans.setText("Hide Ans");
//				for (int i = 0; i < btnarr.length; i++) {
//					btnarr[i].setVisible(true);
//				}
//				
//			}
//        });
//        stroke.setOnAction(e->{
//        	try {
//				Desktop.getDesktop().browse(
//			new URL("http://www.chinesehideout.com/tools/strokeorder.php?c="+strokebox.getValue().toString()).toURI());
//			} catch (IOException | URISyntaxException e1) {
//				e1.printStackTrace();
//			}
//        });
////       	
//        
//        meaningbox.getItems().addAll("Google","English");
//        meaningbox.getSelectionModel().selectFirst();
//        search.setOnAction(e->{
//			try {
//				String URL=null;
//				if (meaningbox.getValue().toString().equals("English")) {
//					URL="https://dictionary.cambridge.org/dictionary/japanese-english/"+main.getText();
//				}else if (meaningbox.getValue().toString().equals("Google")) {
//					URL="https://translate.google.com/#view=home&op=translate&sl=ja&tl=my&text="+main.getText();
//				}
//				Desktop.getDesktop().browse(
//			new URL(URL).toURI());
//			} catch (IOException | URISyntaxException e1) {
//				e1.printStackTrace();
//			}
//        });
//        stroke.setStyle(
//        		"-fx-border-color:linear-gradient(from 0% 0% to 100% 100%,aqua,aqua);"
//        				+ "-fx-border-radius:10%;"
//        				+ "-fx-background-radius:10%;"
//        				+ "-fx-background-color:transparent;"
//        				+ "-fx-text-fill:white;"
//        				+ "-fx-cursor:Hand;"
//        				+"-fx-font-weight:bold;"
//        				+ "-fx-font-size:1.1em;");
//        stroke.setPrefWidth(70);
//        strokebox.setStyle(
//        		"-fx-border-color:linear-gradient(from 0% 0% to 100% 100%,blue,blue);"
//        				+ "-fx-background-color:aqua;"
//        				+ "-fx-text-fill:white;"
//        				+ "-fx-cursor:Hand;"
//        				+"-fx-text-color:white;"
//        				+"-fx-foreground-color:white;"
//        				+ "-fx-font-size:1.2em;");
//        meaningbox.setStyle(
//        		"-fx-border-color:linear-gradient(from 0% 0% to 100% 100%,orange,aqua);"
//				+ "-fx-background-color:aqua;"
//				+ "-fx-text-fill:white;"
//				+ "-fx-cursor:Hand;"
//				+"-fx-text-color:white;"
//				        				+"-fx-font-weight:bold;"
//				+"-fx-foreground-color:white;"
//				+ "-fx-font-size:1em;");
//        search.setStyle(
//        		"-fx-border-color:linear-gradient(from 0% 0% to 100% 100%,aqua,aqua);"
//        				+ "-fx-border-radius:10%;"
//        				+ "-fx-background-radius:10%;"
//        				+ "-fx-background-color:transparent;"
//        				+ "-fx-text-fill:white;"
//        				+ "-fx-cursor:Hand;"
//        				+"-fx-font-weight:bold;"
//        				+ "-fx-font-size:1.1em;");
//        hideans.setStyle(
//        		"-fx-border-color:linear-gradient(from 0% 0% to 100% 100%,aqua,aqua);"
//        				+ "-fx-border-radius:10%;"
//        				+ "-fx-background-radius:10%;"
//        				+ "-fx-background-color:transparent;"
//        				+ "-fx-text-fill:white;"
//        				+ "-fx-cursor:Hand;"
//        				+"-fx-font-weight:bold;"
//        				+ "-fx-font-size:1.1em;");
//        currentlocation.setStyle("-fx-text-fill:white;-fx-font-size:1em;");
//        center.setStyle("-fx-hgap:20;-fx-vgap:20;"
//                + "-fx-padding:30 0 0 170;");
//        rightbox.setStyle("-fx-padding:0 0 0 10;");
//        Title.setStyle("-fx-padding:0 0 80 0;-fx-font-weight:bold;"
//        		+"-fx-text-fill:linear-gradient(from 0% 80% to 100% 100%,aqua,aqua);"
//        		+ "-fx-font-size:2em;");
//        Home.setStyle("-fx-font-weight:bold;-fx-font-size:1.2em;"
//                + "-fx-border-radius:55%;"
//                + "-fx-background-radius:50%;"
//                + "-fx-padding:0;"
//                + "-fx-background-color:black;"
//                + "-fx-text-fill:linear-gradient(from 0% 0% to 100% 100%,orange,aqua);"
//                + "-fx-border-color:skyblue;"
//                + "-fx-cursor:Hand;");
////        st.sets
//        left.setStyle("-fx-font-weight:bold;-fx-font-size:1.2em;"
//                + "-fx-padding:10 0 0 15;"
//        		
//                + "");
//
//        main.setStyle("-fx-font-weight:bold;-fx-font-size:2em;"
//        		+ "-fx-text-fill:white;"
//                + "-fx-cursor:Hand;");
//        for (int i = 0; i < btnarr.length; i++) {
//            btnarr[i].setStyle("-fx-font-size:1.5em;"
//                    + "-fx-border-color:linear-gradient(from 0% 0% to 100% 100%,orange,aqua);"
//                    + "-fx-border-radius:10%;"
//                    + "-fx-background-radius:10%;"
//                    + "-fx-background-color:transparent;"
//                    + "-fx-text-fill:white;"
//                    + "-fx-cursor:Hand;");
//        }
//
//        next.setStyle("-fx-font-size:2em;-fx-font-weight:bold;"
//                + "-fx-padding:-8 10;"
//                + "-fx-border-color:aqua;"
//                + "-fx-border-radius:10%;"
//                + "-fx-background-radius:10%;"
//                + "-fx-background-color:transparent;"
//                + "-fx-text-fill:white;"
//                + "-fx-cursor:Hand;");
//        pre.setStyle("-fx-font-size:2em;-fx-font-weight:bold;"
//                + "-fx-border-color:aqua;"
//                + "-fx-border-radius:10%;"
//                + "-fx-background-radius:10%;"
//                + "-fx-background-color:transparent;"
//                + "-fx-text-fill:white;"
//                + "-fx-padding:-8 10;"
//                + "-fx-cursor:Hand;");
//        textshowarea.setStyle("-fx-font-size:1em;"
////        		+ "-fx-font-weight:bold;"
//        		+ "-fx-spacing:50;"
//        		+"-fx-border-color:aqua;"
//        		+ "-fx-background-color:transparent !important;"
//        		+ "-fx-text-fill:black;"
//        		+ "-fx-cursor:Hand;");
//        showhistory.setStyle("-fx-font-size:1.2em;"
//                + "-fx-font-weight:bold;"
////                + "-fx-hgap:750;-fx-vgap:50;"
//                + "-fx-spacing:50;"
//                +"-fx-border-color:aqua;"
//                + "-fx-border-radius:60%;"
//                + "-fx-background-radius:10%;"
//                + "-fx-background-color:transparent;"
//                + "-fx-text-fill:white;"
//                + "-fx-cursor:Hand;");
//        save.setStyle("-fx-font-size:1.4em;-fx-font-weight:bold;"
//                + "-fx-font-size:16;"
//                + "-fx-text-fill:green;"
//                +"-fx-border-color:aqua;"
//                + "-fx-spacing:50;"
//                + "-fx-background-color:transparent;"
//                + "-fx-text-fill:white;"
////                + "-fx-hgap:230;-fx-vgap:50;"
//                + "-fx-cursor:Hand;");
//        back.setStyle("-fx-font-size:16;"
//        		 + "-fx-background-color:transparent;"
//        		 +"-fx-border-color:aqua;"
//                 + "-fx-text-fill:white;"
//                + "-fx-padding:0 10;"
//                + "-fx-cursor:Hand;");
//        
//        root.setStyle("-fx-background-color:black;-fx-text-fill:red;");
//        
//        //style end #343A40
//        st.centerOnScreen();
//        
//        
//       
//
//        //import to BorderPane	and Show Stage
//        HBox bottom = new HBox(back);
//        bottom.setStyle("-fx-padding:10 30;");
//        root.setBottom(bottom);
//        root.setLeft(left);
//        root.setCenter(center);
//
//        homeController h=new homeController();
//        h.mylocationclassToback="kanji1";
//        
//        st.getIcons().add(new Image(this.getClass().getResourceAsStream("japanese-culture.jpg")));
//        Scene sc = new Scene(root);
//        
//        st.sizeToScene();
//        st.centerOnScreen();
//        st.setScene(sc);
//        st.setResizable(false);
//        st.setTitle("Japanese Learning System");
//        root.setPrefSize(1400, 700);
//        st.show();
//        
//        KeyCombination home=new KeyCodeCombination(KeyCode.M,KeyCombination.ALT_DOWN);
//        Mnemonic mh=new Mnemonic(Home,home);
//        sc.addMnemonic(mh);
//        KeyCombination kc=new KeyCodeCombination(KeyCode.N,KeyCombination.ALT_DOWN);
//        Mnemonic mn=new Mnemonic(next,kc);
//        sc.addMnemonic(mn);
//        KeyCombination kc2=new KeyCodeCombination(KeyCode.P,KeyCombination.ALT_DOWN);
//        Mnemonic mn2=new Mnemonic(pre,kc2);
//        sc.addMnemonic(mn2);
//        KeyCombination km1=new KeyCodeCombination(KeyCode.DIGIT1,KeyCombination.ALT_DOWN);
//        Mnemonic mm1=new Mnemonic(m1,km1);
//        sc.addMnemonic(mm1);
//        KeyCombination km2=new KeyCodeCombination(KeyCode.DIGIT2,KeyCombination.ALT_DOWN);
//        Mnemonic mm2=new Mnemonic(m2,km2);
//        sc.addMnemonic(mm2);
//        KeyCombination km3=new KeyCodeCombination(KeyCode.DIGIT3,KeyCombination.ALT_DOWN);
//        Mnemonic mm3=new Mnemonic(m3,km3);
//        sc.addMnemonic(mm3);
//        KeyCombination km4=new KeyCodeCombination(KeyCode.DIGIT4,KeyCombination.ALT_DOWN);
//        Mnemonic mm4=new Mnemonic(m4,km4);
//        sc.addMnemonic(mm4);
//
//        //buttom part start
//        back.setOnAction(e -> {
//        	new Fxloader((Stage)((Node)e.getSource()).getScene().getWindow(), "/view/home.fxml");
//        });
//       
//        save.setOnAction(e -> {
//           
//            int right = (mark - wrongMark);
//
//            System.out.println(right);
//            System.out.println(mark);
//            double percentage = (right  / (mark * .01));
//            System.out.println(percentage+"percentage");
//
//            String quality = "perfect";
//            if (mark==0&&right==0) {
//				quality="bad";
//			}else {
//            if (right == mark) {
//                quality = "perfect";
//            } else{
//                 if (percentage < 100 && percentage>=80) {
//                quality = "Good";
//	            }
//	            else if (percentage < 80 && percentage>=50) {
//	                quality = "Normal";
//	            } 
//	            else if (percentage < 50 && percentage>=0) {
//	                quality = "bad";
//	            }
//                 
//            }
//			}
//            
////            A_Account.saveMarkHistory(name, right + " marks in " + mark + " Questions", percentage  + "%", quality, "N3",category);
//            A_Account.saveMarkHistory("You", right + " marks in " + mark + " Questions", percentage  + "%", quality, "N3","Kanji");
//            
//          
//    			
//        });
//
//        showhistory.setOnAction(e -> {
//        	curques=1;
//            A_MarkHistory m = new A_MarkHistory();
//            try {
//                m.start(st);
//                st.sizeToScene();
//                st.centerOnScreen();
//            } catch (Exception e3) {
//                System.out.println(e3.getMessage());
//            }
//
//        });
//        Home.setOnAction(e -> {
//        	curques=1;
//            new Fxloader((Stage)((Node)e.getSource()).getScene().getWindow(), "/view/home.fxml");
//        });
//      //next button action
//        next.setOnAction(e -> {
//        	strokebox.getItems().clear();
//            b++;
//            if (b == queslength) {
//            	Alert end=new Alert(AlertType.WARNING,"You reached End.Do you want to Replay",ButtonType.YES,ButtonType.NO);
//            	if (end.showAndWait().get()==ButtonType.YES) {
//            		 b = 0;
//            		 
//            		 currentlocation.setStyle("-fx-text-fill:white;");
//				}
//            }
//            currentlocation.setText("Current :"+curques+"/"+queslength);
//            curques++;
//            if (b == queslength) {
//            	 curques=1;
//            	currentlocation.setStyle("-fx-text-fill:green;");
//                }
//            main.setText(Questions[b]);
//            m1.setText(fourans[b][0]);
//            m2.setText(fourans[b][1]);
//            m3.setText(fourans[b][2]);
//            m4.setText(fourans[b][3]);
//            
//            quesdata= Questions[b].split("");
//            strokebox.getSelectionModel().select("漢字");
//            for ( i = 0; i < quesdata.length; i++){
//            	 strokedata=i;
//    			strokebox.getItems().addAll(quesdata[i]);
//    			
//    		}
//            for (i = 0; i < btnarr.length; i++) {
//                btnarr[i].setOnAction(ee -> {
//                    play(ee, Answers[b]);
//                });
//            }
//           
//        });
//
//        pre.setOnAction(e -> {
//        	  if (b == 0) {
//          		new Alert(AlertType.WARNING,"You are from start point.Can Reverse!",ButtonType.OK).showAndWait();
//          		return;
//        	  }
//        	strokebox.getItems().clear();
//            b--;
//            curques--;
//            currentlocation.setText("Current :"+(curques-1)+"/"+queslength);
//   		 currentlocation.setStyle("-fx-text-fill:white;");
//
//          
//            main.setText(Questions[b]);
//            m1.setText(fourans[b][0]);
//            m2.setText(fourans[b][1]);
//            m3.setText(fourans[b][2]);
//            m4.setText(fourans[b][3]);
//            quesdata= Questions[b].split("");
//            strokebox.getSelectionModel().select("漢字");
//            for ( i = 0; i < quesdata.length; i++){
//            	 strokedata=i;
//    			strokebox.getItems().addAll(quesdata[i]);
//    			
//    		}
//            for (i = 0; i < btnarr.length; i++) {
//                btnarr[i].setOnAction(ee -> {
//                    play(ee, Answers[b]);
//                });
//            }
//           
//           
//        });
//      
//    }
//
//    public void play(ActionEvent e, String r) {
//        Button clicked = (Button) e.getSource();
//        String data = (String) clicked.getText();
//        if (data.equalsIgnoreCase(r)) {
//            Alert a = new Alert(AlertType.INFORMATION, "Congratulations!Do you want to Continue next quiz?", ButtonType.YES, ButtonType.NO);
//            if (a.showAndWait().get() == ButtonType.YES) {
//                mark++;
//                currentlocation.setText("Current :"+curques+"/"+queslength);
//                
//               
//                b++;
//                if (b == AllFile.length) {
////                	curques=1;
//                	Alert end=new Alert(AlertType.WARNING,"You reached End.Do you want to Replay",ButtonType.YES,ButtonType.NO);
//                	if (end.showAndWait().get()==ButtonType.YES) {
//                		 b = 0;
//                		
//                		 currentlocation.setStyle("-fx-text-fill:white;");
//    				}
//                }
//                curques++;
//                if (b == AllFile.length-1) {
//                	currentlocation.setStyle("-fx-text-fill:green;");
//                	 curques=1;
//                	 
//                }
//               
//                
//                main.setText(Questions[b]);
//                m1.setText(fourans[b][0]);
//                m2.setText(fourans[b][1]);
//                m3.setText(fourans[b][2]);
//                m4.setText(fourans[b][3]);
//                strokebox.getItems().clear();
//                quesdata= Questions[b].split("");
//                strokebox.getSelectionModel().select("漢字");
//                for ( i = 0; i < quesdata.length; i++){
//                	 strokedata=i;
//        			strokebox.getItems().addAll(quesdata[i]);
//        			
//        		}
//
//                System.out.println(AllFile.length);
//                if (wrongClick > 0) {
//                    wrongMark++;
//                    
//                    wrongClick = 0;
//                    Path p=Paths.get("C:/Users/Lenovo/Documents/wrong.txt");
//                    allwrongquestion=(Questions[b-1]+"="+r+" "+LocalDate.now()).toString();
//                	try {
//                			Files.write(p,
//                					Arrays.asList(allwrongquestion),
//                					Charset.forName("UTF-8"),
//                					StandardOpenOption.CREATE,
//                					StandardOpenOption.APPEND
//                					);
//                        
//					} catch (Exception e2) {
//						// TODO: handle exception
//					}
//                }
//            }
//        } else {
//            new Alert(AlertType.WARNING, "Sorry,Your choose Wrong", ButtonType.OK).showAndWait();
//            
//            wrongClick++;
//
//        }
//    }
//    private void load(String c) {
//        try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(c) , "UTF-8"));
//            b=0;
//            String line = null;
//            while ((line = br.readLine()) != null) {                
//                mainarr = line;
//            }
//            br.close();
//        } catch (Exception ee) {
//            System.err.println("Error: " + ee);
//        }
//        AllFile = new String[mainarr.length()];
//        AllFile = mainarr.split("/");
//        List<String> Alllist = new ArrayList<String>();
//        for (int i = 0; i < AllFile.length; i++) {
//            Alllist.add(AllFile[i]);
//        }
//        Collections.shuffle(Alllist);
//        AllFile = (String[]) Alllist.toArray(new String[Alllist.size()]);
//        Questions = new String[AllFile.length];
//        Answers = new String[AllFile.length];
//        MultipleChoice = new String[AllFile.length];
//        fourans = new String[AllFile.length][];
//      queslength=AllFile.length;
//      currentlocation.setText("Current :"+curques+"/"+queslength);
//      curques++;
//        for (i = 0; i < AllFile.length; i++) {
//            QuesAnsMulti = AllFile[i].split("-");
//            for (int j = 0; j < 3; j++) {
//                ques = QuesAnsMulti[0];
//                ans = QuesAnsMulti[1];
//                multi = QuesAnsMulti[2];
//            }
//            Questions[i] = ques;
//            Answers[i] = ans;
//            MultipleChoice[i] = multi;
//            //test question and answer is right or not
//            System.out.println(Questions[i]);
////            System.out.println(Answers[i]);
////            System.out.println(MultipleChoice[i]);
//            fourans[i] = MultipleChoice[i].split(",");
//           
//            
//        }
//        System.out.println("=========================");
//        for ( i = 0; i < AllFile.length; i++) {
//            ArrayList<String> al = new ArrayList<String>();
//            for (int j = 0; j < fourans[i].length; j++) {
//                al.add(fourans[i][j]);
//            }
//            Collections.shuffle(al);
//            fourans[i] = (String[]) al.toArray(new String[al.size()]);
//            al = new ArrayList<String>();
//        }
//
//    }
//    public MenuBar menubarForN3() {
//    	 MenuBar bar = new MenuBar();
//         load("N3TextFile/N3.txt");
//         for ( i = 1; i <8; i++) {
//        	 if (i==7) {
//				Menu m=new Menu("All");
//				MenuItem playAll=new MenuItem("All chapters");
//				m.getItems().add(playAll);
//				playAll.setOnAction(e->{
//					 curques=1;
//                	 currentlocation.setStyle("-fx-text-fill:white;");
//                 	Title.setText("N3 All Chapters ");
//                     load("N3TextFile/N3.txt"); 
//                     main.setText(Questions[b]);
//                     m1.setText(fourans[b][0]);
//                     m2.setText(fourans[b][1]);
//                     m3.setText(fourans[b][2]);
//                     m4.setText(fourans[b][3]);
//				});
//				bar.getMenus().add(m);
//			} else {
//        	 Menu m = new Menu(String.format("第%d週", i));
//             for ( j = 1; j <=8; j++) {
//             	int week=i; 	
//             	int day=j;
//             	if (j==8) {
//					MenuItem itm=new MenuItem(String.format("第%d週All", i));
//					 m.getItems().add(itm);
//					 itm.setUserData(String.format("N3TextFile/N3Kanji%d.txt", i));
//					 itm.setOnAction(e->{
//	                	 curques=1;
//	                	 currentlocation.setStyle("-fx-text-fill:white;");
////	                	 currentlocation.setText(value);
////	                	 currentlocation.setText("Current :"+curques+"/"+queslength);
//	                 	Title.setText(String.format("N3 第%d週 All Chapters ", week));
//	                 	System.out.println(itm.getUserData());
//	                     load(itm.getUserData().toString()); 
//	                     main.setText(Questions[b]);
//	                     m1.setText(fourans[b][0]);
//	                     m2.setText(fourans[b][1]);
//	                     m3.setText(fourans[b][2]);
//	                     m4.setText(fourans[b][3]);
//	                    
//	                 });
//					 itm.setStyle(""
//	                      		+ "-fx-text-fill:blue;");
//					 
//				}else {
//                 MenuItem itm = new MenuItem(String.format("%d日目", j));
//                 m.getItems().add(itm);
//                 itm.setUserData(String.format("N3TextFile/N3kanji%dChapter%d.txt", i, j));
//                 itm.setOnAction(e->{
//                	 curques=1;
//                	 currentlocation.setStyle("-fx-text-fill:white;");
//                 	Title.setText(String.format("N3 第%d週  %d日目", week,day));
//                 	System.out.println(itm.getUserData());
//                     load(itm.getUserData().toString()); 
//                     main.setText(Questions[b]);
//                     m1.setText(fourans[b][0]);
//                     m2.setText(fourans[b][1]);
//                     m3.setText(fourans[b][2]);
//                     m4.setText(fourans[b][3]);
//                 });
//				
//                 itm.setStyle(""
//                 		+ "-fx-text-fill:blue;");
//				}
//             }
//             m.setStyle("-fx-text-fill:blue;");
//             
//             quesdata= Questions[b].split("");
//             bar.getMenus().add(m);
//         }
//       
//         }
//         return bar;
//         
//    }
//   
//    public MenuBar MenubarForN4N5()
//    {
//    	MenuBar bar = new MenuBar();
//        homeController sechome=new homeController();
//        if (sechome.level().equals("N 5")) {
//			level="N5";
//		}else  if (sechome.level().equals("N 4")) {
//			level="N4";
//		}
//        load(String.format("%sTextFile/%s.txt", level,level));
//        Menu mm45 = new Menu("Chapters");
//        for (int i = 1; i <=11; i++) {
//       	int lesson=i;
//            if (i==11) {
//            	MenuItem itm=new MenuItem(String.format("All Chapter"));
//				 mm45.getItems().add(itm);
//				 itm.setOnAction(e->{
//               	 curques=1;
//               	 currentlocation.setStyle("-fx-text-fill:white;");
//                	Title.setText(String.format("%s All Chapters ", level));
//                    load(level.toString()+".txt"); 
//                    main.setText(Questions[b]);
//                    m1.setText(fourans[b][0]);
//                    m2.setText(fourans[b][1]);
//                    m3.setText(fourans[b][2]);
//                    m4.setText(fourans[b][3]);
//                   
//                   
//                });
//				 itm.setStyle("-fx-text-fill:blue;");
//				
//				
//			}else {
//                MenuItem itm = new MenuItem(String.format("Chapters%d", i));
//                mm45.getItems().add(itm);
//                itm.setUserData(String.format("%sTextFile/%sChapter%d.txt",level,level, i));
//                itm.setOnAction(e->{
//                	 curques=1;
//                	 currentlocation.setStyle("-fx-text-fill:white;");
//                	Title.setText(String.format("%s Chapter %d",level,lesson));
//                    System.out.println(itm.getUserData());
//                    load(itm.getUserData().toString()); 
//                    main.setText(Questions[b]);
//                    m1.setText(fourans[b][0]);
//                    m2.setText(fourans[b][1]);
//                    m3.setText(fourans[b][2]);
//                    m4.setText(fourans[b][3]);
//                });
//                itm.setStyle(""
//                 		+ "-fx-text-fill:blue;");
//        }
//        }
//        quesdata= Questions[b].split("");
//        bar.getMenus().add(mm45);
//        bar.setStyle("-fx-font-weight:bold;");
//        return bar;
//        
//    }
//    public String[] loadN2(int start,int end) {
//    	int length=end-start;
//    	
//    	N2arr=new String[length];
//    	for (int i = 0; i <length; i++) {
//			N2arr[i]=kanjiarr[start];
//			start++;
//		}
//    	return N2arr;
//    }
//    
//}
