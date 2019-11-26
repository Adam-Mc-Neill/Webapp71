package views;

import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.apache.commons.lang3.StringEscapeUtils;
import org.h2.mvstore.MVMap;

import java.io.IOException;

import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.WebRequest;
import web.WebResponse;

import views.Navbar;
import model.PlayerProgress;
import model.Game;
import parser.LanguageParser;
import parser.ParserException;
import parser.SafeReplacer;


public class PlayGameView extends DynamicWebPage {

	public PlayGameView(DatabaseInterface db, FileStoreInterface fs) {
		super(db, fs);
	}
	
	private String loadPage(String filePath) {
		String everything = "";
		
		try {
			File fileDir = new File(filePath);
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
			String str;

			while ((str = in.readLine()) != null) {
			    everything += str + "\n";
			}

	        in.close();

		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e){
			System.out.println(e.getMessage());
	    }

		return everything;
	}
	
	public boolean process(WebRequest toProcess) {
		if (toProcess.cookies.containsKey("username")) {
			if (toProcess.path.equals("update_progress")) {
				MVMap<String, PlayerProgress> progress = this.db.s.openMap("PlayerProgress");
				
				PlayerProgress p = new PlayerProgress();
				p.username = toProcess.cookies.get("username");
				p.gameId = toProcess.params.get("gameId");
				p.currentSection = toProcess.params.get("currentSection");
				p.currentStatement = toProcess.params.get("currentStatement");
				p.lastImage = toProcess.params.get("lastImage");
				p.lastText = toProcess.params.get("lastText");
				p.override = toProcess.params.get("override");
				p.log = toProcess.params.get("log");
				
				progress.put(p.username + p.gameId, p);
				this.db.s.commit();
				
				toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_PLAINTEXT, "ok");
				return true;
				
			}
		}
		
		if (!toProcess.path.startsWith("play_game/")) {
			return false;
		}
		
		String gameId = toProcess.path.substring(10);
		MVMap<String, Game> games = db.s.openMap("Games");
		MVMap<String, PlayerProgress> progress = db.s.openMap("PlayerProgress");
		
		Game game = games.get(gameId);

		try {
			LanguageParser.parse(game.code);
		} catch (Exception e) {
			String s = "<!DOCTYPE html>\r\n" + 
					"<html lang=\"en\">\r\n" + 
					"  <head>\r\n" + 
					"    <!-- Required meta tags -->\r\n" + 
					"    <meta charset=\"utf-8\">\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n" + 
					"    \r\n" + 
					"    <link rel=\"stylesheet\" href=\"../css/bootstrap.min.css\">\r\n" + 
					"    <link rel=\"stylesheet\" href=\"../css/PlayGame.css\">\r\n" + 
					"    \r\n" + 
					"    <title>Ooops..</title>\r\n" + 
					"  \r\n" + 
					"  <body style=\"background-color: #2c3e50\">\r\n" + 
					"\r\n" + 
					"    <main role=\"main\">\r\n" + 
					"    \r\n" + 
					"      <div class=\"container-fluid game text-center\">\r\n" + 
					"        <h2 class=\"oops\">Looks like this game has broken :(<br>\r\n" + 
					"        If you are not redirected within 5 seconds click <a class=\"not-active\" href=\"/\"><i>here</i></a></h2>" +
					"      </div>\r\n" + 
					"      \r\n" + 
					"\r\n" + 
					"      \r\n" + 
					"    </main>\r\n" + 
					"    \r\n" + 
					"    <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n" + 
					"    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js\" integrity=\"sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut\" crossorigin=\"anonymous\"></script>\r\n" + 
					"    <script src=\"../js/bootstrap.min.js\"></script>\r\n" + 
					"    <script>setTimeout(function(){window.location = \"/\";}, 5000);</script>" +
					"  </body>\r\n" + 
					"</html>";
			
			toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_HTML, s);
			return true;
		}
		
		String currentSection = "start", currentStatement = "0", lastImage = "", lastText = "", override = "false", log = "";
		
		if (toProcess.cookies.containsKey("username")) {
			if (progress.containsKey(toProcess.cookies.get("username") + gameId)) {
				currentSection = progress.get(toProcess.cookies.get("username") + gameId).currentSection;
				currentStatement = progress.get(toProcess.cookies.get("username") + gameId).currentStatement;
				lastImage = progress.get(toProcess.cookies.get("username") + gameId).lastImage;
				lastText = progress.get(toProcess.cookies.get("username") + gameId).lastText;
				override = progress.get(toProcess.cookies.get("username") + gameId).override;
				
				if (!progress.get(toProcess.cookies.get("username") + gameId).log.isEmpty()) {
					log = progress.get(toProcess.cookies.get("username") + gameId).log;
				}
			}
		}

		//System.out.println(toProcess.path.substring(10));
		String template = loadPage("./httpdocs/PlayGame.html");
		
		HashMap<String, String> replacements = new HashMap<>();
		replacements.put("{{name}}", game.title);
		replacements.put("{{desc}}", game.description);
		replacements.put("{{nav}}", Navbar.getNav(toProcess.cookies, db));
		replacements.put("{{section}}", StringEscapeUtils.escapeEcmaScript(currentSection));
		replacements.put("{{statement}}", currentStatement);
		replacements.put("{{id}}", gameId);
		replacements.put("{{lastImage}}", StringEscapeUtils.escapeEcmaScript(lastImage));
		replacements.put("{{lastText}}", StringEscapeUtils.escapeEcmaScript(lastText));
		replacements.put("{{override}}", override);
		replacements.put("{{log}}", StringEscapeUtils.escapeEcmaScript(log));
		
		try {
			replacements.put("{{javascript}}", LanguageParser.tokensToString(LanguageParser.parse(game.code)));
		} catch (ParserException e) {
			e.printStackTrace();
		}
		
		template = SafeReplacer.replace(replacements, template);

		toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_HTML, template);
		return true;
	}
}
