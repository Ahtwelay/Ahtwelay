package controllers;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
public class A_Account {
	static int totallength=0;
	public static void Questions() {
		String sql="create table Questions("
				+ "Questions varchar(240),"
				+ "rightans varchar(240), "
				+ "ans1 varchar(240), "
				+ "ans2 varchar(240), "
				+ "ans3 varchar(240), "
				+ "ans4 varchar(240))";
		
		try(Statement st=A_DBcontrol.createconnection().createStatement();){
			st.execute(sql);
			System.out.println("Table is created");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static boolean save(String ques,String right,String ans1,String ans2,String ans3,String ans4) {
		
		String sql="insert into Questions values(?,?,?,?,?,?)";
		try(PreparedStatement pst=A_DBcontrol.createconnection().prepareStatement(sql)){
			pst.setString(1, ques);
			pst.setString(2, right);
			pst.setString(3, ans1);
			pst.setString(4, ans2);
			pst.setString(5, ans3);
			pst.setString(6, ans4);
			System.out.println("success");
			return pst.executeUpdate()>0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	public static void MarkHistory() {
		String sql="create table MarkHistory("
				+"id integer  primary key not null generated always AS identity "
				+ "(start with 100 ,increment by 1),"
				+ "name varchar(30),"
				+ "dob date,"
				+ "Mark varchar(200),"
				+ "percentage varchar(250),"
				+ "quality varchar(50),"
		  		+ "level varchar(30),"
		  		+ "category varchar(30))"
		  		;
		
		try(Statement st=A_DBcontrol.createconnection().createStatement();){
			st.execute(sql);
			System.out.println("Show Table is created");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}
	
	public static boolean saveMarkHistory(String name,String mark,String percen,String quality,String level,String Category) {
		String sql="insert into MarkHistory(name,dob,Mark,percentage,quality,level,category)"
				+ " values(?,?,?,?,?,?,?)";
		
		try(PreparedStatement pst=A_DBcontrol.createconnection().prepareStatement(sql)){
			pst.setString(1,name);
			pst.setString(2, LocalDate.now().toString());
			pst.setString(3, mark);
			pst.setString(4, percen);
			pst.setString(5, quality);
			pst.setString(6, level);
			pst.setString(7, Category);
			System.out.println("success");
			return pst.executeUpdate()>0;
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	//finish part
	
	

	
	public static void main(String[] args) throws SQLException, Exception {
		Questions();
		
//		save("猫", "ねこ", "ねこ", "とり", "ぶた","いぬ");
		showques();
	}
	public static A_Member logincheck(String name,String pass) {
	 	String sql="select * from useraccount where name= ? and password= ?";
		try(PreparedStatement pst=A_DBcontrol.createconnection().prepareStatement(sql)){
			pst.setString(1, name);
			pst.setString(2, pass);
			ResultSet rs=pst.executeQuery();
			
			if (rs.next()) {
				A_Member m=new A_Member(rs.getString(1), rs.getString(2));	
				return m;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
		
		
	}
	
	//finish part
	public static ArrayList<A_ShowMarkHistory>showall(){
		
		String sql="select * from MarkHistory ";
		ArrayList<A_ShowMarkHistory> list=new ArrayList<A_ShowMarkHistory>();
		try(PreparedStatement pst=A_DBcontrol.createconnection().prepareStatement(sql)){
			ResultSet rs=pst.executeQuery();
			while (rs.next()) {
				System.out.println();
				A_ShowMarkHistory m=new A_ShowMarkHistory(rs.getInt(1),rs.getString(2), LocalDate.parse(rs.getString(3)), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
				
				list.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	
	}
	public static void showques(){
		
		String sql="select * from Questions ";
		try(PreparedStatement pst=A_DBcontrol.createconnection().prepareStatement(sql)){
			ResultSet rs=pst.executeQuery();
			int count=rs.getMetaData().getColumnCount();
			while (rs.next()) {
				for (int i =1; i <=count; i++) {
					System.out.print(rs.getString(i)+"\t");
					
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	

	
	
}
