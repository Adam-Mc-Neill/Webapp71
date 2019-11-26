package parser;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringEscapeUtils;

public class LanguageParser {
	
	public static String tokensToString(HashMap<String, List<GameAction>> tokens) {
		String s = "{";
		boolean first = true;
		
		for (String k : tokens.keySet()) {
			if (first) {
				s += String.format("'%s': %s", StringEscapeUtils.escapeEcmaScript(k), tokens.get(k));
				first = false;
			} else {
				s += String.format(", '%s': %s", StringEscapeUtils.escapeEcmaScript(k), tokens.get(k));
			}

		}
		
		return s + "}";
	}
	
	public static HashMap<String, List<GameAction>> parse(String text) throws ParserException {
		HashMap<String, List<GameAction>> tokens = new HashMap<>();
		
		int lineNumber = 0;
		String currentRoute = null;
		
		for (String line : text.split("\n")) {
			
			if (line == null || line.isEmpty()) continue;
			
			if (line.startsWith("route:")) {
				String route = line.substring(6).trim();
				
				if (currentRoute != null || tokens.containsKey(route)) {
					throw new ParserException("Unexpected :route: declaration.", lineNumber);
				} else {
					currentRoute = route;
					tokens.put(route, new ArrayList<GameAction>());
				}
				
			} else if (line.startsWith("end:")) {
				String route = line.substring(4).trim();
				
				if (currentRoute == null || !tokens.containsKey(route)) {
					throw new ParserException("Unexpected :end route: declaration.", lineNumber);
				} else {
					currentRoute = null;
				}
				
			} else if (line.startsWith("text:")) {
				if (currentRoute == null) throw new ParserException("No route defined.", lineNumber);
				
				String txt = line.substring(5).trim();
				tokens.get(currentRoute).add(new TextAction(txt));
				
			} else if (line.startsWith("image:")) {
				if (currentRoute == null) throw new ParserException("No route defined.", lineNumber);
				
				String im = line.substring(6).trim();
				tokens.get(currentRoute).add(new ImageAction(im));
				
			} else if (line.startsWith("choice:")) {
				if (currentRoute == null) throw new ParserException("No route defined.", lineNumber);
				
				String choiceString = line.substring(7).trim();
				List<HashMap<String, String>> choices = new ArrayList<>();
				
				for (String choice : choiceString.split("~")) {
					String[] args = choice.split("\\|");
					HashMap<String, String> ch = new HashMap<>();
					
					if (args[0].trim().equals("continue")) {
						ch.put("action", "continue");
						ch.put("choice", args[1].trim());
						
					} else if (args[0].trim().equals("divert")) {
						ch.put("action", "divert");
						ch.put("target", args[1].trim());
						ch.put("choice", args[2].trim());
						
					} else if (args[0].trim().equals("speak")) {
						ch.put("action", "speak");
						ch.put("text", args[1].trim());
						ch.put("choice", args[2].trim());
					}
					
					choices.add(ch);
				}
				
				tokens.get(currentRoute).add(new ChoiceAction(choices));
			}
			
			lineNumber++;
			
		}
		
		if (!tokens.containsKey("start")) {
			throw new ParserException("No main route found.", lineNumber);
		}
		
		if (currentRoute != null) {
			throw new ParserException("Route not closed.", lineNumber);
		}
		
		return tokens;
	}
	
	/*
	public static void main(String[] args) throws ParserException {

	}*/

}
