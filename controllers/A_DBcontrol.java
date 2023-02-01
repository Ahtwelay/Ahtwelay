package controllers;

import java.sql.Connection;
import java.sql.DriverManager;

public class A_DBcontrol {
	
public static Connection createconnection()throws Exception{
//	return DriverManager.getConnection("jdbc:mysql://localhost/jlpa","root"," ");
	return DriverManager.getConnection("jdbc:derby:newacc2;create=true;");
}
}
