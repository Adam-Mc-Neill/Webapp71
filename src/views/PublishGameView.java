package views;

import java.time.Instant;

import org.h2.mvstore.MVMap;

import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.WebRequest;
import web.WebResponse;
import model.Account;
import model.Game;
import parser.LanguageParser;


public class PublishGameView extends DynamicWebPage {

	public PublishGameView(DatabaseInterface db, FileStoreInterface fs) {
		super(db, fs);
	}
	
	public boolean process(WebRequest toProcess) {
		if (toProcess.path.equals("publish")) {
			if (toProcess.cookies.get("username") == null) {
				WebResponse w = new WebResponse(WebResponse.HTTP_REDIRECT, WebResponse.MIME_HTML, "ok");
				w.header.put("Location", "/login.html");
				toProcess.r = w;
				return true;
			}
			
			String resp = "<!DOCTYPE html>\r\n" + 
					"<html lang=\"en\">\r\n" + 
					"  <head>\r\n" + 
					"    <!-- Required meta tags -->\r\n" + 
					"    <meta charset=\"utf-8\">\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n" + 
					"    \r\n" + 
					"    <link rel=\"stylesheet\" href=\"./css/bootstrap.min.css\">\r\n" + 
					"    <link rel=\"stylesheet\" href=\"./css/PlayGame.css\">\r\n" + 
					"    \r\n" + 
					"    <title>Publish Game</title>\r\n" + 
					"  \r\n" + 
					"  <body>\r\n" ; 
					
					resp += Navbar.getNav(toProcess.cookies, db);
					
					resp += ""+
					"\r\n" + 
					"    <main role=\"main\">\r\n" + 
					"    \r\n" + 
					"      <div class=\"container-fluid\">\r\n" + 
					"      \r\n" + 
					"        <div class=\"row justify-content-center\">\r\n" + 
					"          <div class=\"col game img-overlay\">\r\n" + 
					"             <form action=\"/lab6-write\">\r\n" + 
					"             \r\n" + 
					"              <div class=\"form-group\">\r\n" + 
					"                <label for=\"title\">Game Title:</label>\r\n" + 
					"                <input type=\"text\" class=\"form-control\" id=\"title\" name=\"title\" required>\r\n" + 
					"              </div>\r\n" + 
					"              \r\n" + 
					"              <div class=\"form-group\">\r\n" + 
					"                <label for=\"desc\">Game Description:</label>\r\n" + 
					"                <input type=\"text\" class=\"form-control\" id=\"desc\" name=\"desc\" required>\r\n" + 
					"              </div>\r\n" + 
					"  \r\n" + 
					"              <div class=\"form-group\">\r\n" + 
					"                <label for=\"thumbnail\">Game Thumbnail:</label>\r\n" + 
					"                <input type=\"text\" class=\"form-control\" id=\"desc\" name=\"thumbnail\" required>\r\n" + 
					"              </div>\r\n" + 
					"  \r\n" + 
					"              <div class=\"form-group\">\r\n" + 
					"                <label for=\"game-code\">Game Code:</label>\r\n" + 
					"                <textarea class=\"form-control\" id=\"game-code\" rows=\"5\" name=\"game-code\" required></textarea>\r\n" + 
					"              </div>\r\n" + 
					"              \r\n" + 
					"              <button type=\"submit\" class=\"btn btn-dark\">Publish</button>\r\n" + 
					"            </form> \r\n" + 
					"          </div>\r\n" + 
					"        </div>\r\n" + 
					"      </div>\r\n" + 
					"      \r\n" + 
					"    </main>\r\n" + 
					"    \r\n" + 
					"    <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n" + 
					"    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js\" integrity=\"sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut\" crossorigin=\"anonymous\"></script>\r\n" + 
					"    <script src=\"./js/bootstrap.min.js\"></script>\r\n" + 
					"  </body>\r\n" + 
					"</html>";
			
			toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_HTML, resp);
			return true;
		} else if (toProcess.path.equals("lab6-write")) {
			Game g = new Game();
			g.id = String.valueOf(Instant.now().getEpochSecond());
			g.title = toProcess.params.get("title");
			g.description = toProcess.params.get("desc");
			g.code = toProcess.params.get("game-code");
			g.thumbnail = toProcess.params.get("thumbnail");
			g.username = toProcess.cookies.get("username");
			
			try {
				LanguageParser.parse(g.code);
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
						"        <h2 class=\"oops\">Could not upload game :(<br>\r\n" + 
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
			
			MVMap<String, Game> m = this.db.s.openMap("Games");
			m.put(g.id, g);
			this.db.s.commit();
			
			WebResponse w = new WebResponse(WebResponse.HTTP_REDIRECT, WebResponse.MIME_PLAINTEXT, "ok");
			w.header.put("Location", "/play_game/" + g.id);
			
			toProcess.r = w;
			return true;
		}
		
		return false;
	}
}
