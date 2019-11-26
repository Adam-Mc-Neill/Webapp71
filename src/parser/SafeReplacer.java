package parser;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SafeReplacer {

	public static String replace(Map<String, String> replacements, String str) {
		int x = 0;
		String regexp = "";
		
		for (String k : replacements.keySet()) {
			if (x != 0) {
				regexp += "|";
			}
			
			regexp += Pattern.quote(k);
			x++;
		}

		StringBuffer sb = new StringBuffer();
		Pattern p = Pattern.compile(regexp);
		Matcher m = p.matcher(str);

		while (m.find()) {
		    m.appendReplacement(sb, Matcher.quoteReplacement(replacements.get(m.group())));
		}
		
		m.appendTail(sb);
		return sb.toString();
	}
	
}
