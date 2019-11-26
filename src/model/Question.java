package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {
	
	public long ID;
	public String title;
	public String question;
	public ArrayList <Answer> answers = new ArrayList<Answer>();
	public String date;
	public String username;
	
	

}
