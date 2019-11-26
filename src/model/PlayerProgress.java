package model;

import java.io.Serializable;


public class PlayerProgress implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public String username;
	public String gameId;
	public String currentSection;
	public String currentStatement;
	public String lastText;
	public String lastImage;
	public String override;
	public String log;
}
