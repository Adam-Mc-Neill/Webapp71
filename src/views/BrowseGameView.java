package views;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.h2.mvstore.MVMap;

import model.Account;
import model.BrowseGames;
import model.Game;
import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.WebRequest;
import web.WebResponse;

import views.Navbar;


public class BrowseGameView extends DynamicWebPage {

	public BrowseGameView(DatabaseInterface db, FileStoreInterface fs) {
		super(db, fs);
	}
	public boolean process(WebRequest toProcess)
	{
		String stringToSendToWebBrowser = "<!DOCTYPE html>\r\n" + 
		"<html>\r\n" + 
		"\r\n" + 
		"<head>\r\n" + 
		"<style>.card {margin-top:10px;}</style>\r\n"+
		"  <meta charset=\"utf-8\">\r\n" + 
		"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
		"  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\" type=\"text/css\">\r\n" + 
		"  <link rel=\"stylesheet\" href=\"https://static.pingendo.com/bootstrap/bootstrap-4.2.1.css\">\r\n" + 
		"<style>\r\n" + 
		".navbar {\r\n" + 
		"  margin-bottom: 0; \r\n" + 
		"}\r\n" + 
		"\r\n" +
		".card-header {\r\n" +
		"  background-color: #2EAAA8;\r\n" + 
		"  color: #fff;\r\n" +
		"  text-align: center;\r\n" +
		"  font-weight: bold;\r\n" +
		"}\r\n" +
		".alink:link {\r\n" + 
		"  color: #2EAAA8;\r\n" + 
		"}\r\n" +
		".card-body {\r\n" +
		"  background-color: #2f3542;\r\n" + 
		"  color: #fff;\r\n" +
		"}\r\n" +
		".bg-blue {\r\n" + 
		"  background-color: #2f3542;\r\n" + 
		"}\r\n" + 
		"body  {\r\n" + 
		"  margin-top: 50px;\r\n" + 
		"}\r\n" +
		"\r\n" + 
		".text-upper {\r\n" + 
		"  text-transform: uppercase;\r\n" + 
		"}\r\n" + 
		"\r\n" + 
		".nav-brand {\r\n" + 
		"  color: #ced6e0;\r\n" + 
		"  font-weight: 700;\r\n" + 
		"  margin-right: 80px;\r\n" + 
		"  margin-left: 20px;\r\n" + 
		"}\r\n" + 
		".para1 {\r\n" +
		"  border-bottom: 1px solid #2EAAA8;\r\n" +
		"  padding-bottom: 10px;\r\n" +
		"}\r\n" +
		".para2 {\r\n" +
		"  border-bottom: 1px solid #2EAAA8;\r\n" +
		"  padding-bottom: 10px;\r\n" +
		"}\r\n" +
		".input-group {\r\n" +
		"  padding-bottom:20px; \r\n" +
		"}\r\n" +
		".th, td {\r\n" +
		"  padding: 10px; \r\n" +
		"  border-bottom: 1px solid #2EAAA8;\r\n" + 
		"}\r\n" +
		".h4 {\r\n" +
		" text-align:center; \r\n" +
		"}\r\n" +
		".carousel-inner img {\r\n" +
		"  height: 400px; \r\n" +
		"  margin: auto; \r\n" +
		"}\r\n" +
		"</style>"+
		"</head>\r\n" + 
		"\r\n" + 
		"<body style=\"background-color: #575761\" class=\"\">\r\n";
		
		stringToSendToWebBrowser += Navbar.getNav(toProcess.cookies, db);
		
		stringToSendToWebBrowser += "<div class=\"py-5\">\r\n" + 
				"    <div class=\"container\">\r\n" + 
				"      <div class=\"row\">\r\n" + 
				"        <div class=\"col-md-12\">\r\n" + 
				"          <div class=\"card\">\r\n" + 
				"            <div class=\"card-header\" ></div>\r\n" + 
				"            <div class=\"card-body\">\r\n" + 
				"              <h4 class = \"head1\" align = \"center\"><b>WELCOME TO ADVENTURE71</b></h4>\r\n" + 
				"			   <p> </p>\r\n" +		
				"              <p class = \"para1\">Adenture71 is the home of interactive adventure games, whether you want to see your ideas come to life or want a classic gaming experience</p>\r\n" +
				"			   <p class = \"para2\"><b>CREATE</b> your own interactive adventure game exactly as youve always imagined it for other fans of the genre throughout the world. Its easy, intuitive and free!<a href=\"/login.html\"> Log in or Sign up to get started</a></p>\r\n" +
				"			   <p><b>PLAY</b> other great user created games as many times as you want, completely free of charge. You don't even need an account to get started, just choose from one of our great games below!</p>\r\n" +					
				"            </div>\r\n" + 
				"          </div>\r\n" + 
				"        </div>\r\n" + 
				"      </div>\r\n" + 
				"    </div>\r\n" + 
				"  </div>"+
				"<div class=\"py-5\">\r\n" + 
				"    <div class=\"container\">\r\n" + 
				"      <div class=\"row\">\r\n" + 
				"        <div class=\"col-md-12\">\r\n" + 
				"          <div class=\"card\">\r\n" + 
				"            <div class=\"card-header\" >Random Games</div>\r\n" + 
				"            <div class=\"card-body\">\r\n" + 
				"<div id=\"carouselExampleIndicators\" class=\"carousel slide\" data-ride=\"carousel\">\r\n" + 
				"  <ol class=\"carousel-indicators\">\r\n" + 
				"    <li data-target=\"#carouselExampleIndicators\" data-slide-to=\"0\" class=\"active\"></li>\r\n" + 
				"    <li data-target=\"#carouselExampleIndicators\" data-slide-to=\"1\"></li>\r\n" + 
				"    <li data-target=\"#carouselExampleIndicators\" data-slide-to=\"2\"></li>\r\n" + 
				"  </ol>\r\n" + 
				"  <div class=\"carousel-inner\">\r\n" + 
				"    <div class=\"carousel-item active\">\r\n";

		if(toProcess.path.equalsIgnoreCase("browseview")) {
			MVMap<String, Game> m = db.s.openMap("Games");
		    List<String> games = new ArrayList<String>(m.keyList());
		    
		    Random rand = new Random();
		    String keyRandom = games.get(rand.nextInt(games.size()));
		    Game rand1 = m.get(keyRandom);
		    
		    
		stringToSendToWebBrowser += "      <a href =\"/play_game/"+rand1.id+"\"><img class=\"d-block w-100\" src=\"" + rand1.thumbnail + "\" alt=\"Second slide\" ></a>\r\n" + 
				"<div class=\"carousel-caption d-none d-md-block\">\r\n" + 
					"    <h5>"+ rand1.title+ "</h5>\r\n" + 
					"    <p>" + rand1.description + "</p>\r\n" + 
				"  </div>\r\n" +
				"    </div>\r\n" + 
				"    <div class=\"carousel-item\">\r\n";
		boolean unique1 = false;
		String keyRandom2 = "";
		while (unique1 == false) {
			Random randz = new Random();
			keyRandom2 = games.get(randz.nextInt(games.size()));
			if (keyRandom2 != keyRandom) {
				unique1 = true;
			}
		}
		Game rand2 = m.get(keyRandom2);
		
		stringToSendToWebBrowser +="      <a href =\"/play_game/"+rand2.id+"\"><img class=\"d-block w-100\" src=\"" + rand2.thumbnail + "\" alt=\"Second slide\" ></a>\r\n" + 
				"<div class=\"carousel-caption d-none d-md-block\">\r\n" + 
				"    <h5>"+rand2.title+"</h5>\r\n" + 
				"    <p>"+rand2.description+"</p>\r\n" + 
				" </div>\r\n" +
				"    </div>\r\n" + 
				"    <div class=\"carousel-item\">\r\n";
		boolean unique = false;
		String keyRandom1 = "";
		while (unique == false) {
			Random randx = new Random();
			keyRandom1 = games.get(randx.nextInt(games.size()));
			if (keyRandom1 != keyRandom && keyRandom1 != keyRandom2) {
				unique = true;
			}
		}
		Game rand3 = m.get(keyRandom1);
		
		stringToSendToWebBrowser += "      <a href =\"/play_game/"+rand3.id+"\"><img class=\"d-block w-100\" src=\"" + rand3.thumbnail + "\" alt=\"Second slide\" ></a>\r\n" +
				"<div class=\"carousel-caption d-none d-md-block\">\r\n" + 
				"    <h5>"+rand3.title+"</h5>\r\n" + 
				"    <p>"+rand3.description+"</p>\r\n" + 
				" </div>\r\n" +
				"    </div>\r\n" + 
				"  </div>\r\n" + 
				"  <a class=\"carousel-control-prev\" href=\"#carouselExampleIndicators\" role=\"button\" data-slide=\"prev\">\r\n" + 
				"    <span class=\"carousel-control-prev-icon\" aria-hidden=\"true\"></span>\r\n" + 
				"    <span class=\"sr-only\">Previous</span>\r\n" + 
				"  </a>\r\n" + 
				"  <a class=\"carousel-control-next\" href=\"#carouselExampleIndicators\" role=\"button\" data-slide=\"next\">\r\n" + 
				"    <span class=\"carousel-control-next-icon\" aria-hidden=\"true\"></span>\r\n" + 
				"    <span class=\"sr-only\">Next</span>\r\n" + 
				"  </a>\r\n" + 
				"</div>" +
				"            </div>\r\n" + 
				"          </div>\r\n" + 
				"        </div>\r\n" + 
				"      </div>\r\n" + 
				"    </div>\r\n" + 
				"  </div>" +
		"  <div class=\"py-5\">\r\n" + 
		"    <div class=\"container\">\r\n" + 
		"      <div class=\"row\">\r\n" + 
		"        <div class=\"col-md-12\">\r\n" + 
		"  <div class=\"card\" style=\"\">\r\n" + 
		"            <div class=\"card-header\"> Browse Games</div>\r\n" + 
		"            <div class=\"card-body\">\r\n" + 
		"			    <table id=\"myTable\">\r\n" + 
		"                <tbody>\r\n" + 
		"          <form class=\"form-inline\" role=\"form\" method=\"GET\" action=\"/searchresults.html\">\r\n" + 
		"            <div class=\"input-group\">\r\n" + 
		"              <input type=\"text\" class=\"form-control\" id=\"searchbox\" onkeyup=\"myFunction()\" name=\"searchstring\" placeholder=\"Search for title, description or user\">\r\n" + 
		"              <div class=\"input-group-append\"></div>\r\n" + 
		"            </div>\r\n" + 
		"          </form>\r\n" +
		"                  <tr>\r\n" + 
		"                    <th style=\"text-align:center\"></th>\r\n" + 
		"                    <th style=\"text-align:center\"></th>\r\n" +
		"                    <th style=\"text-align:center\"></th>\r\n" +
		"                  </tr>\r\n";
		
		    int x = 0;
		    
			for(String key : games) {
				Game g = m.get(key);
				
				if (x == 0) {
					stringToSendToWebBrowser += 
					
					"                  <tr>\r\n" + 
					"                    <td height=\"101\" width=\"101\"><a href =\"/play_game/"+g.id+"\"><img src=\""+g.thumbnail+"\" width=\"100\" height=\"100\" align=\"centre\"></a></td>\r\n" + 
					"                    <td><a class = \"alink\" href =\"/play_game/"+g.id+"\">"+g.title+"</a></td>\r\n" +
					"                    <td>"+g.description+"</td>\r\n" + 
					"                  </tr>\r\n";
				} else {
					stringToSendToWebBrowser += "                  <tr>\r\n" + 
							"                    <td height=\"101\" width=\"101\"><a href =\"/play_game/"+g.id+"\"><img src=\""+g.thumbnail+"\" width=\"100\" height=\"100\" align=\"centre\"></a></td>\r\n" + 
							"                    <td><a class = \"alink\" href=\"/play_game/"+g.id+"\">"+g.title+"</a></td>\r\n" + 
							"                    <td>"+g.description+"</td>\r\n" + 
							"                  </tr>\r\n";
				}
				
				x++;
				
			}
			stringToSendToWebBrowser += "                </tbody>\r\n" + 
					"              </table>" +
					"        </div>\r\n" + 
					"      </div>\r\n" + 
					"    </div>\r\n" + 
					"  </div>\r\n";
			
			stringToSendToWebBrowser += "  <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n" + 
					"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js\" integrity=\"sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut\" crossorigin=\"anonymous\"></script>\r\n" + 
					"  <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js\" integrity=\"sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k\" crossorigin=\"anonymous\"></script>\r\n" + 
					"<script type = \"text/javascript\">" +
					"function myFunction() {" +
					" var input, filter, table, tr, td, i, txtValue; " +
					" input = document.getElementById(\"searchbox\");" +
					" filter = input.value.toUpperCase();" +
					" table = document.getElementById(\"myTable\");" +
					" tr = table.getElementsByTagName(\"tr\");" +
					" for (i = 0; i < tr.length; i++) {" +
					"   td = tr[i].getElementsByTagName(\"td\")[1];" +
					"   tds = tr[i].getElementsByTagName(\"td\")[2];" +
					"   if (td) {"+
					      "txtValue = td.textContent || td.innerText;"+
					      "txtValues = tds.textContent || tds.innerText;"+
					      "if (txtValue.toUpperCase().indexOf(filter) > -1 || txtValues.toUpperCase().indexOf(filter) > -1) {"+
					        "tr[i].style.display = \"table-row\";"+
					      "} else {" +
					        "tr[i].style.display = \"none\";"+
					      "}" +
					    "}" +       
					  "}" +
					"}" +
					"</script>"+
					"</body>\r\n" + 
					"\r\n" + 
					"</html>";
			
			toProcess.r = new WebResponse( WebResponse.HTTP_OK,
					WebResponse.MIME_HTML, stringToSendToWebBrowser );
			return true;
		}
		return false;
	}

}