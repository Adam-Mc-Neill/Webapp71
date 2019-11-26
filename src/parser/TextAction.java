package parser;

import org.apache.commons.lang3.StringEscapeUtils;

public class TextAction extends GameAction {
	
	public String args;
	
	public TextAction(String args) {
		this.args = args;
	}
	
	public String toString() {
		return String.format("{'action': 'text', 'args': '%s'}", StringEscapeUtils.escapeEcmaScript(this.args));
	}
	
}
