package controllers;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ResourceBundle;

//import SchoolProject.Createaccount;
//import SchoolProject.Login;
//import SchoolProject.MarkHistory;
//import SchoolProject.ClassFileN2.N2Home;
//import SchoolProject.ClassFileN3.N3Home;
//import SchoolProject.ClassFileN4.N4Home;
//import SchoolProject.ClassFileN5.N5Home;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import javafx.stage.Stage;
import javafx.stage.Window;
import loader.Fxloader;

public class homeController  {
	private static Object SkillLevel=null;
	public static String mylocationclassToback="home";

	
    @FXML
    private Menu MenuGetmoreapps;

    @FXML
    private MenuItem MitemMetroSA;

    @FXML
    private Menu MenuSettings;

    @FXML
    private MenuItem MenuDevelopby;
    @FXML
    private MenuItem MitemRole;
    
   
  
    
    @FXML
    private MenuItem MitemHistory;

    @FXML
    private MenuItem MitemExit;

    @FXML
    private Menu MenuHelp;

    @FXML
    private MenuItem MitemShortcut;

    @FXML
    private MenuItem MitemHowtouse;
    @FXML
    private MenuItem Mitemerror;
    @FXML
    private MenuItem Mitemwrong;
    @FXML
    private MenuItem Mitemabout;

    @FXML
    private Button btnN5;

    @FXML
    private Button btnN4;

    @FXML
    private Button btnN3;

    @FXML
    private Button btnN2;
    @FXML
    private Button btnN1;
    
    @FXML
    private MenuItem MitemAddKanji;
    
//    @FXML
//    .getIcons().add(new Image(this.getClass().getResourceAsStream("japanese-culture.jpg")));
  
    
//    sc.addMnemonic(mm1);
    
//    KeyCombination km2=new KeyCodeCombination(KeyCode.DIGIT2,KeyCombination.ALT_DOWN);
//    Mnemonic mm2=new Mnemonic(m2,km2);
//    sc.addMnemonic(mm2);
//    KeyCombination km3=new KeyCodeCombination(KeyCode.DIGIT3,KeyCombination.ALT_DOWN);
//    Mnemonic mm3=new Mnemonic(m3,km3);
//    sc.addMnemonic(mm3);
//    KeyCombination km4=new KeyCodeCombination(KeyCode.DIGIT4,KeyCombination.ALT_DOWN);
//    Mnemonic mm4=new Mnemonic(m4,km4);
//    sc.addMnemonic(mm4);
    @FXML
     void btnN1OnAction(ActionEvent event) throws Exception {
    	N1 n1=new N1();
		n1.start(new Stage());
		((Node)btnN2).getScene().getWindow().hide();
    }
    @FXML
    void MitemAddKanjiOnAction(ActionEvent event){
        new Fxloader((Stage)((Node)btnN2).getScene().getWindow(), "/view/AddQuestion.fxml");

    }

