package parser;

import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.HashMap;

public class ChoiceAction extends GameAction {
	
	public List<HashMap<String, String>> args;
	
	public ChoiceAction(List<HashMap<String, String>> args) {
		this.args = args;
	}
	
	public String toString() {
		String s = "[";
		boolean first = true;
		
		for (HashMap<String, String> h : this.args) {
			boolean f = true;
			String str = "{";
			
			for (String k : h.keySet()) {
				if (f) {
					str += String.format("'%s': '%s'", StringEscapeUtils.escapeEcmaScript(k), StringEscapeUtils.escapeEcmaScript(h.get(k)));
					f = false;
				} else {
					str += String.format(", '%s': '%s'", StringEscapeUtils.escapeEcmaScript(k), StringEscapeUtils.escapeEcmaScript(h.get(k)));
				}
			}
			
			if (first) {
				s += str + "}";
				first = false;
			} else {
				s += ", " + str + "}";
			}
		}
		
		return String.format("{'action': 'choice', 'args': %s}", s + "]");
	}
	
}
