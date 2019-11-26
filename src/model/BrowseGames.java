package model;

import java.io.Serializable;
import java.util.ArrayList;

public class BrowseGames implements Serializable {
	private static final long serialVersionUID = 1L;
	ArrayList<String> games = new ArrayList<String>();
	public String filepathToImage;
	public String gameTitle;
	public String gameDescription;
	public String gameRating;
	
}
