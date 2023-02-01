package controllers;

import java.time.LocalDate;

public class A_ShowMarkHistory {
	private int id;
	private String name;
	private LocalDate date;
	private String mark;
	private String percen;
	private String quality;
	private String level;
	private String category;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getPercen() {
		return percen;
	}
	public void setPercen(String percen) {
		this.percen = percen;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public A_ShowMarkHistory(int id, String name, LocalDate date, String mark, String percen, String quality,
			String level, String category) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.mark = mark;
		this.percen = percen;
		this.quality = quality;
		this.level = level;
		this.category = category;
	}
	@Override
	public String toString() {
		return name + ":" +  level  + ":" + mark + "marks:"
				+ percen + ": quality=" + quality + ":" + date + ":" + category + "\n";
	}
	
	

}
