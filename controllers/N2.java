package controllers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;



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
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import loader.Fxloader;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

public class N2 extends Application {
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
	    int total=2211;
	    private  String kanjiarr[]=new String[total];
	    private  String kataarr[]=new String[total];
		private  String myanmararr[]=new String[total];
		
		static int rowno=0;
		static int increamarr=0;
		static Button show=new Button("show");
		static Label myanmar=null;
		static int end=0;
		static int start=0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage st) throws Exception {
    
        BorderPane root = new BorderPane();
      
        	level="N2";
        	root.setTop(MenubarForN2());
        
        
       
        Title.setText(level); 		
        //Menu part  is finished
        
        strokebox.getSelectionModel().select("漢字");
       
      
        
        main = new Label(kanjiarr[b]);
        System.out.println(kanjiarr[b]);
        m1 = new Button(kataarr[b]);
        m2 = new Button(kataarr[b+1]);
        m3 = new Button(kanjiarr[b+2]);
        m4 = new Button(kataarr[b+3]);
//        HBox box = new HBox(m1, m2, m3, m4);
        Button btnarr[] = {m1, m2, m3, m4};
        for (i = 0; i < btnarr.length; i++) {
            btnarr[i].setOnAction(ee -> {
                play(ee);
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

        HBox homebox = new HBox(12,Home,show);
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
        center.add(myanmar, 3, 2,2,1);
        GridPane.setHalignment(main, HPos.CENTER);
        GridPane.setHalignment(Title, HPos.CENTER);

        pre.setPrefWidth(60);
        next.setPrefWidth(60);
        show.setPrefWidth(60);
        //style start
        myanmar.setVisible(false);
        show.setOnAction(e->{
        	if (show.getText().equals("show")) {
        		show.setText("hide");
				myanmar.setVisible(true);
				
			}else {
				myanmar.setVisible(false);
				show.setText("show");
			}
        	
        });
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
        currentlocation.setText("current:1/2008 ");
        stroke.setStyle(
        		"-fx-border-color:linear-gradient(from 0% 0% to 100% 100%,aqua,aqua);"
        				+ "-fx-border-radius:10%;"
        				+ "-fx-background-radius:10%;"
        				+ "-fx-background-color:transparent;"
        				+ "-fx-text-fill:white;"
        				+ "-fx-cursor:Hand;"
        				+"-fx-font-weight:bold;"
        				+ "-fx-font-size:1.1em;");
        show.setStyle(
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
                + "-fx-padding:30 0 0 120;");
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
        	b=0;
        	rowno=0;
        	curques=1;
            new Fxloader((Stage)((Node)e.getSource()).getScene().getWindow(), "/view/home.fxml");
        });
      //next button action
        next.setOnAction(e -> {
        	strokebox.getItems().clear();
            b++;
            System.out.println(b);
            
            if (b == end) {
            	 curques=1;
            	Alert end=new Alert(AlertType.WARNING,"You reached End.Do you want to Replay",ButtonType.YES,ButtonType.NO);
            	if (end.showAndWait().get()==ButtonType.YES) {
            		 b = start;
            		 refresh();
            		 currentlocation.setStyle("-fx-text-fill:white;");
				}
            }
            currentlocation.setText("Current :"+curques+"/"+queslength);
            curques++;
            if (b == end) {
            	
            	
            	currentlocation.setStyle("-fx-text-fill:green;");
                }
            refresh();

            strokebox.getSelectionModel().select("漢字");
//            for ( i = 0; i < quesdata.length; i++){
//            	 strokedata=i;
//    			strokebox.getItems().addAll(quesdata[i]);
//    			
//    		}
            for (i = 0; i < btnarr.length; i++) {
                btnarr[i].setOnAction(ee -> {
                    play(ee);
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

          
          refresh();
            quesdata= kanjiarr[b].split("");
            strokebox.getSelectionModel().select("漢字");
            for ( i = 0; i < quesdata.length; i++){
            	 strokedata=i;
    			strokebox.getItems().addAll(quesdata[i]);
    			
    		}
            for (i = 0; i < btnarr.length; i++) {
                btnarr[i].setOnAction(ee -> {
                    play(ee);
                });
            }
           
           
        });
      
    }

    public void play(ActionEvent e) {
        Button clicked = (Button) e.getSource();
        String data = (String) clicked.getText();
        if (data.equalsIgnoreCase(kataarr[b])) {
            Alert a = new Alert(AlertType.INFORMATION, "Congratulations!Do you want to Continue next quiz?", ButtonType.YES, ButtonType.NO);
            if (a.showAndWait().get() == ButtonType.YES) {
                mark++;
                currentlocation.setText("Current :"+curques+"/"+queslength);
                
               
                b++;
                refresh();
                if (b ==end) {
                	 curques=1;
                	Alert end=new Alert(AlertType.WARNING,"You reached End.Do you want to Replay",ButtonType.YES,ButtonType.NO);
                	if (end.showAndWait().get()==ButtonType.YES) {
                		 b = start;
                		 refresh();
                		
                		 currentlocation.setStyle("-fx-text-fill:white;");
    				}
                }
                curques++;
                if (b == end-1) {
                	currentlocation.setStyle("-fx-text-fill:green;");
                	 curques=1;
                	 
                }
               
                
              
                strokebox.getItems().clear();
                quesdata= kanjiarr[b].split("");
                strokebox.getSelectionModel().select("漢字");
                for ( i = 0; i < quesdata.length; i++){
                	 strokedata=i;
        			strokebox.getItems().addAll(quesdata[i]);
        			
        		}

                System.out.println(queslength);
                if (wrongClick > 0) {
                    wrongMark++;
                    
                    wrongClick = 0;
                    Path p=Paths.get("C:/Users/Lenovo/Documents/wrong.txt");
                    allwrongquestion=(kanjiarr[b-1]+"="+kataarr[b]+" "+LocalDate.now()).toString();
                	try {
                			Files.write(p,
                					Arrays.asList(allwrongquestion),
                					Charset.forName("UTF-8"),
                					StandardOpenOption.CREATE,
                					StandardOpenOption.APPEND
                					);
                        
					} catch (Exception e2) {
						// 
						e2.printStackTrace();
					}
                }
            }
        } else {
            new Alert(AlertType.WARNING, "Sorry,Your choose Wrong", ButtonType.OK).showAndWait();
            
            wrongClick++;

        }
    }
    public MenuBar MenubarForN2()throws Exception {
    	 MenuBar bar = new MenuBar();
    		String excelFilePath = "C:/Users/Yan Naing Htwe/Documents/Mine/extra/n2.xlsx";
    		
  	        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
//  	        InputStreamReader is=new InputStreamReader(getClass().getResourceAsStream("n2full.xlsx") , "UTF-8");
  	        
  	        Workbook workbook = new XSSFWorkbook(inputStream);
  	        Sheet firstSheet = workbook.getSheetAt(0);
  	        Iterator<Row> iterator = firstSheet.iterator();
  	         
  	        while (iterator.hasNext()) {
  	            Row nextRow = iterator.next();
  	            Iterator<Cell> cellIterator = nextRow.cellIterator();
  	             
  	            while (cellIterator.hasNext()) {
  	                Cell cell = cellIterator.next();
  	                        int columnIndex = cell.getColumnIndex();
  	                        
  	                        switch (columnIndex) {
  	                        case 0:
  	                            kanjiarr[rowno]=cell.getStringCellValue();
  	                        case 1:
  	                        	 kataarr[rowno]=cell.getStringCellValue();
  	                        case 2:
  	                        	 myanmararr[rowno]=cell.getStringCellValue();
  	                        	 
  	                       
  	                        }
  	            }
  	            
  	            System.out.println();
  	            rowno++;
  	        }
  	        
  	      myanmar=new Label(myanmararr[b]);
	      myanmar.setFont(Font.loadFont(getClass().getResourceAsStream("PYIDAUNGSU-2.5.3_BOLD.TTF"),18));

  	        workbook.close();
         for ( i = 1; i <=9; i++) {
        	 if (i==9) {
        		 Menu m=new Menu("All");
 				MenuItem playAll=new MenuItem("All chapters");
 				m.getItems().add(playAll);
 				playAll.setOnAction(e->{
 					 curques=1;
                 	 currentlocation.setStyle("-fx-text-fill:white;");
                 	 currentlocation.setText("Current:"+curques+"/"+total);
                 	 queslength=total;
                  	Title.setText("N2 All Chapters ");
                      main.setText(kanjiarr[b]);
                      m1.setText(kataarr[b]);
                      m2.setText(kataarr[b+1]);
                      m3.setText(kataarr[b+2]);
                      m4.setText(kataarr[b+3]);
                      loadN2(0, 2008);
 				});
 				bar.getMenus().add(m);
			}else {
             Menu m = new Menu(String.format("第%d週", i));
             
             for ( j = 1; j <=8; j++) {
             	int week=i; 	
             	int day=j;
             	int all=275;
             	
             	if (j==8) {
             		MenuItem itm=new MenuItem(String.format("第%d週All", i));
					 m.getItems().add(itm);
					 itm.setOnAction(e->{
	                	 curques=1;
	                	 currentlocation.setStyle("-fx-text-fill:white;");
	                 	Title.setText(String.format("N2第%d週 All Chapters ", i));
	                     loadN2((all*week)-275,(all*week) );
	                     currentlocation.setText("Current:"+curques+"/"+queslength);
	                    refresh();
	                    
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
                	 loadN2(10, 20);
                	 loadN2(40*(day-1)+((week-1)*275),39*day+(week-1)*275 );
                     currentlocation.setText("Current:"+curques+"/"+queslength);
                       
                  refresh();
                 });
                 itm.setStyle(""
                   		+ "-fx-text-fill:blue;");
             }
             }
             bar.getMenus().add(m);
             bar.setStyle("-fx-font-weight:bold;-fx-background-color:white;");
             
         }
         }
          return bar;
    }
     public void loadN2(int startpoint,int endpoint) {
    	 myanmar.setText(myanmararr[b]);
    	int length=endpoint-startpoint;
    	end=endpoint;
    	start=startpoint;
    	queslength=length;
    	queslength=length;
//    	System.out.println(length);
    	b=start;
//    	b=0;
//    	int l=2191-b;
//    	System.out.println(endpoint+"-"+startpoint+":"+queslength);
//    	for (int i = 0; i < l; i++)
//    		
//    	{
//    		  System.out.print(kanjiarr[b+i]+"-"+kataarr[b+i]+"-"+kataarr[b+i]+","+kataarr[b+1+i]+","+kataarr[b+2+i]+","+kataarr[b+3+i]+"/");
//    		  
//		}
    	
    }
     public void refresh() {
    	 myanmar.setText(myanmararr[b]);
    	 main.setText(kanjiarr[b]);
         m1.setText(kataarr[b]);
         m2.setText(kataarr[b+1]);
         m3.setText(kataarr[b+2]);
         m4.setText(kataarr[b+3]);
//         System.out.println(kanjiarr[b]+"-"+kataarr[b]+"-"+kataarr[b]+","+kataarr[b+1]+","+kataarr[b+2]+","+kataarr[b+3]);
     }
    
}
