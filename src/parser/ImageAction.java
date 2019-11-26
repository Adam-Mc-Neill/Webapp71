package parser;

import org.apache.commons.lang3.StringEscapeUtils;

public class ImageAction extends GameAction {

	public String args;
	
	public ImageAction(String args) {
		this.args = args;
	}
	
	public String toString() {
		return String.format("{'action': 'image', 'args': '%s'}", StringEscapeUtils.escapeEcmaScript(this.args));
	}
	
}
