package model;

import java.io.Serializable;


public class Game implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public String id;
	public String title;
	public String description;
	public String code;
	public String thumbnail;
	public String username;
}