	@FXML
    void MenuGetmoreappsOnAction(ActionEvent event) {
    	try {
			Desktop.getDesktop().browse(new URL("https://yannainghwe287.wixsite.com/metrosa").toURI());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
    }

    @FXML
    void MenuHelpOnAction(ActionEvent event) {
    	
    }

    @FXML
    void MenuSettingsOnAction(ActionEvent event) {

    }

    @FXML
    void MenuDevelopbyOnAction(ActionEvent event) throws URISyntaxException {
//    	new Alert(AlertType.INFORMATION,"This app is developed by 'Japanese Pro' Project Members"
//				+ "\n(Yan Naing Htwe,\n"
//				+ "Si Thu Bo San ,\n"
//				+ "Thar Htet Nyan,\n"
//				+ "Sett Thiha Naing,\n"
//				+ "May Pyae Sone)",ButtonType.OK).showAndWait();
    }

    
   
    

    @FXML
    void MitemExitOnAction(ActionEvent event) {
    	Alert a=new Alert(AlertType.INFORMATION,"Do you want to exit?",ButtonType.YES,ButtonType.NO);
		if (a.showAndWait().get()==ButtonType.YES) {
			System.exit(0);
		}
    }

    @FXML
    void MitemHowtouseOnAction(ActionEvent event) throws URISyntaxException, IOException {
    	String text="	          	Warmly Welcome from JLS\r\n" + 
    			"\r\n" + 
    			"***Using this system,you can save the time to collect data and easily learn with the stylish view of the system.\r\n" + 
    			"			Now we're going to show you the use of our system.***\r\n" + 
    			"\r\n" + 
    			"1.The very first thing is to create your own account.\r\n" + 
    			"2.Can select the desired courses of japanese levels.\r\n" + 
    			"3.After choosing the level,you can conveniently make a choice of practicing kanji lessons and learning and parcticing grammar lessons.\r\n" + 
    			"4.Once you feel the tiredness from studying,there is an entertainment section to stress out.\r\n" + 
    			"5.In this section,there is a wide variety of enjoyable movies,animes,mtv and etc.,So everyone can choose their likes in a short time without browsing in any other websites.\r\n" + 
    			"6.If you are the one who is crazy at reading the books,this system provides u with the link of books which leads u to the mega cloud website to download as the pdf file so that u can easily read.\r\n" + 
    			"7.After studying with JLS,you surely are going to improve your skills.So if you're intended to study O.Q,we give u the some reliable website link for ur better educational studies.\r\n" + 
    			"\r\n" + 
    			"Thanks for using our system as the tool to improve your japanese skills.\r\n" + 
    			"We,developer team,appreciately precious you for choosing us.\r\n" + 
    			"\r\n" + 
    			"\r\n" + 
    			"";
//    	Path p=Paths.get("C:/How to use.txt");
//    	Files.write(p, text.getBytes(), 
//				StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
//			Desktop.getDesktop().open(p.toFile());
			new Alert(AlertType.INFORMATION,text,ButtonType.CLOSE).showAndWait();
			
    }
    @FXML
    void MitemWrongOnAction(ActionEvent event) throws URISyntaxException, IOException {
    	
    	Path p=Paths.get("C:/Users/Lenovo/Documents/wrong.txt");
    	
    	Desktop.getDesktop().open(p.toFile());
    	
    	
    }
    @FXML
    void MitemAboutOnAction(ActionEvent event) throws URISyntaxException, IOException {
    	String text="This app is for student who is learning Japanese Language.\nCredit All Links We Used.";
    	Path p=Paths.get("C:/Users/Lenovo/Documents/About.txt");
    	Files.write(p, text.getBytes(), 
    			StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
    	Desktop.getDesktop().open(p.toFile());
    	
    	
    }

  
    @FXML
    void MitemerrorOnAction(ActionEvent event) throws MalformedURLException, IOException, URISyntaxException {
		Desktop.getDesktop().browse(new URL("https://tttartar29581.wixsite.com/JapaneseLearning/contact").toURI());

    }
    @FXML
    void MitemMetroSAOnAction(ActionEvent event) throws MalformedURLException, IOException, URISyntaxException {
    	Desktop.getDesktop().browse(new URL("https://yannainghtwe287.wixsite.com/metrosa").toURI());
    	
    }


    @FXML
    void MitemShortcutOnAction(ActionEvent event) throws URISyntaxException, IOException {
    	String text="***Shortcut key for Japanese Learning System***\nHome(ALT+M)\nBack(ALT+B)\nSave(ALT+S)\nHistory(ALT+H)\nPre Button(ALT+P)\nNext Button(ALT+N)\nFirst Answer(ALT+1)\nSecond Answer(ALT+2)\nThird Answer(ALT+3)\nFourth Answer(ALT+4)";
//    	Path p=Paths.get("C:/Users/Lenovo/Documents/shortcut.txt");
//    	Files.write(p, text.getBytes(), 
//				StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
//			Desktop.getDesktop().open(p.toFile());
    	new Alert(AlertType.INFORMATION,text,ButtonType.CLOSE).showAndWait();
    }
    @FXML
    void MitemyanOnAction(ActionEvent event) throws URISyntaxException, MalformedURLException, IOException {
    	Desktop.getDesktop().browse(new URL("https://web.facebook.com/Marsarain?epa=SEARCH_BOX").toURI());

    }
    @FXML
    void MitemsithuOnAction(ActionEvent event) throws URISyntaxException, MalformedURLException, IOException {
    	Desktop.getDesktop().browse(new URL("https://web.facebook.com/aid3n.mobiusTbs/").toURI());

    }
    @FXML
    void MitemtharOnAction(ActionEvent event) throws URISyntaxException, MalformedURLException, IOException {
    	Desktop.getDesktop().browse(new URL("https://web.facebook.com/profile.php?id=100007202387389").toURI());

    }
    @FXML
    void MitemsetOnAction(ActionEvent event) throws URISyntaxException, MalformedURLException, IOException {
    	Desktop.getDesktop().browse(new URL("https://web.facebook.com/mo.thwin").toURI());

    }
    @FXML
    void MitemmayOnAction(ActionEvent event) throws URISyntaxException, MalformedURLException, IOException {
    	Desktop.getDesktop().browse(new URL("https://web.facebook.com/maypyae.sonee").toURI());

    }
    

    @FXML
    void MitemSelectRole(ActionEvent event) {
        new Fxloader((Stage)((Node)event.getSource()).getScene().getWindow(), "/A_ShowMarkHistory.java");

    	
    }
    @FXML
    void MitemHistory(ActionEvent event) throws Exception {
    	System.out.println("Hi");
    	A_MarkHistory history=new A_MarkHistory();
		history.start(new Stage());
		((Node)btnN2).getScene().getWindow().hide();
    	
    }
    @FXML
    void btnN2OnAction(ActionEvent event) throws Exception {
    	N2 kanji=new N2();
		kanji.start(new Stage());
		((Node)event.getSource()).getScene().getWindow().hide();
//    	SkillLevel=btnN2.getText();
//    	play(event);
    }
    
    @FXML
    void btnN3OnAction(ActionEvent event) throws Exception {
    	SkillLevel=btnN3.getText();
    	play(event);
    }

    @FXML
    void btnN4OnAction(ActionEvent event) throws Exception {
    	SkillLevel=btnN4.getText();
    	play(event);
    }

    @FXML
    void btnN5OnAction(ActionEvent event) throws Exception {
    	SkillLevel=btnN5.getText();
    	
    	play(event);
    }
    public  String level() {
    	String jlevel=SkillLevel.toString();
    	return jlevel;
    
    }
    public void play(ActionEvent event) throws Exception {

    	KanjiPractice kanji=new KanjiPractice();
		kanji.start(new Stage());
		((Node)event.getSource()).getScene().getWindow().hide();
		}
    public Object  exit() {
    	return (Object)MitemExit;
    }

	
    }
    


